package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerEmpresaParaUsuario;
import model.util.JTextFieldLimit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

public class TelaEmpresaParaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfPesquisaTelaAddEmpresaUsuario;
	private JTable tableEmpresaUsuario;
	private JComboBox cbPesquisaEmpresaTelaAddEmpresa;
	private JButton btnAddEmpresaUsuario;
	private ControllerEmpresaParaUsuario control;
	private JLabel lblPor;
	private JLabel lblPesquisar;
	private JScrollPane scrollPaneEmpresaUsuario;
	private JButton btnCancelar;
	private JLabel lblLogoTopo;
	private DefaultTableModel modelTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEmpresaParaUsuario frame = new TelaEmpresaParaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEmpresaParaUsuario() {
		setResizable(false);
		setMaximumSize(new Dimension(0, 0));
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TelaEmpresaParaUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				control = new ControllerEmpresaParaUsuario(tfPesquisaTelaAddEmpresaUsuario, tableEmpresaUsuario,
						cbPesquisaEmpresaTelaAddEmpresa);
				control.atualizarJTableAction();
			}
		});
		setTitle("Empresa Para Usu\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 497);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbPesquisaEmpresaTelaAddEmpresa = new JComboBox();
		cbPesquisaEmpresaTelaAddEmpresa.setBackground(Color.WHITE);
		cbPesquisaEmpresaTelaAddEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbPesquisaEmpresaTelaAddEmpresa
				.setModel(new DefaultComboBoxModel(new String[] { "Nome", "CNPJ", "Codigo", "Tipo", "Responsavel" }));
		cbPesquisaEmpresaTelaAddEmpresa.setBounds(382, 62, 130, 27);
		contentPane.add(cbPesquisaEmpresaTelaAddEmpresa);

		lblPor = new JLabel("Por");
		lblPor.setBounds(382, 37, 105, 14);
		lblPor.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPor);

		tfPesquisaTelaAddEmpresaUsuario = new JTextField();
		tfPesquisaTelaAddEmpresaUsuario.setDocument(new JTextFieldLimit(20));
		tfPesquisaTelaAddEmpresaUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				control.pesquisarAction();
				if (tfPesquisaTelaAddEmpresaUsuario.getText().equals("")) {
					control.atualizarJTableAction();
				}
			}
		});
		tfPesquisaTelaAddEmpresaUsuario.setBounds(10, 64, 352, 25);
		tfPesquisaTelaAddEmpresaUsuario.setColumns(10);
		contentPane.add(tfPesquisaTelaAddEmpresaUsuario);

		lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBounds(10, 39, 148, 14);
		lblPesquisar.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPesquisar);

		scrollPaneEmpresaUsuario = new JScrollPane();
		scrollPaneEmpresaUsuario.setBounds(10, 243, 577, 201);
		contentPane.add(scrollPaneEmpresaUsuario);

		
		modelTable = new DefaultTableModel(new Object[] {
				"C\u00F3digo", "Nome", "CNPJ", "Tipo", "Respons\u00E1vel" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		
		
		
		tableEmpresaUsuario = new JTable();
		tableEmpresaUsuario.setEnabled(true);
		tableEmpresaUsuario.setBackground(Color.WHITE);
		tableEmpresaUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPaneEmpresaUsuario.setViewportView(tableEmpresaUsuario);
		tableEmpresaUsuario.setModel(modelTable);
		btnAddEmpresaUsuario = new JButton("");
		btnAddEmpresaUsuario.setToolTipText("Clique e atribua empresa ao Usuário");
		btnAddEmpresaUsuario.setBackground(Color.WHITE);
		btnAddEmpresaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnAddEmpresaUsuario.setBounds(610, 316, 45, 46);
		btnAddEmpresaUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAddEmpresaUsuario.setIcon(
				new ImageIcon(TelaEmpresaParaUsuario.class.getResource("/icons/diversos/icons8-adicionar-propriedade-36.png")));
		contentPane.add(btnAddEmpresaUsuario);

		btnCancelar = new JButton("");
		btnCancelar.setToolTipText("clique para cancelar e fechar a janela");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(610, 389, 45, 46);
		btnCancelar.setIcon(
				new ImageIcon(TelaEmpresaParaUsuario.class.getResource("/icons/empresa/icons8-cancelar-32.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.atualizarJTableAction();
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnCancelar);

		lblLogoTopo = new JLabel("");
		lblLogoTopo.setBounds(592, 11, 96, 95);
		lblLogoTopo.setIcon(new ImageIcon(TelaEmpresaParaUsuario.class.getResource("/icons/vga/Logo VGA.jpg")));
		contentPane.add(lblLogoTopo);
	}

	public ControllerEmpresaParaUsuario getControl() {
		return control;
	}

	public void setControl(ControllerEmpresaParaUsuario control) {
		this.control = control;
	}

	public JTable getTableTelaAddEmpresaUsuario() {
		return tableEmpresaUsuario;
	}

	public JTextField getTfPesquisaTelaAddEmpresaUsuario() {
		return tfPesquisaTelaAddEmpresaUsuario;
	}

	public JComboBox getCbPesquisaEmpresaTelaAddEmpresa() {
		return cbPesquisaEmpresaTelaAddEmpresa;
	}

	public JButton getBtnAddEmpresaUsuario() {
		return btnAddEmpresaUsuario;
	}
}
