package com.prueba.devsubank.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {
    M("MASCULINO"),F("FEMENINO"),O("OTROS");
    private String descripcion;

    private Genero(String descripcion){
        this.descripcion = descripcion;
    }

    @JsonValue
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
