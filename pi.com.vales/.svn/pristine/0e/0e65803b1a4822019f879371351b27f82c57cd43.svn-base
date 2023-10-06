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
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import Controle.BaixaCtrl;
import Controle.FuncionarioCtrl;
import Persistencia.EstCampo;
import Persistencia.LancamentoVO;
import funcoes.funcaoData;
/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *  Data 29/10/2009
 */

public class BaixaProFuncionario extends JPanel{

	JLabel lFuncionario = new JLabel("Funcionario");
	JLabel lPeriodo = new JLabel("Periodo");
	JLabel lPeriodoAte = new JLabel("Ate");
	JLabel lDataPgto = new JLabel("Pagamento");	

	JComboBox jcFuncionario = new JComboBox(new String[]{});


	JFormattedTextField tPeriodoIni = new JFormattedTextField();
	JFormattedTextField tPeriodoFim = new JFormattedTextField();
	JFormattedTextField tDataPgto = new JFormattedTextField();

	/**
	 * Criando os Botões
	 */
	JButton quitar = new JButton("Quitar");
	JButton limpar = new JButton("Limpa");

	/**
	 * Criando Os paineis (JPANEL).
	 */
	JPanel jcampos = new JPanel(null);
	JPanel jbotoes = new JPanel();
	JPanel jposicao = new JPanel();
	JPanel jimagem = new JPanel();

	/**
	 * Criando o Construtor da classe.
	 */
	public BaixaProFuncionario(){
		super (new BorderLayout());

		this.setBackground(Color.WHITE);

		int blinha = 30;
		int nlinha = 1;

		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Baixa Por Funcionario", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));


		FuncionarioCtrl controleFunc = new FuncionarioCtrl();
		jcFuncionario = new JComboBox(controleFunc.combofuncionario().toArray());

		lFuncionario.setBounds(10, nlinha * blinha, 100, 20);
		jcampos.add(lFuncionario);
		jcFuncionario.setBounds(110, nlinha * blinha, 305, 20);
		jcampos.add(jcFuncionario);

		nlinha++;
		lPeriodo.setBounds(10, nlinha * blinha, 100, 20);
		jcampos.add(lPeriodo);
		tPeriodoIni.setBounds(110, nlinha * blinha, 90, 20);
		jcampos.add(tPeriodoIni);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tPeriodoIni);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lPeriodoAte.setBounds(220, nlinha * blinha, 30, 20);
		jcampos.add(lPeriodoAte);
		tPeriodoFim.setBounds(260, nlinha * blinha, 90, 20);
		jcampos.add(tPeriodoFim);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tPeriodoFim);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nlinha++;
		lDataPgto.setBounds(10, nlinha * blinha, 100, 20);
		jcampos.add(lDataPgto);
		tDataPgto.setBounds(110, nlinha * blinha, 90, 20);
		jcampos.add(tDataPgto);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataPgto);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.add(jcampos,BorderLayout.CENTER);

		nlinha++;
		nlinha++;

		// Configurando os Botões.
		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		jposicao.add(quitar);
		quitar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				LancamentoVO baixaVO = new LancamentoVO();
				
				if(funcaoData.validaData(tPeriodoIni.getText()).trim().length() == 0){
					JOptionPane.showMessageDialog(null,  "Periodo Inicio Invalido" , null,   JOptionPane.INFORMATION_MESSAGE);
				}else{
					if(funcaoData.validaData(tPeriodoFim.getText()).trim().length() == 0){
						JOptionPane.showMessageDialog(null,  "Periodo Final Invalido" , null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
						baixaVO.setDatinicial(tPeriodoIni.getText());
						baixaVO.setDatfinal(tPeriodoFim.getText());
						baixaVO.setCodigoFun(((EstCampo)(jcFuncionario.getSelectedItem())).valor);

						baixaVO.setDataPgto(tDataPgto.getText());

						BaixaCtrl Controle = new BaixaCtrl();
						String ret = Controle.quitar(baixaVO);
						JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		/**
		 * Limpando a tela 
		 */
		jposicao.add(limpar);
		limpar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				jcFuncionario.setSelectedIndex(0);
				tPeriodoIni.setText("");
				tPeriodoFim.setText("");
				tDataPgto.setText("");
			}
		});
		jbotoes.setLayout(new BorderLayout());
		jbotoes.add(jposicao, BorderLayout.EAST);
		jbotoes.setPreferredSize(new Dimension(500,40));
		jbotoes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(jbotoes,BorderLayout.NORTH);


		//Configurando imagem em um painel.
		jimagem.setBackground(Color.WHITE);
		jcampos.add(jimagem, BorderLayout.EAST);
		jimagem.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/logomarca.jpeg"))));
		this.add(jimagem,BorderLayout.SOUTH);		
	}
}
