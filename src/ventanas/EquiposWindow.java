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
    private ArrayList<Temporada> temporadas; // Lista de temporadas cargadass

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

        // Panel inferior con botón de retroceso
        JPanel panel_6 = new JPanel();
        panel_1.add(panel_6, BorderLayout.SOUTH);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(e -> dispose());
        panel_6.add(btnAtras);

        // Cargar las temporadas al iniciar
        cargarTemporadas();
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
}
