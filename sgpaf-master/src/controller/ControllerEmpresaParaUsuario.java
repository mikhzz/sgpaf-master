package controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.boNovo.EmpresaBO;
import model.boNovo.UsuarioEmpresaBO;
import model.daoNovo.db.Empresa;

public class ControllerEmpresaParaUsuario {

	private JTextField tfPesquisaTelaAddEmpresaUsuario;
	private JTable tableTelaAddEmpresaUsuario;
	private JComboBox cbPesquisaEmpresaTelaAddEmpresa;
	private JButton btnAddEmpresaUsuario;
	private List<Empresa> empresas;
	
	private static final EmpresaBO empresaBO = new EmpresaBO();
//	private static final Table tableEmrpesaStatic =empresaBO.getTableEmpresa() ;
	private static final UsuarioEmpresaBO usuarioEmpresaBO = new UsuarioEmpresaBO();
	
	public ControllerEmpresaParaUsuario(JTextField tfPesquisaTelaAddEmpresaUsuario, JTable tableTelaAddEmpresaUsuario,
			JComboBox cbPesquisaEmpresaTelaAddEmpresa) {
		super();
		this.tfPesquisaTelaAddEmpresaUsuario = tfPesquisaTelaAddEmpresaUsuario;
		this.tableTelaAddEmpresaUsuario = tableTelaAddEmpresaUsuario;
		this.cbPesquisaEmpresaTelaAddEmpresa = cbPesquisaEmpresaTelaAddEmpresa;
	}
	public ControllerEmpresaParaUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void atualizarJTableAction() {
		empresas = empresaBO.consultarTodas();
		DefaultTableModel model = new DefaultTableModel();
		 model  = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		 
		//limpa a table e lista novamente
		model.setNumRows(0);
		for(Empresa empresa: empresas) {
			model.addRow(new Object[] {
					empresa.getIdEmpresa(),
					empresa.getNome(),
					empresa.getCnpj(),
					empresa.getTipo(),
					empresa.getResponsavel(),
			});
		}
	}
	public void atualizarJTableAction(List<Empresa> pListEmpresa) {
//		tableEmpresa = new JTable();
		empresas = pListEmpresa;
		DefaultTableModel model  = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		//limpa a table e lista novamente
		model.setNumRows(0);
		for(Empresa empresa: empresas) {
			model.addRow(new Object[] {
					empresa.getIdEmpresa(),
					empresa.getNome(),
					empresa.getCnpj(),
					empresa.getTipo(),
					empresa.getResponsavel(),
			});
		}
	}
	public  Empresa getEmpresaSelecionadaTable() {
//		 Empresa empresaSel = new Empresa();
		 //Resgatar linhas da tabela
	DefaultTableModel model;
//	tableEmpresa = new JTable();
	model = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
	int linha = tableTelaAddEmpresaUsuario.getSelectedRow();
	 if(linha>=0) {
		 System.out.println("LINHA SELECIONADA GET EMPRESA CONTROLLER EMPRESA PARA USUARIO LINHA: "+linha);
			 return empresas.get(linha);
		 }else {
			 System.out.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela ControllerEmpresaParaUsuario LINHA ROW :"+linha);
			 return null;
		 }
	}
	public void pesquisarAction() {
		System.out.println("ControllerEmpresaParaUsuario entrou no pesquisar action");
		System.out.println("index comboBox = "+cbPesquisaEmpresaTelaAddEmpresa.getSelectedIndex());
		
		switch (cbPesquisaEmpresaTelaAddEmpresa.getSelectedIndex()) {
		case 0:
			System.out.println("Cliente control entrou no pesquisar action pesquisa por nome");
			String nome =tfPesquisaTelaAddEmpresaUsuario.getText();
			String colunaNome = "nome";
			empresas= empresaBO.pesquisarPor(colunaNome,nome,1);
			atualizarJTableAction(empresas);
			break;
		case 1:
			try {
				String cnpj =tfPesquisaTelaAddEmpresaUsuario.getText();
				String colunaCnpj = "cnpj";
				empresas= empresaBO.pesquisarPor(colunaCnpj,cnpj,1);
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				String codigo =tfPesquisaTelaAddEmpresaUsuario.getText();
				String colunaCodigo = "id_empresa";
				empresas= empresaBO.pesquisarPor(colunaCodigo,codigo,1);
						atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				String tipo =tfPesquisaTelaAddEmpresaUsuario.getText();
				String colunaTipo= "tipo";
				empresas= empresaBO.pesquisarPor(colunaTipo,tipo,1);
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4 :
			try {
				String responsavel =tfPesquisaTelaAddEmpresaUsuario.getText();
				String colunaResponsavel= "responsavel";
				empresas= empresaBO.pesquisarPor(colunaResponsavel,responsavel,1);
				atualizarJTableAction(empresas);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		default: 
			
		break;
		}		
	}
	public JButton getBtnAddEmpresaUsuario() {
		return btnAddEmpresaUsuario;
	}

	public void setBtnAddEmpresaUsuario(JButton btnAddEmpresaUsuario) {
		this.btnAddEmpresaUsuario = btnAddEmpresaUsuario;
	}

	public JTextField getTfPesquisaTelaAddEmpresaUsuario() {
		return tfPesquisaTelaAddEmpresaUsuario;
	}

	public void setTfPesquisaTelaAddEmpresaUsuario(JTextField tfPesquisaTelaAddEmpresaUsuario) {
		this.tfPesquisaTelaAddEmpresaUsuario = tfPesquisaTelaAddEmpresaUsuario;
	}

	public JTable getTableTelaAddEmpresaUsuario() {
		return tableTelaAddEmpresaUsuario;
	}

	public void setTableTelaAddEmpresaUsuario(JTable tableTelaAddEmpresaUsuario) {
		this.tableTelaAddEmpresaUsuario = tableTelaAddEmpresaUsuario;
	}

	public JComboBox getCbPesquisaEmpresaTelaAddEmpresa() {
		return cbPesquisaEmpresaTelaAddEmpresa;
	}

	public void setCbPesquisaEmpresaTelaAddEmpresa(JComboBox cbPesquisaEmpresaTelaAddEmpresa) {
		this.cbPesquisaEmpresaTelaAddEmpresa = cbPesquisaEmpresaTelaAddEmpresa;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
}
