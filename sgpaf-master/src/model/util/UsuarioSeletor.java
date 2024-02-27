package model.util;

public class UsuarioSeletor extends BaseSeletor {

	private String nome;
	private String cargo;

	public UsuarioSeletor() {

	}

	@Override
	public boolean temFiltro() {
		boolean temFiltro = false;

		
		
		if ((this.cargo != null) && (this.cargo.trim().length() > 0)) {
			temFiltro = true;
		}

		if((this.nome != null) && (this.nome.trim().length() >0)) {
			
			temFiltro = true;
		}
		
		return temFiltro;

	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
