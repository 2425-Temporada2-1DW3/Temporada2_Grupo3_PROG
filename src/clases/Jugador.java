package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.DefaultListModel;

public class Jugador implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -906779548639525455L;
	//a
	private String nombre;
    private String posicion;
    private Equipo equipo;
    private int edad;
    private String nacionalidad;

 // Constructor completo
    public Jugador(String nombre, String posicion, Equipo equipo, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.setEquipo(equipo);
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    
 // Métodos toString
    @Override
    public String toString() {
        String nombreEquipo = (equipo != null) ? equipo.getNombre() : "sin equipo asignado";
        return "El jugador " + nombre + " que juega de " + posicion + " en el " + nombreEquipo + " a la edad de " + edad + " nacido en " + nacionalidad;
    }

    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador jugador = (Jugador) obj;
        return nombre.equals(jugador.nombre) && equipo.equals(jugador.equipo);
    }
    
 // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	// Método para guardar jugadores en un archivo .ser
	public static void guardarJugadores(DefaultListModel<Jugador> listaJugadores) {
	    try (FileOutputStream fos = new FileOutputStream("jugadores.ser");
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

	        oos.writeObject(listaJugadores);  // Guardar la lista
	        System.out.println(listaJugadores);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	// Método para cargar jugadores desde un archivo .ser
	@SuppressWarnings("unchecked")
	public static DefaultListModel<Jugador> cargarJugadores() {
	    DefaultListModel<Jugador> listaJugadores = new DefaultListModel<>();
	    try (FileInputStream fis = new FileInputStream("jugadores.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	        listaJugadores = (DefaultListModel<Jugador>) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return listaJugadores;
	}

}
