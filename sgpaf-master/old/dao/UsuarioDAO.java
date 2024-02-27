package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.vo.PerfilVO;
import model.vo.UsuarioVO;

public class UsuarioDAO implements BaseDAO<UsuarioVO> {

	public boolean cadastrar(UsuarioVO pUsuario) {
		try {
			String sql = "INSERT INTO "
					+ "		USUARIO (NOME, CPF, CARGO, LOGIN, SENHA, ID_PERFIL) "
					+ " VALUES "
					+ "		(?,?,?,?,?,?)";
			PreparedStatement stmt;
			Connection conexao = Banco.getConnection();
			stmt = Banco.getPreparedStatement(conexao, sql);
			stmt.setString(1, pUsuario.getNome());
			stmt.setString(2, pUsuario.getCpf());
			stmt.setString(3, pUsuario.getCargo());
			stmt.setString(4, pUsuario.getLogin());
			stmt.setString(5, pUsuario.getSenha());
			stmt.setInt(6, pUsuario.getPerfil().getIdPerfil());
			
			stmt.executeUpdate();
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
			return true;
		} catch (SQLException e) {
			System.out.println("Entrou no catch usuarioDao");
			System.out.println(e.getMessage());
			
			return false;
		}
	}
	@Override
	public boolean excluir(int idUsuario) {
		
		int quantidadeLinhasAfetadas = 0;
		
		String  sql = "	UPDATE" + 
				"  			 usuario " + 
				"		SET " + 
				"			usuario.ativo = 0 " + 
				"		WHERE " + 
				"			usuario.id_usuario = "+idUsuario;
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		try {
			
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			System.out.println("Erro ao excluir Usu�rio.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
			
		}
		return quantidadeLinhasAfetadas > 0;
	}
	
	public boolean habilitarDesabilitarUsr(int idUsuario, int habilitadoDesabilitado) {
		
		int quantidadeLinhasAfetadas = 0;
		
		String  sql = "	UPDATE" + 
				"  			 usuario " + 
				"		SET " + 
				"			usuario.habilitado = " + habilitadoDesabilitado+
				"		WHERE " + 
				"			usuario.id_usuario = "+idUsuario;
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		try {
			
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			System.out.println("Erro ao excluir Usu�rio.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
			
		}
		return quantidadeLinhasAfetadas > 0;
	}

	@Override
	public boolean alterar(UsuarioVO usuario) {
		Connection conexao = Banco.getConnection();
		String sql = "UPDATE  usuario SET nome =  '" + usuario.getNome() + "', cpf = '" + usuario.getCpf()
		+ "', cargo = '" + usuario.getCargo() + "', login = '" + usuario.getLogin() + "', id_perfil = '"
		+ usuario.getPerfil().getIdPerfil() + "', senha = '" + usuario.getSenha() + "' WHERE id_usuario =  "
		+ usuario.getIdUsuario();

		Statement stmt = Banco.getPreparedStatement(conexao, sql);

		
		int registrosAlterados = 0;
		try {

			registrosAlterados = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("Erro ao Alterar Usuario.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return registrosAlterados > 0;
	}

	
	@Override
	public ArrayList<UsuarioVO> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = "	SELECT " + 
				"			usuario.id_usuario , usuario.nome , usuario.cpf , usuario.cargo ," + 
				"			usuario.login, usuario.senha, usuario.habilitado, perfil.id_perfil ,"
				+ "			perfil.descricao, perfil.tipo" + 
				"		FROM" + 
				"			usuario " + 
				"		INNER JOIN" + 
				"			perfil ON perfil.id_perfil = usuario.id_perfil" + 
				"		WHERE" + 
				"			usuario.ativo = 1" ;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UsuarioVO usuario = construirUsuarioDoResultSet(rs);
				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar Usuarios.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return usuarios;
	}

	private UsuarioVO construirUsuarioDoResultSet(ResultSet rs) {
		UsuarioVO usuario = new UsuarioVO();
		PerfilVO perfil = new PerfilVO();
		try {
			usuario.setIdUsuario(rs.getInt("usuario.id_usuario"));
			usuario.setNome(rs.getString("usuario.nome"));
			usuario.setCpf(rs.getString("usuario.cpf"));
			usuario.setCargo(rs.getString("usuario.cargo"));
			usuario.setLogin(rs.getString("usuario.login"));
			usuario.setSenha(rs.getString("usuario.senha"));
			usuario.setHabilitado(rs.getInt("usuario.habilitado"));
			perfil.setIdPerfil(rs.getInt("perfil.id_perfil"));
			perfil.setDescricao(rs.getString("perfil.descricao"));
			perfil.setTipo(rs.getString("perfil.tipo"));
			
			usuario.setPerfil(perfil);
			
		} catch (SQLException e) {
			System.out.println("Erro ao construir Usu�rio a partir do ResultSet.");
			System.out.println("Erro: " + e.getMessage());
		}
		return usuario;
	}

	public UsuarioVO logar(String login, String senha) {
		UsuarioVO usuario = null;
		Connection conexao = Banco.getConnection();
		
		String sql = "	SELECT " + 
				"			usuario.id_usuario , usuario.nome , usuario.cpf , usuario.cargo ," + 
				"			usuario.login, usuario.senha, usuario.habilitado, perfil.id_perfil ,"
				+ "			perfil.descricao, perfil.tipo" + 
				"		FROM" + 
				"			usuario " + 
				"		INNER JOIN" + 
				"			perfil ON perfil.id_perfil = usuario.id_perfil" + 
				"		WHERE" + 
				"			usuario.ativo = 1"
				+ "		AND "
				+ "			usuario.login = ?"
				+ "		AND "
				+ "			usuario.senha = ?" ;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		
		try {
			
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				usuario = construirUsuarioDoResultSet(rs);
			}
			
			return usuario;
		}  catch (SQLException e) {
			System.out.println("Erro ao consultar Usuarios.");
			System.out.println("Erro: " + e.getMessage());
			return null;
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
			
		}

		
//		Connection conexao = Banco.getConnection();
//		String sql = "SELECT login, senha FROM USUARIO WHERE login='" + login + "' AND SENHA=" + senha;
//		Statement stmt = Banco.getPreparedStatement(conexao, sql);
//		ResultSet resultadoDaConsulta = null;
//
//		boolean autenticado = false;
//
//		try {
//
//			resultadoDaConsulta = stmt.executeQuery(sql);
//
//			if (resultadoDaConsulta.next()) {
//
//				autenticado = true;
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao Logar no Sistema");
//			System.out.println("Erro: " + e.getMessage());
//		} finally {
//			Banco.closeResultSet(resultadoDaConsulta);
//			Banco.closePreparedStatement(stmt);
//			Banco.closeConnection(conexao);
//		}
//
//		return autenticado;

	}

	public boolean loginJaUtilizado(String login) {

		Connection conexao = Banco.getConnection();
		String sql = "SELECT cpf FROM usuario WHERE login = '" + login + "'";
		Statement stmt = Banco.getPreparedStatement(conexao, sql);
		ResultSet resultadoDaConsulta = null;
		boolean loginJaUtilizado = false;

		try {

			resultadoDaConsulta = stmt.executeQuery(sql);

			if (resultadoDaConsulta.next()) {
				loginJaUtilizado = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Usuario Login");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return loginJaUtilizado;
	}

	@Override
	public List<UsuarioVO> buscarPor(String pTipoConsultaString, String pColunaBanco) {
		try {
			String stringConsulta = "%"+pTipoConsultaString+"%";
			String sql = "	SELECT " + 
					"			usuario.id_usuario , usuario.nome , usuario.cpf , usuario.cargo ," + 
					"			usuario.login, usuario.senha, usuario.habilitado, perfil.id_perfil ,"
					+ "			perfil.descricao, perfil.tipo" + 
					"		FROM" + 
					"			usuario " + 
					"		INNER JOIN" + 
					"			perfil ON perfil.id_perfil = usuario.id_perfil" + 
					"		WHERE" + 
					"			usuario.ativo = 1"
					+ " 	AND"
					+ "			usuario."+pColunaBanco
					+ "		LIKE"
					+ "			? " ;
			PreparedStatement stmt;
			Connection conexao = Banco.getConnection();
			stmt = conexao.prepareStatement(sql);
			stmt = Banco.getPreparedStatement(conexao, sql);
			stmt.setString(1, stringConsulta);
			ResultSet result = stmt.executeQuery();
			List<UsuarioVO> listUsr = new ArrayList<UsuarioVO>();
						
			while(result.next()) {
				UsuarioVO usuario = new UsuarioVO();
				PerfilVO perfil	= new PerfilVO();
				int idUser = (result.getInt("id_usuario"));
				usuario.setIdUsuario(idUser);
				usuario.setNome(result.getString("nome"));
				usuario.setCpf(result.getString("cpf"));
				usuario.setCargo(result.getString("cargo"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
				usuario.setHabilitado(result.getInt("habilitado"));
				perfil.setIdPerfil(result.getInt("id_perfil"));
				perfil.setDescricao(result.getString("descricao"));
				perfil.setTipo(result.getString("tipo"));
				usuario.setPerfil(perfil);
				listUsr.add(usuario);
			}
			return listUsr;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public boolean existeRegistroPorCpf(String cpf) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT cpf FROM usuario WHERE cpf = '" + cpf + "'";
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica exist�ncia de Usu�rio por CPF.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
	public static void main(String[] args) {

//************************ TESTE O DAO **************************//
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioVO usuario = new UsuarioVO();
		List<UsuarioVO> usuarios = new ArrayList<>();
		
		PerfilVO perfil = new PerfilVO(2, "Acesso total", "Adimin");
		
		usuario.setIdUsuario(3);
		usuario.setNome("Paulo");
		usuario.setCpf("8.888.888-78");
		usuario.setCargo("DEV");
		usuario.setLogin("paulodev");
		usuario.setSenha("123");
		usuario.setPerfil(perfil);
		
		usuarioDAO.alterar(usuario);
//		usuarioDAO.excluir(usuario.getIdUsuario());
//		usuarioDAO.habilitarDesabilitarUsr(usuario.getIdUsuario(), 0);
		
//		usuario = usuarioDAO.logar("TATI", "123");
		
		System.out.println(usuario.getNome()+" "+ usuario.getPerfil().getTipo());
		for (UsuarioVO usuarioVO : usuarios) {
			System.out.println(usuarioVO.getIdUsuario()+" "+ usuarioVO.getNome()+" "+usuarioVO.getCpf()+" "+usuarioVO.getCargo()+" "+usuarioVO.getLogin()+" "+ usuarioVO.getSenha()+" "+usuarioVO.getAtivo()+" "+usuarioVO.getHabilitado());
			System.out.println(usuarioVO.getPerfil().getIdPerfil()+" "+usuarioVO.getPerfil().getDescricao()+" "+usuarioVO.getPerfil().getTipo());
		}
		
	}
}

