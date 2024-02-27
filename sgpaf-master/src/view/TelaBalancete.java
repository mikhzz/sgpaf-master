package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import controller.ControllerBalancete;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import model.util.JTextFieldLimit;
import model.util.MonetarioDocument;

public class TelaBalancete extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtRefPt;
	private JTextField txtNivel;
	private JTextField txtCEstruturado;
	private JTextField txtCodReduzido;
	private JTextField txtDescr;
	private JTextField txtSaldoInicial;
	private JTextField txtCredito;
	private JTextField txtDebito;
	private JTextField tfPesquisaLancamento;
	
	private JLabel lblNomeUsuario;
	private JLabel lblNomeEmpresa;
	private JLabel lblResultadoSaldoFinal;
	
	private JDateChooser dataFinal;
	private JDateChooser dataInicial;
	
	private JComboBox cbNatuConta;
	private JComboBox cbPesquisa;
	
	private JTable tableLancamentos;
	
	
	private ControllerBalancete control;
	private UsuarioEmpresa usuarioEmpresa;
	
	private TelaVincularEmpresaUsuario  telaVincularEmpresaUsuario = null;
	private JButton btnEmpresa;
	private JButton btnImportLancamento;
	private JYearChooser yearChooserDataBase;
	private DefaultTableModel modelTable;
	private Lancamento lancamentoSelecionado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBalancete frame = new TelaBalancete(new Usuario());
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
	public static TelaBalancete GenerateTelaBalancete(Usuario pUsuario,Balancete balancete) {
		TelaBalancete t = new TelaBalancete( pUsuario);
		t.getControl().setBalancete(balancete);
		return t;
	}
	public TelaBalancete(Usuario usuario) {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				if(control==null) {
					System.out.println("control ESTA NULO NO WINDOWaCTIVATE TELAVINCULAR");
					return;
				}else {
					control.windowActivated();
				}
			}
		});

		setTitle("Balancete");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaBalancete.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1205, 724);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRefpt = new JLabel("Ref.");
		lblRefpt.setBounds(22, 120, 38, 26);
		lblRefpt.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblRefpt);
		
		JLabel lblNaturezaConta = new JLabel("Nat. Conta");
		lblNaturezaConta.setBounds(98, 123, 100, 20);
		lblNaturezaConta.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNaturezaConta);
		
		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setBounds(208, 120, 46, 26);
		lblNivel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNivel);
		
		JLabel lblCdiigoEstr = new JLabel("C\u00F3digo Estr.");
		lblCdiigoEstr.setBounds(281, 123, 103, 20);
		lblCdiigoEstr.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCdiigoEstr);
		
		JLabel lblCdigoReduzido = new JLabel("C\u00F3digo Red.");
		lblCdigoReduzido.setBounds(439, 120, 79, 26);
		lblCdigoReduzido.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCdigoReduzido);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(553, 124, 79, 14);
		lblDescrio.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblDescrio);
		
		JLabel lblSaldoInicial = new JLabel("Saldo Inicial");
		lblSaldoInicial.setBounds(24, 192, 92, 26);
		lblSaldoInicial.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblSaldoInicial);
		
		JLabel lblDbito = new JLabel("D\u00E9bito");
		lblDbito.setBounds(208, 193, 63, 27);
		lblDbito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblDbito);
		
		JLabel lblCrdito = new JLabel("Cr\u00E9dito");
		lblCrdito.setBounds(400, 192, 86, 26);
		lblCrdito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCrdito);
		
		txtRefPt = new JTextField();
		txtRefPt.setDocument(new JTextFieldLimit(3));
		txtRefPt.setBounds(22, 144, 38, 26);
		txtRefPt.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtRefPt);
		txtRefPt.setColumns(10);
		
		txtNivel = new JTextField();
		txtNivel.setDocument(new JTextFieldLimit(10));
		txtNivel.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				control.validarCamposSomenteNumeros(e,txtNivel);
			}
		});
		txtNivel.setBounds(208, 144, 38, 26);
		txtNivel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtNivel);
		txtNivel.setColumns(10);
		
		txtCEstruturado = new JTextField();
		txtCEstruturado.setDocument(new JTextFieldLimit(25));
		
		txtCEstruturado.setBounds(281, 144, 118, 26);
		txtCEstruturado.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtCEstruturado);
		txtCEstruturado.setColumns(10);
		
		txtCodReduzido = new JTextField();
		txtCodReduzido.setDocument(new JTextFieldLimit(11));
		txtCodReduzido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				control.validarCamposSomenteNumeros(e,txtCodReduzido);
			}
		});
		txtCodReduzido.setBounds(439, 144, 69, 26);
		txtCodReduzido.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtCodReduzido);
		txtCodReduzido.setColumns(10);
		
		txtDescr = new JTextField();
		txtDescr.setDocument(new JTextFieldLimit(255));
		txtDescr.setBounds(553, 144, 470, 26);
		txtDescr.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtDescr);
		txtDescr.setColumns(10);
		
		txtSaldoInicial = new JTextField();
		txtSaldoInicial.setDocument(new MonetarioDocument());
		
		txtSaldoInicial.setBounds(44, 218, 112, 25);
		txtSaldoInicial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				control.calcularCredFinal();
			}
		});
		txtSaldoInicial.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtSaldoInicial);
		txtSaldoInicial.setColumns(10);
		
		txtCredito = new JTextField();
		txtCredito.setDocument(new MonetarioDocument());
		txtCredito.setBounds(424, 217, 112, 26);
		txtCredito.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				control.calcularCredFinal();
			}
		});
		txtCredito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtCredito);
		txtCredito.setColumns(10);
		
		txtDebito = new JTextField();
		txtDebito.setDocument(new MonetarioDocument());
		txtDebito.setBounds(232, 217, 112, 27);
		txtDebito.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				control.calcularCredFinal();
			}
		});
		txtDebito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txtDebito);
		txtDebito.setColumns(10);
		
		JLabel lblPeriodoInicial = new JLabel("Per\u00EDodo Inicial:");
		lblPeriodoInicial.setBounds(620, 45, 103, 23);
		lblPeriodoInicial.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPeriodoInicial);
		
		JButton btnAddLancamento = new JButton("");
		btnAddLancamento.setToolTipText("Adicione um novo lançamento se houver");
		btnAddLancamento.setBounds(968, 192, 55, 55);
		btnAddLancamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cadastrarLancamento();
			}
		});
		btnAddLancamento.setBackground(Color.WHITE);
		btnAddLancamento.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-add-list-50(1).png")));
		contentPane.add(btnAddLancamento);
		
		JLabel lblLogoTopo = new JLabel("");
		lblLogoTopo.setBounds(1040, 11, 101, 92);
		lblLogoTopo.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/vga/Logo VGA.jpg")));
		contentPane.add(lblLogoTopo);
		
		JLabel lblAuditor = new JLabel("Auditor:");
		lblAuditor.setBounds(22, 30, 55, 23);
		lblAuditor.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblAuditor);
		
		lblNomeUsuario = new JLabel(usuario.getNome());
		lblNomeUsuario.setBounds(86, 29, 352, 23);
		lblNomeUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNomeUsuario);
		
		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(22, 64, 55, 23);
		lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblEmpresa);
		
		JLabel lblPeriodoFinal = new JLabel("Per\u00EDodo Final:");
		lblPeriodoFinal.setBounds(620, 80, 103, 23);
		lblPeriodoFinal.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPeriodoFinal);
		
		lblNomeEmpresa = new JLabel();
		lblNomeEmpresa.setBounds(86, 64, 352, 23);
		lblNomeEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNomeEmpresa);
		
		dataInicial = new JDateChooser();
		dataInicial.setToolTipText("Escolha um período inicial no calendário ao lado");
		dataInicial.setBounds(712, 45, 154, 20);
		contentPane.add(dataInicial);
		
		dataFinal = new JDateChooser();
		dataFinal.setToolTipText("Escolha um período final no calendario ao lado");
		dataFinal.setBounds(712, 80, 154, 20);
		contentPane.add(dataFinal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-12, 114, 1455, 2);
		contentPane.add(separator);
		
		cbNatuConta = new JComboBox();
		cbNatuConta.setBackground(Color.WHITE);
		cbNatuConta.setBounds(98, 144, 69, 26);
		cbNatuConta.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Passivo"}));
		cbNatuConta.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(cbNatuConta);
		
		JLabel lblCifraoSaldoInicial = new JLabel("R$");
		lblCifraoSaldoInicial.setBounds(22, 217, 28, 26);
		lblCifraoSaldoInicial.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCifraoSaldoInicial);
		
		JLabel lblCifraoDebito = new JLabel("R$");
		lblCifraoDebito.setBounds(211, 218, 28, 26);
		lblCifraoDebito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCifraoDebito);
		
		JLabel lblCifraoCredito = new JLabel("R$");
		lblCifraoCredito.setBounds(400, 217, 28, 26);
		lblCifraoCredito.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblCifraoCredito);
		
		lblResultadoSaldoFinal = new JLabel("0");
		lblResultadoSaldoFinal.setBounds(620, 217, 154, 26);
		lblResultadoSaldoFinal.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblResultadoSaldoFinal);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-12, 254, 1405, 9);
		contentPane.add(separator_1);
		
		btnEmpresa = new JButton("");
		btnEmpresa.setToolTipText("Clique para trocar empresa");
		btnEmpresa.setBounds(463, 32, 55, 55);
		btnEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.selectEmpresa();
			}
		});
		btnEmpresa.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-empresas-relacionadas-50.png")));
		btnEmpresa.setBackground(Color.WHITE);
		contentPane.add(btnEmpresa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 363, 1001, 313);
		contentPane.add(scrollPane);
		
		
		   modelTable = new DefaultTableModel(new Object[] {
				   "Ref.", "Natureza", "N\u00EDvel", "C\u00F3d. Estru.", "C\u00F3d. Red", "Descri\u00E7\u00E3o", "Saldo Inicial", "D\u00E9bito", "Cr\u00E9dito", "Saldo Final" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		
		
		tableLancamentos = new JTable();
		tableLancamentos.setEnabled(true);
		tableLancamentos.setToolTipText("Balancete da  Empresa");
		tableLancamentos.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableLancamentos);
		tableLancamentos.setModel(modelTable);
		
		tfPesquisaLancamento = new JTextField();
		tfPesquisaLancamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				control.pesquisarAction();
				if(tfPesquisaLancamento.getText().equals("")){
					control.atualizarJTableAction();
				}
			}
		});
		tfPesquisaLancamento.setBounds(22, 306, 436, 26);
		tfPesquisaLancamento.setFont(new Font("Calibri", Font.PLAIN, 14));
		tfPesquisaLancamento.setColumns(10);
		contentPane.add(tfPesquisaLancamento);
		
		cbPesquisa = new JComboBox();
		cbPesquisa.setBackground(Color.WHITE);
		cbPesquisa.setBounds(487, 306, 140, 27);
		cbPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Referência", "Descrição", "Natureza", "Nível"}));
		cbPesquisa.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(cbPesquisa);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setToolTipText("Selecione um lançamento para excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancamentoSelecionado = control.lancamentoSelecionadoTable();
				if(lancamentoSelecionado!=null) {
					control.excluirLancamentoAction();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma conta!");
				}
				lancamentoSelecionado=null;
			}
		});
		btnExcluir.setBounds(1033, 581, 129, 30);
		btnExcluir.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-excluir-32.png")));
		btnExcluir.setBackground(Color.WHITE);
		contentPane.add(btnExcluir);
		
