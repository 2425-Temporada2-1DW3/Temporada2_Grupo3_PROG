package javainterfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class añadirUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField leerUsuario;
    private JTextField leerContra;
    private JTextField leerContra2;
    private GridBagConstraints gbc_1;
    private GridBagConstraints gbc_leerUsuario;
    private GridBagConstraints gbc_lblContra;
    private GridBagConstraints gbc_leerContra;
    private GridBagConstraints gbc_lblContra2;
    private GridBagConstraints gbc_leerContra2;
    private GridBagConstraints gbc_lblTipo;
    private GridBagConstraints gbc_9;
    private ArrayList<usuario> listaUsuarios; // Modelo para la lista de usuarios

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                añadirUsuario frame = new añadirUsuario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public añadirUsuario() {
        setTitle("Añadir Usuario - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500); // Ajustar tamaño de ventana
        setLocationRelativeTo(null); // Centrar ventana

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
        contentPane = new JPanel(gbl_contentPane);
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

        JLabel lblLogo = new JLabel(new ImageIcon(añadirUsuario.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Usuario
        gbc_1 = new GridBagConstraints();
        gbc_1.insets = new Insets(0, 0, 5, 5);
        gbc_1.gridx = 0;
        gbc_1.gridy = 1;
        gbc_1.anchor = GridBagConstraints.EAST;

        JLabel lblNombre = new JLabel("Nuevo usuario:");
        contentPane.add(lblNombre, gbc_1);

        gbc_leerUsuario = new GridBagConstraints();
        gbc_leerUsuario.insets = new Insets(0, 0, 5, 0);
        gbc_leerUsuario.gridx = 1;
        gbc_leerUsuario.gridy = 1;
        gbc_leerUsuario.weightx = 1.0;
        gbc_leerUsuario.fill = GridBagConstraints.HORIZONTAL;

        leerUsuario = new JTextField();
        leerUsuario.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerUsuario, gbc_leerUsuario);

        // Contraseña
        gbc_lblContra = new GridBagConstraints();
        gbc_lblContra.insets = new Insets(0, 0, 5, 5);
        gbc_lblContra.gridx = 0;
        gbc_lblContra.gridy = 2;
        gbc_lblContra.anchor = GridBagConstraints.EAST;

        JLabel lblContra = new JLabel("Contraseña:");
        contentPane.add(lblContra, gbc_lblContra);

        gbc_leerContra = new GridBagConstraints();
        gbc_leerContra.insets = new Insets(0, 0, 5, 0);
        gbc_leerContra.gridx = 1;
        gbc_leerContra.gridy = 2;
        gbc_leerContra.weightx = 1.0;
        gbc_leerContra.fill = GridBagConstraints.HORIZONTAL;

        leerContra = new JTextField();
        leerContra.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerContra, gbc_leerContra);

        // Validar contraseña
        gbc_lblContra2 = new GridBagConstraints();
        gbc_lblContra2.insets = new Insets(0, 0, 5, 5);
        gbc_lblContra2.gridx = 0;
        gbc_lblContra2.gridy = 3;
        gbc_lblContra2.anchor = GridBagConstraints.EAST;

        JLabel lblContra2 = new JLabel("Repetir contraseña:");
        contentPane.add(lblContra2, gbc_lblContra2);

        gbc_leerContra2 = new GridBagConstraints();
        gbc_leerContra2.insets = new Insets(0, 0, 5, 0);
        gbc_leerContra2.gridx = 1;
        gbc_leerContra2.gridy = 3;
        gbc_leerContra2.weightx = 1.0;
        gbc_leerContra2.fill = GridBagConstraints.HORIZONTAL;

        leerContra2 = new JTextField();
        leerContra2.setPreferredSize(new Dimension(200, 30));
        contentPane.add(leerContra2, gbc_leerContra2);

        // Tipo
        gbc_lblTipo = new GridBagConstraints();
        gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
        gbc_lblTipo.gridx = 0;
        gbc_lblTipo.gridy = 4;
        gbc_lblTipo.anchor = GridBagConstraints.EAST;

        JLabel lblTipo = new JLabel("Tipo de usuario:");
        contentPane.add(lblTipo, gbc_lblTipo);
        
        JComboBox<String> comboUsuario = new JComboBox<String>();
        GridBagConstraints gbc_comboUsuario = new GridBagConstraints();
        gbc_comboUsuario.insets = new Insets(0, 0, 5, 0);
        gbc_comboUsuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboUsuario.gridx = 1;
        gbc_comboUsuario.gridy = 4;
        contentPane.add(comboUsuario, gbc_comboUsuario);
        comboUsuario.addItem("Administrador");
        comboUsuario.addItem("Árbitro");
        comboUsuario.addItem("Usuario");

        // Botón "Agregar"
        gbc_9 = new GridBagConstraints();
        gbc_9.insets = new Insets(0, 0, 5, 0);
        gbc_9.gridx = 0;
        gbc_9.gridy = 5;
        gbc_9.gridwidth = 2;
        gbc_9.anchor = GridBagConstraints.CENTER;

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String usuario = leerUsuario.getText();
        		String contra = leerContra.getText();
        		String contra2 = leerContra2.getText();
        		int tipo = comboUsuario.getSelectedIndex();
        		if (usuario.isEmpty() || contra.isEmpty() || contra2.isEmpty()) {
        			// si algun campo de texto esta vacio
    				JOptionPane.showMessageDialog(añadirUsuario.this,(String)"Error. Rellene todos los campos.","Error",JOptionPane.ERROR_MESSAGE,null);
        		} else if (contra.equals(contra2)) {
        			
        			usuario u = new usuario(usuario, contra, tipo);
        			listaUsuarios.add(u);
        			// Guardar el usuario predeterminados en el archivo
                    usuario.guardarUsuarios(listaUsuarios);
        		}
        	}
        });
        contentPane.add(btnAgregar, gbc_9);

        // Botón "Atrás"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnVolver = new JButton("Atrás");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gestionAdmin ventanaAdmin = new gestionAdmin();  // Ventana gestión de equipos
        		ventanaAdmin.setVisible(true);  // Mostrar la ventana
                dispose();  // Cerrar la ventana
        	}
        });
        contentPane.add(btnVolver, gbc);
    }
}
