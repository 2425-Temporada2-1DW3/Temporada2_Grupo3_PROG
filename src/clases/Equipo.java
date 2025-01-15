package clases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Equipo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2642978724951727903L;
	//aa
	private String nombre;
    private String anoFundacion;
    private String pais;
    private List<Jugador> jugadores;
    
 // Constructor completo
    public Equipo(String nombre, String anoFundacion, String pais, List<Jugador> jugadores) {
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.pais = pais;
        this.jugadores = jugadores;
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "El " + nombre + " fundado el año " + anoFundacion + " en " + pais;
    }
    
    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equals(equipo.nombre) && pais.equals(equipo.pais);
    }
    
    
 // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaFundacion() {
        return anoFundacion;
    }

    public void setFechaFundacion(String añoFundacion) {
        this.anoFundacion = añoFundacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        this.jugadores.remove(jugador);
    }
    
    public static void guardarJugadores(ArrayList<Equipo> listaEquipos) {
    	// grabo los datos en racionales.ser
    	 try (FileOutputStream fos = new FileOutputStream("equipos.ser");
    	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

    	        // Guardar la lista completa de usuarios en el archivo
    	        oos.writeObject(listaEquipos);  // Guardamos toda la lista
    	        JOptionPane.showMessageDialog(null, "Equipo guardado correctamente.");
    	    } catch (IOException e) {
    	        e.printStackTrace();  // Si ocurre un error, lo imprime en consola
    	    }
    	}
    
}
