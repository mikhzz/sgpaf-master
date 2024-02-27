package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;

import dao.ChecagemDaoImpl;
import dao.UsuarioProcedimentoDaoImpl;
import model.boNovo.FolhamestraBO;
import model.boNovo.ProcedimentoBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Checagem;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Procedimento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioProcedimento;

public class ControllerProced {

	private JLabel lblUsuarioUltimaAlteracaoFolhaRef;
	private JCheckBox chbNaoInspecaoDocumental;
	private JCheckBox chbNaoContagemFisica;
	private JCheckBox chbNaoAcessoOnlie;
	private JCheckBox chbNaoRecalculo;
	private JCheckBox chbNaoEntrevistas;
	private JCheckBox chbNaoAplicacaoQuestionario;
	private JCheckBox chbNaoCruzamentoSaldos;
	private JCheckBox chbCircularizacao;
	private JCheckBox chbValidaoAmostra;
	private JCheckBox chbLiquidacaoSubsequente;
	private JTextArea txtAreaConclusaoProc;
	private JComboBox cbREFProc;
	private Procedimento procedimento;
	private String ref;
	private Balancete balancete;
	private UsuarioProcedimento usuarioProcedimentoExecutor;
	private UsuarioProcedimento usuarioProcedimentoUltimo;
	private Usuario usuarioExecutor;
	private static final ProcedimentoBO procedimentoBO = new ProcedimentoBO();
	private static final UsuarioProcedimentoDaoImpl usuarioProcedimentoDAO = new UsuarioProcedimentoDaoImpl();
	private static final ChecagemDaoImpl checkDAO = new ChecagemDaoImpl();
	private FolhaMestra folhamestra;
	private static final FolhamestraBO folhaBO = new FolhamestraBO();

	public ControllerProced(JCheckBox chbNaoInspecaoDocumental, JCheckBox chbNaoContagemFisica,
			JCheckBox chbNaoAcessoOnlie, JCheckBox chbNaoRecalculo, JCheckBox chbNaoEntrevistas,
			JCheckBox chbNaoAplicacaoQuestionario, JCheckBox chbNaoCruzamentoSaldos, JCheckBox chbCircularizacao,
			JCheckBox chbValidaoAmostra, JCheckBox chbLiquidacaoSubsequente, JTextArea txtAreaConclusaoProc,
			JComboBox cbREFProc, JLabel lblUsuarioUltimaAlteracaoFolhaRef) {
		super();
		this.chbNaoInspecaoDocumental = chbNaoInspecaoDocumental;
		this.chbNaoContagemFisica = chbNaoContagemFisica;
		this.chbNaoAcessoOnlie = chbNaoAcessoOnlie;
		this.chbNaoRecalculo = chbNaoRecalculo;
		this.chbNaoEntrevistas = chbNaoEntrevistas;
		this.chbNaoAplicacaoQuestionario = chbNaoAplicacaoQuestionario;
		this.chbNaoCruzamentoSaldos = chbNaoCruzamentoSaldos;
		this.chbCircularizacao = chbCircularizacao;
		this.chbValidaoAmostra = chbValidaoAmostra;
		this.chbLiquidacaoSubsequente = chbLiquidacaoSubsequente;
		this.txtAreaConclusaoProc = txtAreaConclusaoProc;
		this.cbREFProc = cbREFProc;
		this.lblUsuarioUltimaAlteracaoFolhaRef = lblUsuarioUltimaAlteracaoFolhaRef;
	}

