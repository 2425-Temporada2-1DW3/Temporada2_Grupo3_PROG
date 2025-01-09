package javainterfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class añadirJugador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField leerNombre;
    private JTextField leerFecha;
    private JTextField leerEdad;
    private JTextField leerNacionalidad;
    private JComboBox<String> comboEquipos;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                añadirJugador frame = new añadirJugador();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public añadirJugador() {
        setTitle("Añadir Jugador - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500); // Ajustar tamaño de ventana
        setLocationRelativeTo(null); // Centrar ventana

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Logo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblLogo = new JLabel(new ImageIcon(añadirJugador.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Nombre
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblNombre = new JLabel("Nombre:");
        contentPane.add(lblNombre, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerNombre = new JTextField();
        leerNombre.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerNombre, gbc);

        // Fecha de fundación
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblFechaFun = new JLabel("Posición:");
        contentPane.add(lblFechaFun, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerFecha = new JTextField();
        leerFecha.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerFecha, gbc);

        // Equipo
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblEquipos = new JLabel("Equipo:");
        contentPane.add(lblEquipos, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboEquipos = new JComboBox<>();
        contentPane.add(comboEquipos, gbc);

        // Edad
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblEdad = new JLabel("Edad:");
        contentPane.add(lblEdad, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerEdad = new JTextField();
        leerEdad.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerEdad, gbc);

        // Nacionalidad
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblNacionalidad = new JLabel("Nacionalidad:");
        contentPane.add(lblNacionalidad, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerNacionalidad = new JTextField();
        leerNacionalidad.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerNacionalidad, gbc);

        // Botón "Agregar"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnAgregar = new JButton("Agregar");
        contentPane.add(btnAgregar, gbc);

        // Botón "Atrás"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnVolver = new JButton("Atrás");
        contentPane.add(btnVolver, gbc);
    }
}
