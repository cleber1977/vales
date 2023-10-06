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
import Persistencia.EmpresaDAO;
import Persistencia.EmpresaVO;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *  Data 29/10/2009
 */
public class CadEmpresa extends JPanel{

	private JLabel lCodEmp = new JLabel("Codigo");
	private JLabel lRazao = new  JLabel("Razão");
	private JLabel lFantasia = new JLabel("Fantasia");
	private JLabel lEndereco = new JLabel("Endereço");
	private JLabel lCidade = new JLabel("Cidade");
	private JLabel lestado= new JLabel("Estado");
	private JLabel lBairro= new JLabel("Bairro");
	private JLabel lNumero = new JLabel("Numero");
	private JLabel lCep = new JLabel("CEP");
	private JLabel lCnpj = new JLabel ("CNPJ");
	private JLabel lInscEstadual = new JLabel("Insc. Estadual");
	private JLabel lTelefone = new JLabel ("Telefone");

	private JComboBox jcEstado = new JComboBox(new String[]{"GO", "MG","PA", "RJ", "SP", "DF","MS", "TO"});


	private JTextField tCodEmp = new JTextField();
	private JTextField tRazao = new JTextField();
	private JTextField tFantasia = new JTextField();
	private JTextField tEndereco = new JTextField();
	private JTextField tCidade =  new JTextField();
	private JTextField tBairro = new JTextField();
	private JTextField tNumero = new JTextField();
	private JTextField tCep = new JTextField();
	private JFormattedTextField tCnpj = new JFormattedTextField();
	private JTextField tInscEstadual = new JTextField();
	private JFormattedTextField tTelefone = new JFormattedTextField();

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
	private JPanel jposicao =  new JPanel();
	private JPanel jimagem = new JPanel();

