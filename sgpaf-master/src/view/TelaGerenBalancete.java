package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;

import controller.ControllerGerenBalancete;
import controller.ControllerUsuario;
import model.boNovo.EmpresaBO;
import model.daoNovo.db.Balancete;
import model.daoNovo.db.Empresa;
import model.daoNovo.db.Usuario;
import model.daoNovo.db.UsuarioEmpresa;

public class TelaGerenBalancete extends JFrame {

	private Usuario usuario;
	private Empresa empresa;
	private UsuarioEmpresa usuarioEmpresa;
	private EmpresaBO empresaBO;
	private JPanel contentPane;
	private final JSeparator separator = new JSeparator();
	private JTable tableBalancetes;
	private JLabel lblUsuario;
	private JLabel lblEmpresa;
	private TelaVincularEmpresaUsuario telaVincularEmpresaUsuario = null;
	private ControllerGerenBalancete control;
	private ControllerUsuario usuarioControl;
	private JButton btnEmpresa;
	private Balancete balaceteSelecionado = null;
	private TelaFolhaMestra1 telaFolhaMestra;
	private TelaMaterialidade telaMaterialidade;
	private JButton btnEditarBalancete;
	private JDateChooser JdataInicial;
	private JDateChooser JdataFinal;
	private JYearChooser yearChooser;
	private DefaultTableModel modelTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenBalancete frame = new TelaGerenBalancete(new Usuario());
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
	public TelaGerenBalancete(Usuario pUsuario) {
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(800, 600));
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaGerenBalancete.class.getResource("/icons/vga/Logo VGA.jpg")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					usuarioEmpresa = telaVincularEmpresaUsuario.getControl().getEmpresaTableBalancete();
					empresa = usuarioEmpresa.getEmpresa();
					lblEmpresa.setText(empresa.getNome());
					control.setEmpresa(empresa);
					control.atualizarJTableAction();
				} catch (Exception e2) {
					System.out.println(e2.getStackTrace() + " " + e2.getCause() + " " + e2.getMessage()
							+ " Cath tela gerenciamento balancete evento windowActivated ");
				}
			}
		});
		this.usuario = pUsuario;
		setTitle("Gerenciador de Balancete");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1101, 630);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(10, 21, 199, 14);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		lblUsuario = new JLabel(usuario.getNome());
		lblUsuario.setBounds(10, 40, 214, 21);
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblUsuario);

		lblEmpresa = new JLabel();
		lblEmpresa.setBounds(10, 86, 279, 21);
		lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblEmpresa);

		JLabel labelEmpresa = new JLabel("Empresa");
		labelEmpresa.setBounds(10, 72, 214, 14);
		labelEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(labelEmpresa);
		separator.setBounds(-380, 123, 1465, 14);
		contentPane.add(separator);

		btnEmpresa = new JButton("");
		btnEmpresa.setToolTipText("Trocar Empresa");
		btnEmpresa.setBounds(391, 57, 55, 55);
		btnEmpresa.setBackground(Color.WHITE);
		btnEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				control.pegarEmpresa();
