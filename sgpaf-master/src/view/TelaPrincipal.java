package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

import controller.ControllerEmpresa;
import controller.ControllerUsuario;
import model.daoNovo.db.Perfil;
import model.daoNovo.db.Usuario;
import model.util.JImagePanel;
import model.util.JImagePanel.FillType;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {

	private Usuario usuarioLogado;
	private ControllerEmpresa empresaControl;
	private ControllerUsuario usuarioControl;
	private JPanel excluiUsuario;
	private JPanel contentPane;
	private JPanel panel;
	private JDialog caixa;
	private JFrame frame;
	private TelaVincularEmpresaUsuario telaEscolherEmpresa;

	private JDesktopPane desktopPaneFundo;
	private static JMenu mnUsurio;
	private static JMenu mnEmpresa;
	private static JMenuItem mntmCadastrar;
	private static JMenuItem mntmVincularEmopresaUsario;
	protected static final String login = null;

	private String msgAcesso = "Acesso somente para Administradores";

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//
//			public void run() {
//				try {
//					TelaPrincipal frame = new TelaPrincipal();
//					
//					frame.setVisible(true);
//					
//					// this.disable();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param user
	 * @throws IOException
	 */
	public TelaPrincipal(Usuario usuario) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setMinimumSize(new Dimension(800, 600));
		setExtendedState(Frame.MAXIMIZED_BOTH); // METODO QUE TORNA A TELA RESPONSIVA (INICIA MAXIMIZADA DE ACORDO COM A
											
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				usuarioControl = new ControllerUsuario();

			}
		});

		this.usuarioLogado = usuario;
		System.out.println(usuarioLogado.getNome() + " - " + usuarioLogado.getPerfil().getIdPerfil() + " - "
				+ usuarioLogado.getPerfil().getTipo());

		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(new Color(0, 0, 0));

		JLabel lblLogoFundo = new JLabel("");
		lblLogoFundo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/vga/vga_fundo_painel.jpg")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(168)
						.addComponent(lblLogoFundo, GroupLayout.PREFERRED_SIZE, 917, Short.MAX_VALUE).addGap(181)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblLogoFundo, GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE).addContainerGap()));
		getContentPane().setLayout(groupLayout);
		setFont(new Font("Dialog", Font.BOLD, 13));
		setTitle("SGPAF - Sistema de Gestão para Auditoria Fiscal   ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icons/vga/Logo VGA.jpg")));

		setBounds(100, 100, 1272, 679);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);

		mnUsurio = new JMenu("Usu\u00E1rio");
		mnUsurio.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnUsurio.setBackground(Color.WHITE);
		mnUsurio.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/usuario/icons8-nome-32.png")));
		menuBar.add(mnUsurio);

		mntmCadastrar = new JMenuItem("Gerenciar");
		mntmCadastrar.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmCadastrar.setBackground(Color.WHITE);
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				 contentPane = new CadastroUsuario();

//				Perfil perfilTest =  usuarioControl.getPerfil(usuarioLogado);
				if (usuarioLogado != null && usuarioLogado.getPerfil().getIdPerfil() == 1) {
					// setContentPane(contentPane);
					// revalidate();
					caixa = new TelaGerenciadorUsuario();
					caixa.setModal(true); // para prender o foco
					caixa.show(); // ou setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, msgAcesso);
				}
			}
		});

		mntmCadastrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmCadastrar.setIcon(
				new ImageIcon(TelaPrincipal.class.getResource("/icons/usuario/icons8-editar-utilizador-32.png")));
		mnUsurio.add(mntmCadastrar);

		mntmVincularEmopresaUsario = new JMenuItem("Vincular Empresa");
		mntmVincularEmopresaUsario.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmVincularEmopresaUsario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

