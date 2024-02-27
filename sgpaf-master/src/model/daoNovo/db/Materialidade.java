package model.daoNovo.db;

import java.math.BigDecimal;

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
@Table(name = "materialidade")
public class Materialidade {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_MATERIALIDADE")
	private Long idMaterialidade;
	@Column(name = "LUCRO")
	private BigDecimal lucro;
	@Column(name = "LUCRO_PORC")
	private BigDecimal lucro_porc;
	@Column(name = "ATIVO_TOTAL")
	private BigDecimal ativoTotal;
	@Column(name = "ATIVO_PORC")
	private BigDecimal ativoPorc;
	@Column(name = "RECEITA")
	private BigDecimal receita;
	@Column(name = "RECEITA_PORC")
	private BigDecimal receita_porc;
	@Column(name = "PATRIMONIO")
	private BigDecimal patrimonio;
	@Column(name = "PATRIMONIO_PORC")
	private BigDecimal patrimonio_porc;
	@Column(name = "MATERIALIDADE_GLOBAL")
	private BigDecimal materialidadeGlobal;
	@Column(name = "ATIVO")
	private int ativo;
	@OneToOne
    @JoinColumn(name = "ID_BALANCETE")
	private Balancete balancete;
	
	public Materialidade() {
		super();
		this.lucro = new BigDecimal("0.00");
		this.lucro_porc = new BigDecimal("0.00");
		this.ativoTotal = new BigDecimal("0.00");
		this.ativoPorc = new BigDecimal("0.00");
		this.receita = new BigDecimal("0.00");
		this.receita_porc = new BigDecimal("0.00");
		this.patrimonio = new BigDecimal("0.00");
		this.patrimonio_porc = new BigDecimal("0.00");
		this.materialidadeGlobal = new BigDecimal("0.00");
		this.ativo = 1;
	}

	public Materialidade(Long idMaterialidade, BigDecimal lucro, BigDecimal lucro_porc, BigDecimal ativoTotal,
			BigDecimal ativoPorc, BigDecimal receita, BigDecimal receita_porc, BigDecimal patrimonio,
			BigDecimal patrimonio_porc, BigDecimal materialidadeGlobal, int ativo, Balancete balancete) {
		super();
		this.idMaterialidade = idMaterialidade;
		this.lucro = lucro;
		this.lucro_porc = lucro_porc;
		this.ativoTotal = ativoTotal;
		this.ativoPorc = ativoPorc;
		this.receita = receita;
		this.receita_porc = receita_porc;
		this.patrimonio = patrimonio;
		this.patrimonio_porc = patrimonio_porc;
		this.materialidadeGlobal = materialidadeGlobal;
		this.ativo = ativo;
		this.balancete = balancete;
	}
	
	public Long getIdMaterialidade() {
		return idMaterialidade;
	}
	public void setIdMaterialidade(Long idMaterialidade) {
		this.idMaterialidade = idMaterialidade;
	}
	public BigDecimal getLucro() {
		return lucro;
	}
	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}
	public BigDecimal getLucro_porc() {
		return lucro_porc;
	}
	public void setLucro_porc(BigDecimal lucro_porc) {
		this.lucro_porc = lucro_porc;
	}
	public BigDecimal getAtivoTotal() {
		return ativoTotal;
	}
	public void setAtivoTotal(BigDecimal ativoTotal) {
		this.ativoTotal = ativoTotal;
	}
	public BigDecimal getAtivoPorc() {
		return ativoPorc;
	}
	public void setAtivoPorc(BigDecimal ativoPorc) {
		this.ativoPorc = ativoPorc;
	}
	public BigDecimal getReceita() {
		return receita;
	}
	public void setReceita(BigDecimal receita) {
		this.receita = receita;
	}
	public BigDecimal getReceita_porc() {
		return receita_porc;
	}
	public void setReceita_porc(BigDecimal receita_porc) {
		this.receita_porc = receita_porc;
	}
	public BigDecimal getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(BigDecimal patrimonio) {
		this.patrimonio = patrimonio;
	}
	public BigDecimal getPatrimonio_porc() {
		return patrimonio_porc;
	}
	public void setPatrimonio_porc(BigDecimal patrimonio_porc) {
		this.patrimonio_porc = patrimonio_porc;
	}
	public BigDecimal getMaterialidadeGlobal() {
		return materialidadeGlobal;
	}
	public void setMaterialidadeGlobal(BigDecimal materialidadeGlobal) {
		this.materialidadeGlobal = materialidadeGlobal;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public Balancete getBalancete() {
		return balancete;
	}
	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
