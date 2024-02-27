package controller;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import dao.LancamentoDaoImpl;
import model.daoNovo.db.Lancamento;

public class ControllerRecomendacao {
	
	private JCheckBox chckbxDivergencias;
	private JTextArea txtAreaRecomendacoes;
	private Lancamento lancamento;
	private static final LancamentoDaoImpl lancamentoDAO = new LancamentoDaoImpl();
	
	public ControllerRecomendacao(JCheckBox chckbxDivergencias, JTextArea txtAreaRecomendacoes, Lancamento lancamento) {
		super();
		this.chckbxDivergencias = chckbxDivergencias;
		this.txtAreaRecomendacoes = txtAreaRecomendacoes;
		this.lancamento = lancamento;
	}
	
	
	public void popularTela() {
		chckbxDivergencias.setSelected(lancamento.isDivergencia());
		if(lancamento.getRecomendacao()!=null) {
			txtAreaRecomendacoes.setText(lancamento.getRecomendacao());
		}
	}
	
	public void salvarAction() {
		
		lancamento.setDivergencia(chckbxDivergencias.isSelected());
		lancamento.setRecomendacao(txtAreaRecomendacoes.getText());
		if(lancamentoDAO.salvarOuAlterar(lancamento)) {
			JOptionPane.showMessageDialog(null, "Salvei com sucesso", "OK",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Erro ao salvar recomendação", "Ops!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	

}
