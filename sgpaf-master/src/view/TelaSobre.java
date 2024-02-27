package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class TelaSobre extends JFrame {

	private JPanel contentPane;
	private JTextArea txtrDesenvolvidoPorMikael;
	private JLabel lblFundo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setBackground(Color.WHITE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSobre.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 497);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtrDesenvolvidoPorMikael = new JTextArea();
		txtrDesenvolvidoPorMikael.setEditable(false);
		txtrDesenvolvidoPorMikael.setFont(new Font("Calibri", Font.BOLD, 14));
		txtrDesenvolvidoPorMikael.setBackground(Color.WHITE);
		txtrDesenvolvidoPorMikael.setText(
				"         Desenvolvido por: Mikael (@mikaelgrc) e Paulo Daros (@paulojosedaros)\r\n        \r\n         Florianópolis - 2020.\r\n\r\n         Todos os Direitos Reservados a VGA-AUDITORES INDEPENDENTES.");
		txtrDesenvolvidoPorMikael.setBounds(10, 0, 669, 149);
		contentPane.add(txtrDesenvolvidoPorMikael);

		lblFundo = new JLabel("");
		lblFundo.setBackground(Color.WHITE);
		lblFundo.setIcon(new ImageIcon(TelaSobre.class.getResource("/icons/vga/logoFundoSobre.png")));
		lblFundo.setBounds(191, 141, 498, 316);
		contentPane.add(lblFundo);
	}
}