	/**
	 * Criando o Construtor da classe.
	 */
	public CadEmpresa(){
		super(new BorderLayout());
		this.setBackground(Color.WHITE);		

		int elinha = 30;
		int nlinha = 1;

		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cadastro de Empresa", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));

		lCodEmp.setBounds(10, nlinha * elinha , 100, 20);
		jcampos.add(lCodEmp);
		tCodEmp.setBounds(85, nlinha * elinha, 80, 20);
		jcampos.add(tCodEmp);

		busca.setBounds(170, nlinha * elinha, 70, 20);
		jcampos.add(busca);

		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * elinha, 20, 20);
		jcampos.add(pesquisa);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaEmpresa(CadEmpresa.this);
			}

		});



		nlinha++;

		lRazao.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lRazao);
		tRazao.setBounds(85, nlinha * elinha, 300, 20);
		jcampos.add(tRazao);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tRazao.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tRazao.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	
		nlinha++;

		lFantasia.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lFantasia);
		tFantasia.setBounds(85, nlinha * elinha, 300, 20);
		jcampos.add(tFantasia);
		nlinha++;

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tFantasia.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tFantasia.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	

		lEndereco.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lEndereco);
		tEndereco.setBounds(85, nlinha * elinha, 200, 20);
		jcampos.add(tEndereco);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tEndereco.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tEndereco.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	


		lCep.setBounds(320, nlinha * elinha, 100, 20);
		jcampos.add(lCep);
		tCep.setBounds(360, nlinha * elinha, 80, 20);
		jcampos.add(tCep);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tCep.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tCep.getText().length() >= 8){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	

		lNumero.setBounds(460, nlinha * elinha, 100, 20);
		jcampos.add(lNumero);
		tNumero.setBounds(510, nlinha * elinha, 90, 20);
		jcampos.add(tNumero);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tNumero.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tNumero.getText().length() >= 11){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	

		nlinha++;

		lCidade.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lCidade);
		tCidade.setBounds(85, nlinha * elinha, 300, 20);
		jcampos.add(tCidade);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tCidade.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tCidade.getText().length() >= 20){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});	

		nlinha++;
		lBairro.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lBairro);
		tBairro.setBounds(85, nlinha * elinha, 300, 20);
		jcampos.add(tBairro);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tBairro.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tBairro.getText().length() >= 20){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});
		lestado.setBounds(460, nlinha * elinha, 100, 20);
		jcampos.add(lestado);
		jcEstado.setBounds(510, nlinha * elinha, 60, 20);
		jcampos.add(jcEstado);	

		nlinha++;

		lCnpj.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lCnpj);
		tCnpj.setBounds(85, nlinha * elinha, 130, 20);
		jcampos.add(tCnpj);

		try {
			MaskFormatter mk = new MaskFormatter ("##.###.###/####-##");
			mk.install(tCnpj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lInscEstadual.setBounds(250, nlinha * elinha, 100, 20);
		jcampos.add(lInscEstadual);
		tInscEstadual.setBounds(350, nlinha * elinha, 100, 20);
		jcampos.add(tInscEstadual);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tInscEstadual.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tInscEstadual.getText().length() >= 16){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});
		nlinha++;

		lTelefone.setBounds(10, nlinha * elinha, 100, 20);
		jcampos.add(lTelefone);
		tTelefone.setBounds(85, nlinha * elinha, 90, 20);
		jcampos.add(tTelefone);

		try {
			MaskFormatter mk = new MaskFormatter ("(##)####-####");
			mk.install(tTelefone);
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
		jposicao.add(incluir);

		/**
		 * Adicionando uma ação ao botão incluir
		 */
		incluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EmpresaVO novaEmpresa = new EmpresaVO();

				novaEmpresa.setRazaoSocial(tRazao.getText());
				novaEmpresa.setNomeFantasia(tFantasia.getText());
				novaEmpresa.setCnpj(tCnpj.getText());
				novaEmpresa.setInscrEst(tInscEstadual.getText());
				novaEmpresa.setEndEmp(tEndereco.getText());
				novaEmpresa.setNumeroEmp(tNumero.getText());
				novaEmpresa.setBairroEmp(tBairro.getText());
				novaEmpresa.setCidadeEmp(tCidade.getText());
				novaEmpresa.setUfEmp((String)jcEstado.getSelectedItem());
				novaEmpresa.setCepEmp(tCep.getText());
				novaEmpresa.setFoneEmp(tTelefone.getText());

				EmpresaCtrl controle = new EmpresaCtrl();
				ArrayList<String> ret = controle.incluir(novaEmpresa);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tCodEmp.setText(ret.get(1));
			}

		});

		/**
		 * Adicionando acao ao botão gravar.
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EmpresaVO voAltera = new EmpresaVO();
				voAltera.setCodigoEmp(tCodEmp.getText());
				voAltera.setRazaoSocial(tRazao.getText());
				voAltera.setNomeFantasia(tFantasia.getText());
				voAltera.setCnpj(tCnpj.getText());
				voAltera.setInscrEst(tInscEstadual.getText());
				voAltera.setEndEmp(tEndereco.getText());
				voAltera.setNumeroEmp(tNumero.getText());
				voAltera.setBairroEmp(tBairro.getText());
				voAltera.setCidadeEmp(tCidade.getText());
				voAltera.setUfEmp((String)jcEstado.getSelectedItem());
				voAltera.setCepEmp(tCep.getText());
				voAltera.setFoneEmp(tTelefone.getText());

				EmpresaCtrl Controle = new EmpresaCtrl();
				String ret = Controle.alterar(voAltera);
				if(ret.trim().length() > 0){
					JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
				}
			}

		});
		/**
		 * Adicionando uma ação ao botão consultar.
		 */
		busca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EmpresaVO voConsulta = new EmpresaVO();
				EmpresaDAO daoConsulta = new EmpresaDAO();

				try {
					if (tCodEmp.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
						voConsulta = daoConsulta.pesquisar(Integer.valueOf(tCodEmp.getText()));
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
				tRazao.setText(voConsulta.getRazaoSocial());
				tFantasia.setText(voConsulta.getNomeFantasia());
				tCnpj.setText(voConsulta.getCnpj());
				tInscEstadual.setText(voConsulta.getInscrEst());
				tEndereco.setText(voConsulta.getEndEmp());
				tNumero.setText(String.valueOf(voConsulta.getNumeroEmp()));
				tBairro.setText(voConsulta.getBairroEmp());
				tCidade.setText(voConsulta.getCidadeEmp());
				jcEstado.setSelectedItem(voConsulta.getUfEmp());
				tCep.setText(voConsulta.getCepEmp());
				tTelefone.setText(voConsulta.getFoneEmp());
			}

		});

		/**
		 * Adicionando uma ação no botão excluir.
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EmpresaVO voExcluir = new EmpresaVO();
				voExcluir.setCodigoEmp(tCodEmp.getText());

				EmpresaCtrl Controle = new EmpresaCtrl();

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

				tCodEmp.setText("");
				tRazao.setText("");
				tFantasia.setText("");
				tCnpj.setText("");
				tInscEstadual.setText("");
				tEndereco.setText("");
				tNumero.setText("");
				tBairro.setText("");
				tCidade.setText("");
				tCep.setText("");
				tTelefone.setText("");

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
		tCodEmp.setText(cod);
		busca.doClick();
	}
}
