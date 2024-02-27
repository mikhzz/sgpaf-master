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
@Table(name = "usuario_empresa")
public class UsuarioEmpresa {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO_EMPRESA")
	private Long idUsuarioEmpresa;
	@Column(name = "DATA_INICIO")
	private Date dataInicio;
	@Column(name = "DATA_FIM")
	private Date dataFim;
	@Column(name = "ATIVO")
	private int ativo;
	@ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;
	@ManyToOne
    @JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	
	public UsuarioEmpresa() {
		super();
		this.dataInicio = new Date();
		this.ativo = 1;
	}
	public UsuarioEmpresa(Long idUsuarioEmpresa, Date dataInicio, Date dataFim, Empresa empresa,
			Usuario usuario, int ativo) {
		super();
		this.idUsuarioEmpresa = idUsuarioEmpresa;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.empresa = empresa;
		this.usuario = usuario;
		this.ativo = ativo;
	}
	public Long getIdUsuarioEmpresa() {
		return idUsuarioEmpresa;
	}
	public void setIdUsuarioEmpresa(Long idUsuarioEmpresa) {
		this.idUsuarioEmpresa = idUsuarioEmpresa;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioEmpresa!= null ? idUsuarioEmpresa.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEmpresa)) {
            return false;
        }
        UsuarioEmpresa other = (UsuarioEmpresa) object;
        if ((this.idUsuarioEmpresa == null && other.idUsuarioEmpresa != null) || (this.idUsuarioEmpresa != null && !this.idUsuarioEmpresa.equals(other.idUsuarioEmpresa))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "USUARIO EMPRESA id=" + idUsuarioEmpresa ;
    }
}