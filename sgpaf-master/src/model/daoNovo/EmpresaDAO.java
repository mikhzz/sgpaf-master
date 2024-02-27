package model.daoNovo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.daoNovo.base.AutoDao;
import model.daoNovo.base.Banco;
import model.daoNovo.db.Empresa;

public class EmpresaDAO extends AutoDao<Empresa,Integer>{

	public EmpresaDAO() {
		super( Empresa.class);
		// TODO Auto-generated constructor stub
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
}
