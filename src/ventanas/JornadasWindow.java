package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;

import clases.Equipo;
import clases.Jornada;
import clases.Partido;
import clases.Temporada;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    
    private JButton btnGuardar;
    private JButton btnExportarPDF;
    private DefaultTableModel modelClasificacion;
    
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
    	
    	// Crear la tabla y el modelo
    	modelClasificacion = new DefaultTableModel(
    	    new Object[][] {}, // Datos iniciales (vacíos)
    	    new String[] {"Posición", "Equipo", "Puntos", "Partidos Jugados"} // Encabezados de la tabla
    	);
    	tablaClasificacion = new JTable(modelClasificacion);

    	// Agregar la tabla a un JScrollPane
    	JScrollPane scrollPane = new JScrollPane(tablaClasificacion);
    	
    	   // 1. Inicializar componentes críticos primero
        btnGuardar = new JButton("Guardar");
        btnExportarPDF = new JButton("Exportar a PDF");
    	
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
       
        
     // En el ActionListener de comboBoxTemporada
        comboBoxTemporada.addActionListener(e -> {
            cargarJornadas();
            Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
            if (temporadaSeleccionada != null) {
                ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();
                actualizarTablaClasificacion(equipos);
            }
            actualizarEstadoUI(); // Actualizar UI al cambiar temporada
        });

        // En el ActionListener de comboBoxJornada
        comboBoxJornada.addActionListener(e -> {
            mostrarPartidosDeJornada();
            verificarJornadaJugados();
            Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
            if (temporadaSeleccionada != null) {
                calcularClasificacion(temporadaSeleccionada);
                ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();
                actualizarTablaClasificacion(equipos);
            }
            actualizarEstadoUI(); // Actualizar UI al cambiar jornada
        });
        
        
        
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
		
		  
        cargarJornadas();
        mostrarPartidosDeJornada(); // Mostrar los partidos de la jornada seleccionada
        verificarJornadaJugados(); // Verificar si la jornada está jugada
		
		JButton btnExportarXML = new JButton("Exportar XML");
		btnExportarXML.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
        		
        		ArrayList<Temporada> temporadas = new Temporada(0, "").cargarTemporadas();
        		

        		for (Temporada t : temporadas) {
        		    System.out.println(t.mostrarDetalles());
        		}


        		Temporada.generarXMLDesdeListaTemporadas(temporadas, "temporada");

        	}
		});
		panel_13.add(btnExportarXML);
		
		btnGuardar = new JButton("Guardar");
		panel_13.add(btnGuardar);
		
		btnGuardar.addActionListener(e -> {
		    Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
		    if (temporadaSeleccionada != null) {
		        Jornada jornadaSeleccionada = (Jornada) comboBoxJornada.getSelectedItem();
		        if (jornadaSeleccionada != null) {
		                ArrayList<Partido> partidos = jornadaSeleccionada.getPartidos();

		                try {
		                    if (partidos.size() >= 1) {
		                        String gL1 = golLocal_1.getText();
		                        String gV1 = golVisitante_1.getText();
		                        if (!gL1.isEmpty() && !gV1.isEmpty()) {
		                            partidos.get(0).setGolesLocal(Integer.parseInt(gL1));
		                            partidos.get(0).setGolesVisitante(Integer.parseInt(gV1));
		                        }
		                    }
		                    if (partidos.size() >= 2) {
		                        String gL2 = golLocal_2.getText();
		                        String gV2 = golVisitante_2.getText();
		                        if (!gL2.isEmpty() && !gV2.isEmpty()) {
		                            partidos.get(1).setGolesLocal(Integer.parseInt(gL2));
		                            partidos.get(1).setGolesVisitante(Integer.parseInt(gV2));
		                        }
		                    }
		                    if (partidos.size() >= 3) {
		                        String gL3 = golLocal_3.getText();
		                        String gV3 = golVisitante_3.getText();
		                        if (!gL3.isEmpty() && !gV3.isEmpty()) {
		                            partidos.get(2).setGolesLocal(Integer.parseInt(gL3));
		                            partidos.get(2).setGolesVisitante(Integer.parseInt(gV3));
		                        }
		                    }

		                    // Guardar las temporadas actualizadas
		                    Temporada.guardarTemporadas(listaTemporadas);
		                    JOptionPane.showMessageDialog(null, "Resultados guardados correctamente.");

		                    // Recalcular clasificación
		                    calcularClasificacion(temporadaSeleccionada);
		                    actualizarTablaClasificacion(temporadaSeleccionada.getListEquipos());

		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Error: Asegúrate de ingresar solo números en los goles.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		    }
		});
		
		
		btnExportarPDF = new JButton("Exportar a PDF");
		panel_13.add(btnExportarPDF);
		btnExportarPDF.addActionListener(e -> {
		    exportarClasificacionAPDF(modelClasificacion);
		});




		
		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 gestionAdmin ventanaAdmin = new gestionAdmin();
                 ventanaAdmin.setVisible(true);
                 dispose();
			}
		});
		panel_16.add(btnAtras);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_2.add(panel_14, BorderLayout.NORTH);
		
		JLabel lblNewLabel_7 = new JLabel("CLASIFICACION");
		panel_14.add(lblNewLabel_7);
		
		tablaClasificacion = new JTable();
		panel_2.add(tablaClasificacion, BorderLayout.CENTER);
		
		
		actualizarEstadoUI(); 
    }
 // Método para cargar temporadas
 // Cargar las temporadas desde el archivo .ser y agregar los objetos Temporada al JComboBox
    private void cargarTemporadasCombo() {
        Temporada temp = new Temporada(0, ""); // Instancia temporal para cargar temporadas
        listaTemporadas = temp.cargarTemporadas();

        // Limpiar el JComboBox antes de agregar los nuevos elementos
        comboBoxTemporada.removeAllItems();

        Temporada temporadaActiva = null; // Variable para guardar la temporada activa

        // Agregar las temporadas al JComboBox
        for (Temporada temporada : listaTemporadas) {
            comboBoxTemporada.addItem(temporada);

            // Verificar si la temporada está activa y seleccionarla inmediatamente
            if (temporada.isActiva() && temporadaActiva == null) {
                temporadaActiva = temporada;
            }
        }

        // Seleccionar la temporada activa (si existe)
        if (temporadaActiva != null) {
            comboBoxTemporada.setSelectedItem(temporadaActiva);
            comboBoxTemporada.repaint();  // Forzar actualización visual

            // Cargar las jornadas de la temporada activa
            cargarJornadas();

            // Seleccionar la primera jornada de la temporada activa
            if (comboBoxJornada.getItemCount() > 0) {
                comboBoxJornada.setSelectedIndex(0); // Seleccionar la primera jornada
                mostrarPartidosDeJornada(); // Mostrar los partidos de la jornada seleccionada
                verificarJornadaJugados(); // Verificar si la jornada está jugada
            }
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
                        
                        // Solo mostrar los goles si el partido ha sido jugado
                        if (partido1.getGolesLocal() != -1 && partido1.getGolesVisitante() != -1) {
                            golLocal_1.setText(String.valueOf(partido1.getGolesLocal()));
                            golVisitante_1.setText(String.valueOf(partido1.getGolesVisitante()));
                        } else {
                            golLocal_1.setText("");  // Vacío si no ha sido jugado
                            golVisitante_1.setText("");  // Vacío si no ha sido jugado
                        }
                    }
                    if (partidos.size() >= 2) {
                        Partido partido2 = partidos.get(1);
                        lblLocal_2.setText(partido2.getEquipoLocal().getNombre());
                        lblVisitante_2.setText(partido2.getEquipoVisitante().getNombre());
                        
                        if (partido2.getGolesLocal() != -1 && partido2.getGolesVisitante() != -1) {
                            golLocal_2.setText(String.valueOf(partido2.getGolesLocal()));
                            golVisitante_2.setText(String.valueOf(partido2.getGolesVisitante()));
                        } else {
                            golLocal_2.setText("");  // Vacío si no ha sido jugado
                            golVisitante_2.setText("");  // Vacío si no ha sido jugado
                        }
                    }
                    if (partidos.size() >= 3) {
                        Partido partido3 = partidos.get(2);
                        lblLocal_3.setText(partido3.getEquipoLocal().getNombre());
                        lblVisitante_3.setText(partido3.getEquipoVisitante().getNombre());
                        
                        if (partido3.getGolesLocal() != -1 && partido3.getGolesVisitante() != -1) {
                            golLocal_3.setText(String.valueOf(partido3.getGolesLocal()));
                            golVisitante_3.setText(String.valueOf(partido3.getGolesVisitante()));
                        } else {
                            golLocal_3.setText("");  // Vacío si no ha sido jugado
                            golVisitante_3.setText("");  // Vacío si no ha sido jugado
                        }
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


 // Método para actualizar la tabla de clasificación después de cada jornada
    public void calcularClasificacion(Temporada temporada) {
        // Reiniciar los puntos de todos los equipos
        for (Equipo equipo : temporada.getListEquipos()) {
            equipo.setPuntos(0); // Reiniciar los puntos a 0
        }

        // Recorrer todas las jornadas de la temporada
        for (Jornada jornada : temporada.getListJornadas()) {
            // Recorrer los partidos de cada jornada
            for (Partido partido : jornada.getPartidos()) {
                // Verificar si el partido ha sido jugado (tiene un resultado válido)
                if (partido.getGolesLocal() != -1 && partido.getGolesVisitante() != -1) {
                    partido.actualizarPuntos(); // Actualizar los puntos de los equipos
                }
            }
        }

        // Ordenar los equipos por puntos (de mayor a menor)
        ArrayList<Equipo> equipos = temporada.getListEquipos();
        equipos.sort((e1, e2) -> Integer.compare(e2.getPuntos(), e1.getPuntos()));
    }


    
    private void verificarJornadaJugados() {
        Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
        if (temporadaSeleccionada != null) {
            Jornada jornadaSeleccionada = (Jornada) comboBoxJornada.getSelectedItem();
            if (jornadaSeleccionada != null) {
                ArrayList<Partido> partidos = jornadaSeleccionada.getPartidos();
                boolean todosJugados = true;

                // Verificar si todos los partidos están jugados, considerando los JTextField
                for (int i = 0; i < partidos.size(); i++) {
                    Partido partido = partidos.get(i);

                    // Verifica si los goles en los JTextField no son -1
                    int golesLocal = getGolLocalDesdeJTextField(i);  // Método para obtener el valor del JTextField
                    int golesVisitante = getGolVisitanteDesdeJTextField(i);  // Método para obtener el valor del JTextField

                    // Si cualquier gol es -1, la jornada no está completa
                    if (golesLocal == -1 || golesVisitante == -1) {
                        todosJugados = false;
                        break;  // Si encontramos un partido no jugado, salimos del bucle
                    }
                }}
            }
        }
    


    // Métodos para obtener los valores de goles de los JTextField
    private int getGolLocalDesdeJTextField(int index) {
        switch (index) {
            case 0:
                return golLocal_1.getText().isEmpty() ? -1 : parseGol(golLocal_1.getText());
            case 1:
                return golLocal_2.getText().isEmpty() ? -1 : parseGol(golLocal_2.getText());
            case 2:
                return golLocal_3.getText().isEmpty() ? -1 : parseGol(golLocal_3.getText());
            default:
                return -1;
        }
    }

    private int getGolVisitanteDesdeJTextField(int index) {
        switch (index) {
            case 0:
                return golVisitante_1.getText().isEmpty() ? -1 : parseGol(golVisitante_1.getText());
            case 1:
                return golVisitante_2.getText().isEmpty() ? -1 : parseGol(golVisitante_2.getText());
            case 2:
                return golVisitante_3.getText().isEmpty() ? -1 : parseGol(golVisitante_3.getText());
            default:
                return -1;
        }
    }
 // Método auxiliar para parsear el texto a número, manejando la excepción si es necesario
    private int parseGol(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1; // Si no se puede parsear, devolvemos -1 como valor inválido
        }
    }


 // Método para actualizar la tabla de clasificación
    private void actualizarTablaClasificacion(ArrayList<Equipo> equipos) {
        // Limpiar las filas existentes
        modelClasificacion.setRowCount(0);

        // Recorrer la lista de equipos para llenar la tabla
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);

            // Añadir los datos del equipo a la tabla
            modelClasificacion.addRow(new Object[] {
                i + 1,  // Posición (comienza en 1)
                equipo.getNombre(),
                equipo.getPuntos(),
                equipo.getPartidosJugados()
            });
        }
    }

    
    public void exportarClasificacionAPDF(DefaultTableModel modelClasificacion) {
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("clasificacion.pdf"));
            documento.open();

            // Metadata
            documento.addTitle("Clasificación de Equipos");
            documento.addSubject("Exportación a PDF");
            documento.addKeywords("Java, PDF, iText, Clasificación, Fútbol");
            documento.addAuthor("Tu Nombre");
            documento.addCreator("Tu Aplicación");

            // Título
            Paragraph titulo = new Paragraph("Clasificación de Equipos", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.add(new Paragraph(" ")); // Espacio
            documento.add(titulo);

            // Tabla con 5 columnas
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);

            // Encabezados
            String[] columnas = {"Posición", "Equipo", "Puntos", "PJ"};
            for (String columna : columnas) {
                PdfPCell cell = new PdfPCell(new Phrase(columna, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(cell);
            }

            // Datos de la tabla
            for (int fila = 0; fila < modelClasificacion.getRowCount(); fila++) {
                tabla.addCell(modelClasificacion.getValueAt(fila, 0).toString()); // Posición
                tabla.addCell(modelClasificacion.getValueAt(fila, 1).toString()); // Equipo
                
                // Aquí podrías insertar una imagen si los escudos son accesibles como URLs o rutas locales
                //tabla.addCell("(Escudo)"); // Marcador de imagen
                
                tabla.addCell(modelClasificacion.getValueAt(fila, 2).toString()); // Partidos Jugados
                tabla.addCell(modelClasificacion.getValueAt(fila, 3).toString()); // Puntos
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "El documento PDF se ha generado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void actualizarEstadoUI() {
        Temporada temporadaSeleccionada = (Temporada) comboBoxTemporada.getSelectedItem();
        String estado = temporadaSeleccionada.getEstado();
        
        // Corrected the comparison operator (== instead of =)
        if (estado != null && estado.equals("Finalizado")) {

            // Deshabilitar campos de texto y botón Guardar si la temporada está finalizada
            golLocal_1.setEditable(false);
            golVisitante_1.setEditable(false);
            golLocal_2.setEditable(false);
            golVisitante_2.setEditable(false);
            golLocal_3.setEditable(false);
            golVisitante_3.setEditable(false);
            btnGuardar.setEnabled(false);
        }
}
    
    
}