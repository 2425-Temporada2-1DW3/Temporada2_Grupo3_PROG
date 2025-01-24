package javainterfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import clases.Equipo;
import clases.Jugador;
import clases.Temporada;
import javax.swing.ImageIcon;

public class TemporadasWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private List<Temporada> temporadas;

    private void cargarTemporadas() {
        // Cargar la lista de equipos desde el archivo (sin asociarlos a las temporadas)
        DefaultListModel<Equipo> defaultListModelEquipos = Equipo.cargarEquipos();
        List<Equipo> listaEquipos = new ArrayList<>();

        // Convertir DefaultListModel<Equipo> a List<Equipo>
        for (int i = 0; i < defaultListModelEquipos.size(); i++) {
            listaEquipos.add(defaultListModelEquipos.getElementAt(i));
        }

        // Crear un DefaultListModel vacío de Jugadores
        DefaultListModel<Jugador> listaJugadores = new DefaultListModel<>();

        // Cargar las temporadas desde el archivo sin agregar equipos automáticamente
        temporadas = Temporada.cargarTemporadasDesdeArchivo(listaEquipos, listaJugadores);
        actualizarListaTemporadas();
    }

    private void actualizarListaTemporadas() {
        listModel.clear();
        for (Temporada temporada : temporadas) {
            listModel.addElement(temporada.toString());
        }
    }
    
    

    private void agregarTemporada(Temporada temporada) {
        // Validación para evitar agregar más de una temporada activa o inactiva
        if ("Activa".equals(temporada.getEstado()) && Temporada.hayTemporadaActiva(temporadas)) {
            JOptionPane.showMessageDialog(null, "Ya existe una temporada activa. No se puede agregar otra temporada activa.");
        } else if ("Inactiva".equals(temporada.getEstado()) && Temporada.hayTemporadaActiva(temporadas)) {
            JOptionPane.showMessageDialog(null, "Ya existe una temporada inactiva. No se puede agregar otra temporada inactiva.");
        } else {
            // Asociar los equipos actuales a la nueva temporada cuando se crea una temporada nueva
            DefaultListModel<Equipo> defaultListModelEquipos = Equipo.cargarEquipos();
            List<Equipo> listaEquipos = new ArrayList<>();

            // Convertir DefaultListModel<Equipo> a List<Equipo>
            for (int i = 0; i < defaultListModelEquipos.size(); i++) {
                listaEquipos.add(defaultListModelEquipos.getElementAt(i));
            }

            // Llamar al método para agregar equipos a la temporada
            agregarEquiposATemporada(listaEquipos, temporada);

            // Agregar la nueva temporada a la lista y actualizar
            temporadas.add(temporada);
            actualizarListaTemporadas();
            Temporada.guardarTemporadasEnArchivo(temporadas);
        }
    }


    private void eliminarTemporada(Temporada temporada) {
        temporadas.remove(temporada);
        actualizarListaTemporadas();
        Temporada.guardarTemporadasEnArchivo(temporadas);
    }

    private void modificarEstadoTemporada(Temporada temporada, String nuevoEstado) {
        int index = temporadas.indexOf(temporada);
        if (index != -1) {
            temporada.setEstado(nuevoEstado);
            actualizarListaTemporadas();
            Temporada.guardarTemporadasEnArchivo(temporadas);
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TemporadasWindow frame = new TemporadasWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public TemporadasWindow() {
        setTitle("Gestión Temporadas - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(TemporadasWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        panel.add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(e -> {
            gestionAdmin ventanaAdmin = new gestionAdmin();
            ventanaAdmin.setVisible(true);
            dispose();
        });
        GridBagConstraints gbc_btnAtras = new GridBagConstraints();
        gbc_btnAtras.gridx = 1;
        gbc_btnAtras.gridy = 0;
        panel_1.add(btnAtras, gbc_btnAtras);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> {
            String temporadaStr = JOptionPane.showInputDialog(
                    "Ingrese la temporada (ej. 2023) y su estado (Activa, Inactiva, Finalizada) (ejemplo: 2023 - Activa):");
            if (temporadaStr != null && !temporadaStr.isEmpty()) {
                String[] parts = temporadaStr.split(" - ");
                if (parts.length == 2) {
                    Temporada nuevaTemporada = new Temporada(Integer.parseInt(parts[0]), parts[1]);
                    agregarTemporada(nuevaTemporada);
                } else {
                    JOptionPane.showMessageDialog(null, "Formato inválido.");
                }
            }
        });
        panel_3.add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {
            Temporada temporadaSeleccionada = getSelectedTemporada();
            if (temporadaSeleccionada != null) {
                eliminarTemporada(temporadaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una temporada para eliminar.");
            }
        });
        panel_3.add(btnEliminar);

        JButton btnModificar = new JButton("Modificar Estado");
        btnModificar.addActionListener(e -> {
            Temporada temporadaSeleccionada = getSelectedTemporada();
            if (temporadaSeleccionada != null) {
                String nuevoEstado = JOptionPane
                        .showInputDialog("Ingresa el nuevo estado (Activa, Inactiva, Finalizada):");
                if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
                    modificarEstadoTemporada(temporadaSeleccionada, nuevoEstado);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una temporada para modificar su estado.");
            }
        });
        panel_3.add(btnModificar);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        panel_4.add(list);

        // Cargar las temporadas desde el archivo al iniciar (sin agregar equipos)
        cargarTemporadas();
    }

    // Obtener la temporada seleccionada en la lista
    private Temporada getSelectedTemporada() {
        String temporadaStr = list.getSelectedValue();
        if (temporadaStr != null) {
            for (Temporada temporada : temporadas) {
                if (temporada.toString().equals(temporadaStr)) {
                    return temporada;
                }
            }
        }
        return null;
    }

    // Método para agregar todos los equipos de la lista de equipos
    private void agregarEquiposATemporada(List<Equipo> equipos, Temporada temporada) {
        for (Equipo equipo : equipos) {
            temporada.agregarEquipo(equipo);
            equipo.agregarTemporada(temporada); // Actualiza también el equipo
        }
    }

}
