package com.gs1.drawyourrouteapi.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Persona;

/**
 *
 * @author
 */
@Path("/map")
public class JavaEE8Resource {
    
    @GET
    @Path("/saludo")
    @Produces(MediaType.APPLICATION_JSON)
    public String dimeHola() {
        return "Hola";
    }
    
    @POST
    @Path("/adios")
    @Produces(MediaType.APPLICATION_JSON)
    public String dimeAdios() {
        return "Adios";
    }
}
