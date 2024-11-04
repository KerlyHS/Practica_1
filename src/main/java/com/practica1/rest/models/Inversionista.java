package com.practica1.rest.models;

import java.io.Serializable;

public class Inversionista implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private String apellido;
    private Float montoI;

    // Constructor sin parámetros
    public Inversionista() {
    }

    // Constructor con parámetros
    public Inversionista(Integer id, String nombre, String apellido, Float montoI) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.montoI = montoI;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Float getMontoI() {
        return montoI;
    }

    public void setMontoI(Float montoI) {
        this.montoI = montoI;
    }

    // Método toString para facilitar la impresión del objeto
    @Override
    public String toString() {
        return "Inversionista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", montoI=" + montoI +
                '}';
    }
}
