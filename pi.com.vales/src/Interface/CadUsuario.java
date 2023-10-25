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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import Controle.UsuarioCtrl;
import Persistencia.UsuarioDAO;
import Persistencia.UsuarioVO;
import funcoes.CPasswordField;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 * Data 19/10/2009
 */
public class CadUsuario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lCodUsu = new JLabel("Codigo");
	private JLabel lNomeUsu = new JLabel("Nome");
	private JLabel lSenhaUsu = new JLabel("Senha");
	private JLabel lNivel = new JLabel("Nivel");


	private JComboBox jcNivel = new JComboBox(new String[]{"0-Diretor", "1-Gerente"});

	private JTextField tCodUsu = new JTextField();
	private JTextField tNomeUsu = new JTextField();	
	private CPasswordField tSenhaUsu = new CPasswordField(true, 6);


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
	public CadUsuario(){
		super(new BorderLayout());
		this.setBackground(Color.WHITE);

		int ulinha = 30;
		int nlinha = 1;
		
		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cadastro de Usuarios", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));

		lCodUsu.setBounds(10, nlinha * ulinha, 100, 20);
		jcampos.add(lCodUsu);
		tCodUsu.setBounds(85, nlinha* ulinha, 80, 20);
		jcampos.add(tCodUsu);
		//tCodUsu.addFocusListener(arg0);


		busca.setBounds(170, nlinha * ulinha, 70, 20);
		jcampos.add(busca);

		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * ulinha, 20, 20);
		jcampos.add(pesquisa);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaUsuario(CadUsuario.this);
			}

		});


		nlinha++;

		lNomeUsu.setBounds(10, nlinha * ulinha, 100, 20);
		jcampos.add(lNomeUsu);
		tNomeUsu.setBounds(85, nlinha * ulinha, 200, 20);
		jcampos.add(tNomeUsu);

		// Adicionando evento de colocar maiusculo quando digitar o texto.
		tNomeUsu.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tNomeUsu.getText().length() >= 8){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		nlinha++;

		lSenhaUsu.setBounds(10, nlinha * ulinha, 100, 20);
		jcampos.add(lSenhaUsu);
		tSenhaUsu.setBounds(85, nlinha * ulinha, 100, 20);
		jcampos.add(tSenhaUsu);

		nlinha++;
		lNivel.setBounds(10, nlinha * ulinha, 100, 20);
		jcampos.add(lNivel);
		jcNivel.setBounds(85, nlinha * ulinha, 150, 20);
		jcampos.add(jcNivel);	

		this.add(jcampos,BorderLayout.CENTER);

		nlinha = 2;
		ulinha = 5;

		// Configurando os Botões.	
		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		jposicao.add(incluir);
		/**
		 * Adicionando ação no botão Inlcuir
		 */
		incluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UsuarioVO novoUsuario = new UsuarioVO();

				novoUsuario.setNomeUsuario(tNomeUsu.getText());
				novoUsuario.setSenhaUsuario(new String(tSenhaUsu.getPassword()));
				novoUsuario.setNivelUsuario(String.valueOf(jcNivel.getSelectedIndex()));

				UsuarioCtrl Controle = new UsuarioCtrl();
				ArrayList<String> ret = Controle.incluir(novoUsuario);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tCodUsu.setText(ret.get(1));
			}

		});
		/**
		 * Adicionando ação no botão Alterar(gravar)
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UsuarioVO voAltera = new UsuarioVO();

				voAltera.setCodigoUsuario(tCodUsu.getText());
				voAltera.setNomeUsuario(tNomeUsu.getText());
				voAltera.setSenhaUsuario(new String(tSenhaUsu.getPassword()));
				voAltera.setNivelUsuario(String.valueOf(jcNivel.getSelectedIndex()));

				UsuarioCtrl Controle = new UsuarioCtrl();
				String ret = Controle.alterar(voAltera);
				JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);
			}

		});

		/**
		 * Adicionando ação no botão Consultar
		 */
		busca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UsuarioVO voConsulta = new UsuarioVO();
				UsuarioDAO daoConsulta = new UsuarioDAO();
				try {
					if (tCodUsu.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
						voConsulta = daoConsulta.pesquisar(Integer.valueOf(tCodUsu.getText()));	
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
				tNomeUsu.setText(voConsulta.getNomeUsuario());
				tSenhaUsu.setText(voConsulta.getSenhaUsuario());
				jcNivel.setSelectedIndex(voConsulta.getNivelUsuario_Int());
			}
		});

		/**
		 * Adicionando ação no botão excluir
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				UsuarioVO voExclui = new UsuarioVO();

				voExclui.setCodigoUsuario(tCodUsu.getText());
				jcNivel.setSelectedIndex(voExclui.getNivelUsuario_Int());

				UsuarioCtrl Controle = new UsuarioCtrl();

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

				tCodUsu.setText("");
				tNomeUsu.setText("");
				tSenhaUsu.setText("");
				jcNivel.setSelectedIndex(0);
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
		tCodUsu.setText(cod);
		busca.doClick();
	}
}
