package javainterfaz;
import clases.usuario;


import java.awt.EventQueue;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;



public class LoginWindow extends JFrame {

	
    private static final long serialVersionUID = 1L;
    private JTextField textUsuario;
    private JTextField textContrasena;
 // Mover la declaración de dlmUsuarios aquí para que sea accesible en toda la clase
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
    	
    	// Inicializar el DefaultListModel aquí, ya que ahora es un campo de instancia
        dlmUsuarios = new DefaultListModel<>(); 
       
        
     // Cargar usuarios desde el archivo
        dlmUsuarios = usuario.cargarUsuarios();
        
        
    	
    	
        
        
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
                
             
                
             // Validar credenciales / Devuelve el valor del rol si esta bien, sino -1 o 0
                int rol = usuario.validarCredenciales(dlmUsuarios, nombreUsuario, contraseña);
                
                
                switch (rol) {
                case 1:
                    // Abrir ventana para el rol 1 (Administrador, por ejemplo)
                    JOptionPane.showMessageDialog(null, "Bienvenido, administrador.");
                    // Aquí abrirías la ventana del administrador
                    gestionAdmin ventanaAdmin = new gestionAdmin(); // Crea la ventana del administrador
                    ventanaAdmin.setVisible(true); // Muestra la ventana
                    dispose(); // Cierra esta ventana de login
                    break;
                    
                case 2:
                    // Abrir ventana para el rol 2 (Moderador, por ejemplo)
                    JOptionPane.showMessageDialog(null, "Bienvenido, arbitro.");
                    // Aquí abrirías la ventana del moderador
                    JornadasWindow ventanaJornadas = new JornadasWindow(); // Crea la ventana del administrador
                    ventanaJornadas.setVisible(true); // Muestra la ventana
                    dispose(); // Cierra esta ventana de login
                    break;
                    
                case 3:
                    // Abrir ventana para el rol 3 (Usuario regular, por ejemplo)
                    JOptionPane.showMessageDialog(null, "Bienvenido, usuario.");
                    // Aquí abrirías la ventana del usuario
                    JornadasWindow ventanaJornadasUsuario = new JornadasWindow(); // Crea la ventana del administrador
                    ventanaJornadasUsuario.setVisible(true); // Muestra la ventana
                    dispose(); // Cierra esta ventana de login
                    break;
                    
                case 0:
                    // Credenciales incorrectas
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case -1:
                    // Rol inválido
                    JOptionPane.showMessageDialog(null, "Error en los datos del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
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
    
   
    
    
 

}