//				Perfil perfilTest =  usuarioControl.getPerfil(usuarioLogado);
				if (usuarioLogado != null && usuarioLogado.getPerfil().getIdPerfil() == 1) {
					// setContentPane(contentPane);
					// revalidate();
					frame = new TelaVincularEmpresaUsuario(usuarioLogado);
					frame.show(); // ou setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, msgAcesso);
				}

			}
		});
		mntmVincularEmopresaUsario.setIcon(
				new ImageIcon(TelaPrincipal.class.getResource("/icons/usuario/icons8-empresa-cliente-32 (1).png")));
		mnUsurio.add(mntmVincularEmopresaUsario);

		mnEmpresa = new JMenu("Empresa");
		mnEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnEmpresa.setBackground(Color.WHITE);
		mnEmpresa.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/companies-icon.png")));
		menuBar.add(mnEmpresa);

		JMenuItem mntmCadastrar_1 = new JMenuItem("Gerenciar");
		mntmCadastrar_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmCadastrar_1.setBackground(Color.WHITE);
		mntmCadastrar_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (usuarioLogado != null && usuarioLogado.getPerfil().getIdPerfil() == 1) {
					empresaControl = new ControllerEmpresa();
					caixa = new TelaGerenciadorEmpresa(empresaControl);
					caixa.show();
				} else {
					JOptionPane.showMessageDialog(null, msgAcesso);
				}
			}
		});
		mntmCadastrar_1
				.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-organização-32.png")));
		mnEmpresa.add(mntmCadastrar_1);

		JMenu mnBalancete = new JMenu("Balancete");
		mnBalancete.setIcon(
				new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-relatório-de-lucro-32.png")));
		mnBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnBalancete.setBackground(Color.WHITE);
		menuBar.add(mnBalancete);

		JMenuItem mntmNovo = new JMenuItem("Novo");
		mntmNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioLogado != null) {
					frame = new TelaBalancete(usuarioLogado);
					frame.show();
				} else {
					JOptionPane.showMessageDialog(null, msgAcesso);
				}
			}
		});
		mntmNovo.setIcon(
				new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-adicionar-a-lista-32.png")));
		mntmNovo.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmNovo.setBackground(Color.WHITE);
		mnBalancete.add(mntmNovo);

		JMenuItem menuItem = new JMenuItem("Gerenciar");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usuarioLogado != null) {
					frame = new TelaGerenBalancete(usuarioLogado);
					frame.show();
				} else {
					JOptionPane.showMessageDialog(null, msgAcesso);
				}

			}
		});
		menuItem.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-editar-32.png")));
		menuItem.setFont(new Font("Calibri", Font.PLAIN, 14));
		menuItem.setBackground(Color.WHITE);
		mnBalancete.add(menuItem);

		JMenu mnSobre = new JMenu("Sobre");
		mnSobre.setFont(new Font("Calibri", Font.PLAIN, 14));
		mnSobre.setBackground(Color.WHITE);
		mnSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/usuario/My-Computer-icon.png")));
		menuBar.add(mnSobre);

		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmManual.setBackground(Color.WHITE);
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TelaManual mn = new TelaManual();
				mn.setVisible(true);

			}
		});
		mntmManual.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-contatos-32.png")));
		mntmManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		mnSobre.add(mntmManual);

		JMenuItem mntmNewMenuItem = new JMenuItem("Ajuda");
		mntmNewMenuItem.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAjuda aj = new TelaAjuda();
				aj.setVisible(true);

			}
		});
		mntmNewMenuItem
				.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-undefined-32.png")));
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		mnSobre.add(mntmNewMenuItem);

		JMenuItem mntmAutor = new JMenuItem("Autor");
		mntmAutor.setFont(new Font("Calibri", Font.PLAIN, 14));
		mntmAutor.setBackground(Color.WHITE);
		mntmAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre s = new TelaSobre();

				s.setVisible(true);

			}
		});
		mntmAutor.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icons/empresa/icons8-foundation-32.png")));
		mntmAutor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
		mnSobre.add(mntmAutor);
		menuBar.add(Box.createHorizontalGlue());
		setJMenuBar(menuBar);

		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				TelaLogin tela = new TelaLogin();
				tela.show();

			}
		});

		mnSair.setIcon(
				new ImageIcon(TelaPrincipal.class.getResource("/icons/usuario/icons8-logout-arredondado-32.png")));
		menuBar.add(mnSair);

	}

	public JMenuItem getMntmVincularEmopresaUsario() {
		return mntmVincularEmopresaUsario;
	}
}
