package model.daoNovo.base;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public enum Cast {
	BINARY, CHAR, DATE(), DATETIME(), DECIMAL, SIGNED, TIME, UNSIGNED, BOOLEAN(" NOT NOT ( %s )"), DOUBLE("( %s + 0E0 )"),
	FLOAT("( %s + 0.0 )");

	private String op;
	private String func;

	Cast() {
		this.setFunc(opToFunc(this.name()));
	}

	Cast(String func) {
		this.setFunc(func);
	}

	private static String opToFunc(String op) {
		return "cast( %s as " + op + " )";
	}
	public  String toSimpleClassName() {
		return castToSimpleClassName(this);
	}

	public static String castToSimpleClassName(Cast c) {
		switch (c) {
		case CHAR:
			return "String";
		case SIGNED:
			return "Integer";

		case DECIMAL:
			return "BigDecimal";

		case BOOLEAN:
			return "Boolean";

		case DATE:
			return "Date";

		case DOUBLE:
			return "Double";

		case FLOAT:
			return "Float";

		case TIME:
			return "Time";

		case DATETIME:
			return "Timestamp";

		default:
			return null;
		}
	}
	
	static public Cast castOfSimpleClassName(String simpleClassName) {
		switch (simpleClassName) {
		case "String":
		case "string":
			return CHAR;
		case "int":
		case "Int":
		case "Integer":
		case "integer":
			return SIGNED;

		case "BigDecimal":
			return DECIMAL;

		case "Boolean":
		case "boolean":
			return BOOLEAN;

		case "Date":
			return DATE;

		case "Double":
		case "double":
			return DOUBLE;

		case "Float":
		case "float":
			return FLOAT;

		case "Long":
		case "long":
			return SIGNED;

		case "Null":
		case "null":
			return null;

		case "Short":
		case "short":
			return SIGNED;

		case "Time":
			return TIME;

		case "Timestamp":
			return DATETIME;

		case "URL":

			return CHAR;
		default:
			return null;
		}
	}

	static public Object stringToClass(String input, String simpleClassName) {
		switch (simpleClassName) {
		case "String":
		case "string":
			return input.toString();
		case "int":
		case "Int":
		case "Integer":
		case "integer":
			return Integer.parseInt(input);

		case "BigDecimal":
			return new BigDecimal(input);

		case "Boolean":
		case "boolean":
			return Boolean.parseBoolean(input);

		case "Date":
			return Date.valueOf(input);

		case "Double":
		case "double":
			return Double.parseDouble(input);

		case "Float":
		case "float":
			return Float.parseFloat(input);

		case "Long":
		case "long":
			return Long.parseLong(input);

		case "Null":
		case "null":
			return null;

		case "Short":
		case "short":
			return Short.parseShort(input);

		case "Time":
			return Time.valueOf(input);

		case "Timestamp":
			return Timestamp.valueOf(input);

		case "URL":
			try {
				return new URL(input);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		default:
			return null;
		}
	}

	public String getFunc(Colum colum) {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}
}
