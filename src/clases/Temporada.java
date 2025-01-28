package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public String toString() {
        return this.nombre;  // Retorna solo el nombre de la temporada
    }

    // Método para agregar una jornada a la temporada
    public void agregarJornada(Jornada jornada) {
        this.listJornadas.add(jornada);
    }

    // Método para agregar un equipo a la temporada
    public void agregarEquipo(Equipo equipo) {
        listEquipos.add(equipo);
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
    public static ArrayList<Temporada> cargarTemporadas() {
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
    
 // Método para crear los datos predeterminados (equipos, temporada, etc.)
    public static ArrayList<Temporada> crearDatosPredeterminados() {
        ArrayList<Temporada> temporadas = new ArrayList<>();
        
        // Crear equipos
        Equipo equipo1 = new Equipo("Real Madrid");
        Equipo equipo2 = new Equipo("Barcelona");
        Equipo equipo3 = new Equipo("Atletico Madrid");
        Equipo equipo4 = new Equipo("Valencia");
        Equipo equipo5 = new Equipo("Betis");
        Equipo equipo6 = new Equipo("Sevilla");

        // Crear la temporada y agregar equipos
        Temporada temporada = new Temporada(1, "Temporada 202555");

        // Agregar equipos a la temporada
        temporada.agregarEquipo(equipo1);
        temporada.agregarEquipo(equipo2);
        temporada.agregarEquipo(equipo3);
        temporada.agregarEquipo(equipo4);
        temporada.agregarEquipo(equipo5);
        temporada.agregarEquipo(equipo6);

        // Crear y agregar jornadas (puedes agregar la lógica para crear jornadas)
        List<Jornada> jornadas = new ArrayList<>();
        for (Jornada jornada : jornadas) {
            temporada.agregarJornada(jornada);
        }

        // Añadir la temporada a la lista
        temporadas.add(temporada);
        
        return temporadas;
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
