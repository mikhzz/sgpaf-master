package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaAjuda extends JFrame {

	private JPanel contentPane;
	private JTextPane txtpnParaAjudaContate;
	private JLabel lblIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAjuda frame = new TelaAjuda();
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
	public TelaAjuda() {
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaAjuda.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 301, 378);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnParaAjudaContate = new JTextPane();
		txtpnParaAjudaContate.setEditable(false);
		txtpnParaAjudaContate.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtpnParaAjudaContate.setBackground(Color.WHITE);
		txtpnParaAjudaContate.setText(
				"    Para ajuda contate o Administrador do Sistema.\r\n\r\n     tel: (48) 98829-3138\r\n     email: mikael@vgaauditores.com.br");
		txtpnParaAjudaContate.setBounds(10, 11, 266, 143);
		contentPane.add(txtpnParaAjudaContate);

		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(TelaAjuda.class.getResource("/icons/usuario/mission-control-icon.png")));
		lblIcon.setBounds(76, 198, 141, 111);
		contentPane.add(lblIcon);
	}
}
