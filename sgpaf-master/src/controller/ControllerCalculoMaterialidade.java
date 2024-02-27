package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.MaterialidadeDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Materialidade;

public class ControllerCalculoMaterialidade {

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
	private String lucroLiqStr;
	private String ativoTotalStr;
	private String receitaBrutaStr;
	private String patrimonioLiquidoStr;
	private String porcLucroLiquido;
	private String porcAtivoTotal;
	private String porcReceitaBruta;
	private String porcPatrimonio;
	private BigDecimal bDlucroLiq;
	private BigDecimal bDativoTotal;
	private BigDecimal bDreceitaBruta;
	private BigDecimal bDpatrimonioLiquido;
	private BigDecimal bDporcLucroLiquido;
	private BigDecimal bDporcAtivoTotal;
	private BigDecimal bDporcReceitaBruta;
	private BigDecimal bDporcPatrimonio;
	private BigDecimal totalAtivo;
	private BigDecimal totalLucro;
	private BigDecimal totalPatrimonio;
	private BigDecimal totalReceita;
	private BigDecimal totalmaterialidade;
	private Balancete balancete;
	private Materialidade materialidade;
	private static final MaterialidadeDaoImpl materialidadeDAO = new MaterialidadeDaoImpl();

	private NumberFormat decimalFormat = NumberFormat.getCurrencyInstance();
	private DecimalFormat decimalFormatNumero = new DecimalFormat("#,##0.00");

	public ControllerCalculoMaterialidade(JTextField tFLucroLiquido, JTextField tFdAtivoTotal,
			JTextField tFReceitaBruta, JTextField tFPatrimonioLiquido, JTextField tFPorcLucroLiquido,
			JTextField tFPorcAtivoTotal, JTextField tFPOrcReceitaBruta, JTextField tFPorcPatrimonioLiquido,
			JLabel lblResultLucro, JLabel lblResultAtivo, JLabel lblResultReceita, JLabel lblResultPatrimonio,
			JLabel lblResultadoMaterialidade, Balancete balancete) {
		super();
		this.tFLucroLiquido = tFLucroLiquido;
		this.tFdAtivoTotal = tFdAtivoTotal;
		this.tFReceitaBruta = tFReceitaBruta;
		this.tFPatrimonioLiquido = tFPatrimonioLiquido;
		this.tFPorcLucroLiquido = tFPorcLucroLiquido;
		this.tFPorcAtivoTotal = tFPorcAtivoTotal;
		this.tFPOrcReceitaBruta = tFPOrcReceitaBruta;
		this.tFPorcPatrimonioLiquido = tFPorcPatrimonioLiquido;
		this.lblResultLucro = lblResultLucro;
		this.lblResultAtivo = lblResultAtivo;
		this.lblResultReceita = lblResultReceita;
		this.lblResultPatrimonio = lblResultPatrimonio;
		this.lblResultadoMaterialidade = lblResultadoMaterialidade;
		this.balancete = balancete;
	}

	public void calcularMaterialidade() {

		totalAtivo = new BigDecimal("0.00");
		totalLucro = new BigDecimal("0.00");
		totalPatrimonio = new BigDecimal("0.00");
		totalReceita = new BigDecimal("0.00");

		formatarTextFieldParaString();

		bDativoTotal = new BigDecimal(verificarStringVaziaValorCalculo(ativoTotalStr));
		bDlucroLiq = new BigDecimal(verificarStringVaziaValorCalculo(lucroLiqStr));
		bDpatrimonioLiquido = new BigDecimal(verificarStringVaziaValorCalculo(patrimonioLiquidoStr));
		bDreceitaBruta = new BigDecimal(verificarStringVaziaValorCalculo(receitaBrutaStr));

		bDporcAtivoTotal = new BigDecimal(verificarStringVaziaValorCalculo(porcAtivoTotal));
		bDporcLucroLiquido = new BigDecimal(verificarStringVaziaValorCalculo(porcLucroLiquido));
		bDporcPatrimonio = new BigDecimal(verificarStringVaziaValorCalculo(porcPatrimonio));
		bDporcReceitaBruta = new BigDecimal(verificarStringVaziaValorCalculo(porcReceitaBruta));

		totalmaterialidade = calcularResultadoFinal();

		lblResultAtivo.setText(decimalFormat.format(totalAtivo));
		lblResultLucro.setText(decimalFormat.format(totalLucro));
		lblResultPatrimonio.setText(decimalFormat.format(totalPatrimonio));
		lblResultReceita.setText(decimalFormat.format(totalReceita));
		lblResultadoMaterialidade.setText(decimalFormat.format(totalmaterialidade));

	}

