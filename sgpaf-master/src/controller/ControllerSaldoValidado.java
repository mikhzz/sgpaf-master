package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.boNovo.LancamentoBO;
import model.daoNovo.db.Lancamento;
import model.util.ConversorData;
import view.TelaFolhaMestra1;

public class ControllerSaldoValidado {

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
	private JTextField textField;
	private Lancamento lancamento;
	private NumberFormat decimalFormat = NumberFormat.getCurrencyInstance();
	private DecimalFormat decimalFormatNumero = new DecimalFormat("#,##0.00");
//	private DecimalFormat decimalFormatBD = new DecimalFormat("#.##0,00");
	private static LancamentoBO lancamentoBO = new LancamentoBO();

	public ControllerSaldoValidado(JLabel lblNomeEmpresa, JLabel lblDataBase, JLabel lblPeriodo, JLabel lblSaldoTotal,
			JLabel lblRefptimp, JLabel lblDescricao, JLabel lblDebito, JLabel lblSaldoInicial, JLabel lblCredito,
			JLabel lblSaldoFinal, JLabel lblAH, JLabel lblAV, JLabel lblDiferenca, JTextField textField) {
		super();
		this.lblNomeEmpresa = lblNomeEmpresa;
		this.lblDataBase = lblDataBase;
		this.lblPeriodo = lblPeriodo;
		this.lblSaldoTotal = lblSaldoTotal;
		this.lblRefptimp = lblRefptimp;
		this.lblDescricao = lblDescricao;
		this.lblDebito = lblDebito;
		this.lblSaldoInicial = lblSaldoInicial;
		this.lblCredito = lblCredito;
		this.lblSaldoFinal = lblSaldoFinal;
		this.lblAH = lblAH;
		this.lblAV = lblAV;
		this.lblDiferenca = lblDiferenca;
		this.textField = textField;

	}
//	public ControllerSaldoValidado() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public void popularTela(Lancamento pLancamento) {
		this.lancamento = pLancamento;

		System.out.println("===========================================TESTE DE PARAMETRO LANCAMENTO: "
				+ lancamento.getBalancete().getEmpresa().getNome());
		String nomeEmpresaStr = lancamento.getBalancete().getEmpresa().getNome();
		lblNomeEmpresa.setText(lancamento.getBalancete().getEmpresa().getNome());
		String dataBaseStr = lancamento.getBalancete().getDatabase().toString();
		lblDataBase.setText(dataBaseStr);
		String periodoStr = ConversorData.dataBancoParaUsuario(lancamento.getBalancete().getDataBalancoInicio()) + " - "
				+ ConversorData.dataBancoParaUsuario(lancamento.getBalancete().getDataBalancoFim());
		lblPeriodo.setText(periodoStr);
		String saldoTotalStr = decimalFormat.format(lancamento.getBalancete().getSaldototal());
		lblSaldoTotal.setText(saldoTotalStr);
		String refPTStr = lancamento.getRefpt();
		lblRefptimp.setText(refPTStr);
		String descricaoStr = lancamento.getDescricao();
		lblDescricao.setText(descricaoStr);
		String debitoString = decimalFormat.format(lancamento.getDebito());
		lblDebito.setText(debitoString);
		String saldoIniStr = decimalFormat.format(lancamento.getSaldoInicial());
		lblSaldoInicial.setText(saldoIniStr);
		String creditoStr = decimalFormat.format(lancamento.getCredito());
		lblCredito.setText(creditoStr);
		String saldoFinalStr = decimalFormat.format(lancamento.getSaldoFinal());
		lblSaldoFinal.setText(saldoFinalStr);
		String aHStr = lancamento.getaH().setScale(0) + "%";
		lblAH.setText(aHStr);
		String aVStr = lancamento.getaV().setScale(0) + "%";
		lblAV.setText(aVStr);
		String saldoValidadoStr = decimalFormatNumero.format(lancamento.getSaldoValidado());
		textField.setText(saldoValidadoStr);
		String diferencaStr = decimalFormat.format(lancamento.getDiferenca());
		lblDiferenca.setText(diferencaStr);
	}

	public void calcularDiferenca() {

		String valorValiStr = textField.getText();

		valorValiStr = valorValiStr.replace(".", "");
		valorValiStr = valorValiStr.replace(",", ".");

		BigDecimal valorVali = new BigDecimal(valorValiStr);
		BigDecimal valorFirenca = new BigDecimal("0.00");
//		if () {
//
//		}
		valorFirenca = lancamento.getSaldoFinal().subtract(valorVali);
		lblDiferenca.setText(decimalFormat.format(valorFirenca));
		lancamento.setDiferenca(valorFirenca);
		lancamento.setSaldoValidado(valorVali);
	}

	public void salvarAction() {
		lancamentoBO.salvarOuAlterarLancamento(lancamento);
		lancamento = null;
	}

	public JLabel getLblNomeEmpresa() {
		return lblNomeEmpresa;
	}

	public void setLblNomeEmpresa(JLabel lblNomeEmpresa) {
		this.lblNomeEmpresa = lblNomeEmpresa;
	}

	public JLabel getLblDataBase() {
		return lblDataBase;
	}

	public void setLblDataBase(JLabel lblDataBase) {
		this.lblDataBase = lblDataBase;
	}

	public JLabel getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(JLabel lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public JLabel getLblSaldoTotal() {
		return lblSaldoTotal;
	}

	public void setLblSaldoTotal(JLabel lblSaldoTotal) {
		this.lblSaldoTotal = lblSaldoTotal;
	}

	public JLabel getLblRefptimp() {
		return lblRefptimp;
	}

	public void setLblRefptimp(JLabel lblRefptimp) {
		this.lblRefptimp = lblRefptimp;
	}

	public JLabel getLblDescricao() {
		return lblDescricao;
	}

	public void setLblDescricao(JLabel lblDescricao) {
		this.lblDescricao = lblDescricao;
	}

	public JLabel getLblDebito() {
		return lblDebito;
	}

	public void setLblDebito(JLabel lblDebito) {
		this.lblDebito = lblDebito;
	}

	public JLabel getLblSaldoInicial() {
		return lblSaldoInicial;
	}

	public void setLblSaldoInicial(JLabel lblSaldoInicial) {
		this.lblSaldoInicial = lblSaldoInicial;
	}

	public JLabel getLblCredito() {
		return lblCredito;
	}

	public void setLblCredito(JLabel lblCredito) {
		this.lblCredito = lblCredito;
	}

	public JLabel getLblSaldoFinal() {
		return lblSaldoFinal;
	}

	public void setLblSaldoFinal(JLabel lblSaldoFinal) {
		this.lblSaldoFinal = lblSaldoFinal;
	}

	public JLabel getLblAH() {
		return lblAH;
	}

	public void setLblAH(JLabel lblAH) {
		this.lblAH = lblAH;
	}

	public JLabel getLblAV() {
		return lblAV;
	}

	public void setLblAV(JLabel lblAV) {
		this.lblAV = lblAV;
	}

	public JLabel getLblDiferenca() {
		return lblDiferenca;
	}

	public void setLblDiferenca(JLabel lblDiferenca) {
		this.lblDiferenca = lblDiferenca;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
