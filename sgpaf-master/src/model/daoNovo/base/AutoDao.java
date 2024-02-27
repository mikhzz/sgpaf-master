package model.daoNovo.base;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import model.daoNovo.util.PropertyDescriptorSorter;

public class AutoDao<T,Id> extends BaseDAO_Tables<T,Id> {
	protected Class<T> clazz;
	protected PropertyDescriptor[] pdArray;

	public AutoDao(Class<T> model) {
		super(new AutoTable(model));
		this.clazz = (Class<T>) model;
		this.pdArray = PropertyDescriptorSorter.getSortedPDArray(clazz);
	}

	public AutoDao(Table table, Class<T> model, PropertyDescriptor[] pdArray) {
		super(table);
		this.clazz = (Class<T>) model;
		this.pdArray = pdArray;
	}

	@Override
	protected void setValoresAtributosInsert(T entidade, PreparedStatement stmt) throws SQLException {
		i = 1;
		boolean skip = true;
		for (PropertyDescriptor pd : pdArray) {
			if (skip) {
				skip = false;
				continue;
			}
			try {

				setStmtField(stmt, pd.getPropertyType().getSimpleName(), pd.getReadMethod().invoke(entidade),i);

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}

	}

	@Override
	protected void setValoresAtributosUpdate(T entidade, PreparedStatement stmt) throws SQLException {
		i = 1;
		for (PropertyDescriptor pd : pdArray) {
			try {

				setStmtField(stmt, pd.getPropertyType().getSimpleName(), pd.getReadMethod().invoke(entidade),i);

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

	@Override
	protected T construirObjetoDoResultSet(ResultSet resultado) throws SQLException {
		return construirObjetoDoResultSetGenerico(resultado, clazz, pdArray);
	}

	protected <E> E construirObjetoDoResultSetGenerico(ResultSet resultado, Class<E> clazzGeneric,
			PropertyDescriptor[] pdArrayGeneric) throws SQLException {
		E entidade = null;
		try {

			entidade = clazzGeneric.newInstance();
			for (int count = 0; pdArrayGeneric.length > count; count++) {
				PropertyDescriptor pd = pdArrayGeneric[count];
				System.out.println(count);
				System.out.println(pd.getName());
				getRSField(resultado, pd, entidade);
			}

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entidade;
	}

	protected <E> void getRSField(ResultSet resultado, PropertyDescriptor pd, E entidade)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
		switch (pd.getPropertyType().getSimpleName()) {
		case "String":
		case "string":
			pd.getWriteMethod().invoke(entidade, resultado.getString(i++));
			break;
		case "int":
		case "Int":
		case "Integer":
		case "integer":
			pd.getWriteMethod().invoke(entidade, resultado.getInt(i++));
			break;

		case "BigDecimal":
			pd.getWriteMethod().invoke(entidade, resultado.getBigDecimal(i++));
			break;

		case "Boolean":
		case "boolean":
			pd.getWriteMethod().invoke(entidade, resultado.getBoolean(i++));
			break;

		case "Date":
			pd.getWriteMethod().invoke(entidade, resultado.getDate(i++));
			break;

		case "Double":
		case "double":
			pd.getWriteMethod().invoke(entidade, resultado.getDouble(i++));
			break;

		case "Float":
		case "float":
			pd.getWriteMethod().invoke(entidade, resultado.getFloat(i++));
			break;

		case "Long":
		case "long":
			pd.getWriteMethod().invoke(entidade, resultado.getLong(i++));
			break;

		case "Null":
		case "null":
			pd.getWriteMethod().invoke(entidade, (Object) null);
			i++;
			break;

		case "Short":
		case "short":
			pd.getWriteMethod().invoke(entidade, resultado.getShort(i++));
			break;

		case "Time":
			pd.getWriteMethod().invoke(entidade, resultado.getTime(i++));
			break;

		case "Timestamp":
			pd.getWriteMethod().invoke(entidade, resultado.getTimestamp(i++));
			break;

		case "URL":
			pd.getWriteMethod().invoke(entidade, resultado.getTimestamp(i++));
			break;
		case "Instant":
			pd.getWriteMethod().invoke(entidade, resultado.getDate(i++).toInstant());
			break;
		default:
			System.out.println("Erro ao identificar tipo :" + pd.getPropertyType().getName() + " de:" + pd.getName()
					+ " valor:" + pd.getReadMethod().invoke(entidade));
			i++;
			break;
		}

	}


}
