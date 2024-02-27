package model.daoNovo.base;

public enum Comparador {
	IGUAL(" = "), MAIOR(" > "), MENOR(" < "), MAIOR_IGUAL(" >= "), MENOR_IGUAL(" <= "), DIFERENTE(" <> "), 
	LIKE(" LIKE "), NOT_LIKE(" NOT LIKE "), REGEXP(" REGEXP "), NOT_REGEXP(" NOT REGEXP ");
	public String op;

	Comparador(String op) {
		this.op = op;
	}
}
