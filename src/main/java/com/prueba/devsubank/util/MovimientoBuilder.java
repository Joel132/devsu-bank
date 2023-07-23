package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.enums.TipoMovimiento;

import java.math.BigDecimal;

public class MovimientoBuilder {

    public static Movimiento build(MovimientoPostReq movimientoPostReq, BigDecimal saldoAnterior, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        BigDecimal valorMovimiento = movimientoPostReq.getTipoMovimiento().equals(TipoMovimiento.CREDITO)?movimientoPostReq.getValor():movimientoPostReq.getValor().negate();
        movimiento.setFecha(movimientoPostReq.getFecha());
        movimiento.setValor(valorMovimiento);
        movimiento.setTipo(movimientoPostReq.getTipoMovimiento());
        movimiento.setSaldo(valorMovimiento.add(saldoAnterior));
        movimiento.setCuenta(cuenta);
        return movimiento;
    }

}
