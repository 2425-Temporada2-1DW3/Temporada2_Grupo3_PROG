package ventanas;

import java.util.ArrayList;

import clases.Jornada;
import clases.Temporada;

public class PROBANDO {

	public static void main(String[] args) {
		ArrayList<Temporada> temporadas = Temporada.cargarTemporadas(); // Método de la clase Temporada

	    for (Temporada temporada : temporadas) {
	        System.out.println("Temporada: " + temporada.getNombre());
	        System.out.println("Jornadas en la temporada: " + temporada.getListJornadas().size());

	        for (Jornada jornada : temporada.getListJornadas()) {
	            System.out.println("  - Jornada " + jornada.getNumero() + " con " + jornada.getPartidos().size() + " partidos.");
	        }
	    }
	}

}
