package com.practica1.rest.arreglos;

import com.practica1.rest.controller.tda.list.Exception.ListEmptyException;
import com.practica1.rest.controller.tda.list.LinkedList;
import com.practica1.rest.models.Plan;

public class PlanArray {
    private LinkedList<Plan> planList;

    // Constructor
    public PlanArray() {
        this.planList = new LinkedList<>();
    }

    // Método para agregar un plan
    public void addPlan(Plan plan) {
        planList.add(plan);
    }

    // Método para obtener un plan por índice
    public Plan getPlan(int index) throws ListEmptyException, IndexOutOfBoundsException {
        return planList.get(index);
    }

    // Método para actualizar un plan
    public void updatePlan(Plan plan, int index) throws ListEmptyException, IndexOutOfBoundsException {
        planList.update(plan, index);
    }

    // Método para convertir la lista a un arreglo
    public Plan[] toArray() {
        return planList.toArray();
    }

    // Método para mostrar todas los planes
    public void mostrarPlan() {
        System.out.println(planList.toString());
    }
}

