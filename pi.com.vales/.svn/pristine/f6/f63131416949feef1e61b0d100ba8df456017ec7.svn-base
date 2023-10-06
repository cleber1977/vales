package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Persistencia.EmpresaDAO;
import Persistencia.EmpresaVO;

public class ConsultaEmpresa extends JFrame {

	private JLabel lLocalizar = new JLabel("Localizar");
	private JTextField tLocalizar = new JTextField();
	private JButton buscar = new JButton("Buscar");
	private JButton fechar = new JButton("Fechar");
	private JTextField tResposta = new JTextField();
	private JScrollPane jscrolConsulta = new JScrollPane();
	private JTable jtConsulta = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	
	//Tela que chamou a consulta
	private CadEmpresa telaPai;
	
	/**
	 * Criando Os paineis (JPANEL).
	 */
	JPanel jcampos = new JPanel(null);

	/**
	 * Criando o Construtor da classe.
	 */
	public ConsultaEmpresa(CadEmpresa tela){
		super("Consulta Empresa");
		telaPai = tela;
		
		this.setBackground(Color.WHITE);
		
		jcampos.setBackground(Color.WHITE);
		
		tResposta.setBounds(new Rectangle(5, 350, 715, 25));
		tResposta.setEditable(false);
		tResposta.setForeground(Color.RED);
		tResposta.setBackground(Color.WHITE);
		jcampos.add(tResposta);

		lLocalizar.setBounds(new Rectangle(5, 10, 68, 25));
		lLocalizar.setHorizontalAlignment(SwingConstants.RIGHT);
		jcampos.add(lLocalizar);

		tLocalizar.setBounds(new Rectangle(82, 10, 415, 25));
		tLocalizar.addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));	
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					buscar.doClick();
				}			
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
		jcampos.add(tLocalizar);

		buscar.setBounds(new Rectangle(520, 10, 95, 25));
		buscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//limpa grid
				int cont = dtm.getRowCount();
				int i=0;
				while (i < cont)
				{
					dtm.removeRow(0);             
					i = i + 1;
				}	
				
				String local = tLocalizar.getText();
				EmpresaDAO DAO = new EmpresaDAO();
				ArrayList<EmpresaVO> lista = null;
				
				if((local != null) && (local.trim().length() != 0)){
					lista = DAO.geral(null, local, "razaoSocial");
					if(lista == null){
						lista = DAO.geral(local, null, "codigoEmp");
						if(lista == null){
							tResposta.setText("Nenhum Registro Encontrado");
						}
					}
				}else{
					lista = DAO.geral(null, null, "codigoEmp");
				}
				
				if(lista != null){
					String[] linha = null;
					for (Iterator<EmpresaVO> iter = lista.iterator(); iter.hasNext();) {
						EmpresaVO vo = iter.next();
						
						linha = new String[]{String.valueOf(vo.getCodigoEmp()), vo.getRazaoSocial(), vo.getCnpj(), vo.getFoneEmp()};
						
						dtm.addRow(linha);
					}
				}
				jtConsulta.setModel(dtm);
				
				tLocalizar.setText("");
			}

		});
		jcampos.add(buscar);

		fechar.setBounds(new Rectangle(623, 10, 95, 25));
		fechar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
		jcampos.add(fechar);
		
		//cria colunas
		dtm.addColumn("Código*");
		dtm.addColumn("Razao Social");
		dtm.addColumn("CNPJ / CNPJ");	
		dtm.addColumn("Telefone");
				
		//coloca colunas na tabela
		jtConsulta.setModel(dtm);
		jtConsulta.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int coluna = jtConsulta.getSelectedColumn();
				if(coluna == 0){
					int linha = jtConsulta.getSelectedRow();
					if(linha >= 0){
						telaPai.consultou((String)jtConsulta.getValueAt(linha, 0));
						dispose();
					}
				}
			}			
		});
		
		//coloca a tabela no scroll
		jscrolConsulta.setBounds(new Rectangle(5, 41, 715, 304));
		jscrolConsulta.setViewportView(jtConsulta);
		jcampos.add(jscrolConsulta);
		
		this.add(jcampos,BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setSize(731, 407);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
