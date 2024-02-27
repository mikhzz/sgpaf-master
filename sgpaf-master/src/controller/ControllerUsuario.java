package controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.UsuarioDaoImpl;
import model.boNovo.PerfilBO;
import model.boNovo.UsuarioBO;
import model.boNovo.UsuarioEmpresaBO;
import model.boNovo.msg.Mensagem;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.base.Table;
import model.daoNovo.db.Perfil;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import model.util.GeradoraHash;

public class ControllerUsuario {

	private JTextField textFieldNomeUser;
	private JTextField tfLoginUsr;
	private JTextField tfPesquisaUser;
	private JTextField tfCPF;

	private JPasswordField passwordFieldSenhaUsr;

	private JComboBox cbCargo;
	private JComboBox cbNivelAcessoUsr;
	private JComboBox<String> cbPesquisaUser;

	private JFormattedTextField txtCpfUser;

	private JTable table;

	private static final UsuarioBO usuarioBO = new UsuarioBO();
	private static final UsuarioEmpresaBO usuEmpBO = new UsuarioEmpresaBO();
	private Usuario usuario;
	private Perfil perfil;
	private List<Usuario> usuarios;
	private static final UsuarioDaoImpl usuDao = new UsuarioDaoImpl();
	public static final int COLUNA_CODIGO = 0;
	public static final int COLUNA_NOME = 1;
	public static final int COLUNA_LOGIN = 2;
	public static final int COLUNA_CPF = 3;
	public static final int COLUNA_CARGO = 4;
	public static final int COLUNA_NIVEL = 5;

	public ControllerUsuario(JTextField textFieldNomeUser, JTextField tfLoginUsr, JPasswordField passwordFieldSenhaUsr,
			JComboBox cbCargo, JComboBox cbNivelAcessoUsr, JFormattedTextField txtCpfUser, JTextField tfPesquisaUser,
			JTable table, JComboBox<String> cbPesquisaUser) {
		super();
		this.tfCPF = tfCPF;
		this.textFieldNomeUser = textFieldNomeUser;
		this.tfLoginUsr = tfLoginUsr;
		this.passwordFieldSenhaUsr = passwordFieldSenhaUsr;
		this.cbCargo = cbCargo;
		this.cbNivelAcessoUsr = cbNivelAcessoUsr;
		this.txtCpfUser = txtCpfUser;
		this.tfPesquisaUser = tfPesquisaUser;
		this.table = table;
		this.cbPesquisaUser = cbPesquisaUser;
	}

	public ControllerUsuario() {

	}

	public void cadastrarAction(Usuario usuarioSelecionado) {
		if (usuarioSelecionado == null) {
			usuarioSelecionado = new Usuario();
		}
		Perfil perfil = new Perfil();
		String nomeDigitado = textFieldNomeUser.getText();
		String cpfDigitado = txtCpfUser.getText().replace(".", "").replace(".", "").replace("-", "");
		String cargoDigitado = (String) cbCargo.getSelectedItem();
		String loginDigitado = tfLoginUsr.getText();
		switch ((String) cbNivelAcessoUsr.getSelectedItem()) {
		case "Admin":
			perfil.setIdPerfil(1L);
			break;
		case "Comum":
			perfil.setIdPerfil(2L);
			break;
		}
		String senhaDigitada = new String(passwordFieldSenhaUsr.getPassword());
		String mensagem = validarCamposSalvar(nomeDigitado, cpfDigitado, loginDigitado, senhaDigitada);

		if (cbCargo.getSelectedIndex() == -1 || cbNivelAcessoUsr.getSelectedIndex() == -1) {

			JOptionPane.showMessageDialog(null, "Por Favor, escolha um cargo ou um Nivel de Acesso");

		} else if (mensagem.isEmpty()) {
			usuarioSelecionado.setNome(nomeDigitado);
			usuarioSelecionado.setCpf(cpfDigitado);
			usuarioSelecionado.setCargo(cargoDigitado);
			usuarioSelecionado.setLogin(loginDigitado);
			usuarioSelecionado.setPerfil(perfil);
			usuarioSelecionado.setSenha(ControllerLogin.gerarHashUsr(senhaDigitada));
			Mensagem confirma = usuarioBO.salvarUsuario(usuarioSelecionado);
			if (confirma.getSucess()) {
				JOptionPane.showMessageDialog(null, "Cadastrado com sucesso! " + "\nNome: " + usuarioSelecionado.getNome() + ""
						+ "\nCPF: " + usuarioSelecionado.getCpf());
			} else {
				JOptionPane.showMessageDialog(null, "Ops! Erro de comunicação ao cadastrar!");
			}
			limparCampos();
			atualizarJTableAction();
		}
	}

