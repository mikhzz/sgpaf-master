package model.boNovo;

import java.util.List;
import dao.ProcedimentoDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Procedimento;

public class ProcedimentoBO {

	private static final ProcedimentoDaoImpl procedimentoDAO = new ProcedimentoDaoImpl();
//	private static final Table tableProcedimento = procedimentoDAO.getTable();

//	public static Table getTableprocedimento() {
//		return tableProcedimento;
//	}

	public List<Procedimento> consultarTodosProcedimentos() {
		return procedimentoDAO.listarTodos();
	}

//	public List<Procedimento> consultarTodosProcedimentos(String refpt) {
//		Filtro filtroRefpt = new Filtro(tableProcedimento.getColummJava("refpt"), Comparador.IGUAL, refpt);
//		return procedimentoDAO.consultaComparadorPorColuna("refpt", "=", pConsulta, pBalancete)(filtroRefpt);
//	}

//	public List<Procedimento> consultarProcedimentos(Balancete balancete, String refpt) {
//		return consultarProcedimentos(balancete.getIdBalancete(), refpt);
//	}

	public Procedimento consultarProcedimentos(Balancete pBalancete, String pConsultaRefpt) {
//		Filtro filtroBalancete = new Filtro(tableProcedimento.getColummJava("idBalancete"), Comparador.IGUAL, idBalancete);
//		Filtro filtroRefpt = new Filtro(tableProcedimento.getColummJava("refpt"), Comparador.IGUAL, refpt);

//		List<Filtro> filtroList = new ArrayList<Filtro>();
//		filtroList.add(filtroRefpt);
//		filtroList.add(filtroBalancete);
		String colunaJava ="refpt";
		return procedimentoDAO.pesquisaPorRef(pConsultaRefpt, pBalancete);
	}
	public List<Procedimento> consultarProcedimentos(Balancete pBalancete) {
//		Filtro filtroBalancete = new Filtro(tableProcedimento.getColummJava("idBalancete"), Comparador.IGUAL, idBalancete);

//		List<Filtro> filtroList = new ArrayList<Filtro>();
//		filtroList.add(filtroBalancete);

		return procedimentoDAO.listarTodosPorBalancete(pBalancete);
	}

//	public Boolean excluirProcedimento(Procedimento procedimento) {
//		return excluirProcedimento(procedimento.getIdProcedimento());
//	}

	public boolean excluirProcedimento(Procedimento pProcedimento) {
		return procedimentoDAO.excluir(pProcedimento);
	}

	public boolean salvarOuAlterarProcedimento(Procedimento procedimento) {
		return procedimentoDAO.salvarOuAlterar(procedimento);
	}

//	public boolean alterarProcedimento(Procedimento procedimento) {
//		return procedimentoDAO.alterar(procedimento, procedimento.getIdProcedimento());
//	}

}
