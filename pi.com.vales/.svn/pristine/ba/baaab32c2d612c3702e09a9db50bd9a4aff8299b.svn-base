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
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

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
import Controle.LancamentoCtrl;
import Persistencia.EstCampo;
import Persistencia.HistoricoDAO;
import Persistencia.HistoricoVO;
import Persistencia.LancamentoDAO;
import Persistencia.LancamentoVO;
import funcoes.CComboBox;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *	Data 30/10/2009
 */

public class Lancamento extends JPanel{
	
	private JLabel lNumero = new JLabel("Numero");
	private JLabel lDescricao = new JLabel("Descricao");
	private JLabel lDataEmissao = new JLabel("Dt Emissão");
	private JLabel lDataVencimento = new JLabel("Dt Vencimento");
	private JLabel lDataPagamento = new JLabel ("Dt Pagamento");
	private JLabel lComplemento = new JLabel ("His. Complem");
	private JLabel lValor = new JLabel ("Valor");
	private JLabel lFuncionario = new JLabel("Funcionario");
	private JLabel lEmpresa = new JLabel("Empresa");
	private JLabel lHistorico = new JLabel("Historico");
	private JLabel lDebCre = new JLabel ("Deb/Cred");

	private JTextField tNumero = new JTextField();
	private JTextField tDescricao = new JTextField();
	private JFormattedTextField tDataEmissao = new JFormattedTextField();
	private JFormattedTextField tDataVencimento = new JFormattedTextField();
	private JFormattedTextField tDataPagamento = new JFormattedTextField();
	private JTextField tComplemento = new JTextField();
	private JTextField tValor = new JTextField();
	private JTextField tDebCre = new JTextField();
	
	private JComboBox jcEmpresa = new JComboBox(new String[]{});
	private JComboBox jcFuncionario = new JComboBox(new String[]{});
	private JComboBox jcHistorico = new JComboBox(new String[]{});
	/**
	 * Criando os Botões
	 */
	private JButton incluir = new JButton("Incluir");
	private JButton alterar = new JButton("Alterar");
	private JButton excluir = new JButton ("Excluir");
	private JButton busca = new JButton ("Busca");
	private JButton pesquisa = new JButton();
	private JButton limpar = new JButton("Limpa");
	
	
	/**
	 * Criando Os paineis (JPANEL).
	 */
	private JPanel jcampos = new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();
	
	/**
	 * Instanciando o Construtor da Classe.
	 */
	public Lancamento(){
		super (new BorderLayout());
		this.setBackground(Color.WHITE);
		
		int colLabel = 95;
		int llinha = 30;
		int nlinha = 1;
		
		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Lancamentos", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));
		
		lNumero.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lNumero);
		tNumero.setBounds(colLabel, nlinha * llinha, 70, 20);
		jcampos.add(tNumero);
		
