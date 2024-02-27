package model.daoNovo.base;

import com.google.common.base.CaseFormat;

public class Colum {
	private String type;
	private String name;
	private String javaName;
	private String fullName;
	private String alias;

	private Table table;

	public Colum(String type, String name, Table table) {
		super();
		this.type = type;
		this.name = name;
		this.table = table;
		this.fullName=(table.getName() + "." + name);
		this.setAlias(("'"+table.getName() + "." + name+"'"));
		this.javaName = (CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name));

	}

	public Colum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public String getJavaName() {
		return javaName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


}
