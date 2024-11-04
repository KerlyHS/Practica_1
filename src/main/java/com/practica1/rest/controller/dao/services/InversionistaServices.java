package com.practica1.rest.controller.dao.services;
import com.practica1.rest.controller.dao.InversionistaDao;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.models.Inversionista;

public class InversionistaServices {
    private InversionistaDao obj;

    public InversionistaServices() {
        obj = new InversionistaDao();
    }
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    @SuppressWarnings("rawtypes")
    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Inversionista getInversionista() {
        return obj.getInversionista();
    }

    public void setInversionista(Inversionista inversionista) {
        obj.setInversionista(inversionista);
    }

}

