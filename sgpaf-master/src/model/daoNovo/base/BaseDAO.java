/**
 * 
 */
package model.daoNovo.base;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata com os métodos mínimos para que qualquer Data Access Object
 * (DAO).
 * 
 * @author Vilmar César Pereira Júnior
 * @param <T> o tipo da entidade (ou VO) que associada ao DAO específico
 * 
 */
public abstract class BaseDAO<T,Id> {
	protected int i = 0;
	private static final int CODIGO_RETORNO_SUCESSO_SQL = 1;

	/**
	 * 
	 * @param entidade a entidade do tipo informado a ser persistida
	 * @return
	 * @throws SQLException
	 */
	public int inserir(T entidade) {
		// SQL: INSERT INTO NOMETABELA (atributo1, atributo2,... atributoN)
		// VALUES (?,?,...?)
		String query = " INSERT INTO " + getNomeTabela() + " ( " + getNomesColunasInsert() + " ) VALUES ( "
				+ getInterrogacoesInsert() + " ) ";

		Connection conn = Banco.getConnection();
		PreparedStatement preparedStmt = Banco.getPreparedStatement(conn, query, Statement.RETURN_GENERATED_KEYS);
		int idEntidadeSalva = -1;

		try {
			// Este método DEVE ser implementado na classe concreta
			this.setValoresAtributosInsert(entidade, preparedStmt);

			preparedStmt.executeUpdate();
			ResultSet rs = preparedStmt.getGeneratedKeys();
			if (rs.next()) {
				idEntidadeSalva = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir " + entidade.getClass().toString() + "\n" + e.getMessage());
			System.out.println(e.getLocalizedMessage());

		} finally {
			Banco.closeStatement(preparedStmt);
			Banco.closeConnection(conn);
		}
		return idEntidadeSalva;
	}

	public boolean alterar(T entidade, Id idEntidade) {
		// SQL: UPDATE NOMETABELA
		// SET atributo1 = valor1, atributo2 = valor 2,... atributoN = valorN) WHERE
		// IDTABELA = idEntidade
		String sql = "UPDATE " + getNomeTabela() + " SET " + getValoresClausulaSetUpdate() + " WHERE "
				+ getNomeColunaChavePrimaria() + " = " + idEntidade;

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		boolean sucessoUpdate = false;

		try {
			// Este método DEVE ser implementado na classe concreta
			this.setValoresAtributosUpdate(entidade, stmt);

			int retorno = stmt.executeUpdate();
			sucessoUpdate = (retorno == CODIGO_RETORNO_SUCESSO_SQL);
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o registro com id = " + idEntidade + "da entidade "
					+ entidade.getClass().toString() + "\n" + e.getMessage());
			System.out.println(e.getLocalizedMessage());

		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucessoUpdate;
	}

	public boolean excluir(Id idEntidade) {
		// SQL: DELETE FROM NomeTabela WHERE ID = idEntidade
		String sql = "DELETE FROM " + getNomeTabela() + " WHERE " + getNomeColunaChavePrimaria() + " = " + "?;";

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			setStmtField (stmt,idEntidade.getClass().getSimpleName(),idEntidade,1);
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
			e1.printStackTrace();
		}
		boolean sucessoDelete = false;

		try {
			int resultado = stmt.executeUpdate();
			sucessoDelete = (resultado == CODIGO_RETORNO_SUCESSO_SQL);
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o registro com id = " + idEntidade + "da entidade "
					+ this.getClass().toString() + "\n" + e.getMessage());
			System.out.println(e.getLocalizedMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return sucessoDelete;
	}

	public T pesquisarPorId(Id idEntidade) {
		// SQL: SELECT * FROM NOMETABELA WHERE WHERE ID = idEntidade
		String sql = "SELECT * FROM " + getNomeTabela() + " WHERE " + getNomeColunaChavePrimaria() + " = " + "? ;";
		System.out.println(sql);

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		try {
			i=1;
			setStmtField (stmt,idEntidade.getClass().getSimpleName(),idEntidade,1);
		} catch (SQLException e1) {
			System.out.println(e1.getLocalizedMessage());
			e1.printStackTrace();
		}

		ResultSet resultado = null;
		T objetoConsultado = null;

		try {
			resultado = stmt.executeQuery();
			while (resultado.next()) {
				i = 1;
				objetoConsultado = construirObjetoDoResultSet(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o registro com id = " + idEntidade + "da entidade "
					+ this.getClass().toString());
			System.out.println(e.getLocalizedMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return objetoConsultado;
	}

	public List<T> consultarTodos() {
		String sql = "SELECT * FROM " + getNomeTabela();

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet resultado = null;
		ArrayList<T> listaEntidades = new ArrayList<T>();

		try {
			resultado = stmt.executeQuery();
			while (resultado.next()) {
				i = 1;
				T objetoConsultado = construirObjetoDoResultSet(resultado);
				listaEntidades.add(objetoConsultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os objetos da entidade " + this.getClass().toString());
			System.out.println(e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaEntidades;
	}
	protected void setStmtField(PreparedStatement stmt, String type, Object value,int i) throws SQLException {
		if (value == null) {
			switch (type) {
			case "String":
			case "string":
				stmt.setNull(i, java.sql.Types.VARCHAR);
				break;
			case "int":
			case "Int":
			case "Integer":
			case "integer":
				stmt.setNull(i, java.sql.Types.INTEGER);
				break;

			case "BigDecimal":
				stmt.setNull(i, java.sql.Types.DECIMAL);
				break;

			case "Boolean":
			case "boolean":
				stmt.setNull(i, java.sql.Types.BOOLEAN);
				break;

			case "Date":
				stmt.setNull(i, java.sql.Types.DATE);
				break;

			case "Double":
			case "double":
				stmt.setNull(i, java.sql.Types.DOUBLE);
				break;

			case "Float":
			case "float":
				stmt.setNull(i, java.sql.Types.FLOAT);
				break;

			case "Long":
			case "long":
				stmt.setNull(i, java.sql.Types.BIGINT);
				break;
			case "Short":
			case "short":
				stmt.setNull(i, java.sql.Types.SMALLINT);
				break;

			case "Time":
				stmt.setNull(i, java.sql.Types.TIME);
				break;

			case "Timestamp":
				stmt.setNull(i, java.sql.Types.TIMESTAMP);
				break;

			case "URL":
				stmt.setNull(i, java.sql.Types.DATALINK);
				break;

			}
			return;
		}
		switch (type) {
		case "String":
		case "string":
			stmt.setString(i, value.toString());
			break;
		case "int":
		case "Int":
		case "Integer":
		case "integer":
			stmt.setInt(i, (Integer) value);
			break;

		case "BigDecimal":
			stmt.setBigDecimal(i, (BigDecimal) value);
			break;

		case "Boolean":
		case "boolean":
			stmt.setBoolean(i, (Boolean) value);
			break;

		case "Date":
			stmt.setDate(i, new java.sql.Date(((java.util.Date) value).getTime()));
			break;

		case "Double":
		case "double":
			stmt.setDouble(i, (Double) value);
			break;

		case "Float":
		case "float":
			stmt.setFloat(i, (Float) value);
			break;

		case "Long":
		case "long":
			stmt.setLong(i, (Long) value);
			break;

		case "Null":
		case "null":
			stmt.setNull(i, (Integer) value);
			break;

		case "Short":
		case "short":
			stmt.setShort(i, (Short) value);
			break;

		case "Time":
			
			stmt.setTime(i, (Time) value);
			break;

		case "Timestamp":
			stmt.setTimestamp(i, (Timestamp) value);
			break;

		case "URL":
			stmt.setURL(i, (URL) value);
			break;

		default:
			System.out.println("Erro ao identificar tipo :" + type + " valor:" + value.toString());
			break;
		}

	}


	/**
	 * Daqui para baixo...
	 * 
	 * Métodos abstratos, que obrigatoriamente serão implementados nas classes
	 * concretas.
	 * 
	 * Classe concreta: subclasse da classe abstrata que pode ter objeto
	 * construído.
	 * 
	 */

	/**
	 * @return String o nome da tabela criado no BD.
	 */
	protected abstract String getNomeTabela();

	/**
	 * @return String o nome da PK criada no BD.
	 */
	protected abstract String getNomeColunaChavePrimaria();

	/**
	 * Constrói uma string formada pelos nomes das colunas (do BD) do INSERT
	 * separados por vírgula.
	 * 
	 * @return String os nomes das colunas separados por vírgula.
	 */
	protected abstract String getNomesColunasInsert();

	/**
	 * Constrói uma string formada por pontos de interrogação separados por
	 * vírgula, onde cada interrogação representa um das colunas que constam na
	 * cláusula INSERT ver ({@link #getNomesColunasInsert()}
	 * 
	 * @return String o texto com as interrogações separadas por vírgula.
	 */
	protected abstract String getInterrogacoesInsert();

	/**
	 * Preenche os valores das colunas do insert um a um.
	 * 
	 * Obs.: A implementação deve levar em conta o tipo exato da coluna e da
	 * entidade, e também colocar aspas simples caso o valor seja uma String.
	 * 
	 * @param preparedStmt o objeto que detém a consulta SQL criada.
	 * @throws SQLException
	 */
	protected abstract void setValoresAtributosInsert(T entidade, PreparedStatement stmt) throws SQLException;

	/**
	 * Constrói uma string com os pares chave-valor da clásula SET de um UPDATE,
	 * onde:
	 * 
	 * chave = nome da coluna, valor = valor que será atualizado na coluna (vem do
	 * objeto em questão)
	 *
	 * Obs.: A implementação deve levar em conta o tipo exato da coluna e da
	 * entidade, e também colocar aspas simples caso o valor seja uma String.
	 * 
	 * @return String a clásula SET preenchida por completo.
	 */
	protected abstract String getValoresClausulaSetUpdate();

	protected abstract void setValoresAtributosUpdate(T entidade, PreparedStatement stmt) throws SQLException;

	/**
	 * Converte um resultSet para um objeto do tipo T.
	 * 
	 * @param resultado objeto do tipo ResultSet, que armazena as tuplas retornadas
	 *                  em uma determinada consulta.
	 * 
	 * @return T o objeto da classe concreta, com seus atributos preenchidos com
	 *         valores oriundos do resultado.
	 * @throws SQLException
	 */
	protected abstract T construirObjetoDoResultSet(ResultSet resultado) throws SQLException;

	// TODO e como listar com filtros? Veremos mais à frente ;)

}