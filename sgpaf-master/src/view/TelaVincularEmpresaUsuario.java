package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerUsuarioEmpresa;
import model.boNovo.UsuarioEmpresaBO;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;

import java.awt.Toolkit;
import java.awt.Dimension;

public class TelaVincularEmpresaUsuario extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTable tableEmpresasDosUsuarios;
	private JLabel labelNomeUsuario;
	private TelaEmpresaParaUsuario telaEmpresaParaUsuario = null;
	private TelaUsuarioParaEmpresa telaUsuarioParaEmpresa = null;
	private Usuario usuarioLogado;
	private Usuario usuarioVincular;
	private Empresa empresa;
	private JLabel labelNomeUsuarioSelecionado;
	private ControllerUsuarioEmpresa control;
	private JButton btnTrocaUsuario;
	private JButton btnADDEmpresa;
	private JButton btnExluirEmpresa;
	private JButton btnOk;
	private JLabel lblUsuario;
	private JLabel lblLogoTopo;
	private JSeparator separator;
	private JScrollPane scrollPaneEmpresasDosUsuarios;
	private DefaultTableModel modelTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaVincularEmpresaUsuario dialog = new TelaVincularEmpresaUsuario(new Usuario());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaVincularEmpresaUsuario(Usuario pUsuarioLogado) {
		setMaximumSize(new Dimension(875, 600));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaVincularEmpresaUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.usuarioVincular = pUsuarioLogado;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				control = new ControllerUsuarioEmpresa(tableEmpresasDosUsuarios, labelNomeUsuario,
						tableEmpresasDosUsuarios, labelNomeUsuarioSelecionado, pUsuarioLogado);
				control.atualizarJTableAction(pUsuarioLogado);

			}

			@Override
			public void windowActivated(WindowEvent e) {
				if (control == null) {
					System.out.println("control ESTA NULO NO WINDOWaCTIVATE TELAVINCULAR");
					return;
				}
				if (usuarioVincular != null) {
					control.atualizarJTableAction(usuarioVincular);
				} else {
					System.out.println("OBJETO USUARIOVINCULAR ESTA NULO NO WINDOWaCTIVATE TELAVINCULAR");
				}

				try {

					System.out.println(
							"entrou no envento activedtela vincular empresa no evento windowActivated Usuario");
					usuarioVincular = telaUsuarioParaEmpresa.getControl().getUserSelecionadaTable();
					control.atualizarJTableAction(usuarioVincular);
					control.populaLabelUsuario(usuarioVincular);

				} catch (Exception arg0) {
					System.out.println(
							arg0.getMessage() + " tryCath tela vincular empresa no evento windowActivated Usuario");
				}
				try {
					empresa = telaEmpresaParaUsuario.getControl().getEmpresaSelecionadaTable();
					control.addEmpresaParaUsuario(usuarioVincular, empresa);
					telaEmpresaParaUsuario.getControl().atualizarJTableAction();
					control.atualizarJTableAction(usuarioVincular);
				} catch (Exception arg0) {
					System.out.println(
							arg0.getMessage() + " tryCath tela vincular empresa no evento windowActivated empresa");
				}
			}
		});

		setTitle("Vincular Empresa ao Usu\u00E1rio");
		setBounds(100, 100, 462, 527);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsuario.setBounds(23, 34, 94, 21);
		contentPanel.add(lblUsuario);

		lblLogoTopo = new JLabel("");
		lblLogoTopo.setIcon(new ImageIcon(TelaVincularEmpresaUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		lblLogoTopo.setBounds(345, 11, 101, 104);
		contentPanel.add(lblLogoTopo);

		separator = new JSeparator();
		separator.setBounds(-12, 131, 824, 2);
		contentPanel.add(separator);

		scrollPaneEmpresasDosUsuarios = new JScrollPane();
		scrollPaneEmpresasDosUsuarios.setBackground(Color.WHITE);
		scrollPaneEmpresasDosUsuarios.setBounds(23, 176, 302, 289);
		contentPanel.add(scrollPaneEmpresasDosUsuarios);
		
		
		modelTable = new DefaultTableModel(new Object[] {
				"Empresas" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		

		tableEmpresasDosUsuarios = new JTable();
		tableEmpresasDosUsuarios.setEnabled(true);
		tableEmpresasDosUsuarios.setBackground(Color.WHITE);
		tableEmpresasDosUsuarios.setFont(new Font("Calibri", Font.PLAIN, 14));
		tableEmpresasDosUsuarios.setModel(modelTable);
		tableEmpresasDosUsuarios.getColumnModel().getColumn(0).setPreferredWidth(209);
		scrollPaneEmpresasDosUsuarios.setViewportView(tableEmpresasDosUsuarios);

		btnExluirEmpresa = new JButton("");
		btnExluirEmpresa.setBackground(Color.WHITE);
		btnExluirEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEmpresa usuEmp = control.getEmpresaSelecionadaTable();
				if (usuEmp != null) {
					control.desvincularEmpresa(usuEmp);
					control.atualizarJTableAction(usuarioVincular);
				} else {
					System.out.println(
							"CHEGANDO USUARIO NULO OU INDICE -1 NO BOTAO LIXO" + " NA TELA VINCULAR EMPRESA USUARIO");
				}
			}
		});
		btnExluirEmpresa.setIcon(
				new ImageIcon(TelaVincularEmpresaUsuario.class.getResource("/icons/empresa/icons8-excluir-32.png")));
		btnExluirEmpresa.setBounds(362, 339, 45, 46);
		contentPanel.add(btnExluirEmpresa);

		labelNomeUsuario = new JLabel(usuarioVincular.getNome());
		labelNomeUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelNomeUsuario.setBounds(23, 144, 302, 21);
		contentPanel.add(labelNomeUsuario);

		btnADDEmpresa = new JButton("");
		btnADDEmpresa.setBackground(Color.WHITE);
		btnADDEmpresa.setIcon(new ImageIcon(
				TelaVincularEmpresaUsuario.class.getResource("/icons/empresa/icons8-empresas-relacionadas-50.png")));
		btnADDEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaEmpresaParaUsuario.show();
			}
		});
		btnADDEmpresa.setBounds(362, 269, 45, 46);
		contentPanel.add(btnADDEmpresa);

		labelNomeUsuarioSelecionado = new JLabel(usuarioVincular.getNome());
		labelNomeUsuarioSelecionado.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelNomeUsuarioSelecionado.setBounds(80, 34, 245, 21);
		contentPanel.add(labelNomeUsuarioSelecionado);

		btnTrocaUsuario = new JButton("");
		btnTrocaUsuario.setBackground(Color.WHITE);
		btnTrocaUsuario.setIcon(new ImageIcon(TelaVincularEmpresaUsuario.class
				.getResource("/icons/empresa/icons8-adicionar-usu\u00E1rio-masculino-48.png")));
		btnTrocaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTrocaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaUsuarioParaEmpresa.show();
			}
		});
		btnTrocaUsuario.setBounds(270, 60, 45, 46);
		contentPanel.add(btnTrocaUsuario);

		btnOk = new JButton("");
		btnOk.setBackground(Color.WHITE);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setIcon(new ImageIcon(TelaVincularEmpresaUsuario.class.getResource("/icons/empresa/icons8-ok-38.png")));
		btnOk.setBounds(362, 410, 45, 46);
		contentPanel.add(btnOk);

		telaEmpresaParaUsuario = new TelaEmpresaParaUsuario();
		telaUsuarioParaEmpresa = new TelaUsuarioParaEmpresa();
	}

	public JTable getTableEmpresasDosUsuarios() {
		return tableEmpresasDosUsuarios;
	}

	public JLabel getLabelNomeUsuario() {
		return labelNomeUsuario;
	}

	public JLabel getLabelNomeUsuarioSelecionado() {
		return labelNomeUsuarioSelecionado;
	}

	public JButton getBtnTrocaUsuario() {
		return btnTrocaUsuario;
	}

	public JButton getBtnADDEmpresa() {
		return btnADDEmpresa;
	}

	public JButton getBtnExluirEmpresa() {
		return btnExluirEmpresa;
	}

	public ControllerUsuarioEmpresa getControl() {
		return control;
	}

	public void setControl(ControllerUsuarioEmpresa control) {
		this.control = control;
	}

	public JButton getBtnOk() {
		return btnOk;
	}
}
