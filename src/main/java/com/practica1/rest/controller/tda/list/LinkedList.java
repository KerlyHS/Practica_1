package com.practica1.rest.controller.tda.list;

import com.practica1.rest.controller.tda.list.Exception.ListEmptyException;

public class LinkedList<E> {
    private Node<E> header; // header=cabecera
    private Node<E> last; // last=cola
    private Integer size; // size=tamaño

    // Constructor
    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    // Ver si está vacía
    public boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    // Agregar en cabeza
    public void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            last = help;  // Establecer last cuando se agrega el primer nodo
        } else {
            Node<E> helpHeader = this.header;
            help = new Node<>(dato, helpHeader);
            this.header = help;
        }
        this.size++;
    }

    // Agregar en cola
    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            addHeader(info);
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
            this.size++;
        }
    }

    public void add(E info) {
        addLast(info);
    }

    // Obtener nodo por índice
    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index == 0) {
            return header;
        } else if (index == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            for (int cont = 0; cont < index; cont++) {
                search = search.getNext();
            }
            return search;
        }
    }

    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        }
        return getNode(index).getInfo();
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index == 0) {
            addHeader(info);
        } else if (index == this.size) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List Data \n");
        if (isEmpty()) {
            sb.append("La lista está vacía.\n");
        } else {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" -> ");
                help = help.getNext();
            }
            sb.append("null");
        }
        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }

    // Método para transformar la lista en un arreglo
    public E[] toArray() {
        E[] matrix = null;
        if (!isEmpty()) {
            Class<?> clazz = header.getInfo().getClass();
            @SuppressWarnings("unchecked")
            E[] tempMatrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            matrix = tempMatrix;
            Node<E> aux = header;
            for (int i = 0; i < this.size; i++) {
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matrix;
    }

    // Método para transformar un arreglo en una lista
    public LinkedList<E> toList(E[] matrix) {
        reset(); // para resetear la lista
        for (E element : matrix) {
            this.add(element);
        }
        return this;
    }

    public void removeAt(int intValue) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAt'");
    }


    public void set(E object, int index) {
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    public void removeAt(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAt'");
    }   
    
}
