package clases;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Jugador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3594324243356090829L;
	private String nombre;          // Nombre del jugador
    private int edad;              // Edad del jugador
    private String posicion;       // Posición ( 4 delantero, 4 defensa, 4 mediocampista, 3 portero.)
    private Equipo equipo;         // El equipo al que pertenece el jugador
    private ImageIcon imagen;  // New field to store the jugador image
    
 // Constructor de la clase Jugador
    public Jugador(String nombre, int edad, String posicion, Equipo equipo , ImageIcon imagen) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
        this.equipo = equipo;
        this.imagen = imagen;
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

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

}
