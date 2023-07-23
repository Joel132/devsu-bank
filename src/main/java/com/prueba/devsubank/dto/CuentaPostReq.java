package com.prueba.devsubank.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

//TODO: poner annotations para validaciones
public class CuentaPostReq {

    @NotBlank
    @Size(min = 1, max = 64)
    private String numero;
    @NotBlank
    @Size(min = 1, max = 16)
    private String tipo;
    @NotNull
    private Boolean activo;
    @NotNull
    @PositiveOrZero
    private BigDecimal saldoInicial;
    @NotBlank
    @Size(max = 8)
    private String moneda;


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }


    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "CuentaPostReq{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", activo=" + activo +
                ", saldoInicial=" + saldoInicial +
                ", moneda='" + moneda + '\'' +
                '}';
    }
}
