package controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.daoNovo.db.Balancete;
import model.daoNovo.db.Lancamento;

public class ControllerMaterialidade {

	private Balancete balancete;

	private JTable tableAtivoCirculante;
	private JLabel lblTotalAtivoCirculante;

	private JTable tableAtivoNaoCirculante;
	private JLabel lblTotalATivoNaoCirculante;

	private JTable tablePassivoCirculante;
	private JLabel lblTotalPassivoCirculante;

	private JTable tablePassivoNaoCirculante;
	private JLabel lblTotalPassivoNaoCirculante;

	private JTable tablePatrimonioLiquido;
	private JLabel lblTotalPatrimonioLiquido;

	private JTable tableResultadoPeriodo;
	private JLabel lblResultadoPeriodo;
	
	private List<Lancamento> ativosCirculantesList;
	private List<Lancamento> ativosNaoCirculantesList;
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	
	private List<Lancamento> listPassivoCirculante;

	private List<Lancamento> lancamentos;

	public ControllerMaterialidade(Balancete balancete, JTable tableAtivoCirculante, JLabel lblTotalAtivoCirculante,
			JTable tableAtivoNaoCirculante, JLabel lblTotalATivoNaoCirculante, JTable tablePassivoCirculante,
			JLabel lblTotalPassivoCirculante, JTable tablePassivoNaoCirculante, JLabel lblTotalPassivoNaoCirculante,
			JTable tablePatrimonioLiquido, JLabel lblTotalPatrimonioLiquido, JTable tableResultadoPeriodo,
			JLabel lblResultadoPeriodo) {
		super();
		this.balancete = balancete;
		this.tableAtivoCirculante = tableAtivoCirculante;
		this.lblTotalAtivoCirculante = lblTotalAtivoCirculante;
		this.tableAtivoNaoCirculante = tableAtivoNaoCirculante;
		this.lblTotalATivoNaoCirculante = lblTotalATivoNaoCirculante;
		this.tablePassivoCirculante = tablePassivoCirculante;
		this.lblTotalPassivoCirculante = lblTotalPassivoCirculante;
		this.tablePassivoNaoCirculante = tablePassivoNaoCirculante;
		this.lblTotalPassivoNaoCirculante = lblTotalPassivoNaoCirculante;
		this.tablePatrimonioLiquido = tablePatrimonioLiquido;
		this.lblTotalPatrimonioLiquido = lblTotalPatrimonioLiquido;
		this.tableResultadoPeriodo = tableResultadoPeriodo;
		this.lblResultadoPeriodo = lblResultadoPeriodo;
		this.listPassivoCirculante = new ArrayList<Lancamento>();
	}

	public void popularTelaMaterialidade() {
		lancamentos = balancete.getLancamentos();

		ativosCirculantesList = lancamentos.stream()
				.filter((Lancamento p) -> p.getRefpt().equals("AA") && p.getNivel().equals(6) || p.getRefpt().equals("AB") && p.getNivel().equals(6)
						|| p.getRefpt().equals("AC") && p.getNivel().equals(6) || p.getRefpt().equals("AD") && p.getNivel().equals(6)
						 || p.getRefpt().equals("AE")  && p.getNivel().equals(6) || p.getRefpt().equals("AF") && p.getNivel().equals(6) ||
						 p.getRefpt().equals("AG") && p.getNivel().equals(6)
						|| p.getRefpt().equals("AH") && p.getNivel().equals(6)).collect(Collectors.toList());
		ativosNaoCirculantesList = lancamentos.stream()
				.filter((Lancamento p) -> p.getRefpt().equals("AM") && p.getNivel().equals(6)|| p.getRefpt().equals("AI") && p.getNivel().equals(6)
						|| p.getRefpt().equals("AL")&& p.getNivel().equals(6)|| p.getRefpt().equals("AJ")&& p.getNivel().equals(6)).collect(Collectors.toList());
		
		
		BigDecimal totalAtivoCirculante = ativosCirculantesList.stream().map((Lancamento p) -> p.getSaldoValidado()).reduce(BigDecimal.ZERO,BigDecimal::add);
		BigDecimal totalAtivoNaoCirculante = ativosNaoCirculantesList.stream().map((Lancamento p) -> p.getSaldoValidado()).reduce(BigDecimal.ZERO,BigDecimal::add);
		
		atualizarJTableAction(ativosCirculantesList,tableAtivoCirculante);
		atualizarJTableAction(ativosNaoCirculantesList,tableAtivoNaoCirculante);
		lblTotalAtivoCirculante.setText(totalAtivoCirculante.toString());
		lblTotalATivoNaoCirculante.setText(totalAtivoNaoCirculante.toString());
//		List <Lancamento> emprestimos = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PA"))
//				.collect(Collectors.toList());
//		List <Lancamento> obrigacoesSociais = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PC"))
//				.collect(Collectors.toList());
//		List <Lancamento> obrigacoesTributarias = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PD"))
//				.collect(Collectors.toList());
//		BigDecimal fornecedores =  balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PB"))
//				.map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);

//		BigDecimal emprestimos = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PA")).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
//		BigDecimal obrigacoesSociais = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PC")).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
//		BigDecimal obrigacoesTributarias = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PD")).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
//		BigDecimal outrasObrigacoesPagar = balancete.getLancamentos().stream().filter((Lancamento p) -> p.getRefpt().equals("PF")).map((Lancamento p) -> p.getSaldoFinal()).reduce(BigDecimal.ZERO,BigDecimal::add);
//		atualizarTablePassivoCirculante();

	}
	public void atualizarJTableAction(List<Lancamento> pListLancamento,JTable pJTable) {
		lancamentos = pListLancamento;
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) pJTable.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Lancamento pLancamento : lancamentos) {
			boolean isDivervengia = pLancamento.isDivergencia();
			String strDivergencia ;
			if(isDivervengia) {
				strDivergencia = "HÁ DIVERGENCIA";
			}else {
				strDivergencia="NÃO HÁ DIVERGENCIA";
			}
			model.addRow(new Object[] { pLancamento.getRefpt(), pLancamento.getDescricao(),
					decimalFormat.format(pLancamento.getSaldoValidado()),strDivergencia,pLancamento.getRecomendacao()
					});

		}
	}
	public Lancamento lancamentoSelecionadoTable(List<Lancamento> pLancamentos,JTable pJTable) {
		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) pJTable.getModel();
		int linha = pJTable.getSelectedRow();
		if (linha >= 0) {
			return pLancamentos.get(linha);
		} else {
			System.out
					.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela GerenciamentoEmpresa");
			return null;
		}
	}
//	private void atualizarTablePassivoCirculante() {
//		DefaultTableModel model = new DefaultTableModel();
//
//		model = (DefaultTableModel) table.getModel();
//		// limpa a table e lista novamente
//		model.setNumRows(0);
//		for (Lancamento pLancamento : lancamentos) {
//			model.addRow(new Object[] {});
//		}
//	}

	public List<Lancamento> getAtivosCirculantesList() {
		return ativosCirculantesList;
	}

	

	public List<Lancamento> getAtivosNaoCirculantesList() {
		return ativosNaoCirculantesList;
	}
}
