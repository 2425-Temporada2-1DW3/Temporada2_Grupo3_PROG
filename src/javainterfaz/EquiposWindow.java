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
import javax.swing.DefaultListModel;

public class EquiposWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxTemporada;
    private DefaultListModel<Equipo> listaEquipos;
    private JButton btnAnadir, btnEliminar, btnAtras, btnGuardar;
    private JList<Equipo> equiposList;
    private List<Temporada> temporadas;

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

    public EquiposWindow() {
        System.out.println("[INFO] Inicializando EquiposWindow...");

        // Cargar lista de equipos
        listaEquipos = Equipo.cargarEquipos();
        System.out.println("[DEBUG] Equipos cargados: " + listaEquipos);

        // Cargar lista de temporadas
        DefaultListModel<Jugador> listaJugadores = new DefaultListModel<>();
        temporadas = Temporada.cargarTemporadasDesdeArchivo(new ArrayList<>(), listaJugadores);
        System.out.println("[DEBUG] Temporadas cargadas:");

        for (Temporada temp : temporadas) {
            System.out.println("- Temporada " + temp.getNumero() + " (" + temp.getEstado() + ")");
            if (temp.getEquipos() != null && !temp.getEquipos().isEmpty()) {
                for (Equipo equipo : temp.getEquipos()) {
                    System.out.println("  * Equipo: " + equipo.getNombre() + ", Ciudad: " + equipo.getCiudad());
                }
            } else {
                System.out.println("  * No hay equipos en esta temporada.");
            }
        }

        setTitle("Gestión Equipos - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 590, 402);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(EquiposWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.WEST);

        btnAnadir = new JButton("Añadir");
        btnAnadir.addActionListener(this);
        panel_3.add(btnAnadir);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        panel_3.add(btnEliminar);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.EAST);

        JLabel lblNewLabel_1 = new JLabel("Temporada");
        panel_4.add(lblNewLabel_1);

        comboBoxTemporada = new JComboBox<>();
        for (Temporada temp : temporadas) {
            comboBoxTemporada.addItem("Temporada " + temp.getNumero());
        }
        comboBoxTemporada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEquiposPorTemporada();
            }
        });
        panel_4.add(comboBoxTemporada);

        equiposList = new JList<>(listaEquipos);
        JScrollPane scrollPane = new JScrollPane(equiposList);
        panel_1.add(scrollPane, BorderLayout.CENTER);

        JPanel panel_5 = new JPanel();
        contentPane.add(panel_5, BorderLayout.SOUTH);

        btnAtras = new JButton("Atras");
        btnAtras.addActionListener(this);
        panel_5.add(btnAtras);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        panel_5.add(btnGuardar);

        // Actualizar equipos al inicio con la temporada seleccionada
        actualizarEquiposPorTemporada(); // Esto asegurará que se carguen los equipos de la temporada seleccionada

        System.out.println("[INFO] EquiposWindow inicializado correctamente.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAnadir) {
            System.out.println("[INFO] Botón Añadir presionado.");
            
            // Abrir ventana para agregar equipo
            añadirEquipo ventanaEquipo = new añadirEquipo();
            ventanaEquipo.setVisible(true);

            // Espera a que el usuario cree un nuevo equipo y lo devuelva
            Equipo nuevoEquipo = ventanaEquipo.obtenerEquipo(); // Obtener el equipo de la ventana

            if (nuevoEquipo != null) {
                // Obtener la temporada seleccionada
                String temporadaSeleccionada = (String) comboBoxTemporada.getSelectedItem();
                int numeroTemporada = Integer.parseInt(temporadaSeleccionada.split(" ")[1]); // Extraer el número de temporada
                Temporada temporada = obtenerTemporadaPorNumero(numeroTemporada);

                // Si la temporada existe, agregar el equipo solo a esa temporada
                if (temporada != null) {
                    // Agregar el equipo solo a la temporada seleccionada
                    temporada.agregarEquipo(nuevoEquipo);  
                    
                    // Actualizar la lista de equipos para reflejar el cambio solo para la temporada seleccionada
                    actualizarEquiposPorTemporada();
                    System.out.println("[INFO] Equipo añadido a la temporada " + numeroTemporada);
                } else {
                    System.out.println("[ERROR] La temporada seleccionada no existe.");
                }
            }
        } else if (e.getSource() == btnGuardar) {
            System.out.println("[INFO] Botón Guardar presionado.");
            System.out.println("[DEBUG] Guardando equipos: " + listaEquipos);
            Equipo.guardarEquipos(listaEquipos); // Asegúrate de que este método esté correctamente implementado
            System.out.println("[INFO] Equipos guardados correctamente.");
            imprimirEquiposPorTemporada(); // Llamar al método para imprimir los equipos
        } else if (e.getSource() == btnAtras) {
            System.out.println("[INFO] Botón Atrás presionado. Cerrando ventana...");
            this.setVisible(false);
        }
    }

    private void actualizarEquiposPorTemporada() {
        // Obtener temporada seleccionada
        String temporadaSeleccionada = (String) comboBoxTemporada.getSelectedItem();
        int numeroTemporada = Integer.parseInt(temporadaSeleccionada.split(" ")[1]); // Extraer el número de temporada
        Temporada temporada = obtenerTemporadaPorNumero(numeroTemporada);
        
        if (temporada != null) {
            // Limpiar la lista de equipos
            listaEquipos.clear();
            
            // Agregar los equipos de la temporada seleccionada
            List<Equipo> equiposTemporada = temporada.getEquipos();
            
            // Verificar que la lista no esté vacía
            if (equiposTemporada != null && !equiposTemporada.isEmpty()) {
                for (Equipo equipo : equiposTemporada) {
                    listaEquipos.addElement(equipo);
                }
            } else {
                System.out.println("[INFO] No hay equipos asignados para la temporada " + numeroTemporada);
            }
        } else {
            System.out.println("[ERROR] La temporada no se encontró: " + numeroTemporada);
        }
    }

    private void imprimirEquiposPorTemporada() {
        System.out.println("[INFO] Listado de equipos por temporada:");
        for (Temporada temp : temporadas) {
            System.out.println("- Temporada " + temp.getNumero() + " (" + temp.getEstado() + ")");
            if (temp.getEquipos() != null && !temp.getEquipos().isEmpty()) {
                for (Equipo equipo : temp.getEquipos()) {
                    System.out.println("  * Equipo: " + equipo.getNombre() + ", Ciudad: " + equipo.getCiudad());
                }
            } else {
                System.out.println("  * No hay equipos en esta temporada.");
            }
        }
    }

    private Temporada obtenerTemporadaPorNumero(int numero) {
        for (Temporada temp : temporadas) {
            if (temp.getNumero() == numero) {
                return temp;
            }
        }
        return null;
    }
}
