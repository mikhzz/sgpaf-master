package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import controller.ControllerRecomendacao;
import model.daoNovo.db.Lancamento;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaRecomendacoes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Lancamento lancamento;
	private JCheckBox chckbxDivergencias;
	private JTextArea txtAreaRecomendacoes;
	private ControllerRecomendacao controlRecomendacao;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaRecomendacoes dialog = new TelaRecomendacoes(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaRecomendacoes(Lancamento pLancamento) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				controlRecomendacao.popularTela();
			}
		});
		this.lancamento = pLancamento;
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaRecomendacoes.class.getResource("/icons/vga/Logo VGA.jpg")));
		setTitle("Recomendações");
		setResizable(false);
		setBounds(100, 100, 771, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRecomendações = new JLabel("                                                                           Recomendações");
			lblRecomendações.setBorder(new LineBorder(Color.BLACK));
			
			lblRecomendações.setBounds(90, 11, 562, 24);
			lblRecomendações.setOpaque(true);
			
			contentPanel.add(lblRecomendações);
		}
		
		JLabel lblRecomendacoes = new JLabel("Digite as Recomendações:");
		lblRecomendacoes.setBounds(87, 35, 167, 48);
		contentPanel.add(lblRecomendacoes);
		
		JScrollPane scrollPaneRecomendacoes = new JScrollPane();
		scrollPaneRecomendacoes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRecomendacoes.setBackground(Color.WHITE);
		scrollPaneRecomendacoes.setBounds(90, 94, 562, 183);
		contentPanel.add(scrollPaneRecomendacoes);
		
		txtAreaRecomendacoes = new JTextArea();
		txtAreaRecomendacoes.setBackground(Color.WHITE);
		scrollPaneRecomendacoes.setViewportView(txtAreaRecomendacoes);
		
		chckbxDivergencias = new JCheckBox("Há Divergências");
		chckbxDivergencias.setToolTipText("Selecione se houver divergência na conta");
		chckbxDivergencias.setBackground(Color.WHITE);
		chckbxDivergencias.setBounds(518, 48, 120, 23);
		contentPanel.add(chckbxDivergencias);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						controlRecomendacao.salvarAction();
						hide();
					}
				});
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						hide();
						
					}
					
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		controlRecomendacao = new ControllerRecomendacao(chckbxDivergencias, txtAreaRecomendacoes, lancamento);
	}
	public JCheckBox getChckbxDivergencias() {
		return chckbxDivergencias;
	}
	public JTextArea getTxtAreaRecomendacoes() {
		return txtAreaRecomendacoes;
	}
}
