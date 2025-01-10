package clases;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
//a
	private int numero; // Ejemplo: 2023
    private boolean enCurso; // true si la temporada está activa, false si no ha comenzado
    private List<Equipo> equipos;
    private List<Partido> partidos;

 // Constructor completo
    public Temporada(int numero, boolean enCurso) {
        this.numero = numero;
        this.enCurso = enCurso;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

 // Constructor con equipos y partidos vacíos
    public Temporada(int numero) {
        this(numero, false);
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "Temporada " + numero + " - " + enCurso;
    }
    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temporada temporada = (Temporada) obj;
        return numero == temporada.numero;
    }
    
    // Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
