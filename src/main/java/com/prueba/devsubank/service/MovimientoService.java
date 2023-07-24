package com.prueba.devsubank.service;

import com.prueba.devsubank.config.DevsuBankConfig;
import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.MovimientoRepository;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPutReq;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.dto.MovimientoResponse;
import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.enums.TipoMovimiento;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.MovimientoBuilder;
import com.prueba.devsubank.util.ReporteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    Logger logger = LoggerFactory.getLogger(MovimientoService.class);
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final DevsuBankConfig configProps;

    public MovimientoService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, MovimientoRepository movimientoRepository, DevsuBankConfig configProps) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.configProps = configProps;
    }

    public MovimientoResponse registrarMovimiento(MovimientoPostReq movimientoPostReq, Long cuentaId){
        logger.debug("Obteniendo cuenta {}",cuentaId);
        Cuenta cuenta = obtenerCuenta(cuentaId);
        logger.debug("Cuenta obtenida {}",cuenta);
        if(!cuenta.getActivo() || !cuenta.getCliente().getActivo()){
            logger.error("Cliente no posee cuenta activa");
            throw BankException.newBankException("00","Cuenta inactiva");
        }
        Movimiento ultimoMovimiento = obtenerUltimoMovimiento(cuenta);
        logger.debug("El ultimo movimiento registrado para esta cuenta es: {}",ultimoMovimiento);
        if(movimientoPostReq.getFecha().isBefore(ultimoMovimiento.getFecha())){
            logger.error("No se puede registrar movimiento con fecha inferior al ultimo movimiento");
            throw BankException.newBankException("00","Fecha de movimiento invalida");
        }
        Movimiento movimiento = MovimientoBuilder.build(movimientoPostReq,ultimoMovimiento.getSaldo(),cuenta);
        if(movimientoPostReq.getTipoMovimiento().equals(TipoMovimiento.DEBITO)){
            //validaciones para debito
            BigDecimal montoExtraidoHoy = obtenerMontoExtraidoDelDia(movimientoPostReq.getFecha());
            logger.debug("Monto extraido durante el dia de hoy {}",montoExtraidoHoy);
            BigDecimal saldoDespuesMovimiento = movimiento.getSaldo();
            BigDecimal montoExtraidoDelDia = montoExtraidoHoy.add(movimientoPostReq.getValor());//incluye movimiento a agregar
            verificarDebito(saldoDespuesMovimiento, montoExtraidoDelDia);
        }
        movimiento = movimientoRepository.save(movimiento);
        logger.debug("Movimiento agregado con exito: {}",movimiento);
        return MovimientoBuilder.build(movimiento,movimientoPostReq.getValor(),movimientoPostReq.getTipoMovimiento());
    }

    public void eliminarMovimiento(Long movimientoId) {
        Optional<Movimiento> movimientoO = movimientoRepository.findById(movimientoId);
        if(movimientoO.isEmpty()){
            throw BankException.newBankException("00","No se encuentra movimiento");
        }
        Movimiento movimiento = movimientoO.get();
        List<Movimiento> movimientosPosteriores = movimientoRepository.findMovimientosByCuentaAndIdIsGreaterThan(movimiento.getCuenta(),movimiento.getId());
        if(movimientosPosteriores.size()>0){
            throw BankException.newBankException("00","Solo el ultimo movimiento se puede eliminar");
        }

        movimientoRepository.delete(movimiento);
    }

    public MovimientoResponse modificarMovimiento(Long movimientoId, MovimientoPutReq movimientoPutReq) {
        Optional<Movimiento> movimientoO = movimientoRepository.findById(movimientoId);
        if(movimientoO.isEmpty()){
            throw BankException.newBankException("00","No se encuentra movimiento");
        }
        Movimiento oldMovimiento = movimientoO.get();
        List<Movimiento> movimientosPosteriores = movimientoRepository.findMovimientosByCuentaAndIdIsGreaterThan(oldMovimiento.getCuenta(),oldMovimiento.getId());
        if(movimientosPosteriores.size()>0){
            throw BankException.newBankException("00","Solo el ultimo movimiento se puede modificar");
        }

        Movimiento movimientoAModificar = MovimientoBuilder.buildMovimientoAModificar(oldMovimiento, movimientoPutReq);

        if(movimientoPutReq.getTipoMovimiento().equals(TipoMovimiento.DEBITO)){
            //validaciones para debito
            BigDecimal valorNoConsiderable = oldMovimiento.getValor().negate();//si el movimiento a modificar era DEBITO entonces se excluye del monto extraido
            if(oldMovimiento.getTipo().equals(TipoMovimiento.CREDITO)){
                valorNoConsiderable = BigDecimal.ZERO;
            }
            BigDecimal montoExtraidoEnElDiaMovimiento = obtenerMontoExtraidoDelDia(oldMovimiento.getFecha()).subtract(valorNoConsiderable);
            logger.debug("Monto extraido durante el dia sin movimiento a modificar {} -> {}",oldMovimiento.getFecha(),montoExtraidoEnElDiaMovimiento);
            BigDecimal saldoDespuesMovimiento = movimientoAModificar.getSaldo();
            BigDecimal montoExtraidoDelDia = montoExtraidoEnElDiaMovimiento.add(movimientoPutReq.getValor());//incluye movimiento a modificar
            verificarDebito(saldoDespuesMovimiento, montoExtraidoDelDia);
        }

        return MovimientoBuilder.build(movimientoRepository.save(movimientoAModificar),movimientoPutReq.getValor(),movimientoPutReq.getTipoMovimiento());
    }

    private void verificarDebito(BigDecimal saldoDespuesMovimiento, BigDecimal montoExtraidoDelDia) {
        if(saldoDespuesMovimiento.compareTo(BigDecimal.ZERO)<0){
            logger.error("Saldo no disponible");
            throw BankException.newBankException("00","Saldo No Disponible");
        }
        if(montoExtraidoDelDia.compareTo(configProps.getLimiteDiarioRetiro())>0){
            logger.error("Cupo diario excedido");
            throw BankException.newBankException("00","Cupo diario Excedido");
        }
    }

    private Movimiento obtenerUltimoMovimiento(Cuenta cuenta){
        Optional<Movimiento> movimientoOptional = movimientoRepository.findFirstByCuentaOrderByIdDesc(cuenta);
        if(movimientoOptional.isPresent()){
            return movimientoOptional.get();
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setFecha(LocalDate.MIN);
        return movimiento;
    }

    private Cuenta obtenerCuenta(Long cuentaId) {
        Optional<Cuenta> cuentaO = cuentaRepository.findById(cuentaId);
        if(cuentaO.isEmpty()){
            throw BankException.newBankException("00","No se encuentra cuenta");
        }
        Cuenta cuenta = cuentaO.get();
        return cuenta;
    }

    private BigDecimal obtenerMontoExtraidoDelDia(LocalDate date) {


        List<Movimiento> movimientos = movimientoRepository.findMovimientosByFechaBetweenAndTipoEquals(date, date,TipoMovimiento.DEBITO);

        if(CollectionUtils.isEmpty(movimientos)) return BigDecimal.ZERO;

        return movimientos.stream()
                .map(Movimiento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();//se suma valores negativos
    }

    public List<ReporteGetResponse> generarReporte(LocalDate to, LocalDate t1, Long clienteId) {
        return ReporteBuilder.build(movimientoRepository.findMovimientosByCuenta_Cliente_IdAndFechaBetweenOrderByIdAsc(clienteId,to,t1));
    }
}
