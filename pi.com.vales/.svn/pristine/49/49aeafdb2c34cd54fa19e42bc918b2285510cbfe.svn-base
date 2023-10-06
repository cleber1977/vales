package Interface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import Controle.EmpresaCtrl;
import Controle.FuncionarioCtrl;
import Controle.HistoricoCtrl;
import Controle.RelatorioCtrl;
import Persistencia.EstCampo;
import funcoes.funcaoData;


public class Relatorio extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane abasRel;

	private JLabel lEmpresa = new JLabel ("Empresa");
	private JLabel lFuncRel = new JLabel ("Funcionario");
	private JLabel lHistorico = new JLabel ("Historico");
	private JLabel lDataEmissao = new JLabel("Emitidos em:");
	private JLabel lDataFinalEmissao = new JLabel("a");
	private JLabel lDataPagame = new JLabel("Pagos em:");
	private JLabel lDataFinalPagame = new JLabel("a");
	private JLabel lDataVencimento = new JLabel("Vencendo em:");
	private JLabel lDataFinalVenc = new JLabel("a");
	private JLabel lBaixados = new JLabel("Som. Baixados");
	private JLabel lPeriodoExt = new JLabel("Periodo");
	private JLabel lPeriodoAteExt = new JLabel("Ate");


	private JFormattedTextField tDataInicio = new JFormattedTextField();
	private JFormattedTextField tDataFinal = new JFormattedTextField();
	private JFormattedTextField tPeriodoIniExt = new JFormattedTextField();
	private JFormattedTextField tPeriodoFimExt = new JFormattedTextField();
	private JFormattedTextField tDataPagame = new JFormattedTextField();
	private JFormattedTextField tDataFinalPagame = new JFormattedTextField();
	private JFormattedTextField tDataInicioVenc = new JFormattedTextField();
	private JFormattedTextField tDataFimVenc = new JFormattedTextField();


	private JLabel lFuncExtrato = new JLabel ("Funcionario");

	private JComboBox jcEmpresa = new JComboBox(new String[]{});
	private JComboBox jcFuncRel = new JComboBox(new String[]{});
	private JComboBox jcHistorico = new JComboBox(new String[]{});
	private JComboBox jcBaixados = new JComboBox(new String[]{"Sim", "Nao"});

	private JComboBox jcFuncExtrato = new JComboBox(new String[]{});

	//Criando os Botões
	private JButton gerar = new JButton("Gerar");

	//Criando Os paineis (JPANEL).
	private JPanel jcamposrel = new JPanel(null);
	private JPanel jcamposExt = new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();

	public Relatorio(){
		super(new BorderLayout());

		abasRel = new JTabbedPane(JTabbedPane.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(abasRel);
		abasRel.add("Lancamento", jcamposrel);
		this.setBackground(Color.WHITE);
		int rlinha = 30;
		int nlinha = 1;
		jcamposrel.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Relatórios", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));


		EmpresaCtrl controleEmp = new EmpresaCtrl();
		jcEmpresa = new JComboBox(controleEmp.comboEmpresa().toArray());
		
		lEmpresa.setBounds(10, nlinha * rlinha, 100, 20);
		jcamposrel.add(lEmpresa);
		jcEmpresa.setBounds(90, nlinha * rlinha, 305, 20);
		jcamposrel.add(jcEmpresa);

		nlinha++;

		FuncionarioCtrl controleFunc = new FuncionarioCtrl();
		jcFuncRel = new JComboBox(controleFunc.combofuncionario().toArray());
		
		lFuncRel.setBounds(10, nlinha * rlinha, 100, 20);
		jcamposrel.add(lFuncRel);
		jcFuncRel.setBounds(90, nlinha * rlinha, 305, 20);
		jcamposrel.add(jcFuncRel);
		
	
		nlinha++;

		HistoricoCtrl controleHist = new HistoricoCtrl();
		jcHistorico = new JComboBox(controleHist.comboHistorico().toArray());
		
		lHistorico.setBounds(10, nlinha * rlinha, 100, 20);
		jcamposrel.add(lHistorico);
		jcHistorico.setBounds(90, nlinha * rlinha, 305, 20);
		jcamposrel.add(jcHistorico);

		nlinha++;

		//Filtrando o perido de emissão
		lDataEmissao.setBounds(10, nlinha * rlinha, 100, 20);
		jcamposrel.add(lDataEmissao);
		tDataInicio.setBounds(90, nlinha * rlinha, 75, 20);
		jcamposrel.add(tDataInicio);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataInicio);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lDataFinalEmissao.setBounds(180, nlinha * rlinha, 40, 20);
		jcamposrel.add(lDataFinalEmissao);
		tDataFinal.setBounds(200, nlinha * rlinha, 75, 20);
		jcamposrel.add(tDataFinal);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataFinal);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nlinha++;

		//Filtrando periodo de pagamento 
		lDataPagame.setBounds(10, nlinha * rlinha, 100, 20);
		jcamposrel.add(lDataPagame);
		tDataPagame.setBounds(90, nlinha * rlinha, 75, 20);
		jcamposrel.add(tDataPagame);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataPagame);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lDataFinalPagame.setBounds(180, nlinha * rlinha, 40, 20);
		jcamposrel.add(lDataFinalPagame);
		tDataFinalPagame.setBounds(200, nlinha * rlinha, 75, 20);
		jcamposrel.add(tDataFinalPagame);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataFinalPagame);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			nlinha++;

			//Filtrando periodo de pagamento 
			lDataVencimento.setBounds(10, nlinha * rlinha, 100, 20);
			jcamposrel.add(lDataVencimento);
			tDataInicioVenc.setBounds(90, nlinha * rlinha, 75, 20);
			jcamposrel.add(tDataInicioVenc);
			try {
				MaskFormatter mk = new MaskFormatter ("##/##/####");
				mk.install(tDataInicioVenc);
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lDataFinalVenc.setBounds(180, nlinha * rlinha, 40, 20);
			jcamposrel.add(lDataFinalVenc);
			tDataFimVenc.setBounds(200, nlinha * rlinha, 75, 20);
			jcamposrel.add(tDataFimVenc);

			try {
				MaskFormatter mk = new MaskFormatter ("##/##/####");
				mk.install(tDataFimVenc);
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		this.add(abasRel);

		// Configurando os Botões.	
		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		gerar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if (abasRel.getSelectedIndex()== 0) {
					RelatorioCtrl controle = new RelatorioCtrl();
					controle.relLancamento(((EstCampo)(jcEmpresa.getSelectedItem())).valor, ((EstCampo)(jcFuncRel.getSelectedItem())).valor, ((EstCampo)(jcHistorico.getSelectedItem())).valor, tDataInicio.getText().trim(), tDataFinal.getText().trim(), tDataPagame.getText().trim(), tDataFinalPagame.getText().trim(), tDataInicioVenc.getText().trim(), tDataFimVenc.getText().trim());
				}else {
					if(funcaoData.validaData(tPeriodoIniExt.getText()).trim().length() == 0){
						JOptionPane.showMessageDialog(null,  "Periodo Inicio Invalido" , null,   JOptionPane.INFORMATION_MESSAGE);
					}else{
						if(funcaoData.validaData(tPeriodoFimExt.getText()).trim().length() == 0){
							JOptionPane.showMessageDialog(null,  "Periodo Final Invalido" , null,   JOptionPane.INFORMATION_MESSAGE);
						}else {
							RelatorioCtrl controle = new RelatorioCtrl();										
							String msm = controle.recibo_Extrato((EstCampo) jcFuncExtrato.getSelectedItem(), tPeriodoIniExt.getText(), tPeriodoFimExt.getText());
							if (msm.length() > 0) {
								JOptionPane.showMessageDialog(null, msm);	
							}
						}
					}
				}
			}
		});
		jposicao.add(gerar);
		jbotoes.setLayout(new BorderLayout());
		jbotoes.add(jposicao, BorderLayout.EAST);
		jbotoes.setPreferredSize(new Dimension(500,40));
		jbotoes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(jbotoes,BorderLayout.NORTH);

		//Configurando imagem em um painel.
		jimagem.setBackground(Color.WHITE);
		jcamposrel.add(jimagem, BorderLayout.EAST);
		jimagem.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/logomarca.jpeg"))));
		this.add(jimagem,BorderLayout.SOUTH);		

		
		
		////criando a segunda aba de Extrato;

		abasRel.add(jcamposExt);
		abasRel.add("Extrato", jcamposExt);
		int elinha = 30;
		int exnlinha = 1;
		this.setBackground(Color.WHITE);
		jcamposExt.setBackground(Color.WHITE);

		FuncionarioCtrl controleFuncExtrato = new FuncionarioCtrl();
		jcFuncExtrato = new JComboBox(controleFuncExtrato.combofuncionario().toArray());
		
		lFuncExtrato.setBounds(10, exnlinha * elinha, 100, 20);
		jcamposExt.add(lFuncExtrato);
		jcFuncExtrato.setBounds(110, exnlinha * elinha, 305, 20);
		jcamposExt.add(jcFuncExtrato);

		exnlinha++;

		lPeriodoExt.setBounds(10, exnlinha * elinha, 100, 20);
		jcamposExt.add(lPeriodoExt);
		tPeriodoIniExt.setBounds(110, exnlinha * elinha, 75, 20);
		jcamposExt.add(tPeriodoIniExt);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tPeriodoIniExt);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lPeriodoAteExt.setBounds(220, exnlinha * elinha, 30, 20);
		jcamposExt.add(lPeriodoAteExt);
		tPeriodoFimExt.setBounds(260, exnlinha * elinha, 75, 20);
		jcamposExt.add(tPeriodoFimExt);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tPeriodoFimExt);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exnlinha++;
		lBaixados.setBounds(10, exnlinha * elinha, 100, 20);
		jcamposExt.add(lBaixados);
		jcBaixados.setBounds(110, exnlinha * elinha, 100, 20);
		jcamposExt.add(jcBaixados);
	}
}
