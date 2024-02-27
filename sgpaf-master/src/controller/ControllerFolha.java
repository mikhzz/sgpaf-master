package controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.LancamentoDaoImpl;
import dao.UsuarioProcedimentoDaoImpl;
import model.boNovo.ProcedimentoBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioProcedimento;
import model.util.ConversorData;

public class ControllerFolha {
	private JTable table;
	private JLabel lblEmpresa;
	private JLabel lblDataBase;
	private JLabel lblPeriodo;
	private JLabel lblExecutor;
	private JComboBox cbREFFolha;
	private NumberFormat decimalFormat = NumberFormat.getCurrencyInstance();
	private Usuario usuarioExecutor;
	private Empresa empresa;
	private Balancete balancete;
	private String ref;
	private JLabel lblSaldoAnteriorTotal;
	private JLabel lblDebitoTotal;
	private JLabel lblSaldoValidadoTotal;
	private JLabel lblSaldoFinalTotal;
	private JLabel lblCreditoTotal;
	private JLabel lblDiferencaTotal;
	private JLabel lblUsuarioUltimaAlteracao;
	private Procedimento procedimento;
	private static final ProcedimentoBO procedimentoBO = new ProcedimentoBO();
	private static final UsuarioProcedimentoDaoImpl usuarioProcedimentoDAO = new UsuarioProcedimentoDaoImpl();
	
	private List<Lancamento> lancamentos;
	private static final LancamentoDaoImpl lancamentoDAO= new LancamentoDaoImpl();
	public ControllerFolha(JTable table, JLabel lblEmpresa, JLabel lblDataBase, JLabel lblPeriodo, JLabel lblExecutor,
			JComboBox cbREFFolha, JLabel lblSaldoAnteriorTotal, JLabel lblDebitoTotal, JLabel lblSaldoValidadoTotal,
			JLabel lblSaldoFinalTotal, JLabel lblCreditoTotal, JLabel lblDiferencaTotal,JLabel lblUsuarioUltimaAlteracao) {
		super();
		this.table = table;
		this.lblEmpresa = lblEmpresa;
		this.lblDataBase = lblDataBase;
		this.lblPeriodo = lblPeriodo;
		this.lblExecutor = lblExecutor;
		this.cbREFFolha = cbREFFolha;
		this.lblSaldoAnteriorTotal = lblSaldoAnteriorTotal;
		this.lblDebitoTotal = lblDebitoTotal;
		this.lblSaldoValidadoTotal = lblSaldoValidadoTotal;
		this.lblSaldoFinalTotal = lblSaldoFinalTotal;
		this.lblCreditoTotal = lblCreditoTotal;
		this.lblDiferencaTotal = lblDiferencaTotal;
		this.lblUsuarioUltimaAlteracao = lblUsuarioUltimaAlteracao;
	}

	public ControllerFolha() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void popularCamposFolha(Balancete pBalancete, Empresa pEmpresa, Usuario pUsuarioExecutor) {
		this.empresa = pEmpresa;
		this.usuarioExecutor = pUsuarioExecutor;
		this.balancete = pBalancete;
		lblDataBase.setText(String.valueOf(balancete.getDatabase()));
		lblEmpresa.setText(empresa.getNome());
		lblExecutor.setText(usuarioExecutor.getNome());
		lblPeriodo.setText(ConversorData.dataBancoParaUsuario(balancete.getDataBalancoInicio()) + " - "
				+ ConversorData.dataBancoParaUsuario(balancete.getDataBalancoFim()));
	}

