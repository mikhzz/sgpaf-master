package model.daoNovo.base;

public enum Join {

	INNER(" INNER JOIN "), LEFT(" LEFT JOIN "), RIGHT(" RIGHT JOIN ");
	String op;

	Join(String op) {
		this.op = op;
	}

}
