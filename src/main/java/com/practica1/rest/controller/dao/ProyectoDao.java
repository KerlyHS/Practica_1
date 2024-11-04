package com.practica1.rest.controller.dao;

import com.practica1.rest.controller.dao.implement.AdapterDao;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.models.Proyecto;

public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto;
    private LinkedList<Proyecto> listAll;

    // Constructor que pasa los parámetros requeridos al constructor de AdapterDao
    public ProyectoDao() {
        super(Proyecto.class, true); // true para usar LinkedList
    }

    public Proyecto getProyecto() {
        if (proyecto == null) {
            proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getListAll() {
        if (listAll == null) {
            this.listAll = super.listAll(); // Usa super.listAll() para cargar desde JSON
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1; // Asignación de un ID único
        proyecto.setId(id);
        this.persist(this.proyecto); // Persistir el proyecto en JSON
        return true;
    }

}
