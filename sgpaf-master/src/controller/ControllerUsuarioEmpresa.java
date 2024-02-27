package controller;

import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.boNovo.UsuarioEmpresaBO;
import model.daoNovo.base.JoinAgregator;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import view.TelaEmpresaParaUsuario;

public class ControllerUsuarioEmpresa {
	
	private JTable tableEmpresasDosUsuarios;
	private JLabel labelNomeUsuario;
	private JLabel labelNomeUsuarioSelecionado;
	private JTable tableTelaAddEmpresaUsuario;
	private Usuario usuarioLogado;
	private Usuario usuarioVincular;
	private Empresa empresa;
	private UsuarioEmpresa usuarioEmpresa;
	
	private JoinAgregator<Usuario, UsuarioEmpresa, Empresa> joinAgregator;
//	private List<Empresa> listEmpresas;
	private List<UsuarioEmpresa> listUsuarioEmpresas;
	private TelaEmpresaParaUsuario telaEmpresaParaUsuario = null;
	private int indiceUsuarioEmpre;
	
	private static final UsuarioEmpresaBO usuarioEmpresaBO = new UsuarioEmpresaBO();
	
	public ControllerUsuarioEmpresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControllerUsuarioEmpresa( JTable tableEmpresasDosUsuarios, JLabel labelNomeUsuario,
			 JTable tableTelaAddEmpresaUsuario,JLabel labelNomeUsuarioSelecionado,
			 Usuario usuarioLogado) {
		super();
		this.tableEmpresasDosUsuarios = tableEmpresasDosUsuarios;
		this.labelNomeUsuario = labelNomeUsuario;
		this.tableTelaAddEmpresaUsuario = tableTelaAddEmpresaUsuario;
		this.labelNomeUsuarioSelecionado = labelNomeUsuarioSelecionado;
		this.usuarioLogado = usuarioLogado;
		
	}

	public void populaLabelUsuario(Usuario pUsuario) {
		this.usuarioVincular = pUsuario;
		labelNomeUsuario.setText(pUsuario.getNome());
		labelNomeUsuarioSelecionado.setText(pUsuario.getNome());
		System.out.println("populaLabelUsuario Controller Usuario Empresa id: "+pUsuario.getIdUsuario());
		
	}
	
	
	public void addEmpresaParaUsuario(Usuario pUsuario, Empresa pEmpresa) {
		
		usuarioEmpresa = new UsuarioEmpresa();
		usuarioEmpresa.setEmpresa(pEmpresa);
		usuarioEmpresa.setUsuario(pUsuario);
		usuarioEmpresa.setDataInicio(new Date());
//		joinAgregator = usuarioEmpresaBO.consultarJoinPorUsuario(pUsuario);
//		listEmpresas = joinAgregator.getListList();
//		listUsuarioEmpresas = joinAgregator.getJoinList();
		usuarioEmpresaBO.salvarUsuarioEmpresa(usuarioEmpresa);
		atualizarJTableAction();
	}
	
	private void atualizarJTableAction() {
		listUsuarioEmpresas = usuarioEmpresaBO.consultarTodos(usuarioVincular);
		DefaultTableModel model  = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		//limpa a table e lista novamente
		model.setNumRows(0);
		for (UsuarioEmpresa usuarioEmpresa : listUsuarioEmpresas) {
			model.addRow(new Object[] {
					usuarioEmpresa.getEmpresa().getNome(),
			});
		}
	}
	
	public void atualizarJTableAction(Usuario pUsuario) {
		listUsuarioEmpresas = usuarioEmpresaBO.consultarTodos(pUsuario);
		DefaultTableModel model  = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		//limpa a table e lista novamente
		model.setNumRows(0);
		for (UsuarioEmpresa usuarioEmpresa : listUsuarioEmpresas) {
			model.addRow(new Object[] {
					usuarioEmpresa.getEmpresa().getNome(),
			});
		}
//		joinAgregator = usuarioEmpresaBO.consultarJoinPorUsuario(pUsuario);
//		listEmpresas = joinAgregator.getListList();
//		listUsuarioEmpresas = joinAgregator.getJoinList();
//		
//		DefaultTableModel model  = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		//limpa a table e lista novamente
//		model.setNumRows(0);
//		for(Empresa empresa: listEmpresas) {
//			model.addRow(new Object[] {
//					empresa.getNome(),
//			});
//		}
	}
	
	public UsuarioEmpresa getEmpresaSelecionadaTable() {
		 //Resgatar linhas da tabela
		 DefaultTableModel model;
//		tableEmpresa = new JTable();
		 model = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
		 int linha = tableTelaAddEmpresaUsuario.getSelectedRow();
		 System.out.println(linha);
		 if(linha>=0) {
			 return listUsuarioEmpresas.get(linha);
		 }else {
			 System.out.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela ControllerEmpresaParaUsuario");
			 return null;
		 }
	}
	public  UsuarioEmpresa getEmpresaTableBalancete() {
		 UsuarioEmpresa empresaSel = new UsuarioEmpresa();
		 //Resgatar linhas da tabela
	DefaultTableModel model;
//	tableEmpresa = new JTable();
	model = (DefaultTableModel) tableTelaAddEmpresaUsuario.getModel();
	int linha = tableTelaAddEmpresaUsuario.getSelectedRow();
	 if(linha>=0) {
			 return listUsuarioEmpresas.get(linha);
		 }else {
			 System.out.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela ControllerEmpresaParaUsuario");
			 return null;
		 }
	}
	public String desvincularEmpresa(UsuarioEmpresa pUsuarioDesvincular) {
		
//		joinAgregator = usuarioEmpresaBO.consultarJoinPorUsuario(pUsuario);
//		listEmpresas = joinAgregator.getListList();
//		listUsuarioEmpresas = joinAgregator.getJoinList();
		usuarioEmpresa = getEmpresaSelecionadaTable();
		atualizarJTableAction(pUsuarioDesvincular.getUsuario());
		return usuarioEmpresaBO.excluirUsuarioEmpresaBO(usuarioEmpresa);
	}
	public JTable getTableEmpresasDosUsuarios() {
		return tableEmpresasDosUsuarios;
	}

	public void setTableEmpresasDosUsuarios(JTable tableEmpresasDosUsuarios) {
		this.tableEmpresasDosUsuarios = tableEmpresasDosUsuarios;
	}

	public JLabel getLabelNomeUsuario() {
		return labelNomeUsuario;
	}

	public void setLabelNomeUsuario(JLabel labelNomeUsuario) {
		this.labelNomeUsuario = labelNomeUsuario;
	}

	public JTable getTableTelaAddEmpresaUsuario() {
		return tableTelaAddEmpresaUsuario;
	}

	public void setTableTelaAddEmpresaUsuario(JTable tableTelaAddEmpresaUsuario) {
		this.tableTelaAddEmpresaUsuario = tableTelaAddEmpresaUsuario;
	}

	public Usuario getUsuarioVincular() {
		return usuarioVincular;
	}

	public void setUsuarioVincular(Usuario usuarioVincular) {
		this.usuarioVincular = usuarioVincular;
	}

}
