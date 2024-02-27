package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerLogin;
import model.util.JTextFieldLimit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordFieldSenha;
	private ControllerLogin loginControl;
	private TelaPrincipal validadoLogin;
	private JButton btnEntrar;
	private JLabel lblSenha;
	private JLabel lblLogin;
	private JLabel lblLogoTopo;
	private JLabel lblIconeUsuario;
	private JLabel lblIconeSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				loginControl = new ControllerLogin(textFieldLogin, passwordFieldSenha);
			}
		});
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("Ol\u00E1, seja bem vindo!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaLogin.class.getResource("/icons/vga/Logo VGA.jpg")));
		setBounds(100, 100, 505, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblLogin.setBounds(118, 164, 64, 27);
		contentPane.add(lblLogin);

		textFieldLogin = new JTextField();
		textFieldLogin.setToolTipText("Digite seu login!");
		textFieldLogin.setFont(new Font("Calibri", Font.PLAIN, 17));

		textFieldLogin.setBounds(118, 195, 242, 39);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		textFieldLogin.setDocument(new JTextFieldLimit(100));

		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblSenha.setBounds(118, 245, 97, 27);
		contentPane.add(lblSenha);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setToolTipText("Digite sua senha!");
		passwordFieldSenha.setFont(new Font("Calibri", Font.PLAIN, 17));
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				// System.out.println(arg0.getKeyCode());
				if (arg0.getKeyCode() == 10) {
					btnEntrar.doClick();
				}
			}
		});
		passwordFieldSenha.setBounds(118, 273, 242, 39);
		contentPane.add(passwordFieldSenha);
		passwordFieldSenha.setDocument(new JTextFieldLimit(50));
		btnEntrar = new JButton("Entrar");
		btnEntrar.setToolTipText("Clique para entrar!");
		btnEntrar.setFont(new Font("Calibri", Font.BOLD, 23));
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBackground(new Color(51, 153, 51));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean logado = loginControl.logarAction();
				System.out.println(logado + "------------------------------");
				if (logado) {
					System.out.println(logado + "------------------------------");
					dispose();
				}
			}
		});
		btnEntrar.setBounds(118, 377, 242, 39);
		contentPane.add(btnEntrar);

		lblLogoTopo = new JLabel("");
		lblLogoTopo.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/vga/Logo VGA.jpg")));
		lblLogoTopo.setBounds(190, 35, 97, 103);
		contentPane.add(lblLogoTopo);

		lblIconeUsuario = new JLabel("");
		lblIconeUsuario.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/usuario/boss-icon (1).png")));
		lblIconeUsuario.setBounds(77, 185, 88, 60);
		contentPane.add(lblIconeUsuario);

		lblIconeSenha = new JLabel("");
		lblIconeSenha.setIcon(new ImageIcon(TelaLogin.class.getResource("/icons/usuario/padlock-lock-icon.png")));
		lblIconeSenha.setBounds(77, 263, 81, 49);
		contentPane.add(lblIconeSenha);
	}

}
