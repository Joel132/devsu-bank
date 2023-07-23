package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.TipoMovimiento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoPostReq {

    private LocalDate fecha;
    @PositiveOrZero
    @NotNull
    private BigDecimal valor;
    @NotNull
    private TipoMovimiento tipoMovimiento;

    public MovimientoPostReq() {
        this.fecha = LocalDate.now();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