	public void popularCampos() {
		if (cbREFProc.getSelectedItem() != null) {
			ref = cbREFProc.getSelectedItem().toString();
		} else {
			ref = "";
		}

		procedimento = procedimentoBO.consultarProcedimentos(balancete, ref);

		if (procedimento != null) {
			List<UsuarioProcedimento> usuariosProcedimento = usuarioProcedimentoDAO.pesquisarPor("descricao",
					"EXECUTOR", procedimento);
			LongSummaryStatistics summary = usuariosProcedimento.stream()
					.collect(Collectors.summarizingLong(UsuarioProcedimento::getIdUsuarioProcedimento));
			Long maiorID = summary.getMax();
			for (UsuarioProcedimento usuarioProcedimento : usuariosProcedimento) {
				if (usuarioProcedimento.getIdUsuarioProcedimento().equals(maiorID) ) {
					ControllerFolha folhaControl = new ControllerFolha();
					lblUsuarioUltimaAlteracaoFolhaRef.setText(usuarioProcedimento.getUsuario().getNome());
				}
			}

			txtAreaConclusaoProc.setText(procedimento.getConclusoes());

			chbNaoInspecaoDocumental.setSelected(pegarCheckPorDescricao("INSPEÇÃO DOCUMENTAL"));
			eventoCheckBox(chbNaoInspecaoDocumental);

			chbNaoContagemFisica.setSelected(pegarCheckPorDescricao("CONTAGEM FÍSICA"));
			eventoCheckBox(chbNaoContagemFisica);

			chbCircularizacao.setSelected(pegarCheckPorDescricao("CIRCULARIZAÇÃO"));
			eventoCheckBox(chbCircularizacao);

			chbNaoAcessoOnlie.setSelected(pegarCheckPorDescricao("ACESSO ONLINE"));
			eventoCheckBox(chbNaoAcessoOnlie);

			chbNaoRecalculo.setSelected(pegarCheckPorDescricao("RECÁLCULO"));
			eventoCheckBox(chbNaoRecalculo);

			chbNaoEntrevistas.setSelected(pegarCheckPorDescricao("ENTREVISTAS"));
			eventoCheckBox(chbNaoEntrevistas);

			chbNaoAplicacaoQuestionario
					.setSelected(pegarCheckPorDescricao("APLICAÇÃO DE QUESTIONÁRIOS DE CONTROLES INTERNOS"));
			eventoCheckBox(chbNaoAplicacaoQuestionario);

			chbNaoCruzamentoSaldos.setSelected(pegarCheckPorDescricao("CRUZAMENTO DE SALDOS DE RELÁTÓRIOS"));
			eventoCheckBox(chbNaoCruzamentoSaldos);

			chbValidaoAmostra.setSelected(pegarCheckPorDescricao("VALIDAÇÃO DA AMOSTRA"));
			eventoCheckBox(chbValidaoAmostra);

			chbLiquidacaoSubsequente.setSelected(pegarCheckPorDescricao("LIQUIDAÇÃO SUBSEQUENTE"));
			eventoCheckBox(chbLiquidacaoSubsequente);

			
		} else {
			limparCamposProcedimento();
		}
	}

	public void eventoCheckBox(JCheckBox chkBox) {
		if (chkBox.isSelected()) {
			chkBox.setText("Sim");
		} else {
			chkBox.setText("Não");
		}
	}

	private Boolean pegarCheckPorDescricao(String pDescricao) {
		Checagem chk = new Checagem();
		chk.setSelecionado(false);
		if (procedimento.getChecagensLista().size() > 0) {
			for (Checagem checagem : procedimento.getChecagensLista()) {
				if (checagem.getDescricao().equals(pDescricao)) {
					chk = checagem;
				}
			}

		} else {
			return false;
		}
		return chk.isSelecionado();
	}

	public void btnSalvarAction() {
		
		if (procedimento == null) {
			procedimento = new Procedimento();
			procedimento.setRefpt(ref);
			procedimento.setBalancete(balancete);
			procedimento.setDataUltimaAlteracao(new Date());
			procedimento.setConclusoes(txtAreaConclusaoProc.getText());
			procedimentoBO.salvarOuAlterarProcedimento(procedimento);
		}else {
			procedimento.setDataUltimaAlteracao(new Date());
			procedimento.setConclusoes(txtAreaConclusaoProc.getText());
			procedimentoBO.salvarOuAlterarProcedimento(procedimento);
		}
		salvarUsuarioProcedimentoExecutor();
	}
	