//		JButton btnEditar = new JButton("");
//		btnEditar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnEditar.setBounds(1062, 455, 55, 55);
//		btnEditar.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-editar-32.png")));
//		btnEditar.setBackground(Color.WHITE);
//		contentPane.add(btnEditar);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(22, 284, 69, 21);
		lblPesquisar.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPesquisar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Clique para Salvar");
		btnSalvar.setBounds(1032, 622, 129, 30);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cadastrarBalancete();
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-salvar-32.png")));
		btnSalvar.setBackground(Color.WHITE);
		contentPane.add(btnSalvar);
		tableLancamentos.getColumnModel().getColumn(0).setPreferredWidth(33);
		tableLancamentos.getColumnModel().getColumn(1).setPreferredWidth(57);
		tableLancamentos.getColumnModel().getColumn(2).setPreferredWidth(35);
		tableLancamentos.getColumnModel().getColumn(5).setPreferredWidth(276);
		
		
		
		JLabel lblDataBase_1 = new JLabel("Data Base:");
		lblDataBase_1.setBounds(620, 11, 103, 23);
		lblDataBase_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblDataBase_1);
		
		btnImportLancamento = new JButton("");
		btnImportLancamento.setToolTipText("Clique para importar um balancete em .xls");
		btnImportLancamento.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/importIcon.png")));
		btnImportLancamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.importarLancamento();
			}
		});
		btnImportLancamento.setBackground(Color.WHITE);
		btnImportLancamento.setBounds(1033, 192, 55, 55);
		contentPane.add(btnImportLancamento);

		
		yearChooserDataBase = new JYearChooser();
		yearChooserDataBase.setBounds(712, 11, 47, 20);
		yearChooserDataBase.getSpinner().setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(yearChooserDataBase);
		control = new ControllerBalancete(txtRefPt, txtNivel, txtCEstruturado, txtCodReduzido, txtDescr, 
				txtSaldoInicial, txtCredito, txtDebito, lblNomeUsuario, lblNomeEmpresa, dataFinal, 
				dataInicial, cbNatuConta, tableLancamentos, tfPesquisaLancamento, cbPesquisa, 
				lblResultadoSaldoFinal,btnEmpresa,yearChooserDataBase,btnImportLancamento,this,usuario);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lancamentoSelecionado = control.lancamentoSelecionadoTable();
				if(lancamentoSelecionado!=null) {
					control.popularCamposLancamento(lancamentoSelecionado);
				}else {
					JOptionPane.showMessageDialog(null, "Selecione uma conta!");
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/empresa/icons8-editar-32.png")));
		btnEditar.setToolTipText("Selecione um lançamento para excluir");
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setBounds(1033, 540, 129, 30);
		contentPane.add(btnEditar);
		
		JButton btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.limparCamposLancamento();
			}
		});
		btnCancel.setIcon(new ImageIcon(TelaBalancete.class.getResource("/icons/icons8-cancel-50.png")));
		btnCancel.setToolTipText("Adicione um novo lançamento se houver");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(1098, 192, 55, 55);
		contentPane.add(btnCancel);
		
	}
	public JLabel getLblNomeUsuario() {
		return lblNomeUsuario;
	}

	public JLabel getLblEmpresaBal() {
		return lblNomeEmpresa;
	}
	public JTextField getTxtRefPt() {
		return txtRefPt;
	}
	public JTextField getTxtNivel() {
		return txtNivel;
	}
	public JTextField getTxtCEstruturado() {
		return txtCEstruturado;
	}
	public JTextField getTxtCodReduzido() {
		return txtCodReduzido;
	}
	public JTextField getTxtDescr() {
		return txtDescr;
	}
	public JTextField getTxtSaldoInicial() {
		return txtSaldoInicial;
	}
	public JTextField getTxtDebito() {
		return txtDebito;
	}
	public JTextField getTxtCredito() {
		return txtCredito;
	}
	public JDateChooser getDataFinal() {
		return dataFinal;
	}
	public JDateChooser getDataInicial() {
		return dataInicial;
	}
	public JComboBox getCbNatuConta() {
		return cbNatuConta;
	}
	public JTable getTableLancamentos() {
		return tableLancamentos;
	}
	public JTextField getTfPesquisaLancamento() {
		return tfPesquisaLancamento;
	}
	public JComboBox getCbPesquisa() {
		return cbPesquisa;
	}
	public JLabel getLblResultadoSaldoFinal() {
		return lblResultadoSaldoFinal;
	}
	public JButton getBtnEmpresa() {
		return btnEmpresa;
	}
	public JYearChooser getYearChooserDataBase() {
		return yearChooserDataBase;
	}

	public ControllerBalancete getControl() {
		return control;
	}

	public void setControl(ControllerBalancete control) {
		this.control = control;
	}
}