		busca.setBounds(170, nlinha * llinha, 70, 20);
		jcampos.add(busca);
		
		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * llinha, 20, 20);
		jcampos.add(pesquisa);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaLancamento(Lancamento.this);
			}
			
		});

		
		nlinha++;
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
								tDebCre.setText("DEBITO");
							}else{
								tDebCre.setText("CREDITO");
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
		
		lDataVencimento.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDataVencimento);
		tDataVencimento.setBounds(colLabel, nlinha * llinha, 90, 20);
		jcampos.add(tDataVencimento);
		
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataVencimento);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nlinha++;
		
		lDataPagamento.setBounds(10, nlinha * llinha, 100, 20);
		jcampos.add(lDataPagamento);
		tDataPagamento.setBounds(colLabel, nlinha * llinha, 90, 20);
		jcampos.add(tDataPagamento);
		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataPagamento);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
				LancamentoVO novoLancamento = new LancamentoVO();

				novoLancamento.setDescricaoLanc(tDescricao.getText());
				novoLancamento.setCodigoFun(((EstCampo)(jcFuncionario.getSelectedItem())).valor);
				novoLancamento.setCodigoHist(((EstCampo)(jcHistorico.getSelectedItem())).valor);
				if(tDebCre.getText().equals("DEBITO")){
					novoLancamento.setDebCreHist("0");
				}else novoLancamento.setDebCreHist("1");
				novoLancamento.setCodigoEmp(((EstCampo)(jcEmpresa.getSelectedItem())).valor);
				novoLancamento.setValorLanc(tValor.getText());
				novoLancamento.setDataEmissao(tDataEmissao.getText());
				novoLancamento.setDataVenc(tDataVencimento.getText());
				novoLancamento.setDataPgto(tDataPagamento.getText().trim());
				novoLancamento.setComplLanc(tComplemento.getText());
				
				LancamentoCtrl Controle = new LancamentoCtrl();
				ArrayList<String> ret = Controle.incluir(novoLancamento);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tNumero.setText(ret.get(1));
			}
		});
		
		
		/**
		 * Adicionando acao ao botão gravar.
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				LancamentoVO voAltera = new LancamentoVO();
				
				voAltera.setCodigoLanc(tNumero.getText());
				voAltera.setDescricaoLanc(tDescricao.getText());
				voAltera.setCodigoFun(((EstCampo)(jcFuncionario.getSelectedItem())).valor);
				voAltera.setCodigoHist(((EstCampo)(jcHistorico.getSelectedItem())).valor);
				
				if(tDebCre.getText().equals("DEBITO")){
					voAltera.setDebCreHist("0");
				}else voAltera.setDebCreHist("1");
				voAltera.setCodigoEmp(((EstCampo)(jcEmpresa.getSelectedItem())).valor);
				voAltera.setCodigoHist(((EstCampo)(jcHistorico.getSelectedItem())).valor);
				voAltera.setValorLanc(tValor.getText());
				voAltera.setDataEmissao(tDataEmissao.getText());
				voAltera.setDataVenc(tDataVencimento.getText());
				voAltera.setDataPgto(tDataPagamento.getText().trim());
				voAltera.setComplLanc(tComplemento.getText());
				
				LancamentoCtrl Controle = new LancamentoCtrl();
				String ret = Controle.alterar(voAltera);
				JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		/**
		 * Adicionando uma ação ao botão consultar.
		 */
		busca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				LancamentoVO voConsulta = new LancamentoVO();
				LancamentoDAO daoConsulta = new LancamentoDAO();
				
				try {
					if (tNumero.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
					voConsulta = daoConsulta.pesquisar(Integer.valueOf(tNumero.getText()));
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				CComboBox c = new CComboBox();
				c.setValor(jcFuncionario, ""+voConsulta.getCodigoFun());
				c.setValor(jcHistorico, ""+voConsulta.getCodigoHist());
				c.setValor(jcEmpresa, ""+voConsulta.getCodigoEmp());
				
				tNumero.setText(String.valueOf(voConsulta.getCodigoLanc()));
				tDescricao.setText(voConsulta.getDescricaoLanc());
				jcFuncionario.setSelectedItem(voConsulta.getCodigoFun());
				jcHistorico.setSelectedItem(voConsulta.getCodigoHist());
				if(tDebCre.getText().equals("0")){
					voConsulta.setDebCreHist("DEBITO");
				}else voConsulta.setDebCreHist("CREDITO");
				jcEmpresa.setSelectedItem(voConsulta.getCodigoEmp());
				tValor.setText(String.valueOf(voConsulta.getValorLanc()));
				tDataEmissao.setText(voConsulta.getDataEmissaoBusca());
				tDataVencimento.setText(voConsulta.getDataVencBusca());
				tDataPagamento.setText(voConsulta.getDataPgtoBusca());
				tComplemento.setText(voConsulta.getComplLanc());
			}
		});
		
		
		/**
		 * Adicionando uma ação no botão excluir.
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				LancamentoVO voExcluir = new LancamentoVO();
				voExcluir.setCodigoLanc(tNumero.getText());
				
				LancamentoCtrl Controle = new LancamentoCtrl();
				try {
					String ret = Controle.excluir(voExcluir);
					if(ret.trim().length() > 0){
						JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
						if(ret.contains("Sucesso")){
							limpar.doClick();
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}			
			}
			
		});
	
		/**
		 * Limpando a tela 
		 */
		jposicao.add(limpar);
		limpar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				tNumero.setText("");
				tDescricao.setText("");
				jcFuncionario.setSelectedIndex(0);
				jcHistorico.setSelectedIndex(0);
				tDebCre.setText("");
				jcEmpresa.setSelectedIndex(0);
				tValor.setText("");
				tDataEmissao.setText("");
				tDataVencimento.setText("");
				tDataPagamento.setText("");
				tComplemento.setText("");
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
	public void consultou(String cod){
		tNumero.setText(cod);
		busca.doClick();
	}
}
