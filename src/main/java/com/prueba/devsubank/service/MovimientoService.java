package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.MovimientoRepository;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.util.MovimientoBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public MovimientoService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public void registrarMovimiento(MovimientoPostReq movimientoPostReq){
        Optional<Cuenta> cuentaO = cuentaRepository.findCuentaByNumero(movimientoPostReq.getNumeroCuenta());
        if(cuentaO.isEmpty()){
            //TODO: Lanzar error
            throw new RuntimeException("No se encuentra cuenta");
        }
        BigDecimal saldoAntesMovimiento = cuentaO.get().getSaldoInicial();
        Cuenta cuenta = cuentaO.get();
        Optional<Movimiento> movimientoOptional = movimientoRepository.findFirstByCuentaOrderByIdDesc(cuentaO.get());
        if(movimientoOptional.isPresent()){
            saldoAntesMovimiento = movimientoOptional.get().getSaldo();
        }
        Movimiento movimiento = MovimientoBuilder.build(movimientoPostReq,saldoAntesMovimiento);
        movimiento.setCuenta(cuentaO.get());
        movimientoRepository.save(movimiento);
    }
}
