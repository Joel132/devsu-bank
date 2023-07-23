package com.prueba.devsubank.dao.model;

import com.prueba.devsubank.enums.Genero;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="numero_documento", length=30, nullable=false)
    @Size(max = 30)
    @NotBlank
    private String numeroDocumento;

    @Column(name="tipo_documento", length=5, nullable=false)
    @Size(max = 5)
    @NotBlank
    private String tipoDocumento;

    @Column(name="nombre", length=100, nullable=false)
    @NotBlank
    @Size(max = 100)
    private String nombre;

    @Column(name="genero", length=10, nullable=false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Genero genero;

    @Column(name="edad", nullable=false)
    @NotNull
    @Min(18)
    private Integer edad;

    @Column(name="direccion", length=100, nullable=false)
    @NotBlank
    @Size(max = 100)
    private String direccion;

    @Column(name="telefono", length=20, nullable=false)
    @NotBlank
    @Size(max = 20)
    private String telefono;


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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", genero=" + genero +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
