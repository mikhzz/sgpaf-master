package model.daoNovo.base;

public class Filtro {
	private Comparador comparador;
	private Cast cast;
	private Colum col;
	private Object value;
	private Filtro alt;

	public Filtro( Colum col,Comparador comparador, Object value, Cast cast ,Filtro alt) {
		super();
		this.comparador = comparador;
		this.col = col;
		this.value = value;
		this.cast=cast;
		this.alt = alt;
	}
	public Filtro( Colum col,Comparador comparador, Object value,Cast cast) {
		super();
		this.comparador = comparador;
		this.col = col;
		this.value = value;
		this.cast=cast;
		this.alt = null;

	}
	public Filtro( Colum col,Comparador comparador, Object value, Filtro alt) {
		super();
		this.comparador = comparador;
		this.col = col;
		this.value = value;
		this.alt = alt;
		this.cast=null;
	}
	public Filtro( Colum col,Comparador comparador, Object value) {
		super();
		this.comparador = comparador;
		this.col = col;
		this.value = value;
		this.alt = null;
		this.cast=null;
	}
	public Comparador getComparador() {
		return comparador;
	}
	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}
	public Colum getCol() {
		return col;
	}
	public void setCol(Colum col) {
		this.col = col;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Filtro getAlt() {
		return alt;
	}
	public void setAlt(Filtro alt) {
		this.alt = alt;
	}
	public Cast getCast() {
		return cast;
	}
	public void setCast(Cast cast) {
		this.cast = cast;
	}
	public String getClausula() {
		String clausulaSet;
		if (this.getCast() == null) {
			clausulaSet= this.getCol().getFullName();
		}else {
			clausulaSet= String.format(this.getCast().getFunc(this.getCol()), this.getCol().getFullName());
		}
		clausulaSet += getComparador().op + "  ? ";
		return clausulaSet;
	}
}
	
	

