package Interface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import Controle.EmpresaCtrl;
import Controle.FuncionarioCtrl;
import Controle.HistoricoCtrl;
import Controle.LancAutomaticoCtrl;
import Persistencia.EstCampo;
import Persistencia.HistoricoDAO;
import Persistencia.HistoricoVO;
import Persistencia.LancAutomaticoVO;
import funcoes.funcaoData;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *	Data 01/11/2009
 */
public class LancAutomatico extends JPanel{
	JLabel lDescricao = new JLabel("Descrição");
	JLabel lDataEmissao = new JLabel("Dt Emissão");
	JLabel lDiaVence = new JLabel("Dia Vencer");
	JLabel lComplemento = new JLabel ("His. Complem");
	JLabel lValor = new JLabel ("Valor");
	JLabel lFuncionario = new JLabel("Funcionario");
	JLabel lEmpresa = new JLabel("Empresa");
	JLabel lHistorico = new JLabel("Historico");
	JLabel lDebCre = new JLabel("Deb/Cred");
	JLabel lParcela = new JLabel("Parcelas");
	JLabel lDataApartir = new JLabel("Partir de");

	JTextField tDescricao = new JTextField();
	JFormattedTextField tDataEmissao = new JFormattedTextField();
	JTextField tDiaVence = new JTextField(2);
	JTextField tComplemento = new JTextField();
	JTextField tValor = new JTextField();
	JTextField tParcela = new JTextField();
	JTextField tDebCre = new JTextField();
	JFormattedTextField tDataApartir = new JFormattedTextField();

	JComboBox jcEmpresa = new JComboBox(new String[]{});
	JComboBox jcFuncionario = new JComboBox(new String[]{});
	JComboBox jcHistorico = new JComboBox(new String[]{});

	/**
	 * Criando os Botões
	 */
	JButton incluir = new JButton("Executar");


	/**
	 * Criando Os paineis (JPANEL).
	 */
	JPanel jcampos = new JPanel(null);
	JPanel jbotoes = new JPanel();
	JPanel jposicao = new JPanel();
	JPanel jimagem = new JPanel();

	/**
	 * Instanciando o Construtor da Classe.
	 */
	public LancAutomatico(){

		super (new BorderLayout());
		this.setBackground(Color.WHITE);

		int colLabel = 95;
		int llinha = 30;
		int nlinha = 1;

		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Lancamento Automatico", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));


		lDescricao.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDescricao);
		tDescricao.setBounds(colLabel, nlinha * llinha, 240, 20);
		jcampos.add(tDescricao);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tDescricao.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tDescricao.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	

		nlinha++;
		FuncionarioCtrl controleFunc = new FuncionarioCtrl();
		jcFuncionario = new JComboBox(controleFunc.combofuncionario().toArray());

