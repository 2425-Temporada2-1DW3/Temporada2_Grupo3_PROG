package javainterfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class gestionAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                gestionAdmin frame = new gestionAdmin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public gestionAdmin() {
        setTitle("Gestión Administrativa - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400); // Ajustar tamaño de la ventana
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

        JLabel lblLogo = new JLabel(new ImageIcon(gestionAdmin.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Botón "Gestionar equipos"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JButton btnGestEq = new JButton("Gestionar equipos");
        contentPane.add(btnGestEq, gbc);

        // Botón "Gestionar resultados"
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;

        JButton btnGestRes = new JButton("Gestionar resultados");
        contentPane.add(btnGestRes, gbc);

        // Botón "Gestionar jugadores"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;

        JButton btnGestJug = new JButton("Gestionar jugadores");
        contentPane.add(btnGestJug, gbc);

        // Botón "Gestionar temporadas"
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;

        JButton btnGestTemp = new JButton("Gestionar temporadas");
        contentPane.add(btnGestTemp, gbc);

        // Botón "Cerrar sesión"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnCerrar = new JButton("Cerrar sesión");
        contentPane.add(btnCerrar, gbc);
    }
}
