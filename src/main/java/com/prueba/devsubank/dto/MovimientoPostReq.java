package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.TipoMovimiento;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoPostReq {

    private LocalDate fecha;
    @Positive
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