	public void pesquisaRef() {
		lblUsuarioUltimaAlteracao.setText("");
		if(cbREFFolha.getSelectedItem()!=null) {
			ref = cbREFFolha.getSelectedItem().toString();
		}else {
			ref="";
		}
		System.out.println("=====================================================ref vindo do combo:" + ref);
		if(!ref.equals("")) {
			pegarUltimoUsuarioExecutor();
			lancamentos =balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals(ref))
					.collect(Collectors.toList());
			atualizarJTableAction(ref,balancete);
			calcularValoresTotais(lancamentos);
			
		}
		
	}
	private void pegarUltimoUsuarioExecutor() {
		procedimento = procedimentoBO.consultarProcedimentos(balancete, ref);
		List<UsuarioProcedimento> usuariosProcedimento = usuarioProcedimentoDAO.pesquisarPor("descricao",
				"EXECUTOR", procedimento);
		LongSummaryStatistics summary = usuariosProcedimento.stream()
				.collect(Collectors.summarizingLong(UsuarioProcedimento::getIdUsuarioProcedimento));
		Long maiorID = summary.getMax();
		for (UsuarioProcedimento usuarioProcedimento : usuariosProcedimento) {
			if (usuarioProcedimento.getIdUsuarioProcedimento().equals(maiorID) ) {
				ControllerFolha folhaControl = new ControllerFolha();
				lblUsuarioUltimaAlteracao.setText(usuarioProcedimento.getUsuario().getNome());
			}
		}
	}
	private void atualizarJTableAction(String pRef, Balancete pBalancete) {
		List<Lancamento> lancamentosPorRef = lancamentoDAO.pesquisarPorRef(pRef, pBalancete);
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) table.getModel();
		// limpa a table e lista novamente
		String divergencia;
		model.setNumRows(0);
		for (Lancamento pLancamento : lancamentosPorRef) {
			if(pLancamento.isDivergencia()) {
				divergencia = "SIM";
			}else if(!pLancamento.isDivergencia() &&pLancamento.getRecomendacao()==null ) {
				divergencia= "";
			}else {
				divergencia= "NÃO";
			}
			
			model.addRow(new Object[] { pLancamento.getRefpt(), pLancamento.getNaturezaConta(), pLancamento.getNivel(),
					pLancamento.getCodigoEstruturado(), pLancamento.getCodigoReduzido(), pLancamento.getDescricao(),
					decimalFormat.format(pLancamento.getSaldoInicial()), decimalFormat.format(pLancamento.getDebito()),
					decimalFormat.format(pLancamento.getCredito()), decimalFormat.format(pLancamento.getSaldoFinal()),
					pLancamento.getaH().setScale(0) + " %", pLancamento.getaV().setScale(0) + " %",
					decimalFormat.format(pLancamento.getSaldoValidado()),
					decimalFormat.format(pLancamento.getDiferenca()),divergencia });
		}
	}
	public Lancamento getLancamentoSelecionadoTable() {
		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		if (linha >= 0) {
			return lancamentos.get(linha);
		} else {
			System.out
					.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela GerenciamentoEmpresa");
			return null;
		}
	}


	private void calcularValoresTotais(List<Lancamento> pLancamentos) {

		BigDecimal totalFinal = pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref) && p.getNivel().equals(6)).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalCredito = pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref)).map((Lancamento p) -> p.getCredito()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalDebito = pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref)).map((Lancamento p) -> p.getDebito()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalAnterior =  pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref)).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalValidado = pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref)).map((Lancamento p) -> p.getSaldoValidado()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalDiferença = pLancamentos.stream().filter((Lancamento p) -> p.getRefpt().equals(ref)).map((Lancamento p) -> p.getDiferenca()).reduce(BigDecimal.ZERO,BigDecimal::add);

		lblSaldoAnteriorTotal.setText(decimalFormat.format(totalAnterior));
		lblDebitoTotal.setText(decimalFormat.format(totalDebito));
		lblCreditoTotal.setText(decimalFormat.format(totalCredito));
		lblSaldoFinalTotal.setText(decimalFormat.format(totalFinal));
		lblSaldoValidadoTotal.setText(decimalFormat.format(totalValidado));
		lblDiferencaTotal.setText(decimalFormat.format(totalDiferença));
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JLabel getLblEmpresa() {
		return lblEmpresa;
	}

	public void setLblEmpresa(JLabel lblEmpresa) {
		this.lblEmpresa = lblEmpresa;
	}

	public JLabel getLblDataBase() {
		return lblDataBase;
	}

	public void setLblDataBase(JLabel lblDataBase) {
		this.lblDataBase = lblDataBase;
	}

	public JLabel getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(JLabel lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public JLabel getLblExecutor() {
		return lblExecutor;
	}

	public void setLblExecutor(JLabel lblExecutor) {
		this.lblExecutor = lblExecutor;
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

	public JComboBox getCbREFFolha() {
		return cbREFFolha;
	}

	public void setCbREFFolha(JComboBox cbREFFolha) {
		this.cbREFFolha = cbREFFolha;
	}

	public JLabel getLblSaldoAnteriorTotal() {
		return lblSaldoAnteriorTotal;
	}

	public void setLblSaldoAnteriorTotal(JLabel lblSaldoAnteriorTotal) {
		this.lblSaldoAnteriorTotal = lblSaldoAnteriorTotal;
	}

	public JLabel getLblDebitoTotal() {
		return lblDebitoTotal;
	}

	public void setLblDebitoTotal(JLabel lblDebitoTotal) {
		this.lblDebitoTotal = lblDebitoTotal;
	}

	public JLabel getLblSaldoValidadoTotal() {
		return lblSaldoValidadoTotal;
	}

	public void setLblSaldoValidadoTotal(JLabel lblSaldoValidadoTotal) {
		this.lblSaldoValidadoTotal = lblSaldoValidadoTotal;
	}

	public JLabel getLblSaldoFinalTotal() {
		return lblSaldoFinalTotal;
	}

	public void setLblSaldoFinalTotal(JLabel lblSaldoFinalTotal) {
		this.lblSaldoFinalTotal = lblSaldoFinalTotal;
	}

	public JLabel getLblCreditoTotal() {
		return lblCreditoTotal;
	}

	public void setLblCreditoTotal(JLabel lblCreditoTotal) {
		this.lblCreditoTotal = lblCreditoTotal;
	}

}
