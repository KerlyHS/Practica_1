package com.practica1.rest.models;

public class Inversionista {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private Float montoI;

    public Inversionista() {
    }

    public Inversionista(Integer id, String nombre, String apellido, Float montoI, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.montoI = montoI;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Float getMontoI() {
        return this.montoI;
    }

    public void setMontoI(Float montoI) {
        this.montoI = montoI;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


  @Override
    public String toString() {
        return "Inversionista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", montoI=" + montoI +
                ", dni='" + dni + '\'' +
                '}';
    }
}
