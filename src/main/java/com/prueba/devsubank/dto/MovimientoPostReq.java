package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.TipoMovimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MovimientoPostReq {

    private OffsetDateTime fecha;
    @PositiveOrZero
    private BigDecimal valor;
    @NotNull
    private TipoMovimiento tipoMovimiento;

    public MovimientoPostReq() {
        this.fecha = OffsetDateTime.now();
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

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
        return "MovimientoPostReq{" +
                "fecha=" + fecha +
                ", valor=" + valor +
                ", tipoMovimiento=" + tipoMovimiento +
                '}';
    }
}
