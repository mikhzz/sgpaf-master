package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.ControllerCalculoMaterialidade;
import model.daoNovo.db.Balancete;
import model.util.MonetarioDocument;
import model.util.PorcentagemDocumentMask;

public class TelaCalculoMaterialidade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tFLucroLiquido;
	private JTextField tFdAtivoTotal;
	private JTextField tFReceitaBruta;
	private JTextField tFPatrimonioLiquido;
	private JTextField tFPorcLucroLiquido;
	private JTextField tFPorcAtivoTotal;
	private JTextField tFPOrcReceitaBruta;
	private JTextField tFPorcPatrimonioLiquido;
	private JLabel lblResultLucro;
	private JLabel lblResultAtivo;
	private JLabel lblResultReceita;
	private JLabel lblResultPatrimonio;
	private JLabel lblResultadoMaterialidade;
	private ControllerCalculoMaterialidade calculoMaterControl;
	private Balancete balancete;
	private JLabel lblPorcentagemLucroLiquido;
	private JLabel lblPorcentagemAtivoTotal;
	private JLabel lblPorcentagemReceitaBruta;
	private JLabel lblPorcentagemPatrimonioLiquido;
	private JLabel lblCifraoLucroLiquido;
	private JLabel lblCifraoAtivoTotal;
	private JLabel lblCifraoReceitaBruta;
	private JLabel lblCifraoPatrimonioLiquido;
	private JLabel lblIconPorcentagem;
	private JLabel lblIconDinheiro;
	private JLabel lblContasAConsiderar;
	private JLabel lblLucroLiquido;
	private JLabel lblAtivoTotal;
	private JLabel lblReceitaBruta;
	private JLabel lblPatrimonioLiquido;
	private JLabel lblLogoTopo;
	private JLabel lblClculoDaMaterialidade;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaCalculoMaterialidade dialog = new TelaCalculoMaterialidade(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaCalculoMaterialidade(Balancete pBalancete) {
		this.balancete = pBalancete;
		setTitle("Calculo da Materialidade");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaCalculoMaterialidade.class.getResource("/icons/vga/Logo VGA.jpg")));
		setBounds(100, 100, 864, 478);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblContasAConsiderar = new JLabel("  CONTAS A SEREM CONSIDERADAS");
			lblContasAConsiderar.setBounds(10, 138, 208, 34);
			lblContasAConsiderar.setOpaque(true);
			lblContasAConsiderar.setBackground(SystemColor.menu);
			lblContasAConsiderar.setBorder(new LineBorder(Color.BLACK));
			contentPanel.add(lblContasAConsiderar);
		}
		{
			lblLucroLiquido = new JLabel("  LUCRO LÍQUIDO");
			lblLucroLiquido.setBounds(10, 226, 136, 26);
			lblLucroLiquido.setOpaque(true);
			lblLucroLiquido.setBackground(SystemColor.menu);
			lblLucroLiquido.setBorder(new LineBorder(Color.BLACK));
			contentPanel.add(lblLucroLiquido);
		}
		{
			lblAtivoTotal = new JLabel("  ATIVO TOTAL");
			lblAtivoTotal.setBounds(10, 263, 136, 26);
			lblAtivoTotal.setOpaque(true);
			lblAtivoTotal.setBackground(SystemColor.menu);
			lblAtivoTotal.setBorder(new LineBorder(Color.BLACK));
			contentPanel.add(lblAtivoTotal);
		}
		{
			lblReceitaBruta = new JLabel("  RECEITA BRUTA");
			lblReceitaBruta.setBounds(10, 300, 136, 26);
			lblReceitaBruta.setOpaque(true);
			lblReceitaBruta.setBackground(SystemColor.menu);
			lblReceitaBruta.setBorder(new LineBorder(Color.BLACK));

			contentPanel.add(lblReceitaBruta);
		}
		{
			lblPatrimonioLiquido = new JLabel("  PATRIMÔNIO LÍQUIDO");
			lblPatrimonioLiquido.setBounds(10, 337, 136, 26);
			lblPatrimonioLiquido.setOpaque(true);
			lblPatrimonioLiquido.setBackground(SystemColor.menu);
			lblPatrimonioLiquido.setBorder(new LineBorder(Color.BLACK));
			contentPanel.add(lblPatrimonioLiquido);
		}

		tFLucroLiquido = new JTextField();
		tFLucroLiquido.setToolTipText("Digite o Valor do Lucro Líquido");
		tFLucroLiquido.setDocument(new MonetarioDocument());
		tFLucroLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFLucroLiquido.setBounds(170, 226, 136, 26);
		contentPanel.add(tFLucroLiquido);
		tFLucroLiquido.setColumns(10);

		tFdAtivoTotal = new JTextField();
		tFdAtivoTotal.setToolTipText("Digite o Valor do Ativo Total");
		tFdAtivoTotal.setDocument(new MonetarioDocument());
		tFdAtivoTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFdAtivoTotal.setColumns(10);
		tFdAtivoTotal.setBounds(170, 263, 136, 26);
		contentPanel.add(tFdAtivoTotal);

		tFReceitaBruta = new JTextField();
		tFReceitaBruta.setToolTipText("Digite o Valor da Receita Bruta");
		tFReceitaBruta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFReceitaBruta.setDocument(new MonetarioDocument());
		tFReceitaBruta.setColumns(10);
		tFReceitaBruta.setBounds(170, 300, 136, 26);
		contentPanel.add(tFReceitaBruta);

		tFPatrimonioLiquido = new JTextField();
		tFPatrimonioLiquido.setToolTipText("Digite o Valor do Patrimônio Líquido");
		tFPatrimonioLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPatrimonioLiquido.setDocument(new MonetarioDocument());
		tFPatrimonioLiquido.setColumns(10);
		tFPatrimonioLiquido.setBounds(170, 337, 136, 26);
		contentPanel.add(tFPatrimonioLiquido);

		tFPorcLucroLiquido = new JTextField();
		tFPorcLucroLiquido.setToolTipText("Digite a Porcentagem corresponde a conta");
		tFPorcLucroLiquido.setDocument(new PorcentagemDocumentMask());
		tFPorcLucroLiquido.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calculoMaterControl.calcularMaterialidade();
			}
		});
		tFPorcLucroLiquido.setBounds(316, 226, 43, 26);
		contentPanel.add(tFPorcLucroLiquido);
		tFPorcLucroLiquido.setColumns(10);
		{
			tFPorcAtivoTotal = new JTextField();
			tFPorcAtivoTotal.setToolTipText("Digite a Porcentagem corresponde a conta");
			tFPorcAtivoTotal.setDocument(new PorcentagemDocumentMask());
			tFPorcAtivoTotal.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calculoMaterControl.calcularMaterialidade();
				}
			});
			tFPorcAtivoTotal.setColumns(10);
			tFPorcAtivoTotal.setBounds(316, 263, 43, 26);
			contentPanel.add(tFPorcAtivoTotal);
		}
		{
			tFPOrcReceitaBruta = new JTextField();
			tFPOrcReceitaBruta.setToolTipText("Digite a Porcentagem corresponde a conta");
			tFPOrcReceitaBruta.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calculoMaterControl.calcularMaterialidade();
				}
			});
			tFPOrcReceitaBruta.setDocument(new PorcentagemDocumentMask());
			tFPOrcReceitaBruta.setColumns(10);
			tFPOrcReceitaBruta.setBounds(316, 300, 43, 26);
			contentPanel.add(tFPOrcReceitaBruta);
		}
		{
			tFPorcPatrimonioLiquido = new JTextField();
			tFPorcPatrimonioLiquido.setToolTipText("Digite a Porcentagem corresponde a conta");
			tFPorcPatrimonioLiquido.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calculoMaterControl.calcularMaterialidade();
				}
			});
			tFPorcPatrimonioLiquido.setDocument(new PorcentagemDocumentMask());
			tFPorcPatrimonioLiquido.setColumns(10);
			tFPorcPatrimonioLiquido.setBounds(316, 337, 43, 26);
			contentPanel.add(tFPorcPatrimonioLiquido);
		}
		{
			lblLogoTopo = new JLabel("");
			lblLogoTopo.setIcon(
					new ImageIcon(TelaCalculoMaterialidade.class.getResource("/icons/vga/vga_fundo_folha.png")));
			lblLogoTopo.setBounds(0, 0, 294, 107);
			contentPanel.add(lblLogoTopo);
		}
		{
			lblClculoDaMaterialidade = new JLabel("  CÁLCULO DA MATERIALIDADE GLOBAL");
			lblClculoDaMaterialidade.setOpaque(true);
			lblClculoDaMaterialidade.setBorder(new LineBorder(Color.BLACK));
			lblClculoDaMaterialidade.setBackground(SystemColor.menu);
			lblClculoDaMaterialidade.setBounds(595, 138, 243, 34);
			contentPanel.add(lblClculoDaMaterialidade);
		}
		{
			lblResultadoMaterialidade = new JLabel("");
			lblResultadoMaterialidade.setBounds(595, 276, 243, 26);
			contentPanel.add(lblResultadoMaterialidade);
		}

		lblResultLucro = new JLabel("");
		lblResultLucro.setBounds(391, 226, 191, 26);
		contentPanel.add(lblResultLucro);

		lblResultAtivo = new JLabel("");
		lblResultAtivo.setBounds(391, 263, 191, 26);
		contentPanel.add(lblResultAtivo);

		lblResultReceita = new JLabel("");
		lblResultReceita.setBounds(391, 300, 191, 26);
		contentPanel.add(lblResultReceita);

		lblResultPatrimonio = new JLabel("");
		lblResultPatrimonio.setBounds(391, 337, 191, 26);
		contentPanel.add(lblResultPatrimonio);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Prezado(a) Auditor(a) confira os valores e clique em OK",
								"Atenção", JOptionPane.WARNING_MESSAGE);

					}
				});
				okButton.setToolTipText("Aperte para confirmar e salvar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hide();

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		calculoMaterControl = new ControllerCalculoMaterialidade(tFLucroLiquido, tFdAtivoTotal, tFReceitaBruta, tFPatrimonioLiquido, tFPorcLucroLiquido, tFPorcAtivoTotal, tFPOrcReceitaBruta, tFPorcPatrimonioLiquido, lblResultLucro, lblResultAtivo, lblResultReceita, lblResultPatrimonio, lblResultadoMaterialidade,balancete);
		{
			lblPorcentagemLucroLiquido = new JLabel("%");
			lblPorcentagemLucroLiquido.setBounds(361, 226, 20, 26);
			contentPanel.add(lblPorcentagemLucroLiquido);
		}
		{
			lblPorcentagemAtivoTotal = new JLabel("%");
			lblPorcentagemAtivoTotal.setBounds(361, 263, 20, 26);
			contentPanel.add(lblPorcentagemAtivoTotal);
		}
		{
			lblPorcentagemReceitaBruta = new JLabel("%");
			lblPorcentagemReceitaBruta.setBounds(361, 300, 20, 26);
			contentPanel.add(lblPorcentagemReceitaBruta);
		}
		{
			lblPorcentagemPatrimonioLiquido = new JLabel("%");
			lblPorcentagemPatrimonioLiquido.setBounds(361, 337, 20, 26);
			contentPanel.add(lblPorcentagemPatrimonioLiquido);
		}
		{
			lblCifraoLucroLiquido = new JLabel("R$:");
			lblCifraoLucroLiquido.setBounds(150, 226, 20, 26);
			contentPanel.add(lblCifraoLucroLiquido);
		}
		{
			lblCifraoAtivoTotal = new JLabel("R$:");
			lblCifraoAtivoTotal.setBounds(150, 263, 20, 26);
			contentPanel.add(lblCifraoAtivoTotal);
		}
		{
			lblCifraoReceitaBruta = new JLabel("R$:");
			lblCifraoReceitaBruta.setBounds(150, 300, 20, 26);
			contentPanel.add(lblCifraoReceitaBruta);
		}
		{
			lblCifraoPatrimonioLiquido = new JLabel("R$:");
			lblCifraoPatrimonioLiquido.setBounds(150, 337, 20, 26);
			contentPanel.add(lblCifraoPatrimonioLiquido);
		}
		{
			lblIconPorcentagem = new JLabel("");
			lblIconPorcentagem.setIcon(new ImageIcon(
					TelaCalculoMaterialidade.class.getResource("/icons/diversos/icons8-percentagem-32.png")));
			lblIconPorcentagem.setBounds(316, 181, 57, 34);
			contentPanel.add(lblIconPorcentagem);
		}
		{
			lblIconDinheiro = new JLabel("");
			lblIconDinheiro.setIcon(new ImageIcon(
					TelaCalculoMaterialidade.class.getResource("/icons/diversos/icons8-dinheiro-32.png")));
			lblIconDinheiro.setBounds(210, 181, 57, 34);
			contentPanel.add(lblIconDinheiro);
		}
	}

	public JTextField getTFLucroLiquido() {
		return tFLucroLiquido;
	}

	public JTextField getTFdAtivoTotal() {
		return tFdAtivoTotal;
	}

	public JTextField getTFReceitaBruta() {
		return tFReceitaBruta;
	}

	public JTextField getTFPatrimonioLiquido() {
		return tFPatrimonioLiquido;
	}

	public JTextField getTFPorcLucroLiquido() {
		return tFPorcLucroLiquido;
	}

	public JTextField getTFPorcAtivoTotal() {
		return tFPorcAtivoTotal;
	}

	public JTextField getTFPOrcReceitaBruta() {
		return tFPOrcReceitaBruta;
	}

	public JTextField getTFPorcPatrimonioLiquido() {
		return tFPorcPatrimonioLiquido;
	}

	public JLabel getLblResultLucro() {
		return lblResultLucro;
	}

	public JLabel getLblResultAtivo() {
		return lblResultAtivo;
	}

	public JLabel getLblResultReceita() {
		return lblResultReceita;
	}

	public JLabel getLblResultPatrimonio() {
		return lblResultPatrimonio;
	}

	public JLabel getLblResultadoMaterialidade() {
		return lblResultadoMaterialidade;
	}
}
