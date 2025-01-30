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

 // Constructor cuando el partido ya tiene un resultado
    public Partido(Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    // Constructor para un partido aún no jugado
    public Partido(Equipo equipoLocal, Equipo equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = -1; // Valor especial para indicar que el partido no se ha jugado
        this.golesVisitante = -1;
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
    
    public boolean estaJugado() {
        return golesLocal >= 0 && golesVisitante >= 0;
    }

    
 // Método para obtener el resultado del partido
    public int obtenerResultado() {
        if (golesLocal == -1 || golesVisitante == -1) {
            return -1;  // Partido no jugado
        } else if (golesLocal > golesVisitante) {
            return 1;  // Gana el local
        } else if (golesVisitante > golesLocal) {
            return 2;  // Gana el visitante
        } else {
            return 0;  // Empate
        }
    }

    
    public void actualizarPuntos() {
        int resultado = obtenerResultado();
        
        // Si el resultado es 1, gana el local
        if (resultado == 1) {
            // Sumar 3 puntos al equipo local
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 3);
        }
        // Si el resultado es 2, gana el visitante
        else if (resultado == 2) {
            // Sumar 3 puntos al equipo visitante
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 3);
        }
        // Si el resultado es 0, es empate
        else if (resultado == 0) {
            // Sumar 1 punto a cada equipo
            equipoLocal.setPuntos(equipoLocal.getPuntos() + 1);
            equipoVisitante.setPuntos(equipoVisitante.getPuntos() + 1);
        }
    }


}