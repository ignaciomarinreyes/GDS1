package com.gs1.drawyourrouteapi.resources;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.MuchasPersonas;
import model.Persona;

/**
 *
 * @author
 */
@Path("/")
public class JavaEE8Resource {
    
    @Context
    HttpServletRequest request;
    
    private MuchasPersonas mp;
    private Persona p1;
    private Persona p2;
    private Persona p3;
    private Persona p4;
    private Persona p5;
    
    public JavaEE8Resource() {
        mp = new MuchasPersonas();
        p1 =  new Persona("Juan",35,"passwd",1);
        p2 =  new Persona("Maria",15,"passwd",2);
        p3 =  new Persona("Pedro",65,"passwd",3);
        p4 =  new Persona("Juan",85,"passwd",4);
        p5 =  new Persona("Julian",25,"passwd",5);  
        mp.añadirPersona(p1);
        mp.añadirPersona(p2);
        mp.añadirPersona(p3);
        mp.añadirPersona(p4);
        mp.añadirPersona(p5);
    }
    
    
    @GET
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Persona> getUsuarios() {
        return mp.getUsuarios();
    }
    
    @GET
    @Path("usuarios/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Persona> getUsuariosByNombre(@PathParam("nombre") String nombre) {
        return mp.getPersonaByNombre(nombre);
    }
    
    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Persona getUsuariosByNombreYContraseña() {
       return new Persona("Pedro", 20, "passwd", 0);
    }
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Persona añadirNuevoUsuario() {
        Persona p = new Persona("Iris", 34, "passwd", 6);
        mp.añadirPersona(p);
        return mp.getUsuarios().get(4);
    }
    
}
