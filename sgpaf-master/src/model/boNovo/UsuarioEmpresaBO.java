package model.boNovo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dao.UsuarioEmpresaDaoImpl;
import model.boNovo.msg.Mensagem;
import model.boNovo.msg.MensagemGenerica;
import model.boNovo.msg.StatusAlteracao;
import model.boNovo.msg.StatusCadastro;
import model.daoNovo.UsuarioEmpresaDAO;
import model.daoNovo.UsuarioEmpresaJoinDAO;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.base.JoinAgregator;
import model.daoNovo.base.Table;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;

public class UsuarioEmpresaBO {

//	private static final EmpresaBO empresaBO = new EmpresaBO();
//	private static final UsuarioBO usuarioBO = new UsuarioBO();
//	private static final Table tableEmpresa = EmpresaBO.getTableEmpresa();

	private static final UsuarioEmpresaDaoImpl usuarioEmpresaDAO = new UsuarioEmpresaDaoImpl();
//	private static final Table tableUsuarioEmpresa = usuarioEmpresaDAO.getTable();
//	private static final UsuarioEmpresaJoinDAO usuarioEmpresaJoinDAO = new UsuarioEmpresaJoinDAO();

	public Mensagem salvarUsuarioEmpresa(UsuarioEmpresa pUsuarioEmpresa) {
//		Filtro filtroEmpresa = new Filtro(tableUsuarioEmpresa.getColumm("ID_EMPRESA"), Comparador.IGUAL,
//				pUsuarioEmpresa.getEmpresa().getIdEmpresa());
//		Filtro filtroUsuario = new Filtro(tableUsuarioEmpresa.getColumm("ID_USUARIO"), Comparador.IGUAL,
//				pUsuarioEmpresa.getUsuario().getIdUsuario());
//		List<Filtro> listaFlitros = Arrays.asList(filtroEmpresa, filtroUsuario);
		List<UsuarioEmpresa> usuariosEmpresas;
		try {
			usuariosEmpresas = usuarioEmpresaDAO.pesquisarPorUsuarioEmpresa("id_usuario", pUsuarioEmpresa.getUsuario().getIdUsuario().toString(), "id_empresa", pUsuarioEmpresa.getEmpresa().getIdEmpresa().toString(), 1);
			if (!usuariosEmpresas.isEmpty()) {
				return StatusCadastro.erroCadastroEmpresaVinculada;
			} else if (pUsuarioEmpresa.getEmpresa() == null || pUsuarioEmpresa.getEmpresa().getIdEmpresa() == null) {
				return StatusCadastro.erroEmpresa;
			} else if (pUsuarioEmpresa.getUsuario() == null || pUsuarioEmpresa.getUsuario().getIdUsuario() == null) {
				return StatusCadastro.erroUsuario;
			} else {
//				pUsuarioEmpresa.setIdEmpresa(pUsuarioEmpresa.getEmpresa().getIdEmpresa());
//				pUsuarioEmpresa.setIdUsuario(pUsuarioEmpresa.getUsuario().getIdUsuario());
//				int idUsuarioEmpresa = usuarioEmpresaDAO.inserir(pUsuarioEmpresa);
				if (usuarioEmpresaDAO.salvarOuAlterar(pUsuarioEmpresa)) {
					return StatusCadastro.sucessoUsuarioEmpresa;
				} else {
					return StatusCadastro.erroCadastroUsuarioEmpresa;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

			return new MensagemGenerica(e.getLocalizedMessage(), -1, false);
		}
	}

	public String excluirUsuarioEmpresaBO(UsuarioEmpresa pUsuarioEmpresa) {

		String mensagem = "";
		pUsuarioEmpresa.setAtivo(0);
		pUsuarioEmpresa.setDataFim(new Date());
		if (usuarioEmpresaDAO.salvarOuAlterar(pUsuarioEmpresa)) {
			mensagem = "Empresa Desvinculada com Sucesso";
		} else {
			mensagem = "Erro ao Desvincular Empresa";
		}
		return mensagem;
	}

	public Mensagem alterar(UsuarioEmpresa pUsuarioEmpresa) {
//		pUsuarioEmpresa.setIdEmpresa(pUsuarioEmpresa.getEmpresa().getIdEmpresa());
//		pUsuarioEmpresa.setIdUsuario(pUsuarioEmpresa.getUsuario().getIdUsuario());

		if (usuarioEmpresaDAO.salvarOuAlterar(pUsuarioEmpresa)) {
			return StatusAlteracao.sucessoUsuarioEmpresa;
		} else {
			return StatusAlteracao.erroCadastroUsuarioEmpresa;
		}
	}

	public List<UsuarioEmpresa> consultarTodos(Usuario pUsuario) {
//		for (UsuarioEmpresa usuarioEmpresa : usuarioEmpresaList) {
//			usuarioEmpresa.setEmpresa(empresaBO.consultarPorId(usuarioEmpresa.getIdEmpresa()));
//			usuarioEmpresa.setUsuario(usuarioBO.consultarPorId(usuarioEmpresa.getIdUsuario()));
//		}
		return usuarioEmpresaDAO.pesquisarPor("id_usuario", pUsuario.getIdUsuario().toString(), 1);
	}
	public List<UsuarioEmpresa> consultarPorEmpresa(Empresa pEmpresa){
		
		return usuarioEmpresaDAO.pesquisarPor("id_empresa", pEmpresa.getIdEmpresa().toString(), 1);
	}
//	public JoinAgregator<Usuario, UsuarioEmpresa, Empresa> consultarJoinPorIdUsuario(int idUsuario) {
//		JoinAgregator<Usuario, UsuarioEmpresa, Empresa> usuarioempresaJoin = usuarioEmpresaJoinDAO
//				.pesquisarJoinPorId(idUsuario);
//		return usuarioempresaJoin;
//	}

//	public JoinAgregator<Usuario, UsuarioEmpresa, Empresa> consultarJoinPorUsuario(Usuario usuario) {
//		return consultarJoinPorIdUsuario(usuario.getIdUsuario());
//	}

//	public JoinAgregator<Usuario, UsuarioEmpresa, Empresa> consultarJoinPorIdUsuarioAtivo(int idUsuario) {
//		Filtro filtroEmpresaAtiva = new Filtro(tableEmpresa.getColumm("ATIVO"), Comparador.IGUAL, 1);
//		JoinAgregator<Usuario, UsuarioEmpresa, Empresa> usuarioempresaJoin = usuarioEmpresaJoinDAO
//				.pesquisarJoinPorFiltro(idUsuario, filtroEmpresaAtiva);
//		return usuarioempresaJoin;
//	}

//	public JoinAgregator<Usuario, UsuarioEmpresa, Empresa> consultarJoinPorUsuarioAtivo(Usuario usuario) {
//		return consultarJoinPorIdUsuarioAtivo(usuario.getIdUsuario());
//	}

//	public static Table getTableUsuarioEmpresa() {
//		return tableUsuarioEmpresa;
//	}

	public static void main(String[] args) {
		
//		====================================================INICIO TESTES ANTIGOS VERSAO TCC===============================================

//		List<Empresa> empresas = new ArrayList<>();
//		List<UsuarioEmpresa> usuarioEmpresas = new ArrayList<>();
//		Usuario usuario = new Usuario();
//		Empresa empresa = new Empresa();
//		UsuarioEmpresa usuarioEmpresa = new UsuarioEmpresa();
//		UsuarioEmpresaBO usuarioEmpresaBO = new UsuarioEmpresaBO();
//		
//		empresa.setIdEmpresa(2);
//		usuario.setIdUsuario(1);
//		usuarioEmpresa.setIdUsuarioEmpresa(3);
//		String mensgem = usuarioEmpresaBO.excluirUsuarioEmpresaBO(usuarioEmpresa.getIdUsuarioEmpresa());
//		usuarioEmpresa.setDataInicio(new Date());
//		usuarioEmpresa.setEmpresa(empresa);
//		usuarioEmpresa.setUsuario(usuario);
//		Mensagem mensagem = usuarioEmpresaBO.salvarUsuarioEmpresa(usuarioEmpresa);
//		
//		System.out.println(mensagem.getMensagem());

//		JoinAgregator<Usuario, UsuarioEmpresa, Empresa> joinAgregator = usuarioEmpresaBO
//				.consultarJoinPorUsuario(usuario);
//		empresas = joinAgregator.getListList();
//		usuarioEmpresas = joinAgregator.getJoinList();
//		for (UsuarioEmpresa usuarioEmpresa : usuarioEmpresas) {
//
//			System.out.println(usuarioEmpresa.getEmpresa() + " " + usuarioEmpresa.getIdUsuarioEmpresa()
//					+ "================================================================");
//
//		}
//		for (Empresa empresa : empresas) {
//
//			System.out.println(empresa.getNome() + "================================================================");
//
//		}
		
//		====================================================FIM TESTES ANTIGOS VERSAO TCC===============================================

	}
}
