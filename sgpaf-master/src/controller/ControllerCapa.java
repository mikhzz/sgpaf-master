package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.UsuarioFolhaMestraDaoImpl;
import model.boNovo.FolhamestraBO;
import model.boNovo.UsuarioBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Checagem;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioFolhaMestra;
import view.TelaFolhaMestra1;

public class ControllerCapa {

	private JLabel lblClienteCapa;
	private JLabel lblDataBaseCapa;
	private JLabel lblRecebeDataUltimaAltera;
	private JLabel lblDataRevisado;
	private JComboBox cbSocioDiretor;
	private JComboBox cbRevisor;
	private JLabel lblAuditorAssistenteCapa;
	private JLabel lblUlitimaAlteracaoAuditor;
	private JCheckBox checkBoxRevisadoCapa;
	private JTextArea textAreaConclusaoCapa;
	private Balancete balanceteCapa;
	private FolhaMestra folhaMestraCapa;
//	private Usuario usuarioAuditor;
	private Usuario usuarioSocioDiretor;
	private Usuario usuarioExecutorLogado;
	private Usuario usuarioExecutorUltimo;
	private Usuario usuarioRevisor;
	private UsuarioFolhaMestra usuarioFolhaRevisor;
	private UsuarioFolhaMestra usuarioFolhaExecutor;
	private UsuarioFolhaMestra usuarioFolhaSocio;
	private UsuarioFolhaMestra usuarioFolhaAuditor;
	private Empresa empresa;
	private List<UsuarioFolhaMestra> usuariosFolhaMestra;
	private TelaFolhaMestra1 telaFolhaMestra = null;

	private static final UsuarioBO usuarioBO = new UsuarioBO();
	private static final FolhamestraBO folhaMestraBO = new FolhamestraBO();
	private static final UsuarioFolhaMestraDaoImpl usuFolhaDAO = new UsuarioFolhaMestraDaoImpl();
//	private static final Table tableUsuario = usuarioBO.getTableUsuario();

	public ControllerCapa(JLabel lblClienteCapa, JLabel lblDataBaseCapa, JComboBox cbSocioDiretor,
			JLabel lblAuditorAssistenteCapa, JComboBox cbRevisor, JCheckBox checkBoxRevisadoCapa,
			JTextArea textAreaConclusaoCapa, JLabel lblUlitimaAlteracaoAuditor, JLabel lblRecebeDataUltimaAltera,
			JLabel lblDataRevisado) {
		super();
		this.lblClienteCapa = lblClienteCapa;
		this.lblDataBaseCapa = lblDataBaseCapa;
		this.lblAuditorAssistenteCapa = lblAuditorAssistenteCapa;
		this.cbRevisor = cbRevisor;
		this.checkBoxRevisadoCapa = checkBoxRevisadoCapa;
		this.textAreaConclusaoCapa = textAreaConclusaoCapa;
		this.cbSocioDiretor = cbSocioDiretor;
		this.lblUlitimaAlteracaoAuditor = lblUlitimaAlteracaoAuditor;
		this.lblRecebeDataUltimaAltera = lblRecebeDataUltimaAltera;
		this.lblDataRevisado = lblDataRevisado;
	}

	public void popularCamposCapa(Balancete pBalancete, Empresa pEmpresa, Usuario pExecutorLogado,
			FolhaMestra pFolhamestra) {

		this.folhaMestraCapa = pFolhamestra;
		this.balanceteCapa = pBalancete;
		this.usuarioExecutorLogado = pExecutorLogado;
		this.empresa = pEmpresa;

//		if (usuarioFolhaExecutor != null) {
		lblAuditorAssistenteCapa.setText(usuarioExecutorLogado.getNome());
//		}
		List<UsuarioFolhaMestra> usuarioFolhaExecutorList = new ArrayList<UsuarioFolhaMestra>();
		usuarioFolhaExecutorList = usuFolhaDAO.pesquisarPor("descricao", "EXECUTOR", folhaMestraCapa);
		LongSummaryStatistics summary = usuarioFolhaExecutorList.stream()
				.collect(Collectors.summarizingLong(UsuarioFolhaMestra::getIdUsuarioFolhaMestra));
		Long maiorID = summary.getMax();
		for (UsuarioFolhaMestra usuarioFolhaMestraCom : usuarioFolhaExecutorList) {
			if (usuarioFolhaMestraCom.getIdUsuarioFolhaMestra() == maiorID) {
				lblUlitimaAlteracaoAuditor.setText(usuarioFolhaMestraCom.getUsuario().getNome());
				lblRecebeDataUltimaAltera.setText(usuarioFolhaMestraCom.getDataVinculo().toString());
			}
		}

		String dataBase = String.valueOf(balanceteCapa.getDatabase());
		String nomeEmpresa = empresa.getNome();
		lblClienteCapa.setText(nomeEmpresa);
		lblDataBaseCapa.setText(dataBase);
		textAreaConclusaoCapa.setText(folhaMestraCapa.getConclusao());
		popularComboBoxAction();
		pegarUsuariosDaLista();
		popularChkBoxRevisado();
	}

