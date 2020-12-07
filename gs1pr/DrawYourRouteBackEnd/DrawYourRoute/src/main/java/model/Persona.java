package model;

import java.util.ArrayList;

public class Persona {
    private String nombre;
    private int edad;
    private String contraseña;
    private int id;
    private ArrayList<Persona> amigos;

    public Persona(String nombre, int edad, String contraseña, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.contraseña = contraseña;
        this.id = id;
        this.amigos = new ArrayList<>();
    }
    
    public Persona() {
        this.nombre = "Martel";
        this.edad = 80;
        this.contraseña = "passwd";
        this.id = 9;
    }
    
    public void añadirAmigo(Persona p) {
        if(!amigos.contains(p) && !p.getNombre().equals(this.nombre)) {
            amigos.add(p);
        }
    }
    
    public boolean eliminarAmigo(Persona p) {
        if(!p.getNombre().equals(this.nombre) && amigos.contains(p)) {
            amigos.remove(p);
            return true;
        }
        return false;
    }
    
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    @Override
    public String toString() {
        return "Persona: " + nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ArrayList<Persona> getAmigos() {
        return amigos;
    }
}
