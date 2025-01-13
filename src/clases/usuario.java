package clases;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Objects;

import javax.swing.DefaultListModel;

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

	
	
	
	
	
	
	public static void guardarUsuarios(DefaultListModel<usuario> dlmUsuarios) {
	    try (FileOutputStream fos = new FileOutputStream("usuarios.ser");
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

	        // Guardar cada usuario del DLM en el archivo
	        for (int i = 0; i < dlmUsuarios.size(); i++) {
	            oos.writeObject(dlmUsuarios.get(i));
	        }
	        System.out.println("Usuarios guardados exitosamente.");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


    
	// Cargar usuarios desde un archivo .ser en un DefaultListModel
	@SuppressWarnings("unchecked")
	public static DefaultListModel<usuario> cargarUsuarios() {
	    DefaultListModel<usuario> dlmUsuarios = new DefaultListModel<>();
	    try (FileInputStream fis = new FileInputStream("usuarios.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis)) {

	        // Leer el DefaultListModel desde el archivo
	        dlmUsuarios = (DefaultListModel<usuario>) ois.readObject();
	        

	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return dlmUsuarios;
	}
	
	// Método para validar credenciales y redirigir según el rol
	public static int validarCredenciales(DefaultListModel<usuario> dlmUsuarios, String nombreUsuario, String contrasena) {
	    for (int i = 0; i < dlmUsuarios.size(); i++) {
	        usuario u = dlmUsuarios.getElementAt(i);
	        // Validar usuario y contraseña
	        if (u.getNombre().equals(nombreUsuario) && u.getContrasena().equals(contrasena)) {
	            // 
	            try {
	            	//Devuelve el numero del rol
	                return u.getRol();
	            } catch (NumberFormatException e) {
	                return -1; // Rol inválido
	            }
	        }
	    }
	    return 0; // Usuario o contraseña incorrectos
	}

    
    
}
