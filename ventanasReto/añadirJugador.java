import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class añadirJugador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField leerNombre;
	private JTextField leerFecha;
	private JTextField leerEdad;
	private JTextField LeerNacionalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					añadirJugador frame = new añadirJugador();
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
	public añadirJugador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLogo = new JLabel("LOGO");
		contentPane.add(lblLogo, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre");
		panel_2.add(lblNombre);
		
		leerNombre = new JTextField();
		panel_2.add(leerNombre);
		leerNombre.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblPosicion = new JLabel("Posición");
		panel_4.add(lblPosicion);
		
		leerFecha = new JTextField();
		panel_4.add(leerFecha);
		leerFecha.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblEquipos = new JLabel("Equipo");
		panel_6.add(lblEquipos);
		
		JComboBox comboEquipos = new JComboBox();
		panel_6.add(comboEquipos);
		
		JButton btnAgregar = new JButton("Agregar");
		panel_5.add(btnAgregar, BorderLayout.SOUTH);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);
		
		JLabel lblEdad = new JLabel("Edad");
		panel_9.add(lblEdad);
		
		leerEdad = new JTextField();
		panel_9.add(leerEdad);
		leerEdad.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		panel_11.add(lblNacionalidad);
		
		LeerNacionalidad = new JTextField();
		panel_11.add(LeerNacionalidad);
		LeerNacionalidad.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnVolver = new JButton("Atrás");
		panel_7.add(btnVolver, BorderLayout.WEST);
	}

}
