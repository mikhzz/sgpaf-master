package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ControllerADDUsuarioParaEmpresa;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class TelaUsuarioParaEmpresa extends JFrame {

	private JPanel contentPane;
	private JTextField tfPesquiUsarioEmpresa;
	private JComboBox cbPesquisaUsuarioEmpresa;
	private JTable tableUsuarioEmpresa;
	private ControllerADDUsuarioParaEmpresa control;
	private JLabel lblPor;
	private JLabel lblPesquisar;
	private JButton btnVincularUsuarioEmpresa;
	private JButton btnCancelUsuarioEmpresa;
	private JLabel lblLogoTopo;
	private JScrollPane scrollPaneUsuarioEmpresa;
	private DefaultTableModel modelTable;

	public ControllerADDUsuarioParaEmpresa getControl() {
		return control;
	}

	public void setControl(ControllerADDUsuarioParaEmpresa control) {
		this.control = control;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarioParaEmpresa frame = new TelaUsuarioParaEmpresa();
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
	public TelaUsuarioParaEmpresa() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaUsuarioParaEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
		setTitle("Escolher Usu\u00E1rio Para V\u00EDnculo Com Empresa");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				control = new ControllerADDUsuarioParaEmpresa(tfPesquiUsarioEmpresa,cbPesquisaUsuarioEmpresa,tableUsuarioEmpresa);
				control.atualizarJTableAction();
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 373);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbPesquisaUsuarioEmpresa = new JComboBox();
		cbPesquisaUsuarioEmpresa.setBackground(Color.WHITE);
		cbPesquisaUsuarioEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbPesquisaUsuarioEmpresa.setModel(new DefaultComboBoxModel(new String[] {"Nome", "CPF", "Cargo", "C\u00F3digo"}));
		cbPesquisaUsuarioEmpresa.setBounds(382, 62, 130, 27);
		contentPane.add(cbPesquisaUsuarioEmpresa);
		
		lblPor = new JLabel("Por");
		lblPor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPor.setBounds(382, 37, 105, 14);
		contentPane.add(lblPor);
		
		tfPesquiUsarioEmpresa = new JTextField();
		tfPesquiUsarioEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		tfPesquiUsarioEmpresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				control.pesquisarAction();
				
				if(tfPesquiUsarioEmpresa.getText().equals("")){
					control.atualizarJTableAction();
				}
			}
		});
		tfPesquiUsarioEmpresa.setColumns(10);
		tfPesquiUsarioEmpresa.setBounds(10, 64, 352, 25);
		contentPane.add(tfPesquiUsarioEmpresa);
		
		lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPesquisar.setBounds(10, 39, 148, 14);
		contentPane.add(lblPesquisar);
		
		btnVincularUsuarioEmpresa = new JButton("");
		btnVincularUsuarioEmpresa.setBackground(Color.WHITE);
		btnVincularUsuarioEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVincularUsuarioEmpresa.setIcon(new ImageIcon(TelaUsuarioParaEmpresa.class.getResource("/icons/empresa/icons8-adicionar-a-lista-32.png")));
		btnVincularUsuarioEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVincularUsuarioEmpresa.setBounds(543, 202, 45, 46);
		contentPane.add(btnVincularUsuarioEmpresa);
		
		btnCancelUsuarioEmpresa = new JButton("");
		btnCancelUsuarioEmpresa.setBackground(Color.WHITE);
		btnCancelUsuarioEmpresa.setIcon(new ImageIcon(TelaUsuarioParaEmpresa.class.getResource("/icons/empresa/icons8-cancelar-32.png")));
		btnCancelUsuarioEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.atualizarJTableAction();
				dispose();
			}
		});
		btnCancelUsuarioEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelUsuarioEmpresa.setBounds(543, 268, 45, 46);
		contentPane.add(btnCancelUsuarioEmpresa);
		
		lblLogoTopo = new JLabel("");
		lblLogoTopo.setIcon(new ImageIcon(TelaUsuarioParaEmpresa.class.getResource("/icons/vga/Logo VGA.jpg")));
		lblLogoTopo.setBounds(561, 11, 96, 95);
		contentPane.add(lblLogoTopo);
		
		scrollPaneUsuarioEmpresa = new JScrollPane();
		scrollPaneUsuarioEmpresa.setBackground(Color.WHITE);
		scrollPaneUsuarioEmpresa.setBounds(10, 131, 504, 192);
		contentPane.add(scrollPaneUsuarioEmpresa);
		
		modelTable = new DefaultTableModel(new Object[] {
				"C\u00F3digo", "Nome", "CPF", "Cargo" }, 0) {
			 /**
					 * 
					 */
		     private static final long serialVersionUID = 1L;

			@Override
			 public boolean isCellEditable(int rowIndex, int mColIndex){  
			 return false;  
			 }
			 };
		
		
		
		tableUsuarioEmpresa = new JTable();
		tableUsuarioEmpresa.setBackground(Color.WHITE);
		tableUsuarioEmpresa.setEnabled(true);
		tableUsuarioEmpresa.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPaneUsuarioEmpresa.setViewportView(tableUsuarioEmpresa);
		tableUsuarioEmpresa.setModel(modelTable);
		tableUsuarioEmpresa.getColumnModel().getColumn(0).setPreferredWidth(49);
		tableUsuarioEmpresa.getColumnModel().getColumn(1).setPreferredWidth(345);
		tableUsuarioEmpresa.getColumnModel().getColumn(2).setPreferredWidth(123);
	}
	public JTextField getTfPesquiUsarioEmpresa() {
		return tfPesquiUsarioEmpresa;
	}
	public JComboBox getCbPesquisaUsuarioEmpresa() {
		return cbPesquisaUsuarioEmpresa;
	}
	public JTable getTableUsuarioEmpresa() {
		return tableUsuarioEmpresa;
	}
}
