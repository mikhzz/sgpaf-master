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
@Table(name = "usuario_folhamestra")
public class UsuarioFolhaMestra {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO_FOLHAMESTRA")
	private Long idUsuarioFolhaMestra;
	@Column(name = "DATA_VINCULO")
	private Date dataVinculo;
	@Column(name = "DESCRICAO")
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "ID_FOLHAMESTRA")
	private FolhaMestra folhaMestra;
	public UsuarioFolhaMestra(Long idUsuarioFolhaMestra, Date dataVinculo, String descricao, Usuario usuario,
			FolhaMestra folhaMestra) {
		super();
		this.idUsuarioFolhaMestra = idUsuarioFolhaMestra;
		this.dataVinculo = dataVinculo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.folhaMestra = folhaMestra;
	}
	public UsuarioFolhaMestra() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdUsuarioFolhaMestra() {
		return idUsuarioFolhaMestra;
	}
	public void setIdUsuarioFolhaMestra(Long idUsuarioFolhaMestra) {
		this.idUsuarioFolhaMestra = idUsuarioFolhaMestra;
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
	public FolhaMestra getFolhaMestra() {
		return folhaMestra;
	}
	public void setFolhaMestra(FolhaMestra folhaMestra) {
		this.folhaMestra = folhaMestra;
	}
	
	
	
}
