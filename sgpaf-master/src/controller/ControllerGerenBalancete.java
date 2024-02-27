package controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import model.boNovo.BalanceteBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.util.ConversorData;
import view.TelaVincularEmpresaUsuario;

public class ControllerGerenBalancete {

	private JTable tableBalancetes;
	private JLabel lblUsuario;
	private JLabel lblEmpresa;
	private JButton btnEmpresa;
	private NumberFormat decimalFormat2 = NumberFormat.getCurrencyInstance();
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	private List<Balancete> balancetes;
	private Usuario usuario;
	private Empresa empresa;
	private ControllerUsuario usuarioControl;
	private ConversorData conversorData = new ConversorData();
	private JDateChooser jDataInicial;
	private JDateChooser jDataFinal;
	private JYearChooser yearChooser;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private TelaVincularEmpresaUsuario telaVincularEmpresaUsuario = null;
	private static final BalanceteBO balanBO = new BalanceteBO();

	public ControllerGerenBalancete(JTable tableBalancetes, 
			JLabel lblUsuario, JLabel lblEmpresa, JButton btnEmpresa, Usuario usuario, JDateChooser jDataInicial,
			JDateChooser jDataFinal, JYearChooser yearChooser) {
		super();
		this.tableBalancetes = tableBalancetes;
		this.lblUsuario = lblUsuario;
		this.lblEmpresa = lblEmpresa;
		this.btnEmpresa = btnEmpresa;
		this.usuario = usuario;
		this.jDataFinal = jDataFinal;
		this.jDataInicial = jDataInicial;
		this.yearChooser = yearChooser;
		usuarioControl = new ControllerUsuario();

//		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
//		calendar.setTime(date);
		int ano = calendar.get(Calendar.YEAR);

		this.jDataFinal.setDate(null);
		this.jDataInicial.setDate(null);
		this.yearChooser.setYear(ano);
	}

	public ControllerGerenBalancete() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void atualizarJTableAction() {
//		balancetes = balanBO.consultarBalancetes(empresa);
		balancetes = balanBO.consultarBalancetes(empresa.getIdEmpresa());
		DefaultTableModel model = new DefaultTableModel();
		model = (DefaultTableModel) tableBalancetes.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Balancete pBalancete : balancetes) {
			model.addRow(new Object[] { pBalancete.getIdBalancete(), empresa.getNome(), pBalancete.getDatabase(),
					ConversorData.dataBancoParaUsuario(pBalancete.getDataBalancoInicio()),
					ConversorData.dataBancoParaUsuario(pBalancete.getDataBalancoFim()) });
		}
	}

	private void atualizarJTableAction(List<Balancete> pListBalancete) {
		balancetes = pListBalancete;
		DefaultTableModel model = (DefaultTableModel) tableBalancetes.getModel();
		// limpa a table e lista novamente
		model.setNumRows(0);
		for (Balancete pBalancete : balancetes) {
			model.addRow(new Object[] { pBalancete.getIdBalancete(), empresa.getNome(), pBalancete.getDatabase(),
					conversorData.dataBancoParaUsuario(pBalancete.getDataBalancoInicio()),
					conversorData.dataBancoParaUsuario(pBalancete.getDataBalancoFim()) });
		}
	}

	public Balancete getBalanceteSelecionadoTable() {
		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) tableBalancetes.getModel();
		int linha = tableBalancetes.getSelectedRow();
		if (linha >= 0) {
			return balancetes.get(linha);
		} else {
			System.out
					.println("ERRO!! ESTA RETORNANDO NULL NO getEmpresaSelecionadaTable Da tela GerenciamentoEmpresa");
			return null;
		}
	}

	public void excluirEmresaAction(Balancete pBalancete, Empresa pEmpresa) {
		boolean excluido = false;
		int confirma = JOptionPane.showConfirmDialog(null,
				"Tem certeza que deseja excluir balancete? \nEmpresa: " + pEmpresa.getNome() + "\nCNPJ: "
						+ pEmpresa.getCnpj() + "" + "\nData in�cio: " + pBalancete.getDataBalancoInicio()
						+ "\nData Fim: " + pBalancete.getDataBalancoFim(),
				"Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(confirma);

		if (confirma == 0) {
			excluido = balanBO.excluirBalancete(pBalancete);
			if (excluido) {
				JOptionPane.showMessageDialog(null, "Balancete Excluido com sucesso!");
			}

			if (pBalancete.equals(null)) {
				JOptionPane.showMessageDialog(null, "ERRO, Seleciona uma Empresa!");
			}
		}
		atualizarJTableAction();
	}

	public void pesquisarAction(int pesquisa) {
		balancetes = balanBO.consultarBalancetes(empresa.getIdEmpresa());
		System.out.println(" control gerenciador balancete entrou no pesquisar action");
		switch (pesquisa) {
		case 0:
			Integer dataBase = yearChooser.getYear();
			List<Balancete>balancetesDataBase = new ArrayList<Balancete>();
			
			for (Balancete balancete : balancetes) {
				if(balancete.getDatabase().equals(dataBase)) {
					balancetesDataBase.add(balancete);
				}
			}
			atualizarJTableAction(balancetesDataBase);
			break;

		case 1:
			Date dataInicio = jDataInicial.getDate();
			String dataString = dateFormat.format(dataInicio);
			List<Balancete> balancetesDataInicio = new ArrayList<Balancete>();
			for (Balancete balancete : balancetes) {
				String dataBalancete = balancete.getDataBalancoInicio().toString().replaceAll("00:00:00.0", "");
				dataBalancete = dataBalancete.replaceAll(" ", "");
				if (dataBalancete.equals(dataString)) {
					balancetesDataInicio.add(balancete);
				}
			}
			atualizarJTableAction(balancetesDataInicio);
			break;
		case 2:
			Date dataFim = jDataFinal.getDate();
			String dataStringFim = dateFormat.format(dataFim);
			List<Balancete> balancetesDataFim = new ArrayList<Balancete>();
			for (Balancete balancete : balancetes) {
				String dataBalancete = balancete.getDataBalancoFim().toString().replaceAll("00:00:00.0", "");
				dataBalancete = dataBalancete.replaceAll(" ", "");
				if (dataBalancete.equals(dataStringFim)) {
					balancetesDataFim.add(balancete);
				}
			}
			atualizarJTableAction(balancetesDataFim);
			break;
		default:
			break;
		}
	}

	private void verificarEmpresaNull() {
		if (empresa == null) {

			String mensagem = "Selecione uma Empresa!";
			JOptionPane.showMessageDialog(null, mensagem);
			btnEmpresa.doClick();
			return;
		}
	}

	public JTable getTableBalancetes() {
		return tableBalancetes;
	}

	public void setTableBalancetes(JTable tableBalancetes) {
		this.tableBalancetes = tableBalancetes;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblEmpresa() {
		return lblEmpresa;
	}

	public void setLblEmpresa(JLabel lblEmpresa) {
		this.lblEmpresa = lblEmpresa;
	}

	public JButton getBtnEmpresa() {
		return btnEmpresa;
	}

	public void setBtnEmpresa(JButton btnEmpresa) {
		this.btnEmpresa = btnEmpresa;
	}

	public List<Balancete> getBalancetes() {
		return balancetes;
	}

	public void setBalancetes(List<Balancete> balancetes) {
		this.balancetes = balancetes;
	}

	public static BalanceteBO getBalanbo() {
		return balanBO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ControllerUsuario getUsuarioControl() {
		return usuarioControl;
	}

	public void setUsuarioControl(ControllerUsuario usuarioControl) {
		this.usuarioControl = usuarioControl;
	}
}
