package Interface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Controle.HistoricoCtrl;
import Persistencia.HistoricoDAO;
import Persistencia.HistoricoVO;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 * Data 21/10/2009
 */
public class CadHistorico extends JPanel{
	private JLabel lCodhistorico = new JLabel("Codigo");
	private JLabel lDescHistorico = new JLabel("Descrição");
	private JLabel lDebCre = new JLabel ("Deb/Cred");

	
	private JComboBox jcDebCre = new JComboBox(new String[]{"Debito", "Credito"});
	
	private JTextField tCodHistorico = new JTextField();
	private JTextField tDEscHistorico = new JTextField();
	
	/**
	 * Criando os Botões.
	 */
	private JButton incluir = new JButton("Incluir");
	private JButton alterar = new JButton("Alterar");
	private JButton busca = new JButton("Busca");
	private JButton excluir = new JButton("Excluir");
	private JButton pesquisa = new JButton();
	private JButton limpar = new JButton("Limpa");

	/**
	 * Criando Os paineis (JPANEL).
	 */
	private JPanel jcampos =  new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();

	/**
	 * Criando o Construtor da classe.
	 */
	public CadHistorico(){
		super(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		int hlinha = 30;
		int nlinha = 1;
		
		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cadastro de Históricos", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));
		
		lCodhistorico.setBounds(10, nlinha * hlinha, 100, 20);
		jcampos.add(lCodhistorico);
		tCodHistorico.setBounds(85, nlinha * hlinha, 80, 20);
		jcampos.add(tCodHistorico);
		
		busca.setBounds(170, nlinha * hlinha, 70, 20);
		jcampos.add(busca);
		
		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * hlinha, 20, 20);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaHistorico(CadHistorico.this);
			}
			
		});
		jcampos.add(pesquisa);
		
		nlinha++;
		lDescHistorico.setBounds(10, nlinha * hlinha, 100, 20);
		jcampos.add(lDescHistorico);
		tDEscHistorico.setBounds(85, nlinha * hlinha, 350, 20);
		jcampos.add(tDEscHistorico);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tDEscHistorico.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tDEscHistorico.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		nlinha++;
		lDebCre.setBounds(10, nlinha * hlinha, 100, 20);
		jcampos.add(lDebCre);
		jcDebCre.setBounds(85, nlinha * hlinha, 100, 20);
		jcampos.add(jcDebCre);
			
		this.add(jcampos,BorderLayout.CENTER);
		
		nlinha++;
		nlinha++;
		
		// Configurando os Botões.
		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		jposicao.add(incluir);
		
		/**
		 * Adicionando ação no botão Inlcuir
		 */
		incluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HistoricoVO novoHistorico = new HistoricoVO();
				
				novoHistorico.setDescricaoHist(tDEscHistorico.getText());
				novoHistorico.setDebCreHist(String.valueOf(jcDebCre.getSelectedIndex()));
				
				HistoricoCtrl Controle = new HistoricoCtrl();
				ArrayList<String> ret = Controle.incluir(novoHistorico);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tCodHistorico.setText(ret.get(1));
			}
			
		});
		
		/**
		 * Adicionando ação no botão Alterar(gravar)
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HistoricoVO voAlterar = new HistoricoVO();
				
				voAlterar.setCodigoHist(tCodHistorico.getText());
				voAlterar.setDescricaoHist(tDEscHistorico.getText());
				voAlterar.setDebCreHist(String.valueOf(jcDebCre.getSelectedIndex()));
				
				HistoricoCtrl Controle = new HistoricoCtrl();
				String ret = Controle.alterar(voAlterar);
				JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		/**
		 * Adicionando ação no botão Consultar
		 */
		busca.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				HistoricoVO voConsulta = new HistoricoVO();
				HistoricoDAO Daohisto = new HistoricoDAO();
				try {
					if (tCodHistorico.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
					voConsulta = Daohisto.pesquisar(Integer.valueOf(tCodHistorico.getText()));
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
				tDEscHistorico.setText(voConsulta.getDescricaoHist());
				jcDebCre.setSelectedIndex(voConsulta.getDebCreHist_Int());
			}
			
		});
		
		/**
		 * Adicionando ação no botão excluir
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HistoricoVO voExclui= new HistoricoVO();
				voExclui.setCodigoHist(tCodHistorico.getText());
				
				HistoricoCtrl Controle = new HistoricoCtrl();
				
				try {
					String ret = Controle.excluir(voExclui);
					if(ret.trim().length() > 0){
						JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
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
				
				tCodHistorico.setText("");
				tDEscHistorico.setText("");
				jcDebCre.setSelectedIndex(0);
				
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
		tCodHistorico.setText(cod);
		busca.doClick();
	}
}
