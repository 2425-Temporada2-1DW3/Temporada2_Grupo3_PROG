package javainterfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Equipo;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class añadirEquipo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField leerNombre;
    private JTextField leerFecha;
    private JTextField leerCiudad;
    private GridBagConstraints gbc_lblCiudad;
    private GridBagConstraints gbc_leerCiudad;
    private GridBagConstraints gbc_1;
    private GridBagConstraints gbc_2;
    private GridBagConstraints gbc_3;
    private GridBagConstraints gbc_4;
    private GridBagConstraints gbc_5;
    
    private DefaultListModel<Equipo> listaEquipos;

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

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 28, 0, 0, 0};
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

        JLabel lblLogo = new JLabel(new ImageIcon(añadirEquipo.class.getResource("/img/imagenes/logotxurdi.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLogo, gbc);
        
                // Nombre
                gbc_1 = new GridBagConstraints();
                gbc_1.insets = new Insets(0, 0, 5, 5);
                gbc_1.gridx = 0;
                gbc_1.gridy = 1;
                gbc_1.gridwidth = 1;
                gbc_1.anchor = GridBagConstraints.EAST;
                
                        JLabel lblNombre = new JLabel("Nombre:");
                        contentPane.add(lblNombre, gbc_1);
        
                gbc_2 = new GridBagConstraints();
                gbc_2.insets = new Insets(0, 0, 5, 0);
                gbc_2.gridx = 1;
                gbc_2.gridy = 1;
                gbc_2.weightx = 1.0; // Permitir que el campo se expanda
                gbc_2.fill = GridBagConstraints.HORIZONTAL;
                
                        leerNombre = new JTextField();
                        leerNombre.setPreferredSize(new Dimension(200, 30)); // Tamaño por defecto más grande
                        contentPane.add(leerNombre, gbc_2);
                
                        // Fecha de fundación
                        gbc_3 = new GridBagConstraints();
                        gbc_3.insets = new Insets(0, 0, 5, 5);
                        gbc_3.gridx = 0;
                        gbc_3.gridy = 2;
                        gbc_3.anchor = GridBagConstraints.EAST;
                        
                                JLabel lblFechaFun = new JLabel("Fecha de fundación:");
                                contentPane.add(lblFechaFun, gbc_3);
        
                gbc_4 = new GridBagConstraints();
                gbc_4.insets = new Insets(0, 0, 5, 0);
                gbc_4.gridx = 1;
                gbc_4.gridy = 2;
                gbc_4.weightx = 1.0;
                gbc_4.fill = GridBagConstraints.HORIZONTAL;
                
                        leerFecha = new JTextField();
                        leerFecha.setPreferredSize(new Dimension(200, 30));
                        contentPane.add(leerFecha, gbc_4);
        
                // Ciudad
                gbc_lblCiudad = new GridBagConstraints();
                gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
                gbc_lblCiudad.gridx = 0;
                gbc_lblCiudad.gridy = 3;
                gbc_lblCiudad.anchor = GridBagConstraints.EAST;
                
                        JLabel lblCiudad = new JLabel("Ciudad:");
                        contentPane.add(lblCiudad, gbc_lblCiudad);

        // Botón "Agregar"
        gbc_5 = new GridBagConstraints();
        gbc_5.insets = new Insets(0, 0, 5, 0);
        gbc_5.gridx = 0;
        gbc_5.gridy = 6;
        gbc_5.gridwidth = 2;
        gbc_5.anchor = GridBagConstraints.CENTER;

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String nomEquipo = leerNombre.getText();
        		String fecFund = leerFecha.getText();
        		String ciu = leerCiudad.getText();
        		if (nomEquipo.isEmpty() || fecFund.isEmpty() || ciu.isEmpty()) {
        			// si algun campo de texto esta vacio
    				JOptionPane.showMessageDialog(añadirEquipo.this,(String)"Error. Rellene todos los campos.","Error",JOptionPane.ERROR_MESSAGE,null);
        		} else {
        			 listaEquipos = Equipo.cargarEquipos(); 
        			 Equipo eq = new Equipo(nomEquipo, fecFund, ciu, null);
        			 listaEquipos.addElement(eq);
        			 Equipo.guardarEquipos(listaEquipos);
        			 JOptionPane.showMessageDialog(null, "Equipo guardado correctamente.");
        		}
        		
        	}
        });
        
                gbc_leerCiudad = new GridBagConstraints();
                gbc_leerCiudad.insets = new Insets(0, 0, 5, 0);
                gbc_leerCiudad.gridx = 1;
                gbc_leerCiudad.gridy = 3;
                gbc_leerCiudad.weightx = 1.0;
                gbc_leerCiudad.fill = GridBagConstraints.HORIZONTAL;
                
                        leerCiudad = new JTextField();
                        leerCiudad.setPreferredSize(new Dimension(200, 30));
                        contentPane.add(leerCiudad, gbc_leerCiudad);
        
        JLabel lblJugadores = new JLabel("Jugadores:");
        GridBagConstraints gbc_lblJugadores = new GridBagConstraints();
        gbc_lblJugadores.anchor = GridBagConstraints.EAST;
        gbc_lblJugadores.insets = new Insets(0, 0, 5, 5);
        gbc_lblJugadores.gridx = 0;
        gbc_lblJugadores.gridy = 4;
        contentPane.add(lblJugadores, gbc_lblJugadores);
        contentPane.add(btnAgregar, gbc_5);

        // Botón "Atrás"
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton btnVolver = new JButton("Atrás");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		EquiposWindow ventanaEquipos = new EquiposWindow();  // Ventana gestión de equipos
        		ventanaEquipos.setVisible(true);  // Mostrar la ventana
                dispose();  // Cerrar la ventana
        	}
        });
        contentPane.add(btnVolver, gbc);
    }
}