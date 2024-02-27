package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerFolha;
import controller.ControllerSaldoValidado;
import model.daoNovo.db.Lancamento;
import model.util.JNumberFormatField;
import model.util.MonetarioDocument;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaValorDiferenca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNomeEmpresa;
	private JLabel lblDataBase;
	private JLabel lblPeriodo;
	private JLabel lblSaldoTotal;
	private JLabel lblRefptimp;
	private JLabel lblDescricao;
	private JLabel lblDebito;
	private JLabel lblSaldoInicial;
	private JLabel lblCredito;
	private JLabel lblSaldoFinal;
	private JLabel lblAH;
	private JLabel lblAV;
	private JLabel lblDiferenca;
	private JTextField textFieldSaldoValidade;
	private Lancamento lancamento;
	private ControllerSaldoValidado controlSaldo;
	private JButton okButton;
	private JLabel lblBalancete;
	private JLabel lblDataBase2;
	private JLabel lblPeriodo2;
	private JLabel lblSaldoTotal2;
	private JLabel lblLancamento;
	private JLabel lblRefpt;
	private JLabel lblDescrio;
	private JLabel lblDbito;
	private JLabel lblSaldoInicial2;
	private JLabel lblCr;
	private JLabel lblSaldoFinal2;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblAh;
	private JLabel lblAv;
	private JLabel lblSaldoValidado2;
	private JLabel lblSaldoFinal2_1_1;
	private JLabel lblR;
	private JPanel buttonPane;
	private JButton cancelButton;
	private TelaFolhaMestra1 telaFolha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaValorDiferenca dialog = new TelaValorDiferenca(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaValorDiferenca(Lancamento pLancamento) {
		this.lancamento = pLancamento;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
//				lancamento = new Lancamento();
//				controlSaldo.popularTela(pLancamento);
			}
		});
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaValorDiferenca.class.getResource("/icons/vga/Logo VGA.jpg")));
		setTitle("Saldo Validado");
		setBounds(100, 100, 743, 452);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblNomeEmpresa = new JLabel("");
		lblNomeEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomeEmpresa.setBounds(10, 11, 433, 22);
		contentPanel.add(lblNomeEmpresa);

		lblBalancete = new JLabel("Balancete");
		lblBalancete.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblBalancete.setBounds(10, 44, 81, 22);
		contentPanel.add(lblBalancete);

		lblDataBase2 = new JLabel("Data Base");
		lblDataBase2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDataBase2.setBounds(10, 77, 81, 22);
		contentPanel.add(lblDataBase2);

		lblPeriodo2 = new JLabel("Periodo");
		lblPeriodo2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPeriodo2.setBounds(236, 77, 81, 22);
		contentPanel.add(lblPeriodo2);

		lblSaldoTotal2 = new JLabel("Saldo Total");
		lblSaldoTotal2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoTotal2.setBounds(423, 77, 81, 22);
		contentPanel.add(lblSaldoTotal2);

		lblLancamento = new JLabel("Lançamento");
		lblLancamento.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLancamento.setBounds(10, 149, 81, 22);
		contentPanel.add(lblLancamento);

		lblDataBase = new JLabel("");
		lblDataBase.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDataBase.setBounds(10, 100, 81, 22);
		contentPanel.add(lblDataBase);

		lblPeriodo = new JLabel("");
		lblPeriodo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPeriodo.setBounds(178, 100, 148, 22);
		contentPanel.add(lblPeriodo);

		lblSaldoTotal = new JLabel("");
		lblSaldoTotal.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoTotal.setBounds(423, 100, 202, 22);
		contentPanel.add(lblSaldoTotal);

		lblRefpt = new JLabel("Ref. PT");
		lblRefpt.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRefpt.setBounds(10, 182, 45, 22);
		contentPanel.add(lblRefpt);

		lblRefptimp = new JLabel("");
		lblRefptimp.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRefptimp.setBounds(10, 200, 45, 22);
		contentPanel.add(lblRefptimp);

		lblDescrio = new JLabel("Descrição");
		lblDescrio.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDescrio.setBounds(114, 182, 64, 22);
		contentPanel.add(lblDescrio);

		lblDescricao = new JLabel("");
		lblDescricao.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDescricao.setBounds(114, 200, 354, 22);
		contentPanel.add(lblDescricao);

		lblDbito = new JLabel("Débito");
		lblDbito.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDbito.setBounds(188, 248, 64, 22);
		contentPanel.add(lblDbito);

		lblDebito = new JLabel("");
		lblDebito.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDebito.setBounds(188, 267, 168, 22);
		contentPanel.add(lblDebito);

		lblSaldoInicial2 = new JLabel("Saldo Inicial");
		lblSaldoInicial2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoInicial2.setBounds(10, 248, 94, 22);
		contentPanel.add(lblSaldoInicial2);

		lblSaldoInicial = new JLabel("");
		lblSaldoInicial.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoInicial.setBounds(10, 267, 168, 22);
		contentPanel.add(lblSaldoInicial);

		lblCr = new JLabel("Crédito");
		lblCr.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCr.setBounds(366, 248, 64, 22);
		contentPanel.add(lblCr);

		lblCredito = new JLabel("");
		lblCredito.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCredito.setBounds(366, 267, 168, 22);
		contentPanel.add(lblCredito);

		lblSaldoFinal2 = new JLabel("Saldo Final");
		lblSaldoFinal2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoFinal2.setBounds(544, 248, 81, 22);
		contentPanel.add(lblSaldoFinal2);

		lblSaldoFinal = new JLabel("");
		lblSaldoFinal.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoFinal.setBounds(544, 267, 168, 22);
		contentPanel.add(lblSaldoFinal);

		separator = new JSeparator();
		separator.setBounds(-25, 133, 996, 29);
		contentPanel.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(-12, 37, 996, 29);
		contentPanel.add(separator_1);

		lblAh = new JLabel("AH");
		lblAh.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAh.setBounds(10, 300, 36, 22);
		contentPanel.add(lblAh);

		lblAH = new JLabel("");
		lblAH.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAH.setBounds(10, 321, 100, 22);
		contentPanel.add(lblAH);

		lblAv = new JLabel("AV");
		lblAv.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAv.setBounds(114, 300, 36, 22);
		contentPanel.add(lblAv);

		lblAV = new JLabel("");
		lblAV.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAV.setBounds(114, 321, 100, 22);
		contentPanel.add(lblAV);

		lblSaldoValidado2 = new JLabel("Saldo Validado");
		lblSaldoValidado2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoValidado2.setBounds(299, 300, 100, 22);
		contentPanel.add(lblSaldoValidado2);

		lblSaldoFinal2_1_1 = new JLabel("Diferença");
		lblSaldoFinal2_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSaldoFinal2_1_1.setBounds(544, 300, 105, 22);
		contentPanel.add(lblSaldoFinal2_1_1);

		lblDiferenca = new JLabel("");
		lblDiferenca.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDiferenca.setBounds(544, 321, 148, 22);
		contentPanel.add(lblDiferenca);

		lblR = new JLabel("R$");
		lblR.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblR.setBounds(276, 321, 24, 22);
		contentPanel.add(lblR);

		textFieldSaldoValidade = new JTextField(15);
		textFieldSaldoValidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				controlSaldo.calcularDiferenca();
				if (e.getKeyCode() == 10) {
					okButton.doClick();
				}
			}
		});
		textFieldSaldoValidade.setDocument(new MonetarioDocument());
		textFieldSaldoValidade.setFont(new Font("Calibri", Font.PLAIN, 14));
		textFieldSaldoValidade.setBounds(299, 321, 194, 22);
		contentPanel.add(textFieldSaldoValidade);
		textFieldSaldoValidade.setColumns(10);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controlSaldo.salvarAction();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		controlSaldo = new ControllerSaldoValidado(lblNomeEmpresa, lblDataBase, lblPeriodo, lblSaldoTotal, lblRefptimp,
				lblDescricao, lblDebito, lblSaldoInicial, lblCredito, lblSaldoFinal, lblAH, lblAV, lblDiferenca,
				textFieldSaldoValidade);
		
	}

	public ControllerSaldoValidado getControlSaldo() {
		return controlSaldo;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		System.out.println("============================TESTE SET LANCAMENTO TELA VALOR DIFERENÇA"
				+ lancamento.getBalancete().getEmpresa().getNome());
		this.lancamento = lancamento;
	}

	public void setControlSaldo(ControllerSaldoValidado controlSaldo) {
		this.controlSaldo = controlSaldo;
	}

	public JLabel getLblNomeEmpresa() {
		return lblNomeEmpresa;
	}

	public JLabel getLblDataBase() {
		return lblDataBase;
	}

	public JLabel getLblPeriodo() {
		return lblPeriodo;
	}

	public JLabel getLblSaldoTotal() {
		return lblSaldoTotal;
	}

	public JLabel getLblRefptimp() {
		return lblRefptimp;
	}

	public JLabel getLblDescricao() {
		return lblDescricao;
	}

	public JLabel getLblDebito() {
		return lblDebito;
	}

	public JLabel getLblSaldoInicial() {
		return lblSaldoInicial;
	}

	public JLabel getLblCredito() {
		return lblCredito;
	}

	public JLabel getLblSaldoFinal() {
		return lblSaldoFinal;
	}

	public JLabel getLblAH() {
		return lblAH;
	}

	public JLabel getLblAV() {
		return lblAV;
	}

	public JLabel getLblDiferenca() {
		return lblDiferenca;
	}

	public JButton getOkButton() {
		return okButton;
	}
}
