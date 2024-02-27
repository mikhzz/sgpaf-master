package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerCalculoMaterialidade;
import controller.ControllerMaterialidade;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;
import model.util.MonetarioDocument;
import model.util.PorcentagemDocumentMask;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.MatteBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaMaterialidade extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPaneMaterialidade;
	private JPanel panelAtivo;
	private JPanel panelPassivo;
	private JPanel panelPatrimonioLiquido;
	private JLabel lblLogoFundoAtivo;
	private JLabel lblLogoFundoPassivo;
	private JLabel lblLogoFundoPatrimonio;
	private JPanel panelCapa;
	private JLabel lblLogoFundoCapa;
	private JLabel lblMapaAcumulado;
	private JTable tableAtivoCirculante;
	private JLabel lblTotalAtivoCirculante2;
	private JScrollPane scrollPaneAtivoNaoCirculante;
	private JTable tableAtivoNaoCirculante;
	private JScrollPane scrollPaneAtivoCirculante;
	private JLabel lblTotalATivoNaoCirculante2;
	private JScrollPane scrollPanePassivoCirculante;
	private JTable tablePassivoCirculante;
	private JLabel lblTotalPassivoCirculante2;
	private JScrollPane scrollPanePassivoNaoCirculante;
	private JTable tablePassivoNaoCirculante;
	private JLabel lblTotalPassivoNaoCirculante2;
	private JScrollPane scrollPanePatrimonioLiquido;
	private JTable tablePatrimonioLiquido;
	private JLabel lblTotalPatrimonioLiquido2;
	private JScrollPane scrollPaneResultadoPeriodo;
	private JTable tableResultadoPeriodo;
	private JLabel lblResultadoPeriodo2;
	private JPanel panelMaterialidadeGlobal;
	private JPanel panelMaterialidadeEspecifica;
	private JLabel lblLogoFundoMatGlobal;
	private JTextArea textAreaMaterialidadeGlobal;
	private JTextArea textAreaJustificativaMatGlobal;
	private JLabel lblMaterialidadeGlobal;
	private JLabel lblJustMatGlobal;
	private JLabel lblLogoFundoMateEspecifica;
	private JLabel lblMaterialidadeEspecifica;
	private JTextArea textAreaMaterialidadeEspecifica;
	private JLabel label_1;
	private JTextArea textArea;
	private JLabel lblTotalAtivoCirculante;
	private JLabel lblTotalATivoNaoCirculante;
	private JLabel lblTotalPassivoNaoCirculante;
	private JLabel lblTotalPatrimonioLiquido;
	private JLabel lblResultadoPeriodo;
	private JLabel lblTotalPassivoCirculante;

	private JPanel panelCalculoMaterialidade;
	private JTextField tFLucroLiquido;
	private JTextField tFdAtivoTotal;
	private JTextField tFReceitaBruta;
	private JTextField tFPatrimonioLiquido;
	private JTextField tFPorcLucroLiquido;
	private JTextField tFPorcAtivoTotal;
	private JTextField tFPOrcReceitaBruta;
	private JTextField tFPorcPatrimonioLiquido;
	private JLabel lblIconePorcentagem;
	private JLabel lblIconeDinheiro;
	private JLabel lblPorcentagemLucroLiquido;
	private JLabel lblPorcentagemAtivoTotal;
	private JLabel lblPorcentagemReceitaBruta;
	private JLabel lblPorcentagemPatrimonioLiquido;
	private JLabel lblCalculoMatGlobal;
	private JLabel lblResultLucro;
	private JLabel lblResultAtivo;
	private JLabel lblResultReceita;
	private JLabel lblResultPatrimonio;
	private JLabel lblResultadoMaterialidade;
	private JLabel lblCalculoMaterialidadeTopo;
	private JLabel lblLogoTopoMaterialidade;
	private JLabel lblContasAConsiderar;
	private JLabel lblLucroLliquido;
	private JLabel lblAtivoTotal;
	private JLabel lblReceitaBruta;
	private JLabel lblPatrimonioLiquido;
	private JLabel lblCifraoLucroLiquido;
	private JLabel lblCifraoAtivoTotal;
	private JLabel lblCifraoReceitaBruta;
	private JLabel lblCifraoPatrimonioLiquido;
	private ControllerCalculoMaterialidade calculoMaterControl;
	private ControllerMaterialidade materialidadeControl;
	private Balancete balancete;
	private TelaRecomendacoes recomendacoes;
	private JButton btnRecomendacoesPassivoCirulante;
	private JButton btnPassivoNCirulante;
	private JButton btnPatrimonioLiquido;
	private JButton btnResultadoDoPeriodo;
	private DefaultTableModel modelTable;
	private DefaultTableModel modelTable2;
	private DefaultTableModel modelTable3;
	private DefaultTableModel modelTable4;
	private DefaultTableModel modelTable5;
	private DefaultTableModel modelTable6;
	private Lancamento lancamentoSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMaterialidade frame = new TelaMaterialidade(null);
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
	public TelaMaterialidade(Balancete pBalancete) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				calculoMaterControl.popularTela();
				materialidadeControl.popularTelaMaterialidade();
			}
		});
		this.balancete = pBalancete;
		setTitle("Materialidade");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaMaterialidade.class.getResource("/icons/vga/Logo VGA.jpg")));
		UIManager.getDefaults().put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0)); // ELIMINA AS BORDAS DO
																								// LOOK AND FEEL E
																								// PERMITE A ESTILIZAÇÃO
																								// POSTERIOR
		UIManager.getDefaults().put("TabbedPane.tabsOverlapBorder", true); // ELIMINA AS BORDAS DO LOOK AND FEEL E
																			// PERMITE A ESTILIZAÇÃO POSTERIOR

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1278, 731);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPaneMaterialidade = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneMaterialidade.setOpaque(true);
		tabbedPaneMaterialidade.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPaneMaterialidade.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.setBounds(10, 11, 1242, 670);

		contentPane.add(tabbedPaneMaterialidade);

		panelCapa = new JPanel();
		panelCapa.setBorder(new LineBorder(Color.BLACK));
		panelCapa.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Capa", null, panelCapa, null);
		panelCapa.setLayout(null);

		lblLogoFundoCapa = new JLabel("");
		lblLogoFundoCapa.setToolTipText("Visite: www.vgaauditores.com.br");
		lblLogoFundoCapa.setVerticalAlignment(SwingConstants.TOP);
		lblLogoFundoCapa.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/cliente-vga-1.jpg")));
		lblLogoFundoCapa.setBounds(347, 190, 544, 269);
		panelCapa.add(lblLogoFundoCapa);

		lblMapaAcumulado = new JLabel(
				"                                                           MAPA ACUMULADO COM AS PRINCIPAIS CONTAS A SEREM AUDITADAS DO PERÍODO");
		lblMapaAcumulado.setOpaque(true);
		lblMapaAcumulado.setFont(new Font("Cambria", Font.BOLD, 11));
		lblMapaAcumulado.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblMapaAcumulado.setBackground(SystemColor.menu);
		lblMapaAcumulado.setBounds(311, 92, 609, 30);
		panelCapa.add(lblMapaAcumulado);

		panelAtivo = new JPanel();
		panelAtivo.setBorder(new LineBorder(Color.BLACK));
		panelAtivo.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Ativo", null, panelAtivo, null);
		panelAtivo.setLayout(null);

		lblLogoFundoAtivo = new JLabel("");
		lblLogoFundoAtivo.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoFundoAtivo.setBounds(10, 0, 294, 107);
		panelAtivo.add(lblLogoFundoAtivo);

		scrollPaneAtivoCirculante = new JScrollPane();
		scrollPaneAtivoCirculante.setBackground(Color.WHITE);
		scrollPaneAtivoCirculante.setBounds(62, 118, 1073, 167);
		panelAtivo.add(scrollPaneAtivoCirculante);

		modelTable = new DefaultTableModel(
				new Object[] { "Ref", "Ativo Circulante", "Saldo Validado", "Diverg\u00EAncias", "Conclus\u00F5es" },
				0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tableAtivoCirculante = new JTable();
		tableAtivoCirculante.setBackground(Color.WHITE);
		tableAtivoCirculante.setEnabled(true);
		scrollPaneAtivoCirculante.setViewportView(tableAtivoCirculante);
		tableAtivoCirculante.setModel(modelTable);
		lblTotalAtivoCirculante2 = new JLabel("Total: ");
		lblTotalAtivoCirculante2.setBounds(62, 297, 40, 14);
		panelAtivo.add(lblTotalAtivoCirculante2);

		scrollPaneAtivoNaoCirculante = new JScrollPane();
		scrollPaneAtivoNaoCirculante.setBackground(Color.WHITE);
		scrollPaneAtivoNaoCirculante.setBounds(62, 337, 1073, 167);
		panelAtivo.add(scrollPaneAtivoNaoCirculante);

		modelTable2 = new DefaultTableModel(new Object[] { "Ref", "Ativo Não Circulante", "Saldo Validado",
				"Diverg\u00EAncias", "Conclus\u00F5es" }, 0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tableAtivoNaoCirculante = new JTable();
		tableAtivoNaoCirculante.setEnabled(true);
		scrollPaneAtivoNaoCirculante.setViewportView(tableAtivoNaoCirculante);
		tableAtivoNaoCirculante.setModel(modelTable2);

		lblTotalATivoNaoCirculante2 = new JLabel("Total: ");
		lblTotalATivoNaoCirculante2.setBounds(62, 515, 40, 14);
		panelAtivo.add(lblTotalATivoNaoCirculante2);

		lblTotalAtivoCirculante = new JLabel("");
		lblTotalAtivoCirculante.setBounds(99, 297, 311, 14);
		panelAtivo.add(lblTotalAtivoCirculante);

		lblTotalATivoNaoCirculante = new JLabel("");
		lblTotalATivoNaoCirculante.setBounds(99, 515, 311, 14);
		panelAtivo.add(lblTotalATivoNaoCirculante);

		JButton btnRecomendaçõesAtivoCirculante = new JButton("");
		btnRecomendaçõesAtivoCirculante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancamentoSelecionado = materialidadeControl.lancamentoSelecionadoTable(materialidadeControl.getAtivosCirculantesList(), tableAtivoCirculante);
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
		btnRecomendaçõesAtivoCirculante
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnRecomendaçõesAtivoCirculante.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnRecomendaçõesAtivoCirculante.setBackground(Color.WHITE);
		btnRecomendaçõesAtivoCirculante.setBounds(1072, 72, 50, 32);
		panelAtivo.add(btnRecomendaçõesAtivoCirculante);

		JButton btnRecomendacoesAtivoNCirculante = new JButton("");
		btnRecomendacoesAtivoNCirculante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancamentoSelecionado = materialidadeControl.lancamentoSelecionadoTable(materialidadeControl.getAtivosNaoCirculantesList(), tableAtivoNaoCirculante);
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
		btnRecomendacoesAtivoNCirculante
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnRecomendacoesAtivoNCirculante.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnRecomendacoesAtivoNCirculante.setBackground(Color.WHITE);
		btnRecomendacoesAtivoNCirculante.setBounds(1072, 296, 50, 32);
		panelAtivo.add(btnRecomendacoesAtivoNCirculante);

		panelPassivo = new JPanel();
		panelPassivo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPassivo.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Passivo", null, panelPassivo, null);
		panelPassivo.setLayout(null);

		lblLogoFundoPassivo = new JLabel("");
		lblLogoFundoPassivo
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoFundoPassivo.setBounds(10, 0, 294, 107);
		panelPassivo.add(lblLogoFundoPassivo);

		scrollPanePassivoCirculante = new JScrollPane();
		scrollPanePassivoCirculante.setBackground(Color.WHITE);
		scrollPanePassivoCirculante.setBounds(62, 118, 1073, 167);
		panelPassivo.add(scrollPanePassivoCirculante);

		modelTable3 = new DefaultTableModel(
				new Object[] { "Ref", "Passivo Circulante", "Saldo Validado", "Diverg\u00EAncias", "Conclus\u00F5es" },
				0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tablePassivoCirculante = new JTable();
		tablePassivoCirculante.setEnabled(true);
		scrollPanePassivoCirculante.setViewportView(tablePassivoCirculante);
		tablePassivoCirculante.setModel(modelTable3);

		lblTotalPassivoCirculante2 = new JLabel("Total: ");
		lblTotalPassivoCirculante2.setBounds(62, 296, 38, 14);
		panelPassivo.add(lblTotalPassivoCirculante2);

		scrollPanePassivoNaoCirculante = new JScrollPane();
		scrollPanePassivoNaoCirculante.setBackground(Color.WHITE);
		scrollPanePassivoNaoCirculante.setBounds(62, 337, 1073, 167);
		panelPassivo.add(scrollPanePassivoNaoCirculante);

		modelTable4 = new DefaultTableModel(new Object[] { "Ref", "Passivo Não Circulante", "Saldo Validado",
				"Diverg\u00EAncias", "Conclus\u00F5es" }, 0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tablePassivoNaoCirculante = new JTable();
		tablePassivoNaoCirculante.setEnabled(true);
		scrollPanePassivoNaoCirculante.setViewportView(tablePassivoNaoCirculante);
		tablePassivoNaoCirculante.setModel(modelTable4);

		lblTotalPassivoNaoCirculante2 = new JLabel("Total: ");
		lblTotalPassivoNaoCirculante2.setBounds(62, 515, 38, 14);
		panelPassivo.add(lblTotalPassivoNaoCirculante2);

		lblTotalPassivoNaoCirculante = new JLabel("");
		lblTotalPassivoNaoCirculante.setBounds(93, 515, 311, 14);
		panelPassivo.add(lblTotalPassivoNaoCirculante);

		lblTotalPassivoCirculante = new JLabel("");
		lblTotalPassivoCirculante.setBounds(93, 296, 311, 14);
		panelPassivo.add(lblTotalPassivoCirculante);

		btnRecomendacoesPassivoCirulante = new JButton("");
		btnRecomendacoesPassivoCirulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recomendacoes.show();
			}
		});
		btnRecomendacoesPassivoCirulante
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnRecomendacoesPassivoCirulante.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnRecomendacoesPassivoCirulante.setBackground(Color.WHITE);
		btnRecomendacoesPassivoCirulante.setBounds(1072, 72, 50, 32);
		panelPassivo.add(btnRecomendacoesPassivoCirulante);

		btnPassivoNCirulante = new JButton("");
		btnPassivoNCirulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recomendacoes.show();
			}
		});
		btnPassivoNCirulante
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnPassivoNCirulante.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnPassivoNCirulante.setBackground(Color.WHITE);
		btnPassivoNCirulante.setBounds(1072, 296, 50, 32);
		panelPassivo.add(btnPassivoNCirulante);

		panelPatrimonioLiquido = new JPanel();
		panelPatrimonioLiquido.setBorder(new LineBorder(Color.BLACK));
		panelPatrimonioLiquido.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Patrimônio Líquido", null, panelPatrimonioLiquido, null);
		panelPatrimonioLiquido.setLayout(null);

		lblLogoFundoPatrimonio = new JLabel("");
		lblLogoFundoPatrimonio
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoFundoPatrimonio.setBounds(10, 0, 294, 107);
		panelPatrimonioLiquido.add(lblLogoFundoPatrimonio);

		scrollPanePatrimonioLiquido = new JScrollPane();
		scrollPanePatrimonioLiquido.setBackground(Color.WHITE);
		scrollPanePatrimonioLiquido.setBounds(62, 118, 1073, 167);
		panelPatrimonioLiquido.add(scrollPanePatrimonioLiquido);

		modelTable5 = new DefaultTableModel(
				new Object[] { "Ref", "Patrimônio Líquido", "Saldo Validado", "Diverg\u00EAncias", "Conclus\u00F5es" },
				0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tablePatrimonioLiquido = new JTable();
		tablePatrimonioLiquido.setEnabled(true);
		scrollPanePatrimonioLiquido.setViewportView(tablePatrimonioLiquido);
		tablePatrimonioLiquido.setModel(modelTable5);

		lblTotalPatrimonioLiquido2 = new JLabel("Total: ");
		lblTotalPatrimonioLiquido2.setBounds(62, 296, 38, 14);
		panelPatrimonioLiquido.add(lblTotalPatrimonioLiquido2);

		scrollPaneResultadoPeriodo = new JScrollPane();
		scrollPaneResultadoPeriodo.setBackground(Color.WHITE);
		scrollPaneResultadoPeriodo.setBounds(62, 337, 1074, 86);
		panelPatrimonioLiquido.add(scrollPaneResultadoPeriodo);

		modelTable6 = new DefaultTableModel(new Object[] { "Ref", "Resultado do Período", "Saldo Validado",
				"Diverg\u00EAncias", "Conclus\u00F5es" }, 0) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		tableResultadoPeriodo = new JTable();
		tableResultadoPeriodo.setEnabled(true);
		scrollPaneResultadoPeriodo.setViewportView(tableResultadoPeriodo);
		tableResultadoPeriodo.setModel(modelTable6);

		lblResultadoPeriodo2 = new JLabel("Resultado do Período:");
		lblResultadoPeriodo2.setBounds(62, 436, 130, 14);
		panelPatrimonioLiquido.add(lblResultadoPeriodo2);

		lblTotalPatrimonioLiquido = new JLabel("");
		lblTotalPatrimonioLiquido.setBounds(92, 296, 311, 14);
		panelPatrimonioLiquido.add(lblTotalPatrimonioLiquido);

		lblResultadoPeriodo = new JLabel("");
		lblResultadoPeriodo.setBounds(174, 436, 311, 14);
		panelPatrimonioLiquido.add(lblResultadoPeriodo);

		btnPatrimonioLiquido = new JButton("");
		btnPatrimonioLiquido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recomendacoes.show();
			}
		});
		btnPatrimonioLiquido
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnPatrimonioLiquido.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnPatrimonioLiquido.setBackground(Color.WHITE);
		btnPatrimonioLiquido.setBounds(1072, 72, 50, 32);
		panelPatrimonioLiquido.add(btnPatrimonioLiquido);

		btnResultadoDoPeriodo = new JButton("");
		btnResultadoDoPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recomendacoes.show();
			}
		});
		btnResultadoDoPeriodo
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-sugestão-32.png")));
		btnResultadoDoPeriodo.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnResultadoDoPeriodo.setBackground(Color.WHITE);
		btnResultadoDoPeriodo.setBounds(1072, 296, 50, 32);
		panelPatrimonioLiquido.add(btnResultadoDoPeriodo);

		panelMaterialidadeGlobal = new JPanel();
		panelMaterialidadeGlobal.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMaterialidadeGlobal.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Materialidade Global", null, panelMaterialidadeGlobal, null);
		panelMaterialidadeGlobal.setLayout(null);

		lblLogoFundoMatGlobal = new JLabel("");
		lblLogoFundoMatGlobal
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoFundoMatGlobal.setBounds(10, 0, 294, 107);
		panelMaterialidadeGlobal.add(lblLogoFundoMatGlobal);

		textAreaMaterialidadeGlobal = new JTextArea();
		textAreaMaterialidadeGlobal.setBorder(new LineBorder(Color.BLACK));
		textAreaMaterialidadeGlobal.setText(
				"A materialidade para as demonstrações contábeis como um todo. \r\n(materialidade global.)\r\nÉ baseada no julgamento profissional do auditor sobre o valor mais alto de distorções que poderia estar incluído nas demonstrações contábeis sem afetar\r\nas decisões econômicas tomadas por um usuário das demonstrações contábeis.\r\nSe o valor de distorções não corrigidas, individualmente ou em conjunto, é maior que a materialidade global estabelecida para o trabalho.\r\nIsso significaria que as demonstrações contábeis apresentam distorções relevantes.\r\nA materialidade global é baseada nas necessidades de informações financeiras comuns de diversos usuários como um grupo.\r\nConsequentemente, não é considerado o possível efeito de distorções sobre usuários individuais específicos, cujas necessidades podem variar significativamente.");
		textAreaMaterialidadeGlobal.setFont(new Font("Calibri", Font.PLAIN, 13));
		textAreaMaterialidadeGlobal.setEditable(false);
		textAreaMaterialidadeGlobal.setBounds(27, 118, 907, 170);
		panelMaterialidadeGlobal.add(textAreaMaterialidadeGlobal);

		textAreaJustificativaMatGlobal = new JTextArea();
		textAreaJustificativaMatGlobal.setBorder(new LineBorder(Color.BLACK));
		textAreaJustificativaMatGlobal.setText(
				"Foco primário dos Usuários.\r\nQuais Informações das demonstrações contábeis\r\natrairão mais a atenção dos Usuários?\r\npelo fato do lucro por ação e índices de liquidez\r\nsolvência e endividamento.\r\nInfluencairem significativamente as operações\r\nda CIA.\r\n• na avaliação do desempenho das operações\r\nconcentrar-se-ão em lucros, receitas ou ativos líquidos\r\n• nos recursos utilizados para alcançar certas metas\r\nou objetivos enfocarão a natureza e a extensão\r\nde receitas e despesas.");
		textAreaJustificativaMatGlobal.setFont(new Font("Calibri", Font.PLAIN, 13));
		textAreaJustificativaMatGlobal.setEditable(false);
		textAreaJustificativaMatGlobal.setBounds(27, 375, 907, 232);
		panelMaterialidadeGlobal.add(textAreaJustificativaMatGlobal);

		lblMaterialidadeGlobal = new JLabel("                  Materialidade Global");
		lblMaterialidadeGlobal.setOpaque(true);
		lblMaterialidadeGlobal.setBorder(new LineBorder(Color.BLACK));
		lblMaterialidadeGlobal.setBackground(SystemColor.menu);
		lblMaterialidadeGlobal.setBounds(547, 70, 238, 37);
		panelMaterialidadeGlobal.add(lblMaterialidadeGlobal);

		lblJustMatGlobal = new JLabel("            Justificativa Materialidade Global");
		lblJustMatGlobal.setOpaque(true);
		lblJustMatGlobal.setBorder(new LineBorder(Color.BLACK));
		lblJustMatGlobal.setBackground(SystemColor.menu);
		lblJustMatGlobal.setBounds(547, 304, 238, 37);
		panelMaterialidadeGlobal.add(lblJustMatGlobal);

		panelMaterialidadeEspecifica = new JPanel();
		panelMaterialidadeEspecifica.setBorder(new LineBorder(Color.BLACK));
		panelMaterialidadeEspecifica.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Materialidade Especifica", null, panelMaterialidadeEspecifica, null);
		panelMaterialidadeEspecifica.setLayout(null);

		lblLogoFundoMateEspecifica = new JLabel("");
		lblLogoFundoMateEspecifica
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoFundoMateEspecifica.setBounds(10, 0, 294, 107);
		panelMaterialidadeEspecifica.add(lblLogoFundoMateEspecifica);

		lblMaterialidadeEspecifica = new JLabel("                  Materialidade Específica");
		lblMaterialidadeEspecifica.setOpaque(true);
		lblMaterialidadeEspecifica.setBorder(new LineBorder(Color.BLACK));
		lblMaterialidadeEspecifica.setBackground(SystemColor.menu);
		lblMaterialidadeEspecifica.setBounds(547, 70, 238, 37);
		panelMaterialidadeEspecifica.add(lblMaterialidadeEspecifica);

		textAreaMaterialidadeEspecifica = new JTextArea();
		textAreaMaterialidadeEspecifica.setBorder(new LineBorder(Color.BLACK));
		textAreaMaterialidadeEspecifica.setText(
				"Em alguns casos, pode haver a necessidade de identificar distorções de valores inferiores ao da materialidade global que afetariam as decisões econômicas\r\nde usuários de demonstrações contábeis. \r\nIsso pode estar relacionado com áreas sensíveis, tais como divulgações de notas específicas (tais como, remuneração da administração ou dados específicos \r\ndo setor), cumprimento de legislação ou de certos termos de contrato, ou transações sobre as quais se baseiam bônus. \r\nPode estar relacionado, também, com a natureza de uma possível distorção.");
		textAreaMaterialidadeEspecifica.setFont(new Font("Calibri", Font.PLAIN, 13));
		textAreaMaterialidadeEspecifica.setEditable(false);
		textAreaMaterialidadeEspecifica.setBounds(27, 118, 890, 118);
		panelMaterialidadeEspecifica.add(textAreaMaterialidadeEspecifica);

		label_1 = new JLabel("          Justificativa Materialidade Específica");
		label_1.setOpaque(true);
		label_1.setBorder(new LineBorder(Color.BLACK));
		label_1.setBackground(SystemColor.menu);
		label_1.setBounds(547, 289, 265, 37);
		panelMaterialidadeEspecifica.add(label_1);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(Color.BLACK));
		textArea.setText(
				"Consideramos no caso em apreço a materialidade de\r\n30% da materialidade global, conforme padrões\r\nutilizados. Entretanto optamos por aumentar as\r\namostras em contas expostas a mais risco,\r\naumentando a confiabilidade dos testes e detecção de\r\namostras em contas expostas a mais risco, \r\naumentando a confiabilidade dos testes e detecção\r\nde distorção. Na análise da materialidade específica ainda \r\nanalisamos individualmente as distorções que, em \r\nconjunto com as métricas estabelecidas não devem representar \r\nmais que 10% do grupo ao qual pertencem.");
		textArea.setFont(new Font("Calibri", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(27, 375, 890, 197);
		panelMaterialidadeEspecifica.add(textArea);

		panelCalculoMaterialidade = new JPanel();
		panelCalculoMaterialidade.setBorder(new LineBorder(Color.BLACK));
		panelCalculoMaterialidade.setBackground(Color.WHITE);
		tabbedPaneMaterialidade.addTab("Cálculo Materialidade", null, panelCalculoMaterialidade, null);
		panelCalculoMaterialidade.setLayout(null);

		lblLogoTopoMaterialidade = new JLabel("");
		lblLogoTopoMaterialidade
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
		lblLogoTopoMaterialidade.setBounds(10, 0, 294, 107);
		panelCalculoMaterialidade.add(lblLogoTopoMaterialidade);

		lblContasAConsiderar = new JLabel("                           CONTAS A SEREM CONSIDERADAS");
		lblContasAConsiderar.setOpaque(true);
		lblContasAConsiderar.setBorder(new LineBorder(Color.BLACK));
		lblContasAConsiderar.setBackground(SystemColor.menu);
		lblContasAConsiderar.setBounds(10, 180, 365, 34);
		panelCalculoMaterialidade.add(lblContasAConsiderar);

		lblLucroLliquido = new JLabel("  LUCRO LÍQUIDO");
		lblLucroLliquido.setOpaque(true);
		lblLucroLliquido.setBorder(new LineBorder(Color.BLACK));
		lblLucroLliquido.setBackground(SystemColor.menu);
		lblLucroLliquido.setBounds(10, 267, 136, 26);
		panelCalculoMaterialidade.add(lblLucroLliquido);

		lblAtivoTotal = new JLabel("  ATIVO TOTAL");
		lblAtivoTotal.setOpaque(true);
		lblAtivoTotal.setBorder(new LineBorder(Color.BLACK));
		lblAtivoTotal.setBackground(SystemColor.menu);
		lblAtivoTotal.setBounds(10, 314, 136, 26);
		panelCalculoMaterialidade.add(lblAtivoTotal);

		lblReceitaBruta = new JLabel("  RECEITA BRUTA");
		lblReceitaBruta.setOpaque(true);
		lblReceitaBruta.setBorder(new LineBorder(Color.BLACK));
		lblReceitaBruta.setBackground(SystemColor.menu);
		lblReceitaBruta.setBounds(10, 366, 136, 26);
		panelCalculoMaterialidade.add(lblReceitaBruta);

		lblPatrimonioLiquido = new JLabel("  PATRIMÔNIO LÍQUIDO");
		lblPatrimonioLiquido.setOpaque(true);
		lblPatrimonioLiquido.setBorder(new LineBorder(Color.BLACK));
		lblPatrimonioLiquido.setBackground(SystemColor.menu);
		lblPatrimonioLiquido.setBounds(10, 418, 136, 26);
		panelCalculoMaterialidade.add(lblPatrimonioLiquido);

		lblCifraoLucroLiquido = new JLabel("R$:");
		lblCifraoLucroLiquido.setBounds(156, 267, 20, 26);
		panelCalculoMaterialidade.add(lblCifraoLucroLiquido);

		lblCifraoAtivoTotal = new JLabel("R$:");
		lblCifraoAtivoTotal.setBounds(156, 314, 20, 26);
		panelCalculoMaterialidade.add(lblCifraoAtivoTotal);

		lblCifraoReceitaBruta = new JLabel("R$:");
		lblCifraoReceitaBruta.setBounds(156, 366, 20, 26);
		panelCalculoMaterialidade.add(lblCifraoReceitaBruta);

		lblCifraoPatrimonioLiquido = new JLabel("R$:");
		lblCifraoPatrimonioLiquido.setBounds(156, 418, 20, 26);
		panelCalculoMaterialidade.add(lblCifraoPatrimonioLiquido);

		tFLucroLiquido = new JTextField();
		tFLucroLiquido.setDocument(new MonetarioDocument());
		tFLucroLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFLucroLiquido.setToolTipText("Digite o Valor do Lucro Líquido");
		tFLucroLiquido.setColumns(10);
		tFLucroLiquido.setBounds(186, 270, 136, 26);
		panelCalculoMaterialidade.add(tFLucroLiquido);

		tFdAtivoTotal = new JTextField();
		tFdAtivoTotal.setDocument(new MonetarioDocument());
		tFdAtivoTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFdAtivoTotal.setToolTipText("Digite o Valor do Lucro Líquido");
		tFdAtivoTotal.setColumns(10);
		tFdAtivoTotal.setBounds(186, 314, 136, 26);
		panelCalculoMaterialidade.add(tFdAtivoTotal);

		tFReceitaBruta = new JTextField();
		tFReceitaBruta.setDocument(new MonetarioDocument());
		tFReceitaBruta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFReceitaBruta.setToolTipText("Digite o Valor do Lucro Líquido");
		tFReceitaBruta.setColumns(10);
		tFReceitaBruta.setBounds(186, 366, 136, 26);
		panelCalculoMaterialidade.add(tFReceitaBruta);

		tFPatrimonioLiquido = new JTextField();
		tFPatrimonioLiquido.setDocument(new MonetarioDocument());
		tFPatrimonioLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPatrimonioLiquido.setToolTipText("Digite o Valor do Lucro Líquido");
		tFPatrimonioLiquido.setColumns(10);
		tFPatrimonioLiquido.setBounds(186, 418, 136, 26);
		panelCalculoMaterialidade.add(tFPatrimonioLiquido);

		tFPorcLucroLiquido = new JTextField();
		tFPorcLucroLiquido.setDocument(new PorcentagemDocumentMask());
		tFPorcLucroLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPorcLucroLiquido.setToolTipText("Digite a Porcentagem corresponde a conta");
		tFPorcLucroLiquido.setColumns(10);
		tFPorcLucroLiquido.setBounds(332, 270, 43, 26);
		panelCalculoMaterialidade.add(tFPorcLucroLiquido);

		tFPorcAtivoTotal = new JTextField();
		tFPorcAtivoTotal.setDocument(new PorcentagemDocumentMask());
		tFPorcAtivoTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPorcAtivoTotal.setToolTipText("Digite a Porcentagem corresponde a conta");
		tFPorcAtivoTotal.setColumns(10);
		tFPorcAtivoTotal.setBounds(332, 314, 43, 26);
		panelCalculoMaterialidade.add(tFPorcAtivoTotal);

		tFPOrcReceitaBruta = new JTextField();
		tFPOrcReceitaBruta.setDocument(new PorcentagemDocumentMask());
		tFPOrcReceitaBruta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPOrcReceitaBruta.setToolTipText("Digite a Porcentagem corresponde a conta");
		tFPOrcReceitaBruta.setColumns(10);
		tFPOrcReceitaBruta.setBounds(332, 366, 43, 26);
		panelCalculoMaterialidade.add(tFPOrcReceitaBruta);

		tFPorcPatrimonioLiquido = new JTextField();
		tFPorcPatrimonioLiquido.setDocument(new PorcentagemDocumentMask());
		tFPorcPatrimonioLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPorcPatrimonioLiquido.setToolTipText("Digite a Porcentagem corresponde a conta");
		tFPorcPatrimonioLiquido.setColumns(10);
		tFPorcPatrimonioLiquido.setBounds(332, 421, 43, 26);
		panelCalculoMaterialidade.add(tFPorcPatrimonioLiquido);

		lblIconePorcentagem = new JLabel("");
		lblIconePorcentagem.setIcon(
				new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-percentagem-32.png")));
		lblIconePorcentagem.setBounds(332, 225, 57, 34);
		panelCalculoMaterialidade.add(lblIconePorcentagem);

		lblIconeDinheiro = new JLabel("");
		lblIconeDinheiro
				.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/diversos/icons8-dinheiro-32.png")));
		lblIconeDinheiro.setBounds(220, 225, 57, 34);
		panelCalculoMaterialidade.add(lblIconeDinheiro);

		lblPorcentagemLucroLiquido = new JLabel("%");
		lblPorcentagemLucroLiquido.setBounds(385, 267, 20, 26);
		panelCalculoMaterialidade.add(lblPorcentagemLucroLiquido);

		lblPorcentagemAtivoTotal = new JLabel("%");
		lblPorcentagemAtivoTotal.setBounds(385, 314, 20, 26);
		panelCalculoMaterialidade.add(lblPorcentagemAtivoTotal);

		lblPorcentagemReceitaBruta = new JLabel("%");
		lblPorcentagemReceitaBruta.setBounds(385, 366, 20, 26);
		panelCalculoMaterialidade.add(lblPorcentagemReceitaBruta);

		lblPorcentagemPatrimonioLiquido = new JLabel("%");
		lblPorcentagemPatrimonioLiquido.setBounds(385, 418, 20, 26);
		panelCalculoMaterialidade.add(lblPorcentagemPatrimonioLiquido);

		lblCalculoMatGlobal = new JLabel("  CÁLCULO DA MATERIALIDADE GLOBAL");
		lblCalculoMatGlobal.setOpaque(true);
		lblCalculoMatGlobal.setBorder(new LineBorder(Color.BLACK));
		lblCalculoMatGlobal.setBackground(SystemColor.menu);
		lblCalculoMatGlobal.setBounds(836, 225, 243, 34);
		panelCalculoMaterialidade.add(lblCalculoMatGlobal);

		lblResultLucro = new JLabel("");
		lblResultLucro.setBounds(415, 267, 191, 26);
		panelCalculoMaterialidade.add(lblResultLucro);

		lblResultAtivo = new JLabel("");
		lblResultAtivo.setBounds(415, 314, 191, 26);
		panelCalculoMaterialidade.add(lblResultAtivo);

		lblResultReceita = new JLabel("");
		lblResultReceita.setBounds(415, 366, 191, 26);
		panelCalculoMaterialidade.add(lblResultReceita);

		lblResultPatrimonio = new JLabel("");
		lblResultPatrimonio.setBounds(415, 424, 191, 26);
		panelCalculoMaterialidade.add(lblResultPatrimonio);

		lblResultadoMaterialidade = new JLabel("");
		lblResultadoMaterialidade.setBounds(836, 344, 243, 26);
		panelCalculoMaterialidade.add(lblResultadoMaterialidade);

		lblCalculoMaterialidadeTopo = new JLabel(
				"                                                                 CÁLCULO DA MATERIALIDADE");
		lblCalculoMaterialidadeTopo.setOpaque(true);
		lblCalculoMaterialidadeTopo.setBorder(new LineBorder(Color.BLACK));
		lblCalculoMaterialidadeTopo.setBackground(SystemColor.menu);
		lblCalculoMaterialidadeTopo.setBounds(355, 88, 544, 34);
		panelCalculoMaterialidade.add(lblCalculoMaterialidadeTopo);
		calculoMaterControl = new ControllerCalculoMaterialidade(tFLucroLiquido, tFdAtivoTotal, tFReceitaBruta,
				tFPatrimonioLiquido, tFPorcLucroLiquido, tFPorcAtivoTotal, tFPOrcReceitaBruta, tFPorcPatrimonioLiquido,
				lblResultLucro, lblResultAtivo, lblResultReceita, lblResultPatrimonio, lblResultadoMaterialidade,
				balancete);
		materialidadeControl = new ControllerMaterialidade(balancete, tableAtivoCirculante, lblTotalAtivoCirculante,
				tableAtivoNaoCirculante, lblTotalATivoNaoCirculante, tablePassivoCirculante, lblTotalPassivoCirculante,
				tablePassivoNaoCirculante, lblTotalPassivoNaoCirculante, tablePatrimonioLiquido,
				lblTotalPatrimonioLiquido, tableResultadoPeriodo, lblResultadoPeriodo);
		
		JButton btnAtulizar = new JButton("");
		btnAtulizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				materialidadeControl.popularTelaMaterialidade();
			}
		});
		btnAtulizar.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/refrescar.png")));
		btnAtulizar.setToolTipText("Selecione a conta para visualizar recomendações (se houver)");
		btnAtulizar.setBackground(Color.WHITE);
		btnAtulizar.setBounds(1003, 72, 50, 32);
		panelAtivo.add(btnAtulizar);
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculoMaterControl.salvarAction();
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaMaterialidade.class.getResource("/icons/empresa/icons8-salvar-32.png")));
		btnSalvar.setToolTipText("Clique para Salvar");
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(1059, 542, 129, 30);
		panelCalculoMaterialidade.add(btnSalvar);
		
		
	}

	public JTable getTableAtivoCirculante() {
		return tableAtivoCirculante;
	}

	public JTable getTableAtivoNaoCirculante() {
		return tableAtivoNaoCirculante;
	}

	public JLabel getLblTotalAtivoCirculante() {
		return lblTotalAtivoCirculante;
	}

	public JLabel getLblTotalATivoNaoCirculante() {
		return lblTotalATivoNaoCirculante;
	}

	public JTable getTablePassivoCirculante() {
		return tablePassivoCirculante;
	}

	public JTable getTablePassivoNaoCirculante() {
		return tablePassivoNaoCirculante;
	}

	public JLabel getLblTotalPassivoNaoCirculante() {
		return lblTotalPassivoNaoCirculante;
	}

	public JTable getTablePatrimonioLiquido() {
		return tablePatrimonioLiquido;
	}

	public JTable getTableResultadoPeriodo() {
		return tableResultadoPeriodo;
	}

	public JLabel getLblTotalPatrimonioLiquido() {
		return lblTotalPatrimonioLiquido;
	}

	public JLabel getLblResultadoPeriodo() {
		return lblResultadoPeriodo;
	}

	public JLabel getLblTotalPassivoCirculante() {
		return lblTotalPassivoCirculante;
	}

	public JLabel getLblResultPatrimonio() {
		return lblResultPatrimonio;
	}

	public JLabel getLblResultReceita() {
		return lblResultReceita;
	}

	public JLabel getLblResultAtivo() {
		return lblResultAtivo;
	}

	public JLabel getLblResultLucro() {
		return lblResultLucro;
	}

	public JTextField getTFPorcLucroLiquido() {
		return tFPorcLucroLiquido;
	}

	public JTextField getTFPorcPatrimonioLiquido() {
		return tFPorcPatrimonioLiquido;
	}

	public JTextField getTFPOrcReceitaBruta() {
		return tFPOrcReceitaBruta;
	}

	public JTextField getTFPorcAtivoTotal() {
		return tFPorcAtivoTotal;
	}

	public JTextField getTFPatrimonioLiquido() {
		return tFPatrimonioLiquido;
	}

	public JTextField getTFdAtivoTotal() {
		return tFdAtivoTotal;
	}

	public JTextField getTFReceitaBruta() {
		return tFReceitaBruta;
	}

	public JTextField getTFLucroLiquido() {
		return tFLucroLiquido;
	}

	public JLabel getLblResultadoMaterialidade() {
		return lblResultadoMaterialidade;
	}
}
