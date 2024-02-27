package controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.boNovo.EmpresaBO;
import model.boNovo.UsuarioEmpresaBO;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.base.Table;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.UsuarioEmpresa;
import view.TelaVisualizaEmpresa;

public class ControllerEmpresa {

	public static final String TIPO_RELATORIO_XLS = "xls";
	private Empresa empresaTelaVisual = null;
	private TelaVisualizaEmpresa telaVisualEmpresa = null;
	private static final EmpresaBO empresaBO = new EmpresaBO();
	private static final UsuarioEmpresaBO usuEmpBO= new UsuarioEmpresaBO();
//	private static final Table tableEmrpesaStatic =empresaBO.getTableEmpresa() ;

	// ___________________________________________________________________________
	// ATRIBUTOS DA TELA GERENCIAMENTO DE EMPRESAS
	// ___________________________________________________________________________

	private JTextField textFieldNome;
	private JTextField textFieldEndereco;

	private JTextField textFieldTipo;
	private JTextField textFieldEmail;
	private JTextField textFieldResponsavel;
	private JFormattedTextField txtCnpj;
	private JFormattedTextField txtTelefone;

	private JTable tableEmpresa;
	private JTextField tfPesquisaEmp;
	private List<Empresa> empresas;
	
	private Empresa empresaSelecionada = null;
	private JComboBox cbPesquisa;
	private Empresa empresa;

	// ___________________________________________________________________________
	// CONTRUTOR DA TELA GERENCIAMENTO DE EMPRESAS
	// ___________________________________________________________________________

	public ControllerEmpresa() {
		super();
	}

	public ControllerEmpresa(JFormattedTextField txtTelefone, JFormattedTextField txtCnpj, JTextField textFieldNome,
			JTextField textFieldEndereco, JTextField textFieldTipo, JTextField textFieldEmail,
			JTextField textFieldResponsavel, JTable tableEmpresa, JTextField tfPesquisaEmp,JComboBox cbPesquisa) {
		super();
		this.textFieldNome = textFieldNome;
		this.textFieldEndereco = textFieldEndereco;
		this.textFieldTipo = textFieldTipo;
		this.textFieldEmail = textFieldEmail;
		this.textFieldResponsavel = textFieldResponsavel;
		this.tableEmpresa = tableEmpresa;
		this.tfPesquisaEmp = tfPesquisaEmp;
		this.txtCnpj = txtCnpj;
		this.txtTelefone = txtTelefone;
		this.cbPesquisa = cbPesquisa;
	}

	public String salvarEmpresaController(Empresa Empresa) {
		EmpresaBO empresaBO = new EmpresaBO();
		return empresaBO.salvarEmpresaBO(Empresa);
	}
	public String ExcluirEmpresaController(Empresa pEmpresaExcluir) {
		EmpresaBO empresaBO = new EmpresaBO();
		return empresaBO.excluirEmpresaBO(pEmpresaExcluir);
	}
	public String alterarEmpresaController(Empresa Empresa) {
		EmpresaBO empresaBO = new EmpresaBO();
		return empresaBO.alterarEmpresaBO(Empresa);
	}
	
