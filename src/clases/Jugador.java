package clases;

import java.io.Serializable;

public class Jugador implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -906779548639525455L;
	//a
	private String nombre;
    private String posicion;
    private Equipo equipo;
    private int edad;
    private String nacionalidad;

 // Constructor completo
    public Jugador(String nombre, String posicion, Equipo equipo, int edad, String nacionalidad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.setEquipo(equipo);
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    
 // Métodos toString
    @Override
    public String toString() {
        return "El jugador " + nombre + " que juega de " + posicion + " en el " + equipo.getNombre() + " a la edad de " + edad + " nacido en " + nacionalidad;
    }
    
 // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jugador jugador = (Jugador) obj;
        return nombre.equals(jugador.nombre) && equipo.equals(jugador.equipo);
    }
    
 // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
}
