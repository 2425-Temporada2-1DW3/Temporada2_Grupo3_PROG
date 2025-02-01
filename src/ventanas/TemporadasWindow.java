package ventanas;

import clases.Temporada;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TemporadasWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ArrayList<Temporada> temporadas;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TemporadasWindow frame = new TemporadasWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TemporadasWindow() {
        setTitle("Gestion Temporadas - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Inicializar la lista de temporadas
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        actualizarListaTemporadas();
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(TemporadasWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0};
        gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        GridBagConstraints gbc_btnAtras = new GridBagConstraints();
        gbc_btnAtras.gridwidth = 2;
        gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
        gbc_btnAtras.gridx = 6;
        gbc_btnAtras.gridy = 0;
        panel_1.add(btnAtras, gbc_btnAtras);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Agregar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(TemporadasWindow.this, "Ingrese el nombre de la temporada:");
                if (nombre != null && !nombre.isEmpty()) {
                    int id = temporadas.size() + 1;
                    Temporada nuevaTemporada = new Temporada(id, nombre, "Activa");
                    temporadas.add(nuevaTemporada);
                    actualizarListaTemporadas();
                }
            }
        });
        panel_3.add(btnNewButton);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
                    String nuevoNombre = JOptionPane.showInputDialog(TemporadasWindow.this, "Ingrese el nuevo nombre:", temporadaSeleccionada.getNombre());
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        temporadaSeleccionada.setNombre(nuevoNombre);
                        actualizarListaTemporadas();
                    }
                } else {
                    JOptionPane.showMessageDialog(TemporadasWindow.this, "Seleccione una temporada para modificar.");
                }
            }
        });
        panel_3.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    temporadas.remove(selectedIndex);
                    actualizarListaTemporadas();
                } else {
                    JOptionPane.showMessageDialog(TemporadasWindow.this, "Seleccione una temporada para eliminar.");
                }
            }
        });
        panel_3.add(btnEliminar);

        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Temporada temporadaSeleccionada = temporadas.get(selectedIndex);
                    JOptionPane.showMessageDialog(TemporadasWindow.this,
                        "Nombre: " + temporadaSeleccionada.getNombre() + "\n" +
                        "Estado: " + temporadaSeleccionada.getEstado(),
                        "Detalles de la Temporada",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TemporadasWindow.this, "Seleccione una temporada para ver detalles.");
                }
            }
        });
        panel_3.add(btnVerDetalles);

        JPanel panel_4 = new JPanel();
        panel_2.add(panel_4, BorderLayout.CENTER);
        panel_4.add(new JScrollPane(list));
    }

    private void actualizarListaTemporadas() {
        listModel.clear();
        Temporada temp = new Temporada(0, ""); // Instancia temporal para cargar temporadas
        temporadas = temp.cargarTemporadas();

        for (Temporada t : temporadas) {
        	listModel.addElement("" +t.getNombre() + " Estado: " + t.getEstado());
        }
    }
}