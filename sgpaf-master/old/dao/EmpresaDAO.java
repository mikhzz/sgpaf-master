package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.EmpresaVO;
import model.vo.UsuarioVO;

public class EmpresaDAO implements BaseDAO<EmpresaVO> {

	public EmpresaVO salvar(EmpresaVO novaEmpresa) {

		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO EMPRESA (NOME, CNPJ, ENDERECO, TELEFONE, TIPO, EMAIL, RESPONSAVEL) "
				+ " VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, novaEmpresa.getNome());
			stmt.setString(2, novaEmpresa.getCnpj());
			stmt.setString(3, novaEmpresa.getEndereco());
			stmt.setString(4, novaEmpresa.getTelefone());
			stmt.setString(5, novaEmpresa.getTipo());
			stmt.setString(6, novaEmpresa.getEmail());
			stmt.setString(7, novaEmpresa.getResponsavel());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				novaEmpresa.setIdEmpresa(idGerado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir nova Empresa.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);

		}
		return novaEmpresa;

	}

	@Override
	public boolean excluir(int idEmpresa) {
		Connection conn = Banco.getConnection();
		String sql = "	DELETE "
				+ "		FROM "
				+ "			EMPRESA "
				+ "		WHERE "
				+ "			IDEMPRESA= "+ idEmpresa;
		Statement stmt = Banco.getStatement(conn);

		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir Empresa.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}
		return quantidadeLinhasAfetadas > 0;
	}

	@Override
	public boolean alterar(EmpresaVO empresaVO) {

		Connection conexao = Banco.getConnection();
		String sql = "UPDATE  empresa SET nome =  '" + empresaVO.getNome() + "', cnpj = '" + empresaVO.getCnpj()
				+ "', endereco = '" + empresaVO.getEndereco() + "', telefone = '" + empresaVO.getTelefone()
				+ "', tipo = '" + empresaVO.getTipo() + "',email =' " + empresaVO.getEmail() + "', responsavel = '"
				+ empresaVO.getResponsavel() + "'  WHERE idempresa =" + empresaVO.getIdEmpresa();

		Statement stmt = Banco.getPreparedStatement(conexao, sql);

		int registrosAlterados = 0;
		try {

			// stmt.setString(1, empresa.getNome());
			// stmt.setString(2, empresa.getCnpj());
			// stmt.setString(3, empresa.getEndereco());
			// stmt.setString(4, empresa.getTipo());
			// stmt.setString(5, empresa.getEmail());
			// stmt.setString(6, empresa.getResponsavel());

			registrosAlterados = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("Erro ao Alterar Empresa.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return registrosAlterados > 0;
	}
	
	
	@Override
	public List<EmpresaVO> buscarPor(String pTipoConsultaString, String pColunaBanco) {
		try {
			String stringConsulta = "%"+pTipoConsultaString+"%";
			String sql = "SELECT "
					+ "		empresa.idempresa ,empresa.nome, empresa.CNPJ, empresa.ENDERECO, empresa.TELEFONE, empresa.TIPO, empresa.EMAIL, empresa.RESPONSAVEL"+
					"	FROM " + 
					"		empresa"
					+ " WHERE "
					+ "		empresa."+pColunaBanco
					+ " LIKE "
					+ "		? "
					+ "GROUP BY "
					+ "empresa.IDEMPRESA ";
			PreparedStatement stmt;
			Connection conexao = Banco.getConnection();
			stmt = conexao.prepareStatement(sql);
			stmt = Banco.getPreparedStatement(conexao, sql);
			stmt.setString(1, stringConsulta);
			ResultSet result = stmt.executeQuery();
			List<EmpresaVO> listEmpresa = new ArrayList<EmpresaVO>();
						
			while(result.next()) {
				EmpresaVO empresa = new EmpresaVO();
				int idEmpresa = (result.getInt("idEmpresa"));
				empresa.setIdEmpresa(idEmpresa);
				empresa.setNome(result.getString("nome"));
				empresa.setCnpj(result.getString("cnpj"));
				empresa.setEndereco(result.getString("endereco"));
				empresa.setTelefone(result.getString("telefone"));
				empresa.setTipo(result.getString("tipo"));
				empresa.setEmail(result.getString("email"));
				empresa.setResponsavel(result.getString("responsavel"));
				listEmpresa.add(empresa);
			}
			return listEmpresa;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	@Override
	public ArrayList<EmpresaVO> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<EmpresaVO> empresas = new ArrayList<EmpresaVO>();
		String query = "SELECT"
				+ " 		*"
				+ " 	FROM"
				+ "			empresa";

		try {
			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				
				empresas.add(construirEmpresasDoResultSet(resultado));
				EmpresaVO empresaTest= construirEmpresasDoResultSet(resultado);
//				System.out.println(empresaTest.getNome());
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de Empresas.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return empresas;
	}

	private EmpresaVO construirEmpresasDoResultSet(ResultSet rs) {
		EmpresaVO empresaVO = null;

		try {

			int idEmpresa = rs.getInt("idEmpresa");
			String nome = rs.getString("nome");
			String cnpj = (rs.getString("cnpj"));
			String endereco = (rs.getString("endereco"));
			String telefone = (rs.getString("telefone"));
			String tipo = (rs.getString("tipo"));
			String email = (rs.getString("email"));
			String responsavel = (rs.getString("responsavel"));

			empresaVO = new EmpresaVO(idEmpresa, nome, cnpj, endereco, telefone, tipo, email, responsavel);

		} catch (SQLException e) {

			System.out.println("Erro ao construir Empresas do ResultSet");
			System.out.println("Erro: " + e.getMessage());

		}

		return empresaVO;
	}

	public ArrayList<String> consultarTipo() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT DISTINCT(tipo) FROM empresa ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<String> tipo = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				tipo.add(rs.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de Tipos.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return tipo;

	}

	public ArrayList<EmpresaVO> consultarEmpresasPorTipo(String tipoSelecionado, String nomeSelecionado) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<EmpresaVO> empresasVO = new ArrayList<EmpresaVO>();

		String query = " SELECT idempresa, nome, cnpj,endereco,telefone,tipo,email,responsavel FROM empresa "
				+ " WHERE tipo = '" + tipoSelecionado + "' " + " AND nome LIKE '" + nomeSelecionado + "%' ";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				EmpresaVO empresa = construirEmpresasDoResultSet(resultado);
				empresasVO.add(empresa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de Empresa por Tipo.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return empresasVO;
	}

	public boolean validarCnpj(String cnpj) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT cnpj FROM empresa WHERE cnpj = '" + cnpj + "'";
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query que verifica existência de Empresa por cnpj.");
			System.out.println("Erro: " + e.getMessage());
			return false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}
}
