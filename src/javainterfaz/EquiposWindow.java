package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import clases.Equipo;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class EquiposWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxTemporada;
	private DefaultListModel<Equipo> listaEquipos;
	private JButton btnAnadir, btnEliminar, btnAtras, btnGuardar;
	private JList<Equipo> equiposList;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquiposWindow frame = new EquiposWindow();
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
	public EquiposWindow() {
		
		 // Crear algunos usuarios de ejemplo si el archivo está vacío
        listaEquipos = Equipo.cargarEquipos();  // Intentar cargar los usuarios desde el archivo
        
        // Verificar si la lista está vacía, si es así, agregar usuarios por defecto
        if (listaEquipos.isEmpty()) {
            // Crear algunos usuarios predeterminados
            Equipo equipo1 = new Equipo("Athletic Club", "1898", "Bilbao", null); 
            Equipo equipo2 = new Equipo("Real Madrid", "1902", "Madrid", null); 
            Equipo equipo3 = new Equipo("FC Barcelona", "1899", "Barcelona", null); 
            Equipo equipo4 = new Equipo("Sevilla FC", "1890", "Sevilla", null); 
            Equipo equipo5 = new Equipo("Valencia CF", "1919", "Valencia", null); 
            Equipo equipo6 = new Equipo("Atlético Madrid", "1903", "Madrid", null); 
            
            listaEquipos.addElement(equipo1);
            listaEquipos.addElement(equipo2);
            listaEquipos.addElement(equipo3);
            listaEquipos.addElement(equipo4);
            listaEquipos.addElement(equipo5);
            listaEquipos.addElement(equipo6);
           

            // Guardar los usuarios predeterminados en el archivo
            Equipo.guardarEquipos(listaEquipos);
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
		lblNewLabel.setIcon(new ImageIcon(EquiposWindow.class.getResource("/img/imagenes/logotxurdi.png")));
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
		
		JLabel lblNewLabel_1 = new JLabel("Temporada");
		panel_4.add(lblNewLabel_1);
		
		comboBoxTemporada = new JComboBox<String>();
		panel_4.add(comboBoxTemporada);
		
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
		
		btnGuardar = new JButton("Guardar");
		panel_8.add(btnGuardar);
		
		
		equiposList = new JList<Equipo>(listaEquipos);
		panel_7.add(equiposList, BorderLayout.CENTER);
		

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
				int[] indices = equiposList.getSelectedIndices();
				int numeroOpciones = indices.length;
				if (numeroOpciones <= 0) {
					// si NO hay opciones seleccionadas
					JOptionPane.showMessageDialog(this,(String)"Error. No hay equipos seleccionados","Error",JOptionPane.ERROR_MESSAGE,null);
				} 
				else {
					 for (int i = indices.length - 1; i >= 0; i--) {
				            listaEquipos.remove(indices[i]);
					 } // Guardar los usuarios predeterminados en el archivo
			            Equipo.guardarEquipos(listaEquipos);
				}
			}
		}

}