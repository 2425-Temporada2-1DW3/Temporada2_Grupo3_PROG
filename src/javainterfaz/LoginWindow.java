package javainterfaz;

import java.awt.EventQueue;
import javax.swing.*;

import clases.usuario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textUsuario;
    private JTextField textContrasena;
    
    //Crear lista donde se introduciran los usuarios traidos de XML
    private DefaultListModel<usuario> dlmUsuarios; // Modelo para la lista de usuarios    
    
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginWindow frame = new LoginWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginWindow() {
    	
        dlmUsuarios = cargarUsuariosDesdeXML("usuarios.xml"); // Cargar usuarios desde el XML

    	
    	usuario a1 = new usuario();
        System.out.println(a1);
        
        
        setTitle("Inicio de Sesion - Txurdi Liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        JPanel contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);

        GridBagConstraints gbc;

        // Título
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel lblTitulo = new JLabel("Inicio de Sesion - Txurdi Liga", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(lblTitulo, gbc);

        // Espacio para logo
        gbc = new GridBagConstraints(); // Create a new instance to avoid reuse issues
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);

        // Usuario
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(lblUsuario, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;

        textUsuario = new JTextField();
        contentPane.add(textUsuario, gbc);

        // Contraseña
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(lblContrasena, gbc);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;

        textContrasena = new JPasswordField();
        contentPane.add(textContrasena, gbc);

        // Botones
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnIngresar = new JButton("Ingresar");
        
        
        btnIngresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//Cojemos la informacion de los campos de texto y se guarda
        		String nombreUsuario = textUsuario.getText();
                String contraseña = textContrasena.getText();
                
                usuario usuarioIntroducido = validarCredenciales(nombreUsuario, contraseña);
                
                if (usuarioIntroducido != null) {
                    // Aquí entra correctamente te lleva a su ventana correspondiente
                	
                	
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        
        
        JButton btnInvitado = new JButton("Invitado");
        panelBotones.add(btnIngresar);
        panelBotones.add(btnInvitado);
        contentPane.add(panelBotones, gbc);

        // Pie de página
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        JLabel lblFooter = new JLabel("Txurdi Liga APP", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));
        contentPane.add(lblFooter, gbc);
    }
    
    
    
    // VALIDAR CREDENCIALES
    private usuario validarCredenciales(String nombreUsuario, String contraseña) {
        for (int i = 0; i < dlmUsuarios.size(); i++) {
            usuario u = dlmUsuarios.get(i);
            if (u.getNombre().equals(nombreUsuario) && u.getContrasena().equals(contraseña)) {
                return u;  // Si encuentra un usuario que coincide
            }
        }
        return null;  // Si no se encuentra
    }

}
