package controller;

import java.text.NumberFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;
import model.util.ConversorData;

public class ControllerBalanceteFolhaMestra {
	
	private JLabel lblEmpresaBalancete;
	private JLabel lblDataBaseBalancete;
	private JLabel lblAuditorBalancete;
	private JLabel lblPeriodoBalancete;
	private JTable tableBalanceteLan;
	private NumberFormat decimalFormat = NumberFormat.getCurrencyInstance();
	private Usuario usuarioExecutor;
	private Empresa empresa;
	private Balancete balancete;
	
	private List<Lancamento> lancamentos;
	
	
	public ControllerBalanceteFolhaMestra(JLabel lblEmpresaBalancete, JLabel lblDataBaseBalancete,
			JLabel lblAuditorBalancete, JLabel lblPeriodoBalancete,
			JTable tableBalanceteLan) {
		super();
		this.lblEmpresaBalancete = lblEmpresaBalancete;
		this.lblDataBaseBalancete = lblDataBaseBalancete;
		this.lblAuditorBalancete = lblAuditorBalancete;
		this.lblPeriodoBalancete = lblPeriodoBalancete;
		this.tableBalanceteLan = tableBalanceteLan;
	}

	public void popularCamposBalancete(Balancete pBalancete, Empresa pEmpresa,Usuario pUsuarioExecutor) {
		this.balancete = pBalancete;
		this.empresa = pEmpresa;
		this.usuarioExecutor = pUsuarioExecutor;
		atualizarJTableAction();
		lblEmpresaBalancete.setText(empresa.getNome());
		lblDataBaseBalancete.setText(String.valueOf(balancete.getDatabase()));
		lblAuditorBalancete.setText(usuarioExecutor.getNome());
		lblPeriodoBalancete.setText(ConversorData.dataBancoParaUsuario(balancete.getDataBalancoInicio())+" - "+ConversorData.dataBancoParaUsuario(balancete.getDataBalancoFim()));
		
	}
	private void atualizarJTableAction() {
		lancamentos = balancete.getLancamentos();
		DefaultTableModel model = new DefaultTableModel();
		 model  = (DefaultTableModel) tableBalanceteLan.getModel();
		//limpa a table e lista novamente
		model.setNumRows(0);
		for(Lancamento pLancamento: lancamentos) {
			model.addRow(new Object[] {
					pLancamento.getRefpt(),
					pLancamento.getNaturezaConta(),
					pLancamento.getNivel(),
					pLancamento.getCodigoEstruturado(),
					pLancamento.getCodigoReduzido(),
					pLancamento.getDescricao(),
					decimalFormat.format(pLancamento.getSaldoInicial()),
					decimalFormat.format(pLancamento.getDebito()),
					decimalFormat.format(pLancamento.getCredito()),
					decimalFormat.format(pLancamento.getSaldoFinal())
			});
		}
	}

	public JLabel getLblEmpresaBalancete() {
		return lblEmpresaBalancete;
	}

	public void setLblEmpresaBalancete(JLabel lblEmpresaBalancete) {
		this.lblEmpresaBalancete = lblEmpresaBalancete;
	}

	public JLabel getLblDataBaseBalancete() {
		return lblDataBaseBalancete;
	}

	public void setLblDataBaseBalancete(JLabel lblDataBaseBalancete) {
		this.lblDataBaseBalancete = lblDataBaseBalancete;
	}

	public JLabel getLblAuditorBalancete() {
		return lblAuditorBalancete;
	}

	public void setLblAuditorBalancete(JLabel lblAuditorBalancete) {
		this.lblAuditorBalancete = lblAuditorBalancete;
	}

	public JLabel getLblPeriodoBalancete() {
		return lblPeriodoBalancete;
	}

	public void setLblPeriodoBalancete(JLabel lblPeriodoBalancete) {
		this.lblPeriodoBalancete = lblPeriodoBalancete;
	}

	public JTable getTableBalanceteLan() {
		return tableBalanceteLan;
	}

	public void setTableBalanceteLan(JTable tableBalanceteLan) {
		this.tableBalanceteLan = tableBalanceteLan;
	}

	public Usuario getUsuarioExecutor() {
		return usuarioExecutor;
	}

	public void setUsuarioExecutor(Usuario usuarioExecutor) {
		this.usuarioExecutor = usuarioExecutor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Balancete getBalancete() {
		return balancete;
	}

	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}
	
}
