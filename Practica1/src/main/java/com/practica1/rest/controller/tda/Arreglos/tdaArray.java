package com.practica1.rest.controller.tda.arreglos;

import java.util.Arrays;

public class tdaArray<E> {
    private E[] elements;
    private int size;
    private static final int INITIAL_CAPACITY = 10; // Capacidad inicial del arreglo

    // Constructor
    public tdaArray() {
        this.elements = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Verifica si el arreglo está vacío
    public boolean isEmpty() {
        return size == 0;
    }

    // Añadir elemento al final
    public void add(E info) {
        ensureCapacity();
        elements[size++] = info;
    }

    // Añadir elemento en una posición específica
    public void add(E info, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = info;
        size++;
    }

    // Obtener un elemento por índice
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        }
        return elements[index];
    }

    // Elimina un elemento en una posición específica
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        }
        E element = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // Ayuda al recolector de basura
        return element;
    }

    // Obtener el tamaño del arreglo
    public int getSize() {
        return size;
    }

    // Convierte la estructura a un arreglo
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    // Asegura la capacidad del arreglo, duplicando el tamaño si está lleno
    private void ensureCapacity() {
        if (size == elements.length) {
            E[] newElements = (E[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ArrayList Data: \n");
        if (isEmpty()) {
            sb.append("El arreglo está vacío.\n");
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(elements[i]).append(" -> ");
            }
            sb.append("null"); // Para mostrar el final de la lista
        }
        return sb.toString();
    }
}
