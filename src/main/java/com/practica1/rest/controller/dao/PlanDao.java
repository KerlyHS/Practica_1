package com.practica1.rest.controller.dao;

import com.practica1.rest.controller.dao.implement.AdapterDao;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.models.Plan;

public class PlanDao extends AdapterDao<Plan> {
    private Plan plan;
    private LinkedList listAll;

    public PlanDao() {
        super(Plan.class);
    }

    public Plan getPlan() {
        if (plan == null) {
            plan = new Plan();
        }
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        plan.setId(id);
        this.persist(this.plan);
        return true;
    }
    public Boolean update() throws Exception {
        this.merge(getPlan(), getPlan().getId());
        return true;
    }

}

