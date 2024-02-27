package controller;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import dao.LancamentoDaoImpl;
import dao.UsuarioFolhaMestraDaoImpl;
import model.boNovo.BalanceteBO;
import model.boNovo.EmpresaBO;
import model.boNovo.FolhamestraBO;
import model.boNovo.LancamentoBO;
import model.boNovo.msg.Mensagem;
import model.boNovo.msg.MensagemGenerica;
import model.daoNovo.base.Comparador;
import model.daoNovo.base.Filtro;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.FolhaMestra;
import model.daoNovo.db.Lancamento;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;
import model.daoNovo.db.UsuarioFolhaMestra;
import model.util.CalculoAH;
import model.util.CalculoAV;
import model.util.ImportadorDePlanilha;
import view.TelaBalancete;
import view.TelaVincularEmpresaUsuario;

public class ControllerBalancete {

	private JTextField txtRefPt;
	private JTextField txtNivel;
	private JTextField txtCEstruturado;
	private JTextField txtCodReduzido;
	private JTextField txtDescr;
	private JTextField txtSaldoInicial;
	private JTextField txtCredito;
	private JTextField txtDebito;
	private JTextField tfPesquisaLancamento;

	private JLabel lblNomeUsuario;
	private JLabel lblNomeEmpresa;
	private JLabel lblResultadoSaldoFinal;

	private JDateChooser dataFinal;
	private JDateChooser dataInicial;
	private JYearChooser anoDataBase;

	private JComboBox cbNatuConta;
	private JComboBox cbPesquisa;

	private JTable tableLancamentos;

	private Usuario usuario;
	private Empresa empresa;
	private Lancamento lancamento;
	private Balancete balancete;
	private FolhaMestra folhamestra;
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	private List<Lancamento> lancamentos;
	private static final BalanceteBO balanBO = new BalanceteBO();
	private static final LancamentoBO lancamentoBO = new LancamentoBO();
	private static final FolhamestraBO folhaMestraBO = new FolhamestraBO();
	private ImportadorDePlanilha importadorDePlanilha = new ImportadorDePlanilha();
	private static final EmpresaBO empresaBO = new EmpresaBO();
	private static final UsuarioFolhaMestraDaoImpl usuarioFolhaDAO = new UsuarioFolhaMestraDaoImpl();
	private JButton btnEmpresa;
	private JButton btnImportLancamento;
	private JFileChooser fc = new JFileChooser();
	private Component telaBalancete;
	private String newline = System.getProperty("line.separator");
	private TelaVincularEmpresaUsuario telaVincularEmpresaUsuario;
	private String strSaldoInicial;
	private String strDebito;
	private String strCredito;
	private BigDecimal bDSaldoInicial;
	private BigDecimal bDDebito;
	private BigDecimal bDCredito;
	private BigDecimal bDSaldoFinal;

	private boolean setEmpresaFlag = false;

	public ControllerBalancete() {
		super();
	}

	public ControllerBalancete(JTextField txtRefPt, JTextField txtNivel, JTextField txtCEstruturado,
			JTextField txtCodReduzido, JTextField txtDescr, JTextField txtSaldoInicial, JTextField txtCredito,
			JTextField txtDebito, JLabel lblNomeUsuario, JLabel lblNomeEmpresa, JDateChooser dataFinal,
			JDateChooser dataInicial, JComboBox cbNatuConta, JTable tableLancamentos, JTextField tfPesquisaLancamento,
			JComboBox cbPesquisa, JLabel lblResultadoSaldoFinal, JButton btnEmpresa, JYearChooser anoDataBase,
			JButton btnImportLancamento, TelaBalancete telaBalancete, Usuario usuario) {
		super();
		this.txtRefPt = txtRefPt;
		this.txtNivel = txtNivel;
		this.txtCEstruturado = txtCEstruturado;
		this.txtCodReduzido = txtCodReduzido;
		this.txtDescr = txtDescr;
		this.txtSaldoInicial = txtSaldoInicial;
		this.txtCredito = txtCredito;
		this.txtDebito = txtDebito;
		this.lblNomeUsuario = lblNomeUsuario;
		this.lblNomeEmpresa = lblNomeEmpresa;
		this.dataFinal = dataFinal;
		this.dataInicial = dataInicial;
		this.cbNatuConta = cbNatuConta;
		this.tableLancamentos = tableLancamentos;
		this.tfPesquisaLancamento = tfPesquisaLancamento;
		this.cbPesquisa = cbPesquisa;
		this.lblResultadoSaldoFinal = lblResultadoSaldoFinal;
		this.btnEmpresa = btnEmpresa;
		this.anoDataBase = anoDataBase;
		this.btnImportLancamento = btnImportLancamento;
		this.telaBalancete = telaBalancete;
		this.usuario = usuario;
		this.telaVincularEmpresaUsuario = new TelaVincularEmpresaUsuario(usuario);
		init();

	}

