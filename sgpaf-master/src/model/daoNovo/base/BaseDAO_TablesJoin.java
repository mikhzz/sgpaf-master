package model.daoNovo.base;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.daoNovo.util.PropertyDescriptorSorter;

public abstract class BaseDAO_TablesJoin<T, J, L,Id> extends AutoDao<T,Id> {
	protected Table tableList;
	protected Table tableJoin;
	protected boolean getConector = true;
	protected boolean getLister = true;
	protected Join joinConnector = Join.INNER;
	protected Join joinLister = Join.INNER;
	protected Class<J> clazzJoin;
	protected Class<L> clazzList;
	protected PropertyDescriptor[] pdArrayJoin;
	protected PropertyDescriptor[] pdArrayList;

	public BaseDAO_TablesJoin(Class<T> model, Class<J> modelJoin, Class<L> modelList) {
		super(model);
		this.clazzJoin = modelJoin;
		this.tableJoin = new AutoTable(clazzJoin);
		this.pdArrayJoin = PropertyDescriptorSorter.getSortedPDArray(clazzJoin);

		this.clazzList = modelList;
		this.tableList = new AutoTable(clazzList);
		this.pdArrayList = PropertyDescriptorSorter.getSortedPDArray(clazzList);

	}

	public Table getTableJoin() {
		return tableJoin;
	}

	public void setTableJoin(Table tableJoin) {
		this.tableJoin = tableJoin;
	}

	public Table getTableList() {
		return tableList;
	}

	public void setTableList(Table tableList) {
		this.tableList = tableList;
	}

	protected J construirObjetoDoResultSetJoin(ResultSet resultado) throws SQLException {
		return construirObjetoDoResultSetGenerico(resultado, clazzJoin, pdArrayJoin);

	}

	protected L construirObjetoDoResultSetList(ResultSet resultado) throws SQLException {
		return construirObjetoDoResultSetGenerico(resultado, clazzList, pdArrayList);
	}

	protected String getNomeColunaChavePrimaria() {
		return getTable().getColums().get(0).getFullName();
	}

	private String getColunasSelect() {
		String clausulaSet = "";

		for (int i = 0; i < getTable().getColums().size(); i++) {

			if (getTable().getColums().get(i).getType().equalsIgnoreCase("point")) {
				clausulaSet += getTable().getColums().get(i).getFullName() + " as "
						+ getTable().getColums().get(i).getAlias() + " ";
			} else {
				clausulaSet += getTable().getColums().get(i).getFullName() + " as "
						+ getTable().getColums().get(i).getAlias() + " ";
			}
			if ((i + 1) < getTable().getColums().size()) {
				clausulaSet += " , ";
			}
		}

		if (getConector == true) {
			clausulaSet += " , ";
			for (int i = 0; i < getTableJoin().getColums().size(); i++) {

				if (getTableJoin().getColums().get(i).getType().equalsIgnoreCase("point")) {
					clausulaSet += getTableJoin().getColums().get(i).getFullName() + " as "
							+ getTableJoin().getColums().get(i).getAlias() + " ";
				} else {
					clausulaSet += getTableJoin().getColums().get(i).getFullName() + " as "
							+ getTableJoin().getColums().get(i).getAlias() + " ";
				}
				if ((i + 1) < getTableJoin().getColums().size()) {
					clausulaSet += " , ";
				}
			}
		}

		if (getLister == true) {
			clausulaSet += " , ";
			for (int i = 0; i < getTableList().getColums().size(); i++) {

				if (getTableList().getColums().get(i).getType().equalsIgnoreCase("point")) {
					clausulaSet += getTableList().getColums().get(i).getFullName() + " as "
							+ getTableList().getColums().get(i).getAlias() + " ";
				} else {
					clausulaSet += getTableList().getColums().get(i).getFullName() + " as "
							+ getTableList().getColums().get(i).getAlias() + " ";
				}
				if ((i + 1) < getTableList().getColums().size()) {
					clausulaSet += " , ";
				}
			}
		}
		return clausulaSet;
	}

	private String getJoinString() {
		// tableJoin = new UsuarioEmpresaDAO().getTable();
		// System.out.println("==================aqui na no
		// baseDAO_tablesJoin====================");
		String clausulaSet = "";
		clausulaSet += table.getName();
		clausulaSet += joinConnector.op;
		clausulaSet += tableJoin.getName();
		clausulaSet += " on ";
		clausulaSet += table.getIdCollum().getFullName();
		clausulaSet += " = ";
		clausulaSet += tableJoin.getName() + "." + table.getId();
		clausulaSet += joinLister.op;
		clausulaSet += tableList.getName();
		clausulaSet += " on ";
		clausulaSet += tableList.getIdCollum().getFullName();
		clausulaSet += " = ";
		clausulaSet += tableJoin.getName() + "." + tableList.getId();

		return clausulaSet;
	}


	public JoinAgregator<T, J, L> pesquisarJoinPorId(Id idEntidade) {
		return pesquisarJoinPorFiltros( idEntidade, null);
	}

	public JoinAgregator<T, J, L> pesquisarJoinPorFiltro(Id idEntidade, Filtro filtro) {
		ArrayList<Filtro> filtros = new ArrayList<Filtro>();
		if(null!=filtro){
			filtros.add(filtro);
		}
		return pesquisarJoinPorFiltros(idEntidade, filtros);
	}

	public JoinAgregator<T, J, L> pesquisarJoinPorFiltros(Id idEntidade, List<Filtro> filtros) {
		if(null==filtros){
			 filtros = new ArrayList<Filtro>();
		}
		String sql = "SELECT " + getColunasSelect() + " FROM " + getJoinString() + " WHERE "
				+ getNomeColunaChavePrimaria() + " = " + idEntidade + getValoresClausulaFlitros(filtros);
		System.out.println(sql);
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet resultado = null;
		JoinAgregator<T, J, L> joinAgregator = new JoinAgregator<T, J, L>();
		T objetoConsultado = null;
		ArrayList<J> listaJoin = new ArrayList<J>();
		ArrayList<L> listaList = new ArrayList<L>();

		try {
			this.setValoresAtributosWhereFiltros(filtros, stmt);
			resultado = stmt.executeQuery();
			while (resultado.next()) {
				i = 1;
				if (objetoConsultado == null) {
					objetoConsultado = construirObjetoDoResultSet(resultado);
				} else {
					i += getTable().getColums().size();
				}

				J objetoConsultadoJoin = construirObjetoDoResultSetJoin(resultado);
				listaJoin.add(objetoConsultadoJoin);
				L objetoConsultadoList = construirObjetoDoResultSetList(resultado);
				listaList.add(objetoConsultadoList);

			}
			joinAgregator.setT(objetoConsultado);
			joinAgregator.setJoinList(listaJoin);
			joinAgregator.setListList(listaList);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar o registro com id = " + idEntidade + "da entidade "
					+ this.getClass().toString());
			System.out.println(e.getLocalizedMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return joinAgregator;
	}

}
