package clases;

import java.util.Objects;

public class usuario {
	
	String nombre;
	String contrasena;
    Integer rol;
	
    
   /*
    * Roles:
    * 
    * Arbitro = 2
    * 
    * Admin = 1
    * 
    * Usuario = 3
    * 
    * */
	
	// constructor por defecto
    public usuario(){
    	nombre = "X";
    	contrasena = "";
    	rol = 1;
	}
    
    
 // copia
	
    usuario(usuario a){
    	nombre = a.nombre;
    	contrasena = a.contrasena;
    	rol = a.rol;
 	}
 	
 	
 // constructor personalizado
    
    public usuario(String c, String b, int n){
    	nombre = c;
    	contrasena = b;
		rol = n;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasena, nombre, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		usuario other = (usuario) obj;
		return Objects.equals(contrasena, other.contrasena) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(rol, other.rol);
	}

	@Override
	public String toString() {
		return "usuario [nombre=" + nombre + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}

    
    
}
