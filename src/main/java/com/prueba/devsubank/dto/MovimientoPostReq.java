package com.prueba.devsubank.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class MovimientoPostReq {

    private OffsetDateTime fecha;
    private BigDecimal valor;
    private String numeroCuenta;
    private String tipoMovimiento;

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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
