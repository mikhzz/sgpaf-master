package model.boNovo;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import dao.BalanceteDaoImpl;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Procedimento;
import model.util.CalculoAV;

public class BalanceteBO {
	private static final BalanceteDaoImpl balanceteDAO = new BalanceteDaoImpl();
//	private static final Table tableBalancete = balanceteDAO.getTable();
//	private static final LancamentoBO lancamentoBO = new LancamentoBO();
//	private static final Table tableLancamento = lancamentoBO.getTablelancamento();
	private static final ProcedimentoBO procedimentoBO = new ProcedimentoBO();
	private static final FolhamestraBO folhamestaBO = new FolhamestraBO();

	public List<Balancete> consultarTodosBalancetes() {
		return balanceteDAO.pesquisarTodos(1);
	}

//	public List<Balancete> consultarBalancetes(Empresa empresa) {
//		return consultarBalancetes(empresa.getIdEmpresa());
//	}

	public List<Balancete> consultarBalancetes(Long idEmpresa) {
//		Filtro filtro = new Filtro(tableBalancete.getColummJava("idEmpresa"), Comparador.IGUAL, idEmpresa);
		return balanceteDAO.pesquisarPor("id_empresa", idEmpresa.toString(), 1);
	}

//	public Boolean excluirBalancete(Balancete balancete) {
//		return excluirBalancete(balancete.getIdBalancete());
//	}
	public Boolean salvarAlterarBalancete(Balancete pBalancete) {
		
		pBalancete = CalculoAV.calcularTodosAv(pBalancete);
		return balanceteDAO.salvarOuAlterar(pBalancete);
	}

	public Boolean excluirBalancete(Balancete pBalancete) {
//		List<Procedimento> procedimentoList = procedimentoBO.consultarProcedimentos(pBalancete);
//		for (Iterator<Procedimento> iterator = procedimentoList.iterator(); iterator.hasNext();) {
//			Procedimento procedimento = (Procedimento) iterator.next(); 
//			procedimentoBO.excluirProcedimento(procedimento);
//		}
//		List<FolhaMestra> folhamestraList = folhamestaBO.consultarFolhamestras(pBalancete);
//		for (Iterator<FolhaMestra> iterator = folhamestraList.iterator(); iterator.hasNext();) {
//			FolhaMestra folhamestra = (FolhaMestra) iterator.next();
//			folhamestaBO.excluirFolhamestra(folhamestra);
//		}
//		List<Lancamento> usuarioEmpresaList = lancamentoBO.consultarLancamentos(pBalancete);
//		for (Iterator<Lancamento> iterator = usuarioEmpresaList.iterator(); iterator.hasNext();) {
//			Lancamento lancamento = (Lancamento) iterator.next();
//			lancamentoBO.excluirLancamento(lancamento);
//		}
		pBalancete.setAtivo(0);
		return balanceteDAO.salvarOuAlterar(pBalancete);
	}

	public Boolean salvarOuAlterarBalancete(Balancete balancete) {
		return balanceteDAO.salvarOuAlterar(balancete);
	}

	public List<Balancete> consultaComparadorPorColuna(String pColunaBanco, String pComparador, String pConsulta,
			Empresa pEmpresa, int pAtivo) {

		return balanceteDAO.consultaComparadorPorColuna(pColunaBanco, pComparador, pConsulta, pEmpresa, pAtivo);
	}

	public List<Balancete> consultaComparadorPorDate(Date pDataConsulta, Empresa pEmpresa, int inicioFim) {

		return balanceteDAO.pesquisarPorData(pDataConsulta, pEmpresa, inicioFim);
	}
//	public List<Balancete> consultarWhereFiltro(Filtro filtro) {
//		List<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtro);
//		return consultarWhereFiltros(filtros);
//	}
//
//	public List<Balancete> consultarWhereFiltros(List<Filtro> filtros) {
//		List<Balancete> balanceteList;
//		balanceteList = balanceteDAO.consultarWhereFiltros(filtros);
//		return balanceteList;
//	}

//	public static Table getTableBalancete() {
//		return tableBalancete;
//	}

//	public List<Balancete> consultaComparadorPorColuna(Colum coluna, Comparador comparador, String pConsulta,
//			Integer idEmpresa) {
//		List<Filtro> filtros = new ArrayList<Filtro>();
//		if (idEmpresa != null) {
//			Filtro filtroEmpresa = new Filtro(tableBalancete.getColummJava("idEmpresa"), Comparador.IGUAL, idEmpresa);
//			filtros.add(filtroEmpresa);
//		}
//		Filtro filtroColuna;
//		
//		if (comparador.equals(Comparador.LIKE) || comparador.equals(Comparador.NOT_LIKE)) {
//			filtroColuna = new Filtro(coluna, comparador, "%" + pConsulta + "%", Cast.CHAR);
//		} else if (comparador.equals(Comparador.REGEXP) || comparador.equals(Comparador.REGEXP)) {
//			filtroColuna = new Filtro(coluna, comparador,  pConsulta  , Cast.CHAR);
//		} else {
//			Object value =pConsulta;
//			Cast cast=null;
//			try {
//				value= Cast.stringToClass(pConsulta, coluna.getType());
//			}catch (Exception e) {
//				cast=Cast.CHAR;
//			}
//			filtroColuna = new Filtro(coluna, comparador, value,cast);
//		}
//		filtros.add(filtroColuna);
//
//		return this.consultarWhereFiltros(filtros);
//	}
}
