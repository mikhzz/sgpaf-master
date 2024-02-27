package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerBalancete;
import controller.ControllerBalanceteFolhaMestra;
import controller.ControllerCapa;
import controller.ControllerFolha;
import controller.ControllerFolhaMestra;
import controller.ControllerProced;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;
import model.util.JTextFieldLimit;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class TelaFolhaMestra1 extends JFrame {

	private JPanel contentPane;
	private JLabel lblClienteCapa;
	private JLabel lblDataBaseCapa;
	private JLabel lblAuditorAssistenteCapa;
	private JCheckBox checkBoxRevisadoCapa;
	private JButton btnSalvarConclusaoCapa;
	private Lancamento lancamentoSelecionado = null;
	private TelaValorDiferenca telaValorDiferenca;


	private JLabel lblEmpresaAB;
	private JLabel lblPeriodoAB;
	private JLabel lblDataBaseAB;
	private JLabel lblExecutorAB;

	private JCheckBox chbNaoInspecaoDocumental;
	private JCheckBox chbNaoContagemFisica;
	private JCheckBox chbNaoAcessoOnlie;
	private JCheckBox chbNaoRecalculo;
	private JCheckBox chbNaoEntrevistas;
	private JCheckBox chbNaoAplicacaoQuestionario;
	private JCheckBox chbNaoCruzamentoSaldos;
	private JCheckBox chbCircularizacao;
	private JCheckBox chbValidaoAmostra;
	private JCheckBox chbLiquidacaoSubsequente;
	private JTextArea txtAreaConclusaoProcAB;

	private JLabel lblValorAB;
	private JLabel lblTotalSaldoAnteriorFooterAB;
	private JLabel lblTotalDebitoFooterAB;
	private JLabel lblTotalCreditoFooterAB;
	private JLabel lblTotalSaldoFinalFooterAB;
	private JLabel lblTotalSaldoValidadoFooterAB;
	private JLabel lblTotalDiferencaFooterAB;
	private JButton btnSalvarFooterAB;
	private JLabel lblReferenciaTopoAG;

	private ControllerFolhaMestra controlFolhaMestra;
	private ControllerCapa controlCapa;

	private ControllerProced controlProc;
	private ControllerFolha controlFolha;

	private ControllerBalanceteFolhaMestra balanceteControl;
	private Empresa empresa;
	private Usuario executor;
	private Balancete balancete;
	private JComboBox cbSocioDiretor;
	private JTable tableBalanceteLan;
	private JTable tableFolhaAB;
	private JTextArea textAreaConclusaoCapa;
	private JLabel lblEmpresaBalancete;
	private JLabel lblDataBaseBalancete;
	private JLabel lblAuditorBalancete;
	private JLabel lblPeriodoBalancete;
	private JComboBox cbRevisor;
	private JLabel lblUlitimaAlteracaoAuditor;
	private JLabel lblSaldoAnteriorTotal;
	private JLabel lblDebitoTotal;
	private JLabel lblSaldoValidadoTotal;
	private JLabel lblSaldoFinalTotal;
	private JLabel lblCreditoTotal;
	private JComboBox cbREFFolha;
	private JComboBox cbREFProc;
	private JLabel lblDiferencaTotal;
	private JLabel lblLogo;
	private JLabel lblCliente;
	private JLabel lblSocioDrt;
	private JLabel lblDataBase;
	private JLabel lblAuditoresEAssistentes;
	private JLabel lblrevisor;
	private JPanel panelProcedimentoAB_1;
	private JLabel lblLogoFundoAB;
	private JLabel lblProcAuditoria;
	private JLabel lblDescrioDoProcedimento;
	private JLabel lblOrientacoes;
	private JLabel lblInspeçãoDocumental;
	private JLabel lblContagemFisica;
	private JLabel lblAcessoOnline;
	private JLabel lblRecalculo;
	private JLabel lblEntrevistas;
	private JLabel lblAplicacaoQuestionario;
	private JLabel lblNewLabel;
	private JLabel lblCruzamentoDeSaldos;
	private JLabel lblDataRevisado;
	private DefaultTableModel modelTable;
	private DefaultTableModel modelTable2;
	private JLabel lblUsuarioUltimaAlteracao;
	private JLabel lblRecebeDataUltimaAltera;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private TelaRecomendacoes recomendacoes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaFolhaMestra1(Empresa pEmpresa, Balancete pBalancete, Usuario pExecutor) {
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));
		UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));  // ELIMINA AS BORDAS DO LOOK AND FEEL E PERMITE A ESTILIZAÇÃO POSTERIOR  
		UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true);  // ELIMINA AS BORDAS DO LOOK AND FEEL E PERMITE A ESTILIZAÇÃO POSTERIOR 
		setResizable(false);
		this.empresa = pEmpresa;
		this.executor = pExecutor;
		this.balancete = pBalancete;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getControlFolhaMestra().popularComboRef();
				getControllerProcAB().popularCampos();
				controlFolha.pesquisaRef();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				getControlFolhaMestra().popularFolhaMestra();
			}
			
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaFolhaMestra1.class.getResource("/icons/vga/Logo VGA.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1336, 729);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setMinimumSize(new Dimension(800, 600));
		tabbedPane.setBounds(10, 0, 1271, 675);
		contentPane.add(tabbedPane);

		JPanel panelCapa = new JPanel();
		panelCapa.setBorder(new LineBorder(Color.BLACK));
		panelCapa.setMaximumSize(new Dimension(0, 0));
		panelCapa.setMinimumSize(new Dimension(0, 0));
		panelCapa.setBackground(Color.WHITE);
		tabbedPane.addTab("CAPA", null, panelCapa, null);

		jlabelFixaCapa(panelCapa);

		checkBoxRevisadoCapa = new JCheckBox("Revisado");
		checkBoxRevisadoCapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControlcapa().pegarDataClickRevisadoTela();
			}
		});
		checkBoxRevisadoCapa.setBounds(578, 149, 97, 23);
		checkBoxRevisadoCapa.setFont(new Font("Calibri", Font.PLAIN, 14));
		checkBoxRevisadoCapa.setBackground(Color.WHITE);

		JLabel label_7 = new JLabel("Conclus\u00E3o:");
		label_7.setBounds(21, 273, 147, 30);
		label_7.setFont(new Font("Calibri", Font.PLAIN, 14));

		btnSalvarConclusaoCapa = new JButton("Salvar");
		btnSalvarConclusaoCapa.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/empresa/icons8-salvar-32.png")));
		btnSalvarConclusaoCapa.setBounds(63, 554, 129, 30);
		btnSalvarConclusaoCapa.setMaximumSize(new Dimension(800, 600));
		btnSalvarConclusaoCapa.setMinimumSize(new Dimension(800, 600));
		btnSalvarConclusaoCapa.setBackground(Color.WHITE);
		btnSalvarConclusaoCapa.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnSalvarConclusaoCapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int confirma = controlFolhaMestra.salvarAction();
				switch (confirma) {
				case 0:

					break;
				case 1:
					dispose();
					break;
				case 2:

					break;

				default:
					dispose();
					break;
				}
			}
		});

		lblClienteCapa = new JLabel("");
		lblClienteCapa.setBounds(21, 114, 502, 23);
		lblClienteCapa.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDataBaseCapa = new JLabel("");
		lblDataBaseCapa.setBounds(21, 166, 166, 23);
		lblDataBaseCapa.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblAuditorAssistenteCapa = new JLabel(" ");
		lblAuditorAssistenteCapa.setBounds(21, 215, 483, 23);
		lblAuditorAssistenteCapa.setFont(new Font("Calibri", Font.PLAIN, 14));

		cbSocioDiretor = new JComboBox();
		cbSocioDiretor.setBounds(578, 39, 166, 27);
		cbSocioDiretor.setBackground(Color.WHITE);
		cbSocioDiretor.setFont(new Font("Calibri", Font.PLAIN, 14));


		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 296, 1203, 247);

		textAreaConclusaoCapa = new JTextArea();
		textAreaConclusaoCapa.setDocument(new JTextFieldLimit(1024));
		scrollPane_2.setViewportView(textAreaConclusaoCapa);

		cbRevisor = new JComboBox();
		cbRevisor.setEditable(false);
		cbRevisor.setBackground(Color.WHITE);
		cbRevisor.setBounds(581, 107, 166, 27);
		cbRevisor.setFont(new Font("Calibri", Font.PLAIN, 14));
		

		JLabel lblltimaAlteraoFeita = new JLabel("Última Alteração:\r\n");
		lblltimaAlteraoFeita.setBounds(829, 11, 175, 30);
		lblltimaAlteraoFeita.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblUlitimaAlteracaoAuditor = new JLabel(" ");
		lblUlitimaAlteracaoAuditor.setBounds(829, 75, 408, 23);
		lblUlitimaAlteracaoAuditor.setFont(new Font("Calibri", Font.PLAIN, 14));

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 262, 1228, 11);
		panelCapa.setLayout(null);
		panelCapa.add(lblLogo);
		panelCapa.add(lblClienteCapa);
		panelCapa.add(lblCliente);
		panelCapa.add(lblSocioDrt);
		panelCapa.add(cbSocioDiretor);
		panelCapa.add(lblltimaAlteraoFeita);
		panelCapa.add(lblUlitimaAlteracaoAuditor);
		panelCapa.add(lblDataBase);
		panelCapa.add(lblDataBaseCapa);
		panelCapa.add(lblAuditoresEAssistentes);
		panelCapa.add(lblAuditorAssistenteCapa);
		panelCapa.add(lblrevisor);
		panelCapa.add(checkBoxRevisadoCapa);
		panelCapa.add(cbRevisor);
		panelCapa.add(scrollPane_2);
		panelCapa.add(separator);
		panelCapa.add(label_7);
		panelCapa.add(btnSalvarConclusaoCapa);
		
		JLabel lblRevisadoEm = new JLabel("Revisado em:\r\n");
		lblRevisadoEm.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRevisadoEm.setBounds(578, 170, 175, 30);
		panelCapa.add(lblRevisadoEm);
		
		lblDataRevisado = new JLabel(" ");
		lblDataRevisado.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDataRevisado.setBounds(578, 196, 208, 30);
		panelCapa.add(lblDataRevisado);
		
		JLabel lblDataCapaUltimaAlte = new JLabel("Data:");
		lblDataCapaUltimaAlte.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDataCapaUltimaAlte.setBounds(829, 136, 175, 23);
		panelCapa.add(lblDataCapaUltimaAlte);
		
		lblRecebeDataUltimaAltera = new JLabel("");
		lblRecebeDataUltimaAltera.setBounds(829, 160, 210, 23);
		panelCapa.add(lblRecebeDataUltimaAltera);
		
		JLabel labelUsrUltimo = new JLabel("Executor:");
		labelUsrUltimo.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelUsrUltimo.setBounds(829, 52, 97, 23);
		panelCapa.add(labelUsrUltimo);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(545, 18, 23, 222);
		panelCapa.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(796, 16, 23, 222);
		panelCapa.add(separator_3);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
		});
		btnCancelar.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/empresa/icons8-cancelar-26.png")));
		btnCancelar.setToolTipText("Cancelar a operação!");
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(219, 554, 129, 30);
		panelCapa.add(btnCancelar);

		JPanel panelBalancete = new JPanel();
		panelBalancete.setBorder(new LineBorder(Color.BLACK));
		panelBalancete.setMaximumSize(new Dimension(0, 0));
		panelBalancete.setMinimumSize(new Dimension(0, 0));
		panelBalancete.setBackground(Color.WHITE);
		tabbedPane.addTab("BALANCETE", null, panelBalancete, null);

		JLabel lblLogoVga = new JLabel("");
		lblLogoVga.setBounds(10, 0, 294, 107);
		lblLogoVga.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/vga/vga_fundo_folha.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 210, 1150, 356);
		scrollPane.setBackground(Color.WHITE);

		modelTable = new DefaultTableModel(new Object[] {
				"Ref.", "Natureza", "N\u00EDvel", "C\u00F3d. Estru.", "C\u00F3d. Red.",
				"Descri\u00E7\u00E3o", "Saldo Anterior", "D\u00E9bito", "Cr\u00E9dito", "Saldo Final" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };  
		
		
		
		
		tableBalanceteLan = new JTable();
		tableBalanceteLan.setEnabled(true);
		tableBalanceteLan.setModel(modelTable);
		tableBalanceteLan.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableBalanceteLan);
		

		JLabel label_17 = new JLabel("Cliente:");
		label_17.setBounds(10, 97, 73, 18);
		label_17.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblEmpresaBalancete = new JLabel("");
		lblEmpresaBalancete.setBounds(11, 109, 499, 18);
		lblEmpresaBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel label_21 = new JLabel("Data-Base:");
		label_21.setBounds(977, 94, 73, 18);
		label_21.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDataBaseBalancete = new JLabel("");
		lblDataBaseBalancete.setBounds(977, 109, 73, 18);
		lblDataBaseBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblAuditor = new JLabel("Auditor:");
		lblAuditor.setBounds(10, 155, 59, 18);
		lblAuditor.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblAuditorBalancete = new JLabel("");
		lblAuditorBalancete.setBounds(10, 169, 486, 18);
		lblAuditorBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblPeriodoBalancete = new JLabel("");
		lblPeriodoBalancete.setBounds(513, 111, 331, 18);
		lblPeriodoBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setBounds(513, 97, 59, 18);
		lblPeriodo.setFont(new Font("Calibri", Font.PLAIN, 14));
		panelBalancete.setLayout(null);
		panelBalancete.add(lblLogoVga);
		panelBalancete.add(label_17);
		panelBalancete.add(lblEmpresaBalancete);
		panelBalancete.add(lblPeriodo);
		panelBalancete.add(lblPeriodoBalancete);
		panelBalancete.add(label_21);
		panelBalancete.add(lblDataBaseBalancete);
		panelBalancete.add(lblAuditorBalancete);
		panelBalancete.add(lblAuditor);
		panelBalancete.add(scrollPane);
		tableBalanceteLan.getColumnModel().getColumn(0).setPreferredWidth(35);
		tableBalanceteLan.getColumnModel().getColumn(1).setPreferredWidth(80);
		tableBalanceteLan.getColumnModel().getColumn(2).setPreferredWidth(41);
		tableBalanceteLan.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableBalanceteLan.getColumnModel().getColumn(5).setPreferredWidth(501);

		JPanel panelFolhaAB = new JPanel();
		panelFolhaAB.setBorder(new LineBorder(Color.BLACK));
		panelFolhaAB.setMaximumSize(new Dimension(0, 0));
		panelFolhaAB.setMinimumSize(new Dimension(0, 0));
		panelFolhaAB.setBackground(Color.WHITE);
		tabbedPane.addTab("FOLHA", null, panelFolhaAB, null);
		folhaAB(panelFolhaAB);

		contruirFolhaAB(panelFolhaAB);

		JButton btnInserirDiferença = new JButton("");
		btnInserirDiferença.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/diversos/icons8-caixa-de-seleção-marcada-32.png")));
		btnInserirDiferença.setToolTipText("Clique para validar o saldo");
		btnInserirDiferença.setBackground(Color.WHITE);
		btnInserirDiferença.setBounds(1193, 135, 45, 39);
		btnInserirDiferença.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancamentoSelecionado = controlFolha.getLancamentoSelecionadoTable();
				if (lancamentoSelecionado != null) {
					telaValorDiferenca = new TelaValorDiferenca(lancamentoSelecionado);
					telaValorDiferenca.getControlSaldo().popularTela(lancamentoSelecionado);
					telaValorDiferenca.setLancamento(lancamentoSelecionado);
					telaValorDiferenca.show();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um lançamento", "Ops!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				lancamentoSelecionado = null;
			}
		});

		lblSaldoAnteriorTotal = new JLabel("");
		lblSaldoAnteriorTotal.setBounds(26, 609, 192, 18);
		lblSaldoAnteriorTotal.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDebitoTotal = new JLabel("");
		lblDebitoTotal.setBounds(228, 609, 192, 18);
		lblDebitoTotal.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblCreditoTotal = new JLabel("");
		lblCreditoTotal.setBounds(430, 609, 192, 18);
		lblCreditoTotal.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblSaldoFinalTotal = new JLabel("");
		lblSaldoFinalTotal.setBounds(606, 609, 192, 18);
		lblSaldoFinalTotal.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblSaldoValidadoTotal = new JLabel("");
		lblSaldoValidadoTotal.setBounds(808, 609, 192, 18);
		lblSaldoValidadoTotal.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDiferencaTotal = new JLabel("");
		lblDiferencaTotal.setBounds(1027, 609, 192, 18);
		lblDiferencaTotal.setFont(new Font("Calibri", Font.PLAIN, 14));
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(13, 191, 1227, 354);
		scrollPane_3.setBackground(Color.WHITE);
		
		
		modelTable2 = new DefaultTableModel(new Object[] {
				"Ref.", "Natureza", "N\u00EDvel", "C\u00F3d. Estru.", "C\u00F3d. Red.",
				"Descri\u00E7\u00E3o", "Saldo Anterior", "D\u00E9bito", "Cr\u00E9dito", "Saldo Final", "AH",
				"AV", "S.Validado", "Diferença" , "Divergência"  }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		
		
		tableFolhaAB = new JTable();
		tableFolhaAB.setEnabled(true);
		tableFolhaAB.setModel(modelTable2);
		tableFolhaAB.setBackground(Color.WHITE);
		scrollPane_3.setViewportView(tableFolhaAB);
		

		lblValorAB = new JLabel(
				"                                                                                                                                                                                                   TOTAL");
		lblValorAB.setBounds(13, 556, 1227, 30);
		lblValorAB.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorAB.setOpaque(true);
		lblValorAB.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		lblTotalSaldoAnteriorFooterAB = new JLabel("Saldo Anterior");
		lblTotalSaldoAnteriorFooterAB.setBounds(26, 597, 106, 14);
		lblTotalSaldoAnteriorFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblTotalDebitoFooterAB = new JLabel("Débito");
		lblTotalDebitoFooterAB.setBounds(228, 597, 85, 14);
		lblTotalDebitoFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblTotalCreditoFooterAB = new JLabel("Crédito");
		lblTotalCreditoFooterAB.setBounds(430, 597, 85, 14);
		lblTotalCreditoFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblTotalSaldoFinalFooterAB = new JLabel("Saldo Final");
		lblTotalSaldoFinalFooterAB.setBounds(606, 597, 85, 14);
		lblTotalSaldoFinalFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblTotalSaldoValidadoFooterAB = new JLabel("Saldo Validado");
		lblTotalSaldoValidadoFooterAB.setBounds(808, 597, 85, 14);
		lblTotalSaldoValidadoFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblTotalDiferencaFooterAB = new JLabel("Diferença");
		lblTotalDiferencaFooterAB.setBounds(1027, 597, 85, 14);
		lblTotalDiferencaFooterAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		btnSalvarFooterAB = new JButton("Salvar");
		btnSalvarFooterAB.setBounds(1183, 855, 89, 23);
		btnSalvarFooterAB.setBackground(SystemColor.activeCaption);
		JLabel lblLogoVGA = new JLabel("");
		lblLogoVGA.setBounds(10, 0, 294, 107);
		lblLogoVGA.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/vga/vga_fundo_folha.png")));

		JLabel lblEmpresa_1 = new JLabel("Empresa:");
		lblEmpresa_1.setBounds(26, 97, 73, 18);
		lblEmpresa_1.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblEmpresaAB = new JLabel("");
		lblEmpresaAB.setBounds(25, 112, 499, 18);
		lblEmpresaAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblPerodo = new JLabel("Período:");
		lblPerodo.setBounds(544, 97, 73, 18);
		lblPerodo.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblPeriodoAB = new JLabel("");
		lblPeriodoAB.setBounds(544, 112, 285, 18);
		lblPeriodoAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblDataBase_2 = new JLabel("Data Base:");
		lblDataBase_2.setBounds(25, 135, 73, 18);
		lblDataBase_2.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDataBaseAB = new JLabel("");
		lblDataBaseAB.setBounds(26, 152, 96, 18);
		lblDataBaseAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblExecutor_1 = new JLabel("Executor:");
		lblExecutor_1.setBounds(544, 135, 73, 18);
		lblExecutor_1.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblExecutorAB = new JLabel("");
		lblExecutorAB.setBounds(544, 152, 499, 18);
		lblExecutorAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		cbREFFolha = new JComboBox();
		cbREFFolha.setBackground(Color.WHITE);
		cbREFFolha.setBounds(342, 9, 45, 30);
		cbREFFolha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlFolha.pesquisaRef();
			}
		});
		cbREFFolha.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblNewLabel_1 = new JLabel("REF.");
		lblNewLabel_1.setBounds(304, 14, 39, 14);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(3, 181, 1235, 19);
		panelFolhaAB.setLayout(null);
		panelFolhaAB.add(lblEmpresa_1);
		panelFolhaAB.add(lblNewLabel_1);
		panelFolhaAB.add(lblLogoVGA);
		panelFolhaAB.add(cbREFFolha);
		panelFolhaAB.add(lblEmpresaAB);
		panelFolhaAB.add(lblDataBase_2);
		panelFolhaAB.add(lblDataBaseAB);
		panelFolhaAB.add(lblPerodo);
		panelFolhaAB.add(lblPeriodoAB);
		panelFolhaAB.add(lblExecutor_1);
		panelFolhaAB.add(lblExecutorAB);
		panelFolhaAB.add(btnInserirDiferença);
		panelFolhaAB.add(separator_1);
		panelFolhaAB.add(scrollPane_3);
		panelFolhaAB.add(lblValorAB);
		panelFolhaAB.add(lblTotalSaldoAnteriorFooterAB);
		panelFolhaAB.add(lblSaldoAnteriorTotal);
		panelFolhaAB.add(lblTotalDebitoFooterAB);
		panelFolhaAB.add(lblDebitoTotal);
		panelFolhaAB.add(lblTotalSaldoFinalFooterAB);
		panelFolhaAB.add(lblSaldoFinalTotal);
		panelFolhaAB.add(lblDiferencaTotal);
		panelFolhaAB.add(lblTotalDiferencaFooterAB);
		panelFolhaAB.add(lblCreditoTotal);
		panelFolhaAB.add(lblTotalCreditoFooterAB);
		panelFolhaAB.add(lblTotalSaldoValidadoFooterAB);
		panelFolhaAB.add(lblSaldoValidadoTotal);
		panelFolhaAB.add(btnSalvarFooterAB);
		
		JLabel lblUltimaAlteracaoPorFolha = new JLabel("Última Alteração Feita Por:\r\n");
		lblUltimaAlteracaoPorFolha.setBackground(Color.WHITE);
		lblUltimaAlteracaoPorFolha.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUltimaAlteracaoPorFolha.setBounds(868, 91, 175, 30);
		panelFolhaAB.add(lblUltimaAlteracaoPorFolha);
		
		lblUsuarioUltimaAlteracao = new JLabel("");
		lblUsuarioUltimaAlteracao.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsuarioUltimaAlteracao.setBounds(866, 123, 252, 18);
		panelFolhaAB.add(lblUsuarioUltimaAlteracao);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlFolha.pesquisaRef();
			}
		});
		button.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/atualizarPreto32.png")));
		button.setToolTipText("Clique para validar o saldo");
		button.setBackground(Color.WHITE);
		button.setBounds(1133, 135, 45, 39);
		panelFolhaAB.add(button);
		
		JButton btnRecomendacoes = new JButton("");
		btnRecomendacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lancamentoSelecionado = controlFolha.getLancamentoSelecionadoTable();
				if (lancamentoSelecionado != null) {
					recomendacoes = new TelaRecomendacoes(lancamentoSelecionado);
					recomendacoes.show();
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um lançamento", "Ops!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				lancamentoSelecionado = null;
				
				
			}
		});
		btnRecomendacoes.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/diversos/icons8-sugestão2-32.png")));
		btnRecomendacoes.setToolTipText("clique e consulte recomendações");
		btnRecomendacoes.setBackground(Color.WHITE);
		btnRecomendacoes.setBounds(1193, 91, 45, 39);
		panelFolhaAB.add(btnRecomendacoes);

		panelProcedimentoAB_1 = new JPanel();
		panelProcedimentoAB_1.setBorder(new LineBorder(Color.BLACK));
		panelProcedimentoAB_1.setMinimumSize(new Dimension(0, 0));
		panelProcedimentoAB_1.setMaximumSize(new Dimension(0, 0));
		panelProcedimentoAB_1.setBackground(Color.WHITE);
		tabbedPane.addTab("PROCEDIMENTO", null, panelProcedimentoAB_1, null);

		construirPanelProcAB(panelProcedimentoAB_1);
		jLabeFixalProcAB(panelProcedimentoAB_1);
		chbAB(panelProcedimentoAB_1);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/empresa/icons8-salvar-32.png")));
		btnSalvar.setBounds(617, 566, 129, 30);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().btnSalvarAction();
			}
		});

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(617, 325, 372, 230);

		txtAreaConclusaoProcAB = new JTextArea();
		txtAreaConclusaoProcAB.setDocument(new JTextFieldLimit(1024));
		scrollPane_5.setViewportView(txtAreaConclusaoProcAB);
		txtAreaConclusaoProcAB.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1 = new JLabel("REF.");
		lblNewLabel_1_1.setBounds(304, 14, 39, 14);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));

		cbREFProc = new JComboBox();
		cbREFProc.setBounds(342, 9, 45, 30);
		cbREFProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().popularCampos();
			}
		});
		cbREFProc.setFont(new Font("Calibri", Font.PLAIN, 14));

		chbValidaoAmostra = new JCheckBox("Não");
		chbValidaoAmostra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbValidaoAmostra);
				getControllerProcAB().salvarCheckAction("VALIDAÇÃO DA AMOSTRA", chbValidaoAmostra);
			}
		});
		chbValidaoAmostra.setBounds(375, 544, 97, 23);
		chbValidaoAmostra.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbValidaoAmostra.setBackground(Color.WHITE);

		JLabel lblValidaoDaAmostra = new JLabel("VALIDAÇÃO DA AMOSTRA");
		lblValidaoDaAmostra.setBounds(25, 542, 340, 26);
		lblValidaoDaAmostra.setFont(new Font("Calibri", Font.PLAIN, 14));

		JLabel lblLiquidaoSubsequente = new JLabel("LIQUIDAÇÃO SUBSEQUENTE");
		lblLiquidaoSubsequente.setBounds(25, 579, 340, 26);
		lblLiquidaoSubsequente.setFont(new Font("Calibri", Font.PLAIN, 14));

		chbLiquidacaoSubsequente = new JCheckBox("Não");
		chbLiquidacaoSubsequente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControllerProcAB().eventoCheckBox(chbLiquidacaoSubsequente);
				getControllerProcAB().salvarCheckAction("LIQUIDAÇÃO SUBSEQUENTE", chbLiquidacaoSubsequente);
			}
		});
		chbLiquidacaoSubsequente.setBounds(375, 571, 49, 25);
		chbLiquidacaoSubsequente.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbLiquidacaoSubsequente.setBackground(Color.WHITE);

		JLabel lblCircularizacao = new JLabel("CIRCULARIZAÇÃO");
		lblCircularizacao.setBounds(25, 318, 221, 31);
		lblCircularizacao.setFont(new Font("Calibri", Font.PLAIN, 14));

		chbCircularizacao = new JCheckBox("Não");
		chbCircularizacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getControllerProcAB().eventoCheckBox(chbCircularizacao);
				getControllerProcAB().salvarCheckAction("CIRCULARIZAÇÃO", chbCircularizacao);
			}
		});
		chbCircularizacao.setBounds(375, 322, 97, 23);
		chbCircularizacao.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbCircularizacao.setBackground(Color.WHITE);
		panelProcedimentoAB_1.setLayout(null);
		panelProcedimentoAB_1.add(cbREFProc);
		panelProcedimentoAB_1.add(lblNewLabel_1_1);
		panelProcedimentoAB_1.add(lblLogoFundoAB);
		panelProcedimentoAB_1.add(lblProcAuditoria);
		panelProcedimentoAB_1.add(lblDescrioDoProcedimento);
		panelProcedimentoAB_1.add(lblOrientacoes);
		panelProcedimentoAB_1.add(lblInspeçãoDocumental);
		panelProcedimentoAB_1.add(lblCircularizacao);
		panelProcedimentoAB_1.add(lblContagemFisica);
		panelProcedimentoAB_1.add(lblAcessoOnline);
		panelProcedimentoAB_1.add(lblRecalculo);
		panelProcedimentoAB_1.add(lblEntrevistas);
		panelProcedimentoAB_1.add(lblAplicacaoQuestionario);
		panelProcedimentoAB_1.add(lblNewLabel);
		panelProcedimentoAB_1.add(chbNaoInspecaoDocumental);
		panelProcedimentoAB_1.add(chbNaoContagemFisica);
		panelProcedimentoAB_1.add(chbCircularizacao);
		panelProcedimentoAB_1.add(chbNaoAcessoOnlie);
		panelProcedimentoAB_1.add(chbNaoRecalculo);
		panelProcedimentoAB_1.add(chbNaoEntrevistas);
		panelProcedimentoAB_1.add(chbNaoAplicacaoQuestionario);
		panelProcedimentoAB_1.add(scrollPane_5);
		panelProcedimentoAB_1.add(lblCruzamentoDeSaldos);
		panelProcedimentoAB_1.add(lblValidaoDaAmostra);
		panelProcedimentoAB_1.add(chbNaoCruzamentoSaldos);
		panelProcedimentoAB_1.add(chbValidaoAmostra);
		panelProcedimentoAB_1.add(btnSalvar);
		panelProcedimentoAB_1.add(lblLiquidaoSubsequente);
		panelProcedimentoAB_1.add(chbLiquidacaoSubsequente);

		JLabel lblExecutado = new JLabel("Executado?");
		lblExecutado.setBounds(375, 202, 71, 14);
		panelProcedimentoAB_1.add(lblExecutado);

		contruirFolhaEProcAE(tabbedPane);

		JLabel label_489 = new JLabel("Conclusões: ");
		label_489.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_489.setBounds(813, 337, 89, 23);

		JLabel label_368 = new JLabel("DESCRIÇÃO DO PROCEDIMENTO:\r\n\t");
		label_368.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_368.setBounds(10, 335, 222, 37);
		;
	}

	public void contruirFolhaAB(JPanel panelFolhaAB) {
		ControllerBalancete controlBalancete = new ControllerBalancete();

	}

	public ControllerFolhaMestra getControlFolhaMestra() {
		if (controlFolhaMestra != null) {
			return controlFolhaMestra;
		} else {
			controlFolhaMestra = new ControllerFolhaMestra(getControllerProcAB(), getBalanceteControl(),
					getControlcapa(), getControllerFolhaAB(), executor, empresa, balancete,cbREFFolha,cbREFProc);
		}
		return controlFolhaMestra;
	}

	public ControllerProced getControllerProcAB() {
		if (controlProc != null) {
			return controlProc;
		} else {
			controlProc = new ControllerProced(chbNaoInspecaoDocumental,chbNaoContagemFisica,chbNaoAcessoOnlie,chbNaoRecalculo,chbNaoEntrevistas,
					chbNaoAplicacaoQuestionario,chbNaoCruzamentoSaldos,chbCircularizacao,chbValidaoAmostra,chbLiquidacaoSubsequente, 
					txtAreaConclusaoProcAB,cbREFProc,lblUsuarioUltimaAlteracao);
		}
		return controlProc;
	}

	public ControllerFolha getControllerFolhaAB() {
		if (controlFolha != null) {
			return controlFolha;
		} else {
			controlFolha = new ControllerFolha(tableFolhaAB, lblEmpresaAB, lblDataBaseAB, lblPeriodoAB, lblExecutorAB,
					cbREFFolha, lblSaldoAnteriorTotal, lblDebitoTotal, lblSaldoValidadoTotal, lblSaldoFinalTotal,
					lblCreditoTotal, lblDiferencaTotal,lblUsuarioUltimaAlteracao);
		}
		return controlFolha;
	}

	public ControllerCapa getControlcapa() {
		if (controlCapa != null) {
			return controlCapa;
		} else {
			controlCapa = new ControllerCapa(lblClienteCapa, lblDataBaseCapa, cbSocioDiretor, 
					lblAuditorAssistenteCapa, cbRevisor, checkBoxRevisadoCapa, textAreaConclusaoCapa,
					lblUlitimaAlteracaoAuditor, lblRecebeDataUltimaAltera,lblDataRevisado);
		}
		return controlCapa;
	}

	public ControllerBalanceteFolhaMestra getBalanceteControl() {
		if (balanceteControl != null) {
			return balanceteControl;
		} else {
			balanceteControl = new ControllerBalanceteFolhaMestra(lblEmpresaBalancete, lblDataBaseBalancete,   // QUANDO RETIRADA A LABEL DO SALDO TOTAL O METODO QUEBRA POIS ESTA ESPERANDO ELE COMO PARAMETRO //
					lblAuditorBalancete, lblPeriodoBalancete, tableBalanceteLan);
		}
		return balanceteControl;
	}

	private void chbAB(JPanel panelProcedimentoAB) {
		chbNaoContagemFisica = new JCheckBox("N\u00E3o");
		chbNaoContagemFisica.setBounds(375, 280, 97, 23);
		chbNaoContagemFisica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoContagemFisica);
				getControllerProcAB().salvarCheckAction("CONTAGEM FÍSICA", chbNaoContagemFisica);
			}
		});
		chbNaoContagemFisica.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoContagemFisica.setBackground(Color.WHITE);

		chbNaoAcessoOnlie = new JCheckBox("N\u00E3o");
		chbNaoAcessoOnlie.setBounds(375, 360, 97, 23);
		chbNaoAcessoOnlie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoAcessoOnlie);
				getControllerProcAB().salvarCheckAction("ACESSO ONLINE", chbNaoAcessoOnlie);
			}
		});
		chbNaoAcessoOnlie.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoAcessoOnlie.setBackground(Color.WHITE);

		chbNaoRecalculo = new JCheckBox("N\u00E3o");
		chbNaoRecalculo.setBounds(375, 396, 97, 23);
		chbNaoRecalculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoRecalculo);
				getControllerProcAB().salvarCheckAction("RECÁLCULO", chbNaoRecalculo);
			}
		});
		chbNaoRecalculo.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoRecalculo.setBackground(Color.WHITE);

		chbNaoEntrevistas = new JCheckBox("N\u00E3o");
		chbNaoEntrevistas.setBounds(375, 433, 97, 23);
		chbNaoEntrevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoEntrevistas);
				getControllerProcAB().salvarCheckAction("ENTREVISTAS", chbNaoEntrevistas);
			}
		});
		chbNaoEntrevistas.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoEntrevistas.setBackground(Color.WHITE);

		chbNaoAplicacaoQuestionario = new JCheckBox("N\u00E3o");
		chbNaoAplicacaoQuestionario.setBounds(375, 468, 97, 23);
		chbNaoAplicacaoQuestionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoAplicacaoQuestionario);
				getControllerProcAB().salvarCheckAction("APLICAÇÃO DE QUESTIONÁRIOS DE CONTROLES INTERNOS", chbNaoAplicacaoQuestionario);
			}
		});
		chbNaoAplicacaoQuestionario.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoAplicacaoQuestionario.setBackground(Color.WHITE);

		chbNaoCruzamentoSaldos = new JCheckBox("N\u00E3o");
		chbNaoCruzamentoSaldos.setBounds(375, 507, 97, 23);
		chbNaoCruzamentoSaldos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoCruzamentoSaldos);
				getControllerProcAB().salvarCheckAction("CRUZAMENTO DE SALDOS DE RELÁTÓRIOS", chbNaoCruzamentoSaldos);
			}
		});
		chbNaoCruzamentoSaldos.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoCruzamentoSaldos.setBackground(Color.WHITE);
	}

	private void chbPG(JPanel panelProcedimentoPG) {
	}

	private void chbAC(Panel panelProcedimentoAC) {

	}

	private void jlabelFixaCapa(JPanel panelCapa) {
		lblLogo = new JLabel("");
		lblLogo.setBounds(10, 0, 294, 107);
		lblLogo.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/vga/vga_fundo_folha.png")));

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(21, 98, 73, 23);
		lblCliente.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblDataBase = new JLabel("Data-Base:");
		lblDataBase.setBounds(21, 148, 84, 23);
		lblDataBase.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblSocioDrt = new JLabel("S\u00F3cio Diretor:\r\n");
		lblSocioDrt.setBounds(578, 11, 97, 30);
		lblSocioDrt.setFont(new Font("Calibri", Font.PLAIN, 14));


		lblAuditoresEAssistentes = new JLabel("Usuário:");
		lblAuditoresEAssistentes.setBounds(21, 196, 155, 23);
		lblAuditoresEAssistentes.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblrevisor = new JLabel("Revisor:\r\n");
		lblrevisor.setBounds(581, 75, 73, 30);
		lblrevisor.setFont(new Font("Calibri", Font.PLAIN, 14));
	}

	private void jLabeFixalProcAB(JPanel panelProcedimentoAB) {

		lblOrientacoes = new JLabel("");
		lblOrientacoes.setBounds(25, 135, 641, 45);
		lblOrientacoes.setIcon(new ImageIcon(
				TelaFolhaMestra1.class.getResource("/icons/vga/orienta\u00E7\u00F5es para auditoria.png")));

		lblInspeçãoDocumental = new JLabel("INSPEÇÃO DOCUMENTAL\r\n\r\n");
		lblInspeçãoDocumental.setBounds(25, 239, 340, 26);
		lblInspeçãoDocumental.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblContagemFisica = new JLabel("CONTAGEM FÍSICA");
		lblContagemFisica.setBounds(25, 276, 340, 31);
		lblContagemFisica.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblAcessoOnline = new JLabel("ACESSO ONLINE");
		lblAcessoOnline.setBounds(25, 360, 340, 23);
		lblAcessoOnline.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblRecalculo = new JLabel("RECÁLCULO");
		lblRecalculo.setBounds(25, 394, 340, 26);
		lblRecalculo.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblEntrevistas = new JLabel("ENTREVISTAS");
		lblEntrevistas.setBounds(25, 431, 340, 26);
		lblEntrevistas.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblCruzamentoDeSaldos = new JLabel("CRUZAMENTO DE SALDOS DE RELÁTÓRIOS");
		lblCruzamentoDeSaldos.setBounds(25, 505, 340, 26);
		lblCruzamentoDeSaldos.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblAplicacaoQuestionario = new JLabel("APLICAÇÃO DE QUESTIONÁRIOS DE CONTROLES INTERNOS");
		lblAplicacaoQuestionario.setBounds(25, 468, 333, 26);
		lblAplicacaoQuestionario.setFont(new Font("Calibri", Font.PLAIN, 14));

		lblNewLabel = new JLabel("Conclus\u00F5es: ");
		lblNewLabel.setBounds(617, 289, 89, 23);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
	}

	private void folhaAB(JPanel panelFolhaAB) {

	}

	private void construirPanelProcAB(JPanel panelProcedimentoAB) {
		lblLogoFundoAB = new JLabel("");
		lblLogoFundoAB.setBounds(10, 0, 294, 107);
		lblLogoFundoAB.setIcon(new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/vga/vga_fundo_folha.png")));

		lblProcAuditoria = new JLabel("");
		lblProcAuditoria.setBounds(25, 98, 931, 26);
		lblProcAuditoria.setIcon(
				new ImageIcon(TelaFolhaMestra1.class.getResource("/icons/vga/procedimentos de auditoria.png")));

		lblDescrioDoProcedimento = new JLabel("DESCRI\u00C7\u00C3O DO PROCEDIMENTO:\r\n\t");
		lblDescrioDoProcedimento.setBounds(25, 191, 221, 37);
		lblDescrioDoProcedimento.setFont(new Font("Calibri", Font.PLAIN, 14));

		chbNaoInspecaoDocumental = new JCheckBox("N\u00E3o");
		chbNaoInspecaoDocumental.setBounds(375, 241, 97, 23);
		chbNaoInspecaoDocumental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControllerProcAB().eventoCheckBox(chbNaoInspecaoDocumental);
				getControllerProcAB().salvarCheckAction("INSPEÇÃO DOCUMENTAL", chbNaoInspecaoDocumental);
			}
		});
		chbNaoInspecaoDocumental.setFont(new Font("Calibri", Font.PLAIN, 14));
		chbNaoInspecaoDocumental.setBackground(Color.WHITE);
	}

	private void contruirPanelFolhAC(Panel panelFolhaAC) {

	}

	public void contruirProcAD(JPanel panelProcedimentoAD) {

	}

	private void construirProcAC(Panel panelProcedimentoAC) {

	}

	public void construirFolhaAA(JPanel panelFolhaAA) {

	}

	public void contruirFolhaEProcAE(JTabbedPane tabbedPane) {

	}

	public void construirProcAA(JPanel panelProcedimentoAA) {

	}

	public void setBalanceteControl(ControllerBalanceteFolhaMestra balanceteControl) {
		this.balanceteControl = balanceteControl;
	}

	public void setControlFolhaMestra(ControllerFolhaMestra control) {
		this.controlFolhaMestra = control;
	}

	public JLabel getLblClienteCapa() {
		return lblClienteCapa;
	}

	public JLabel getLblDataBaseCapa() {
		return lblDataBaseCapa;
	}

	public JLabel getLblAuditorAssistenteCapa() {
		return lblAuditorAssistenteCapa;
	}

	public JCheckBox getCheckBoxRevisadoCapa() {
		return checkBoxRevisadoCapa;
	}

	public JButton getBtnSalvarConclusaoCapa() {
		return btnSalvarConclusaoCapa;
	}

	public JLabel getLblEmpresaAB() {
		return lblEmpresaAB;
	}

	public JLabel getLblPeriodoAB() {
		return lblPeriodoAB;
	}

	public JLabel getLblDataBaseAB() {
		return lblDataBaseAB;
	}

	public JLabel getLblExecutorAB() {
		return lblExecutorAB;
	}

	public JCheckBox getChbNaoSaldoAB() {
		return chbNaoInspecaoDocumental;
	}

	public JCheckBox getChbNaoAcessoAB() {
		return chbNaoContagemFisica;
	}

	public JCheckBox getChbNaoEntrevistaAB() {
		return chbNaoAcessoOnlie;
	}

	public JCheckBox getChbNaoPagamentoAB() {
		return chbNaoRecalculo;
	}

	public JCheckBox getChbNaoDocAB() {
		return chbNaoEntrevistas;
	}

	public JCheckBox getChbNaoProcedAB() {
		return chbNaoAplicacaoQuestionario;
	}

	public JCheckBox getChbNaoPoliticaAB() {
		return chbNaoCruzamentoSaldos;
	}

	public JTextArea getTxtAreaConclusaoProcAB() {
		return txtAreaConclusaoProcAB;
	}

	public void setChbNaoSaldoAB(JCheckBox chbNaoSaldoAB) {
		this.chbNaoInspecaoDocumental = chbNaoSaldoAB;
	}

	public void setChbNaoAcessoAB(JCheckBox chbNaoAcessoAB) {
		this.chbNaoContagemFisica = chbNaoAcessoAB;
	}

	public void setChbNaoEntrevistaAB(JCheckBox chbNaoEntrevistaAB) {
		this.chbNaoAcessoOnlie = chbNaoEntrevistaAB;
	}

	public void setChbNaoPagamentoAB(JCheckBox chbNaoPagamentoAB) {
		this.chbNaoRecalculo = chbNaoPagamentoAB;
	}

	public void setChbNaoDocAB(JCheckBox chbNaoDocAB) {
		this.chbNaoEntrevistas = chbNaoDocAB;
	}

	public void setChbNaoProcedAB(JCheckBox chbNaoProcedAB) {
		this.chbNaoAplicacaoQuestionario = chbNaoProcedAB;
	}

	public void setChbNaoPoliticaAB(JCheckBox chbNaoPoliticaAB) {
		this.chbNaoCruzamentoSaldos = chbNaoPoliticaAB;
	}

	public JComboBox getCbSocioDiretor() {
		return cbSocioDiretor;
	}

	public JTable getTableBalanceteLan() {
		return tableBalanceteLan;
	}

	public JTable getTableFolhaAB() {
		return tableFolhaAB;
	}

	public JTextArea getTextAreaConclusaoCapa() {
		return textAreaConclusaoCapa;
	}

	public JLabel getLblEmpresaBalancete() {
		return lblEmpresaBalancete;
	}

	public JLabel getLblDataBaseBalancete() {
		return lblDataBaseBalancete;
	}

	public JLabel getLblAuditorBalancete() {
		return lblAuditorBalancete;
	}

	

	public JLabel getLblPeriodoBalancete() {
		return lblPeriodoBalancete;
	}

	public JComboBox getCbRevisor() {
		return cbRevisor;
	}

	public JLabel getLblUlitimaAlteracaoAuditor() {
		return lblUlitimaAlteracaoAuditor;
	}

	public JLabel getLblSaldoAnteriorTotal() {
		return lblSaldoAnteriorTotal;
	}

	public JLabel getLblDebitoTotal() {
		return lblDebitoTotal;
	}

	public JLabel getLblSaldoValidadoTotal() {
		return lblSaldoValidadoTotal;
	}

	public JLabel getLblSaldoFinalTotal() {
		return lblSaldoFinalTotal;
	}

	public JLabel getLblCreditoTotal() {
		return lblCreditoTotal;
	}

	public JComboBox getCbREFFolha() {
		return cbREFFolha;
	}

	public JComboBox getCbREFProc() {
		return cbREFProc;
	}

	public JLabel getLblDiferencaTotal() {
		return lblDiferencaTotal;
	}
	public JLabel getLblDataRevisado() {
		return lblDataRevisado;
	}
	public JCheckBox getChbCircularizacao() {
		return chbCircularizacao;
	}
	public JCheckBox getChbValidaoAmostra() {
		return chbValidaoAmostra;
	}
	public JCheckBox getChbLiquidacaoSubsequente() {
		return chbLiquidacaoSubsequente;
	}
	public JLabel getLblUsuarioUltimaAlteracao() {
		return lblUsuarioUltimaAlteracao;
	}
	public JLabel getLblRecebeDataUltimaAltera() {
		return lblRecebeDataUltimaAltera;
	}
}
