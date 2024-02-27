package model.vo;

public class PerfilVO {
	
	private int idPerfil;
	private String descricao;
	private String tipo;
	
	public PerfilVO(int idPerfil, String descricao, String tipo) {
		super();
		this.idPerfil = idPerfil;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public PerfilVO() {
		super();
	}

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
