package javainterfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class añadirEquipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField leerNombre;
	private JTextField leerFecha;
	private JTextField leerPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					añadirEquipo frame = new añadirEquipo();
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
	public añadirEquipo() {
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
		
		JLabel lblFechaFun = new JLabel("Fecha de fundación");
		panel_4.add(lblFechaFun);
		
		leerFecha = new JTextField();
		panel_4.add(leerFecha);
		leerFecha.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblPais = new JLabel("País");
		panel_6.add(lblPais);
		
		leerPais = new JTextField();
		panel_6.add(leerPais);
		leerPais.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		panel_5.add(btnAgregar, BorderLayout.SOUTH);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JButton btnVolver = new JButton("Atrás");
		panel_7.add(btnVolver, BorderLayout.WEST);
	}

}
