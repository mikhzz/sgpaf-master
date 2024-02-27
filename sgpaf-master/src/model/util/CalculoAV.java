package model.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.LancamentoDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;

public class CalculoAV {
	
	public static Balancete calcularTodosAv(Balancete pBalancete) {
		LancamentoDaoImpl lancamentoDao = new LancamentoDaoImpl();

		List<Lancamento> lancamentosRef = lancamentoDao.consultaGroupByRefPT(pBalancete);
		List<Lancamento> lancamentosAv = new ArrayList<Lancamento>();

		for (Lancamento lancamento : lancamentosRef) {
			if (!lancamento.getRefpt().equals("")) {
				String refpt = lancamento.getRefpt();
				
				List<Lancamento> lancamentos =  pBalancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals(refpt)).collect(Collectors.toList());
				lancamentos = calcularAV(lancamentos);
				for (Lancamento lancamentoAV : lancamentos) {
					lancamentosAv.add(lancamentoAV);
				}
			}
		}
		pBalancete.setLancamentos(lancamentosAv);
		return pBalancete;
	}

	public static List<Lancamento> calcularAV(List<Lancamento> lancamentos) {
		
		BigDecimal total = lancamentos.stream().map((Lancamento p) -> p.getDebito()).reduce(BigDecimal.ZERO,BigDecimal::add);

		for (Lancamento lancamento : lancamentos) {
			BigDecimal aV = new BigDecimal("0.00");
			if (!lancamento.getDebito().equals(aV)) {
				if (!total.equals(aV)) {
					aV = lancamento.getDebito().divide(total, 1);
					aV = aV.multiply(new BigDecimal("100")).setScale(0);
				}
			}
			lancamento.setaV(aV);
		}
		return lancamentos;
	}

}
