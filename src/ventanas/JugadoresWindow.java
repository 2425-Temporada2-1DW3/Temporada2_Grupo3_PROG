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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JugadoresWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxTemporada;
    private JComboBox<String> comboBoxEquipo;
    private JPanel panelJugadores;
    private ArrayList<Temporada> temporadas;
    private ArrayList<Equipo> equiposActuales;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
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

        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(e -> dispose());
        GridBagConstraints gbc_btnAtras = new GridBagConstraints();
        gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
        gbc_btnAtras.gridx = 6;
        gbc_btnAtras.gridy = 0;
        panel_6.add(btnAtras, gbc_btnAtras);

        JPanel panel_8 = new JPanel();
        GridBagConstraints gbc_panel_8 = new GridBagConstraints();
        gbc_panel_8.gridx = 7;
        gbc_panel_8.gridy = 0;
        panel_6.add(panel_8, gbc_panel_8);

        JButton btnAgregar = new JButton("Agregar");
        panel_8.add(btnAgregar);
        JButton btnModificar = new JButton("Modificar");
        panel_8.add(btnModificar);
        JButton btnEliminar = new JButton("Eliminar");
        panel_8.add(btnEliminar);

        cargarTemporadas();
        actualizarEquipos();

        comboBoxTemporada.addActionListener(e -> actualizarEquipos());
        comboBoxEquipo.addActionListener(e -> actualizarJugadores());
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

}