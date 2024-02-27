package model.daoNovo.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	@Column(name = "NOME", nullable = false, unique = true)
	private String nome;
	@Column(name = "CPF", nullable = false, unique = true)
	private String cpf;
	@Column(name = "CARGO", nullable = false, unique = true)
	private String cargo;
	@Column(name = "LOGIN", nullable = false, unique = true)
	private String login;
	@Column(name = "SENHA", nullable = false, unique = true)
	private String senha;
	@Column(name = "ATIVO")
	private int ativo;
	@Column(name = "ENABLE")
	private int habilitado;
	@ManyToOne
    @JoinColumn(name = "ID_PERFIL")
	private Perfil perfil;
//	private List<Empresa> empresas;
	
	
	
	
	
	public Usuario() {
		super();
		this.ativo= 1;
		this.habilitado = 1;
		// TODO Auto-generated constructor stub
	}
	public Usuario(Long idUsuario, String nome, String cpf, String cargo, String login, String senha,
			int ativo, int habilitado, Perfil perfil) {
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
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public Perfil getPerfil() {
		 if (perfil == null) {
	            perfil = new Perfil();
	        }
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
	
}
