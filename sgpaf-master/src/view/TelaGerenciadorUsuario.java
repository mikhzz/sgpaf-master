package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.ControllerUsuario;
import model.daoNovo.db.Usuario;
import model.util.JTextFieldLimit;
import java.awt.Dimension;

public class TelaGerenciadorUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNomeUser;
	private JTextField tfLoginUsr;
	private JPasswordField passwordFieldSenhaUsr;
	private ControllerUsuario controller;
	private JFormattedTextField txtCpfUser;
	private JTextField tfPesquisaUser;
	private JTable tableListaUsuario;
	private JButton btnSalvar;
	private JComboBox<String> cbPesquisaUser;
	private ControllerUsuario usuarioControl;
	private JComboBox cbNivel;
	private JComboBox cbCargo;
	private Usuario usuarioSelecionado = null;
	private JLabel lblNome;
	private JLabel lblCargo;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JButton btnFechar;
	private JLabel lblPor;
	private JLabel lblLogoTopo;
	private JLabel lblNvel;
	private JLabel lblCpf;
	private JSeparator separator;
	private JLabel lblPesquisar;
	private JScrollPane scrollPaneListaUsuario;
	private JButton btnEditar;
	private JButton btnExcluir;
	private DefaultTableModel modelTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaGerenciadorUsuario dialog = new TelaGerenciadorUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaGerenciadorUsuario() {
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				usuarioControl = new ControllerUsuario(textFieldNomeUser, tfLoginUsr, passwordFieldSenhaUsr, cbCargo,
						cbNivel, txtCpfUser, tfPesquisaUser, tableListaUsuario, cbPesquisaUser);
				usuarioControl.atualizarJTableAction();
			}
		});

		setFont(new Font("Calibri", Font.BOLD, 14));
		setTitle("Gerenciador de Usu\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaGerenciadorUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		setBounds(100, 100, 800, 675);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setMinimumSize(new Dimension(875, 600));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNome = new JLabel("Nome:");
			lblNome.setBounds(10, 11, 46, 20);
			lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNome);
		}
		{
			textFieldNomeUser = new JTextField();
			textFieldNomeUser.setToolTipText("Digite o novo Usuário");
			textFieldNomeUser.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldNomeUser.setBounds(10, 34, 176, 25);
			contentPanel.add(textFieldNomeUser);
			textFieldNomeUser.setColumns(10);
			textFieldNomeUser.setDocument(new JTextFieldLimit(255));
		}
		{
			lblCargo = new JLabel("Cargo:");
			lblCargo.setBounds(410, 11, 55, 20);
			lblCargo.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblCargo);
		}

