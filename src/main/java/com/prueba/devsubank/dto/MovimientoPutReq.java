package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class MovimientoPutReq {

    @PositiveOrZero
    @NotNull
    private BigDecimal valor;
    @NotNull
    private TipoMovimiento tipoMovimiento;


    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    @Override
    public String toString() {
        return "MovimientoPutReq{" +
                ", valor=" + valor +
                ", tipoMovimiento=" + tipoMovimiento +
                '}';
    }
}
