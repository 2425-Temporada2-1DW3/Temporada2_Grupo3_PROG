package javainterfaz;
import clases.usuario;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textUsuario;      // Campo de texto para ingresar el nombre de usuario
    private JTextField textContrasena;   // Campo de texto para ingresar la contraseña
    private ArrayList<usuario> listaUsuarios; // Modelo para la lista de usuarios

    // Método principal que lanza la ventana de inicio de sesión
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginWindow frame = new LoginWindow();  // Crear una nueva ventana de login
                frame.setVisible(true);  // Hacerla visible
            } catch (Exception e) {
                e.printStackTrace();  // En caso de error, imprimirlo
            }
        });
    }

    // Constructor de la clase LoginWindow
    public LoginWindow() {


        // Crear algunos usuarios de ejemplo si el archivo está vacío
        listaUsuarios = usuario.cargarUsuarios();  // Intentar cargar los usuarios desde el archivo

        // Verificar si la lista está vacía, si es así, agregar usuarios por defecto
        if (listaUsuarios.isEmpty()) {
            // Crear algunos usuarios predeterminados
            usuario usuario1 = new usuario("Itxiar", "pass123", 1); // Admin
            usuario usuario2 = new usuario("Arnaitz", "pass456", 2); // Árbitro
            usuario usuario3 = new usuario("Andres", "pass789", 3); // Usuario
            listaUsuarios.add(usuario1);
            listaUsuarios.add(usuario2);
            listaUsuarios.add(usuario3);

            // Guardar los usuarios predeterminados en el archivo
            usuario.guardarUsuarios(listaUsuarios);
        }
        // Configuración de la ventana
        setTitle("Inicio de Sesión - Txurdi Liga");  // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cerrar la aplicación al cerrar la ventana
        setSize(450, 300);  // Establecer el tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear un panel con un diseño GridBagLayout para organizar los componentes
        JPanel contentPane = new JPanel(new GridBagLayout());
        setContentPane(contentPane);  // Asignar este panel como el contenido de la ventana

        GridBagConstraints gbc;  // Restricciones para los componentes dentro del layout

        // Título de la ventana
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado alrededor del componente
        gbc.fill = GridBagConstraints.HORIZONTAL;  // El componente debe llenar el ancho disponible
        gbc.gridx = 0;  // Posición en la grilla (columna)
        gbc.gridy = 0;  // Posición en la grilla (fila)
        gbc.gridwidth = 2;  // El componente ocupa dos columnas

        JLabel lblTitulo = new JLabel("Inicio de Sesión - Txurdi Liga", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));  // Establecer el tipo de fuente
        contentPane.add(lblTitulo, gbc);  // Añadir el título al panel

        // Espacio para el logo (si existe)
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(LoginWindow.class.getResource("/img/imagenes/logotxurdi.png")));  // Cargar el logo desde el recurso
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);  // Centrar el logo
        contentPane.add(lblLogo, gbc);

        // Campo para ingresar el nombre de usuario
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);  // Alinear el texto a la derecha
        contentPane.add(lblUsuario, gbc);

        // Campo de texto donde el usuario ingresará su nombre
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;

        textUsuario = new JTextField();  // Crear el campo de texto
        contentPane.add(textUsuario, gbc);  // Añadir el campo al panel

        // Campo para ingresar la contraseña
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(lblContrasena, gbc);

        // Campo de texto donde el usuario ingresará su contraseña
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;

        textContrasena = new JPasswordField();  // Crear el campo de contraseña (que oculta el texto)
        contentPane.add(textContrasena, gbc);

        // Crear un panel para los botones
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Crear un panel con flujo centrado
        JButton btnIngresar = new JButton("Ingresar");  // Botón para ingresar

        // Acción del botón "Ingresar"
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos de los campos de texto
                String nombreUsuario = textUsuario.getText();
                String contraseña = textContrasena.getText();

                // Validar las credenciales y obtener el rol
                int rol = usuario.validarCredenciales(listaUsuarios, nombreUsuario, contraseña);

                // Dependiendo del rol, abrir una ventana diferente
                switch (rol) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Bienvenido, administrador.");
                        gestionAdmin ventanaAdmin = new gestionAdmin();  // Ventana para el administrador
                        ventanaAdmin.setVisible(true);  // Mostrar la ventana
                        dispose();  // Cerrar la ventana de login
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "Bienvenido, árbitro.");
                        JornadasWindow ventanaJornadas = new JornadasWindow();  // Ventana para el árbitro
                        ventanaJornadas.setVisible(true);  // Mostrar la ventana
                        dispose();  // Cerrar la ventana de login
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Bienvenido, usuario.");
                        JornadasWindow ventanaJornadasUsuario = new JornadasWindow();  // Ventana para el usuario
                        ventanaJornadasUsuario.setVisible(true);  // Mostrar la ventana
                        dispose();  // Cerrar la ventana de login
                        break;

                    case 0:
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    case -1:
                        JOptionPane.showMessageDialog(null, "Error en los datos del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

        // Botón para ingresar como invitado (sin validación)
        JButton btnInvitado = new JButton("Invitado");
        btnInvitado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JornadasWindow ventanaJornadasUsuario = new JornadasWindow();  // Ventana para el usuario
                ventanaJornadasUsuario.setVisible(true);  // Mostrar la ventana
                dispose();  // Cerrar la ventana de login
        	}
        });
        panelBotones.add(btnIngresar);  // Añadir el botón "Ingresar"
        panelBotones.add(btnInvitado);  // Añadir el botón "Invitado"
        contentPane.add(panelBotones, gbc);  // Añadir el panel de botones al contenido

        // Pie de página de la ventana
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        JLabel lblFooter = new JLabel("Txurdi Liga APP", SwingConstants.CENTER);  // Texto de pie de página
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 12));  // Establecer la fuente
        contentPane.add(lblFooter, gbc);  // Añadir al panel
    }
}
