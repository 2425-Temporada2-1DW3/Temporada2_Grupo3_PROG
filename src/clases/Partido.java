package clases;

import java.io.Serializable;

public class Partido implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 772724823964523943L;
	private Equipo equipoLocal; // Equipo local
    private Equipo equipoVisitante; // Equipo visitante
    private int golesLocal; // Goles marcados por el equipo local
    private int golesVisitante; // Goles marcados por el equipo visitante

    // Constructor
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }
    
    
    // PRUEBA CONSTRUCTOR
    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
    	this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }
    
    
 // Getters y Setters
    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    
 // Método para obtener el resultado del partido
    public int obtenerResultado() {
    	
    	//Si gana local devuelve 1
        if (golesLocal > golesVisitante) {
            return 1;
          //Si gana visitante devuelve 2
        } else if (golesVisitante > golesLocal) {
            return 2;
          //En caso de empate devuelve 0
        } else {
            return 0;
        }
    }
    
    public void actualizarPuntos() {
        // Obtenemos el resultado del partido (1 = victoria local, 2 = victoria visitante, 0 = empate)
        int resultado = obtenerResultado();

        if (resultado == 1) {
            // Si el equipo local gana, obtiene 3 puntos
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
        } else if (resultado == 2) {
            // Si el equipo visitante gana, obtiene 3 puntos
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);
        } else {
            // Si hay empate, ambos equipos reciben 1 punto
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
        }
    }

}