//		controller = new ControllerUsuario();
//		cargos = controller.consultarCargos();
//		limparCamposConsulta();

		lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 66, 55, 20);
		lblLogin.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPanel.add(lblLogin);

		tfLoginUsr = new JTextField();
		tfLoginUsr.setToolTipText("Crie um login para este novo Usuário! ex: email");
		tfLoginUsr.setFont(new Font("Calibri", Font.PLAIN, 14));
		tfLoginUsr.setBounds(10, 91, 176, 24);
		contentPanel.add(tfLoginUsr);
		tfLoginUsr.setColumns(10);
		tfLoginUsr.setDocument(new JTextFieldLimit(100));

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(212, 70, 46, 20);
		lblSenha.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPanel.add(lblSenha);

		passwordFieldSenhaUsr = new JPasswordField();
		passwordFieldSenhaUsr.setToolTipText("Digite uma senha, evite números simples");
		passwordFieldSenhaUsr.setFont(new Font("Calibri", Font.PLAIN, 14));
		passwordFieldSenhaUsr.setBounds(212, 91, 176, 24);
		contentPanel.add(passwordFieldSenhaUsr);
		passwordFieldSenhaUsr.setDocument(new JTextFieldLimit(50));
		{
			btnSalvar = new JButton("Salvar");
			btnSalvar.setToolTipText("Clique para salvar");
			btnSalvar.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnSalvar.setBackground(Color.WHITE);
			btnSalvar.setIcon(
					new ImageIcon(TelaGerenciadorUsuario.class.getResource("/icons/empresa/icons8-salvar-32.png")));
			btnSalvar.setBounds(10, 146, 129, 30);
			contentPanel.add(btnSalvar);
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (usuarioSelecionado != null) {
						usuarioControl.editarUsuarioAction(usuarioSelecionado);

					} else {
						usuarioControl.cadastrarAction(usuarioSelecionado);
					}
				}
			});
			btnSalvar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSalvar);
		}

		btnFechar = new JButton("Cancelar");
		btnFechar.setToolTipText("Cancelar a operação!");
		btnFechar.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnFechar.setBackground(Color.WHITE);
		btnFechar.setIcon(
				new ImageIcon(TelaGerenciadorUsuario.class.getResource("/icons/empresa/icons8-cancelar-24.png")));
		btnFechar.setBounds(149, 146, 129, 30);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioControl.limparCampos();
				usuarioSelecionado = null;
			}
		});
		contentPanel.add(btnFechar);

		lblLogoTopo = new JLabel("");
		lblLogoTopo.setBounds(615, 11, 104, 115);
		lblLogoTopo.setIcon(new ImageIcon(TelaGerenciadorUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		contentPanel.add(lblLogoTopo);

		lblNvel = new JLabel("N\u00EDvel:");
		lblNvel.setBounds(411, 69, 40, 20);
		lblNvel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPanel.add(lblNvel);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(212, 11, 46, 20);
		lblCpf.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPanel.add(lblCpf);

		MaskFormatter mascaraCpf;

		try {
			mascaraCpf = new MaskFormatter("###.###.### - ##");
			txtCpfUser = new JFormattedTextField(mascaraCpf);
			txtCpfUser.setToolTipText("Digite o CPF do novo Usuário contendo 11 números");
			txtCpfUser.setFont(new Font("Calibri", Font.PLAIN, 14));
			txtCpfUser.setBounds(212, 34, 176, 26);
		} catch (ParseException e) {
		}
		contentPanel.add(txtCpfUser);

		separator = new JSeparator();
		separator.setBounds(-28, 198, 747, 8);
		contentPanel.add(separator);

		tfPesquisaUser = new JTextField();
		tfPesquisaUser.setFont(new Font("Calibri", Font.PLAIN, 14));
		tfPesquisaUser.setDocument(new JTextFieldLimit(20));
		tfPesquisaUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				usuarioControl.pesquisarAction();
				if (tfPesquisaUser.getText().equals("")) {
					usuarioControl.atualizarJTableAction();
				}
			}
		});
		tfPesquisaUser.setColumns(10);
		tfPesquisaUser.setBounds(10, 231, 378, 25);
		contentPanel.add(tfPesquisaUser);

		lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPesquisar.setBounds(10, 212, 59, 14);
		contentPanel.add(lblPesquisar);

		cbPesquisaUser = new JComboBox<String>();
		cbPesquisaUser.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbPesquisaUser.setModel(new DefaultComboBoxModel(new String[] { "Nome", "Login", "CPF", "Cargo" }));
		cbPesquisaUser.setToolTipText("");
		cbPesquisaUser.setBackground(Color.WHITE);
		cbPesquisaUser.setBounds(420, 231, 147, 25);
		contentPanel.add(cbPesquisaUser);

		lblPor = new JLabel("Por");
		lblPor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPor.setBounds(420, 212, 59, 14);
		contentPanel.add(lblPor);

		scrollPaneListaUsuario = new JScrollPane();
		
		scrollPaneListaUsuario.setBackground(Color.WHITE);
		scrollPaneListaUsuario.setBounds(10, 284, 607, 341);
		
		contentPanel.add(scrollPaneListaUsuario);
        tableListaUsuario = new JTable();
		// tableListaUsuario.setCellSelectionEnabled(false);
		
		
			
        
		modelTable = new DefaultTableModel(new Object[] {
				"C\u00F3digo", "Nome", "Login", "CPF", "Cargo", "N\u00EDvel" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			}; 
	    tableListaUsuario.getTableHeader().setReorderingAllowed(true); 
		tableListaUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		tableListaUsuario.setBackground(Color.WHITE);
		tableListaUsuario.setEnabled(true);
		tableListaUsuario.setModel(modelTable);
		
			
		
			
			
			
	
	
		
		scrollPaneListaUsuario.setViewportView(tableListaUsuario);

		btnEditar = new JButton("");
		btnEditar.setToolTipText("Clique em um nome da lista e clique para editar!");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuarioSelecionado = usuarioControl.getItemSelecionadoTable();
				if (usuarioSelecionado != null) {
					usuarioControl.popularCampoEditarEmp();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um Usu�rio", "Ops!",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setIcon(
				new ImageIcon(TelaGerenciadorUsuario.class.getResource("/icons/empresa/icons8-editar-32.png")));
		btnEditar.setBounds(634, 510, 45, 46);
		contentPanel.add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Escolha um Usuário da lista e clique para Excluir!");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuarioControl.excluirUsuarioAction();
			}
		});
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setIcon(
				new ImageIcon(TelaGerenciadorUsuario.class.getResource("/icons/empresa/icons8-excluir-32.png")));
		btnExcluir.setBounds(634, 579, 45, 46);
		contentPanel.add(btnExcluir);

		cbCargo = new JComboBox();
		cbCargo.setToolTipText("Escolha o Cargo do novo Usuário");
		cbCargo.setBackground(Color.WHITE);
		cbCargo.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbCargo.setModel(new DefaultComboBoxModel(new String[] { "Auditor(a)", "Diretor(a)", "Estagi\u00E1rio(a)" }));
		cbCargo.setBounds(410, 34, 116, 25);
		contentPanel.add(cbCargo);

		cbNivel = new JComboBox();
		cbNivel.setToolTipText("Escolha o nível de acesso do novo Usuário");
		cbNivel.setBackground(Color.WHITE);
		cbNivel.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbNivel.setModel(new DefaultComboBoxModel(new String[] { "Admin", "Comum" }));
		cbNivel.setBounds(410, 91, 116, 23);
		contentPanel.add(cbNivel);

	}

	public JTable getTableUsuarios() {
		return tableListaUsuario;
	}

	public JTextField getTextFieldNomeUser() {
		return textFieldNomeUser;
	}

	public JFormattedTextField getTxtCpfUser() {
		return txtCpfUser;
	}

	public JPasswordField getPasswordFieldSenhaUsr() {
		return passwordFieldSenhaUsr;
	}

	public JButton getBtnSalvar() {
		return btnSalvar;
	}

	public JTextField getTfPesquisaUser() {
		return tfPesquisaUser;
	}

	public JComboBox getCbPesquisaUser() {
		return cbPesquisaUser;
	}

	public JTextField getTfLoginUsr() {
		return tfLoginUsr;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JComboBox getCbNivel() {
		return cbNivel;
	}

	public JComboBox getCbCargo() {
		return cbCargo;
	}
	
	
	
	
	
}