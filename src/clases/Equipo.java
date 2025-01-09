package clases;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String fechaFundacion;
    private String pais;
    private List<Jugador> jugadores;

    // Constructor completo
    public Equipo(String nombre, String fechaFundacion, String pais, List<Jugador> jugadores) {
        this.nombre = nombre;
        this.fechaFundacion = fechaFundacion;
        this.pais = pais;
        this.jugadores = jugadores;
    }
    
 // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaFundacion() {
        return getFechaFundacion();
    }

    public void setFechaFundacion(String añoFundacion) {
        this.fechaFundacion = añoFundacion;
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