		lFuncionario.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lFuncionario);
		jcFuncionario.setBounds(colLabel, nlinha * llinha, 305, 20);
		jcampos.add(jcFuncionario);

		nlinha++;

		HistoricoCtrl controleHist = new HistoricoCtrl();
		jcHistorico = new JComboBox(controleHist.comboHistorico().toArray());

		lHistorico.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lHistorico);
		jcHistorico.setBounds(colLabel, nlinha * llinha, 305, 20);
		jcHistorico.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				try{
					String cod = ((EstCampo)(jcHistorico.getSelectedItem())).valor;
					if(cod.trim().length() > 0){
						HistoricoDAO DAO = new HistoricoDAO();
						HistoricoVO vo = DAO.pesquisar(cod);
						if(vo != null){
							if(vo.getDebCreHist().equals("0")){
								tDebCre.setText("Débito");
							}else{
								tDebCre.setText("Crédito");
							}
						}else{
							tDebCre.setText("");
						}
					}else{
						tDebCre.setText("");
					}
				}catch (Exception e) {
					tDebCre.setText("");
				}
			}

		});
		jcampos.add(jcHistorico);

		lDebCre.setBounds(410, nlinha * llinha, 80, 20);
		jcampos.add(lDebCre);
		tDebCre.setBounds(480, nlinha * llinha, 90, 20);
		tDebCre.setEnabled(false);
		jcampos.add(tDebCre);
		nlinha++;

		EmpresaCtrl controleEmp = new EmpresaCtrl();
		jcEmpresa = new JComboBox(controleEmp.comboEmpresa().toArray());

		lEmpresa.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lEmpresa);
		jcEmpresa.setBounds(colLabel, nlinha * llinha, 305, 20);
		jcampos.add(jcEmpresa);

		nlinha++;
		lValor.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lValor);
		tValor.setBounds(colLabel, nlinha * llinha, 80, 20);
		jcampos.add(tValor);

		nlinha++;
		lDataEmissao.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDataEmissao);
		tDataEmissao.setBounds(colLabel, nlinha * llinha, 90, 20);
		jcampos.add(tDataEmissao);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataEmissao);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		nlinha++;

		lDataApartir.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDataApartir);
		tDataApartir.setBounds(colLabel, nlinha * llinha, 100, 20);
		jcampos.add(tDataApartir);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataApartir);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nlinha++;

		lDiaVence.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDiaVence);
		tDiaVence.setBounds(colLabel, nlinha * llinha, 30, 20);
		jcampos.add(tDiaVence);

		nlinha++;
		lParcela.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lParcela);
		tParcela.setBounds(colLabel, nlinha * llinha, 50, 20);
		jcampos.add(tParcela);

		nlinha++;
		lComplemento.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lComplemento);
		tComplemento.setBounds(colLabel, nlinha * llinha, 300, 20);
		jcampos.add(tComplemento);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tComplemento.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tComplemento.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	


		this.add(jcampos, BorderLayout.CENTER);

		//Configurando Painel dos Botoes.

		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		/**
		 * Adicionando uma ação ao botão incluir
		 */
		jposicao.add(incluir);
		incluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				LancAutomaticoVO novoLancAuto = new LancAutomaticoVO();
				LancAutomaticoCtrl Controle = new LancAutomaticoCtrl();

				novoLancAuto.setDescricaoLanc(tDescricao.getText());
				novoLancAuto.setCodigoFun(((EstCampo)(jcFuncionario.getSelectedItem())).valor);
				novoLancAuto.setCodigoHist(((EstCampo)(jcHistorico.getSelectedItem())).valor);
				if(tDebCre.getText().equals("Débito")){
					novoLancAuto.setDebCreHist("0");
				}else novoLancAuto.setDebCreHist("1");
				novoLancAuto.setCodigoEmp(((EstCampo)(jcEmpresa.getSelectedItem())).valor);
				novoLancAuto.setValorLanc(tValor.getText());
				novoLancAuto.setDataEmissao(tDataEmissao.getText());

				novoLancAuto.setComplLanc(tComplemento.getText());


				//
				
				if(((tParcela.getText() == null)) || (tParcela.getText().trim().length() == 0)){
					JOptionPane.showMessageDialog(null,  "Numero Parcelas", null,   JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					if(((tDiaVence.getText() == null)) || (tDiaVence.getText().trim().length() == 0)){
						JOptionPane.showMessageDialog(null,  "Dia Vencimento", null,   JOptionPane.INFORMATION_MESSAGE);
					}else{
						if(funcaoData.validaData(tDataApartir.getText()).trim().length() == 0){
							JOptionPane.showMessageDialog(null,  "A partir de Que data", null,   JOptionPane.INFORMATION_MESSAGE);
						}else{
							String retorno = Controle.antesIncluir(novoLancAuto); 
							if(retorno == null){
								String pegadata = tDataApartir.getText();
								pegadata= pegadata.replace("/", "");

								int dias, mes, ano;
								try {
									dias = Integer.parseInt(pegadata.substring(0,2));
									mes = Integer.parseInt(pegadata.substring(2,4));
									ano = Integer.parseInt(pegadata.substring(4,8));
								} catch (Exception e2) {
									dias = 0; mes = 0; ano = 0;
								}

								for (int i = 0; i < Integer.valueOf(tParcela.getText()); i++) {

									String m = "";
									if(mes <= 9){
										m = "0"+mes;
									}else{
										m = ""+mes;
									}

									String d = tDiaVence.getText();
									if(d.trim().length() < 2){
										d = "0"+d;
									}

									pegadata = d+"/"+m+"/"+ano;
									novoLancAuto.setDataVenc(pegadata);

									try {
										Controle.incluir(novoLancAuto);
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(null,  e2.toString(), null,   JOptionPane.INFORMATION_MESSAGE);
									}

									mes++;
								}

								JOptionPane.showMessageDialog(null,  "Registros Provisionados Com Sucesso", null,   JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null,  retorno, null,   JOptionPane.INFORMATION_MESSAGE);	
							}		
						}
					}
				}
			}


		});
		jbotoes.setLayout(new BorderLayout());
		jbotoes.add(jposicao, BorderLayout.EAST);
		jbotoes.setPreferredSize(new Dimension(500,40));
		jbotoes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(jbotoes,BorderLayout.NORTH);

		/**
		 * Configurando imagem em um painel.
		 */
		jimagem.setBackground(Color.WHITE);
		jcampos.add(jimagem, BorderLayout.EAST);
		jimagem.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/logomarca.jpeg"))));
		this.add(jimagem,BorderLayout.SOUTH);		

	}
}
