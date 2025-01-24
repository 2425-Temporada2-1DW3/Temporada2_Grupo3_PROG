package clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Temporada implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero; // Ejemplo: 2023
    private String estado; // Activa, Inactiva, Finalizada
    private List<Equipo> equipos;
    private List<Partido> partidos;

    // Constructor completo
    public Temporada(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    // Métodos toString
    @Override
    public String toString() {
        return "Temporada " + numero + " - " + estado;
    }

    // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Temporada temporada = (Temporada) obj;
        return numero == temporada.numero;
    }

    // Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    // Add the setEquipos method
    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void agregarEquipo(Equipo equipo) {
        if (!equipos.contains(equipo)) {
            equipos.add(equipo);
            equipo.agregarTemporada(this);
            System.out.println("[DEBUG] Equipo " + equipo.getNombre() + " agregado a la temporada " + numero);
        }
    }


    // Método para agregar todos los equipos de la lista de equipos
    public static void agregarEquiposATemporada(List<Equipo> equipos, Temporada temporada) {
        for (Equipo equipo : equipos) {
            temporada.agregarEquipo(equipo);
        }
    }

    public void eliminarEquipo(Equipo equipo) {
        if (equipos.contains(equipo)) {
            equipos.remove(equipo);
            System.out.println("[INFO] El equipo " + equipo.getNombre() + " ha sido eliminado de la temporada.");
        } else {
            System.out.println("[ERROR] El equipo no existe en la temporada.");
        }
    }

    // Cargar temporadas desde archivo
    @SuppressWarnings("unchecked")
    public static List<Temporada> cargarTemporadasDesdeArchivo(List<Equipo> equipos, DefaultListModel<Jugador> listaJugadores) {
        File archivo = new File("temporadas.ser");
        List<Temporada> temporadas = new ArrayList<>();

        if (!archivo.exists()) {
            // Si el archivo no existe, crear temporadas, equipos y jugadores por defecto
            if (listaJugadores.isEmpty()) {
                // Crear jugadores por defecto si no existen
                Jugador jugador1 = new Jugador("Jugador 1", "Delantero", null, 25, "España");
                Jugador jugador2 = new Jugador("Jugador 2", "Defensa", null, 27, "México");
                Jugador jugador3 = new Jugador("Jugador 3", "Portero", null, 30, "Argentina");
                listaJugadores.addElement(jugador1);
                listaJugadores.addElement(jugador2);
                listaJugadores.addElement(jugador3);

                // Guardar los jugadores en el archivo
                Jugador.guardarJugadores(listaJugadores);
            }

            // Crear equipos por defecto si no existen
            if (equipos.isEmpty()) {
                // Crear equipos por defecto si no existen
                Equipo equipo1 = new Equipo("Equipo A", "1990", "Ciudad A", new ArrayList<>(), new ArrayList<>());
                Equipo equipo2 = new Equipo("Equipo B", "1995", "Ciudad B", new ArrayList<>(), new ArrayList<>());
                equipos.add(equipo1);
                equipos.add(equipo2);

                // Asignar jugadores a equipos
                equipo1.getJugadores().add(listaJugadores.getElementAt(0));
                equipo1.getJugadores().add(listaJugadores.getElementAt(1));
                equipo2.getJugadores().add(listaJugadores.getElementAt(2));

                // Convertir la lista de equipos a DefaultListModel
                DefaultListModel<Equipo> equiposModel = new DefaultListModel<>();
                for (Equipo equipo : equipos) {
                    equiposModel.addElement(equipo);
                }

                // Guardar los equipos en el archivo
                Equipo.guardarEquipos(equiposModel);  // Llamada para guardar los equipos
            }

            // Crear temporadas por defecto
            Temporada temporada2023 = new Temporada(2023, "Activa");
            Temporada temporada2022 = new Temporada(2022, "Finalizada");
            Temporada temporada2021 = new Temporada(2021, "Finalizada");

            // Asignar equipos a todas las temporadas si no tienen equipos
            agregarEquiposSiNoExistente(temporada2023, equipos);  // Asignar equipos a temporada 2023
            agregarEquiposSiNoExistente(temporada2022, equipos);  // Asignar equipos a temporada 2022
            agregarEquiposSiNoExistente(temporada2021, equipos);  // Asignar equipos a temporada 2021

            // Guardar las temporadas por defecto
            temporadas.add(temporada2023);
            temporadas.add(temporada2022);
            temporadas.add(temporada2021);

            // Guardar temporadas en archivo
            guardarTemporadasEnArchivo(temporadas);
            
            return temporadas;
        }

        // Si el archivo de temporadas existe, cargar las temporadas desde el archivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            temporadas = (List<Temporada>) ois.readObject();
            // No agregamos los equipos nuevamente, solo los asignamos si es necesario
            for (Temporada temporada : temporadas) {
                agregarEquiposSiNoExistente(temporada, equipos); // Asignar los equipos a cada temporada solo si es necesario
            }
            return temporadas;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Nueva función para asignar equipos solo si no existen en la temporada
    private static void agregarEquiposSiNoExistente(Temporada temporada, List<Equipo> equipos) {
        for (Equipo equipo : equipos) {
            if (!temporada.getEquipos().contains(equipo)) {
                temporada.getEquipos().add(equipo); // Solo agregar si no está presente
            }
        }
    }

    // Guardar temporadas en archivo
    public static void guardarTemporadasEnArchivo(List<Temporada> temporadas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temporadas.ser"))) {
            oos.writeObject(temporadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para verificar si hay una temporada activa
    public static boolean hayTemporadaActiva(List<Temporada> temporadas) {
        for (Temporada temporada : temporadas) {
            if ("Activa".equalsIgnoreCase(temporada.getEstado())) {
                return true;
            }
        }
        return false;
    }

    // Método para agregar una nueva temporada con validación
    public static boolean agregarTemporada(List<Temporada> temporadas, Temporada nuevaTemporada) {
        if ("Activa".equalsIgnoreCase(nuevaTemporada.getEstado()) && hayTemporadaActiva(temporadas)) {
            return false; // No se puede agregar una nueva temporada activa si ya hay una activa
        }
        temporadas.add(nuevaTemporada);
        guardarTemporadasEnArchivo(temporadas);
        return true;
    }

    // Método para actualizar la lista de equipos de una temporada en la interfaz
    public static void actualizarEquiposPorTemporada(DefaultListModel<Equipo> listaEquipos, Temporada temporada) {
        listaEquipos.clear();
        for (Equipo equipo : temporada.getEquipos()) {
            listaEquipos.addElement(equipo);
        }
    }
}