	private void popularComboBoxAction() {
		List<Usuario> usuarios;
		List<Usuario> usuariosRevisor = new ArrayList<Usuario>();
		List<Usuario> usuariosDiretor = new ArrayList<Usuario>();
		usuarios = usuarioBO.consultarTodos();
		for (Usuario usuario : usuarios) {
			if (usuario.getPerfil().getIdPerfil() == 1L) {
				usuariosRevisor.add(usuario);
			}
			if (usuario.getCargo().equals("Diretor(a)")) {
				usuariosDiretor.add(usuario);
			}
		}
		ComboBoxModel<Object> modelComboRevisor;
		ComboBoxModel<Object> modelComboDiretor;

		modelComboRevisor = new DefaultComboBoxModel<Object>(usuariosRevisor.toArray());
		modelComboDiretor = new DefaultComboBoxModel<Object>(usuariosDiretor.toArray());

		cbSocioDiretor.setModel(modelComboDiretor);
		cbRevisor.setModel(modelComboRevisor);

	}

	private void pegarUsuariosDaLista() {
		usuariosFolhaMestra = usuFolhaDAO.pesquisarPorFolhaMestra(folhaMestraCapa);
		if (usuariosFolhaMestra.size() > 0) {
			for (UsuarioFolhaMestra usuarioFolhaMestra : usuariosFolhaMestra) {
				switch (usuarioFolhaMestra.getDescricao()) {
				case "REVISOR":
					usuarioFolhaRevisor = usuarioFolhaMestra;
					if (usuarioFolhaRevisor.getUsuario() != null) {
						this.usuarioRevisor = usuarioFolhaRevisor.getUsuario();
						cbRevisor.setSelectedItem(usuarioRevisor);
					}
					break;
				case "SÓCIO DIRETOR":
					usuarioFolhaSocio = usuarioFolhaMestra;
					if (usuarioFolhaSocio.getUsuario() != null) {
						this.usuarioSocioDiretor = usuarioFolhaSocio.getUsuario();
						cbSocioDiretor.setSelectedItem(usuarioSocioDiretor);
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private void popularChkBoxRevisado() {
		if (folhaMestraCapa.getCheckboxRevisado() != null) {
			checkBoxRevisadoCapa.setSelected(folhaMestraCapa.getCheckboxRevisado());
		}
		if (folhaMestraCapa.getDataRevisado() != null) {
			lblDataRevisado.setText(folhaMestraCapa.getDataRevisado().toString());
		}
	}

	protected void salvarAction(FolhaMestra pFolhamestra) {
		this.folhaMestraCapa = pFolhamestra;

		usuarioSocioDiretor = (Usuario) cbSocioDiretor.getSelectedItem();
		usuarioRevisor = (Usuario) cbRevisor.getSelectedItem();

		salvarUsuarioExecutorFolhaMestra();
		salvarUsuarioFolha();
		String conclusao = textAreaConclusaoCapa.getText();
		folhaMestraCapa.setBalancete(balanceteCapa);
		folhaMestraCapa.setConclusao(conclusao);
		folhaMestraBO.salvarOuAlterarFolhamestra(folhaMestraCapa);
	}

	private void salvarUsuarioExecutorFolhaMestra() {
		UsuarioFolhaMestra usuarioExecutorFolha = new UsuarioFolhaMestra();
		usuarioExecutorFolha.setDataVinculo(new Date());
		usuarioExecutorFolha.setDescricao("EXECUTOR");
		usuarioExecutorFolha.setFolhaMestra(folhaMestraCapa);
		usuarioExecutorFolha.setUsuario(usuarioExecutorLogado);
		usuFolhaDAO.salvarOuAlterar(usuarioExecutorFolha);
	}

	private void salvarUsuarioFolha() {
		Usuario usuarioSocioCB;
		Usuario usuarioReviCB;
		boolean usarioSocioConfirma=true;
		boolean usarioRevisorConfirma=true;
		
		List<UsuarioFolhaMestra> usuariosFolhaMestraTodos = usuFolhaDAO.pesquisarPorFolhaMestra(folhaMestraCapa);

		for (UsuarioFolhaMestra usuarioFolhaMestra : usuariosFolhaMestraTodos) {
			switch (usuarioFolhaMestra.getDescricao()) {
			case "SÓCIO DIRETOR":
				usarioSocioConfirma = false;
				usuarioFolhaMestra.setDataVinculo(new Date());
				usuarioSocioCB = (Usuario) cbSocioDiretor.getSelectedItem();
				usuarioFolhaMestra.setUsuario(usuarioSocioCB);
				usuFolhaDAO.salvarOuAlterar(usuarioFolhaMestra);
				
				break;

			case "REVISOR":
				usarioRevisorConfirma = false;
				usuarioFolhaMestra.setDataVinculo(new Date());
				usuarioReviCB = (Usuario) cbRevisor.getSelectedItem();
				usuarioFolhaMestra.setUsuario(usuarioReviCB);
				usuFolhaDAO.salvarOuAlterar(usuarioFolhaMestra);
				break;

			default:
				break;
			}
		}
		if(usarioSocioConfirma) {
			usuarioSocioCB = (Usuario) cbSocioDiretor.getSelectedItem();
			UsuarioFolhaMestra usuarioFoMesSocio = new UsuarioFolhaMestra();
			usuarioFoMesSocio.setDataVinculo(new Date());
			usuarioFoMesSocio.setDescricao("SÓCIO DIRETOR");
			usuarioFoMesSocio.setFolhaMestra(folhaMestraCapa);
			usuarioFoMesSocio.setUsuario(usuarioSocioCB);
			usuFolhaDAO.salvarOuAlterar(usuarioFoMesSocio);
		}
		
		if(usarioRevisorConfirma) {
			usuarioReviCB =(Usuario) cbRevisor.getSelectedItem();
			UsuarioFolhaMestra usuarioFoMesRevi = new UsuarioFolhaMestra();
			usuarioFoMesRevi.setDataVinculo(new Date());
			usuarioFoMesRevi.setDescricao("REVISOR");
			usuarioFoMesRevi.setFolhaMestra(folhaMestraCapa);
			usuarioFoMesRevi.setUsuario(usuarioReviCB);
			usuFolhaDAO.salvarOuAlterar(usuarioFoMesRevi);
		}
		
	}

	public void pegarDataClickRevisadoTela() {

		folhaMestraCapa.setCheckboxRevisado(checkBoxRevisadoCapa.isSelected());
		folhaMestraCapa.setDataRevisado(new Date());
		folhaMestraBO.salvarOuAlterarFolhamestra(folhaMestraCapa);

		if (checkBoxRevisadoCapa.isSelected()) {
			lblDataRevisado.setText(folhaMestraCapa.getDataRevisado().toString());
		} else {
			lblDataRevisado.setText("");
		}
	}

	public Balancete getBalanceteCapa() {
		return balanceteCapa;
	}

	public void setBalanceteCapa(Balancete balanceteCapa) {
		this.balanceteCapa = balanceteCapa;
	}

	public FolhaMestra getFolhaMestraCapa() {
		return folhaMestraCapa;
	}

	public void setFolhaMestraCapa(FolhaMestra folhaMestraCapa) {
		this.folhaMestraCapa = folhaMestraCapa;
	}

	public Usuario getUsuarioSocioDiretor() {
		return usuarioSocioDiretor;
	}

	public void setUsuarioSocioDiretor(Usuario usuarioSocioDiretor) {
		this.usuarioSocioDiretor = usuarioSocioDiretor;
	}

	public Usuario getUsuarioRevisor() {
		return usuarioRevisor;
	}

	public void setUsuarioRevisor(Usuario usuarioRevisor) {
		this.usuarioRevisor = usuarioRevisor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TelaFolhaMestra1 getTelaFolhaMestra() {
		return telaFolhaMestra;
	}

	public void setTelaFolhaMestra(TelaFolhaMestra1 telaFolhaMestra) {
		this.telaFolhaMestra = telaFolhaMestra;
	}

	public ControllerCapa() {
		super();
	}

	public JLabel getLblClienteCapa() {
		return lblClienteCapa;
	}

	public void setLblClienteCapa(JLabel lblClienteCapa) {
		this.lblClienteCapa = lblClienteCapa;
	}

	public JLabel getLblDataBaseCapa() {
		return lblDataBaseCapa;
	}

	public void setLblDataBaseCapa(JLabel lblDataBaseCapa) {
		this.lblDataBaseCapa = lblDataBaseCapa;
	}

	public JLabel getLblAuditorAssistenteCapa() {
		return lblAuditorAssistenteCapa;
	}

	public void setLblAuditorAssistenteCapa(JLabel lblAuditorAssistenteCapa) {
		this.lblAuditorAssistenteCapa = lblAuditorAssistenteCapa;
	}

	public JCheckBox getCheckBoxRevisadoCapa() {
		return checkBoxRevisadoCapa;
	}

	public void setCheckBoxRevisadoCapa(JCheckBox checkBoxRevisadoCapa) {
		this.checkBoxRevisadoCapa = checkBoxRevisadoCapa;
	}

	public JTextArea getTextAreaConclusaoCapa() {
		return textAreaConclusaoCapa;
	}

	public void setTextAreaConclusaoCapa(JTextArea textAreaConclusaoCapa) {
		this.textAreaConclusaoCapa = textAreaConclusaoCapa;
	}

	public JComboBox getCbSocioDiretor() {
		return cbSocioDiretor;
	}

	public void setCbSocioDiretor(JComboBox cbSocioDiretor) {
		this.cbSocioDiretor = cbSocioDiretor;
	}

	public static UsuarioBO getUsuariobo() {
		return usuarioBO;
	}
}
