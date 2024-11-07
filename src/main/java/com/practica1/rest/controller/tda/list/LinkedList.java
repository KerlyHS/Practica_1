package com.practica1.rest.controller.tda.list;
import com.practica1.rest.controller.tda.list.Exception.ListEmptyException;
import com.practica1.rest.controller.tda.list.Node;

public class LinkedList <E>{
    private Node <E> header; //header=cabecera
    private Node <E> last; //last= cola
    private Integer size; //size= tamaño

    //constructor que permite realizar una lista enlazada
    public LinkedList (){
        this.header= null;
        this.last= null;
        this.size= 0;
    }

    //1er metodo -->ver si esta vacia--- isEmpty= esta vacia (lista) 
    public boolean isEmpty(){
        return (this.header == null || this.size == 0);
    }

    //2do metodo --> agregar en cabeza
    public void addHeader (E dato){
        Node <E> help;
        if(isEmpty()){
            help = new Node<>(dato);
            header = help;
            last = help;
            
        } else {
            Node helpHeader = this.header;
            help = new Node<>(dato, helpHeader);
            this.header = help;
            //this.size++;
        }
        this.size++;
    }
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

    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header;
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }
    private E getFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        }
        return header.getInfo();
    }

    private E getLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        }
        return last.getInfo();
    }
    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }
    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    /*** END BY POSITION */
    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List Data \n");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" -> ");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }

        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////
    //METODO PARA TRANSFORMAR LA LISTA EN ARREGLO
    public E[]  toArray(){
        E[] matrix= null;
        if(!isEmpty()){
            Class clazz= header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);// (E[].. casteo de datos)
            Node <E> aux= header;
            for (int i=0; i<this.size; i++){
                matrix[i]= aux.getInfo();
                aux= aux.getNext();
            }
        }
        return matrix;
    }

    
    //METODO PARA TRANSFORMAR LA LISTA EN ARREGLO
    public LinkedList<E> toList(E[]matrix){
        reset(); //para resetear la lista 
        for (int i=0; i<matrix.length; i++){
            this.add(matrix[i]);
        }
        return this;
    }

    //METODO PARA ACTUALIZAR UN NODO
    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException{
        if(isEmpty()){
            throw new ListEmptyException("Error, Lista vacía");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()){
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0){
            header.setInfo(info);
        } else if (index.intValue() == (this.size-1)){
            last.setInfo(info);
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()){
                cont++;
                search = search.getNext();
            }
            search.setInfo(info);
        }
    }
    
}
