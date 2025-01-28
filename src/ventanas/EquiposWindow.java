package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Temporada;
import clases.Equipo;

public class EquiposWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxTemporada;
    private JTextArea textAreaEquipos; // Para mostrar los equipos
    private ArrayList<Temporada> temporadas; // Lista de temporadas cargadas

    /**
     * Launch the application.
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
        btnAtras.addActionListener(e -> dispose());
        panel_6.add(btnAtras);

        // Botón para guardar los cambios
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarEquipos());
        panel_6.add(btnGuardar);

        // Cargar las temporadas al iniciar
        cargarTemporadas();

        // Acción para añadir un equipo
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

            // Crear el panel para ingresar los datos del equipo
            JPanel inputPanel = new JPanel();  // Cambié 'panel' por 'inputPanel' para evitar conflicto de nombre
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // Configurar el layout para que los campos se acomoden verticalmente

            JTextField nombreField = new JTextField(20);
            JTextField anioFundacionField = new JTextField(20);
            JTextField ciudadField = new JTextField(20);

            inputPanel.add(new JLabel("Nombre del equipo:"));
            inputPanel.add(nombreField);
            inputPanel.add(new JLabel("Año de fundación:"));
            inputPanel.add(anioFundacionField);
            inputPanel.add(new JLabel("Ciudad del equipo:"));
            inputPanel.add(ciudadField);

            // Mostrar el panel en un diálogo
            int option = JOptionPane.showConfirmDialog(this, inputPanel, "Ingrese los datos del equipo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Verificar si el usuario presionó "OK"
            if (option == JOptionPane.OK_OPTION) {
                String nuevoEquipoNombre = nombreField.getText().trim();
                String anioFundacion = anioFundacionField.getText().trim();
                String ciudad = ciudadField.getText().trim();

                // Validación de los campos
                if (nuevoEquipoNombre.isEmpty() || anioFundacion.isEmpty() || ciudad.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ImageIcon logo = new ImageIcon("C:/Users/ik_1DW3A/Downloads/fc_barcelona.png");

                // Crear el nuevo equipo con los datos proporcionados
                Equipo nuevoEquipo = new Equipo(nuevoEquipoNombre, anioFundacion, ciudad, new ArrayList<>(), 0, logo);
                temporadaSeleccionada.agregarEquipo(nuevoEquipo); // Agrega un solo equipo
                mostrarEquipos();
                JOptionPane.showMessageDialog(this, "Equipo añadido exitosamente.");
            }
        });

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
                    temporadaSeleccionada.eliminarEquipo(equipoEncontrado); // Llamar al método de eliminación
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

    /**
     * Guardar los equipos en el archivo o base de datos.
     */
    private void guardarEquipos() {
        Temporada.guardarTemporadas(temporadas); // Llama al método de guardar
    }
}
