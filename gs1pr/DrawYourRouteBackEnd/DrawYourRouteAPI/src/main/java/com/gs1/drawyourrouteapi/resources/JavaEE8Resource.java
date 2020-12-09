package com.gs1.drawyourrouteapi.resources;

import java.util.ArrayList;
import java.util.HashMap;
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
import model.prueba.MuchasPersonas;
import model.prueba.Persona;
import org.json.JSONObject;


/**
 *
 * @author
 */
@Path("/")
public class JavaEE8Resource {
    private MuchasPersonas mp;
    private Persona p1;
    private Persona p2;
    private Persona p3;
    private Persona p5;
    
    public JavaEE8Resource() {
        mp = new MuchasPersonas();
        p1 =  new Persona("Juan",35,"passwd",1);
        p2 =  new Persona("Maria",15,"passwd",2);
        p3 =  new Persona("Pedro",65,"passwd",3);
        p5 =  new Persona("Julian",25,"passwd",5);  
        mp.añadirPersona(p1);
        mp.añadirPersona(p2);
        mp.añadirPersona(p3);
        mp.añadirPersona(p5);
        
        p2.añadirAmigo(p1);
        p2.añadirAmigo(p5);
    }
    
    @GET //Funciona
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok("ping").build();
    }
    
    
    @GET //Funciona
    @Path("/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        return Response.ok(mp.getUsuarios()).build();
    }
    
    @GET //Funciona
    @Path("/usuarios/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuariosByNombre(@PathParam("nombre") String nombre) {
        return Response.ok(mp.getPersonaByNombre(nombre)).build();
    }
    
    @POST //Funciona
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUsuariosByNombreYContraseña(HashMap<String,String> usuario) {
        JSONObject jo = new JSONObject(usuario);
        String nombre = jo.getString("nombre");
        String passwd = jo.getString("contraseña");
        return Response.ok(mp.getPersonaByNombreYContraseña(nombre, passwd)).build();
    }
    
    @POST 
    @Path("/añadirUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response añadirNuevoUsuario(Persona p) {
        mp.añadirPersona(p);
        return Response.ok(mp.getUsuarios()).build();
    }
    
    @GET //Funciona
    @Path("/amigos/{nombre}")
    public Response getAmigos(@PathParam("nombre") String usuario) {
        for (Persona persona : mp.getUsuarios()) {
            if(persona.getNombre().equals(usuario)) {
                return Response.ok(persona.getAmigos()).build();
            }
        }
        return Response.noContent().build();
    }
    
}