	public void editarUsuarioAction(Usuario usuarioSelecionado) {
		Perfil perfil = new Perfil();
		System.out.println("eNTROU NO EDITAR DO USUARIO CONTROL");
		int confirma = JOptionPane.showConfirmDialog(null, "Editar \nUsuario: " + usuarioSelecionado.getNome(),
				"Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(confirma);

		if (confirma == 0) {

			String mensagem = "";

//			int codigo = usuarioSelecionado.getIdUsuario();
			String nomeDigitado = textFieldNomeUser.getText();
			String cpfDigitado = txtCpfUser.getText().replace(".", "").replace(".", "").replace("-", "");
			String loginDigitado = tfLoginUsr.getText();

			String cargo = (String) cbCargo.getSelectedItem();
			switch ((String) cbNivelAcessoUsr.getSelectedItem()) {
			case "Admin":
				perfil.setIdPerfil(1L);
				break;
			case "Comum":
				perfil.setIdPerfil(2L);
				break;
			}
//			perfil = (Perfil) cbNivelAcessoUsr.getSelectedItem();

//			 mensagem = validarCamposSalvar(nomeDigitado, cpfDigitado, loginDigitado,
//			 senhaDigitada);

			if (mensagem.isEmpty()) {
//				Usuario usuario = new Usuario();
//				nomeDigitado = nomeDigitado.toUpperCase();
//				cpfDigitado = cpfDigitado.toUpperCase();
//				loginDigitado = loginDigitado.toUpperCase();

//				usuario.setIdUsuario(usuarioSelecionado.getIdUsuario());
				usuarioSelecionado.setNome(nomeDigitado);
				usuarioSelecionado.setCpf(cpfDigitado);
				usuarioSelecionado.setLogin(loginDigitado);
				usuarioSelecionado.setCargo(cargo);
				usuarioSelecionado.setPerfil(perfil);
				if (!passwordFieldSenhaUsr.getText().isEmpty()) {
					String senhaDigitada = new String(passwordFieldSenhaUsr.getPassword());
					usuario.setSenha(ControllerLogin.gerarHashUsr(senhaDigitada));
				}

//				Mensagem resultado usuarioBO.alterar(usuario);
				if (usuDao.salvarOuAlterar(usuarioSelecionado)) {
					Mensagem resultado = usuarioBO.alterar(usuario);
					if (resultado.getSucess()) {
						JOptionPane.showMessageDialog(null, "Usuário Alterado com Sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao Alterar no Banco de Dados o Usuário");
					}
				} else {

					JOptionPane.showMessageDialog(null, mensagem);
				}
			}
			limparCampos();
			atualizarJTableAction();
		}
	}

	public void pesquisarAction() {
		System.out.println("Usuario control entrou no pesquisar action");
		switch (cbPesquisaUser.getSelectedIndex()) {
		case 0:
			System.out.println("Usuario control entrou no pesquisar action pesquisa por nome");
			String nomeUsuario = tfPesquisaUser.getText();
			usuarios = usuarioBO.consultarWhereFiltros("nome", nomeUsuario, 1, 1);
			atualizarJTableAction(usuarios);
			break;
		case 1:
			String login = tfPesquisaUser.getText();
			usuarios = usuarioBO.consultarWhereFiltros("login", login, 1, 1);
			atualizarJTableAction(usuarios);
			break;
		case 2:
			String cpf = tfPesquisaUser.getText();
			usuarios = usuarioBO.consultarWhereFiltros("cpf", cpf, 1, 1);
			atualizarJTableAction(usuarios);
			break;
		case 3:
			String StringCargo = tfPesquisaUser.getText();
			usuarios = usuarioBO.consultarWhereFiltros("cargo", StringCargo, 1, 1);
			atualizarJTableAction(usuarios);
			break;
		default:
			break;
		}
	}

	public void excluirUsuarioAction() {
		usuario = getItemSelecionadoTable();
		excluirUsuarioController(usuario);
		atualizarJTableAction();
	}

	private String excluirUsuarioController(Usuario pUsuario) {

		List<UsuarioEmpresa> usuariosEmpresas = usuEmpBO.consultarTodos(pUsuario);
		if (usuariosEmpresas.size() > 0) {
			for (UsuarioEmpresa usuarioEmpresa : usuariosEmpresas) {
				usuarioEmpresa.setAtivo(0);
				usuEmpBO.excluirUsuarioEmpresaBO(usuarioEmpresa);
			}
		}
		UsuarioBO usuarioBO = new UsuarioBO();
		pUsuario.setAtivo(0);
		pUsuario.setHabilitado(0);
		return usuarioBO.excluirUsuarioBO(pUsuario);
	}

	public void limparCampos() {
		textFieldNomeUser.setText("");
		txtCpfUser.setText("");
		tfLoginUsr.setText("");
		passwordFieldSenhaUsr.setText("");
	}

	public void popularCampoEditarEmp() {
		usuario = getItemSelecionadoTable();

		textFieldNomeUser.setText(usuario.getNome());
		txtCpfUser.setText(usuario.getCpf());
		tfLoginUsr.setText(usuario.getLogin());
		passwordFieldSenhaUsr.setText(usuario.getSenha());
	}

	public Usuario getItemSelecionadoTable() {

		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		System.out.println(linha);
		if (linha >= 0) {
			return usuarios.get(linha);
		} else {
			JOptionPane.showMessageDialog(null, "Selecione");
			return null;
		}
	}

	public void atualizarJTableAction(List<Usuario> pListUsuarios) {

		usuarios = pListUsuarios;

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Usuario usuario : usuarios) {
			model.addRow(new Object[] { usuario.getIdUsuario(), usuario.getNome(), usuario.getLogin(), usuario.getCpf(),
					usuario.getCargo(), usuario.getPerfil().getTipo(), });
		}
	}

