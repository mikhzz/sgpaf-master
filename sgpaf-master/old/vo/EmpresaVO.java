package model.vo;

import java.sql.ResultSet;

public class EmpresaVO {

	private int idEmpresa;
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	private String tipo;
	private String email;
	private String responsavel;

	public EmpresaVO(int idEmpresa, String nome, String cnpj, String endereco, String telefone, String tipo,
			String email, String responsavel) {
		super();
		this.idEmpresa = idEmpresa;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.tipo = tipo;
		this.email = email;
		this.responsavel = responsavel;
	}

	public EmpresaVO(String nome, String cnpj, String endereco, String telefone, String tipo, String email,
			String responsavel) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.tipo = tipo;
		this.email = email;
		this.responsavel = responsavel;
	}

	public EmpresaVO() {
		super();

	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
}
