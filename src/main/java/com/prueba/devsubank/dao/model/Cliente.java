package com.prueba.devsubank.dao.model;

import com.prueba.devsubank.config.EncriptadorPassword;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Persona{

    @Column(name = "cliente_id",length = 64, nullable = false)
    private String clienteId;
    @Convert(converter = EncriptadorPassword.class)
    private String contrasena;

    private String estado;

    public String getContrasena() {
        return contrasena;
    }

    public String getEstado() {
        return estado;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}
