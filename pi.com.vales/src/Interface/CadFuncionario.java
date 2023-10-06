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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import Controle.CargoCtrl;
import Controle.EmpresaCtrl;
import Controle.FuncionarioCtrl;
import Controle.RelatorioCtrl;
import Persistencia.EstCampo;
import Persistencia.FuncionarioDAO;
import Persistencia.FuncionarioVO;
import funcoes.CComboBox;
import funcoes.funcaoData;
/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 *  Data 29/10/2009
 */

public class CadFuncionario extends JPanel{
	private JLabel lCodFunc = new JLabel("Codigo");
	private JLabel lNomeFunc = new JLabel("Nome");
	private JLabel lCodemp = new JLabel("Empresa");
	private JLabel lCodCargo = new JLabel("Cargo");
	private JLabel lSalario= new JLabel("Salario");
	private JLabel lEndereco= new JLabel("Endereço");
	private JLabel lBairro = new JLabel("Bairro");
	private JLabel lCidade = new JLabel("Cidade");
	private JLabel lCep = new JLabel("Cep");
	private JLabel lestado= new JLabel("Estado");
	private JLabel lCpffun=new JLabel("CPF");
	private JLabel lTelefone = new JLabel("Telefone");
	private JLabel lCelular = new JLabel("Celular");
	private JLabel lDataAdmissao= new JLabel("Admissão");
	private JLabel lDataDemissao= new JLabel("Demissao");
	private JLabel lMesAnoFechad = new JLabel("Mes/ano");
	private JLabel lDataNascimento = new JLabel("Nascimento");
	private JLabel lRgFuncionario = new JLabel("Identidade");
	private JLabel lCtpsfunc = new JLabel("CTPS");
	private JLabel lSexo = new JLabel("Sexo");

	private JLabel lNomePai = new JLabel ("Nome Pai:");
	private JLabel lNomeMae = new JLabel("Nome Mae");
	private JLabel lEndPais= new JLabel("Endereço");
	private JLabel lBairroPais = new JLabel("Bairro");
	private JLabel lCidadePais = new JLabel("Cidade");
	private JLabel lEstadoPais= new JLabel("Estado");
	private JLabel lFonePais = new JLabel("Telefone");


	private JComboBox jcEmpresa = new JComboBox(new String[]{});
	private JComboBox jcCargo = new JComboBox(new String[]{});
	private JComboBox jcEstado = new JComboBox(new String[]{"GO", "MG","PA", "RJ", "SP", "DF","MS", "TO"});
	private JComboBox jcEstadoPais = new JComboBox(new String[]{"GO", "MG","PA", "RJ", "SP", "DF","MS", "TO"});

	private JTextField tCodFunc = new JTextField();
	private JTextField tNomeFunc = new JTextField();
	private JTextField tSalario = new JTextField();
	private JTextField tEndereco = new JTextField();
	private JTextField tBairro = new JTextField();
	private JTextField tCidade = new JTextField();
	private JTextField tCep = new JTextField();
	private JFormattedTextField tCpfFunc = new JFormattedTextField();
	private JFormattedTextField tTelefone = new JFormattedTextField();
	private JFormattedTextField tCelular =  new JFormattedTextField();
	private JFormattedTextField tDataAdmissao = new JFormattedTextField();
	private JFormattedTextField tDataDemissao =  new JFormattedTextField();
	private JTextField tMesAnoFechado = new JTextField();
	private JFormattedTextField tDataNascimento = new JFormattedTextField();
	private JTextField tRgFuncionario = new JTextField();
	private JTextField tCpts = new JTextField();
	private JTextField tNomePai = new JTextField();
	private JTextField tNomeMae = new JTextField();
	private JTextField tEndPais = new JTextField();
	private JTextField tBairroPais = new JTextField();
	private JTextField tCidadePais = new JTextField();
	private JFormattedTextField tFonePais = new JFormattedTextField();

