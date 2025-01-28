package clases;

import java.io.Serializable;

public class Jugador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3594324243356090829L;
	private String nombre;          // Nombre del jugador
    private int edad;              // Edad del jugador
    private String posicion;       // Posición (delantero, defensa, mediocampista, etc.)
    private Equipo equipo;         // El equipo al que pertenece el jugador
    
 // Constructor de la clase Jugador
    public Jugador(String nombre, int edad, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.equipo = equipo;
    }
    
    // Métodos Getters y Setters
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
