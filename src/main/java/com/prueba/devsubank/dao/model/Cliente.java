package com.prueba.devsubank.dao.model;

import com.prueba.devsubank.config.EncriptadorPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "cliente",uniqueConstraints = { @UniqueConstraint(columnNames = { "numero_documento", "tipo_documento" }) })
public class Cliente extends Persona{

    @Column(name = "cliente_id",length = 64, nullable = false)
    @NotBlank
    @Size(max = 64)
    private String clienteId;
    @Convert(converter = EncriptadorPassword.class)
    @Size(min = 8, max = 255)
    private String contrasena;

    @Column(name = "activo", nullable = false)
    @NotNull
    private Boolean activo;
    @OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;

    public String getContrasena() {
        return contrasena;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId='" + clienteId + '\'' +
                ", activo=" + activo +
                ", cuentas=" + cuentas +
                "} " + super.toString();
    }
}