	private BigDecimal calcularResultadoFinal() {

		BigDecimal bdCem = new BigDecimal("100");
		BigDecimal bdzero = new BigDecimal("0.00");
		BigDecimal somatotal = new BigDecimal("0.00");
		BigDecimal qtdDivide = new BigDecimal("0");

		BigDecimal resultMaterialidade = new BigDecimal("0.00");

		List<BigDecimal> somasTotais = new ArrayList<BigDecimal>();

		try {
			totalAtivo = bDativoTotal.multiply(bDporcAtivoTotal).setScale(2);
			totalAtivo = totalAtivo.divide(bdCem, 1);
		} catch (Exception e) {
			totalAtivo = BigDecimal.ZERO;
		}
		somasTotais.add(totalAtivo);
		try {
			totalLucro = bDlucroLiq.multiply(bDporcLucroLiquido).setScale(2);
			totalLucro = totalLucro.divide(bdCem, 1);

		} catch (Exception e) {
			totalLucro = BigDecimal.ZERO;
		}
		somasTotais.add(totalLucro);
		try {
			totalPatrimonio = bDpatrimonioLiquido.multiply(bDporcPatrimonio).setScale(2);
			totalPatrimonio = totalPatrimonio.divide(bdCem, 1);

		} catch (Exception e) {
			totalPatrimonio = BigDecimal.ZERO;
		}
		somasTotais.add(totalPatrimonio);
		try {
			totalReceita = bDreceitaBruta.multiply(bDporcReceitaBruta).setScale(2);
			totalReceita = totalReceita.divide(bdCem, 1);
		} catch (Exception e) {
			totalReceita = BigDecimal.ZERO;
		}
		somasTotais.add(totalReceita);

		for (BigDecimal total : somasTotais) {
			if (!total.equals(bdzero)) {
				somatotal = somatotal.add(total);
				qtdDivide = qtdDivide.add(new BigDecimal("1"));
			}
		}
		if (!somatotal.equals(bdzero)) {
			resultMaterialidade = somatotal.divide(qtdDivide, 1);
		}

		return resultMaterialidade;
	}

	private String verificarStringVaziaValorCalculo(String stringVerificar) {

		if (stringVerificar.isEmpty()) {
			return "0.00";
		} else {
			return stringVerificar;
		}
	}

	private void formatarTextFieldParaString() {
		lucroLiqStr = tFLucroLiquido.getText();
		ativoTotalStr = tFdAtivoTotal.getText();
		receitaBrutaStr = tFReceitaBruta.getText();
		patrimonioLiquidoStr = tFPatrimonioLiquido.getText();
		porcLucroLiquido = tFPorcLucroLiquido.getText();
		porcAtivoTotal = tFPorcAtivoTotal.getText();
		porcReceitaBruta = tFPOrcReceitaBruta.getText();
		porcPatrimonio = tFPorcPatrimonioLiquido.getText();

		lucroLiqStr = lucroLiqStr.replace(".", "");
		lucroLiqStr = lucroLiqStr.replace(",", ".");
		ativoTotalStr = ativoTotalStr.replace(".", "");
		ativoTotalStr = ativoTotalStr.replace(",", ".");
		receitaBrutaStr = receitaBrutaStr.replace(".", "");
		receitaBrutaStr = receitaBrutaStr.replace(",", ".");
		patrimonioLiquidoStr = patrimonioLiquidoStr.replace(".", "");
		patrimonioLiquidoStr = patrimonioLiquidoStr.replace(",", ".");
	}

	public void popularTela() {
		materialidade = materialidadeDAO.pesquisarPorBalancete(balancete);
		if (materialidade == null) {
			materialidade = new Materialidade();

			materialidade.setBalancete(balancete);
			materialidadeDAO.salvarOuAlterar(materialidade);
		}
		setarTextoTFcomMonetarioDocument(materialidade.getAtivoTotal().toString(), tFdAtivoTotal);
		setarTextoTFcomMonetarioDocument(materialidade.getAtivoPorc().toString(), tFPorcAtivoTotal);
		setarTextoTFcomMonetarioDocument(materialidade.getLucro().toString(), tFLucroLiquido);
		setarTextoTFcomMonetarioDocument(materialidade.getLucro_porc().toString(), tFPorcLucroLiquido);
		setarTextoTFcomMonetarioDocument(materialidade.getPatrimonio().toString(), tFPatrimonioLiquido);
		setarTextoTFcomMonetarioDocument(materialidade.getPatrimonio_porc().toString(), tFPorcPatrimonioLiquido);
		setarTextoTFcomMonetarioDocument(materialidade.getReceita().toString(), tFReceitaBruta);
		setarTextoTFcomMonetarioDocument(materialidade.getReceita_porc().toString(), tFPOrcReceitaBruta);
		calcularMaterialidade();

	}

