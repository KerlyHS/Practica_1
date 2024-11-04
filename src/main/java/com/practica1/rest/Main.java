package com.practica1.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Clase principal para iniciar el servidor HTTP.
 */
public class Main {
    // URI base que el servidor HTTP Grizzly escuchará
    public static final String BASE_URI = "http://localhost:8099/myapp/";

    /**
     * Inicia el servidor HTTP Grizzly exponiendo los recursos JAX-RS definidos en esta aplicación.
     * @return servidor HTTP Grizzly.
     */
    public static HttpServer startServer() {
        // Crear una configuración de recursos que escanea el paquete de recursos
        final ResourceConfig rc = new ResourceConfig().packages("com.practica1.rest");

        // Crear y iniciar una nueva instancia del servidor HTTP Grizzly
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Método principal.
     * @param args argumentos de línea de comandos.
     * @throws IOException en caso de un error de entrada/salida.
     */
    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();
            System.out.println(String.format("Aplicación Jersey iniciada con WADL disponible en "
                    + "%sapplication.wadl\nPresione Enter para detenerlo...", BASE_URI));
            System.in.read();
            server.stop();
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
