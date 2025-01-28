package ventanas;

import clases.*;
import java.awt.EventQueue;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

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
	private JComboBox<String> comboBoxTemporada;
	private JComboBox<String> comboBoxJornada;
	
	private ArrayList<Temporada> listaTemporadas; // Lista de temporadas
    private Temporada temporadaSeleccionada; // Temporada seleccionada

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

	/**
	 * Create the frame.
	 */
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
		
		comboBoxTemporada = new JComboBox<String>();
		panel_3.add(comboBoxTemporada);
		
		// Cargar temporadas desde el archivo .ser
	    cargarTemporadas();
	    
	    
	    
	    
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Jornada");
		panel_5.add(lblNewLabel_2);
		
		comboBoxJornada = new JComboBox<String>();
	    panel_5.add(comboBoxJornada);
	 // Listener para cargar las jornadas al seleccionar una temporada
	    comboBoxJornada.addActionListener(e -> mostrarPartidosDeJornada());
	    cargarJornadas();
        
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		
		lblLocal_1 = new JLabel("New label");
		panel_7.add(lblLocal_1);
		
		golLocal_1 = new JTextField();
		panel_7.add(golLocal_1);
		golLocal_1.setColumns(3);
		
		JLabel vs1 = new JLabel("VS");
		panel_7.add(vs1);
		
		golVisitante_1 = new JTextField();
		panel_7.add(golVisitante_1);
		golVisitante_1.setColumns(3);
		
		JLabel lblVisitante_1 = new JLabel("New label");
		panel_7.add(lblVisitante_1);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		
		JLabel lblLocal_2 = new JLabel("New label");
		panel_9.add(lblLocal_2);
		
		golLocal_2 = new JTextField();
		golLocal_2.setColumns(3);
		panel_9.add(golLocal_2);
		
		JLabel vs2 = new JLabel("VS");
		panel_9.add(vs2);
		
		golVisitante_2 = new JTextField();
		golVisitante_2.setColumns(3);
		panel_9.add(golVisitante_2);
		
		JLabel lblVisitante_2 = new JLabel("New label");
		panel_9.add(lblVisitante_2);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		
		JLabel lblLocal_3 = new JLabel("New label");
		panel_11.add(lblLocal_3);
		
		golLocal_3 = new JTextField();
		golLocal_3.setColumns(3);
		panel_11.add(golLocal_3);
		
		JLabel vs3 = new JLabel("VS");
		panel_11.add(vs3);
		
		golVisitante_3 = new JTextField();
		golVisitante_3.setColumns(3);
		panel_11.add(golVisitante_3);
		
		JLabel lblVisitante_3 = new JLabel("New label");
		panel_11.add(lblVisitante_3);
		
		JPanel panel_12 = new JPanel();
		panel_10.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13, BorderLayout.NORTH);
		
		JButton btnGuardar = new JButton("Guardar");
		panel_13.add(btnGuardar);
		
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
	
	private void cargarTemporadas() {
	    // Crear el manager para cargar las temporadas
	    TemporadaManager temporadaManager = new TemporadaManager();
	    
	    // Cargar temporadas desde el archivo
	    listaTemporadas = temporadaManager.cargarTemporadas();  // Obtienes la lista de temporadas desde el archivo

	    // Limpiar comboBox de temporadas antes de agregar los nuevos elementos
	    comboBoxTemporada.removeAllItems();
	    
	    // Agregar cada temporada al comboBox
	    for (Temporada temporada : listaTemporadas) {
	        comboBoxTemporada.addItem(temporada.getNombre());  // Usamos el nombre de la temporada
	    }
	}

	private void cargarJornadas() {
	    // Limpiar el JComboBox de jornadas
	    comboBoxJornada.removeAllItems();

	    // Obtener la temporada seleccionada
	    String nombreTemporada = (String) comboBoxTemporada.getSelectedItem();
	    Temporada temporada = obtenerTemporadaPorNombre(nombreTemporada);

	    // Verificar si la temporada fue encontrada
	    if (temporada != null) {
	        // Verificar cuántas jornadas tiene la temporada
	        ArrayList<Jornada> jornadas = temporada.getListJornadas();

	        // Si la temporada tiene jornadas, agregar al comboBox
	        if (!jornadas.isEmpty()) {
	            for (Jornada jornada : jornadas) {
	                comboBoxJornada.addItem("Jornada " + jornada.getNumero());
	            }
	        } else {
	            System.out.println("No hay jornadas para esta temporada.");
	        }
	    } else {
	        System.out.println("No se encontró la temporada seleccionada.");
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
    
	// Método para mostrar los partidos de la jornada seleccionada
	private void mostrarPartidosDeJornada() {
	    // Obtener la temporada seleccionada
	    String nombreTemporada = (String) comboBoxTemporada.getSelectedItem();
	    Temporada temporada = obtenerTemporadaPorNombre(nombreTemporada);

	    if (temporada != null) {
	        // Obtener la jornada seleccionada
	        String nombreJornada = (String) comboBoxJornada.getSelectedItem();
	        int numeroJornada = Integer.parseInt(nombreJornada.replace("Jornada ", "")); // Extraer el número de la jornada

	        // Buscar la jornada en la temporada
	        Jornada jornada = temporada.getListJornadas().stream()
	            .filter(j -> j.getNumero() == numeroJornada)
	            .findFirst()
	            .orElse(null);

	        if (jornada != null) {
	            // Obtener los partidos de la jornada
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
	        } else {
	            JOptionPane.showMessageDialog(this, "No se encontró la jornada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "No se encontró la temporada seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
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
