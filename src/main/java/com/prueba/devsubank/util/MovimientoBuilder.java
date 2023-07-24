package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPutReq;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.dto.MovimientoResponse;
import com.prueba.devsubank.enums.TipoMovimiento;

import java.math.BigDecimal;

public class MovimientoBuilder {

    public static Movimiento build(MovimientoPostReq movimientoPostReq, BigDecimal saldoAnterior, Cuenta cuenta) {
        Movimiento movimiento = new Movimiento();
        BigDecimal valorMovimiento = calcularSignoValorMovimiento(movimientoPostReq.getValor(),movimientoPostReq.getTipoMovimiento());
        movimiento.setFecha(movimientoPostReq.getFecha());
        movimiento.setValor(valorMovimiento);
        movimiento.setTipo(movimientoPostReq.getTipoMovimiento());
        movimiento.setSaldo(valorMovimiento.add(saldoAnterior));
        movimiento.setCuenta(cuenta);
        return movimiento;
    }

    public static Movimiento buildMovimientoAModificar(Movimiento oldMovimiento, MovimientoPutReq movimientoPutReq){
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(oldMovimiento.getFecha());
        movimiento.setId(oldMovimiento.getId());
        movimiento.setCuenta(oldMovimiento.getCuenta());

        BigDecimal saldoAnterior = oldMovimiento.getSaldo().subtract(oldMovimiento.getValor());
        BigDecimal valorMovimiento = calcularSignoValorMovimiento(movimientoPutReq.getValor(),movimientoPutReq.getTipoMovimiento());

        movimiento.setValor(valorMovimiento);
        movimiento.setTipo(movimientoPutReq.getTipoMovimiento());
        movimiento.setSaldo(valorMovimiento.add(saldoAnterior));

        return movimiento;
    }

    /**
     * Metodo para determinar el signo del valor de movimiento
     * @param valorMovimiento el valor del movimiento, siempre es positivo
     * @return Devuelve valorMovimiento en positivo si es CREDITO, y en negativo si es DEBITO
     */
    public static BigDecimal calcularSignoValorMovimiento(BigDecimal valorMovimiento, TipoMovimiento tipoMovimiento){
        if(tipoMovimiento.equals(TipoMovimiento.CREDITO)){
            return valorMovimiento;
        }
        return valorMovimiento.negate();

    }

    public static MovimientoResponse build(Movimiento movimiento,BigDecimal valor, TipoMovimiento tipoMovimiento) {
        MovimientoResponse movimientoResponse = new MovimientoResponse();
        movimientoResponse.setId(movimiento.getId());
        movimientoResponse.setFecha(movimiento.getFecha());
        movimientoResponse.setValor(valor);
        movimientoResponse.setTipoMovimiento(tipoMovimiento);
        movimientoResponse.setSaldo(movimiento.getSaldo());
        return movimientoResponse;

    }
}
