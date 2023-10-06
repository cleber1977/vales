package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import Conexao.Database;
import Controle.LoginCtrl;
import Persistencia.UsuarioVO;
import funcoes.CPasswordField;

public class Login extends JFrame
{
	private static JTabbedPane abas_login;
	private JLabel lNomeUsu = new JLabel("Usuario:");
	private JLabel lSenhaUsu = new JLabel("Senha:");
	private JTextField tNomeUsu = new JTextField();
	private CPasswordField tSenhaUsu = new CPasswordField();
	private JButton ok = new JButton("OK");
	private JButton Cancelar = new JButton("Cancelar");

	private JLabel lDatabase = new JLabel("Database:");
	private JLabel lhost = new JLabel("Servidor:");
	private JTextField tDatabase = new JTextField();
	private JTextField thost = new JTextField();

	public Login(String banco, String ip)
	{
		super("Login Vales");
		setBackground(Color.WHITE);

		Set forwardKeys = new HashSet(getFocusTraversalKeys(0));
		forwardKeys.add(KeyStroke.getKeyStroke(10, 0));
		setFocusTraversalKeys(0, forwardKeys);

		Set backwardKeys = new HashSet(getFocusTraversalKeys(1));
		backwardKeys.add(KeyStroke.getKeyStroke(10, 64));
		setFocusTraversalKeys(1, backwardKeys);

		abas_login = new JTabbedPane(1, 1);

		JPanel jcampos = new JPanel(null);

		int clinha = 30;
		int nlinha = 1;
		jcampos.setBackground(Color.WHITE);
		jcampos.add(this.lNomeUsu);

		this.lNomeUsu.setBounds(10, nlinha * clinha, 100, 20);
		this.tNomeUsu.setBounds(110, nlinha * clinha, 80, 20);
		jcampos.add(this.tNomeUsu);

		this.tNomeUsu.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (Login.this.tNomeUsu.getText().length() >= 14) {
					e.consume();
				}
				e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
			}
		});
		++nlinha;
		this.lSenhaUsu.setBounds(10, nlinha * clinha, 100, 20);
		jcampos.add(this.lSenhaUsu);
		this.tSenhaUsu.setBounds(110, nlinha * clinha, 80, 20);
		jcampos.add(this.tSenhaUsu);

		++nlinha;

		this.ok.setBounds(110, nlinha * clinha, 60, 20);
		this.ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				if ((Login.this.thost.getText().trim().length() > 0) && (Login.this.tDatabase.getText().trim().length() > 0))
				{
					Database.atualizaConection(Login.this.thost.getText(), Login.this.tDatabase.getText());
					if (Database.testConection())
					{
						UsuarioVO vologar = new UsuarioVO();
						vologar.setNomeUsuario(Login.this.tNomeUsu.getText());
						vologar.setSenhaUsuario(new String(Login.this.tSenhaUsu.getPassword()));

						LoginCtrl Controle = new LoginCtrl();
						String ret = Controle.logar(vologar);

						if (ret == null)
						{
							new Sistema();

							Principal_main.log.dispose();
						} else {
							JOptionPane.showMessageDialog(null, ret, null, 1);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Verifica parametros da conexao", null, 1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Informe o database e o servidor", null, 1);
				}
			}
		});
		jcampos.add(this.ok);

		this.Cancelar.setBounds(190, nlinha * clinha, 90, 20);
		this.Cancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		jcampos.add(this.Cancelar);

		JPanel conect = new JPanel(null);
		conect.setBackground(Color.WHITE);

		clinha = 30;
		nlinha = 1;

		this.lDatabase.setBounds(10, nlinha * clinha, 100, 20);
		conect.add(this.lDatabase);
		tDatabase.setText(ip);
		this.tDatabase.setBounds(110, nlinha * clinha, 100, 20);
		conect.add(this.tDatabase);

		++nlinha;

		this.lhost.setBounds(10, nlinha * clinha, 100, 20);
		conect.add(this.lhost);
		thost.setText(banco);
		this.thost.setBounds(110, nlinha * clinha, 100, 20);
		conect.add(this.thost);

		abas_login.add("Login", jcampos);
		abas_login.add("Conexão", conect);

		add(abas_login);
		setVisible(true);

		//Setando primeiro foco na tela.
		SwingUtilities.invokeLater(new Runnable() {    
			public void run() {    
				tNomeUsu.requestFocus();    
			}    
		}); 
		setSize(400, 300);
	}
}