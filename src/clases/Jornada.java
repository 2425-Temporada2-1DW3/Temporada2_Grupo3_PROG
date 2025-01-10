package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jornada implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3685474574515091709L;
	private int numero; // Número de la jornada
    private List<Partido> partidos; // Lista de partidos en la jornada
    
 // Constructor completo
    public Jornada(int numero, List<Partido> partidos) {
        this.numero = numero;
        this.partidos = partidos;
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "Jornada " + numero + " Total Partidos: " + partidos.size();
    }
    
    
 // Métodos equals y hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jornada jornada = (Jornada) obj;
        return numero == jornada.numero;
    }

    public List<Partido> obtenerPartidos() {
        return new ArrayList<Partido>(this.partidos); // Retorna una copia para proteger la lista interna
    }

}
