package com.practica1.rest.arreglos;

import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.controller.tda.list.Exception.ListEmptyException;
import com.practica1.rest.models.Inversionista;

public class InversionistaArray {
    private LinkedList<Inversionista> inversionistaList;

    // Constructor
    public InversionistaArray() {
        this.inversionistaList = new LinkedList<>();
    }

    // Método para agregar un inversionista
    public void addInversionista(Inversionista inversionista) {
        inversionistaList.add(inversionista);
    }

    // Método para obtener un inversionista por índice
    public Inversionista getInversionista(int index) throws ListEmptyException, IndexOutOfBoundsException {
        return inversionistaList.get(index);
    }

    // Método para actualizar un inversionista
    public void updateInversionista(Inversionista inversionista, int index) throws ListEmptyException, IndexOutOfBoundsException {
        inversionistaList.update(inversionista, index);
    }

    // Método para convertir la lista a un arreglo
    public Inversionista[] toArray() {
        return inversionistaList.toArray();
    }

    // Método para mostrar todos los inversionistas
    public void mostrarInversionistas() {
        System.out.println(inversionistaList.toString());
    }
}

