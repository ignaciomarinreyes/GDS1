package model;

import java.util.ArrayList;

public class MuchasPersonas {
    private ArrayList<Persona> personas;
    
    public MuchasPersonas() {
        personas = new ArrayList<>();
    }
    
    public void añadirPersona(Persona p) {
        personas.add(p);
    }
    
    public void eliminarPersona(Persona p) {
        personas.remove(p);
    }
    
    public ArrayList<Persona> getUsuarios() {
        return personas;
    }
    
    public Persona getPersonaById(int id) {
        for (Persona persona : personas) {
            if(persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }
    
    public ArrayList<Persona> getPersonaByNombre(String nombre) {
        ArrayList<Persona> gente = new ArrayList<>();
        for (Persona persona : personas) {
            if(persona.getNombre().equals(nombre)) {
                gente.add(persona);
            }
        }
        return gente;
    }

    public Persona getPersonaByNombreYContraseña(String nombre, String contraseña) {
        for (Persona persona : personas) {
            if(persona.getNombre().equals(nombre) && persona.getContraseña().equals(contraseña)) {
                return persona;
            }
        }
        return null;
    }
    
    
            
}
