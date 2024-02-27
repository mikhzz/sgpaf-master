package model.boNovo;

import java.util.ArrayList;
import java.util.List;

import dao.LancamentoDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;
import model.util.CalculoAV;

public class LancamentoBO {

	private static final LancamentoDaoImpl lancamentoDAO = new LancamentoDaoImpl();
//	private static final Table tableLancamento = lancamentoDAO.getTable();

//	public static Table getTablelancamento() {
//		return tableLancamento;
//	}

	public List<Lancamento> consultarTodosLancamentos() {
		return lancamentoDAO.consultarTodosLancamentos();
	}

//	public List<Lancamento> consultarLancamentos(Balancete balancete) {
//		return consultarLancamentos(balancete);
//	}

	public List<Lancamento> consultarLancamentos(Balancete pBalancete) {
//		Filtro filtro = new Filtro(tableLancamento.getColummJava("idBalancete"), Comparador.IGUAL, idBalancete);
		return lancamentoDAO.consultarTodosBalancete(pBalancete);
	}
	public List<Lancamento> consultarLancamentosRefPt(Balancete pBalancete, String pConsultaRefPt) {

		List<Lancamento> lancamentos = lancamentoDAO.pesquisarPor("refpt",pConsultaRefPt,pBalancete);
//		lancamentos = lancamentoDAO.calcularAV(lancamentos);
		
		return lancamentos;
	}
	public List<Lancamento> pesquisarPor(Balancete pBalancete, String pConsulta, String colunaJava) {
		return lancamentoDAO.pesquisarPor(colunaJava,pConsulta,pBalancete);
	}

	public Boolean excluirLancamento(Lancamento pLancamento) {
		BalanceteBO balanceteBO = new BalanceteBO();
		Balancete balancete = pLancamento.getBalancete();
		balancete.getLancamentos().remove(pLancamento);
		lancamentoDAO.excluir(pLancamento);
		
		return balanceteBO.salvarAlterarBalancete(balancete);
	}

	public Boolean salvarOuAlterarLancamento(Lancamento lancamento) {
		lancamentoDAO.salvarOuAlterar(lancamento);
		BalanceteBO balanceteBO = new BalanceteBO();
		Balancete balancete = lancamento.getBalancete();
		if(balancete.getLancamentos()!=null) {
			balancete.getLancamentos().add(lancamento);
		}else {
			List<Lancamento>lancamentos = new ArrayList<Lancamento>();
			lancamentos.add(lancamento);
			balancete.setLancamentos(lancamentos);
		}
		return balanceteBO.salvarAlterarBalancete(balancete);
	}

}
