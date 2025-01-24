package clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 2642978724951727903L;

    private String nombre;
    private String anoFundacion;
    private String ciudad;
    private List<Jugador> jugadores;
    private List<Partido> partidos;
    private List<Temporada> temporadas;

    // Constructor completo
    public Equipo(String nombre, String anoFundacion, String ciudad, List<Jugador> jugadores, List<Temporada> temporadasExistentes) {
        this.nombre = nombre;
        this.anoFundacion = anoFundacion;
        this.ciudad = ciudad;
        this.jugadores = jugadores;
        this.partidos = new ArrayList<>();
        this.temporadas = new ArrayList<>();
        
        // Agregar automáticamente el equipo a todas las temporadas activas o por defecto
        for (Temporada temporada : temporadasExistentes) {
            temporada.agregarEquipo(this); // Esto asegura que el equipo participe en cada temporada
        }
    }

    
    // Métodos toString
    @Override
    public String toString() {
        String temporadasParticipadas = temporadas.isEmpty()
            ? "No tiene temporadas asignadas"
            : "Asignado a las temporadas: " + temporadas.stream()
                  .map(t -> String.valueOf(t.getNumero()))
                  .reduce((a, b) -> a + ", " + b)
                  .orElse("");

        return String.format("%s fundado el año %s en %s (%s)", 
            nombre, 
            anoFundacion, 
            ciudad, 
            temporadasParticipadas);
    }




    // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equals(equipo.nombre) && ciudad.equals(equipo.ciudad);
    }

    // Métodos relacionados con temporadas
    public void agregarTemporada(Temporada temporada) {
        if (!temporadas.contains(temporada)) {
            temporadas.add(temporada); // Añadir la temporada a la lista interna del equipo
        }
    }


    
    
  /*  // Métodos para mostrar las temporadas
    public void mostrarTemporadas() {
        if (temporadas.isEmpty()) {
            System.out.println("El equipo " + nombre + " no ha participado en ninguna temporada.");
        } else {
            System.out.println("Temporadas en las que el equipo " + nombre + " ha participado:");
            for (Temporada temporada : temporadas) {
                System.out.println("Temporada " + temporada.getNumero() + " - Estado: " + temporada.getEstado());
            }
        }
    }*/

    // Métodos para guardar y cargar equipos
    public static void guardarEquipos(DefaultListModel<Equipo> listaEquipos) {
        try (FileOutputStream fos = new FileOutputStream("equipos.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaEquipos);  // Guardar la lista
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static DefaultListModel<Equipo> cargarEquipos() {
        DefaultListModel<Equipo> listaEquipos = new DefaultListModel<>();
        File archivo = new File("equipos.ser");

        // Si el archivo no existe, crearlo vacío
        if (!archivo.exists()) {
            try {
                archivo.createNewFile(); // Crear el archivo si no existe
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Leer el archivo de equipos
        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Verificar si hay datos en el archivo
            if (fis.available() > 0) {
                listaEquipos = (DefaultListModel<Equipo>) ois.readObject();
            }
        } catch (EOFException e) {
            System.out.println("El archivo está vacío o no contiene datos válidos.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaEquipos;
    }



	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	// Métodos getter y setter
public List<Jugador> getJugadores() {
    return jugadores;
}

public List<Temporada> getTemporadas() {
    return temporadas;
}

public boolean perteneceATemporada(String temporada) {
    for (Temporada t : temporadas) {
    	if (String.valueOf(t.getNumero()).equals(temporada)) { 
 // Si la temporada del equipo coincide
            return true;
        }
    }
    return false;
}

public String getCiudad() {
	// TODO Auto-generated method stub
	return ciudad;
}

}
