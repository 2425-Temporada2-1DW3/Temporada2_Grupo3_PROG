package javainterfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import clases.Equipo;
import clases.Jugador;
import clases.Temporada;
import java.util.ArrayList;
import java.util.List;

public class añadirEquipo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField leerNombre;
    private JTextField leerFecha;
    private JTextField leerCiudad;
    private JComboBox<Temporada> comboTemporada;
    private DefaultListModel<Equipo> listaEquipos;
    private List<Temporada> temporadas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                añadirEquipo frame = new añadirEquipo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public añadirEquipo() {
        setTitle("Añadir Equipo - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centrar la ventana

        // Inicializar la lista de equipos
        listaEquipos = Equipo.cargarEquipos();

        // Cargar las temporadas desde archivo (asegurando que sean activas o por defecto)
        temporadas = Temporada.cargarTemporadasDesdeArchivo(new ArrayList<Equipo>(), new DefaultListModel<Jugador>());

        // Panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre los elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Logo
        JLabel lblLogo = new JLabel(new ImageIcon(añadirEquipo.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblNombre, gbc);

        leerNombre = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(leerNombre, gbc);

        // Fecha de fundación
        JLabel lblFechaFun = new JLabel("Fecha de fundación:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblFechaFun, gbc);

        leerFecha = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(leerFecha, gbc);

        // Ciudad
        JLabel lblCiudad = new JLabel("Ciudad:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblCiudad, gbc);

        leerCiudad = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(leerCiudad, gbc);

        // Combo de temporadas
        JLabel lblTemporada = new JLabel("Seleccionar Temporada:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        contentPane.add(lblTemporada, gbc);

        comboTemporada = new JComboBox<>();
        for (Temporada temporada : temporadas) {
            comboTemporada.addItem(temporada);
        }

        gbc.gridx = 1;
        gbc.gridy = 4;
        contentPane.add(comboTemporada, gbc);

        // Botón "Agregar"
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = leerNombre.getText();
                String fecha = leerFecha.getText();
                String ciudad = leerCiudad.getText();

                System.out.println("[DEBUG] Datos ingresados - Nombre: " + nombre + ", Fecha: " + fecha + ", Ciudad: " + ciudad);

                if (nombre.isEmpty() || fecha.isEmpty() || ciudad.isEmpty()) {
                    JOptionPane.showMessageDialog(añadirEquipo.this, "Por favor complete todos los campos.");
                    System.out.println("[ERROR] Algún campo está vacío.");
                } else {
                    // Crear un nuevo equipo solo con los datos del equipo, sin agregarlo a las temporadas
                    Equipo nuevoEquipo = new Equipo(nombre, fecha, ciudad, new ArrayList<>(), new ArrayList<>()); // No agregar a las temporadas aquí

                    Temporada temporadaSeleccionada = (Temporada) comboTemporada.getSelectedItem();

                    if (temporadaSeleccionada != null) {
                        System.out.println("[DEBUG] Temporada seleccionada: " + temporadaSeleccionada.getNumero());

                        // Agregar manualmente solo a la temporada seleccionada
                        temporadaSeleccionada.agregarEquipo(nuevoEquipo);
                        listaEquipos.addElement(nuevoEquipo); // Añadir el nuevo equipo a la lista global

                        // Guardar la lista de equipos
                        Equipo.guardarEquipos(listaEquipos);
                        System.out.println("[INFO] Equipo agregado a la temporada y lista: " + nuevoEquipo);

                        // Mensaje de éxito
                        JOptionPane.showMessageDialog(añadirEquipo.this, "Equipo agregado correctamente.");
                        dispose(); // Cerrar la ventana actual
                    } else {
                        System.out.println("[ERROR] No se ha seleccionado ninguna temporada.");
                    }
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 5;
        contentPane.add(btnAgregar, gbc);

        // Botón "Cancelar"
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana sin hacer nada
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 6;
        contentPane.add(btnCancelar, gbc);
    }

    public Equipo obtenerEquipo() {
        String nombre = leerNombre.getText();
        String fecha = leerFecha.getText();
        String ciudad = leerCiudad.getText();
        
        if (!nombre.isEmpty() && !fecha.isEmpty() && !ciudad.isEmpty()) {
            return new Equipo(nombre, fecha, ciudad, new ArrayList<>(), new ArrayList<>());
        }
        return null; // Si los campos están vacíos, devuelve null
    }
}
