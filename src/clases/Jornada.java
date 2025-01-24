package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jornada extends Temporada implements Serializable {

    private static final long serialVersionUID = 3685474574515091709L;
    private int numero;  // Número de la jornada
    private List<Partido> partidos;  // Lista de partidos en la jornada
    private static Set<String> partidosExistentes = new HashSet<>();  // Conjunto estático para verificar duplicados

    // Constructor completo
    public Jornada(int numero, List<Partido> partidos, int temporadaNumero, String temporadaEstado) {
        super(temporadaNumero, temporadaEstado);  // Llamada al constructor de la clase Temporada
        this.numero = numero;
        this.partidos = new ArrayList<>(partidos);  // Asegurarse de que la lista se inicializa correctamente
    }

    // Constructor vacío con inicialización mínima
    public Jornada(int numero, int temporadaNumero, String temporadaEstado) {
        super(temporadaNumero, temporadaEstado);
        this.numero = numero;
        this.partidos = new ArrayList<>();
    }

    // Getter para el número de la jornada
    public int obtenerNumero() {
        return numero;
    }

    // Método que devuelve el número como String
    public String obtenerNumeroComoString() {
        return "Jornada " + numero;
    }

    // Métodos para generar los partidos en forma de round robin sin duplicados entre jornadas
    /*public void roundRobin(List<String> equipos) {
        partidos.clear();  // Limpiamos la lista de partidos de la jornada
        partidosExistentes.clear();  // Limpiamos los partidos ya existentes

        // Verificar si los equipos están correctamente pasados
        System.out.println("Equipos a generar partidos:");
        for (String equipo : equipos) {
            System.out.println(equipo);
        }

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                String equipoLocal = equipos.get(i);
                String equipoVisitante = equipos.get(j);

                // Generamos un identificador único para cada partido
                String identificadorPartido = generarIdentificadorPartido(equipoLocal, equipoVisitante);

                // Si el partido no existe en la lista de partidos existentes, lo agregamos
                if (!partidosExistentes.contains(identificadorPartido)) {
                    Partido partido = new Partido(equipoLocal, equipoVisitante);
                    agregarPartido(partido);  // Agregar el partido a la lista de partidos
                    partidosExistentes.add(identificadorPartido);  // Añadir el identificador al Set de partidos existentes
                }
            }
        }
    }*/
 // Métodos para generar los partidos en forma de round robin sin duplicados entre jornadas
    public void roundRobin(List<String> equipos) {
        partidos.clear();  // Limpiamos la lista de partidos de la jornada
        partidosExistentes.clear();  // Limpiamos los partidos ya existentes

        int maxPartidos = 3; // Límite máximo de partidos por jornada
        int partidosGenerados = 0; // Contador de partidos generados

        // Verificar si los equipos están correctamente pasados
        System.out.println("Equipos a generar partidos:");
        for (String equipo : equipos) {
            System.out.println(equipo);
        }

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                if (partidosGenerados >= maxPartidos) {
                    // Detenemos la generación si alcanzamos el límite
                    break;
                }

                String equipoLocal = equipos.get(i);
                String equipoVisitante = equipos.get(j);

                // Generamos un identificador único para cada partido
                String identificadorPartido = generarIdentificadorPartido(equipoLocal, equipoVisitante);

                // Si el partido no existe en la lista de partidos existentes, lo agregamos
                if (!partidosExistentes.contains(identificadorPartido)) {
                    Partido partido = new Partido(equipoLocal, equipoVisitante);
                    agregarPartido(partido);  // Agregar el partido a la lista de partidos
                    partidosExistentes.add(identificadorPartido);  // Añadir el identificador al Set de partidos existentes
                    partidosGenerados++; // Incrementamos el contador de partidos generados
                }
            }

            if (partidosGenerados >= maxPartidos) {
                // Salimos del bucle externo si alcanzamos el límite
                break;
            }
        }
    }



    // Método para generar un identificador único para cada partido
    private String generarIdentificadorPartido(String equipoLocal, String equipoVisitante) {
        // Aquí asumimos que el orden de los equipos es importante, pero invertido no es el mismo partido
        return equipoLocal + " vs " + equipoVisitante;
    }

    // Métodos toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jornada ").append(numero)
          .append(" Total Partidos: ").append(partidos.size())
          .append("\nPartidos:\n");
        for (Partido partido : partidos) {
            sb.append(partido.toString()).append("\n");  // Se asume que la clase Partido tiene un buen método toString
        }
        return sb.toString();
    }

    // Métodos equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jornada jornada = (Jornada) obj;
        return numero == jornada.numero;
    }

    // Método para obtener los partidos de la jornada
    public List<Partido> obtenerPartidos() {
        return new ArrayList<>(partidos);  // Retornamos una copia para evitar modificaciones externas
    }

    // Método para agregar un partido a la jornada con validación de duplicados
    public void agregarPartido(Partido partido) {
        if (partido != null && !partidos.contains(partido)) {
            partidos.add(partido);
        }
    }

    // Método para eliminar un partido de la jornada
    public void eliminarPartido(Partido partido) {
        if (partido != null) {
            partidos.remove(partido);
        }
    }

    // Método para buscar un partido en la jornada
    public Partido buscarPartido(String equipoLocal, String equipoVisitante) {
        for (Partido partido : partidos) {
            if (partido.getEquipoLocal().equals(equipoLocal) &&
                partido.getEquipoVisitante().equals(equipoVisitante)) {
                return partido;
            }
        }
        return null;  // Retorna null si no encuentra el partido
    }
}

