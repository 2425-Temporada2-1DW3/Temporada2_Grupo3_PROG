package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Temporada implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -60062956683051207L;
	//a
	private int numero; // Ejemplo: 2023
    private String estado; // true si la temporada está activa, false si no ha comenzado
    private List<Equipo> equipos;
    private List<Partido> partidos;

 // Constructor completo
    public Temporada(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "Temporada " + numero + " - " + estado;
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
    
    public String setEstado(String estado) {
        return this.estado = estado;
    }

    public String getEstado(String estado) {
        return estado;
    }

    public void setEnCurso(String estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
}
