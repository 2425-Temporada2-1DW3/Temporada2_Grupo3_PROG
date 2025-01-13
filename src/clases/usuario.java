package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import javax.swing.DefaultListModel;

public class usuario implements Serializable {
    private static final long serialVersionUID = -4324653987752064090L;

    // Atributos de la clase usuario
    String nombre;      // Nombre del usuario
    String contrasena;  // Contraseña del usuario
    Integer rol;        // Rol del usuario (1 = Admin, 2 = Arbitro, 3 = Usuario)

    /*
     * Roles:
     * Admin = 1
     * Arbitro = 2
     * Usuario = 3
     */
    
    // Constructor por defecto
    public usuario() {
        nombre = "X";      // Nombre por defecto
        contrasena = "";   // Contraseña vacía por defecto
        rol = 3;           // Rol por defecto (Usuario)
    }
    
    // Constructor de copia
    public usuario(usuario a) {
        nombre = a.nombre;         // Copia el nombre del usuario original
        contrasena = a.contrasena; // Copia la contraseña del usuario original
        rol = a.rol;               // Copia el rol del usuario original
    }
    
    // Constructor personalizado con parámetros
    public usuario(String c, String b, int n) {
        nombre = c;           // Inicializa el nombre del usuario
        contrasena = b;       // Inicializa la contraseña del usuario
        rol = n;              // Inicializa el rol del usuario
    }

    // Métodos getter y setter para los atributos de la clase

    public String getNombre() {
        return nombre; // Devuelve el nombre del usuario
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece un nuevo nombre para el usuario
    }

    public String getContrasena() {
        return contrasena; // Devuelve la contraseña del usuario
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena; // Establece una nueva contraseña para el usuario
    }

    public Integer getRol() {
        return rol; // Devuelve el rol del usuario
    }

    public void setRol(Integer rol) {
        this.rol = rol; // Establece un nuevo rol para el usuario
    }

    // Método hashCode() para generar un código hash único basado en los atributos del usuario
    @Override
    public int hashCode() {
        return Objects.hash(contrasena, nombre, rol); // Genera el hash con los atributos
    }

    // Método equals() para comparar dos objetos de la clase usuario
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Si son el mismo objeto, son iguales
        if (obj == null) return false; // Si el objeto es nulo, no son iguales
        if (getClass() != obj.getClass()) return false; // Si las clases son diferentes, no son iguales
        usuario other = (usuario) obj;  // Compara los atributos de los usuarios
        return Objects.equals(contrasena, other.contrasena) && Objects.equals(nombre, other.nombre)
                && Objects.equals(rol, other.rol);
    }

    // Método toString() para representar el objeto usuario como una cadena de texto
    @Override
    public String toString() {
        return "usuario [nombre=" + nombre + ", contrasena=" + contrasena + ", rol=" + rol + "]";
    }

    // Método para guardar la lista de usuarios en un archivo binario (usuarios.ser)
    public static void guardarUsuarios(DefaultListModel<usuario> dlmUsuarios) {
        try (FileOutputStream fos = new FileOutputStream("usuarios.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Guardar cada usuario del DefaultListModel en el archivo
            for (int i = 0; i < dlmUsuarios.size(); i++) {
                oos.writeObject(dlmUsuarios.get(i)); // Escribe el objeto usuario en el archivo
            }
            System.out.println("Usuarios guardados exitosamente.");

        } catch (IOException e) {
            e.printStackTrace(); // Si ocurre un error, lo imprime en consola
        }
    }

    // Método para cargar la lista de usuarios desde un archivo binario (usuarios.ser)
    @SuppressWarnings("unchecked")
    public static DefaultListModel<usuario> cargarUsuarios() {
        DefaultListModel<usuario> dlmUsuarios = new DefaultListModel<>();
        try (FileInputStream fis = new FileInputStream("usuarios.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Leer el DefaultListModel de usuarios desde el archivo
            dlmUsuarios = (DefaultListModel<usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Si ocurre un error, lo imprime en consola
        }
        return dlmUsuarios; // Devuelve la lista de usuarios cargada
    }

    // Método para validar las credenciales de un usuario y devolver su rol
    public static int validarCredenciales(DefaultListModel<usuario> dlmUsuarios, String nombreUsuario, String contrasena) {
        for (int i = 0; i < dlmUsuarios.size(); i++) {
            usuario u = dlmUsuarios.getElementAt(i); // Obtiene un usuario del DefaultListModel
            // Verifica si el nombre de usuario y la contraseña coinciden
            if (u.getNombre().equals(nombreUsuario) && u.getContrasena().equals(contrasena)) {
                try {
                    return u.getRol(); // Devuelve el rol del usuario si las credenciales son correctas
                } catch (NumberFormatException e) {
                    return -1; // Si el rol es inválido, devuelve -1
                }
            }
        }
        return 0; // Si no se encuentran las credenciales, devuelve 0 (usuario o contraseña incorrectos)
    }
}
