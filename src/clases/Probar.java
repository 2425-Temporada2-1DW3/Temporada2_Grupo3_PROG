package clases;

import java.util.ArrayList;

public class Probar {
    public static void main(String[] args) {
        // Crear equipos
        Equipo equipo1 = new Equipo("Real Madrid", "1902", "Madrid", new ArrayList<>(), 0, 0);
        Equipo equipo2 = new Equipo("Barcelona", "1899", "Barcelona", new ArrayList<>(), 0, 0);
        Equipo equipo3 = new Equipo("Atletico Madrid", "1903", "Madrid", new ArrayList<>(), 0, 0);
        Equipo equipo4 = new Equipo("Valencia", "1919", "Valencia", new ArrayList<>(), 0, 0);
        Equipo equipo5 = new Equipo("Sevilla", "1905", "Sevilla", new ArrayList<>(), 0, 0);
        Equipo equipo6 = new Equipo("Betis", "1907", "Sevilla", new ArrayList<>(), 0, 0);

        // Crear temporada
        Temporada temporada = new Temporada(1, "La Liga 2025");
        temporada.agregarEquipo(equipo1);
        temporada.agregarEquipo(equipo2);
        temporada.agregarEquipo(equipo3);
        temporada.agregarEquipo(equipo4);
        temporada.agregarEquipo(equipo5);
        temporada.agregarEquipo(equipo6);

        // Crear las jornadas de forma "round robin" ida y vuelta
        temporada.crearJornadasRobin();

        // Mostrar las jornadas
        for (Jornada jornada : temporada.getListJornadas()) {
            System.out.println("Jornada " + jornada.getNumero());
            for (Partido partido : jornada.getPartidos()) {
                System.out.println(partido.getEquipoLocal().getNombre() + " vs " + partido.getEquipoVisitante().getNombre());
            }
        }
    }
}
