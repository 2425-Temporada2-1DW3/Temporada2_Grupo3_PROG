package javainterfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class añadirEquipo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField leerNombre;
    private JTextField leerFecha;
    private JTextField leerPais;

    /**a
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public añadirEquipo() {
        setTitle("Añadir Equipo - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

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

        JLabel lblLogo = new JLabel(new ImageIcon(añadirEquipo.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Nombre
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblNombre = new JLabel("Nombre:");
        contentPane.add(lblNombre, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Permitir que el campo se expanda
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerNombre = new JTextField();
        leerNombre.setPreferredSize(new Dimension(200, 30)); // Tamaño por defecto más grande
        contentPane.add(leerNombre, gbc);

        // Fecha de fundación
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblFechaFun = new JLabel("Fecha de fundación:");
        contentPane.add(lblFechaFun, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerFecha = new JTextField();
        leerFecha.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerFecha, gbc);

        // País
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;

        JLabel lblPais = new JLabel("País:");
        contentPane.add(lblPais, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        leerPais = new JTextField();
        leerPais.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerPais, gbc);

        // Botón "Agregar"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnAgregar = new JButton("Agregar");
        contentPane.add(btnAgregar, gbc);

        // Botón "Atrás"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnVolver = new JButton("Atrás");
        contentPane.add(btnVolver, gbc);
    }
}
