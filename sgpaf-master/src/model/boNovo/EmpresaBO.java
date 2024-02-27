package model.boNovo;

import java.util.ArrayList;
import java.util.List;

import dao.EmpresaDaoImpl;
import model.daoNovo.db.Empresa;
import model.util.GeradorDePlanilha;

public class EmpresaBO {
	private static final EmpresaDaoImpl empresaDAO = new EmpresaDaoImpl();
//	private static final Table tableEmpresa = empresaDAO.getTable();

	public Empresa consultarPorId(Long pLongIdDigitado , int pAtivo) {
		Empresa empresa = empresaDAO.pesquisarPorID(pLongIdDigitado, pAtivo);
		return empresa;
	}

	public String excluirEmpresaBO(Empresa pEmpresa) {

		String mensagem = "";

		if (empresaDAO.salvarOuAlterar(pEmpresa)) {

			mensagem = "Empresa Excluida com Sucesso";

		} else {

			mensagem = "Erro ao excluir Empresa";

		}

		return mensagem;

	}

	public boolean validarCNPJJaCadastrado(String stringCnpj) {
		
//		Filtro filtroCnpj = new Filtro(tableEmpresa.getColummJava("cnpj"), Comparador.IGUAL, stringCnpj);
		if (!empresaDAO.pesquisarPor("cnpj", stringCnpj, 1).isEmpty()) {

			 return false;
		}else {
			return true;
		}
		
	}
	
	public String salvarEmpresaBO(Empresa empresa) {
		
		StringBuilder mensagem = new StringBuilder();


			if (empresaDAO.salvarOuAlterar(empresa)) {

				mensagem.append("Empresa cadastrada com sucesso");

			} else {

				mensagem.append("Erro ao cadastrar empresa");

			}

		return mensagem.toString();
	}

	public String alterarEmpresaBO(Empresa pEmpresa) {

		String mensagem = "";
		if (empresaDAO.salvarOuAlterar(pEmpresa)) {
			mensagem = "Empresa atualizada com Sucesso";

		} else {

			mensagem = "Erro ao Atualizar Empresa";
		}

		return mensagem;

	}

	

	public List<Empresa> consultarTodas() {

		return empresaDAO.pesquisarTodos(1);

	}

//	public List<Empresa> consultarTodasAtivas() {
//		Filtro filtroEmpresaAtiva = new Filtro(tableEmpresa.getColumm("ATIVO"), Comparador.IGUAL, 1);
//
//		return empresaDAO.consultarWhereFiltro(filtroEmpresaAtiva);
//
//	}

	public List<String> consultarTipos() {
		List<String> tipos = new ArrayList<>();
		for (int i = 0; i < empresaDAO.consultarTipo().size(); i++) {
			tipos.add(empresaDAO.consultarTipo().get(i).getTipo());
		}
		return tipos;
	}

//	public List<Empresa> consultarPorTipo(String tipoSelecionado, String nomeSelecionado) {
//		Filtro filtroNome = new Filtro(tableEmpresa.getColummJava("nome"), Comparador.LIKE, nomeSelecionado + "%");
//		Filtro filtroTipo = new Filtro(tableEmpresa.getColummJava("tipo"), Comparador.IGUAL, tipoSelecionado);
//
//		ArrayList<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtroNome);
//		filtros.add(filtroTipo);

//		return empresaDAO.pesquisarPor(pColunaJava, pConsulta, pAtivo)(filtros);
//
//	}
	
	public List<Empresa> pesquisarPor(String pColunaJava,String pConsulta,int pAtivo){
		
		return empresaDAO.pesquisarPor(pColunaJava, pConsulta, pAtivo);
	}
	
//	public List<Empresa> consultarWhereFiltro(Filtro filtro) {
//		List<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtro);
//		return consultarWhereFiltros(filtros);
//	}

//	public List<Empresa> consultarWhereFiltros(List<Filtro> filtros) {
//		List<Empresa> empresaList;
//		empresaList = empresaDAO.consultarWhereFiltros(filtros);
//		return empresaList;
//	}

	public void gerarPlanilha(String caminhoEscolhido) {
		GeradorDePlanilha gerador = new GeradorDePlanilha();

		gerador.gerarPlanilha(caminhoEscolhido, null);

	}

//	public static Table getTableEmpresa() {
//		return tableEmpresa;
//	}

}