	private JRadioButton jrbMasc = new JRadioButton("Masculino");
	private JRadioButton jrbFem = new JRadioButton("Feminino");

	private ButtonGroup jbG = new ButtonGroup();

	/**
	 * Criando os Botões.
	 */
	private JButton incluir = new JButton("Incluir");
	private JButton alterar = new JButton("Alterar");
	private JButton busca = new JButton("Busca");
	private JButton excluir = new JButton("Excluir");
	private JButton pesquisa = new JButton();
	private JButton limpar = new JButton("Limpa");
	private JButton imprimir = new JButton("Imprimir");

	/**
	 * Criando Os paineis (JPANEL).
	 */
	private JPanel jcamposPessoa = new JPanel(null);
	private JPanel Jcampofili = new JPanel(null);
	private JPanel jbotoes = new JPanel();
	private JPanel jposicao = new JPanel();
	private JPanel jimagem = new JPanel();


	/**
	 * Criando o Construtor da classe.
	 */
	public CadFuncionario(){
		super(new BorderLayout());

		JTabbedPane abasFuncionario = new JTabbedPane(JTabbedPane.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
		this.add(abasFuncionario);
		abasFuncionario.add("Pessoais", jcamposPessoa);
		this.setBackground(Color.WHITE);
		int flinha = 25;
		int nlinha = 1;
		jcamposPessoa.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Cadastro Funcionário", TitledBorder.CENTER, TitledBorder.TOP, new Font(Font.MONOSPACED, Font.BOLD, 16)));

		busca.setBounds(170, nlinha * flinha, 70, 20);
		jcamposPessoa.add(busca);

		pesquisa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/lupa.jpg")));
		pesquisa.setBounds(255, nlinha * flinha, 20, 20);
		jcamposPessoa.add(pesquisa);
		pesquisa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaFuncionario(CadFuncionario.this);
			}

		});




		lCodFunc.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCodFunc);
		tCodFunc.setBounds(85, nlinha * flinha, 80, 20);
		jcamposPessoa.add(tCodFunc);

		nlinha++;

		lNomeFunc.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lNomeFunc);
		tNomeFunc.setBounds(85, nlinha * flinha, 250, 20);
		jcamposPessoa.add(tNomeFunc);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tNomeFunc.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tNomeFunc.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		nlinha++;


		lDataNascimento.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lDataNascimento);
		tDataNascimento.setBounds(85, nlinha * flinha, 80, 20);
		jcamposPessoa.add(tDataNascimento);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataNascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jbG.add(jrbMasc);
		jbG.add(jrbFem);

		//Colocando no JPanel
		lSexo.setBounds(240, nlinha * flinha, 40, 20);
		jcamposPessoa.add(lSexo);
		jrbMasc.setBounds(290, nlinha * flinha, 90, 20);
		jcamposPessoa.add(jrbMasc);
		jrbFem.setBounds(390, nlinha * flinha, 90, 20);
		jcamposPessoa.add(jrbFem);

		nlinha++;

		lEndereco.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lEndereco);
		tEndereco.setBounds(85, nlinha * flinha, 335, 20);
		jcamposPessoa.add(tEndereco);

		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tEndereco.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tEndereco.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		lCep.setBounds(450, nlinha * flinha, 80, 20);
		jcamposPessoa.add(lCep);
		tCep.setBounds(510, nlinha * flinha, 100, 20);
		jcamposPessoa.add(tCep);
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

		nlinha++;
		lBairro.setBounds(10, nlinha * flinha, 70, 20);
		jcamposPessoa.add(lBairro);
		tBairro.setBounds(85, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tBairro);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tBairro.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tBairro.getText().length() >= 35){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		lCidade.setBounds(240, nlinha * flinha, 80, 20);
		jcamposPessoa.add(lCidade);
		tCidade.setBounds(290, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tCidade);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tCidade.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tCidade.getText().length() >= 35){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		lestado.setBounds(450, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lestado);
		jcEstado.setBounds(510, nlinha * flinha, 50, 20);
		jcamposPessoa.add(jcEstado);

		nlinha++;
		lTelefone.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lTelefone);
		tTelefone.setBounds(85, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tTelefone);
		try {
			MaskFormatter mk = new MaskFormatter ("(##)####-####");
			mk.install(tTelefone);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lCelular.setBounds(240, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCelular);
		tCelular.setBounds(290, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tCelular);
		try {
			MaskFormatter mk = new MaskFormatter ("(##)####-####");
			mk.install(tCelular);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nlinha++;

		EmpresaCtrl controleEmpresa = new EmpresaCtrl();
		jcEmpresa = new JComboBox(controleEmpresa.comboEmpresa().toArray());

		lCodemp.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCodemp);
		jcEmpresa.setBounds(85, nlinha * flinha, 305, 20);
		jcamposPessoa.add(jcEmpresa);

		nlinha++;

		CargoCtrl controleCargo = new CargoCtrl();
		jcCargo = new JComboBox(controleCargo.comboCargo().toArray());

		lCodCargo.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCodCargo);
		jcCargo.setBounds(85, nlinha * flinha, 305, 20);
		jcamposPessoa.add(jcCargo);
		lSalario.setBounds(450, nlinha * flinha, 70, 20);
		jcamposPessoa.add(lSalario);
		tSalario.setBounds(510, nlinha * flinha, 80, 20);
		jcamposPessoa.add(tSalario);


		nlinha++;

		lCpffun.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCpffun);
		tCpfFunc.setBounds(85, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tCpfFunc);

		try {
			MaskFormatter mk = new MaskFormatter ("###.###.###-##");
			mk.install(tCpfFunc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lRgFuncionario.setBounds(240, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lRgFuncionario);
		tRgFuncionario.setBounds(310, nlinha * flinha, 100, 20);
		jcamposPessoa.add(tRgFuncionario);

		lCtpsfunc.setBounds(450, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lCtpsfunc);
		tCpts.setBounds(485, nlinha * flinha, 120, 20);
		jcamposPessoa.add(tCpts);

		nlinha++;

		lDataAdmissao.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lDataAdmissao);
		tDataAdmissao.setBounds(85, nlinha * flinha, 130, 20);
		jcamposPessoa.add(tDataAdmissao);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataAdmissao);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lDataDemissao.setBounds(240, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lDataDemissao);
		tDataDemissao.setBounds(310, nlinha * flinha, 100, 20);
		jcamposPessoa.add(tDataDemissao);

		try {
			MaskFormatter mk = new MaskFormatter ("##/##/####");
			mk.install(tDataDemissao);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nlinha++;

		lMesAnoFechad.setBounds(10, nlinha * flinha, 100, 20);
		jcamposPessoa.add(lMesAnoFechad);
		tMesAnoFechado.setBounds(85, nlinha * flinha, 90, 20);
		jcamposPessoa.add(tMesAnoFechado);

		this.add(abasFuncionario);

		/**
		 * Criando os Botões.
		 */
		jbotoes.setBackground(Color.WHITE);
		jposicao.setBackground(Color.WHITE);
		jposicao.add(incluir);

		/**
		 * Adicionando uma ação ao botão incluir
		 */
		incluir.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String sexo = "";
				if(jrbMasc.isSelected()){
					sexo = "M";
				}else{
					sexo = "F";
				}

				FuncionarioVO novoFuncionario = new FuncionarioVO();
				novoFuncionario.setNomeFun(tNomeFunc.getText());
				novoFuncionario.setRgFun(tRgFuncionario.getText());
				novoFuncionario.setCpfFun(tCpfFunc.getText());
				novoFuncionario.setCtpsFun(tCpts.getText());
				novoFuncionario.setDataNascFun(tDataNascimento.getText());
				novoFuncionario.setSexoFun(sexo);
				novoFuncionario.setEndFun(tEndereco.getText());
				novoFuncionario.setBairroFun(tBairro.getText());
				novoFuncionario.setCidadeFun(tCidade.getText());
				novoFuncionario.setUfFun((String)jcEstado.getSelectedItem());
				novoFuncionario.setCodigoEmp(((EstCampo)(jcEmpresa.getSelectedItem())).valor);
				novoFuncionario.setCodigoCargo(((EstCampo)(jcCargo.getSelectedItem())).valor);
				novoFuncionario.setAdmissaoFun(tDataAdmissao.getText());
				novoFuncionario.setDemissaoFun(tDataDemissao.getText());
				novoFuncionario.setSalarioCargo(tSalario.getText());
				novoFuncionario.setCepFun(tCep.getText());
				novoFuncionario.setFoneFun(tTelefone.getText());
				novoFuncionario.setCelularFun(tCelular.getText());
				novoFuncionario.setPaiFun(tNomePai.getText());
				novoFuncionario.setMaeFun(tNomeMae.getText());
				novoFuncionario.setEnderecoPais(tEndPais.getText());
				novoFuncionario.setBairroPais(tBairroPais.getText());
				novoFuncionario.setCidadePais(tCidadePais.getText());
				novoFuncionario.setUfPais((String)jcEstadoPais.getSelectedItem()); 
				novoFuncionario.setFonePais(tFonePais.getText());
				novoFuncionario.setMesfechado(tMesAnoFechado.getText());

				FuncionarioCtrl Controle = new FuncionarioCtrl();
				ArrayList<String> ret = Controle.incluir(novoFuncionario);
				JOptionPane.showMessageDialog(null,  ret.get(0), null,   JOptionPane.INFORMATION_MESSAGE);
				tCodFunc.setText(ret.get(1));
			}

		});

		/**
		 *Adicionando acao ao botão gravar. 
		 */
		jposicao.add(alterar);
		alterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String sexo = "";
				if(jrbMasc.isSelected()){
					sexo = "M";
				}else{
					sexo = "F";
				}

				FuncionarioVO voAltera = new FuncionarioVO();
				voAltera.setCodigoFun(tCodFunc.getText());
				voAltera.setNomeFun(tNomeFunc.getText());
				voAltera.setRgFun(tRgFuncionario.getText());
				voAltera.setCpfFun(tCpfFunc.getText());
				voAltera.setCtpsFun(tCpts.getText());
				voAltera.setDataNascFun(tDataNascimento.getText());
				voAltera.setSexoFun(sexo);
				voAltera.setEndFun(tEndereco.getText());
				voAltera.setBairroFun(tBairro.getText());
				voAltera.setCidadeFun(tCidade.getText());
				voAltera.setUfFun((String)jcEstado.getSelectedItem());
				voAltera.setCodigoEmp(((EstCampo)(jcEmpresa.getSelectedItem())).valor);
				voAltera.setCodigoCargo(((EstCampo)(jcCargo.getSelectedItem())).valor);	
				voAltera.setAdmissaoFun(tDataAdmissao.getText());
				voAltera.setDemissaoFun(tDataDemissao.getText());
				voAltera.setSalarioCargo(tSalario.getText());
				voAltera.setCepFun(tCep.getText());
				voAltera.setFoneFun(tTelefone.getText());
				voAltera.setCelularFun(tCelular.getText());
				voAltera.setPaiFun(tNomePai.getText());
				voAltera.setMaeFun(tNomeMae.getText());
				voAltera.setEnderecoPais(tEndPais.getText());
				voAltera.setBairroPais(tBairroPais.getText());
				voAltera.setCidadePais(tCidadePais.getText());
				voAltera.setUfPais((String)jcEstadoPais.getSelectedItem()); 
				voAltera.setFonePais(tFonePais.getText());
				voAltera.setMesfechado(tMesAnoFechado.getText());

				FuncionarioCtrl Controle = new FuncionarioCtrl();
				String ret = Controle.alterar(voAltera);
				JOptionPane.showMessageDialog(null,  ret, null,   JOptionPane.INFORMATION_MESSAGE);

			}

		});

		/**
		 * Adicionando uma ação ao botão consultar.
		 */
		busca.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FuncionarioVO voConsulta = new FuncionarioVO();
				FuncionarioDAO DaoFuncionaio = new FuncionarioDAO();
				try {
					if (tCodFunc.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null,  "Informe Um Codigo", null,   JOptionPane.INFORMATION_MESSAGE);
					}else {
						voConsulta = DaoFuncionaio.pesquisar(Integer.valueOf(tCodFunc.getText()));
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

				if(voConsulta.getSexoFun()!= null && voConsulta.getSexoFun().equals("M")){
					jrbMasc.setSelected(true);
				}else{
					jrbFem.setSelected(true);
				}

				CComboBox c = new CComboBox();
				c.setValor(jcCargo, ""+voConsulta.getCodigoCargo());
				c.setValor(jcEmpresa, ""+voConsulta.getCodigoEmp());

				tNomeFunc.setText(voConsulta.getNomeFun());
				tRgFuncionario.setText(voConsulta.getRgFun());
				tCpfFunc.setText(voConsulta.getCpfFun());
				tCpts.setText(voConsulta.getCtpsFun());
				tDataNascimento.setText(voConsulta.getDataNascFunBusca());
				tEndereco.setText(voConsulta.getEndFun());
				tBairro.setText(voConsulta.getBairroFun());
				tCidade.setText(voConsulta.getCidadeFun());
				jcEstado.setSelectedItem(voConsulta.getUfFun());
				jcEmpresa.setSelectedItem(voConsulta.getCodigoEmp());
				jcCargo.setSelectedItem(voConsulta.getCodigoCargo());
				tDataAdmissao.setText(voConsulta.getAdmissaoFunBusca());
				tDataDemissao.setText(voConsulta.getDemissaoFunBusca());
				tSalario.setText(String.valueOf(voConsulta.getSalarioCargo()));
				tCep.setText(voConsulta.getCepFun());
				tTelefone.setText(voConsulta.getFoneFun());
				tCelular.setText(voConsulta.getCelularFun());
				tNomePai.setText(voConsulta.getPaiFun());
				tNomeMae.setText(voConsulta.getMaeFun());
				tEndPais.setText(voConsulta.getEnderecoPais());
				tBairroPais.setText(voConsulta.getBairroPais());
				tCidadePais.setText(voConsulta.getCidadePais());
				tFonePais.setText(voConsulta.getFonePais());
				tMesAnoFechado.setText(voConsulta.getMesfechado());
				jcEstadoPais.setSelectedItem(voConsulta.getUfPais());
			}
		});
		/**
		 * Adicionando uma ação no botão excluir.
		 */
		jposicao.add(excluir);
		excluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				FuncionarioVO voExcluir = new FuncionarioVO();
				voExcluir.setCodigoFun(tCodFunc.getText());

				FuncionarioCtrl Controle = new FuncionarioCtrl();

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

				tCodFunc.setText("");
				tNomeFunc.setText("");
				tRgFuncionario.setText("");
				tCpfFunc.setText("");
				tCpts.setText("");
				tDataNascimento.setText("");
				tEndereco.setText("");
				tBairro.setText("");
				tCidade.setText("");
				jcEmpresa.setSelectedIndex(0);
				jcCargo.setSelectedIndex(0);
				tDataAdmissao.setText("");
				tDataDemissao.setText("");
				tSalario.setText("");
				tCep.setText("");
				tTelefone.setText("");
				tCelular.setText("");
				tNomePai.setText("");
				tNomeMae.setText("");
				tEndPais.setText("");
				tBairroPais.setText("");
				tCidadePais.setText("");
				tFonePais.setText("");
				tMesAnoFechado.setText("");


			}
		});

		jposicao.add(imprimir);
		imprimir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				FuncionarioVO voFicha = new FuncionarioVO();
				voFicha.setCodigoFun(tCodFunc.getText());

				RelatorioCtrl controle = new RelatorioCtrl();
				controle.fichaFuncionario(voFicha);	
				String msm = "";
				if (msm.length() > 0) {
					JOptionPane.showMessageDialog(null, msm);	
				}
			}

		});

		jbotoes.setLayout(new BorderLayout());
		jbotoes.add(jposicao, BorderLayout.EAST);
		jbotoes.setPreferredSize(new Dimension(500,40));
		jbotoes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(jbotoes,BorderLayout.NORTH);

		//Configurando imagem em um painel.
		jimagem.setBackground(Color.WHITE);
		jcamposPessoa.add(jimagem, BorderLayout.EAST);
		jimagem.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/logomarca.jpeg"))));
		this.add(jimagem,BorderLayout.SOUTH);	

		//Filiacao Dados dos Pais.

		abasFuncionario.add(Jcampofili);
		abasFuncionario.add("Filiação", Jcampofili);
		int linhaf = 30;
		int linhafili = 1;
		this.setBackground(Color.WHITE);
		Jcampofili.setBackground(Color.WHITE);

		lNomePai.setBounds(10, linhafili * linhaf, 100, 20);;
		Jcampofili.add(lNomePai);
		tNomePai.setBounds(85, linhafili * linhaf, 200, 20);
		Jcampofili.add(tNomePai);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tNomePai.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tNomePai.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		linhafili++;
		lNomeMae.setBounds(10, linhafili * linhaf, 100, 20);;
		Jcampofili.add(lNomeMae);
		tNomeMae.setBounds(85, linhafili * linhaf, 200, 20);
		Jcampofili.add(tNomeMae);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tNomeMae.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tNomeMae.getText().length() >= 45){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		linhafili++;

		lEndPais.setBounds(10, linhafili * linhaf, 100, 20);
		Jcampofili.add(lEndPais);
		tEndPais.setBounds(85, linhafili * linhaf, 300, 20);
		Jcampofili.add(tEndPais);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tEndPais.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tEndPais.getText().length() >= 50){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		linhafili++;
		lFonePais.setBounds(10, linhafili * linhaf, 100, 20);
		Jcampofili.add(lFonePais);
		tFonePais.setBounds(85, linhafili * linhaf, 130, 20);
		Jcampofili.add(tFonePais);

		try {
			MaskFormatter mk = new MaskFormatter ("(##)####-####");
			mk.install(tFonePais);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		linhafili++;

		lBairroPais.setBounds(10, linhafili * linhaf, 100, 20);
		Jcampofili.add(lBairroPais);
		tBairroPais.setBounds(85, linhafili * linhaf, 130, 20);
		Jcampofili.add(tBairroPais);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tBairroPais.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tBairroPais.getText().length() >= 35){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		linhafili++;
		lCidadePais.setBounds(10, linhafili * linhaf, 100, 20);
		Jcampofili.add(lCidadePais);
		tCidadePais.setBounds(85, linhafili * linhaf, 130, 20);
		Jcampofili.add(tCidadePais);
		/**
		 * Adicionando evento de colocar maiusculo quando digitar o testo.
		 */
		tCidadePais.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				if(tCidadePais.getText().length() >= 35){
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}			
		});

		lEstadoPais.setBounds(260, linhafili * linhaf, 100, 20);
		Jcampofili.add(lEstadoPais);
		jcEstadoPais.setBounds(310, linhafili * linhaf, 60, 20);
		Jcampofili.add(jcEstadoPais);

		linhafili++;
	}
	public void consultou(String cod){
		tCodFunc.setText(cod);
		busca.doClick();
	}
}
