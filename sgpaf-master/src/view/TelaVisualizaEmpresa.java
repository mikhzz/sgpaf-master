package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerEmpresa;
import model.daoNovo.db.Empresa;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaVisualizaEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNomeTelaVisualEmp;
	private JTextField tfEnderecoTelaVisualEmp;
	private JTextField tfTipoTelaVisualEmp;
	private JTextField tfEmailTelaVisualEmp;
	private JTextField tfResponsavelTelaVisualEmp;
	private JTextField tfCodigoTelaVisualEmp;
	private JFormattedTextField tfCnpjTelaVisualEmp;
	private JFormattedTextField tfTelefoneTelaVisualEmp;
	private ControllerEmpresa controllerEmpresaTelaVisualizaEmpr;
	private Empresa empresaVisual = null;
	private JLabel lblNome;
	private JLabel lblCnpj;
	private MaskFormatter mascaraCnpj;
	private JLabel lblEndereco;
	private JLabel lblTipo;
	private JLabel lblEmail;
	private JLabel lblResponsvel;
	private JLabel lblTelefone;
	private MaskFormatter mascaraTelefone;
	private JLabel lblCdigoDaEmpresa;
	private JButton okButton;
	private JLabel lblLogoTopo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaVisualizaEmpresa dialog = new TelaVisualizaEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaVisualizaEmpresa() {
		setResizable(false);
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setTitle("Consulta Empresa");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaVisualizaEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
		setBounds(100, 100, 578, 568);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNome = new JLabel("Nome:");
			lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNome.setBounds(20, 80, 58, 20);
			contentPanel.add(lblNome);
		}
		{
			tfNomeTelaVisualEmp = new JTextField();
			tfNomeTelaVisualEmp.setBackground(Color.WHITE);
			tfNomeTelaVisualEmp.setEditable(false);
			tfNomeTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfNomeTelaVisualEmp.setBounds(20, 100, 407, 25);
			contentPanel.add(tfNomeTelaVisualEmp);
			tfNomeTelaVisualEmp.setColumns(10);
		}
		{
			lblCnpj = new JLabel("Cnpj:");
			lblCnpj.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblCnpj.setBounds(20, 147, 58, 20);
			contentPanel.add(lblCnpj);
		}

		try {
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			tfCnpjTelaVisualEmp = new JFormattedTextField(mascaraCnpj);
			tfCnpjTelaVisualEmp.setBackground(Color.WHITE);
			tfCnpjTelaVisualEmp.setEditable(false);
			tfCnpjTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
		} catch (ParseException e) {
		}

		tfCnpjTelaVisualEmp.setBounds(20, 170, 117, 25);
		contentPanel.add(tfCnpjTelaVisualEmp);

		{
			lblEndereco = new JLabel("Endere\u00E7o:");
			lblEndereco.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblEndereco.setBounds(20, 288, 79, 20);
			contentPanel.add(lblEndereco);
		}
		{
			tfEnderecoTelaVisualEmp = new JTextField();
			tfEnderecoTelaVisualEmp.setBackground(Color.WHITE);
			tfEnderecoTelaVisualEmp.setEditable(false);
			tfEnderecoTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfEnderecoTelaVisualEmp.setBounds(20, 312, 407, 25);
			contentPanel.add(tfEnderecoTelaVisualEmp);
			tfEnderecoTelaVisualEmp.setColumns(10);
		}
		{
			lblTipo = new JLabel("Tipo:");
			lblTipo.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTipo.setBounds(196, 357, 58, 26);
			contentPanel.add(lblTipo);
		}
		{
			tfTipoTelaVisualEmp = new JTextField();
			tfTipoTelaVisualEmp.setBackground(Color.WHITE);
			tfTipoTelaVisualEmp.setEditable(false);
			tfTipoTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfTipoTelaVisualEmp.setBounds(196, 386, 231, 25);
			contentPanel.add(tfTipoTelaVisualEmp);
			tfTipoTelaVisualEmp.setColumns(10);
		}
		{
			lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblEmail.setBounds(147, 147, 58, 26);
			contentPanel.add(lblEmail);
		}
		{
			tfEmailTelaVisualEmp = new JTextField();
			tfEmailTelaVisualEmp.setBackground(Color.WHITE);
			tfEmailTelaVisualEmp.setEditable(false);
			tfEmailTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfEmailTelaVisualEmp.setBounds(147, 170, 280, 24);
			contentPanel.add(tfEmailTelaVisualEmp);
			tfEmailTelaVisualEmp.setColumns(10);
		}
		{
			lblResponsvel = new JLabel("Respons\u00E1vel:");
			lblResponsvel.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblResponsvel.setBounds(20, 217, 106, 20);
			contentPanel.add(lblResponsvel);
		}
		{
			tfResponsavelTelaVisualEmp = new JTextField();
			tfResponsavelTelaVisualEmp.setBackground(Color.WHITE);
			tfResponsavelTelaVisualEmp.setEditable(false);
			tfResponsavelTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfResponsavelTelaVisualEmp.setBounds(20, 241, 407, 25);
			contentPanel.add(tfResponsavelTelaVisualEmp);
			tfResponsavelTelaVisualEmp.setColumns(10);
		}
		{
			lblTelefone = new JLabel("Telefone:");
			lblTelefone.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTelefone.setBounds(20, 360, 92, 20);
			contentPanel.add(lblTelefone);

			try {
				mascaraTelefone = new MaskFormatter("(##)####-####");
				tfTelefoneTelaVisualEmp = new JFormattedTextField(mascaraTelefone);
				tfTelefoneTelaVisualEmp.setBackground(Color.WHITE);
				tfTelefoneTelaVisualEmp.setEditable(false);
				tfTelefoneTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			} catch (ParseException e) {
			}
			{

				tfTelefoneTelaVisualEmp.setBounds(20, 386, 166, 25);
				contentPanel.add(tfTelefoneTelaVisualEmp);
			}

		}
		{
			lblCdigoDaEmpresa = new JLabel("C\u00F3digo");
			lblCdigoDaEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblCdigoDaEmpresa.setBounds(20, 11, 63, 20);
			contentPanel.add(lblCdigoDaEmpresa);
		}
		{
			tfCodigoTelaVisualEmp = new JTextField();
			tfCodigoTelaVisualEmp.setBackground(Color.WHITE);
			tfCodigoTelaVisualEmp.setEditable(false);
			tfCodigoTelaVisualEmp.setFont(new Font("Calibri", Font.PLAIN, 14));
			tfCodigoTelaVisualEmp.setBounds(20, 33, 86, 26);
			contentPanel.add(tfCodigoTelaVisualEmp);
			tfCodigoTelaVisualEmp.setColumns(10);
		}

		{
			okButton = new JButton("OK");
			okButton.setBackground(Color.WHITE);
			okButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
			okButton.setBounds(304, 477, 123, 38);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hide();
//					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			lblLogoTopo = new JLabel("");
			lblLogoTopo.setIcon(new ImageIcon(TelaVisualizaEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
			lblLogoTopo.setBounds(460, 11, 96, 95);
			contentPanel.add(lblLogoTopo);
		}

	}

	protected void limparTela() {

		tfNomeTelaVisualEmp.setText("");
		tfCnpjTelaVisualEmp.setText("");
		tfEnderecoTelaVisualEmp.setText("");
		tfEmailTelaVisualEmp.setText("");
		tfResponsavelTelaVisualEmp.setText("");
		tfTelefoneTelaVisualEmp.setText("");
		tfTipoTelaVisualEmp.setText("");

	}

	public ControllerEmpresa getControllerEmpresaTelaVisualizaEmpr() {

		if (controllerEmpresaTelaVisualizaEmpr != null) {
			return controllerEmpresaTelaVisualizaEmpr;
		} else {
			controllerEmpresaTelaVisualizaEmpr = new ControllerEmpresa();
			return controllerEmpresaTelaVisualizaEmpr;
		}
	}

	public void setControllerEmpresaTelaVisualizaEmpr(ControllerEmpresa controllerEmpresaTelaVisualizaEmpr) {
		this.controllerEmpresaTelaVisualizaEmpr = controllerEmpresaTelaVisualizaEmpr;
	}

	public JTextField getTfCodigoTelaVisualEmp() {
		return tfCodigoTelaVisualEmp;
	}

	public JTextField getTfNomeTelaVisualEmp() {
		return tfNomeTelaVisualEmp;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getTfEmailTelaVisualEmp() {
		return tfEmailTelaVisualEmp;
	}

	public JTextField getTfResponsavelTelaVisualEmp() {
		return tfResponsavelTelaVisualEmp;
	}

	public JTextField getTfEnderecoTelaVisualEmp() {
		return tfEnderecoTelaVisualEmp;
	}

	public JFormattedTextField getTfTelefoneTelaVisualEmp() {
		return tfTelefoneTelaVisualEmp;
	}

	public JTextField getTfTipoTelaVisualEmp() {
		return tfTipoTelaVisualEmp;
	}

	public JFormattedTextField getTfCnpjTelaVisualEmp() {
		return tfCnpjTelaVisualEmp;
	}

	public Empresa getEmpresaVisual() {
		return empresaVisual;
	}

	public void setEmpresaVisual(Empresa empresaVisual) {
		this.empresaVisual = empresaVisual;
	}

}
