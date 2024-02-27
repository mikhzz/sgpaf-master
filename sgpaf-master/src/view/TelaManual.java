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

public class TelaManual extends JFrame {

	private JPanel contentPane;
	private JTextPane txtpnCheckOutManual;
	private JLabel lblIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaManual frame = new TelaManual();
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
	public TelaManual() {
		setTitle("Manual");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaManual.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 352, 407);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtpnCheckOutManual = new JTextPane();
		txtpnCheckOutManual.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtpnCheckOutManual.setBackground(Color.WHITE);
		txtpnCheckOutManual.setEditable(false);
		txtpnCheckOutManual.setText("check out MANUAL on:\r\n\r\nhttps://github.com/mikhzz/VGA_AUDIT/wiki");
		txtpnCheckOutManual.setBounds(10, 34, 299, 77);
		contentPane.add(txtpnCheckOutManual);

		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(TelaManual.class.getResource("/icons/usuario/easy-ebook-viewer-icon.png")));
		lblIcon.setBounds(123, 201, 186, 131);
		contentPane.add(lblIcon);
	}

}