	void init() {
		telaVincularEmpresaUsuario.getBtnADDEmpresa().setVisible(false);
		telaVincularEmpresaUsuario.getBtnExluirEmpresa().setVisible(false);
		telaVincularEmpresaUsuario.getBtnTrocaUsuario().setVisible(false);

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int ano = calendar.get(Calendar.YEAR);

		dataFinal.setDate(null);
		dataInicial.setDate(date);
		anoDataBase.setYear(ano);
	}

	public void windowActivated() {
		try {
			if (setEmpresaFlag) {
				UsuarioEmpresa usuarioEmpresa = telaVincularEmpresaUsuario.getControl().getEmpresaTableBalancete();
				setEmpresa(usuarioEmpresa.getEmpresa());
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage() + " Cath tela balancete evento windowActivated VincularEmpresaUsuario");
		}
		try {
			atualizarJTableAction();
		} catch (Exception e2) {
			System.out.println(e2.getMessage() + " Cath tela balancete windowActivated atualizarJTableAction ");
		}
	}

	public Balancete cadastrarBalancete() {
		boolean confirma;
		System.out.println("=================RETORNO SPINER ANO:" + anoDataBase.getYear());
		if (validarCamposVazioBalancete()) {
			if (balancete == null) {

				balancete = new Balancete();
				balancete.setDataBalancoFim(dataFinal.getDate());
				balancete.setDataBalancoInicio(dataInicial.getDate());
				balancete.setDatabase(anoDataBase.getYear());
				balancete.setEmpresa(getEmpresa());
				System.out.println(
						"=====================================ID DO BALANCETE INSERIDO=" + balancete.getIdBalancete());
				confirma = balanBO.salvarOuAlterarBalancete(balancete);
				System.out.println(
						"=====================================ID DO BALANCETE INSERIDO=" + balancete.getIdBalancete());
				folhamestra = new FolhaMestra();
				UsuarioFolhaMestra usuarioFolhaMestra = new UsuarioFolhaMestra();

				folhamestra.setBalancete(balancete);
				folhamestra.setCheckboxRevisado(false);

				folhaMestraBO.salvarOuAlterarFolhamestra(folhamestra);
				usuarioFolhaMestra.setDataVinculo(new Date());
				usuarioFolhaMestra.setFolhaMestra(folhamestra);
				usuarioFolhaMestra.setUsuario(usuario);
				usuarioFolhaMestra.setDescricao("EXECUTOR");
				usuarioFolhaDAO.salvarOuAlterar(usuarioFolhaMestra);
				JOptionPane.showMessageDialog(null, "Salvei com Sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				balancete.setDataBalancoFim(dataFinal.getDate());
				balancete.setDataBalancoInicio(dataInicial.getDate());
				balancete.setDatabase(anoDataBase.getYear());
				balancete.setEmpresa(getEmpresa());

				confirma = balanBO.salvarOuAlterarBalancete(balancete);
				JOptionPane.showMessageDialog(null, "Alterei com Sucesso!", "Sucesso!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			return balancete;
		} else {
			return null;
		}
	}

	public Lancamento lancamentoSelecionadoTable() {
		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) tableLancamentos.getModel();
		int linha = tableLancamentos.getSelectedRow();
		if (linha >= 0) {
			return lancamentos.get(linha);
		} else {
			System.out
					.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela GerenciamentoEmpresa");
			return null;
		}
	}


	public void pesquisarAction() {
		System.out.println("balancete control entrou no pesquisar action");
		switch (cbPesquisa.getSelectedIndex()) {
		case 0:
			List<Lancamento> lancamentosRef = balancete.getLancamentos().stream()
					.filter((Lancamento p) -> p.getRefpt().equalsIgnoreCase(tfPesquisaLancamento.getText()))
					.collect(Collectors.toList());
			atualizarJTableAction(lancamentosRef);
			break;
		case 1:
			try {
				List<Lancamento> lancamentosDescricao = balancete.getLancamentos().stream()
						.filter((Lancamento p) -> p.getDescricao().equalsIgnoreCase(tfPesquisaLancamento.getText()))
						.collect(Collectors.toList());
				atualizarJTableAction(lancamentosDescricao);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				List<Lancamento> lancamentosNatureza = balancete.getLancamentos().stream()
						.filter((Lancamento p) -> p.getNaturezaConta().equalsIgnoreCase(tfPesquisaLancamento.getText()))
						.collect(Collectors.toList());
				atualizarJTableAction(lancamentosNatureza);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			try {
				List<Lancamento> lancamentosNivel = balancete.getLancamentos().stream()
						.filter((Lancamento p) -> p.getNivel().equals(Integer.parseInt(tfPesquisaLancamento.getText())))
						.collect(Collectors.toList());
				atualizarJTableAction(lancamentosNivel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;

		default:

			break;
		}
	}

	public void excluirLancamentoAction() {
		boolean excluido = false;
		Lancamento lancamentoSelecionado = lancamentoSelecionadoTable();
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir lançamento? ", "Confirma",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(confirma);

		if (confirma == 0) {
			excluido = lancamentoBO.excluirLancamento(lancamentoSelecionado);
			if (excluido) {
				JOptionPane.showMessageDialog(null, "Lançamento Excluido com sucesso!");
			}

			if (lancamentoSelecionado.equals(null)) {
				JOptionPane.showMessageDialog(null, "ERRO, Seleciona uma Lançamento!");
			}
		}
		atualizarJTableAction();
	}

	public void popularCamposLancamento(Lancamento pLancamento) {
		lancamento = pLancamento;
		txtRefPt.setText(lancamento.getRefpt());
		txtNivel.setText(lancamento.getNivel().toString());
		txtCEstruturado.setText(lancamento.getCodigoEstruturado());
		txtCodReduzido.setText(lancamento.getCodigoReduzido().toString());
		txtDescr.setText(lancamento.getDescricao());
		setarTextoTFcomMonetarioDocument(lancamento.getSaldoInicial().toString(), txtSaldoInicial);
		setarTextoTFcomMonetarioDocument(lancamento.getDebito().toString(), txtDebito);
		setarTextoTFcomMonetarioDocument(lancamento.getCredito().toString(), txtCredito);

		calcularCredFinal();
	}

	public void calcularCredFinal() {
		formatarTextFieldParaString();
		bDSaldoInicial = new BigDecimal("0.00");
		bDDebito = new BigDecimal("0.00");
		bDCredito = new BigDecimal("0.00");
		bDSaldoFinal = new BigDecimal("0.00");

		bDSaldoInicial = new BigDecimal(strSaldoInicial);
		bDDebito = new BigDecimal(strDebito);
		bDCredito = new BigDecimal(strCredito);
		bDSaldoFinal = (bDSaldoInicial.subtract(bDDebito)).add(bDCredito);

		lblResultadoSaldoFinal.setText(String.valueOf(decimalFormat.format(bDSaldoFinal)));
		lancamento.setSaldoFinal(bDSaldoFinal);
	}

	private void formatarTextFieldParaString() {
		strCredito = txtCredito.getText();
		strDebito = txtDebito.getText();
		strSaldoInicial = txtSaldoInicial.getText();

		strCredito = strCredito.replace(".", "");
		strCredito = strCredito.replace(",", ".");
		strDebito = strDebito.replace(".", "");
		strDebito = strDebito.replace(",", ".");
		strSaldoInicial = strSaldoInicial.replace(".", "");
		strSaldoInicial = strSaldoInicial.replace(",", ".");
	}

	private void setarTextoTFcomMonetarioDocument(String pValor, JTextField tf) {
		try {
			if (pValor.substring(pValor.length() - 2, pValor.length() - 1).equals(".")) {
				tf.setText(pValor.replace(".", ""));
			} else {
				tf.setText(pValor.replace(".", ""));
			}

		} catch (StringIndexOutOfBoundsException ex) {
			if (pValor.substring(pValor.length() - 2, pValor.length() - 1).equals(".")) {
				tf.setText(pValor.replace(".", ""));
			} else {
				tf.setText(pValor.replace(".", ""));
			}
		}
	}

	public void cadastrarLancamento() {

		if (balancete == null) {
			cadastrarBalancete();
		}

		if (validarCamposVazioLancamento()) {
			pegarValorDosCamposLancamento();
			lancamento.setBalancete(balancete);
			lancamentoBO.salvarOuAlterarLancamento(lancamento);
			limparCamposLancamento();
			atualizarJTableAction();
			lancamento = null;
		}
	}

	public void importarLancamento() {
		if (this.balancete == null) {
			this.balancete = cadastrarBalancete();
		}
		int returnVal = fc.showOpenDialog(telaBalancete);
		File file;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			// This is where a real application would open the file.
			System.out.println("Opening: " + file.getName() + ".");
		} else {
			System.out.println("Open command cancelled by user.");
			return;
		}
		System.out.println(
				"==============================================================================================================ID BALANCETE IMPORTAR_LANCAMENTO cONTROLLER BALANCETE ID_BALANCETE"
						+ this.balancete.getIdBalancete());
		importadorDePlanilha.importarLancamentos(this.balancete, file);
		CalculoAV.calcularTodosAv(balancete);
		balanBO.salvarOuAlterarBalancete(balancete);
	}

	private void pegarValorDosCamposLancamento() {
		if (lancamento == null) {
			lancamento = new Lancamento();
		}
		calcularCredFinal();
		lancamento.setRefpt(txtRefPt.getText());
		lancamento.setNivel(Integer.valueOf(txtNivel.getText()));
		lancamento.setCodigoEstruturado((txtCEstruturado.getText()));
		lancamento.setCodigoReduzido(Integer.valueOf(txtCodReduzido.getText()));
		lancamento.setCredito(bDCredito);
		lancamento.setDebito(bDDebito);
		lancamento.setDescricao(txtDescr.getText());
		lancamento.setBalancete(balancete);
		lancamento.setSaldoInicial(bDSaldoInicial);
		
		comboBoxNaturezaConta();
		lancamento = CalculoAH.calcularAHLancamento(lancamento);
	}

	private void comboBoxNaturezaConta() {

		switch (cbNatuConta.getSelectedIndex()) {
		case 0:
			lancamento.setNaturezaConta("Ativo");
			break;
		case 1:
			lancamento.setNaturezaConta("Passivo");
			break;
		}
	}

	public void atualizarJTableAction() {
		lancamentos = lancamentoBO.consultarLancamentos(balancete);
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tableLancamentos.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Lancamento pLancamento : lancamentos) {
			model.addRow(new Object[] { pLancamento.getRefpt(), pLancamento.getNaturezaConta(), pLancamento.getNivel(),
					pLancamento.getCodigoEstruturado(), pLancamento.getCodigoReduzido(), pLancamento.getDescricao(),
					decimalFormat.format(pLancamento.getSaldoInicial()), decimalFormat.format(pLancamento.getDebito()),
					decimalFormat.format(pLancamento.getCredito()),
					decimalFormat.format(pLancamento.getSaldoFinal()) });
		}
	}

	public void atualizarJTableAction(List<Lancamento> pListLancamento) {
		lancamentos = pListLancamento;
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tableLancamentos.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Lancamento pLancamento : lancamentos) {
			model.addRow(new Object[] { pLancamento.getRefpt(), pLancamento.getNaturezaConta(), pLancamento.getNivel(),
					pLancamento.getCodigoEstruturado(), pLancamento.getCodigoReduzido(), pLancamento.getDescricao(),
					decimalFormat.format(pLancamento.getSaldoInicial()), decimalFormat.format(pLancamento.getDebito()),
					decimalFormat.format(pLancamento.getCredito()),
					decimalFormat.format(pLancamento.getSaldoFinal()) });

		}
	}

	public void validarCamposNumericosCalculo(KeyEvent arg0, JTextField campoValidar) {

		if (arg0.getKeyCode() >= 96 && arg0.getKeyCode() <= 105 || arg0.getKeyCode() >= 48 && arg0.getKeyCode() <= 57
				|| arg0.getKeyCode() == 10 || arg0.getKeyCode() <= 40 && arg0.getKeyCode() >= 37
				|| arg0.getKeyCode() == 8 || arg0.getKeyCode() == 27 || arg0.getKeyCode() == 46
				|| arg0.getKeyCode() == 44) {

		} else {
			String modificada = campoValidar.getText().substring(0, campoValidar.getText().length() - 1);
			campoValidar.setText(modificada);
			JOptionPane.showMessageDialog(null, "Somente números", "ERRO", JOptionPane.WARNING_MESSAGE);
			System.out.println(
					"ENTROU NO ELSE DO EVENTO PRESSED TECLADO TELA cadastro balancete codigo key:" + arg0.getKeyCode());
		}
	}

	public void validarCamposSomenteNumeros(KeyEvent e, JTextField campoValidar) {

		if (e.getKeyCode() >= 96 && e.getKeyCode() <= 105 || e.getKeyCode() >= 48 && e.getKeyCode() <= 57
				|| e.getKeyCode() == 10 || e.getKeyCode() <= 40 && e.getKeyCode() >= 37 || e.getKeyCode() == 8
				|| e.getKeyCode() == 27) {

		} else {
			String modificada = campoValidar.getText().substring(0, campoValidar.getText().length() - 1);
			campoValidar.setText(modificada);
			JOptionPane.showMessageDialog(null, "Somente n�meros", "ERRO", JOptionPane.WARNING_MESSAGE);
			System.out.println(
					"ENTROU NO ELSE DO EVENTO PRESSED TECLADO TELA cadastro balancete codigo key:" + e.getKeyCode());
		}
	}

	private boolean validarCamposVazioBalancete() {
		if (empresa == null) {
			String mensagem = "Selecione uma Empresa!";
			JOptionPane.showMessageDialog(null, mensagem);
			btnEmpresa.doClick();
			return false;
		}
		if (dataFinal.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Selecione um periodo final", "ERRO", JOptionPane.WARNING_MESSAGE);
			dataFinal.requestFocusInWindow();
			return false;
		}
		if (dataInicial.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Selecione um periodo inicial", "ERRO", JOptionPane.WARNING_MESSAGE);
			dataInicial.requestFocusInWindow();
			return false;
		}

		return true;
	}

	private boolean validarCamposVazioLancamento() {
		if (txtRefPt.getText().equals("")) {
			txtRefPt.requestFocus();
			JOptionPane.showMessageDialog(null, "Campo REFPT n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtNivel.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo NIVEL n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtNivel.requestFocus();
			return false;
		}
		if (txtCodReduzido.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo C�digo Reduzido n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtCodReduzido.requestFocus();
			return false;
		}
		if (txtCEstruturado.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo C�digo Estruturado n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtCEstruturado.requestFocus();
			return false;
		}
		if (txtDescr.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo descri��o n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtDescr.requestFocus();
			return false;
		}
		if (txtSaldoInicial.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo saldo inicial n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtSaldoInicial.requestFocus();
			return false;
		}
		if (txtDebito.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo d�bito n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtDebito.requestFocus();
			return false;
		}
		if (txtCredito.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo cr�dito n�o pode estar vazio.", "ERRO",
					JOptionPane.WARNING_MESSAGE);
			txtCredito.requestFocus();
			return false;
		}
		return true;
	}

	public void limparCamposBalancete() {

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int ano = calendar.get(Calendar.YEAR);

		dataFinal.setDate(null);
		dataInicial.setDate(date);
		anoDataBase.setYear(ano);
		balancete = null;
		lancamentos = null;
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tableLancamentos.getModel();
		model.setNumRows(0);

	}

	public void limparCamposLancamento() {

		txtRefPt.setText("");
		txtNivel.setText("");
		txtCEstruturado.setText("");
		txtCodReduzido.setText("");
		txtDescr.setText("");
		txtSaldoInicial.setText("");
		txtCredito.setText("");
		txtDebito.setText("");
		lblResultadoSaldoFinal.setText("0,00");
		lancamento = null;

	}


	public void selectEmpresa() {
		setSetEmpresaFlag(true);
		telaVincularEmpresaUsuario.setVisible(true);
	}

	public ArrayList<Balancete> consultarTodosBalancetes() {
		return (ArrayList<Balancete>) balanBO.consultarTodosBalancetes();

	}

	public ArrayList<Balancete> consultarTodos() {
		return (ArrayList<Balancete>) balanBO.consultarTodosBalancetes();
	}

	public JTextField getTxtRefPt() {
		return txtRefPt;
	}

	public void setTxtRefPt(JTextField txtRefPt) {
		this.txtRefPt = txtRefPt;
	}

	public JTextField getTxtNivel() {
		return txtNivel;
	}

	public void setTxtNivel(JTextField txtNivel) {
		this.txtNivel = txtNivel;
	}

	public JTextField getTxtCEstruturado() {
		return txtCEstruturado;
	}

	public void setTxtCEstruturado(JTextField txtCEstruturado) {
		this.txtCEstruturado = txtCEstruturado;
	}

	public JTextField getTxtCodReduzido() {
		return txtCodReduzido;
	}

	public void setTxtCodReduzido(JTextField txtCodReduzido) {
		this.txtCodReduzido = txtCodReduzido;
	}

	public JTextField getTxtDescr() {
		return txtDescr;
	}

	public void setTxtDescr(JTextField txtDescr) {
		this.txtDescr = txtDescr;
	}

	public JTextField getTxtSaldoInicial() {
		return txtSaldoInicial;
	}

	public void setTxtSaldoInicial(JTextField txtSaldoInicial) {
		this.txtSaldoInicial = txtSaldoInicial;
	}

	public JTextField getTxtCredito() {
		return txtCredito;
	}

	public void setTxtCredito(JTextField txtCredito) {
		this.txtCredito = txtCredito;
	}

	public JTextField getTxtDebito() {
		return txtDebito;
	}

	public void setTxtDebito(JTextField txtDebito) {
		this.txtDebito = txtDebito;
	}

	public JLabel getLblNomeUsuario() {
		return lblNomeUsuario;
	}

	public void setLblNomeUsuario(JLabel lblNomeUsuario) {
		this.lblNomeUsuario = lblNomeUsuario;
	}

	public JLabel getLblNomeEmpresa() {
		return lblNomeEmpresa;
	}

	public void setLblNomeEmpresa(JLabel lblNomeEmpresa) {
		this.lblNomeEmpresa = lblNomeEmpresa;
	}

	public JDateChooser getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(JDateChooser dataFinal) {
		this.dataFinal = dataFinal;
	}

	public JDateChooser getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(JDateChooser dataInicial) {
		this.dataInicial = dataInicial;
	}

	public JComboBox getCbNatuConta() {
		return cbNatuConta;
	}

	public void setCbNatuConta(JComboBox cbNatuConta) {
		this.cbNatuConta = cbNatuConta;
	}

	public JTable getTableLancamentos() {
		return tableLancamentos;
	}

	public void setTableLancamentos(JTable tableLancamentos) {
		this.tableLancamentos = tableLancamentos;
	}

	public JTextField getTfPesquisaLancamento() {
		return tfPesquisaLancamento;
	}

	public void setTfPesquisaLancamento(JTextField tfPesquisaLancamento) {
		this.tfPesquisaLancamento = tfPesquisaLancamento;
	}

	public JComboBox getCbPesquisa() {
		return cbPesquisa;
	}

	public void setCbPesquisa(JComboBox cbPesquisa) {
		this.cbPesquisa = cbPesquisa;
	}

	public JLabel getLblResultadoSaldoFinal() {
		return lblResultadoSaldoFinal;
	}

	public void setLblResultadoSaldoFinal(JLabel lblResultadoSaldoFinal) {
		this.lblResultadoSaldoFinal = lblResultadoSaldoFinal;
	}

	public static BalanceteBO getBalanbo() {
		return balanBO;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
		if (empresa != null) {
			lblNomeEmpresa.setText(empresa.getNome());
		} else {
			lblNomeEmpresa.setText("");
		}
//		balancete = null;
	}

	public boolean isSetEmpresaFlag() {
		return setEmpresaFlag;
	}

	public void setSetEmpresaFlag(boolean setEmpresaFlag) {
		this.setEmpresaFlag = setEmpresaFlag;
	}

	public Balancete getBalancete() {
		return balancete;
	}

	public void setBalancete(Balancete balancete) {
		setEmpresa(balancete.getEmpresa());
		this.balancete = balancete;
		dataFinal.setDate(balancete.getDataBalancoFim());
		dataInicial.setDate(balancete.getDataBalancoInicio());
		anoDataBase.setYear(balancete.getDatabase());
	}
}
