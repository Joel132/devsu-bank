package com.prueba.devsubank.service;

import com.prueba.devsubank.config.DevsuBankConfig;
import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.MovimientoRepository;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.MovimientoBuilder;
import com.prueba.devsubank.util.ReporteBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final DevsuBankConfig configProps;

    public MovimientoService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, MovimientoRepository movimientoRepository, DevsuBankConfig configProps) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.configProps = configProps;
    }

    public Long registrarMovimiento(MovimientoPostReq movimientoPostReq, Long cuentaId){
        Cuenta cuenta = obtenerCuenta(cuentaId);
        BigDecimal montoExtraidoHoy = obtenerMontoExtraidoHoy();
        BigDecimal saldoAntesMovimiento = obtenerSaldoAnterior(cuenta);
        Movimiento movimiento = MovimientoBuilder.build(movimientoPostReq,saldoAntesMovimiento,cuenta);
        if(movimiento.getSaldo().compareTo(BigDecimal.ZERO)<0){
            throw BankException.newBankException("00","Saldo No Disponible");
        }
        if(movimientoPostReq.getTipoMovimiento().equalsIgnoreCase("DEBITO") && montoExtraidoHoy.add(movimientoPostReq.getValor()).compareTo(configProps.getLimiteDiarioRetiro())>0){
            throw BankException.newBankException("00","Cupo diario Excedido");
        }
        movimiento = movimientoRepository.save(movimiento);
        return movimiento.getId();
    }

    private BigDecimal obtenerSaldoAnterior(Cuenta cuenta) {
        BigDecimal saldoAntesMovimiento = cuenta.getSaldoInicial();
        Optional<Movimiento> movimientoOptional = movimientoRepository.findFirstByCuentaOrderByIdDesc(cuenta);
        if(movimientoOptional.isPresent()){
            saldoAntesMovimiento = movimientoOptional.get().getSaldo();
        }
        return saldoAntesMovimiento;
    }

    private Cuenta obtenerCuenta(Long cuentaId) {
        Optional<Cuenta> cuentaO = cuentaRepository.findById(cuentaId);
        if(cuentaO.isEmpty()){
            //TODO: Lanzar error
            throw new RuntimeException("No se encuentra cuenta");
        }
        Cuenta cuenta = cuentaO.get();
        return cuenta;
    }

    public void eliminarMovimiento(Long movimientoId) {
        Optional<Movimiento> movimientoO = movimientoRepository.findById(movimientoId);
        if(movimientoO.isEmpty()){
            //TODO: NOT FOUND
        }
        Movimiento movimiento = movimientoO.get();
        List<Movimiento> movimientosPosteriores = movimientoRepository.findMovimientosByCuentaAndIdIsGreaterThan(movimiento.getCuenta(),movimiento.getId());
        if(movimientosPosteriores.size()>0){
            //TODO: SOLO SE PERMITE ELIMINAR MOVIMIENTOS QUE HAYAN SIDO ULTIMOS
            //TODO: SI SE ELIMINA UN MOVIMIENTOS HABRIA QUE DEJAR CONSISTENTES LOS POSTERIORES
            throw new RuntimeException("Solo el ultimo movimiento se puede eliminar");
        }

        movimientoRepository.delete(movimiento);


    }

    private BigDecimal obtenerMontoExtraidoHoy() {
        OffsetDateTime today = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS);

        OffsetDateTime tomorrow = today.plusDays(1);

        List<Movimiento> movimientos = movimientoRepository.findMovimientosByFechaBetweenAndTipoEqualsIgnoreCase(today, tomorrow,"DEBITO");

        if(CollectionUtils.isEmpty(movimientos)) return BigDecimal.ZERO;

        return movimientos.stream()
                .map(Movimiento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();//se suma valores negativos
    }

    public List<ReporteGetResponse> generarReporte(OffsetDateTime to, OffsetDateTime t1, Long clienteId) {
        return ReporteBuilder.build(movimientoRepository.findMovimientosByCuenta_Cliente_IdAndFechaBetweenOrderByIdAsc(clienteId,to,t1));
    }
}
