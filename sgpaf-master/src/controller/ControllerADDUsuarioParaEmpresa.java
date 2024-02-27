package controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.boNovo.UsuarioBO;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.base.Table;
import model.daoNovo.db.Usuario;

public class ControllerADDUsuarioParaEmpresa {

	private JTextField tfPesquiUsarioEmpresa;
	private JComboBox cbPesquisaUsuarioEmpresa;
	private JTable tableUsuarioEmpresa;

	private List<Usuario> usuarios;

	public static final int COLUNA_CODIGO = 0;
	public static final int COLUNA_NOME = 1;
	public static final int COLUNA_CPF = 2;
	public static final int COLUNA_CARGO = 3;

	// public static final UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static final UsuarioBO usuarioBO = new UsuarioBO();
//	public static final Table tableUsuarioStatic = usuarioBO.getTableUsuario();

	public ControllerADDUsuarioParaEmpresa(JTextField tfPesquiUsarioEmpresa, JComboBox cbPesquisaUsuarioEmpresa,
			JTable tableUsuarioEmpresa) {
		super();
		this.tfPesquiUsarioEmpresa = tfPesquiUsarioEmpresa;
		this.cbPesquisaUsuarioEmpresa = cbPesquisaUsuarioEmpresa;
		this.tableUsuarioEmpresa = tableUsuarioEmpresa;
	}

	public ControllerADDUsuarioParaEmpresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void atualizarJTableAction() {

		usuarios = usuarioBO.consultarTodos();
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tableUsuarioEmpresa.getModel();

		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Usuario usuario : usuarios) {
			model.addRow(
					new Object[] { usuario.getIdUsuario(), usuario.getNome(), usuario.getCpf(), usuario.getCargo(), });
		}
	}

	public void atualizarJTableAction(List<Usuario> pListUsuario) {

		usuarios = pListUsuario;

//		tableEmpresa = new JTable();
		DefaultTableModel model = (DefaultTableModel) tableUsuarioEmpresa.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Usuario usuario : usuarios) {
			model.addRow(
					new Object[] { usuario.getIdUsuario(), usuario.getNome(), usuario.getCpf(), usuario.getCargo() });
		}
	}

	public Usuario getUserSelecionadaTable() {
		// userSelecionado = new Usuario();
		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) tableUsuarioEmpresa.getModel();
		int linha = tableUsuarioEmpresa.getSelectedRow();
		System.out.println(linha);
		if (linha >= 0) {
			return usuarios.get(linha);
		} else {
			System.out.println(
					"ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela ControllerEmpresaParaUsuario");
			return null;
		}
	}

	public void pesquisarAction() {
		System.out.println("ControllerUsuarioParaEmpresa entrou no pesquisar action");
		System.out.println("index comboBox = " + cbPesquisaUsuarioEmpresa.getSelectedIndex());

		switch (cbPesquisaUsuarioEmpresa.getSelectedIndex()) {
		case 0:
//			System.out.println("ControllerUsuarioParaEmpresa entrou no pesquisar action pesquisa por nome");
//			String nome =tfPesquiUsarioEmpresa.getText();
//			String colunaNome = "nome";
//			usuarios= usuarioBO.
//					consultarWhereFiltro(new Filtro(tableUsuarioStatic.getColummJava(colunaNome),Comparador.LIKE,"%"+nome+"%"));
//			atualizarJTableAction(usuarios);
			System.out.println("Usuario control entrou no pesquisar action pesquisa por nome");
			String nomeUsuario = tfPesquiUsarioEmpresa.getText();
			usuarios = usuarioBO.consultarWhereFiltros("nome", nomeUsuario, 1, 1);
			atualizarJTableAction(usuarios);
			break;
		case 1:
			try {
//				String cpf =tfPesquiUsarioEmpresa.getText();
//				String colunaCpf = "cpf";
//				usuarios= usuarioBO.
//						consultarWhereFiltro(new Filtro(tableUsuarioStatic.getColummJava(colunaCpf),Comparador.LIKE,"%"+cpf+"%"));
//				atualizarJTableAction(usuarios);
				String cpf = tfPesquiUsarioEmpresa.getText();
				usuarios = usuarioBO.consultarWhereFiltros("cpf", cpf, 1, 1);
				atualizarJTableAction(usuarios);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
//				String cargo =tfPesquiUsarioEmpresa.getText();
//				String colunaCargo = "cargo";
//				usuarios= usuarioBO.
//						consultarWhereFiltro(new Filtro(tableUsuarioStatic.getColummJava(colunaCargo),Comparador.LIKE,"%"+cargo+"%"));
//				atualizarJTableAction(usuarios);
				String StringCargo = tfPesquiUsarioEmpresa.getText();
				usuarios = usuarioBO.consultarWhereFiltros("cargo", StringCargo, 1, 1);
				atualizarJTableAction(usuarios);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
//				String codigo =tfPesquiUsarioEmpresa.getText();
//				String colunaCodigo= "id_usuario";
//				usuarios= usuarioBO.
//						consultarWhereFiltro(new Filtro(tableUsuarioStatic.getColummJava(colunaCodigo),Comparador.LIKE,"%"+codigo+"%"));
//				atualizarJTableAction(usuarios);
				String codigo = tfPesquiUsarioEmpresa.getText();
				usuarios = usuarioBO.consultarWhereFiltros("id_usuario", codigo, 1, 1);
				atualizarJTableAction(usuarios);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		default:

			break;
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public JTextField getTfPesquiUsarioEmpresa() {
		return tfPesquiUsarioEmpresa;
	}

	public void setTfPesquiUsarioEmpresa(JTextField tfPesquiUsarioEmpresa) {
		this.tfPesquiUsarioEmpresa = tfPesquiUsarioEmpresa;
	}

	public JComboBox getCbPesquisaUsuarioEmpresa() {
		return cbPesquisaUsuarioEmpresa;
	}

	public void setCbPesquisaUsuarioEmpresa(JComboBox cbPesquisaUsuarioEmpresa) {
		this.cbPesquisaUsuarioEmpresa = cbPesquisaUsuarioEmpresa;
	}

	public JTable getTableUsuarioEmpresa() {
		return tableUsuarioEmpresa;
	}

	public void setTableUsuarioEmpresa(JTable tableUsuarioEmpresa) {
		this.tableUsuarioEmpresa = tableUsuarioEmpresa;
	}

}
