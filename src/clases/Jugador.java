package clases;

public class Jugador {
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
        this.equipo = equipo;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    
 // Métodos toString
    @Override
    public String toString() {
    	return "El jugador " + nombre + " que juega en la posicion de " + posicion + " en el equipo " + equipo.getNombre() + " con la edad de " + edad + " y nacionalidad de " + nacionalidad;
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

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
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
}
