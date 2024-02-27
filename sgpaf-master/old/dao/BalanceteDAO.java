package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.vo.Balancete;
import model.vo.EmpresaVO;

public class BalanceteDAO {

	public Balancete incluir(Balancete balanco) {

		Connection conexao = Banco.getConnection();
		String sql = "INSERT INTO BALANCETE (NATUREZACONTA,CODIGOESTRUTURADO,CODIGOREDUZIDO, CLASSIFICACAO, DESCRICAO, SALDOINICIAL, DEBITOS, CREDITOS, SALDOATUAL) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			
			stmt.setString(1, balanco.getNaturezaConta());
			stmt.setDouble(2, balanco.getCodigoEstruturado());
			stmt.setDouble(3, balanco.getCodigoReduzido());
			stmt.setDouble(4, balanco.getClassificacao());
			stmt.setString(5, balanco.getDescricao());
			stmt.setDouble(6, balanco.getSaldoInicial());
			stmt.setDouble(7, balanco.getDebitos());
			stmt.setDouble(8, balanco.getCreditos());
			stmt.setDouble(9, balanco.getSaldoAtual());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				balanco.setIdBalancete(idGerado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo Balancete.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);

		}
		return balanco;

	}

	public ArrayList<Balancete> consultarTodosBalancetes() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<Balancete> balancos = new ArrayList<Balancete>();
		String query = "SELECT * FROM balancete";

		try {
			resultado = stmt.executeQuery(query);

			while (resultado.next()) {
				Balancete balanco = new Balancete();

				balanco = construirBalanceteDoResultSet(resultado);
				balancos.add(balanco);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a Query de Consulta de balancetes.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return balancos;
	}

	private Balancete construirBalanceteDoResultSet(ResultSet rs) {

		Balancete novoBalancete = null;

		try {

			int idBalancete = rs.getInt("idBalancete");
			int idEmpresa = rs.getInt("idempresa");
			String naturezaConta = rs.getString("naturezaconta");
			int codigo = (rs.getInt("codigo"));
			int codigoReduzido = (rs.getInt("codigoreduzido"));
			double classificacao = (rs.getDouble("classificacao"));
			String descricao = (rs.getString("descricao"));
			double saldoInicial = (rs.getDouble("saldoinicial"));
			double debito = (rs.getDouble("debitos"));
			double credito = (rs.getDouble("creditos"));
			double saldoAtual = (rs.getDouble("saldoatual"));
			Date dataBase = (rs.getDate("database")); 
			Date dataBalanco = (rs.getDate("databalanco"));
			

			novoBalancete = new Balancete(idBalancete, idEmpresa, naturezaConta, codigo, codigoReduzido, classificacao, descricao,
					saldoInicial, debito, credito, saldoAtual, dataBase, dataBalanco);

		} catch (SQLException e) {

			System.out.println("Erro ao construir Balancetes do ResultSet");
			System.out.println("Erro: " + e.getMessage());

		}

		return novoBalancete;

	}

}
