package com.practica1.rest.models;

public class Plan {

    private Integer id;
    private String descripcion;
    private Float monto;
    private Float watsProducidos;

    public Plan() {
    }

    public Plan(Integer id, String descripcion, Float monto, Float watsProducidos) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.watsProducidos = watsProducidos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    public Float getMonto() {
        return this.monto;
    }

    public void setMonto(Float value) {
        this.monto = value;
    }

    public Float getwatsProducidos() {
        return this.watsProducidos;
    }

    public void setwatsProducidos(Float value) {
        this.watsProducidos = value;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", watsProducidos=" + watsProducidos +
                '}';
    }
}