//				Perfil perfilTest =  usuarioControl.getPerfil(usuario);
				if (usuario != null && usuario.getPerfil().getIdPerfil() == 2) {
					telaVincularEmpresaUsuario = new TelaVincularEmpresaUsuario(usuario);
					telaVincularEmpresaUsuario.getBtnADDEmpresa().setVisible(false);
					telaVincularEmpresaUsuario.getBtnTrocaUsuario().setVisible(false);
					telaVincularEmpresaUsuario.getBtnExluirEmpresa().setVisible(false);

					telaVincularEmpresaUsuario.show(); // ou setVisible(true);
				} else if (usuario != null) {
					telaVincularEmpresaUsuario = new TelaVincularEmpresaUsuario(usuario);
					telaVincularEmpresaUsuario.getBtnADDEmpresa().setVisible(false);
					telaVincularEmpresaUsuario.getBtnExluirEmpresa().setVisible(false);
					telaVincularEmpresaUsuario.getBtnTrocaUsuario().setVisible(false);
					telaVincularEmpresaUsuario.show(); // ou setVisible(true);
				}
			}
		});
		btnEmpresa.setIcon(new ImageIcon(
				TelaGerenBalancete.class.getResource("/icons/empresa/icons8-empresas-relacionadas-50.png")));
		contentPane.add(btnEmpresa);

		JLabel label_1 = new JLabel("Pesquisar:");
		label_1.setBounds(10, 135, 69, 21);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 234, 861, 346);
		contentPane.add(scrollPane);

		modelTable = new DefaultTableModel(new Object[] {
				"C\u00F3digo", "Empresa", "Data Base", "Data In\u00EDcio", "Data Fim" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		
		
		tableBalancetes = new JTable();
		tableBalancetes.setEnabled(true);
		tableBalancetes.setModel(modelTable); 
		tableBalancetes.setBackground(Color.WHITE);
		tableBalancetes.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setViewportView(tableBalancetes);
		;
		tableBalancetes.getColumnModel().getColumn(0).setPreferredWidth(36);
		tableBalancetes.getColumnModel().getColumn(1).setPreferredWidth(201);
		tableBalancetes.getColumnModel().getColumn(2).setPreferredWidth(54);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setToolTipText("");
		btnExcluir.setBounds(898, 534, 141, 30);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				balaceteSelecionado = control.getBalanceteSelecionadoTable();
				if (balaceteSelecionado != null) {
					control.excluirEmresaAction(balaceteSelecionado, empresa);
				} else {
					balaceteSelecionado = null;
					control.atualizarJTableAction();
					JOptionPane.showMessageDialog(null, "Selecione um balancete", "Ops!",
							JOptionPane.INFORMATION_MESSAGE);
				}
				balaceteSelecionado = null;
				control.atualizarJTableAction();

			}
		});
		btnExcluir.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/empresa/icons8-excluir-32.png")));
		btnExcluir.setBackground(Color.WHITE);
		contentPane.add(btnExcluir);

		JButton btnAddBalancete = new JButton("");
		btnAddBalancete.setToolTipText("Gerar Folha Mestra");
		btnAddBalancete.setBounds(898, 234, 45, 46);
		btnAddBalancete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				balaceteSelecionado = control.getBalanceteSelecionadoTable();

				if (balaceteSelecionado != null) {
					if(balaceteSelecionado.getLancamentos().size()>0) {
						telaFolhaMestra = new TelaFolhaMestra1(empresa, balaceteSelecionado, usuario);
						telaFolhaMestra.getControlFolhaMestra().popularFolhaMestra();
						telaFolhaMestra.show();
					}else {
						JOptionPane.showMessageDialog(null, "Balancete sem contas, adicione contas para abrir Folha Mestra");
						TelaBalancete telaBalancete = TelaBalancete.GenerateTelaBalancete(usuario, balaceteSelecionado);
						telaBalancete.setVisible(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um balancete!");
				}

			}
		});
		btnAddBalancete.setIcon(new ImageIcon(TelaGerenBalancete.class
				.getResource("/icons/empresa/icons8-adicionar-o-relat\u00F3rio-gr\u00E1fico-32.png")));
		btnAddBalancete.setBackground(Color.WHITE);
		contentPane.add(btnAddBalancete);

		JLabel label = new JLabel("");
		label.setBounds(863, 11, 101, 92);
		label.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/vga/Logo VGA.jpg")));
		contentPane.add(label);
		usuarioControl = new ControllerUsuario();
		telaVincularEmpresaUsuario = new TelaVincularEmpresaUsuario(usuario);
		empresaBO = new EmpresaBO();

		btnEditarBalancete = new JButton("");
		btnEditarBalancete.setToolTipText("selecione um balancete na lista e clique ");
		btnEditarBalancete.setBounds(898, 285, 45, 46);
		btnEditarBalancete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balaceteSelecionado = control.getBalanceteSelecionadoTable();
				if (balaceteSelecionado != null) {
					TelaBalancete telaBalancete = TelaBalancete.GenerateTelaBalancete(usuario, balaceteSelecionado);
					telaBalancete.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um balancete!");
				}

			}
		});
		btnEditarBalancete
				.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/empresa/editarBalancete.png")));
		btnEditarBalancete.setBackground(Color.WHITE);
		contentPane.add(btnEditarBalancete);

		JdataInicial = new JDateChooser();
		JdataInicial.setToolTipText("Digite uma Data de Inicio");
		JdataInicial.setBounds(147, 184, 118, 20);
		contentPane.add(JdataInicial);

		JdataFinal = new JDateChooser();
		JdataFinal.setToolTipText("Digite uma data final");
		JdataFinal.setBounds(368, 184, 118, 20);
		contentPane.add(JdataFinal);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(10, 184, 47, 20);
		contentPane.add(yearChooser);
		control = new ControllerGerenBalancete(tableBalancetes, lblUsuario, lblEmpresa, btnEmpresa, usuario,
				JdataInicial, JdataFinal, yearChooser);

		JLabel label_1_1 = new JLabel("Data Base");
		label_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1_1.setBounds(10, 165, 69, 21);
		contentPane.add(label_1_1);

		JLabel label_1_1_1 = new JLabel("Data Início");
		label_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1_1_1.setBounds(147, 166, 69, 21);
		contentPane.add(label_1_1_1);

		JLabel label_1_1_1_1 = new JLabel("Data Fim");
		label_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1_1_1_1.setBounds(368, 166, 69, 21);
		contentPane.add(label_1_1_1_1);

		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Escolha uma Data Base para pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.pesquisarAction(0);
			}
		});
		btnNewButton.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/procurar.png")));
		btnNewButton.setBounds(67, 183, 28, 21);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Digite uma Data de inicio para pesquisar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.pesquisarAction(1);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/procurar.png")));
		btnNewButton_1.setBounds(275, 183, 28, 21);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Digita uma data final e clique em pesquisar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.pesquisarAction(2);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/procurar.png")));
		btnNewButton_2.setBounds(496, 183, 28, 21);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.setToolTipText("Pesquisar Balancetes da Empresa");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.atualizarJTableAction();
			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/refrescar.png")));
		btnNewButton_2_1.setBounds(77, 135, 28, 21);
		contentPane.add(btnNewButton_2_1);

		JButton btnMaterialidade = new JButton("");
		btnMaterialidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balaceteSelecionado = control.getBalanceteSelecionadoTable();

				if (balaceteSelecionado != null) {
					if(balaceteSelecionado.getLancamentos().size()>0) {
						telaMaterialidade = new TelaMaterialidade(balaceteSelecionado);
//						telaMaterialidade.getControl().popular();
						telaMaterialidade.show();
					}else {
						JOptionPane.showMessageDialog(null, "Balancete sem contas, adicione contas para abrir Folha Mestra");
						TelaBalancete telaBalancete = TelaBalancete.GenerateTelaBalancete(usuario, balaceteSelecionado);
						telaBalancete.setVisible(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um balancete!");
				}
			}
		});
		btnMaterialidade
				.setIcon(new ImageIcon(TelaGerenBalancete.class.getResource("/icons/icons8-razão-geral-32 (1).png")));
		btnMaterialidade.setToolTipText("Gerar Materialidade");
		btnMaterialidade.setBackground(Color.WHITE);
		btnMaterialidade.setBounds(898, 342, 45, 46);
		contentPane.add(btnMaterialidade);

	}

	public JTable getTableBalancetes() {
		return tableBalancetes;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JLabel getLblEmpresa() {
		return lblEmpresa;
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

	public JButton getBtnEmpresa() {
		return btnEmpresa;
	}

	public JDateChooser getJdataInicial() {
		return JdataInicial;
	}

	public JDateChooser getJdataFinal() {
		return JdataFinal;
	}
}
