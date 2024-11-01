package com.practica1.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.practica1.rest.controller.dao.ProyectoDao;
import com.practica1.rest.controller.tda.list.LinkedList;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as JSON media type.
     *
     * @return Response that will be returned as JSON response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap<String, Object> mapa = new HashMap<>();
        ProyectoDao pd = new ProyectoDao();
        String aux = "";

        try {
            pd.getProyecto().setNombre("Cortes de luz");
            pd.getProyecto().setFechaInicio("2021-05-01");
            pd.getProyecto().setFechaFin("2021-05-30");
            pd.getProyecto().setEstado("Activo");
            pd.save();

            // Verifica si la lista está vacía
            aux = pd.listAll().isEmpty() ? "La lista está vacía" : "La lista tiene elementos";
        } catch (Exception e) {
            mapa.put("error", "Error al procesar la solicitud: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }

        mapa.put("msg", "Ok");
        mapa.put("data", "test " + aux);
        return Response.ok(mapa).build();
    }
}
