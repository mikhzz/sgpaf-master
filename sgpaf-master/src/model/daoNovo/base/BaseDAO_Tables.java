package model.daoNovo.base;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseDAO_Tables<T, Id> extends BaseDAO<T, Id> {
	protected Table table;

	public Table getTable() {
		return table;
	}

	public BaseDAO_Tables(Table table) {
		super();
		this.table = table;
	}

	@Override
	protected String getNomeTabela() {
		return getTable().getName();

	}

	@Override
	protected String getNomeColunaChavePrimaria() {
		return getTable().getColumn(0).getFullName();
	}

	@Override
	protected String getNomesColunasInsert() {
		String s = "";

		for (int i = 1; i < getTable().getColums().size(); i++) {
			s = s + getTable().getColumn(i).getFullName();

			if ((i + 1) != getTable().getColums().size()) {
				s = s + ",";
			}
		}

		return s;
	}

	@Override
	protected String getInterrogacoesInsert() {
		String s = "";
		for (int i = 1; i < getTable().getColums().size(); i++) {
			if (getTable().getColumn(i).getType().equalsIgnoreCase("point")) {
				s = s + "point (?,?)";
			} else {
				s = s + "?";
			}

			if ((i + 1) < getTable().getColums().size()) {
				s = s + ",";
			}
		}

		return s;
	}

	@Override
	protected String getValoresClausulaSetUpdate() {
		// TODO Auto-generated method stub
		String clausulaSet = "";
		for (int i = 0; i < getTable().getColums().size(); i++) {
			if (getTable().getColumn(i).getType().equalsIgnoreCase("point")) {
				clausulaSet += getTable().getColumn(i).getFullName() + " = point (?,?)";
			} else {
				clausulaSet += getTable().getColumn(i).getFullName() + " =  ?";
			}

			if ((i + 1) < getTable().getColums().size()) {
				clausulaSet += ",";
			}
		}
		return clausulaSet;
	}

	protected String getValoresClausulaWhereFlitros(List<Filtro> filtros) {
		return getValoresClausulaFlitros(filtros,  " WHERE ");
	}
	protected String getValoresClausulaFlitros(List<Filtro> filtros) {
		return getValoresClausulaFlitros(filtros, "");
	}

	protected String getValoresClausulaFlitros(List<Filtro> filtros, String clausulaSet) {
		if (filtros.size() <= 0) {
			return "";
		}
		if( 0 < filtros.size()) {
			clausulaSet += filtros.get(0).getClausula();
		}
		for (int i = 1; i < filtros.size(); i++) {
			clausulaSet += " AND ";
			clausulaSet += filtros.get(i).getClausula();
		}
		return clausulaSet;
	}

	public List<T> consultarWhereFiltro(Filtro filtro) {
		return consultarWhereFiltros((List<Filtro>) Arrays.asList(filtro));
	}

	public List<T> consultarWhereFiltros(List<Filtro> filtros,Extra extra) {
		String sql = "SELECT * FROM " + getNomeTabela() + getValoresClausulaWhereFlitros(filtros) +getClausulaExtra(extra) + ";";
		System.out.println(sql);

		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet resultado = null;
		ArrayList<T> listaEntidades = new ArrayList<T>();

		try {
			int stmtIndex =this.setValoresAtributosWhereFiltros(filtros, stmt);
			this.setValoresAtributosExtra(extra, stmt,stmtIndex);

			resultado = stmt.executeQuery();
			while (resultado.next()) {
				i = 1;
				T objetoConsultado = construirObjetoDoResultSet(resultado);
				listaEntidades.add(objetoConsultado);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaEntidades;
	}
	
	private void setValoresAtributosExtra(Extra extra, PreparedStatement stmt, int stmtIndex) throws SQLException {
		if(extra==null) {
			return;
		}
		if(extra.getLimit()!=null){
			stmt.setInt(++stmtIndex, 0);
			stmt.setInt(++stmtIndex, extra.getLimit());
		}
	}

	private String getClausulaExtra(Extra extra) {
		if(extra==null) {
			return"";
		}
		String clausula = " ";
		if(extra.getLimit()!=null){
			clausula+=	"LIMIT ?, ? ";
		}
		return clausula;
	}

	public List<T> consultarWhereFiltros(List<Filtro> filtros) {
		return consultarWhereFiltros( filtros,null);
	}

	protected int setValoresAtributosWhereFiltros(List<Filtro> filtros, PreparedStatement stmt) throws SQLException {
		int stmtIndex =1;
		for (int count = 0; count < filtros.size(); count++) {
			stmtIndex = count+1;
			String simpleClassName;
			if (filtros.get(count).getValue() == null) {
				if (filtros.get(count).getCast() == null) {
					simpleClassName = filtros.get(count).getCol().getType();
				} else {
					simpleClassName = filtros.get(count).getCast().toSimpleClassName();
				}
			} else {
				simpleClassName = filtros.get(count).getValue().getClass().getSimpleName();
			}
			switch (simpleClassName) {
			case "String":
			case "string":
				if (filtros.get(count).getValue() == null) {
					stmt.setString(stmtIndex, (String) null);
				} else {
					stmt.setString(stmtIndex, filtros.get(count).getValue().toString());
				}
				break;
			case "int":
			case "Int":
			case "Integer":
			case "integer":
				stmt.setInt(stmtIndex, (Integer) filtros.get(count).getValue());
				break;

			case "BigDecimal":
				stmt.setBigDecimal(stmtIndex, (BigDecimal) filtros.get(count).getValue());
				break;

			case "Boolean":
			case "boolean":
				stmt.setBoolean(stmtIndex, (Boolean) filtros.get(count).getValue());
				break;

			case "Date":
				stmt.setDate(stmtIndex, (Date) filtros.get(count).getValue());
				break;

			case "Double":
			case "double":
				stmt.setDouble(stmtIndex, (Double) filtros.get(count).getValue());
				break;

			case "Float":
			case "float":
				stmt.setFloat(stmtIndex, (Float) filtros.get(count).getValue());
				break;

			case "Long":
			case "long":
				stmt.setLong(stmtIndex, (Long) filtros.get(count).getValue());
				break;

			case "Short":
			case "short":
				stmt.setShort(stmtIndex, (Short) filtros.get(count).getValue());
				break;

			case "Time":
				stmt.setTime(stmtIndex, (Time) filtros.get(count).getValue());
				break;

			case "Timestamp":
				stmt.setTimestamp(stmtIndex, (Timestamp) filtros.get(count).getValue());
				break;

			case "URL":
				stmt.setURL(stmtIndex, (URL) filtros.get(count).getValue());
				break;

			default:
				System.out.println("Erro ao identificar tipo :" + filtros.get(count).getCol().getType() + " de:"
						+ filtros.get(count).getCol().getFullName() + " valor:" + filtros.get(count).getValue().toString());
				break;
			}

		}
		return stmtIndex;
	}

}
