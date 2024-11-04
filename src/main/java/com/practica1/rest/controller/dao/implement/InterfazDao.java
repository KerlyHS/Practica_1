package com.practica1.rest.controller.dao.implement;
import com.practica1.rest.controller.tda.list.LinkedList;

public interface InterfazDao <T> {
    public void persist(T object) throws Exception;
    public void merge(T object, Integer index) throws Exception;
    public LinkedList<T> listAll();
    public T get(Integer id) throws Exception;    
    public void delete(Integer id) throws Exception;
    
}
