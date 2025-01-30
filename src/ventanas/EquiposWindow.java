package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.Temporada;
import clases.Equipo;
import clases.Jugador;

public class EquiposWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxTemporada;
    private JTextArea textAreaEquipos; // Para mostrar los equipos
    private ArrayList<Temporada> temporadas; // Lista de temporadas cargadas
    private final String RUTA_IMAGENES = "C:\\xampp\\htdocs\\Temporada2_Grupo3_LM\\img\\escudos\\";

    /**
     * Launch the applicationa
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EquiposWindow frame = new EquiposWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public EquiposWindow() {
        setTitle("Gestion Equipos - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Panel superior con el logo
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(EquiposWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        panel.add(lblNewLabel);

        // Panel principal
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        // Panel superior con botones y comboBox
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.WEST);

        JButton btnAnadir = new JButton("Añadir");
        panel_3.add(btnAnadir);
     // Dentro del constructor, en el panel donde están los botones Añadir, Eliminar, Detalles
        JButton btnEditar = new JButton("Editar");
        panel_3.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        panel_3.add(btnEliminar);

        JButton btnDetalles = new JButton("Detalles");
        panel_3.add(btnDetalles);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.EAST);

        JLabel lblNewLabel_1 = new JLabel("Temporada");
        panel_4.add(lblNewLabel_1);

        comboBoxTemporada = new JComboBox<>();
        comboBoxTemporada.addActionListener(e -> mostrarEquipos());
        panel_4.add(comboBoxTemporada);

        // Panel central con el área de texto para mostrar equipos
        textAreaEquipos = new JTextArea();
        textAreaEquipos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaEquipos);
        panel_1.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones de retroceso y guardar
        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.SOUTH);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(e -> {
        	 gestionAdmin ventanaAdmin = new gestionAdmin();  // Ventana gestión de equipos
             ventanaAdmin.setVisible(true);  // Mostrar la ventana
             dispose();  // Cerrar la ventana
        });
        panel_6.add(btnAtras);

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarEquipos());
        panel_6.add(btnGuardar);

        // Cargar las temporadas al iniciar
        cargarTemporadas();

        // Acción para añadir un equipo
     // Acción para añadir un equipo (modificado para incluir imagen)
        btnAnadir.addActionListener(e -> {
            int selectedIndex = comboBoxTemporada.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
            ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();

            if (equipos.size() >= 6) {
                JOptionPane.showMessageDialog(this, "Ya hay 6 equipos en esta temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            JTextField nombreField = new JTextField(20);
            JTextField anioFundacionField = new JTextField(20);
            JTextField ciudadField = new JTextField(20);
            JLabel lblPreview = new JLabel();
            lblPreview.setPreferredSize(new Dimension(100, 100));
            final File[] imagenSeleccionada = {null};
            
            JButton btnSeleccionarLogo = new JButton("Seleccionar Logo");
            btnSeleccionarLogo.addActionListener(ev -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "gif"));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imagenSeleccionada[0] = fileChooser.getSelectedFile();
                    lblPreview.setIcon(new ImageIcon(new ImageIcon(imagenSeleccionada[0].getPath()).getImage()
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                }
            });
            
            inputPanel.add(new JLabel("Nombre del equipo:"));
            inputPanel.add(nombreField);
            inputPanel.add(new JLabel("Año de fundación:"));
            inputPanel.add(anioFundacionField);
            inputPanel.add(new JLabel("Ciudad del equipo:"));
            inputPanel.add(ciudadField);
            inputPanel.add(new JLabel("Logo:"));
            inputPanel.add(btnSeleccionarLogo);
            inputPanel.add(lblPreview);
            
            int option = JOptionPane.showConfirmDialog(
                    this, inputPanel, "Nuevo Equipo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (option == JOptionPane.OK_OPTION) {
                String nombre = nombreField.getText().trim();
                String anio = anioFundacionField.getText().trim();
                String ciudad = ciudadField.getText().trim();
                if (nombre.isEmpty() || anio.isEmpty() || ciudad.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Crear una carpeta con el nombre del equipo
                String carpetaEquipo = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + nombre.replaceAll("\\s+", "_");
                File directorio = new File(carpetaEquipo);
                if (!directorio.exists()) {
                    if (!directorio.mkdirs()) {
                        JOptionPane.showMessageDialog(this, "Error al crear la carpeta del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                // Ruta de la imagen logo dentro de la carpeta del equipo
                String rutaLogo = carpetaEquipo + "/" + nombre.replaceAll("\\s+", "_") + ".png";
                
                // Copiar la imagen seleccionada a la carpeta del equipo
                if (imagenSeleccionada[0] != null) {
                    try {
                        Files.copy(imagenSeleccionada[0].toPath(), new File(rutaLogo).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Error al copiar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                // Crear el nuevo equipo
                Equipo nuevoEquipo = new Equipo(nombre, anio, ciudad, new ArrayList<>(), 0, new ImageIcon(rutaLogo));
                
                // Crear jugadores y asignarlos a los equipos con posiciones específicas
                for (Equipo equipo : new Equipo[]{nuevoEquipo}) {
                    int jugadorNum = 1; // Inicia en 1 para cada equipo

                    // Asignar 4 delanteros
                    for (int i = 0; i < 4; i++) {
                        String nombreJugador = "Jugador_" + jugadorNum;
                        String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                        
                        // Verificar si existe la imagen del jugador
                        if (verificarExistenciaImagen(rutaImagen)) {
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador delantero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Delantero", equipo, imagenJugador);
                            equipo.getJugadores().add(delantero);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } else {
                            // Si no existe la imagen, coger la imagen por defecto y copiarla
                            String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                            if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                                try {
                                    // Copiar la imagen predeterminada al equipo
                                    Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                               new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                                    Jugador delantero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Delantero", equipo, imagenJugador);
                                    equipo.getJugadores().add(delantero);
                                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                                } catch (IOException ex) {
                                    System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                                }
                            } else {
                                System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                            }
                        }
                        jugadorNum++;
                    }

                    // Asignar 4 defensas
                    for (int i = 0; i < 4; i++) {
                        String nombreJugador = "Jugador_" + jugadorNum;
                        String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                        
                        // Verificar si existe la imagen del jugador
                        if (verificarExistenciaImagen(rutaImagen)) {
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador defensa = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Defensa", equipo, imagenJugador);
                            equipo.getJugadores().add(defensa);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } else {
                            // Si no existe la imagen, coger la imagen por defecto y copiarla
                            String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                            if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                                try {
                                    // Copiar la imagen predeterminada al equipo
                                    Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                               new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                                    Jugador defensa = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Defensa", equipo, imagenJugador);
                                    equipo.getJugadores().add(defensa);
                                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                                } catch (IOException ex) {
                                    System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                                }
                            } else {
                                System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                            }
                        }
                        jugadorNum++;
                    }

                    // Asignar 4 mediocampistas
                    for (int i = 0; i < 4; i++) {
                        String nombreJugador = "Jugador_" + jugadorNum;
                        String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                        
                        // Verificar si existe la imagen del jugador
                        if (verificarExistenciaImagen(rutaImagen)) {
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador mediocampista = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Mediocampista", equipo, imagenJugador);
                            equipo.getJugadores().add(mediocampista);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } else {
                            // Si no existe la imagen, coger la imagen por defecto y copiarla
                            String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                            if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                                try {
                                    // Copiar la imagen predeterminada al equipo
                                    Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                               new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                                    Jugador mediocampista = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Mediocampista", equipo, imagenJugador);
                                    equipo.getJugadores().add(mediocampista);
                                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                                } catch (IOException ex) {
                                    System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                                }
                            } else {
                                System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                            }
                        }
                        jugadorNum++;
                    }

                    // Asignar 3 porteros
                    for (int i = 0; i < 3; i++) {
                        String nombreJugador = "Jugador_" + jugadorNum;
                        String rutaImagen = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().toLowerCase().replaceAll("\\s+", "_") + "/Jugador_" + jugadorNum + ".png";
                        
                        // Verificar si existe la imagen del jugador
                        if (verificarExistenciaImagen(rutaImagen)) {
                            ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                            Jugador portero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Portero", equipo, imagenJugador);
                            equipo.getJugadores().add(portero);
                            System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                            System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                        } else {
                            // Si no existe la imagen, coger la imagen por defecto y copiarla
                            String rutaImagenPredeterminada = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/jugadores/Jugador_" + jugadorNum + ".png";
                            if (verificarExistenciaImagen(rutaImagenPredeterminada)) {
                                try {
                                    // Copiar la imagen predeterminada al equipo
                                    Files.copy(new File(rutaImagenPredeterminada).toPath(),
                                               new File(rutaImagen).toPath(), StandardCopyOption.REPLACE_EXISTING);
                                    ImageIcon imagenJugador = new ImageIcon(rutaImagen);
                                    Jugador portero = new Jugador(nombreJugador, 25 + (jugadorNum % 10), "Portero", equipo, imagenJugador);
                                    equipo.getJugadores().add(portero);
                                    System.out.println("Creado: " + nombreJugador + " en " + equipo.getNombre());
                                    System.out.println("Ruta: " + nombreJugador + " en " + rutaImagen);
                                } catch (IOException ex) {
                                    System.out.println("¡Error al copiar la imagen predeterminada de " + nombreJugador + "!");
                                }
                            } else {
                                System.out.println("¡Advertencia! La imagen de " + nombreJugador + " no existe en la ruta: " + rutaImagenPredeterminada);
                            }
                        }
                        jugadorNum++;
                    }
                }
                temporadaSeleccionada.agregarEquipo(nuevoEquipo);
                mostrarEquipos();
                JOptionPane.showMessageDialog(this, "Equipo añadido exitosamente!");
            }
        });



        
        btnEditar.addActionListener(e -> {
            int selectedIndex = comboBoxTemporada.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
            ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();

            if (equipos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay equipos en esta temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] nombresEquipos = equipos.stream().map(Equipo::getNombre).toArray(String[]::new);
            String equipoSeleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un equipo para editar:",
                "Editar Equipo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresEquipos,
                nombresEquipos[0]
            );

            if (equipoSeleccionado != null) {
                Equipo equipoAEditar = equipos.stream()
                    .filter(equipo -> equipo.getNombre().equals(equipoSeleccionado))
                    .findFirst()
                    .orElse(null);

                if (equipoAEditar != null) {
                    JPanel editPanel = new JPanel();
                    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

                    JTextField anioField = new JTextField(equipoAEditar.getAnoFundacion());
                    JTextField ciudadField = new JTextField(equipoAEditar.getCiudad());
                    JLabel lblLogo = new JLabel();
                    lblLogo.setIcon(new ImageIcon(equipoAEditar.getLogo().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

                    JButton btnCambiarLogo = new JButton("Cambiar Logo");
                    btnCambiarLogo.addActionListener(ev -> {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png", "gif"));
                        int result = fileChooser.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File archivoSeleccionado = fileChooser.getSelectedFile();
                            // Eliminar la imagen anterior si existe
                            File archivoAnterior = new File(RUTA_IMAGENES + equipoAEditar.getNombre().replaceAll("\\s+", "_") + ".png");
                            if (archivoAnterior.exists()) {
                                archivoAnterior.delete(); // Eliminar la imagen anterior
                            }

                            // Cambiar el nombre del archivo al nombre del equipo
                            String rutaLogo = RUTA_IMAGENES + equipoAEditar.getNombre().replaceAll("\\s+", "_") + ".png";
                            ImageIcon nuevoLogo = new ImageIcon(archivoSeleccionado.getPath());
                            lblLogo.setIcon(new ImageIcon(nuevoLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                            equipoAEditar.setLogo(nuevoLogo); // Actualizamos el logo en el objeto

                            try {
                                // Guardamos la nueva imagen con el nombre del equipo
                                Files.copy(archivoSeleccionado.toPath(), new File(rutaLogo).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(this, "Error al copiar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    editPanel.add(new JLabel("Año Fundación:"));
                    editPanel.add(anioField);
                    editPanel.add(new JLabel("Ciudad:"));
                    editPanel.add(ciudadField);
                    editPanel.add(new JLabel("Logo:"));
                    editPanel.add(lblLogo);
                    editPanel.add(btnCambiarLogo);

                    int option = JOptionPane.showConfirmDialog(
                        this, 
                        editPanel, 
                        "Editar Equipo", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.PLAIN_MESSAGE
                    );

                    if (option == JOptionPane.OK_OPTION) {
                        if (anioField.getText().trim().isEmpty() || ciudadField.getText().trim().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.", "Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        equipoAEditar.setAnoFundacion(anioField.getText().trim());
                        equipoAEditar.setCiudad(ciudadField.getText().trim());
                        mostrarEquipos();
                        JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
                    }
                }
            }
        });
        // Acción para eliminar un equipo
     // Acción para eliminar un equipo
        btnEliminar.addActionListener(e -> {
            int selectedIndex = comboBoxTemporada.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
            ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();

            if (equipos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay equipos en esta temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Mostrar los nombres de los equipos en un array
            String[] nombresEquipos = equipos.stream().map(Equipo::getNombre).toArray(String[]::new);
            String equipoAEliminar = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un equipo para eliminar:",
                "Eliminar Equipo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresEquipos,
                nombresEquipos[0]
            );

            if (equipoAEliminar != null) {
                // Buscar el equipo por nombre y eliminarlo
                Equipo equipoEncontrado = equipos.stream()
                    .filter(equipo -> equipo.getNombre().equals(equipoAEliminar))
                    .findFirst()
                    .orElse(null);

                if (equipoEncontrado != null) {
                    // Eliminar la carpeta del equipo
                    eliminarCarpetaEquipo(equipoEncontrado);

                    // Eliminar el equipo de la temporada
                    temporadaSeleccionada.eliminarEquipo(equipoEncontrado);
                    mostrarEquipos();
                    JOptionPane.showMessageDialog(this, "Equipo eliminado exitosamente.");
                }
            }
        
        });




        // Acción para mostrar los detalles de un equipo
        btnDetalles.addActionListener(e -> {
            int selectedIndex = comboBoxTemporada.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
            ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();

            if (equipos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay equipos en esta temporada.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Mostrar los nombres de los equipos en un array
            String[] nombresEquipos = equipos.stream().map(Equipo::getNombre).toArray(String[]::new);
            String equipoSeleccionado = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione un equipo para ver los detalles:",
                "Detalles del Equipo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nombresEquipos,
                nombresEquipos[0]
            );

            if (equipoSeleccionado != null) {
                // Buscar el equipo por nombre
                Equipo equipoEncontrado = equipos.stream()
                    .filter(equipo -> equipo.getNombre().equals(equipoSeleccionado))
                    .findFirst()
                    .orElse(null);

                if (equipoEncontrado != null) {
                    // Crear un panel con la información del equipo
                    JPanel detallesPanel = new JPanel();
                    detallesPanel.setLayout(new BoxLayout(detallesPanel, BoxLayout.Y_AXIS));

                    JLabel lblNombre = new JLabel("Nombre: " + equipoEncontrado.getNombre());
                    JLabel lblAnioFundacion = new JLabel("Año de Fundación: " + equipoEncontrado.getAnoFundacion());
                    JLabel lblCiudad = new JLabel("Ciudad: " + equipoEncontrado.getCiudad());

                    // Mostrar el logo del equipo
                    JLabel lblLogo = new JLabel(new ImageIcon(equipoEncontrado.getLogo().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

                    detallesPanel.add(lblNombre);
                    detallesPanel.add(lblAnioFundacion);
                    detallesPanel.add(lblCiudad);
                    detallesPanel.add(lblLogo);

                    // Mostrar los detalles en un diálogo
                    JOptionPane.showMessageDialog(this, detallesPanel, "Detalles del Equipo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    
    
 // Método para eliminar la carpeta del equipo (y su contenido)
    private void eliminarCarpetaEquipo(Equipo equipo) {
        String carpetaEquipo = "C:/xampp/htdocs/Temporada2_Grupo3_LM/img/" + equipo.getNombre().replaceAll("\\s+", "_");

        File directorio = new File(carpetaEquipo);
        if (directorio.exists() && directorio.isDirectory()) {
            try {
                // Eliminar todos los archivos dentro de la carpeta
                File[] archivos = directorio.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        archivo.delete(); // Eliminar archivo
                    }
                }
                
                // Eliminar la carpeta vacía
                directorio.delete();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la carpeta del equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Cargar las temporadas desde el archivo y rellenar el comboBox.
     */
    private void cargarTemporadas() {
        Temporada temp = new Temporada(0, ""); // Instancia temporal para cargar temporadas
        temporadas = temp.cargarTemporadas();

        if (temporadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay temporadas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Rellenar el comboBox con los nombres de las temporadas
        for (Temporada temporada : temporadas) {
            comboBoxTemporada.addItem(temporada.getNombre());
        }
    }

    /**
     * Mostrar los equipos de la temporada seleccionada.
     */
    private void mostrarEquipos() {
        int selectedIndex = comboBoxTemporada.getSelectedIndex();
        if (selectedIndex == -1) return; // No hay selección

        Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
        ArrayList<Equipo> equipos = temporadaSeleccionada.getListEquipos();

        // Mostrar los equipos en el área de texto
        textAreaEquipos.setText("");
        for (Equipo equipo : equipos) {
            textAreaEquipos.append(equipo.getNombre() + "\n");
        }
    }
    
    // Método para verificar si la imagen del jugador existe en el sistema de archivos
    public static boolean verificarExistenciaImagen(String rutaImagen) {
        File archivo = new File(rutaImagen);
        return archivo.exists() && archivo.isFile();
    }

    /**
     * Guardar los equipos en el archivo o base de datos.
     */
    private void guardarEquipos() {
        Temporada.guardarTemporadas(temporadas); // Llama al método de guardar
    }
}
