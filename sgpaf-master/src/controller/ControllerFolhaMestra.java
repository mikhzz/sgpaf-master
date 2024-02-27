package controller;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import dao.LancamentoDaoImpl;
import model.boNovo.FolhamestraBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;

public class ControllerFolhaMestra {
	private ControllerProced controllerProced;
	private ControllerFolha folhaControl;
	private ControllerBalanceteFolhaMestra balanceteControl;
	private ControllerCapa capaControl;
	private JComboBox cbREFFolha;
	private JComboBox cbREFProc;
	private static final LancamentoDaoImpl lancamentoDAO= new LancamentoDaoImpl();
	
	private static final FolhamestraBO folhaMestraBO = new FolhamestraBO();
	
	private Usuario executor;
	private Empresa empresa;
	private Balancete balancete;
	private FolhaMestra folhamestra;
	
	public ControllerFolhaMestra(ControllerProced controllerProced,
			ControllerBalanceteFolhaMestra balanceteControl, ControllerCapa capaControl,
			ControllerFolha folhaControl, Usuario executor,
			Empresa empresa, Balancete balancete, JComboBox cbREFFolha,JComboBox cbREFProc) {
		super();
		
		this.controllerProced = controllerProced;
		this.folhaControl = folhaControl;
		this.capaControl = capaControl;
		this.balanceteControl = balanceteControl;
		this.executor = executor;
		this.empresa = empresa;
		this.balancete = balancete;
		this.cbREFFolha = cbREFFolha;
		this.cbREFProc = cbREFProc;
	}
	
	public ControllerFolhaMestra() {
	}
	public void popularComboRef() {
		List<Lancamento> lancamentosGroupByRefpt = lancamentoDAO.consultaGroupByRefPT(balancete);
		int index = -1 ;
		for (Lancamento lancamentoGroup : lancamentosGroupByRefpt) {
			if(lancamentoGroup.getRefpt().equals("")) {
				index = lancamentosGroupByRefpt.indexOf(lancamentoGroup);
			}
		}
		if(index>=0) {
			lancamentosGroupByRefpt.remove(index);
		}
		ComboBoxModel<Object> modelCombo;

		modelCombo = new DefaultComboBoxModel<Object>(lancamentosGroupByRefpt.toArray());

		cbREFFolha.setModel(modelCombo);
		cbREFProc.setModel(modelCombo);
	}
	
	public int salvarAction() {
		capaControl.salvarAction(folhamestra);
		return mensagemSucesso();
	}
	public void popularFolhaMestra() {
		
		List<FolhaMestra> listFolhamestraresult = folhaMestraBO.consultarFolhamestras(balancete);
		if(listFolhamestraresult.size()>0) {
			folhamestra = listFolhamestraresult.get(0);
		}else {
			folhamestra = new FolhaMestra();
			folhamestra.setCheckboxRevisado(false);
			folhamestra.setBalancete(balancete);
			if(folhaMestraBO.salvarOuAlterarFolhamestra(folhamestra)) {
			}else {
				System.err.println("folha mestra nao salva id negativo ="+folhamestra.getIdFolhamestra());
			}
		}
		capaControl.popularCamposCapa(balancete, empresa, executor,folhamestra);
		balanceteControl.popularCamposBalancete(balancete, empresa, executor);
		controllerProced.setBalancete(balancete);
		controllerProced.setUsuarioExecutor(executor);
		folhaControl.popularCamposFolha(balancete, empresa, executor);
	}
	public void popularFolha() {
		
	}
	
	private int mensagemSucesso() {
		return JOptionPane.showConfirmDialog( null,"Salvei com Sucesso!\nDeja Continuar?", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public ControllerCapa getCapaControl() {
		return capaControl;
	}

	public void setCapaControl(ControllerCapa capaControl) {
		this.capaControl = capaControl;
	}

	public ControllerFolha getfolhaControl() {
		return folhaControl;
	}

	public void setfolhaControl(ControllerFolha folhaControl) {
		this.folhaControl = folhaControl;
	}

}
