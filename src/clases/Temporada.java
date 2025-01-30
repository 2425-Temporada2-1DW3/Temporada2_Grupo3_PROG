package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Temporada implements Serializable {
    private static final long serialVersionUID = 115633587383446069L;
    private int id_temporada; // Identificador único de la temporada
    private String nombre; // Nombre de la temporada
    private String estado; // Estado de finalización de la temporada
    private ArrayList<Jornada> listJornadas; // Lista de jornadas de la temporada
    private ArrayList<Equipo> listEquipos; // Lista de equipos de la temporada

    // Constructor
    public Temporada(int id_temporada, String nombre, String estado) {
        this.id_temporada = id_temporada;
        this.nombre = nombre;
        this.estado = estado;
        this.listJornadas = new ArrayList<>();
        this.listEquipos = new ArrayList<>();
    }

    // Constructor NOMBRE Y NUMERO
    public Temporada(int id_temporada, String nombre) {
        this.id_temporada = id_temporada;
        this.nombre = nombre;
        this.listJornadas = new ArrayList<>(); // Inicializamos la lista
        this.listEquipos = new ArrayList<>();  // Inicializamos la lista
    }

    // Getters y Setters
    public int getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(int id_temporada) {
        this.id_temporada = id_temporada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Equipo> getListEquipos() {
        return listEquipos;
    }

    public void setListEquipos(ArrayList<Equipo> listEquipos) {
        this.listEquipos = listEquipos;
    }

    public ArrayList<Jornada> getListJornadas() {
        return listJornadas;
    }

    public void setListJornadas(ArrayList<Jornada> listJornadas) {
        this.listJornadas = listJornadas;
    }

    // Método para agregar una jornada a la temporada
    public void agregarJornada(Jornada jornada) {
        this.listJornadas.add(jornada);
    }

    // Método para agregar un equipo a la temporada
    public void agregarEquipo(Equipo equipo) {
        listEquipos.add(equipo);
    }
    
 // Método para eliminar un equipo de la temporada
    public void eliminarEquipo(Equipo equipo) {
        listEquipos.remove(equipo);
    }

    // Guardar todas las temporadas en un solo archivo .ser
    public static void guardarTemporadas(ArrayList<Temporada> temporadas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temporadas.ser"))) {
            oos.writeObject(temporadas);
            System.out.println("Temporadas guardadas con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar todas las temporadas desde el archivo .ser
    @SuppressWarnings("unchecked")
    public ArrayList<Temporada> cargarTemporadas() {
        ArrayList<Temporada> temporadas = new ArrayList<>();
        File file = new File("temporadas.ser");
        
        if (!file.exists()) {
        	 // Si el archivo no existe, creamos la temporada con datos predeterminados
            System.out.println("Archivo no encontrado. Se cargarán datos predeterminados.");
            // Crear datos predeterminados
            temporadas = crearDatosPredeterminados();
            // Guardar los datos predeterminados
            guardarTemporadas(temporadas);
        } else {
            // Si el archivo existe, cargamos las temporadas desde el archivo
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temporadas.ser"))) {
                temporadas = (ArrayList<Temporada>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return temporadas;
    }
    

    public static ArrayList<Temporada> crearDatosPredeterminados() {
        ArrayList<Temporada> temporadas = new ArrayList<>();

        // Crear equipos
        ArrayList<Jugador> jugadores = new ArrayList<>();

        // Cargar la imagen predeterminada
        ImageIcon logoPredeterminado = new ImageIcon("C:\\xampp\\htdocs\\Temporada2_Grupo3_LM\\img\\escudos\\escudo.png");
        // Crear equipos y asignarles la imagen renombrada
        Equipo equipo1 = new Equipo("Real Madrid", "1909", "madrid", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "real_madrid"));
        Equipo equipo2 = new Equipo("Barcelona", "1989", "barcelona", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "barcelona"));
        Equipo equipo3 = new Equipo("Atletico Madrid", "1979", "madrid", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "atletico_madrid"));
        Equipo equipo4 = new Equipo("Valencia", "1969", "valencia", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "valencia"));
        Equipo equipo5 = new Equipo("Betis", "1999", "sevilla", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "betis"));
        Equipo equipo6 = new Equipo("Athletic Club", "1959", "bilbao", jugadores, 0, cambiarNombreImagen(logoPredeterminado, "athletic_club"));

        // Crear jugadores y asignarlos a los equipos con posiciones específicas
        for (Equipo equipo : new Equipo[]{equipo1, equipo2, equipo3, equipo4, equipo5, equipo6}) {
        	int jugadorNum = 1; // Inicia en 1 para cada equipo

            // Asignar 4 delanteros
            for (int i = 0; i < 4; i++) {
                String nombreJugador = "Jugador_" + jugadorNum;
                String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                
                // Verificar si existe la imagen del jugador
                if (verificarExistenciaImagen(rutaImagen)) {
                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                    Jugador delantero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Delantero", equipo, imagenJugador);
                    equipo.getJugadores().add(delantero);
                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                } else {
                    // Si no existe la imagen, coger la imagen por defecto y copiarla
                    String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                    if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                        try {
                            // Copiar la imagen predeterminada al equipo
                            Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                       new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador delantero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Delantero", equipo, imagenJugador);
                            equipo.getJugadores().add(delantero);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } catch (IOException ex) {
                            System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                        }
                    } else {
                        System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                    }
                }
                jugadorNum++;
            }

            // Asignar 4 defensas
            for (int i = 0; i < 4; i++) {
                String nombreJugador = "Jugador_" + jugadorNum;
                String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                
                // Verificar si existe la imagen del jugador
                if (verificarExistenciaImagen(rutaImagen)) {
                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                    Jugador defensa = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Defensa", equipo, imagenJugador);
                    equipo.getJugadores().add(defensa);
                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                } else {
                    // Si no existe la imagen, coger la imagen por defecto y copiarla
                    String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                    if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                        try {
                            // Copiar la imagen predeterminada al equipo
                            Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                       new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador defensa = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Defensa", equipo, imagenJugador);
                            equipo.getJugadores().add(defensa);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } catch (IOException ex) {
                            System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                        }
                    } else {
                        System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                    }
                }
                jugadorNum++;
            }

            // Asignar 4 mediocampistas
            for (int i = 0; i < 4; i++) {
                String nombreJugador = "Jugador_" + jugadorNum;
                String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                
                // Verificar si existe la imagen del jugador
                if (verificarExistenciaImagen(rutaImagen)) {
                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                    Jugador mediocampista = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Mediocampista", equipo, imagenJugador);
                    equipo.getJugadores().add(mediocampista);
                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                } else {
                    // Si no existe la imagen, coger la imagen por defecto y copiarla
                    String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                    if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                        try {
                            // Copiar la imagen predeterminada al equipo
                            Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                       new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador mediocampista = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Mediocampista", equipo, imagenJugador);
                            equipo.getJugadores().add(mediocampista);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } catch (IOException ex) {
                            System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                        }
                    } else {
                        System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                    }
                }
                jugadorNum++;
            }

            // Asignar 3 porteros
            for (int i = 0; i < 3; i++) {
                String nombreJugador = "Jugador_" + jugadorNum;
                String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                
                // Verificar si existe la imagen del jugador
                if (verificarExistenciaImagen(rutaImagen)) {
                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                    Jugador portero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Portero", equipo, imagenJugador);
                    equipo.getJugadores().add(portero);
                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                } else {
                    // Si no existe la imagen, coger la imagen por defecto y copiarla
                    String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                    if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                        try {
                            // Copiar la imagen predeterminada al equipo
                            Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                       new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador portero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Portero", equipo, imagenJugador);
                            equipo.getJugadores().add(portero);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } catch (IOException ex) {
                            System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                        }
                    } else {
                        System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                    }
                }
                jugadorNum++;
            }
        }

        // Crear temporadas y agregar equipos
        Temporada temporada2025 = new Temporada(1, "Temporada 2025", "Finalizada");
        Temporada temporada202555 = new Temporada(2, "Temporada 202555", "En Curso");

        // Agregar equipos a las temporadas
        for (Equipo equipo : new Equipo[]{equipo1, equipo2, equipo3, equipo4, equipo5, equipo6}) {
            temporada2025.agregarEquipo(equipo);
            temporada202555.agregarEquipo(equipo);
        }

        // Agregar temporadas a la lista
        temporadas.add(temporada2025);
        temporadas.add(temporada202555);

        return temporadas;
    }

    // Método para verificar si la imagen del jugador existe en el sistema de archivos
    public static boolean verificarExistenciaImagen(String rutaImagen) {
        File archivo = new File(rutaImagen);
        return archivo.exists() && archivo.isFile();
    }

    public ArrayList<Jugador> obtenerJugadoresDeEquipo(String equipoNombre) {
        for (Equipo equipo : getListEquipos()) {
            if (equipo.getNombre().equals(equipoNombre)) {
                return equipo.getJugadores();
            }
        }
        return new ArrayList<>();
    }

    // Método para cambiar el nombre de la imagen, borrar el archivo anterior si existe, y guardarla con el nombre del equipo en su caprta de equipo
    private static ImageIcon cambiarNombreImagen(ImageIcon logoPredeterminado, String nombreEquipo) {
        try {
            // Ruta del archivo original
            File archivoOriginal = new File("C:/xampp/htdocs/Temporada2_Grupo3_LM/img/escudos/escudo.png");

            // Verificar si el archivo original existe
            if (!archivoOriginal.exists()) {
                throw new FileNotFoundException("El archivo original no se encuentra: " + archivoOriginal.getAbsolutePath());
            }

            // Ruta de la carpeta del equipo
            String carpetaEquipo = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + nombreEquipo.replaceAll("\\s+", "_");
            File directorio = new File(carpetaEquipo);

            // Crear la carpeta del equipo si no existe
            if (!directorio.exists()) {
                boolean carpetaCreada = directorio.mkdirs();  // Crea la carpeta si no existe
                if (!carpetaCreada) {
                    throw new IOException("No se pudo crear la carpeta para el equipo: " + carpetaEquipo);
                }
            }

            // Crear el nuevo archivo con el nombre del equipo
            File archivoNuevo = new File(carpetaEquipo + "/" + nombreEquipo + ".png");

            // Si el archivo ya existe, lo eliminamos
            if (archivoNuevo.exists()) {
                archivoNuevo.delete();
            }

            // Copiar el archivo original al nuevo archivo con el nombre adecuado
            Files.copy(archivoOriginal.toPath(), archivoNuevo.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Retornar la imagen con el nuevo nombre
            return new ImageIcon(archivoNuevo.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
            return logoPredeterminado;  // Si hay un error, retorna la imagen predeterminada
        }
    }


    // Método para crear las jornadas de forma "Round Robin" (ida y vuelta)
    public void crearJornadasRobin() {
        if (listEquipos.size() < 6) {
            System.out.println("Se necesitan al menos seis equipos para crear las jornadas.");
            return;
        }

        int numEquipos = listEquipos.size();
        int numJornadas = 10;

        ArrayList<Partido> partidosIda = new ArrayList<>();

        for (int i = 0; i < numJornadas / 2; i++) {
            Jornada jornada = new Jornada(i + 1);
            for (int j = 0; j < numEquipos / 2; j++) {
                Equipo local = listEquipos.get((i + j) % numEquipos);
                Equipo visitante = listEquipos.get((i + numEquipos - j - 1) % numEquipos);

                Partido partidoIda = new Partido(local, visitante, 0, 0);
                jornada.agregarPartido(partidoIda);
                partidosIda.add(partidoIda);
            }
            listJornadas.add(jornada);
        }

        for (int i = numJornadas / 2; i < numJornadas; i++) {
            Jornada jornada = new Jornada(i + 1);
            for (Partido partidoIda : partidosIda) {
                Equipo local = partidoIda.getEquipoVisitante();
                Equipo visitante = partidoIda.getEquipoLocal();

                Partido partidoVuelta = new Partido(local, visitante, 0, 0);
                jornada.agregarPartido(partidoVuelta);
            }
            listJornadas.add(jornada);
        }

        System.out.println("Jornadas creadas exitosamente.");
    }
	
 
}
