package Interface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import Controle.ListagemCrtl;


public class Listagem extends JPanel{

	private JRadioButton jrCargo = new JRadioButton("Cargo");
	private JRadioButton jrEmpresa = new JRadioButton("Empresa");
	private JRadioButton jrFuncionario = new JRadioButton("Funcionario");
	private JRadioButton jrHistorico = new JRadioButton("Historico");
	private JRadioButton jrUsuario = new JRadioButton("Usuario");

	private ButtonGroup jbGlistar = new ButtonGroup();

	//Criando Os paineis (JPANEL).
	private JPanel jcampos = new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();

	private JButton gerar = new JButton("Gerar");

	public Listagem(){
		super (new BorderLayout());
		this.setBackground(Color.WHITE);

		int llinha = 30;
		int nlinha = 1;

		jcampos.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Listagem de Cadastro", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));

		jrUsuario.setBounds(90, nlinha * llinha, 200, 20);
		jbGlistar.add(jrUsuario);
		jcampos.add(jrUsuario);
		nlinha++;

		jrCargo.setBounds(90, nlinha * llinha, 200, 20);
		jbGlistar.add(jrCargo);
		jcampos.add(jrCargo);

		nlinha++;

		jrEmpresa.setBounds(90, nlinha * llinha, 200, 20);
		jbGlistar.add(jrEmpresa);
		jcampos.add(jrEmpresa);

		nlinha++;

		jrHistorico.setBounds(90, nlinha * llinha, 200, 20);
		jbGlistar.add(jrHistorico);
		jcampos.add(jrHistorico);

		nlinha++;

		jrFuncionario.setBounds(90, nlinha * llinha, 200, 20);
		jbGlistar.add(jrFuncionario);
		jcampos.add(jrFuncionario);

		nlinha++;

		gerar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ListagemCrtl ctrl = new ListagemCrtl();

				if(jrCargo.isSelected()){
					ctrl.lisCargo();
				}
				if(jrEmpresa.isSelected()){
					ctrl.lisEmpresa();
				}
				if(jrFuncionario.isSelected()){
					ctrl.lisFuncionario();
				}
				if(jrHistorico.isSelected()){
					ctrl.listHistorico();
				}
				if(jrUsuario.isSelected()){
					ctrl.listUsuario();
				}
			}

		});


		this.add(jcampos, BorderLayout.CENTER);

		//Configurando Painel dos Botoes.

		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		jposicao.add(gerar);
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
