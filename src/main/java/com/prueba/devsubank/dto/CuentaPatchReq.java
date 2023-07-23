package com.prueba.devsubank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

//TODO: poner annotations para validaciones
public class CuentaPatchReq {

    @NotBlank
    @Size(min = 1, max = 64)
    private String numero;
    @NotBlank
    @Size(min = 1, max = 16)
    private String tipo;
    @NotNull
    private Boolean activo;


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


    @Override
    public String toString() {
        return "CuentaPatchReq{" +
                "numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", activo=" + activo +
                '}';
    }
}
