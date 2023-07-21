package com.prueba.devsubank.dao.model;

import jakarta.persistence.*;

@MappedSuperclass
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="genero", length=10, nullable=false)
    private String genero;

    @Column(name="edad", nullable=false)
    private Integer edad;

    @Column(name="direccion", length=100, nullable=true)
    private String direccion;

    @Column(name="telefono", length=20, nullable=true)
    private String telefono;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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
}
