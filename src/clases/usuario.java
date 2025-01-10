package clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4324653987752064090L;
	//a
	String nombre;
	String contrasena;
    Integer rol;
	
    
   /*
    * Roles:
    * Admin = 1
    * 
    * 
    * Arbitro = 2
    * e
    * 
    * Usuario = 3
    * 
    * */
	
	// constructor por defecto
    public usuario(){
    	nombre = "X";
    	contrasena = "";
    	rol = 3;
	}
    
    
 // copia
	
    public usuario(usuario a){
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

	
	
	// Guardar usuarios en el archivo
    public static void guardarUsuarios(List<usuario> listaUsuarios) {
        try (FileOutputStream fos = new FileOutputStream("usuarios.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Guardar cada usuario en el archivo
            for (usuario usuario : listaUsuarios) {
                oos.writeObject(usuario);
            }
            System.out.println("Usuarios guardados exitosamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
