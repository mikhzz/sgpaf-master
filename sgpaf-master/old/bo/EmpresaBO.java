package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.EmpresaDAO;
import model.util.GeradorDePlanilha;
import model.vo.EmpresaVO;

public class EmpresaBO {

	StringBuilder mensagem = new StringBuilder();

	public String excluirEmpresaBO(int codigoDigitado) {
		EmpresaDAO empresaDAO = new EmpresaDAO();

		String mensagem = "";

		if (empresaDAO.excluir(codigoDigitado) == true) {

			mensagem = "Empresa Excluida com Sucesso";

		} else {

			mensagem = "Erro ao excluir Empresa";

		}

		return mensagem;

	}

	public String salvarEmpresaBO(EmpresaVO empresaVO) {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		String mensagem = "";

		if (empresaDAO.validarCnpj(empresaVO.getCnpj())) {

			mensagem += "Cnpj Já Cadastrado";
		} else {

			empresaDAO.salvar(empresaVO);

			if (empresaVO.getIdEmpresa() > 0) {

				mensagem += "Empresa cadastrada com sucesso";

			} else {

				mensagem += "Erro ao cadastrar empresa";

			}
		}

		return mensagem;

	}

	public String alterarEmpresaBO(EmpresaVO empresaVO) {
		EmpresaDAO empresaDAO = new EmpresaDAO();

		String mensagem = "";
		if (empresaDAO.alterar(empresaVO) == true) {
			mensagem = "Empresa atualizada com Sucesso";

		} else {

			mensagem = "Erro ao Atualizar Empresa";
		}

		return mensagem;

	}

	public void validarCnpj(String cnpj, StringBuilder mensagem) {

		validarCnpj(cnpj, mensagem);

		if (cnpj == null || cnpj.trim().length() != 14) {
			mensagem.append("CNPJ deve conter 14 caracteres \n");
		} else {
			try {
				Long.parseLong(cnpj);
			} catch (NumberFormatException ex) {
				mensagem.append("CNPJ deve conter somente números \n");
			}
		}
	}

	public ArrayList<EmpresaVO> consultarTodas() {
		EmpresaDAO empresaDAO = new EmpresaDAO();

		return empresaDAO.consultarTodos();

	}

	public ArrayList<String> consultarTipos() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		return empresaDAO.consultarTipo();
	}

	public ArrayList<EmpresaVO> consultarPorTipo(String tipoSelecionado, String nomeSelecionado) {
		EmpresaDAO empresaDAO = new EmpresaDAO();

		return empresaDAO.consultarEmpresasPorTipo(tipoSelecionado, nomeSelecionado);
	}

	public void gerarPlanilha(String caminhoEscolhido) {
		GeradorDePlanilha gerador = new GeradorDePlanilha();

		gerador.gerarPlanilha(caminhoEscolhido, null);

	}

}
