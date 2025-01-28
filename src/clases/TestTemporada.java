package clases;

import clases.Equipo;
import clases.Jornada;
import clases.Partido;
import clases.Temporada;
import clases.TemporadaManager;

import java.util.ArrayList;
import java.util.List;

public class TestTemporada {

    public static void main(String[] args) {
        // Crear equipos
        Equipo equipo1 = new Equipo("Real Madrid");
        Equipo equipo2 = new Equipo("Barcelona");
        Equipo equipo3 = new Equipo("Atletico Madrid");
        Equipo equipo4 = new Equipo("Valencia");
        Equipo equipo5 = new Equipo("Betis");
        Equipo equipo6 = new Equipo("Sevilla");
        
        List<Jornada> jornadas = new ArrayList<>();

 

        // Crear la temporada y agregar jornadas
        Temporada temporada = new Temporada(1, "Temporada 202555");
        
     // Agregar equipos a la temporada
        temporada.agregarEquipo(equipo1);
        temporada.agregarEquipo(equipo2);
        temporada.agregarEquipo(equipo3);
        temporada.agregarEquipo(equipo4);
        temporada.agregarEquipo(equipo5);
        temporada.agregarEquipo(equipo6);
        
     // Agregar jornadas a la temporada
        for (Jornada jornada : jornadas) {
            temporada.agregarJornada(jornada);
        }

     // Guardar la temporada en archivo .ser usando el método que espera un ArrayList
        ArrayList<Temporada> temporadas = new ArrayList<>();
        temporadas.add(temporada);

        TemporadaManager temporadaManager = new TemporadaManager();
        temporadaManager.guardarTemporadas(temporadas);  // Método que guarda un ArrayList de temporadas

        // Confirmar que la temporada se ha guardado correctamente
        System.out.println("Temporada 'Temporada 202555' guardada con jornadas.");
        System.out.println("Archivo 'temporadas.ser' generado en: " + System.getProperty("user.dir"));
    }
}
