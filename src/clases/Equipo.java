package clases;

import java.io.*;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 2171754837756528471L;
    private String nombre;
    private String anoFundacion;
    private String ciudad;
    private ArrayList<Jugador> jugadores;
    private int puntos;

    // Constructor completo
    public Equipo(String nombre, String anoFundacion, String ciudad, ArrayList<Jugador> jugadores, int puntos) {
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.ciudad = ciudad;
        this.jugadores = jugadores;
        this.puntos = puntos;
    }

    // Constructor solo nombre
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnoFundacion() {
        return anoFundacion;
    }

    public void setAnoFundacion(String anoFundacion) {
        this.anoFundacion = anoFundacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    // Métodos para agregar o eliminar jugadores
    public void agregarJugador(Jugador jugador) {
        if (jugadores == null) {
            jugadores = new ArrayList<>();
        }
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        if (jugadores != null) {
            jugadores.remove(jugador);
        }
    }
}