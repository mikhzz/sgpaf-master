package model.boNovo;

public enum AccessLevel {
	USER_ADMIN("ADMIN"), USER_USER("USUARIO");

	private String dbName;

	AccessLevel(String dbName) {
		this.setDbName(dbName);
	}

	public String getDbName() {
		return dbName;
	}

	private void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
