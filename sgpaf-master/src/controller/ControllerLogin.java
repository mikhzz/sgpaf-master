package controller;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.UsuarioDaoImpl;
import model.boNovo.UsuarioBO;
import model.daoNovo.db.Usuario;
import model.util.GeradoraHash;
import view.TelaLogin;
import view.TelaPrincipal;

public class ControllerLogin {

	private Usuario usuario;

	private JTextField textFieldLogin;

	private JPasswordField passwordFieldSenha;

	private UsuarioBO usuarioBO;
	private UsuarioDaoImpl usuDao;

	private TelaPrincipal telaPrincipal;
	private TelaLogin telaLogin;

	public ControllerLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControllerLogin(JTextField textFieldLogin, JPasswordField passwordFieldSenha) {
		super();
		this.textFieldLogin = textFieldLogin;
		this.passwordFieldSenha = passwordFieldSenha;
//		usuarioBO = new UsuarioBO();
		usuDao = new UsuarioDaoImpl();
	}
	public static String gerarHashUsr(String pSenha) {

		String password = null;

		password = GeradoraHash.stringHexa(GeradoraHash.gerarHash(pSenha, "SHA-256"));

		return password;
	}
	public boolean logarAction() {
		
		String loginDigitado = textFieldLogin.getText();
		String senhaDigitada = passwordFieldSenha.getText();
		String mensagem = validarLogin(loginDigitado, senhaDigitada);
		if (!mensagem.isEmpty()) {
			JOptionPane.showMessageDialog(null, mensagem);
			return false;
		}
		usuario = usuDao.logar(loginDigitado, gerarHashUsr(senhaDigitada));

		if (usuario != null) {
			try {
				telaPrincipal = new TelaPrincipal(usuario);
				telaPrincipal.show();
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}

		} else {
			mensagem = "Usuário ou senha inválido";
			JOptionPane.showMessageDialog(null, mensagem);
			return false;
		}

		limpaTela();
		return true;
	}

	private void limpaTela() {
		textFieldLogin.setText("");
		passwordFieldSenha.setText("");
	}

	public String validarLogin(String loginDigitado, String senhaDigitada) {
		String mensagem = "";

		if (loginDigitado.isEmpty() || loginDigitado.trim().length() < 3) {
			mensagem += "Digite um Login e Senha Válidos ";

		} else if (senhaDigitada.isEmpty() || senhaDigitada.trim().length() < 3) {

			mensagem += "Digite um Login e Senha Válidos";
		}

		return mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}

	public void setTextFieldLogin(JTextField textFieldLogin) {
		this.textFieldLogin = textFieldLogin;
	}

	public JPasswordField getPasswordFieldSenha() {
		return passwordFieldSenha;
	}

	public void setPasswordFieldSenha(JPasswordField passwordFieldSenha) {
		this.passwordFieldSenha = passwordFieldSenha;
	}

	public UsuarioBO getUsuarioDAO() {
		return usuarioBO;
	}

	public void setUsuarioDAO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}

}
