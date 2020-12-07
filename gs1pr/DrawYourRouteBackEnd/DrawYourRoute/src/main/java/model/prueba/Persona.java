package model.prueba;

public class Persona {
    private String nombre;
    private int edad;
    private String contraseña;
    private int id;

    public Persona(String nombre, int edad, String contraseña, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.contraseña = contraseña;
        this.id = id;
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
}
