package Interface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import Controle.CargoCtrl;
import Persistencia.CargoDAO;
import Persistencia.CargoVO;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *  Data 29/10/2009
 */

public class CadCargo extends JPanel{

	private JLabel lCodCar = new JLabel("Codigo");
	private JLabel lDescriCar= new JLabel("Descrição");
	private JLabel lCodContab = new JLabel("Cod Contabil");
	private JLabel lSalarioCar = new JLabel("Salário Teto");

	private JTextField tCodcar = new JTextField();
	private JTextField tDescriCar= new JTextField();
	private JTextField tCodContab = new JTextField();
	private JTextField tSalarioCar= new JTextField();

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
	private JPanel jcampos = new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();

	/**
	 * Criando o Construtor da classe.
	 */
	public CadCargo(){
		super(new BorderLayout());
		this.setBackground(Color.WHITE);

		int clinha = 30;
		int nlinha = 1;

		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cadastro de Cargos", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));

		lCodCar.setBounds(10, nlinha * clinha, 100, 20);
		jcampos.add(lCodCar);
		tCodcar.setBounds(85, nlinha * clinha, 80, 20);
		jcampos.add(tCodcar);
		


		busca.setBounds(170, nlinha * clinha, 70, 20);
		jcampos.add(busca);

		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * clinha, 20, 20);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaCargo(CadCargo.this);
			}

		});
		jcampos.add(pesquisa);

		nlinha++;

		lDescriCar.setBounds(10, nlinha * clinha, 100, 20);
		jcampos.add(lDescriCar);
		tDescriCar.setBounds(85, nlinha* clinha, 350, 20);
		jcampos.add(tDescriCar);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tDescriCar.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tDescriCar.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});
		nlinha++;

		lCodContab.setBounds(10, nlinha * clinha, 100, 20);
		jcampos.add(lCodContab);
		tCodContab.setBounds(85, nlinha * clinha, 100, 20);
		jcampos.add(tCodContab);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tCodContab.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tCodContab.getText().length() >= 14){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		nlinha++;

		lSalarioCar.setBounds(10, nlinha * clinha, 100, 20);
		jcampos.add(lSalarioCar);
		tSalarioCar.setBounds(85, nlinha * clinha, 100, 20);
		jcampos.add(tSalarioCar);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tSalarioCar.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tSalarioCar.getText().length() >= 12.2){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

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

			@Override
			public void actionPerformed(ActionEvent e) {
				CargoVO NovoCargo = new CargoVO();

				NovoCargo.setDescricaoCargo(tDescriCar.getText());
				NovoCargo.setSalarioCargo(tSalarioCar.getText());
				NovoCargo.setCodContabil((tCodContab.getText()));

				CargoCtrl controle = new CargoCtrl();
				ArrayList<String> ret = controle.incluir(NovoCargo);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tCodcar.setText(ret.get(1));
			}

		});

		/**
		 * Adicionando ação no botão Alterar(gravar)
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CargoVO voAltera = new CargoVO();

				voAltera.setCodigoCargo(tCodcar.getText());
				voAltera.setDescricaoCargo(tDescriCar.getText());
				voAltera.setSalarioCargo(tSalarioCar.getText());
				voAltera.setCodContabil((tCodContab.getText()));

				CargoCtrl Controle = new CargoCtrl();
				String ret = Controle.alterar(voAltera);
				if(ret.trim().length() > 0){
					JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});

		/**
		 * Adicionando ação no botão Consultar
		 */

		busca.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CargoVO voConsulta = new CargoVO();
				CargoDAO DaoCarg = new CargoDAO();
				try {
					if (tCodcar.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
						voConsulta = DaoCarg.pesquisar(Integer.valueOf(tCodcar.getText()));
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
				tDescriCar.setText(voConsulta.getDescricaoCargo());
				tSalarioCar.setText(String.valueOf(voConsulta.getSalarioCargo()));
				tCodContab.setText(voConsulta.getCodContabil());	
			}

		});

		/**
		 * Adicionando ação no botão excluir
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				CargoVO voExclui = new CargoVO();
				voExclui.setCodigoCargo(tCodcar.getText());

				CargoCtrl Controle = new CargoCtrl();

				try {
					String ret = Controle.excluir(voExclui);
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

				tCodcar.setText("");
				tDescriCar.setText("");
				tSalarioCar.setText("");
				tCodContab.setText("");

			}
		});
		jbotoes.setLayout(new BorderLayout());
		jbotoes.add(jposicao, BorderLayout.EAST);
		this.add(jbotoes,BorderLayout.NORTH);
		jbotoes.setPreferredSize(new Dimension(500,40));
		jbotoes.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		//Configurando imagem em um painel.
		jimagem.setBackground(Color.WHITE);
		jcampos.add(jimagem, BorderLayout.EAST);
		jimagem.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/logomarca.jpeg"))));
		this.add(jimagem,BorderLayout.SOUTH);	
		
		//Setando primeiro foco na tela.
		SwingUtilities.invokeLater(new Runnable() {    
			public void run() {    
				tCodcar.requestFocus();    
			}    
		}); 

	}


	public void consultou(String cod){
		tCodcar.setText(cod);
		busca.doClick();
	}
}
