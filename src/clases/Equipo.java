package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Equipo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2642978724951727903L;
	//aa
	private String nombre;
    private String anoFundacion;
    private String ciudad;
    private List<Jugador> jugadores;
    
 // Constructor completo
    public Equipo(String nombre, String anoFundacion, String ciudad, List<Jugador> jugadores) {
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.ciudad = ciudad;
        this.jugadores = jugadores;
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "El " + nombre + " fundado el año " + anoFundacion + " en " + ciudad;
    }
    
    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equals(equipo.nombre) && ciudad.equals(equipo.ciudad);
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
    
    public static void guardarEquipos(DefaultListModel<Equipo> listaEquipos) {
    	// grabo los datos en equipos.ser
    	 try (FileOutputStream fos = new FileOutputStream("equipos.ser");
    	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

    	        // Guardar la lista en el archivo
    	        oos.writeObject(listaEquipos);  // Guardamos toda la lista
    	    } catch (IOException e) {
    	        e.printStackTrace();  // Si ocurre un error, lo imprime en consola
    	    }
    	}
		 // Método para cargar la lista de usuarios desde un archivo binario (equipos.ser)
		    @SuppressWarnings("unchecked")
		    public static DefaultListModel<Equipo> cargarEquipos() {
		        DefaultListModel<Equipo> listaEquipos = new DefaultListModel<>();
		        try (FileInputStream fis = new FileInputStream("equipos.ser");
		                ObjectInputStream ois = new ObjectInputStream(fis)) {
		
		               // Leer el ArrayList de usuarios desde el archivo
		               listaEquipos = (DefaultListModel<Equipo>) ois.readObject();
		           } catch (IOException | ClassNotFoundException e) {
		               e.printStackTrace(); // Si ocurre un error, lo imprime en consola
		           }
		           return listaEquipos; // Devuelve la lista de usuarios cargada
		       }
    
}
