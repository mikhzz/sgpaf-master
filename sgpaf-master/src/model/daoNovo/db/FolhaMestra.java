package model.daoNovo.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "folhamestra")
public class FolhaMestra {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_FOLHAMESTRA")
	private Long idFolhamestra;
	@Column(name = "CONCLUSAO")
	private String conclusao;
	@Column(name = "ATIVO")
	private int ativo;
	@Column(name = "ULTIMA_ALTERACAO")
	private Date ultimaAlteracao;
	@Column(name = "CHECKBOX_REVISADO")
	private boolean checkBoxRevisado;
	@Column(name = "DATA_REVISADO")
	private Date dataRevisado;
	@ManyToOne
    @JoinColumn(name = "ID_BALANCETE")
	private Balancete balancete;
	
	public FolhaMestra() {
		super();
		this.ativo=1;
	}
	public FolhaMestra(Long idFolhamestra, String conclusao, Boolean checkboxRevisado, Date dataRevisado,
			Balancete balancete, int ativo , Date ultimaAlteracao) {
		super();
		this.idFolhamestra = idFolhamestra;
		this.conclusao = conclusao;
		this.checkBoxRevisado = checkboxRevisado;
		this.balancete = balancete;
		this.ativo = ativo;
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public boolean isCheckBoxRevisado() {
		return checkBoxRevisado;
	}
	public void setCheckBoxRevisado(boolean checkBoxRevisado) {
		this.checkBoxRevisado = checkBoxRevisado;
	}
	public Date getDataRevisado() {
		return dataRevisado;
	}
	public void setDataRevisado(Date dataRevisado) {
		this.dataRevisado = dataRevisado;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public Long getIdFolhamestra() {
		return idFolhamestra;
	}
	public void setIdFolhamestra(Long idFolhamestra) {
		this.idFolhamestra = idFolhamestra;
	}
	public String getConclusao() {
		return conclusao;
	}
	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}
	public Boolean getCheckboxRevisado() {
		return checkBoxRevisado;
	}
	public void setCheckboxRevisado(Boolean checkboxRevisado) {
		this.checkBoxRevisado = checkboxRevisado;
	}
	public Balancete getBalancete() {
		return balancete;
	}
	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
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
        hash += (idFolhamestra!= null ? idFolhamestra.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FolhaMestra)) {
            return false;
        }
        FolhaMestra other = (FolhaMestra) object;
        if ((this.idFolhamestra == null && other.idFolhamestra != null) || (this.idFolhamestra != null && !this.idFolhamestra.equals(other.idFolhamestra))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "id FOLHA MESTRA =" + idFolhamestra ;
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


