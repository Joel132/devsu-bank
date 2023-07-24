package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoResponse {

    private Long id;
    private LocalDate fecha;
    private BigDecimal valor;
    private BigDecimal saldo;
    private TipoMovimiento tipoMovimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovimientoResponse() {
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "MovimientoResponse{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", valor=" + valor +
                ", saldo=" + saldo +
                ", tipoMovimiento=" + tipoMovimiento +
                '}';
    }
}