	private void setarTextoTFcomMonetarioDocument(String pValor, JTextField tf) {
		try {
			if (pValor.substring(pValor.length() - 2, pValor.length() - 1).equals(".")) {
				tf.setText(pValor.replace(".", ""));
			} else {
				tf.setText(pValor.replace(".", ""));
			}

		} catch (StringIndexOutOfBoundsException ex) {
			if (pValor.substring(pValor.length() - 2, pValor.length() - 1).equals(".")) {
				tf.setText(pValor.replace(".", ""));
			} else {
				tf.setText(pValor.replace(".", ""));
			}
		}
	}

	public void salvarAction() {
		materialidade.setAtivoPorc(bDporcAtivoTotal);
		materialidade.setAtivoTotal(bDativoTotal);
		materialidade.setLucro(bDlucroLiq);
		materialidade.setLucro_porc(bDporcLucroLiquido);
		materialidade.setMaterialidadeGlobal(totalmaterialidade);
		materialidade.setPatrimonio(bDpatrimonioLiquido);
		materialidade.setPatrimonio_porc(bDporcPatrimonio);
		materialidade.setReceita(bDreceitaBruta);
		materialidade.setReceita_porc(bDporcReceitaBruta);

		if (materialidadeDAO.salvarOuAlterar(materialidade)) {
			JOptionPane.showMessageDialog(null, "Salvei com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public JTextField gettFLucroLiquido() {
		return tFLucroLiquido;
	}

	public void settFLucroLiquido(JTextField tFLucroLiquido) {
		this.tFLucroLiquido = tFLucroLiquido;
	}

	public JTextField gettFdAtivoTotal() {
		return tFdAtivoTotal;
	}

	public void settFdAtivoTotal(JTextField tFdAtivoTotal) {
		this.tFdAtivoTotal = tFdAtivoTotal;
	}

	public JTextField gettFReceitaBruta() {
		return tFReceitaBruta;
	}

	public void settFReceitaBruta(JTextField tFReceitaBruta) {
		this.tFReceitaBruta = tFReceitaBruta;
	}

	public JTextField gettFPatrimonioLiquido() {
		return tFPatrimonioLiquido;
	}

	public void settFPatrimonioLiquido(JTextField tFPatrimonioLiquido) {
		this.tFPatrimonioLiquido = tFPatrimonioLiquido;
	}

	public JTextField gettFPorcLucroLiquido() {
		return tFPorcLucroLiquido;
	}

	public void settFPorcLucroLiquido(JTextField tFPorcLucroLiquido) {
		this.tFPorcLucroLiquido = tFPorcLucroLiquido;
	}

	public JTextField gettFPorcAtivoTotal() {
		return tFPorcAtivoTotal;
	}

	public void settFPorcAtivoTotal(JTextField tFPorcAtivoTotal) {
		this.tFPorcAtivoTotal = tFPorcAtivoTotal;
	}

	public JTextField gettFPOrcReceitaBruta() {
		return tFPOrcReceitaBruta;
	}

	public void settFPOrcReceitaBruta(JTextField tFPOrcReceitaBruta) {
		this.tFPOrcReceitaBruta = tFPOrcReceitaBruta;
	}

	public JTextField gettFPorcPatrimonioLiquido() {
		return tFPorcPatrimonioLiquido;
	}

	public void settFPorcPatrimonioLiquido(JTextField tFPorcPatrimonioLiquido) {
		this.tFPorcPatrimonioLiquido = tFPorcPatrimonioLiquido;
	}

	public JLabel getLblResultLucro() {
		return lblResultLucro;
	}

	public void setLblResultLucro(JLabel lblResultLucro) {
		this.lblResultLucro = lblResultLucro;
	}

	public JLabel getLblResultAtivo() {
		return lblResultAtivo;
	}

	public void setLblResultAtivo(JLabel lblResultAtivo) {
		this.lblResultAtivo = lblResultAtivo;
	}

	public JLabel getLblResultReceita() {
		return lblResultReceita;
	}

	public void setLblResultReceita(JLabel lblResultReceita) {
		this.lblResultReceita = lblResultReceita;
	}

	public JLabel getLblResultPatrimonio() {
		return lblResultPatrimonio;
	}

	public void setLblResultPatrimonio(JLabel lblResultPatrimonio) {
		this.lblResultPatrimonio = lblResultPatrimonio;
	}

	public JLabel getLblResultadoMaterialidade() {
		return lblResultadoMaterialidade;
	}

	public void setLblResultadoMaterialidade(JLabel lblResultadoMaterialidade) {
		this.lblResultadoMaterialidade = lblResultadoMaterialidade;
	}

}
