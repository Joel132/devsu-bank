package com.prueba.devsubank.dto;

import com.prueba.devsubank.enums.Genero;
import jakarta.validation.constraints.*;

public class ClientePostReq {

    @NotBlank
    @Size(max = 100)
    private String nombre;
    @NotNull
    private Genero genero;
    @NotNull
    @Min(18)
    private Integer edad;
    @NotBlank
    @Size(max = 100)
    private String direccion;
    @NotBlank
    @Size(max = 20)
    private String telefono;
    @NotBlank
    @Size(min = 8, max = 255)
    private String contrasena;
    @NotBlank
    @Size(max = 64)
    private String clienteId;
    //TODO: crear clase identificacion
    @NotBlank
    @Size(max = 5)
    private String tipoDocumento;
    @NotBlank
    @Size(max = 30)
    private String numeroDocumento;
    @NotNull
    private Boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ClientePostReq{" +
                "nombre='" + nombre + '\'' +
                ", genero=" + genero +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", clienteId='" + clienteId + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", activo=" + activo +
                '}';
    }
}
