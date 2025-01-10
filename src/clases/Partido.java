package clases;

import java.io.Serializable;

public class Partido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5678010098298026099L;
	private String equipoLocal;
    private String equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private String estado; // Puede ser: "Pendiente", "En curso", "Finalizado"

 // Constructor completo
    public Partido(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante, String estado) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.estado = estado;
    }
    
 // Constructor con goles en 0
    public Partido(String equipoLocal, String equipoVisitante) {
        this(equipoLocal, equipoVisitante, 0, 0, "Pendiente");
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return equipoLocal + " vs " + equipoVisitante + " = " + golesLocal + "-" + golesVisitante + " (" + estado + ")";
    }
    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Partido partido = (Partido) obj;
        return equipoLocal.equals(partido.equipoLocal) && equipoVisitante.equals(partido.equipoVisitante);
    }
    
 // Getters y setters
    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
