package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import clases.usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class UsuariosWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<usuario> listaUsuarios;
	private JList<usuario> usuariosList;
	private DefaultListModel<usuario> dlm;
	
	private JButton btnAnadir, btnEliminar, btnAtras;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuariosWindow frame = new UsuariosWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UsuariosWindow() {
		
		 // Crear algunos usuarios de ejemplo si el archivo está vacío
        listaUsuarios = usuario.cargarUsuarios();  // Intentar cargar los usuarios desde el archivo
        
        // Verificar si la lista está vacía, si es así, agregar usuarios por defecto
        if (listaUsuarios.isEmpty()) {
            // Crear algunos usuarios predeterminados
            usuario usuario1 = new usuario("Itxiar", "pass123", 1); // Admin
            listaUsuarios.add(usuario1);

            // Guardar los usuarios predeterminados en el archivo
            usuario.guardarUsuarios(listaUsuarios);
            JOptionPane.showMessageDialog(null, "Equipo guardado correctamente.");
        }
        
        
		setTitle("Gestión Equipos - Txurdi Liga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UsuariosWindow.class.getResource("/img/imagenes/logotxurdi.png")));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		
		btnAnadir = new JButton("Añadir");
		btnAnadir.addActionListener(this);
		panel_3.add(btnAnadir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		panel_3.add(btnEliminar);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(this);
		
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.gridx = 1;
		gbc_btnAtras.gridy = 0;
		panel_6.add(btnAtras, gbc_btnAtras);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, BorderLayout.SOUTH);
		
		DefaultListModel<usuario> dlm = new DefaultListModel<usuario>();
		
		usuariosList = new JList<usuario>(dlm);
		panel_7.add(usuariosList, BorderLayout.CENTER);
		

	}
	
	// actionPerformed
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			// obtengo sobre que componente se ha realizado la accion
			Object o = ae.getSource();
			
			if (o == btnAnadir){
				añadirEquipo ventanaEquipo = new añadirEquipo();  // Ventana gestión de equipos
        		ventanaEquipo.setVisible(true);  // Mostrar la ventana
                dispose();  // Cerrar la ventana
			}
			else if (o == btnAtras) {
				gestionAdmin ventanaAdmin = new gestionAdmin();  // Ventana gestión de equipos
        		ventanaAdmin.setVisible(true);  // Mostrar la ventana
                dispose();  // Cerrar la ventana
			}
			else if (o == btnEliminar) {
				// obtengo cuantas opciones hay seleccionadas en la lista
				int[] indices = usuariosList.getSelectedIndices();
				int numeroOpciones = indices.length;
				if (numeroOpciones <= 0) {
					// si NO hay opciones seleccionadas
					JOptionPane.showMessageDialog(this,(String)"Error. No hay equipos seleccionados","Error",JOptionPane.ERROR_MESSAGE,null);
				} 
				else {
					 for (int i = indices.length - 1; i >= 0; i--) {
				            listaUsuarios.remove(indices[i]);
					 } // Guardar los usuarios predeterminados en el archivo
			            usuario.guardarUsuarios(listaUsuarios);
				}
			}
		}

}