	public void cadastrarEmpresaAction() {

		String mensagem = "";

		if (validarCamposVazioEmpresa(false)) {
			
			popularEmpresaParaBanco(empresa);
			
			mensagem = salvarEmpresaController(empresa);
			
			atualizarJTableAction();
			
			limpartela();
			
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}
	public void editarEmpresaAction(Empresa pEmpresa) {
			
		String mensagem = "";
			
		int confirma = JOptionPane.showConfirmDialog(null,
					"Editar \nEmpresa: " + pEmpresa.getNome() + "\nCNPJ: " + pEmpresa.getCnpj(), "Confirma",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(confirma);
			
		if (confirma == 0) {
			if (validarCamposVazioEmpresa(true)) {
				popularEmpresaParaBanco(pEmpresa);
				mensagem = alterarEmpresaController(pEmpresa);
				atualizarJTableAction();
				limpartela();
				JOptionPane.showMessageDialog(null, mensagem);
				} 
		}
	}
	public void excluirEmresaAction(Empresa pEmpresa) {
		int confirma = JOptionPane.showConfirmDialog(null,
				"Tem certeza que deseja excluir? \nEmpresa: " + pEmpresa.getNome() + "\nCNPJ: " + pEmpresa.getCnpj(),
				"Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(confirma);

		if (confirma == 0) {
			String mensagem = "";
			pEmpresa.setAtivo(0);
			List<UsuarioEmpresa> usuariosEmpresas = usuEmpBO.consultarPorEmpresa(pEmpresa);
			if(usuariosEmpresas.size()>0) {
				for (UsuarioEmpresa usuarioEmpresa : usuariosEmpresas) {
					usuarioEmpresa.setAtivo(0);
					usuEmpBO.excluirUsuarioEmpresaBO(usuarioEmpresa);
				}
			}
			
			mensagem = ExcluirEmpresaController(pEmpresa);
			JOptionPane.showMessageDialog(null, mensagem);
			
			if (pEmpresa.equals(null)) {

				JOptionPane.showMessageDialog(null, "ERRO, Seleciona uma Empresa!");
			} 
		}
		atualizarJTableAction();
	}
	
	public boolean validarCnpjJaCadastrado(JFormattedTextField tfcnpj) {
		boolean retorno =false;
	if(!tfcnpj.getText().isEmpty()) {
		if(!empresaBO.validarCNPJJaCadastrado(tfcnpj.getText().replace(".", "").replace(".", "").replace("/", "").replace("-", ""))) {
			JOptionPane.showMessageDialog(null, "Cnpj j� cadastrado.", "ERRO", JOptionPane.WARNING_MESSAGE);
			tfcnpj.requestFocus();
			retorno= false;
		}else {
			retorno = true;
		}
		}
	return retorno;
	}
	
	private boolean validarCnpj(Boolean ehEditar) {
		boolean retorno = true;
		if(ehEditar) {
			
			if(!validarTxtField(txtCnpj, "CNPJ")) {
				retorno = false;
			}else if(txtCnpj.getText().trim().length() != 18) {
				JOptionPane.showMessageDialog(null, "CNPJ deve conter 14 caracteres.", "ERRO", JOptionPane.WARNING_MESSAGE);
				txtCnpj.requestFocus();
				retorno = false;
				} else {
				List<Empresa>empresaConsulta = consultaLIKEPorColuna("cnpj", txtCnpj.getText().replace(".", "").replace(".", "").replace("/", "").replace("-", ""));
					if(empresaConsulta.size()>=2) {
						JOptionPane.showMessageDialog(null, "CNPJ j� cadastrado.", "ERRO", JOptionPane.WARNING_MESSAGE);
						txtCnpj.requestFocus();
						retorno = false;
					}
				}
		}else {
			if(!validarCnpjJaCadastrado(txtCnpj)) {
				JOptionPane.showMessageDialog(null, "Campo CNPJ n�o pode estar vazio.", "ERRO", JOptionPane.WARNING_MESSAGE);
				txtCnpj.requestFocus();
				retorno = false;
			}else if(txtCnpj.getText().trim().length() != 18) {
				JOptionPane.showMessageDialog(null, "CNPJ deve conter 14 caracteres.", "ERRO", JOptionPane.WARNING_MESSAGE);
				txtCnpj.requestFocus();
				retorno = false;
			}
		}
		return retorno;
	}
	
	private boolean validarTxtField(JTextField campoValidar,String nomeCampo) {
		boolean retorno = true;
		if(campoValidar.getText().equals("")) {
			campoValidar.requestFocus();
				JOptionPane.showMessageDialog(null, "Campo "+nomeCampo +" n�o pode estar vazio.", "ERRO", JOptionPane.WARNING_MESSAGE);
				retorno = false;
		}
		return retorno;
	}
	private boolean validarCamposVazioEmpresa(boolean validarEditar) {
		boolean retorno = true;
		
		if(!validarCnpj(validarEditar)) {
			retorno = false;
		}
		if(!validarTxtField(textFieldNome, "nome")) {
			retorno = false;
		}
		if(!validarTxtField(textFieldEndereco, "Endere�o")) {
			retorno = false;
		}
		if(!validarTxtField(textFieldEmail, "E-mail")) {
			retorno = false;
		}
		if(!validarTxtField(textFieldResponsavel, "Respons�vel")) {
			retorno = false;
		}
		if(!validarTxtField(txtTelefone, "Telefone")) {
			retorno = false;
		}
		if(!validarTxtField(textFieldTipo, "Tipo")) {
			retorno = false;
		}	
		
		return retorno;
	}
	public void pesquisarAction() {
		System.out.println("Cliente control entrou no pesquisar action");
		switch (cbPesquisa.getSelectedIndex()) {
		case 0:
			empresas = consultaLIKEPorColuna("nome", tfPesquisaEmp.getText());
			atualizarJTableAction(empresas);
			break;
		case 1:
			try {
				empresas = consultaLIKEPorColuna("cnpj", tfPesquisaEmp.getText());
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				empresas = consultaLIKEPorColuna("tipo", tfPesquisaEmp.getText());
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				empresas = consultaLIKEPorColuna("email", tfPesquisaEmp.getText());
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		default: 
			
		break;
		}		
	}
	public  Empresa getEmpresaSelecionadaTable() {
		//Resgatar linhas da tabela
	DefaultTableModel model;
	model = (DefaultTableModel) tableEmpresa.getModel();
	int linha = tableEmpresa.getSelectedRow();
	 if(linha>=0) {
			 return empresas.get(linha);
		 }else {
			 System.out.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela GerenciamentoEmpresa");
			 return null;
		 }
	}
	
	private List<Empresa> consultaLIKEPorColuna(String pColuna, String pConsulta){
		return empresas= empresaBO.pesquisarPor(pColuna,pConsulta,1);
	}
	
	private void atualizarJTableAction(List<Empresa> pListEmpresa) {
		empresas = pListEmpresa;
		DefaultTableModel model  = (DefaultTableModel) tableEmpresa.getModel();
		//limpa a table e lista novamente
		model.setNumRows(0);
		for(Empresa pEmpresa: empresas) {
			model.addRow(new Object[] {
					pEmpresa.getIdEmpresa(),
					pEmpresa.getNome(),
					pEmpresa.getCnpj(),
					pEmpresa.getEndereco(),
					pEmpresa.getTelefone(),
					pEmpresa.getTipo(),
					pEmpresa.getEmail(),
					pEmpresa.getResponsavel(),
			});
		}
	}
	public void atualizarJTableAction() {
		empresas = empresaBO.consultarTodas();
		DefaultTableModel model = new DefaultTableModel();
		try {
			model  = (DefaultTableModel) tableEmpresa.getModel();
		} catch (Exception e) {
			System.out.println("CATCH atualizarJTableAction CONTROLLER EMPRESA");
		}
		
		//limpa a table e lista novamente
		model.setNumRows(0);
		for(Empresa pEmpresa: empresas) {
			model.addRow(new Object[] {
					pEmpresa.getIdEmpresa(),
					pEmpresa.getNome(),
					pEmpresa.getCnpj(),
					pEmpresa.getEndereco(),
					pEmpresa.getTelefone(),
					pEmpresa.getTipo(),
					pEmpresa.getEmail(),
					pEmpresa.getResponsavel(),
			});
		}
	}

	private void popularEmpresaParaBanco(Empresa pEmpresa) {
		if(pEmpresa==null) {
		empresa = new Empresa();
		}else {
			empresa = pEmpresa;
		}
		empresa.setNome(textFieldNome.getText().toUpperCase());
		empresa.setCnpj(txtCnpj.getText().replace(".", "").replace(".", "").replace("/", "").replace("-", ""));
		empresa.setEndereco(textFieldEndereco.getText().toUpperCase());
		empresa.setTelefone(txtTelefone.getText().replace("(", "").replace(")", "").replace("-", ""));
		empresa.setTipo(textFieldTipo.getText().toUpperCase());
		empresa.setEmail(textFieldEmail.getText().toUpperCase());
		empresa.setResponsavel(textFieldResponsavel.getText().toUpperCase());
		
	}
	
	public void popularCampoEditarEmp(Empresa pEmpresa) {

		textFieldNome.setText(pEmpresa.getNome());
		textFieldEmail.setText(pEmpresa.getEmail());
		textFieldEndereco.setText(pEmpresa.getEndereco());
		textFieldResponsavel.setText(pEmpresa.getResponsavel());
		textFieldTipo.setText(pEmpresa.getTipo());
		txtTelefone.setText(pEmpresa.getTelefone());
		txtCnpj.setText(pEmpresa.getCnpj());
	}

	public void visualEmpresaPopulaTelaVisualEmpresaAction(Empresa pEmpresa) {

		empresaTelaVisual = pEmpresa;

		telaVisualEmpresa = new TelaVisualizaEmpresa();
		System.out.println(
				"TESTE PARAMETRO Empresa DO visuaEmpresaTelaVisualEmpresaAction ControllerEmpresa NOME Empresa: "
						+ empresaTelaVisual.getNome());
		telaVisualEmpresa.show();

		String codigoEmpresa = Long.toString(empresaTelaVisual.getIdEmpresa());
		telaVisualEmpresa.getTfCodigoTelaVisualEmp().setText(codigoEmpresa);

		String nomeEmpresa = empresaTelaVisual.getNome();
		telaVisualEmpresa.getTfNomeTelaVisualEmp().setText(nomeEmpresa);

		String cnpjEmpresa = empresaTelaVisual.getCnpj();
		telaVisualEmpresa.getTfCnpjTelaVisualEmp().setText(cnpjEmpresa);

		String enderecoEmpresa = empresaTelaVisual.getEndereco();
		telaVisualEmpresa.getTfEnderecoTelaVisualEmp().setText(enderecoEmpresa);

		String telefoneEmpresa = empresaTelaVisual.getTelefone();
		telaVisualEmpresa.getTfTelefoneTelaVisualEmp().setText(telefoneEmpresa);

		String tipoEmpresa = empresaTelaVisual.getTipo();
		telaVisualEmpresa.getTfTipoTelaVisualEmp().setText(tipoEmpresa);

		String emailEmpresa = empresaTelaVisual.getEmail();
		telaVisualEmpresa.getTfEmailTelaVisualEmp().setText(emailEmpresa);

		String responsavelEmpresa = empresaTelaVisual.getResponsavel();
		telaVisualEmpresa.getTfResponsavelTelaVisualEmp().setText(responsavelEmpresa);

	}
	public void limpartela() {
		empresa = null;
//		empresas = null;
		textFieldNome.setText("");
		txtCnpj.setText("");
		textFieldEndereco.setText("");
		txtTelefone.setText("");
		textFieldTipo.setText("");
		textFieldEmail.setText("");
		textFieldResponsavel.setText("");

	}
	// ---------------------------------------------------------------------------------
	// get e set tela gerenciamento de empresa
	// ---------------------------------------------------------------------------------

	public Empresa getEmpresaTelaVisual() {
		return empresaTelaVisual;
	}

	public JTextField getTextFieldNome() {
		return textFieldNome;
	}

	public void setTextFieldNome(JTextField textFieldNome) {
		this.textFieldNome = textFieldNome;
	}

	public JTextField getTextFieldEndereco() {
		return textFieldEndereco;
	}

	public void setTextFieldEndereco(JTextField textFieldEndereco) {
		this.textFieldEndereco = textFieldEndereco;
	}

	public JTextField getTextFieldTipo() {
		return textFieldTipo;
	}

	public void setTextFieldTipo(JTextField textFieldTipo) {
		this.textFieldTipo = textFieldTipo;
	}

	public JTextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public void setTextFieldEmail(JTextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	public JTextField getTextFieldResponsavel() {
		return textFieldResponsavel;
	}

	public void setTextFieldResponsavel(JTextField textFieldResponsavel) {
		this.textFieldResponsavel = textFieldResponsavel;
	}

	public JTable getTableEmpresa() {
		return tableEmpresa;
	}

	public void setTableEmpresa(JTable tableEmpresa) {
		this.tableEmpresa = tableEmpresa;
	}

	public JTextField getTfPesquisaEmp() {
		return tfPesquisaEmp;
	}

	public void setTfPesquisaEmp(JTextField tfPesquisaEmp) {
		this.tfPesquisaEmp = tfPesquisaEmp;
	}


	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public void setEmpresaTelaVisual(Empresa empresaTelaVisual) {
		this.empresaTelaVisual = empresaTelaVisual;
	}

	public TelaVisualizaEmpresa getTelaVisualEmpresa() {
		return telaVisualEmpresa;
	}

	public void setTelaVisualEmpresa(TelaVisualizaEmpresa telaVisualEmpresa) {
		this.telaVisualEmpresa = telaVisualEmpresa;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public JComboBox getCbPesquisa() {
		return cbPesquisa;
	}

	public void setCbPesquisa(JComboBox cbPesquisa) {
		this.cbPesquisa = cbPesquisa;
	}
	public static String getTipoRelatorioXls() {
		return TIPO_RELATORIO_XLS;
	}
	public JFormattedTextField getTxtCnpj() {
		return txtCnpj;
	}

	public void setTxtCnpj(JFormattedTextField txtCnpj) {
		this.txtCnpj = txtCnpj;
	}

	public JFormattedTextField getTxtTelefone() {
		return txtTelefone;
	}

	public void setTxtTelefone(JFormattedTextField txtTelefone) {
		this.txtTelefone = txtTelefone;
	}

}
