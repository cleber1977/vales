package Interface;

import java.awt.Desktop;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Sistema extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static Login log;
	private static JTabbedPane abas;

	public Sistema()
	{
		super("Controle de Pagamentos Funcionarios");

		Set forwardKeys = new HashSet(getFocusTraversalKeys(0));
		forwardKeys.add(KeyStroke.getKeyStroke(10, 0));
		setFocusTraversalKeys(0, forwardKeys);

		Set backwardKeys = new HashSet(getFocusTraversalKeys(1));
		backwardKeys.add(KeyStroke.getKeyStroke(10, 64));
		setFocusTraversalKeys(1, backwardKeys);

		abas = new JTabbedPane(2, 1);

		abas.add("Tela Principal", new TelaPrincipal());
		abas.add("Cad Usuario", new CadUsuario());
		abas.add("Cad Empresa", new CadEmpresa());
		abas.add("Cad Cargo", new CadCargo());
		abas.add("Cad Funcionario", new CadFuncionario());
		abas.add("Cad Historico", new CadHistorico());
		abas.add("Lancamento", new Lancamento());
		abas.add("Lanc. Automatico", new LancAutomatico());
		abas.add("Relatorios", new Relatorio());
		abas.add("Listagens", new Listagem());
		abas.add("Baixa Titulos", new BaixaProFuncionario());

		abas.add("Help - F1", null);
		abas.add("Sair", null);

		abas.addMouseListener(new MouseAdapter()
		{

			public void mouseClicked(MouseEvent arg0) {
				System.out.println(Sistema.abas.getSelectedIndex());
				if (Sistema.abas.getSelectedIndex() == 11) {
					try {
						Desktop.getDesktop().open(new File("Relatorio/Help_Vales.pdf"));
					}
					catch (Exception localException)
					{
					}
				}

				if (Sistema.abas.getSelectedIndex() == 4) {
					abas.setComponentAt(4, new CadFuncionario());
				}

				if (Sistema.abas.getSelectedIndex() == 6) {
					abas.setComponentAt(6, new Lancamento());
				}

				if (Sistema.abas.getSelectedIndex() == 7) {
					abas.setComponentAt(7, new LancAutomatico());
				}

				if (Sistema.abas.getSelectedIndex() == 8) {
					abas.setComponentAt(8, new Relatorio());
				}

				if (Sistema.abas.getSelectedIndex() == 10) {
					abas.setComponentAt(10, new BaixaProFuncionario());
				}

				if (Sistema.abas.getSelectedIndex() != 12) return;
				try {
					System.exit(0);
				}
				catch (Exception localException1)
				{
				}
			}
		});
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher()
		{
			public boolean dispatchKeyEvent(KeyEvent arg0)
			{
				if ((arg0.getID() == 402) && (arg0.getKeyCode() == 112)) {
					try {
						Desktop.getDesktop().open(new File("Relatorio/help.pdf"));
					}
					catch (Exception localException)
					{
					}
				}
				return false;
			}
		});
		add(abas);

	
	 	setVisible(true);
		setSize(800, 600);

		setDefaultCloseOperation(3);
	}

	public static void abaVisivel(boolean qual) {
		abas.setVisible(qual);
	}

	public static void logVisivel(boolean qual) {
		log.setVisible(qual);
	}
}
