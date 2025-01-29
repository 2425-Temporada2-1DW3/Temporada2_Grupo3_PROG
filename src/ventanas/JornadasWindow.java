package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Jornada;
import clases.Partido;
import clases.Temporada;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class JornadasWindow extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField golLocal_1;
    private JTextField golVisitante_1;
    private JTextField golLocal_2;
    private JTextField golVisitante_2;
    private JTextField golLocal_3;
    private JTextField golVisitante_3;
    
    private JLabel lblLocal_1;
    private JLabel lblLocal_2;
    private JLabel lblLocal_3;
    private JLabel lblVisitante_1;
    private JLabel lblVisitante_2;
    private JLabel lblVisitante_3;
    
    private JTable tablaClasificacion;
    private JComboBox<Temporada> comboBoxTemporada;
    private JComboBox<Jornada> comboBoxJornada;
    
    private ArrayList<Temporada> listaTemporadas; // Lista de temporadas

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JornadasWindow frame = new JornadasWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public JornadasWindow() {
    	
        setTitle("Gestion Jornadas - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 757, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Gestion Jornadas");
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.WEST);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3, BorderLayout.NORTH);
        
        JLabel lblNewLabel_1 = new JLabel("Temporada");
        panel_3.add(lblNewLabel_1);
        
        comboBoxTemporada = new JComboBox<Temporada>();
        panel_3.add(comboBoxTemporada);
        
        cargarTemporadasCombo();
        
        JPanel panel_4 = new JPanel();
        panel_1.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_5 = new JPanel();
        panel_4.add(panel_5, BorderLayout.NORTH);
        
        JLabel lblNewLabel_2 = new JLabel("Jornada");
        panel_5.add(lblNewLabel_2);
        
        comboBoxJornada = new JComboBox<Jornada>();
        panel_5.add(comboBoxJornada);
        
     // Agregar un ActionListener para actualizar las jornadas cuando se selecciona una temporada
        comboBoxTemporada.addActionListener(e -> cargarJornadas());
        
     // Agregar un ActionListener para mostrar los partidos cuando se seleccione una jornada
        comboBoxJornada.addActionListener(e -> mostrarPartidosDeJornada());

        
        JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		this.lblLocal_1 = new JLabel("New label");
		panel_7.add(lblLocal_1);
		
		this.golLocal_1 = new JTextField();
		panel_7.add(golLocal_1);
		golLocal_1.setColumns(3);
		
		JLabel vs1 = new JLabel("VS");
		panel_7.add(vs1);
		
		this.golVisitante_1 = new JTextField();
		panel_7.add(golVisitante_1);
		golVisitante_1.setColumns(3);
		
		this.lblVisitante_1 = new JLabel("New label");
		panel_7.add(lblVisitante_1);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		
		this.lblLocal_2 = new JLabel("New label");
		panel_9.add(lblLocal_2);
		
		this.golLocal_2 = new JTextField();
		golLocal_2.setColumns(3);
		panel_9.add(golLocal_2);
		
		JLabel vs2 = new JLabel("VS");
		panel_9.add(vs2);
		
		this.golVisitante_2 = new JTextField();
		golVisitante_2.setColumns(3);
		panel_9.add(golVisitante_2);
		
		this.lblVisitante_2 = new JLabel("New label");
		panel_9.add(lblVisitante_2);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		
		this.lblLocal_3 = new JLabel("New label");
		panel_11.add(lblLocal_3);
		
		this.golLocal_3 = new JTextField();
		golLocal_3.setColumns(3);
		panel_11.add(golLocal_3);
		
		JLabel vs3 = new JLabel("VS");
		panel_11.add(vs3);
		
		this.golVisitante_3 = new JTextField();
		golVisitante_3.setColumns(3);
		panel_11.add(golVisitante_3);
		
		this.lblVisitante_3 = new JLabel("New label");
		panel_11.add(lblVisitante_3);
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13, BorderLayout.NORTH);
		
		JButton btnGuardar = new JButton("Guardar");
		panel_13.add(btnGuardar);
		btnGuardar.addActionListener(e -> {
		    // Obtener la temporada seleccionada
		    Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
		    if (temporadaSeleccionada != null) {
		        // Obtener la jornada seleccionada
		        Jornada jornadaSeleccionada = (Jornada) comboBoxJornada.getSelectedItem();
		        if (jornadaSeleccionada != null) {
		            ArrayList<Partido> partidos = jornadaSeleccionada.getPartidos();

		            try {
		                // Guardar los resultados ingresados y actualizar puntos
		                if (partidos.size() >= 1) {
		                    partidos.get(0).setGolesLocal(Integer.parseInt(golLocal_1.getText()));
		                    partidos.get(0).setGolesVisitante(Integer.parseInt(golVisitante_1.getText()));
		                    partidos.get(0).actualizarPuntos();
		                }
		                if (partidos.size() >= 2) {
		                    partidos.get(1).setGolesLocal(Integer.parseInt(golLocal_2.getText()));
		                    partidos.get(1).setGolesVisitante(Integer.parseInt(golVisitante_2.getText()));
		                    partidos.get(1).actualizarPuntos();
		                }
		                if (partidos.size() >= 3) {
		                    partidos.get(2).setGolesLocal(Integer.parseInt(golLocal_3.getText()));
		                    partidos.get(2).setGolesVisitante(Integer.parseInt(golVisitante_3.getText()));
		                    partidos.get(2).actualizarPuntos();
		                }

		                // Guardar todas las temporadas en el archivo
		                Temporada.guardarTemporadas(listaTemporadas);
		                JOptionPane.showMessageDialog(null, "Resultados guardados correctamente.");
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Error: Asegúrate de ingresar solo números en los goles.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});

		
		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_6 = new JLabel("Cerrar Sesion");
		panel_16.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Atras");
		panel_16.add(lblNewLabel_8);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_2.add(panel_14, BorderLayout.NORTH);
		
		JLabel lblNewLabel_7 = new JLabel("CLASIFICACION");
		panel_14.add(lblNewLabel_7);
		
		tablaClasificacion = new JTable();
		panel_2.add(tablaClasificacion, BorderLayout.CENTER);
    }

    // Método para cargar temporadas
 // Cargar las temporadas desde el archivo .ser y agregar los objetos Temporada al JComboBox
    private void cargarTemporadasCombo() {
        // Cargar las temporadas desde el archivo .ser
        listaTemporadas = Temporada.cargarTemporadas(); // Método de la clase Temporada

        // Limpiar el JComboBox antes de agregar los nuevos elementos
        comboBoxTemporada.removeAllItems();

        // Agregar las temporadas al JComboBox (el objeto Temporada completo, no solo su nombre)
        for (Temporada temporada : listaTemporadas) {
            comboBoxTemporada.addItem(temporada);  // Agregar la instancia completa de Temporada
        }
    }


    // Método para mostrar los partidos de la jornada seleccionada
 // Método para mostrar los partidos de la jornada seleccionada
    private void mostrarPartidosDeJornada() {
        // Obtener la temporada seleccionada como objeto completo
        Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();

        if (temporadaSeleccionada != null) {
            // Obtener la jornada seleccionada
            Jornada jornadaSeleccionada = (Jornada) comboBoxJornada.getSelectedItem();

            if (jornadaSeleccionada != null) {
                int numeroJornadaSeleccionada = jornadaSeleccionada.getNumero(); // Obtener el número de jornada

                Jornada jornada = temporadaSeleccionada.getListJornadas().stream()
                    .filter(j -> j.getNumero() == numeroJornadaSeleccionada) // Comparación correcta
                    .findFirst()
                    .orElse(null);

                if (jornada != null) {
                    ArrayList<Partido> partidos = jornada.getPartidos();

                    // Mostrar los partidos en los campos de texto
                    if (partidos.size() >= 1) {
                        Partido partido1 = partidos.get(0);
                        lblLocal_1.setText(partido1.getEquipoLocal().getNombre());
                        lblVisitante_1.setText(partido1.getEquipoVisitante().getNombre());
                        golLocal_1.setText(String.valueOf(partido1.getGolesLocal()));
                        golVisitante_1.setText(String.valueOf(partido1.getGolesVisitante()));
                    }
                    if (partidos.size() >= 2) {
                        Partido partido2 = partidos.get(1);
                        lblLocal_2.setText(partido2.getEquipoLocal().getNombre());
                        lblVisitante_2.setText(partido2.getEquipoVisitante().getNombre());
                        golLocal_2.setText(String.valueOf(partido2.getGolesLocal()));
                        golVisitante_2.setText(String.valueOf(partido2.getGolesVisitante()));
                    }
                    if (partidos.size() >= 3) {
                        Partido partido3 = partidos.get(2);
                        lblLocal_3.setText(partido3.getEquipoLocal().getNombre());
                        lblVisitante_3.setText(partido3.getEquipoVisitante().getNombre());
                        golLocal_3.setText(String.valueOf(partido3.getGolesLocal()));
                        golVisitante_3.setText(String.valueOf(partido3.getGolesVisitante()));
                    }
                }
            }
        }
    }


 // Método para cargar las jornadas de la temporada seleccionada
    private void cargarJornadas() {
        // Limpiar el JComboBox de jornadas
        comboBoxJornada.removeAllItems();

        // Obtener la temporada seleccionada
        Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();

        if (temporadaSeleccionada != null) {
            System.out.println("Temporada seleccionada: " + temporadaSeleccionada.getNombre());
            System.out.println("Jornadas disponibles: " + temporadaSeleccionada.getListJornadas().size());

            // Agregar las jornadas al JComboBox
            for (Jornada jornada : temporadaSeleccionada.getListJornadas()) {
                System.out.println("Agregando jornada: " + jornada.getNumero());
                comboBoxJornada.addItem(jornada);  // Agregar el objeto Jornada completo
            }
        } else {
            System.out.println("No se seleccionó ninguna temporada.");
        }
    }



    // Método para obtener la temporada por nombre
    private Temporada obtenerTemporadaPorNombre(String nombre) {
        for (Temporada temporada : listaTemporadas) {
            if (temporada.getNombre().equals(nombre)) {
                return temporada;
            }
        }
        return null; // Si no se encuentra la temporada
    }
    
    
}