	public void salvarCheckAction(String descricao, JCheckBox pCchk) {
		boolean cofirm = true;

		if (procedimento == null) {
			procedimento = new Procedimento();
			procedimento.setRefpt(ref);
			procedimento.setBalancete(balancete);
			procedimento.setDataUltimaAlteracao(new Date());
			procedimento.setConclusoes("");
			procedimentoBO.salvarOuAlterarProcedimento(procedimento);

			Checagem chk = new Checagem(descricao);
			chk.setDataCheck(new Date());
			chk.setProcedimento(procedimento);
			chk.setSelecionado(pCchk.isSelected());
			checkDAO.salvarOuAlterar(chk);

		} else {
			procedimento = procedimentoBO.consultarProcedimentos(balancete, ref);
			for (Checagem check : procedimento.getChecagensLista()) {
				if (check.getDescricao().equals(descricao)) {
					check.setDataCheck(new Date());
					check.setSelecionado(pCchk.isSelected());
					checkDAO.salvarOuAlterar(check);
					cofirm = false;
				}
			}
			if (cofirm) {
				Checagem chk = new Checagem(descricao);
				chk.setDataCheck(new Date());
				chk.setProcedimento(procedimento);
				chk.setSelecionado(pCchk.isSelected());
				checkDAO.salvarOuAlterar(chk);
			}
			procedimento.setDataUltimaAlteracao(new Date());
			if (!procedimentoBO.salvarOuAlterarProcedimento(procedimento)) {
				JOptionPane.showMessageDialog(null, "Ops! Erro ao salvar Procedimento!");
			}

		}
		salvarUsuarioProcedimentoExecutor();
	}

	private void salvarUsuarioProcedimentoExecutor() {

		usuarioProcedimentoExecutor = new UsuarioProcedimento();
		usuarioProcedimentoExecutor.setDataVinculo(new Date());
		usuarioProcedimentoExecutor.setUsuario(usuarioExecutor);
		usuarioProcedimentoExecutor.setDescricao("EXECUTOR");
		usuarioProcedimentoExecutor.setProcedimento(procedimento);
		usuarioProcedimentoDAO.salvarOuAlterar(usuarioProcedimentoExecutor);

	}

	private void limparCamposProcedimento() {


		chbNaoInspecaoDocumental.setSelected(false);
		eventoCheckBox(chbNaoInspecaoDocumental);

		chbNaoContagemFisica.setSelected(false);
		eventoCheckBox(chbNaoContagemFisica);

		chbCircularizacao.setSelected(false);
		eventoCheckBox(chbCircularizacao);

		chbNaoAcessoOnlie.setSelected(false);
		eventoCheckBox(chbNaoAcessoOnlie);

		chbNaoRecalculo.setSelected(false);
		eventoCheckBox(chbNaoRecalculo);

		chbNaoEntrevistas.setSelected(false);
		eventoCheckBox(chbNaoEntrevistas);

		chbNaoAplicacaoQuestionario.setSelected(false);
		eventoCheckBox(chbNaoAplicacaoQuestionario);

		chbNaoCruzamentoSaldos.setSelected(false);
		eventoCheckBox(chbNaoCruzamentoSaldos);

		chbValidaoAmostra.setSelected(false);
		eventoCheckBox(chbValidaoAmostra);

		chbLiquidacaoSubsequente.setSelected(false);
		eventoCheckBox(chbLiquidacaoSubsequente);

		txtAreaConclusaoProc.setText("");
	}

