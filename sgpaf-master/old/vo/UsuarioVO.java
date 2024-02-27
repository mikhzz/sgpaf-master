package model.vo;

import java.util.List;

public class UsuarioVO {

	private int idUsuario;
	private String nome;
	private String cpf;
	private String cargo;
	private String login;
	private String senha;
	private int ativo;
	private int habilitado;
	private PerfilVO perfil;
	private List<EmpresaVO> empresas;

	public UsuarioVO() {
		super();

	}
	
	public UsuarioVO(int idUsuario, String nome, String cpf, String cargo, String login, String senha, int ativo,
			int habilitado, PerfilVO perfil, List<EmpresaVO> empresas) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.habilitado = habilitado;
		this.perfil = perfil;
		this.empresas = empresas;
	}


	public List<EmpresaVO> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<EmpresaVO> empresas) {
		this.empresas = empresas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public int getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	
}
