package com.practica1.rest.arreglos;

import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.controller.tda.list.Exception.ListEmptyException;
import com.practica1.rest.models.Proyecto;

public class ProyectoArray {
    private LinkedList<Proyecto> proyectoList;

    // Constructor
    public ProyectoArray() {
        this.proyectoList = new LinkedList<>();
    }

    // Método para agregar un proyecto
    public void addProyecto(Proyecto proyecto) {
        proyectoList.add(proyecto);
    }

    // Método para obtener un proyecto por índice
    public Proyecto getProyecto(int index) throws ListEmptyException, IndexOutOfBoundsException {
        return proyectoList.get(index);
    }

    // Método para actualizar un proyecto
    public void updateProyecto(Proyecto proyecto, int index) throws ListEmptyException, IndexOutOfBoundsException {
        proyectoList.update(proyecto, index);
    }

    // Método para convertir la lista a un arreglo
    public Proyecto[] toArray() {
        return proyectoList.toArray();
    }

    // Método para mostrar todos los proyectos
    public void mostrarProyectos() {
        System.out.println(proyectoList.toString());
    }
}
