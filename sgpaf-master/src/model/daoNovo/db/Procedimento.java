package model.daoNovo.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "procedimento")
public class Procedimento {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PROCEDIMENTO")
	private Long idProcedimento;
	@Column(name = "REFPT")
	private String refpt;
	@Column(name = "CONCLUSOES")
	private String conclusoes;
	@Column(name = "ULTIMA_ALTERACAO")
	private Date dataUltimaAlteracao;
	
	@OneToMany(mappedBy = "procedimento", cascade = {CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Checagem> checagensLista;
	
	@ManyToOne
    @JoinColumn(name = "ID_BALANCETE")
	private Balancete balancete;
	
	public Procedimento() {
		super();
	}
	public Procedimento(Long idProcedimento, String refpt, String conclusoes, 
			Balancete balancete,List<Checagem> checagensLista, Date dataUltimaAlteracao) {
		super();
		this.idProcedimento = idProcedimento;
		this.refpt = refpt;
		this.conclusoes = conclusoes;
		this.balancete = balancete;
		this.checagensLista = checagensLista;
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public List<Checagem> getChecagensLista() {
		if(checagensLista==null) {
			checagensLista = new ArrayList<Checagem>();
		}
		return checagensLista;
	}
	public void setChecagensLista(List<Checagem> checagensLista) {
		this.checagensLista = checagensLista;
	}
	public Long getIdProcedimento() {
		return idProcedimento;
	}
	public void setIdProcedimento(Long idProcedimento) {
		this.idProcedimento = idProcedimento;
	}
	public String getRefpt() {
		return refpt;
	}
	public void setRefpt(String refpt) {
		this.refpt = refpt;
	}
	public String getConclusoes() {
		return conclusoes;
	}
	public void setConclusoes(String conclusoes) {
		this.conclusoes = conclusoes;
	}
	public Balancete getBalancete() {
		return balancete;
	}
	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimento != null ? idProcedimento.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedimento)) {
            return false;
        }
        Procedimento other = (Procedimento) object;
        if ((this.idProcedimento == null && other.idProcedimento != null) || (this.idProcedimento != null && !this.idProcedimento.equals(other.idProcedimento))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "PROCEDIMENTO[ id=" + idProcedimento + " ]";
    }
}