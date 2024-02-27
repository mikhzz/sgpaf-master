package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.ControllerEmpresa;
import model.daoNovo.db.Empresa;
import model.util.JTextFieldLimit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Dimension;

public class TelaGerenciadorEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;

	private JTextField textFieldTipo;
	private JTextField textFieldEmail;
	private JTextField textFieldResponsavel;
	private JFormattedTextField txtCnpj;
	private JFormattedTextField txtTelefone;
	private JTable tableEmpresa;
	private JTextField tfPesquisaEmp;
	private JButton okButton;
	private Empresa empresaSelecionada = null;
	private TelaVisualizaEmpresa telaVisualizarEmpr;
	private ControllerEmpresa empresaControl;
	List<Empresa> listEmpresa;
	private JComboBox<String> cbPesquisaEmp;
	private ControllerEmpresa control;
	private JLabel lblNome;
	private JLabel lblCnpj;
	private MaskFormatter mascaraCnpj;
	private JLabel lblEndereco;
	private JLabel lblTelefone;
	private MaskFormatter mascaraTelefone;
	private JLabel lblTipo;
	private JLabel lblEmail;
	private JLabel lblResponsvel;
	private JLabel lblLogoTopo;
	private JSeparator separator;
	private JScrollPane scrollPaneListaEmpresas;
	private JLabel lblPesquisar;
	private JLabel lblPor;
	private JButton btnEditarEmp;
	private JButton btnExcluir;
	private JButton btnCancelarEmp;
	private JButton btnVisualizar;
	private DefaultTableModel modelTable;

	public static void main(String[] args) {
		try {
			TelaGerenciadorEmpresa dialog = new TelaGerenciadorEmpresa(new ControllerEmpresa());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaGerenciadorEmpresa(ControllerEmpresa control) {
		setResizable(false);
		this.control = control;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				empresaControl = new ControllerEmpresa(txtTelefone, txtCnpj, textFieldNome, textFieldEndereco,
						textFieldTipo, textFieldEmail, textFieldResponsavel, tableEmpresa, tfPesquisaEmp,
						cbPesquisaEmp);

				empresaControl.atualizarJTableAction();
			}
		});
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Cadastro de Empresa");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaGerenciadorEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
		setBounds(100, 100, 1167, 715);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNome = new JLabel("Nome: ");
			lblNome.setBounds(24, 11, 58, 20);
			lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblNome);
		}
		{
			textFieldNome = new JTextField();
			textFieldNome.setToolTipText("Digite o nome da nova Empresa");
			textFieldNome.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldNome.setBounds(24, 31, 223, 30);
			contentPanel.add(textFieldNome);
			textFieldNome.setColumns(10);
			textFieldNome.setDocument(new JTextFieldLimit(255));

		}
		{

			lblCnpj = new JLabel("Cnpj:");
			lblCnpj.setBounds(529, 11, 45, 20);
			lblCnpj.setFont(new Font("Calibri", Font.PLAIN, 14));
			
			contentPanel.add(lblCnpj);

		}

		try {
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			txtCnpj = new JFormattedTextField(mascaraCnpj);
			txtCnpj.setToolTipText("Digite o CNPJ composto por 14 números");
			
			txtCnpj.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					control.validarCnpjJaCadastrado(txtCnpj);
				}
			});
			txtCnpj.setFont(new Font("Calibri", Font.PLAIN, 14));
			txtCnpj.setBounds(529, 31, 181, 30);
		} catch (ParseException e) {
		}
		contentPanel.add(txtCnpj);

		{
			lblEndereco = new JLabel("Endere\u00E7o: ");
			lblEndereco.setBounds(195, 72, 77, 20);
			lblEndereco.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblEndereco);
		}
		{
			textFieldEndereco = new JTextField();
			textFieldEndereco.setToolTipText("Digite o Endereço da nova Empresa");
			textFieldEndereco.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldEndereco.setBounds(195, 92, 465, 32);
			contentPanel.add(textFieldEndereco);
			textFieldEndereco.setColumns(10);
			textFieldEndereco.setDocument(new JTextFieldLimit(255));
		}
		{

			lblTelefone = new JLabel("Telefone:");
			lblTelefone.setBounds(690, 66, 99, 32);
			lblTelefone.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblTelefone);
		}
		{

			try {
				mascaraTelefone = new MaskFormatter("(##)####-####");
				txtTelefone = new JFormattedTextField(mascaraTelefone);
				txtTelefone.setToolTipText("Digite o telefone não esquecendo do DDD");
				txtTelefone.setDocument(new JTextFieldLimit(14));
				txtTelefone.setFont(new Font("Calibri", Font.PLAIN, 14));
				txtTelefone.setBounds(688, 93, 146, 30);
			} catch (ParseException e) {
			}
			contentPanel.add(txtTelefone);

		}
		{
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(21, 72, 45, 20);
			lblTipo.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblTipo);
		}
		{
			textFieldTipo = new JTextField();
			textFieldTipo.setToolTipText("Digite o tipo da nova Empresa ex: ONG");
			textFieldTipo.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldTipo.setBounds(24, 93, 146, 30);
			contentPanel.add(textFieldTipo);
			textFieldTipo.setColumns(10);
			textFieldTipo.setDocument(new JTextFieldLimit(50));
		}
		{
			lblEmail = new JLabel("E-mail:");
			lblEmail.setBounds(280, 11, 54, 20);
			lblEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblEmail);
		}
		{
			textFieldEmail = new JTextField();
			textFieldEmail.setToolTipText("Digite o Email da Empresa");
			textFieldEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldEmail.setBounds(280, 31, 223, 30);
			contentPanel.add(textFieldEmail);
			textFieldEmail.setColumns(10);
			textFieldEmail.setDocument(new JTextFieldLimit(255));
		}
		{
			lblResponsvel = new JLabel("Respons\u00E1vel:");
			lblResponsvel.setBounds(741, 5, 117, 32);
			lblResponsvel.setFont(new Font("Calibri", Font.PLAIN, 14));
			contentPanel.add(lblResponsvel);
		}
		{
			textFieldResponsavel = new JTextField();
			textFieldResponsavel.setToolTipText("Digite o nome do Responsável pelo Empresa");
			textFieldResponsavel.setFont(new Font("Calibri", Font.PLAIN, 14));
			textFieldResponsavel.setBounds(741, 31, 213, 30);
			contentPanel.add(textFieldResponsavel);
			textFieldResponsavel.setColumns(10);
			textFieldResponsavel.setDocument(new JTextFieldLimit(255));
		}
		{

			okButton = new JButton("Salvar");
			okButton.setToolTipText("Clique para salvar nova Empresa");
			okButton.setBackground(Color.WHITE);
			okButton.setFont(new Font("Calibri", Font.PLAIN, 14));
			okButton.setIcon(
					new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/empresa/icons8-salvar-32.png")));
			okButton.setBounds(24, 152, 129, 30);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (empresaSelecionada != null) {

						empresaControl.editarEmpresaAction(empresaSelecionada);
						empresaSelecionada = null;
						control.atualizarJTableAction();
					} else {

						empresaControl.cadastrarEmpresaAction();
						empresaSelecionada = null;
					}

					control.atualizarJTableAction();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);

		}
		{
			lblLogoTopo = new JLabel("");
			lblLogoTopo.setBounds(1044, 0, 117, 104);
			lblLogoTopo.setIcon(new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
			contentPanel.add(lblLogoTopo);
		}

		separator = new JSeparator();
		separator.setBounds(-13, 193, 1212, 13);
		contentPanel.add(separator);

		scrollPaneListaEmpresas = new JScrollPane();
		scrollPaneListaEmpresas.setBackground(Color.WHITE);
		scrollPaneListaEmpresas.setBounds(24, 285, 1038, 379);
		contentPanel.add(scrollPaneListaEmpresas);

		
		modelTable = new DefaultTableModel(new Object[] {
				"ID", "Nome", "CNPJ",
				"Endere\u00E7o", "Telefone", "Tipo", "E-mail", "Respons\u00E1vel" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 }; 
		
		tableEmpresa = new JTable();
		tableEmpresa.setBackground(Color.WHITE);
		tableEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		tableEmpresa.setEnabled(true);
		tableEmpresa.setModel(modelTable);
		tableEmpresa.getColumnModel().getColumn(0).setPreferredWidth(22);
		tableEmpresa.getColumnModel().getColumn(1).setPreferredWidth(237);
		tableEmpresa.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPaneListaEmpresas.setViewportView(tableEmpresa);

		lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPesquisar.setBounds(24, 217, 128, 20);
		contentPanel.add(lblPesquisar);

		tfPesquisaEmp = new JTextField();
		tfPesquisaEmp.setToolTipText("Digite o nome da Empresa para pesquisar");
		tfPesquisaEmp.setDocument(new JTextFieldLimit(20));
		tfPesquisaEmp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				empresaControl.pesquisarAction();
				if (tfPesquisaEmp.getText().equals("")) {
					control.atualizarJTableAction();
				}
			}
		});
		tfPesquisaEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
		tfPesquisaEmp.setColumns(10);
		tfPesquisaEmp.setBounds(24, 238, 262, 30);
		contentPanel.add(tfPesquisaEmp);

		cbPesquisaEmp = new JComboBox<String>();
		cbPesquisaEmp.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				tfPesquisaEmp.requestFocus();
			}
		});

		cbPesquisaEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbPesquisaEmp.setModel(new DefaultComboBoxModel(new String[] { "Nome", "CNPJ", "Tipo", "E-mail" }));
		cbPesquisaEmp.setToolTipText("Selecione uma opção para pesquisar");
		cbPesquisaEmp.setBackground(Color.WHITE);
		cbPesquisaEmp.setBounds(326, 236, 147, 30);
		contentPanel.add(cbPesquisaEmp);

		lblPor = new JLabel("Por:");
		lblPor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPor.setBounds(326, 217, 59, 14);
		contentPanel.add(lblPor);

		btnEditarEmp = new JButton("");
		btnEditarEmp.setToolTipText("Seleciona uma empresa da lista para editar");
		btnEditarEmp.setBackground(Color.WHITE);
		btnEditarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empresaSelecionada = empresaControl.getEmpresaSelecionadaTable();
				if (empresaSelecionada != null) {

					empresaControl.popularCampoEditarEmp(empresaSelecionada);
//					control.atualizarJTableAction();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma empresa", "Ops!",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnEditarEmp.setIcon(
				new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/empresa/icons8-editar-32.png")));
		btnEditarEmp.setBounds(1087, 560, 45, 46);
		contentPanel.add(btnEditarEmp);
		{
			btnExcluir = new JButton("");
			btnExcluir.setToolTipText("Selecione uma empresa da lista para excluir");
			btnExcluir.setBackground(Color.WHITE);
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					empresaSelecionada = empresaControl.getEmpresaSelecionadaTable();
					if (empresaSelecionada != null) {
						empresaControl.excluirEmresaAction(empresaSelecionada);
					} else {
						empresaSelecionada = null;
						control.atualizarJTableAction();
						JOptionPane.showMessageDialog(null, "Selecione uma empresa", "Ops!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					empresaSelecionada = null;
					control.atualizarJTableAction();
				}
			});
			btnExcluir.setIcon(
					new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/empresa/icons8-excluir-32.png")));
			btnExcluir.setBounds(1087, 618, 45, 46);
			contentPanel.add(btnExcluir);
		}
		{
			btnCancelarEmp = new JButton("Cancelar");
			btnCancelarEmp.setToolTipText("Cancelar Operação");
			btnCancelarEmp.setBackground(Color.WHITE);
			btnCancelarEmp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					empresaSelecionada = null;
					empresaControl.limpartela();
					control.atualizarJTableAction();

				}
			});
			btnCancelarEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			btnCancelarEmp.setIcon(
					new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/empresa/icons8-cancelar-24.png")));
			btnCancelarEmp.setActionCommand("OK");
			btnCancelarEmp.setBounds(195, 152, 129, 30);
			contentPanel.add(btnCancelarEmp);
		}
		{
			btnVisualizar = new JButton("");
			btnVisualizar.setToolTipText("Escolha uma empresa da lista para acessar as informações");
			btnVisualizar.setBackground(Color.WHITE);
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					empresaSelecionada = empresaControl.getEmpresaSelecionadaTable();
					if (empresaSelecionada != null) {
						telaVisualizarEmpr = new TelaVisualizaEmpresa();
						telaVisualizarEmpr.getControllerEmpresaTelaVisualizaEmpr()
								.visualEmpresaPopulaTelaVisualEmpresaAction(empresaSelecionada);
						telaVisualizarEmpr.setEmpresaVisual(empresaSelecionada);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione uma empresa", "Ops!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					empresaSelecionada = null;
				}
			});
			btnVisualizar
					.setIcon(new ImageIcon(TelaGerenciadorEmpresa.class.getResource("/icons/empresa/Zoom-icon.png")));
			btnVisualizar.setBounds(1087, 503, 45, 46);
			contentPanel.add(btnVisualizar);
		}
	}

	public ControllerEmpresa getEmpresaControl() {
		return empresaControl;
	}

	public void setEmpresaControl(ControllerEmpresa empresaControl) {
		this.empresaControl = empresaControl;
	}

	public JTable getTableEmpresas() {
		return tableEmpresa;
	}

	public JTextField getTfPesquisaEmp() {
		return tfPesquisaEmp;
	}

	public JTextField getTextFieldNomeEmp() {
		return textFieldNome;
	}

	public JTextField getTextFieldEmailEmp() {
		return textFieldEmail;
	}

	public JTextField getTextFieldResponEmp() {
		return textFieldResponsavel;
	}

	public JFormattedTextField getTxtCnpjEmp() {
		return txtCnpj;
	}

	public JTextField getTextFieldEndEmp() {
		return textFieldEndereco;
	}

	public JFormattedTextField getTxtTelEmp() {
		return txtTelefone;
	}

	public JTextField getTextFieldTipoEmp() {
		return textFieldTipo;
	}

	public JButton getOkButtonSalvarEmp() {
		return okButton;
	}

	public JComboBox getCbPesquisaEmp() {
		return cbPesquisaEmp;
	}
}
