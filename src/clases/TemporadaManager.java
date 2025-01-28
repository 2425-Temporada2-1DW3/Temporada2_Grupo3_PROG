package clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TemporadaManager {

	// Guardar todas las temporadas en un solo archivo .ser
    public void guardarTemporadas(ArrayList<Temporada> temporadas) {
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temporadas.ser"))) {
            temporadas = (ArrayList<Temporada>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temporadas;
    }
}
