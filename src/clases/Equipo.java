package clases;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	//a
	private String nombre;
    private String anoFundacion;
    private String pais;
    private List<Jugador> jugadores;
    
    public Equipo(String nombre, String pais, String anoFundacion) {
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.pais = pais;
        this.jugadores = new ArrayList<>();
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

    public void setFechaFundacion(String anoFundacion) {
        this.anoFundacion = anoFundacion;
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
    
    
}
