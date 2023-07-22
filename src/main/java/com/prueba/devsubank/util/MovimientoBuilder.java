package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.MovimientoPostReq;

import java.math.BigDecimal;

public class MovimientoBuilder {

    public static Movimiento build(MovimientoPostReq movimientoPostReq, BigDecimal saldoAnterior) {
        Movimiento movimiento = new Movimiento();
        BigDecimal valorMovimiento = movimientoPostReq.getTipoMovimiento().equalsIgnoreCase("CREDITO")?movimientoPostReq.getValor():movimientoPostReq.getValor().negate();
        movimiento.setFecha(movimientoPostReq.getFecha());
        movimiento.setValor(valorMovimiento);
        movimiento.setTipo(movimientoPostReq.getTipoMovimiento());
        movimiento.setSaldo(valorMovimiento.add(saldoAnterior));
        return movimiento;
    }

}