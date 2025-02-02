package clases;

import java.util.ArrayList;
import java.util.Collections;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Xml {
	
    private ArrayList<String> elementosXML; // List to store the XML content
    private ArrayList<String> contenidoXMLFormateado; // List to store the XML content

    public Xml() {
    	elementosXML = new ArrayList<>(); // Initialize the list
        contenidoXMLFormateado = new ArrayList<>(); // Initialize the list

    }
    
    public void add(String contenido, boolean esDato, int indentacion) {
        String indentacionString = "    ".repeat(indentacion);
        String contenidoFormateado;

        if (esDato) {
            // Se obtiene la última etiqueta agregada para encapsular el dato correctamente
            if (!elementosXML.isEmpty()) {
                String ultimaEtiqueta = elementosXML.get(elementosXML.size() - 1); // No se elimina, solo se obtiene
                contenidoFormateado = indentacionString + "<" + ultimaEtiqueta + ">" + contenido + "</" + ultimaEtiqueta + ">";
                elementosXML.remove(elementosXML.size() - 1); // Ahora sí eliminamos la etiqueta ya que fue cerrada
            } else {
                // Si no hay una etiqueta abierta, simplemente agrega el contenido sin etiquetas (esto no debería ocurrir)
                contenidoFormateado = indentacionString + contenido;
            }
        } else {
            // Si la etiqueta ya está en la lista, la cerramos
            if (elementosXML.contains(contenido)) {
                contenidoFormateado = indentacionString + "</" + contenido + ">";
                elementosXML.remove(contenido);
            } else {
                // Si la etiqueta no está en la lista, la abrimos
                contenidoFormateado = indentacionString + "<" + contenido + ">";
                elementosXML.add(contenido);
            }
        }

        contenidoXMLFormateado.add(contenidoFormateado);
    }







    public void file(String archivo) {
        String nombreArchivo = "C:\\xampp\\htdocs\\Temporada2_Grupo3_LM\\xmlPrueba\\" + archivo + ".xml";

        try (FileWriter fichero = new FileWriter(nombreArchivo);
                BufferedWriter bw = new BufferedWriter(fichero)) {
            
            // Agregar las declaraciones XML al inicio del archivo
            bw.write("<?xml version='1.0' encoding='utf-8'?>");
            bw.newLine();
            bw.write("<?xml-model href='temporada.xsd'?>");
            bw.newLine();

            // Escribir el contenido formateado del XML
            for (int i = 0; i < contenidoXMLFormateado.size(); i++) {
                bw.write(contenidoXMLFormateado.get(i));
                bw.newLine();
            }
        } catch (IOException e) {
            // Manejo de errores
            System.err.println("Error al crear el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    public void generarXMLDesdeListaTemporadas(ArrayList<Temporada> temporadas, String nombreArchivo) {
        this.clear();
        this.add("Temporadas", false, 0); // Etiqueta raíz

        for (Temporada temporada : temporadas) {
            this.add("Temporada", false, 1);

            // 🔹 **Número de la temporada**
            this.add("Numero", false, 2);
            this.add(temporada.getNombre(), true, 3); // Contenido de la etiqueta <Numero>

            // 🔹 **Estado de la temporada**
            this.add("Estado", false, 2);
            this.add(temporada.getEstado(), true, 3); // Contenido de la etiqueta <Estado>

            // 🔹 **Equipos**
            this.add("Equipos", false, 2);
            for (Equipo equipo : temporada.getListEquipos()) {
                this.add("Equipo", false, 3);

                // 🔹 **Nombre del equipo**
                this.add("Nombre", false, 4);
                this.add(equipo.getNombre(), true, 5); // Contenido de la etiqueta <Nombre>

                // 🔹 **Ciudad del equipo**
                this.add("Ciudad", false, 4);
                this.add(equipo.getCiudad(), true, 5); // Contenido de la etiqueta <Ciudad>

                // 🔹 **Año de fundación del equipo**
                this.add("ano_fundacion", false, 4);
                this.add(equipo.getAnoFundacion(), true, 5); // Contenido de la etiqueta <ano_fundacion>

                // 🔹 **Jugadores del equipo**
                this.add("Jugadores", false, 4);
                for (Jugador jugador : equipo.getJugadores()) {
                    this.add("Jugador", false, 5);

                    // 🔹 **Nombre del jugador**
                    this.add("Nombre", false, 6);
                    this.add(jugador.getNombre(), true, 7); // Contenido de la etiqueta <Nombre>

                    // 🔹 **Edad del jugador**
                    this.add("Edad", false, 6);
                    this.add(String.valueOf(jugador.getEdad()), true, 7); // Contenido de la etiqueta <Edad>

                    // 🔹 **Posición del jugador**
                    this.add("Posicion", false, 6);
                    this.add(jugador.getPosicion(), true, 7); // Contenido de la etiqueta <Posicion>

                    // 🔹 **Imagen del jugador**
                    this.add("Imagen", false, 6);
                    this.add(jugador.getNombre() + ".png", true, 7); // Contenido de la etiqueta <Imagen>

                    this.add("Jugador", false, 5); // Cierre de la etiqueta <Jugador>
                }
                this.add("Jugadores", false, 4); // Cierre de la etiqueta <Jugadores>

                this.add("Equipo", false, 3); // Cierre de la etiqueta <Equipo>
            }
            this.add("Equipos", false, 2); // Cierre de la etiqueta <Equipos>

            // 🔹 **Jornadas**
            this.add("Jornadas", false, 2);
            for (Jornada jornada : temporada.getListJornadas()) {
                this.add("Jornada", false, 3);

                // 🔹 **Número de la jornada**
                this.add("Numero", false, 4);
                this.add(String.valueOf(jornada.getNumero()), true, 5); // Contenido de la etiqueta <Numero>

                // 🔹 **Partidos de la jornada**
                this.add("Partidos", false, 4);
                for (Partido partido : jornada.getPartidos()) {
                    this.add("Partido", false, 5);

                    // 🔹 **Equipo local**
                    this.add("EquipoLocal", false, 6);
                    this.add(partido.getEquipoLocal().getNombre(), true, 7); // Contenido de la etiqueta <EquipoLocal>

                    // 🔹 **Equipo visitante**
                    this.add("EquipoVisitante", false, 6);
                    this.add(partido.getEquipoVisitante().getNombre(), true, 7); // Contenido de la etiqueta <EquipoVisitante>

                    // 🔹 **Goles del equipo local**
                    this.add("GolesLocal", false, 6);
                    this.add(String.valueOf(partido.getGolesLocal()), true, 7); // Contenido de la etiqueta <GolesLocal>

                    // 🔹 **Goles del equipo visitante**
                    this.add("GolesVisitante", false, 6);
                    this.add(String.valueOf(partido.getGolesVisitante()), true, 7); // Contenido de la etiqueta <GolesVisitante>

                    this.add("Partido", false, 5); // Cierre de la etiqueta <Partido>
                }
                this.add("Partidos", false, 4); // Cierre de la etiqueta <Partidos>

                this.add("Jornada", false, 3); // Cierre de la etiqueta <Jornada>
            }
            this.add("Jornadas", false, 2); // Cierre de la etiqueta <Jornadas>

            this.add("Temporada", false, 1); // Cierre de la etiqueta <Temporada>
        }

        this.add("Temporadas", false, 0); // Cierre de la etiqueta raíz
        this.file(nombreArchivo);
    }


    public void clear() {
    	elementosXML.clear();
    	contenidoXMLFormateado.clear();
    }
    
}