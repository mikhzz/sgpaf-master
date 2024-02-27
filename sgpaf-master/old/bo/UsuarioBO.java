package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.UsuarioDAO;
import model.util.GeradorDePlanilha;
import model.vo.UsuarioVO;

public class UsuarioBO {

	private final String USER_ADMIN = "ADMIN";
	private final String USER_USER = "USUARIO";

	// setar os niveis de acesso de usuario

//	public String salvarUsuarioBO(UsuarioVO usuario) {
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		String mensagem = "";
//
//		if (usuarioDAO.existeRegistroPorCpf(usuario.getCpf())) {
//			mensagem += "Cpf já Cadastrado";
//
//		} else if (usuarioDAO.loginJaUtilizado(usuario.getLogin())) {
//
//			mensagem += "Login já Utilizado";
//		} else {
//			usuario = usuarioDAO.cadastrar(usuario);
//
//			if (usuario.getIdUsuario() > 0) {
//				mensagem += "Usuário cadastrado com sucesso";
//			} else {
//				mensagem += "Erro ao cadastrar usuário";
//			}
//		}
//
//		return mensagem;
//	}

	public String excluirUsuarioBO(int codigo) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		String mensagem = "";
		if (usuarioDAO.excluir(codigo) == true) {

			mensagem = "Usuario Excluido com Sucesso";
		} else {

			mensagem = "Erro ao excluir Usuário";
		}

		return mensagem;

	}

//	public boolean logar(String login, String password) {
//
//		UsuarioDAO usuarioDAO = new UsuarioDAO();
//		boolean logado;
//		
//		if (usuarioDAO.logar(login, password)) {
//			logado= true;
//
//		} else {
//
//			logado= false;
//		}
//		return logado;
//	}

	public String alterarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		String mensagem = "";

		if (usuarioDAO.alterar(usuarioVO) == true) {
			mensagem = "Usuário alterado com sucesso";

		} else {

			mensagem = "Erro ao Alterar Usuário";
		}

		return mensagem;

	}

	public ArrayList<UsuarioVO> consultarTodosUsuariosBO() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTodos();

	}

	public String consultarPorId(int idDigitado) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String mensagem = "";

		return mensagem;
	}

}
