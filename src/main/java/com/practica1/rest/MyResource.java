package com.practica1.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.practica1.rest.controller.dao.ProyectoDao;
import com.practica1.rest.controller.dao.InversionistaDao; // Asegúrate de tener un DAO para Inversionista
import com.practica1.rest.models.Proyecto;
import com.practica1.rest.models.Inversionista;

/**
 * Recurso raíz (expuesto en la ruta "proyectos")
 */
@Path("proyectos")
public class MyResource {

    /**
     * Método que maneja las solicitudes HTTP GET. El objeto devuelto se enviará
     * al cliente como tipo de medio JSON.
     *
     * @return Respuesta que se devolverá como respuesta JSON.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap<String, Object> mapa = new HashMap<>();
        ProyectoDao pd = new ProyectoDao();
        InversionistaDao id = new InversionistaDao(); // Asegúrate de tener un DAO para manejar Inversionistas

        try {
            // Crear una lista para almacenar múltiples proyectos
            List<Proyecto> proyectos = new ArrayList<>();

            // Crear el primer proyecto
            Proyecto proyecto1 = new Proyecto();
            proyecto1.setNombre("Cortes de luz");
            proyecto1.setFechaInicio("2021-05-01");
            proyecto1.setFechaFin("2021-05-30");
            proyecto1.setEstado("Activo");
            proyectos.add(proyecto1);

            // Crear el segundo proyecto
            Proyecto proyecto2 = new Proyecto();
            proyecto2.setNombre("Mantenimiento de transformador");
            proyecto2.setFechaInicio("2021-06-01");
            proyecto2.setFechaFin("2021-06-15");
            proyecto2.setEstado("Completo");
            proyectos.add(proyecto2);

            // Crear el tercer proyecto
            Proyecto proyecto3 = new Proyecto();
            proyecto3.setNombre("Instalación de paneles solares");
            proyecto3.setFechaInicio("2021-07-01");
            proyecto3.setFechaFin("2021-07-30");
            proyecto3.setEstado("En progreso");
            proyectos.add(proyecto3);

            // Guardar los proyectos en el almacenamiento
            for (Proyecto proyecto : proyectos) {
                pd.setProyecto(proyecto);
                pd.save();
            }

            // Crear una lista para almacenar múltiples inversionistas
            List<Inversionista> inversionistas = new ArrayList<>();

            // Crear el primer inversionista
            Inversionista inversionista1 = new Inversionista();
            inversionista1.setId(1);
            inversionista1.setNombre("Juan");
            inversionista1.setApellido("Pérez");
            inversionista1.setMontoI(5000.0f);
            inversionistas.add(inversionista1);

            // Crear el segundo inversionista
            Inversionista inversionista2 = new Inversionista();
            inversionista2.setId(2);
            inversionista2.setNombre("María");
            inversionista2.setApellido("Gómez");
            inversionista2.setMontoI(7500.0f);
            inversionistas.add(inversionista2);

            // Crear el tercer inversionista
            Inversionista inversionista3 = new Inversionista();
            inversionista3.setId(3);
            inversionista3.setNombre("Carlos");
            inversionista3.setApellido("López");
            inversionista3.setMontoI(10000.0f);
            inversionistas.add(inversionista3);

            // Guardar los inversionistas en el almacenamiento
            for (Inversionista inversionista : inversionistas) {
                id.setInversionista(inversionista);
                id.save();
            }

            // Verifica el estado de la lista de proyectos
            String aux = pd.getListAll().isEmpty() ? "La lista de proyectos está vacía" : "La lista de proyectos tiene elementos";

            mapa.put("msg", "Proyectos e inversionistas guardados correctamente");
            mapa.put("data", aux);
            return Response.ok(mapa).build();

        } catch (Exception e) {
            mapa.put("error", "Error al procesar la solicitud: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }
    }
}
