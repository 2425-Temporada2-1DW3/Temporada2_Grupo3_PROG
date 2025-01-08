package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class EquiposWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxTemporada;

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
		setTitle("Gestion Equipos - Txurdi Liga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("foto");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		
		JButton btnAnadir = new JButton("Añadir");
		panel_3.add(btnAnadir);
		
		JButton btnEliminar = new JButton("Eliminar");
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
		gbl_panel_6.columnWidths = new int[]{0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Atras");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_6.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
	}

}
