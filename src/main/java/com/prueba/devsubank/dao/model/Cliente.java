package com.prueba.devsubank.dao.model;

import com.prueba.devsubank.config.EncriptadorPassword;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Persona{

    @Column(name = "cliente_id",length = 64, nullable = false)
    private String clienteId;
    @Convert(converter = EncriptadorPassword.class)
    private String contrasena;

    @Column(columnDefinition = "varchar(16) default 'Activo'")
    private String estado;
    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

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

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
