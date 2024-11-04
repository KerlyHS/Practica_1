package com.practica1.rest.controller.dao;

import com.practica1.rest.controller.dao.implement.AdapterDao;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.models.Inversionista;

public class InversionistaDao extends AdapterDao<Inversionista> {
    public static boolean useLinkedList = true; // Valor por defecto
    private Inversionista inversionista;
    private LinkedList<Inversionista> listAll;

    public InversionistaDao() {
        super(Inversionista.class, useLinkedList);
    }

    public Inversionista getInversionista() {
        if (inversionista == null) {
            inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public LinkedList<Inversionista> getListAll() {
        if (listAll == null) {
            this.listAll = super.listAll(); // Usa super.listAll() para inicializar correctamente
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1; // Asignar un ID único
        inversionista.setId(id);
        this.persist(this.inversionista); // Guardar el inversionista en JSON
        return true;
    }
}
