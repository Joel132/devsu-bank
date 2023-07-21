package com.prueba.devsubank.dto;

public class ClienteResponse {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ClienteResponse{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