	private int mensagemSucesso() {
		return JOptionPane.showConfirmDialog(null, "Salvei com Sucesso!\nDeja Continuar?", "Sucesso!",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public JCheckBox getChbNaoInspecaoDocumental() {
		return chbNaoInspecaoDocumental;
	}

	public void setChbNaoInspecaoDocumental(JCheckBox chbNaoInspecaoDocumental) {
		this.chbNaoInspecaoDocumental = chbNaoInspecaoDocumental;
	}

	public JCheckBox getChbNaoContagemFisica() {
		return chbNaoContagemFisica;
	}

	public void setChbNaoContagemFisica(JCheckBox chbNaoContagemFisica) {
		this.chbNaoContagemFisica = chbNaoContagemFisica;
	}

	public JCheckBox getChbNaoAcessoOnlie() {
		return chbNaoAcessoOnlie;
	}

	public void setChbNaoAcessoOnlie(JCheckBox chbNaoAcessoOnlie) {
		this.chbNaoAcessoOnlie = chbNaoAcessoOnlie;
	}

	public JCheckBox getChbNaoRecalculo() {
		return chbNaoRecalculo;
	}

	public void setChbNaoRecalculo(JCheckBox chbNaoRecalculo) {
		this.chbNaoRecalculo = chbNaoRecalculo;
	}

	public JCheckBox getChbNaoEntrevistas() {
		return chbNaoEntrevistas;
	}

	public void setChbNaoEntrevistas(JCheckBox chbNaoEntrevistas) {
		this.chbNaoEntrevistas = chbNaoEntrevistas;
	}

	public JCheckBox getChbNaoAplicacaoQuestionario() {
		return chbNaoAplicacaoQuestionario;
	}

	public void setChbNaoAplicacaoQuestionario(JCheckBox chbNaoAplicacaoQuestionario) {
		this.chbNaoAplicacaoQuestionario = chbNaoAplicacaoQuestionario;
	}

	public JCheckBox getChbNaoCruzamentoSaldos() {
		return chbNaoCruzamentoSaldos;
	}

	public void setChbNaoCruzamentoSaldos(JCheckBox chbNaoCruzamentoSaldos) {
		this.chbNaoCruzamentoSaldos = chbNaoCruzamentoSaldos;
	}

	public JCheckBox getChbCircularizacao() {
		return chbCircularizacao;
	}

	public void setChbCircularizacao(JCheckBox chbCircularizacao) {
		this.chbCircularizacao = chbCircularizacao;
	}

	public JCheckBox getChbValidaoAmostra() {
		return chbValidaoAmostra;
	}

	public void setChbValidaoAmostra(JCheckBox chbValidaoAmostra) {
		this.chbValidaoAmostra = chbValidaoAmostra;
	}

	public JCheckBox getChbLiquidacaoSubsequente() {
		return chbLiquidacaoSubsequente;
	}

	public void setChbLiquidacaoSubsequente(JCheckBox chbLiquidacaoSubsequente) {
		this.chbLiquidacaoSubsequente = chbLiquidacaoSubsequente;
	}

	public JTextArea gettxtAreaConclusaoProc() {
		return txtAreaConclusaoProc;
	}

	public void settxtAreaConclusaoProc(JTextArea txtAreaConclusaoProc) {
		this.txtAreaConclusaoProc = txtAreaConclusaoProc;
	}

	public FolhaMestra getFolhamestra() {
		return folhamestra;
	}

	public void setFolhamestra(FolhaMestra folhamestra) {
		this.folhamestra = folhamestra;
	}

	public static FolhamestraBO getFolhabo() {
		return folhaBO;
	}

	public JComboBox getCbREFProc() {
		return cbREFProc;
	}

	public void setCbREFProc(JComboBox cbREFProc) {
		this.cbREFProc = cbREFProc;
	}

	public Balancete getBalancete() {
		return balancete;
	}

	public void setBalancete(Balancete balancete) {
		this.balancete = balancete;
	}

	public Usuario getUsuarioExecutor() {
		return usuarioExecutor;
	}

	public void setUsuarioExecutor(Usuario usuarioExecutor) {
		this.usuarioExecutor = usuarioExecutor;
	}

}
