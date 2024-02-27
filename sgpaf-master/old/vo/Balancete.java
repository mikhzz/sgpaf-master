package model.vo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Balancete {

	private int idBalancete;
	private int idEmpresa;
	private String naturezaConta;
	private double codigoEstruturado;
	private double codigoReduzido; // unique
	private double classificacao;
	private String descricao;
	private double saldoInicial;
	private double debitos;
	private double creditos;
	private double saldoAtual;
	private Date dataBase1;
	private Date dataBalanco;
	private ArrayList<LancamentoVO> lancamentos;
	

	public Balancete() {
		super();

	}

public Balancete(int idBalancete, int idEmpresa, String naturezaConta, double codigoEstruturado, double codigoReduzido,
		double classificacao, String descricao, double saldoInicial, double debitos, double creditos, double saldoAtual,
		Date dataBase1, Date dataBalanco) {
	super();
	this.idBalancete = idBalancete;
	this.idEmpresa = idEmpresa;
	this.naturezaConta = naturezaConta;
	this.codigoEstruturado = codigoEstruturado;
	this.codigoReduzido = codigoReduzido;
	this.classificacao = classificacao;
	this.descricao = descricao;
	this.saldoInicial = saldoInicial;
	this.debitos = debitos;
	this.creditos = creditos;
	this.saldoAtual = saldoAtual;
	this.dataBase1 = dataBase1;
	this.dataBalanco = dataBalanco;
}


public int getIdBalancete() {
	return idBalancete;
}
public void setIdBalancete(int idBalancete) {
	this.idBalancete = idBalancete;
}
public int getIdEmpresa() {
	return idEmpresa;
}
public void setIdEmpresa(int idEmpresa) {
	this.idEmpresa = idEmpresa;
}
public String getNaturezaConta() {
	return naturezaConta;
}
public void setNaturezaConta(String naturezaConta) {
	this.naturezaConta = naturezaConta;
}
public double getCodigoEstruturado() {
	return codigoEstruturado;
}
public void setCodigoEstruturado(double codigoEstruturado) {
	this.codigoEstruturado = codigoEstruturado;
}
public double getCodigoReduzido() {
	return codigoReduzido;
}
public void setCodigoReduzido(double codigoReduzido) {
	this.codigoReduzido = codigoReduzido;
}
public double getClassificacao() {
	return classificacao;
}
public void setClassificacao(double classificacao) {
	this.classificacao = classificacao;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public double getSaldoInicial() {
	return saldoInicial;
}
public void setSaldoInicial(double saldoInicial) {
	this.saldoInicial = saldoInicial;
}
public double getDebitos() {
	return debitos;
}
public void setDebitos(double debitos) {
	this.debitos = debitos;
}
public double getCreditos() {
	return creditos;
}
public void setCreditos(double creditos) {
	this.creditos = creditos;
}
public double getSaldoAtual() {
	return saldoAtual;
}
public void setSaldoAtual(double saldoAtual) {
	this.saldoAtual = saldoAtual;
}

public Date getDataBase1() {
	return dataBase1;
}

public void setDataBase1(Date dataBase1) {
	this.dataBase1 = dataBase1;
}

public Date getDataBalanco() {
	return dataBalanco;
}

public void setDataBalanco(Date dataBalanco) {
	this.dataBalanco = dataBalanco;
}






	

}
