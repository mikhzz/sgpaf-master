package model.daoNovo.db;
import java.math.BigDecimal;

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
@Table(name = "lancamento")
public class Lancamento {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LANCAMENTO")
	private Long idLancamento;
	@Column(name = "REFPT")
	private String refpt;
	@Column(name = "NATUREZA_CONTA")
	private String naturezaConta;
	@Column(name = "NIVEL")
	private Integer nivel;
	@Column(name = "CODIGO_ESTRUTURADO")
	private String codigoEstruturado;
	@Column(name = "CODIGO_REDUZIDO")
	private Integer codigoReduzido;
	@Column(name = "DESCRICAO")
	private String descricao;
	@Column(name = "SALDO_INICIAL")
	private BigDecimal saldoInicial;
	@Column(name = "DEBITO")
	private BigDecimal debito;
	@Column(name = "CREDITO")
	private BigDecimal credito;
	@Column(name = "SALDO_FINAL")
	private BigDecimal saldoFinal;
	@Column(name = "AH")
	private BigDecimal aH;
	@Column(name = "AV")
	private BigDecimal aV;
	@Column(name = "SALDO_VALIDADO")
	private BigDecimal saldoValidado;
	@Column(name = "DIFERENCA")
	private BigDecimal diferenca;
	@Column(name = "RECOMENDACAO")
	private String recomendacao;
	@Column(name = "DIVERGENCIA")
	private boolean divergencia;
	@ManyToOne
    @JoinColumn(name = "ID_BALANCETE")
	private Balancete balancete;
	public Lancamento() {
		super();
		this.saldoValidado = new BigDecimal("0.00");
		this.diferenca= new BigDecimal("0.00");
	}
	public Lancamento(Long idLancamento, String refpt, String naturezaConta, Integer nivel, String codigoEstruturado,
			Integer codigoReduzido, String descricao, BigDecimal saldoInicial, BigDecimal debito, BigDecimal credito,
			BigDecimal saldoFinal, BigDecimal aH, BigDecimal aV, BigDecimal saldoValidado, BigDecimal diferenca,
			Balancete balancete) {
		super();
		this.idLancamento = idLancamento;
		this.refpt = refpt;
		this.naturezaConta = naturezaConta;
		this.nivel = nivel;
		this.codigoEstruturado = codigoEstruturado;
		this.codigoReduzido = codigoReduzido;
		this.descricao = descricao;
		this.saldoInicial = saldoInicial;
		this.debito = debito;
		this.credito = credito;
		this.saldoFinal = saldoFinal;
		this.aH = aH;
		this.aV = aV;
		this.saldoValidado = saldoValidado;
		this.diferenca = diferenca;
		this.balancete = balancete;
	}
	public Long getIdLancamento() {
		return idLancamento;
	}
	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}
	public String getRefpt() {
		return refpt;
	}
	public void setRefpt(String refpt) {
		this.refpt = refpt;
	}
	public String getNaturezaConta() {
		return naturezaConta;
	}
	public void setNaturezaConta(String naturezaConta) {
		this.naturezaConta = naturezaConta;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	public String getCodigoEstruturado() {
		return codigoEstruturado;
	}
	public void setCodigoEstruturado(String codigoEstruturado) {
		this.codigoEstruturado = codigoEstruturado;
	}
	public Integer getCodigoReduzido() {
		return codigoReduzido;
	}
	public void setCodigoReduzido(Integer codigoReduzido) {
		this.codigoReduzido = codigoReduzido;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public BigDecimal getDebito() {
		return debito;
	}
	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}
	public BigDecimal getCredito() {
		return credito;
	}
	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}
	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	public Balancete getBalancete() {
		return balancete;
	}
	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}
	public BigDecimal getaH() {
		return aH;
	}
	public void setaH(BigDecimal aH) {
		this.aH = aH;
	}
	public BigDecimal getaV() {
		return aV;
	}
	public void setaV(BigDecimal aV) {
		this.aV = aV;
	}
	public BigDecimal getSaldoValidado() {
		return saldoValidado;
	}
	public void setSaldoValidado(BigDecimal saldoValidado) {
		this.saldoValidado = saldoValidado;
	}
	public BigDecimal getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}
	
	public String getRecomendacao() {
		return recomendacao;
	}
	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}
	public boolean isDivergencia() {
		return divergencia;
	}
	public void setDivergencia(boolean divergencia) {
		this.divergencia = divergencia;
	}
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idLancamento != null ? idLancamento.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.idLancamento == null && other.idLancamento != null) || (this.idLancamento != null && !this.idLancamento.equals(other.idLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return refpt ;
    }
	

}
