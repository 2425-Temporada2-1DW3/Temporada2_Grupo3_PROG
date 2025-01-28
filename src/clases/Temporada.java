package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Temporada implements Serializable {

    /**
	 * 
	 */
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

   // GUARDAR LA TEMPORADA A ARCHIVO
    public void guardarTemporadas(ArrayList<Temporada> temporadas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temporadas.ser"))) {
            oos.writeObject(temporadas);
            System.out.println("Temporadas guardadas con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
   // CARGAR LA TEMPORADA DESDE ARCHIVO
    @SuppressWarnings("unchecked")
	public ArrayList<Temporada> cargarTemporadas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temporadas.ser"))) {
            return (ArrayList<Temporada>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    
    
 // Método para crear las jornadas de forma "Round Robin" (ida y vuelta)
    public void crearJornadasRobin() {
        // Verificamos que haya al menos 6 equipos
        if (listEquipos.size() < 6) {
            System.out.println("Se necesitan al menos seis equipos para crear las jornadas.");
            return;
        }

        // Si hay 6 equipos, generamos 10 jornadas (ida y vuelta)
        int numEquipos = listEquipos.size();
        int numJornadas = 10; // Siempre habrá 10 jornadas para 6 equipos

        // Creamos las jornadas de ida (3 partidos por jornada)
        ArrayList<Partido> partidosIda = new ArrayList<>();

        // Generamos las jornadas de ida
        for (int i = 0; i < numJornadas / 2; i++) {
            Jornada jornada = new Jornada(i + 1); // Cada jornada tiene un número

            // Generamos los partidos de ida (solo 3 partidos por jornada)
            for (int j = 0; j < numEquipos / 2; j++) {
                // Emparejamos los equipos para la jornada (primero genera los partidos de ida)
                Equipo local = listEquipos.get((i + j) % numEquipos);
                Equipo visitante = listEquipos.get((i + numEquipos - j - 1) % numEquipos);

                // Creamos el partido de ida
                Partido partidoIda = new Partido(local, visitante, 0, 0);
                jornada.agregarPartido(partidoIda);
                partidosIda.add(partidoIda); // Guardamos el partido de ida en la lista de partidosIda
            }

            // Agregamos la jornada de ida a la lista de jornadas
            listJornadas.add(jornada);
        }

        // Generamos las jornadas de vuelta (a partir de la jornada 6)
        for (int i = numJornadas / 2; i < numJornadas; i++) {
            Jornada jornada = new Jornada(i + 1); // Cada jornada tiene un número

            // Generamos los partidos de vuelta (ya no generamos ida)
            for (Partido partidoIda : partidosIda) {
                // Los partidos de vuelta tienen los equipos invertidos
                Equipo local = partidoIda.getEquipoVisitante(); // El equipo visitante se convierte en local
                Equipo visitante = partidoIda.getEquipoLocal(); // El equipo local se convierte en visitante

                // Creamos el partido de vuelta
                Partido partidoVuelta = new Partido(local, visitante, 0, 0);
                jornada.agregarPartido(partidoVuelta); // Agregamos el partido de vuelta a la jornada
            }

            // Agregamos la jornada de vuelta a la lista de jornadas
            listJornadas.add(jornada);
        }

        System.out.println("Jornadas creadas exitosamente.");
    }
    
}