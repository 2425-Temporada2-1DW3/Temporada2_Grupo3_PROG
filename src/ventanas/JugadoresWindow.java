package ventanas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import clases.Equipo;
import clases.Jugador;
import clases.Temporada;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JugadoresWindow extends JFrame implements WindowListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxTemporada;
    private JComboBox<String> comboBoxEquipo;
    private JPanel panelJugadores;
    private ArrayList<Temporada> temporadas;
    private ArrayList<Equipo> equiposActuales;
    private boolean hayCambiosNoGuardados = false; // Indicador de cambios no guardados

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JugadoresWindow frame = new JugadoresWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public JugadoresWindow() {
        setTitle("Gestion Jugadores - Txurdi Liga");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
        setBounds(100, 100, 730, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel lblNewLabel = new JLabel(new ImageIcon(JugadoresWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.NORTH);

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3);
        comboBoxEquipo = new JComboBox<>();
        panel_3.add(comboBoxEquipo);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4);
        JLabel lblNewLabel_1 = new JLabel("Temporada");
        panel_4.add(lblNewLabel_1);
        comboBoxTemporada = new JComboBox<>();
        panel_4.add(comboBoxTemporada);

        panelJugadores = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JScrollPane scrollPanelJugadores = new JScrollPane(panelJugadores);
        panel_1.add(scrollPanelJugadores, BorderLayout.CENTER);

        JPanel panel_6 = new JPanel(new GridBagLayout());
        panel_1.add(panel_6, BorderLayout.SOUTH);
        
                JButton btnAgregar = new JButton("Agregar");
                GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
                gbc_btnAgregar.insets = new Insets(0, 0, 0, 5);
                gbc_btnAgregar.gridx = 4;
                gbc_btnAgregar.gridy = 0;
                panel_6.add(btnAgregar, gbc_btnAgregar);
        JButton btnModificar = new JButton("Modificar");
        GridBagConstraints gbc_btnModificar = new GridBagConstraints();
        gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
        gbc_btnModificar.gridx = 6;
        gbc_btnModificar.gridy = 0;
        panel_6.add(btnModificar, gbc_btnModificar);
        JButton btnEliminar = new JButton("Eliminar");
        GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
        gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
        gbc_btnEliminar.gridx = 8;
        gbc_btnEliminar.gridy = 0;
        panel_6.add(btnEliminar, gbc_btnEliminar);
        
        
        btnEliminar.addActionListener(e -> {
            int indiceTemporada = comboBoxTemporada.getSelectedIndex();
            int indiceEquipo = comboBoxEquipo.getSelectedIndex();

            if (indiceTemporada >= 0 && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
                Temporada tempSeleccionada = temporadas.get(indiceTemporada);
                Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);
                
                ArrayList<Jugador> jugadores = tempSeleccionada.obtenerJugadoresDeEquipo(equipoSeleccionado.getNombre());
                
                if (jugadores.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay jugadores para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String[] nombresJugadores = jugadores.stream().map(Jugador::getNombre).toArray(String[]::new);
                
                String jugadorAEliminar = (String) JOptionPane.showInputDialog(
                        this, "Seleccione el jugador a eliminar:", "Eliminar Jugador",
                        JOptionPane.QUESTION_MESSAGE, null, nombresJugadores, nombresJugadores[0]
                );
                
                if (jugadorAEliminar != null) {
                    tempSeleccionada.eliminarJugadorDeEquipo(equipoSeleccionado.getNombre(), jugadorAEliminar);
                    JOptionPane.showMessageDialog(this, "Jugador eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarJugadores();
                    hayCambiosNoGuardados = true;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada y un equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        JButton btnDetalles = new JButton("Detalles");
        GridBagConstraints gbc_btnDetalles = new GridBagConstraints();
        gbc_btnDetalles.insets = new Insets(0, 0, 0, 5);
        gbc_btnDetalles.gridx = 12; // Ajusta la posición según sea necesario
        gbc_btnDetalles.gridy = 0;
        panel_6.add(btnDetalles, gbc_btnDetalles);
        
        JButton btnGuardar = new JButton("Guardar");
        GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
        gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
        gbc_btnGuardar.gridx = 10;
        gbc_btnGuardar.gridy = 0;
        panel_6.add(btnGuardar, gbc_btnGuardar);

        
        btnGuardar.addActionListener(e -> guardarEquipos());
        
        JPanel panel_8 = new JPanel();
        GridBagConstraints gbc_panel_8 = new GridBagConstraints();
        gbc_panel_8.gridx = 14;
        gbc_panel_8.gridy = 0;
        panel_6.add(panel_8, gbc_panel_8);
        
                JButton btnAtras = new JButton("Atras");
                contentPane.add(btnAtras, BorderLayout.SOUTH);
                btnAtras.addActionListener(e -> {
                	if (hayCambiosNoGuardados) {
                        int opcion = JOptionPane.showConfirmDialog(
                        		JugadoresWindow.this,
                            "Hay cambios no guardados. ¿Desea guardar antes de salir?",
                            "Cambios no guardados",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE
                        );

                        if (opcion == JOptionPane.YES_OPTION) {
                            guardarEquipos();
                        } else if (opcion == JOptionPane.NO_OPTION) {
                            gestionAdmin ventanaAdmin = new gestionAdmin();
                            ventanaAdmin.setVisible(true);
                            dispose();
                        }
                        // Si elige CANCELAR, no se hace nada (no se cierra la ventana)
                    } else {
                        gestionAdmin ventanaAdmin = new gestionAdmin();
                        ventanaAdmin.setVisible(true);
                        dispose();
                    } // Cerrar la ventana
                });

        cargarTemporadas();
        actualizarEquipos();

        comboBoxTemporada.addActionListener(e -> actualizarEquipos());
        comboBoxEquipo.addActionListener(e -> actualizarJugadores());
        


        btnAgregar.addActionListener(e -> {
            int indiceTemporada = comboBoxTemporada.getSelectedIndex();
            int indiceEquipo = comboBoxEquipo.getSelectedIndex();

            if (indiceTemporada >= 0 && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
                Temporada tempSeleccionada = temporadas.get(indiceTemporada);
                Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);

                
                
             // Verificar si el equipo ya tiene 15 jugadores
                if (equipoSeleccionado.getJugadores().size() >= 15) {
                    JOptionPane.showMessageDialog(null, "El equipo ya tiene 15 jugadores. No se pueden agregar más.", "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                JTextField nombreField = new JTextField();
                JTextField edadField = new JTextField();
                
                // Lista desplegable para la posición
                String[] posiciones = {"Delantero", "Defensa", "Mediocampista", "Portero"};
                JComboBox<String> posicionComboBox = new JComboBox<>(posiciones);
                
                JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
                JLabel imagenLabel = new JLabel();
                
                final File[] archivoImagenSeleccionada = {null}; // Usar File para guardar la imagen seleccionada
                btnSeleccionarImagen.addActionListener(event -> {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        archivoImagenSeleccionada[0] = fileChooser.getSelectedFile();
                        imagenLabel.setIcon(new ImageIcon(archivoImagenSeleccionada[0].getAbsolutePath()));
                    }
                });

                JPanel panel1 = new JPanel();
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
                panel1.add(new JLabel("Nombre:"));
                panel1.add(nombreField);
                panel1.add(new JLabel("Edad:"));
                panel1.add(edadField);
                panel1.add(new JLabel("Posición:"));
                panel1.add(posicionComboBox);
                panel1.add(btnSeleccionarImagen);
                panel1.add(imagenLabel);

                int result = JOptionPane.showConfirmDialog(null, panel1, "Agregar Jugador", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        String nombre = nombreField.getText();
                        int edad = Integer.parseInt(edadField.getText());
                        String posicion = (String) posicionComboBox.getSelectedItem();
                        
                        // Verificar que los campos no estén vacíos y que se haya seleccionado una imagen
                        if (nombre.isEmpty() || posicion.isEmpty() || archivoImagenSeleccionada[0] == null) {
                            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Especificar la ruta en la que deseas guardar la imagen
                        String rutaDestino = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipoSeleccionado.getNombre().toLowerCase().replaceAll("\\s+", "_") + "" + nombre + ".png"; // Cambia esta ruta a la que necesites
                        File destino = new File(rutaDestino);

                        // Copiar la imagen al nuevo destino
                        Files.copy(archivoImagenSeleccionada[0].toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        
                        // Crear el jugador con la imagen copiada
                        ImageIcon imagen = new ImageIcon(destino.getAbsolutePath());
                        Jugador nuevoJugador = new Jugador(nombre, edad, posicion, equipoSeleccionado, imagen);
                        tempSeleccionada.agregarJugadorAEquipo(equipoSeleccionado.getNombre(), nuevoJugador);
                        JOptionPane.showMessageDialog(null, "Jugador agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        actualizarJugadores();
                        hayCambiosNoGuardados = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Edad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una temporada y un equipo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            int indiceTemporada = comboBoxTemporada.getSelectedIndex();
            int indiceEquipo = comboBoxEquipo.getSelectedIndex();

            if (indiceTemporada >= 0 && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
                Temporada tempSeleccionada = temporadas.get(indiceTemporada);
                Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);

                ArrayList<Jugador> jugadores = tempSeleccionada.obtenerJugadoresDeEquipo(equipoSeleccionado.getNombre());

                if (jugadores.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay jugadores para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] nombresJugadores = jugadores.stream().map(Jugador::getNombre).toArray(String[]::new);

                String jugadorAModificar = (String) JOptionPane.showInputDialog(
                        this, "Seleccione el jugador a modificar:", "Modificar Jugador",
                        JOptionPane.QUESTION_MESSAGE, null, nombresJugadores, nombresJugadores[0]
                );

                if (jugadorAModificar != null) {
                    Jugador jugador = jugadores.stream()
                            .filter(j -> j.getNombre().equals(jugadorAModificar))
                            .findFirst()
                            .orElse(null);

                    if (jugador != null) {
                        // Crear un panel para modificar los datos del jugador
                        JTextField nombreField = new JTextField(jugador.getNombre());
                        JTextField edadField = new JTextField(String.valueOf(jugador.getEdad()));
                        String[] posiciones = {"Delantero", "Defensa", "Mediocampista", "Portero"};
                        JComboBox<String> posicionComboBox = new JComboBox<>(posiciones);
                        posicionComboBox.setSelectedItem(jugador.getPosicion());

                        JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen");
                        JLabel imagenLabel = new JLabel(jugador.getImagen());

                        final File[] archivoImagenSeleccionada = {null};
                        btnSeleccionarImagen.addActionListener(event -> {
                            JFileChooser fileChooser = new JFileChooser();
                            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                archivoImagenSeleccionada[0] = fileChooser.getSelectedFile();
                                imagenLabel.setIcon(new ImageIcon(archivoImagenSeleccionada[0].getAbsolutePath()));
                            }
                        });

                        JPanel panelModificar = new JPanel();
                        panelModificar.setLayout(new BoxLayout(panelModificar, BoxLayout.Y_AXIS));
                        panelModificar.add(new JLabel("Nombre:"));
                        panelModificar.add(nombreField);
                        panelModificar.add(new JLabel("Edad:"));
                        panelModificar.add(edadField);
                        panelModificar.add(new JLabel("Posición:"));
                        panelModificar.add(posicionComboBox);
                        panelModificar.add(btnSeleccionarImagen);
                        panelModificar.add(imagenLabel);

                        int result = JOptionPane.showConfirmDialog(null, panelModificar, "Modificar Jugador", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                String nombre = nombreField.getText();
                                int edad = Integer.parseInt(edadField.getText());
                                String posicion = (String) posicionComboBox.getSelectedItem();

                                if (nombre.isEmpty() || posicion.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                // Si se seleccionó una nueva imagen, copiarla al destino
                                if (archivoImagenSeleccionada[0] != null) {
                                    String rutaDestino = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipoSeleccionado.getNombre().toLowerCase().replaceAll("\\s+", "_") + "" + nombre + ".png";
                                    File destino = new File(rutaDestino);
                                    Files.copy(archivoImagenSeleccionada[0].toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    jugador.setImagen(new ImageIcon(destino.getAbsolutePath()));
                                }

                                // Actualizar los datos del jugador
                                jugador.setNombre(nombre);
                                jugador.setEdad(edad);
                                jugador.setPosicion(posicion);

                                JOptionPane.showMessageDialog(null, "Jugador modificado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                actualizarJugadores();
                                hayCambiosNoGuardados = true;
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Edad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al guardar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada y un equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnDetalles.addActionListener(e -> {
            int indiceTemporada = comboBoxTemporada.getSelectedIndex();
            int indiceEquipo = comboBoxEquipo.getSelectedIndex();

            if (indiceTemporada >= 0 && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
                Temporada tempSeleccionada = temporadas.get(indiceTemporada);
                Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);

                ArrayList<Jugador> jugadores = tempSeleccionada.obtenerJugadoresDeEquipo(equipoSeleccionado.getNombre());

                if (jugadores.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No hay jugadores para mostrar detalles.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String[] nombresJugadores = jugadores.stream().map(Jugador::getNombre).toArray(String[]::new);

                String jugadorADetallar = (String) JOptionPane.showInputDialog(
                        this, "Seleccione el jugador para ver detalles:", "Detalles del Jugador",
                        JOptionPane.QUESTION_MESSAGE, null, nombresJugadores, nombresJugadores[0]
                );

                if (jugadorADetallar != null) {
                    Jugador jugador = jugadores.stream()
                            .filter(j -> j.getNombre().equals(jugadorADetallar))
                            .findFirst()
                            .orElse(null);

                    if (jugador != null) {
                        // Crear un panel para mostrar los detalles del jugador
                        JPanel panelDetalles = new JPanel();
                        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));

                        JLabel lblNombre = new JLabel("Nombre: " + jugador.getNombre());
                        JLabel lblEdad = new JLabel("Edad: " + jugador.getEdad());
                        JLabel lblPosicion = new JLabel("Posición: " + jugador.getPosicion());
                        JLabel lblEquipo = new JLabel("Equipo: " + jugador.getEquipo());
                        JLabel lblImagen = new JLabel(jugador.getImagen());

                        panelDetalles.add(lblNombre);
                        panelDetalles.add(lblEdad);
                        panelDetalles.add(lblPosicion);
                        panelDetalles.add(lblEquipo);
                        panelDetalles.add(lblImagen);

                        JOptionPane.showMessageDialog(this, panelDetalles, "Detalles del Jugador", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada y un equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



    }
    
    
    

    private void cargarTemporadas() {
        Temporada temp = new Temporada(0, ""); // Instancia temporal para cargar temporadas
        temporadas = temp.cargarTemporadas();
        
        // Añadir las temporadas al comboBox
        for (Temporada t : temporadas) {
            comboBoxTemporada.addItem(t.getNombre());
        }

        // Establecer la temporada seleccionada por defecto (por ejemplo, la primera)
        if (!temporadas.isEmpty()) {
            comboBoxTemporada.setSelectedIndex(0); // Seleccionar la primera temporada
            actualizarEquipos(); // Actualizar los equipos al seleccionar la temporada por defecto
            actualizarJugadores(); // Actualizar los jugadores de esa temporada y equipo
        }
    }



    private void actualizarEquipos() {
        int indiceTemporada = comboBoxTemporada.getSelectedIndex();
        if (indiceTemporada >= 0 && indiceTemporada < temporadas.size()) {
            Temporada tempSeleccionada = temporadas.get(indiceTemporada);
            equiposActuales = tempSeleccionada.getListEquipos();
            comboBoxEquipo.removeAllItems();
            for (Equipo equipo : equiposActuales) {
                comboBoxEquipo.addItem(equipo.getNombre());
            }
        }
    }
    
    private void actualizarJugadores() {
        int indiceTemporada = comboBoxTemporada.getSelectedIndex();
        int indiceEquipo = comboBoxEquipo.getSelectedIndex();

        if (indiceTemporada >= 0 && indiceTemporada < temporadas.size() && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
            Temporada tempSeleccionada = temporadas.get(indiceTemporada);
            Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);

            System.out.println("Temporada seleccionada: " + tempSeleccionada.getNombre());
            System.out.println("Equipo seleccionado: " + equipoSeleccionado.getNombre());

            // Obtiene los jugadores del equipo seleccionado
            ArrayList<Jugador> jugadores = tempSeleccionada.obtenerJugadoresDeEquipo(equipoSeleccionado.getNombre());

            System.out.println("Número de jugadores obtenidos: " + jugadores.size());

            // Limpia el panel antes de añadir los nuevos jugadores
            panelJugadores.removeAll();
            
            // Establece el layout para que los jugadores se agreguen en una columna
            panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));

            if (jugadores.isEmpty()) {
                System.out.println("No hay jugadores en este equipo.");
                JLabel lblVacio = new JLabel("No hay jugadores en este equipo");
                panelJugadores.add(lblVacio);
            } else {
                for (Jugador jugador : jugadores) {
                    System.out.println("Jugador: " + jugador.getNombre() + " - Equipo: " + jugador.getEquipo());
                    JLabel lblJugador = new JLabel(jugador.getNombre());
                    panelJugadores.add(lblJugador);
                    // Espacio entre los jugadores, si es necesario
                    panelJugadores.add(Box.createVerticalStrut(10));
                }
            }

            // Actualizar la vista
            panelJugadores.revalidate();
            panelJugadores.repaint();
        } else {
            System.out.println("Índices fuera de rango.");
        }
    }
    private void guardarEquipos() {
    	int indiceTemporada = comboBoxTemporada.getSelectedIndex();
        int indiceEquipo = comboBoxEquipo.getSelectedIndex();

        if (indiceTemporada >= 0 && indiceEquipo >= 0 && indiceEquipo < equiposActuales.size()) {
            Temporada tempSeleccionada = temporadas.get(indiceTemporada);
            Equipo equipoSeleccionado = equiposActuales.get(indiceEquipo);

            
            
         // Verificar si el equipo ya tiene 15 jugadores
            if (equipoSeleccionado.getJugadores().size() < 15) {
                JOptionPane.showMessageDialog(null, "hay equipo que no tienen 15 jugadores.agregar más.", "Límite NO alcanzado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            } else {
        Temporada.guardarTemporadas(temporadas); // Llama al método de guardar
        
        gestionAdmin ventanaAdmin = new gestionAdmin();
        ventanaAdmin.setVisible(true);
        dispose();
        //System.exit(0);
            }
    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	 public void windowClosing(WindowEvent e) {
		    if (hayCambiosNoGuardados) {
		    int opcion = JOptionPane.showConfirmDialog(this,(String) "Ha modificado datos. ¿Desea guardarlos?", "Info",  JOptionPane.YES_NO_CANCEL_OPTION);
		    switch (opcion) {
		    case JOptionPane.YES_OPTION:
		    // si pulsa si
		    // guardo los datos en racionales.ser
		    	guardarEquipos();
		    break;
		     case JOptionPane.NO_OPTION:
		    	  gestionAdmin ventanaAdmin = new gestionAdmin();
		          ventanaAdmin.setVisible(true);
		          dispose();
		     break;
		    case JOptionPane.CANCEL_OPTION:
		    case JOptionPane.CLOSED_OPTION:
		    // si pulsa cancelar o X
		    return;
		    }

		    }else {
		    // salgo de la aplicación
		    System.exit(0);
		    }
		    }


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}