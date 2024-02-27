package model.util;

import java.math.BigDecimal;
import java.util.List;

import model.daoNovo.db.Lancamento;

public class CalculoAH {

	public static Lancamento calcularAHLancamento(Lancamento lancamento) {

		BigDecimal aH = new BigDecimal("0.0");
		if (lancamento.getSaldoFinal() != null && lancamento.getSaldoInicial() != null) {
			if (!lancamento.getSaldoFinal().equals(aH)) {
				if (!lancamento.getSaldoInicial().equals(aH)) {

					aH = lancamento.getSaldoFinal().divide(lancamento.getSaldoInicial(), 1);
					aH = aH.multiply(new BigDecimal("100")).setScale(0);

				}
			}
		}
		lancamento.setaH(aH);
		return lancamento;
	}

	public static List<Lancamento> calcularAH(List<Lancamento> lancamentos) {

		for (Lancamento lancamento : lancamentos) {

			BigDecimal aH = new BigDecimal("0.00");
			if (lancamento.getSaldoFinal() != null && lancamento.getSaldoInicial() != null) {
				if (!lancamento.getSaldoFinal().equals(aH)) {
					if (!lancamento.getSaldoInicial().equals(aH)) {

						aH = lancamento.getSaldoFinal().divide(lancamento.getSaldoInicial(), 1);
						aH = aH.multiply(new BigDecimal("100")).setScale(0);
					}
				}
			}
			lancamento.setaH(aH);
		}
		return lancamentos;
	}

}