	public void atualizarJTableAction() {

		usuarios = usuarioBO.consultarTodos();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Usuario usuario : usuarios) {
			model.addRow(new Object[] { usuario.getIdUsuario(), usuario.getNome(), usuario.getLogin(), usuario.getCpf(),
					usuario.getCargo(), usuario.getPerfil().getTipo(), });
		}
	}

//	public static String gerarHashUsr(String pSenha) {
//
//		String password = null;
//
//		password = GeradoraHash.stringHexa(GeradoraHash.gerarHash(pSenha, "SHA-256"));
//
//		return password;
//	}

//	public String alterarUsuarioController(Usuario usuario) {
//
//		UsuarioBO usuarioBO = new UsuarioBO();
//		String password = null;
//
//		password = GeradoraHash.stringHexa(GeradoraHash.gerarHash(usuario.getSenha(), "SHA-256"));
//
//		usuario.setSenha(password);
//		
//		return usuarioBO.alterar(usuario).getMensagem();
//
//	}

	public String validarCamposSalvar(String nomeDigitado, String cpfDigitado, String loginDigitado,
			String senhaDigitada) {

		String mensagem = "";

		if (nomeDigitado.isEmpty() || nomeDigitado.trim().length() < 3) {
			mensagem += "Nome deve possuir pelo menos 3 caracteres \n";
		}
		if (cpfDigitado.isEmpty() || cpfDigitado.trim().length() < 3) {

			mensagem += "Cpf deve possuir 11 digitos \n";
		}

		if (loginDigitado.isEmpty() || loginDigitado.trim().length() < 3) {
			mensagem += "Login deve possuir pelo menos 3 caracteres \n";
		}

//		if (senhaDigitada.isEmpty() || senhaDigitada.trim().length() < 2) {
//			mensagem += "Senha deve possuir no minimo 3 caracteres";
//		}

		return mensagem;
	}

	public List<Usuario> consultarTodos() {
		return usuarioBO.consultarTodos();
	}

//	public Perfil getPerfil(Usuario usuario) {
//		return perfilBO.getPerfil(usuario);
//	}

	public JTextField getTextFieldNomeUser() {
		return textFieldNomeUser;
	}

	public void setTextFieldNomeUser(JTextField textFieldNomeUser) {
		this.textFieldNomeUser = textFieldNomeUser;
	}

	public JTextField getTfLoginUsr() {
		return tfLoginUsr;
	}

	public void setTfLoginUsr(JTextField tfLoginUsr) {
		this.tfLoginUsr = tfLoginUsr;
	}

	public JPasswordField getPasswordFieldSenhaUsr() {
		return passwordFieldSenhaUsr;
	}

	public void setPasswordFieldSenhaUsr(JPasswordField passwordFieldSenhaUsr) {
		this.passwordFieldSenhaUsr = passwordFieldSenhaUsr;
	}

	public JComboBox getCbCargoUsr() {
		return cbCargo;
	}

	public void setCbCargoUsr(JComboBox cbCargo) {
		this.cbCargo = cbCargo;
	}

	public JComboBox getCbNivelAcessoUsr() {
		return cbNivelAcessoUsr;
	}

	public void setCbNivelAcessoUsr(JComboBox cbNivelAcessoUsr) {
		this.cbNivelAcessoUsr = cbNivelAcessoUsr;
	}

	public JFormattedTextField getTxtCpfUser() {
		return txtCpfUser;
	}

	public void setTxtCpfUser(JFormattedTextField txtCpfUser) {
		this.txtCpfUser = txtCpfUser;
	}

	public JTextField getTfPesquisaUser() {
		return tfPesquisaUser;
	}

	public void setTfPesquisaUser(JTextField tfPesquisaUser) {
		this.tfPesquisaUser = tfPesquisaUser;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JComboBox<String> getCbPesquisaUser() {
		return cbPesquisaUser;
	}

	public void setCbPesquisaUser(JComboBox<String> cbPesquisaUser) {
		this.cbPesquisaUser = cbPesquisaUser;
	}

	public JTextField getTfCPF() {
		return tfCPF;
	}

	public void setTfCPF(JTextField tfCPF) {
		this.tfCPF = tfCPF;
	}

	public JComboBox getCbCargo() {
		return cbCargo;
	}

	public void setCbCargo(JComboBox cbCargo) {
		this.cbCargo = cbCargo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
