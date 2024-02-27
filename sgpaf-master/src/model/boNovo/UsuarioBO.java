package model.boNovo;

import java.util.List;
import dao.UsuarioDaoImpl;
import dao.UsuarioEmpresaDaoImpl;
import model.boNovo.msg.Mensagem;
import model.boNovo.msg.StatusAlteracao;
import model.boNovo.msg.StatusCadastro;
import model.daoNovo.UsuarioUpdateSemSenhaDAO;
import model.daoNovo.base.Comparador;
import model.daoNovo.db.Usuario;

public class UsuarioBO {
//	private static final PerfilBO perfilBO = new PerfilBO();
	private static final UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
//	private static final UsuarioUpdateSemSenhaDAO usuarioUpdateDAO = new UsuarioUpdateSemSenhaDAO();

//	private static final Table tableUsuario = usuarioDAO.getTable();
	private static final UsuarioEmpresaDaoImpl usuarioEmpresaDAO = new UsuarioEmpresaDaoImpl();
//	private static final Table tableUsuarioEmpresa = usuarioEmpresaDAO.getTable();
//	private static final UsuarioEmpresaBO usuarioEmpresaBO = new UsuarioEmpresaBO();

	public Mensagem salvarUsuario(Usuario usuario) {
//		Filtro filtroCpf = new Filtro(tableUsuario.getColummJava("cpf"), Comparador.IGUAL, usuario.getCpf());
//		Filtro filtroLogin = new Filtro(tableUsuario.getColummJava("login"), Comparador.IGUAL, usuario.getLogin());

		List<Usuario> usuarios;
		usuarios = usuarioDAO.pesquisarPor("cpf",usuario.getCpf() , 1, 1);
		if (!usuarios.isEmpty()) {
			return StatusCadastro.erroCpf;

		} else if (!usuarioDAO.pesquisarPor("login", usuario.getLogin(),1,1).isEmpty()) {

			return StatusCadastro.erroLoguin;
		} else if (usuario.getPerfil() == null || usuario.getPerfil().getIdPerfil() == null) {
			return StatusCadastro.erroPerfil;
		} else {

//			usuario.setIdPerfil(usuario.getPerfil().getIdPerfil());
//			int idUsuario = usuarioDAO.inserir(usuario);

			if (usuarioDAO.salvarOuAlterar(usuario)) {
//				usuario.setIdUsuario(idUsuario);
				return StatusCadastro.sucesso;
			} else {
				return StatusCadastro.erroCadastro;
			}
		}
	}

	public String excluirUsuarioBO(Usuario usuario) {
		String mensagem = "";
//		Filtro filtroIdUsuario = new Filtro(tableUsuarioEmpresa.getColummJava("idUsuario"), Comparador.IGUAL, codigo);

//		List<UsuarioEmpresa> usuarioEmpresaList = usuarioEmpresaDAO.consultarWhereFiltro(filtroIdUsuario);
//		for (Iterator<UsuarioEmpresa> iterator = usuarioEmpresaList.iterator(); iterator.hasNext();) {
//			UsuarioEmpresa usuarioEmpresa = (UsuarioEmpresa) iterator.next();
//			usuarioEmpresaBO.excluirUsuarioEmpresaBO(usuarioEmpresa.getIdUsuarioEmpresa());
//		}
		if (usuarioDAO.salvarOuAlterar(usuario)) {

			mensagem = "Usuario Excluido com Sucesso";
		} else {

			mensagem = "Erro ao excluir Usu�rio";
		}

		return mensagem;

	}

	public Mensagem alterar(Usuario usuario) {
		if (usuarioDAO.salvarOuAlterar(usuario)){
			return StatusAlteracao.sucesso;

		} else {
			return StatusAlteracao.erroCadastro;
		}
	}

//	public Mensagem alterarSemSenha(Usuario usuario) {
//		usuario.setPerfil(usuario.getPerfil());
//		if (usuarioUpdateDAO.alterar(usuario, usuario.getIdUsuario()) == true) {
//			return StatusAlteracao.sucesso;
//
//		} else {
//			return StatusAlteracao.erroCadastro;
//
//		}

//	}

//	public Usuario logar(String login, String password) {
//		Filtro filtroLogin = new Filtro(tableUsuario.getColummJava("login"), Comparador.IGUAL, login);
//		Filtro filtroSenha = new Filtro(tableUsuario.getColummJava("senha"), Comparador.IGUAL, password);
//
//		ArrayList<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtroSenha);
//		filtros.add(filtroLogin);
//
//		List<Usuario> users;
//		users = usuarioDAO.consultarWhereFiltros(filtros);
//
//		if (users.size() > 1) {
//			// throw new Exception("multiplos usuarios com mesmo loguin");
//
//			return null;
//		} else if (users.size() == 1) {
//			users.get(0).setPerfil(perfilBO.getPerfil(users.get(0).getIdPerfil()));
//			return users.get(0);
//		} else {
//			return null;
//		}
//	}

	public List<Usuario> consultarTodos() {
		List<Usuario> usuarioList = usuarioDAO.pesquisarTodos(1, 1);
		for (Usuario usuario : usuarioList) {
//			usuario.setPerfil(perfilBO.getPerfil(usuario.getIdPerfil()));
		}
		return usuarioList;
	}

	public Usuario consultarPorId(Long idDigitado) {
		Usuario usuario = usuarioDAO.pesquisarPorID(idDigitado, 1, 1);
//		usuario.setPerfil(perfilBO.getPerfil(usuario.getIdPerfil()));
		return usuario;
	}

	public List<Usuario> consultarWhereFiltros(String pColunaJava,String pConsulta, int pHabilitado,int pAtivo ) {
		List<Usuario> usuarioList;
		usuarioList = usuarioDAO.pesquisarPor(pColunaJava, pConsulta, pAtivo, pHabilitado);
//		for (Usuario usuario : usuarioList) {
//			usuario.setPerfil(perfilBO.getPerfil(usuario.getIdPerfil()));
//		}
		return usuarioList;
	}

//	public List<Usuario> consultarWhereFiltro(Filtro filtro) {
//		List<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtro);
//		return consultarWhereFiltros(filtros);
//	}
	
	public List<Usuario> consultarPorNomeCB(String nome) {
		nome="(^|.* )"+nome+".*";
//		Filtro filtroNome = new Filtro(tableUsuario.getColummJava("nome"), Comparador.REGEXP, nome);
//		List<Filtro> filtros = new ArrayList<Filtro>();
//		filtros.add(filtroNome);
//		Extra extra=new Extra();
//		extra.setLimit(16);
		return usuarioDAO.pesquisarPor("nome", nome, 1 ,1);
	}

//	public static Table getTableUsuario() {
//		return tableUsuario;
//	}

}
