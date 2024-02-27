package model.daoNovo.db;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "balancete")
public class Balancete {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_BALANCETE")
	private Long idBalancete;
	@Column(name = "SALDOTOTAL")
	private BigDecimal saldototal;
	@Column(name = "DATA_BASE")
	private Integer database;
	@Column(name = "DATA_BALANCO_INICIO")
	private Date dataBalancoInicio;
	@Column(name = "DATA_BALANCO_FIM")
	private Date dataBalancoFim;
	@Column(name = "ATIVO")
	private int ativo;
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;

	@OneToMany(mappedBy = "balancete", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private List<Lancamento> lancamentos;
	public Balancete() {
		super();
		this.ativo = 1;
		this.dataBalancoInicio = new Date();
	}

	public Balancete(Long idBalancete, BigDecimal saldototal, Integer database, Date dataBalancoInicio, Date dataBalancoFim,
			Empresa empresa, int ativo,List<Lancamento> lancamentos) {
		super();
		this.idBalancete = idBalancete;
		this.saldototal = saldototal;
		this.database = database;
		this.dataBalancoInicio = dataBalancoInicio;
		this.dataBalancoFim = dataBalancoFim;
		this.empresa = empresa;
		this.ativo = ativo;
		this.lancamentos = lancamentos;
		this.saldototal = lancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals("AA") && p.getNivel().equals(6)).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);

	}
	public Long getIdBalancete() {
		return idBalancete;
	}
	public void setIdBalancete(Long idBalancete) {
		this.idBalancete = idBalancete;
	}

	public BigDecimal getSaldototal() {
		this.saldototal = this.saldototal = lancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals("AA") && p.getNivel().equals(6)).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		return saldototal;
	}

	public void setSaldototal(BigDecimal saldototal) {
		this.saldototal = saldototal;
	}

	public Integer getDatabase() {
		return database;
	}

	public void setDatabase(Integer database) {
		this.database = database;
	}

	public Date getDataBalancoInicio() {
		return dataBalancoInicio;
	}

	public void setDataBalancoInicio(Date dataBalancoInicio) {
		this.dataBalancoInicio = dataBalancoInicio;
	}

	public Date getDataBalancoFim() {
		return dataBalancoFim;
	}

	public void setDataBalancoFim(Date dataBalancoFim) {
		this.dataBalancoFim = dataBalancoFim;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idBalancete != null ? idBalancete.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Balancete)) {
			return false;
		}
		Balancete other = (Balancete) object;
		if ((this.idBalancete == null && other.idBalancete != null)
				|| (this.idBalancete != null && !this.idBalancete.equals(other.idBalancete))) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "BALANCETE[ id=" + idBalancete + " ]";
	}
}