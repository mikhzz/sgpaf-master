package model.daoNovo.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_procedimento")
public class UsuarioProcedimento {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO_PROCEDIMENTO")
	private Long idUsuarioProcedimento;
	@Column(name = "DATA_VINCULO")
	private Date dataVinculo;
	@Column(name = "DESCRICAO")
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "ID_PROCEDIMENTO")
	private Procedimento procedimento;
	public UsuarioProcedimento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsuarioProcedimento(Long idUsuarioProcedimento, Date dataVinculo, String descricao, Usuario usuario,
			Procedimento procedimento) {
		super();
		this.idUsuarioProcedimento = idUsuarioProcedimento;
		this.dataVinculo = dataVinculo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.procedimento = procedimento;
	}
	public Long getIdUsuarioProcedimento() {
		return idUsuarioProcedimento;
	}
	public void setIdUsuarioProcedimento(Long idUsuarioProcedimento) {
		this.idUsuarioProcedimento = idUsuarioProcedimento;
	}
	public Date getDataVinculo() {
		return dataVinculo;
	}
	public void setDataVinculo(Date dataVinculo) {
		this.dataVinculo = dataVinculo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}
	
	
	
	
}
