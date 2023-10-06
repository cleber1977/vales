package Interface;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe responsavel pela interface com usuario
 * @author Cleber / Moacir
 * Data 19/10/2009
 */
public class TelaPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Criando Os paineis (JPANEL).
	 */
	private JPanel jimagemP = new JPanel();
	
	private JPanel jcampos = new JPanel(null);

	/**
	 * Criando o Construtor da classe.
	 */
	public TelaPrincipal(){
		super(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		jimagemP.setBackground(Color.WHITE);
		jcampos.add(jimagemP, BorderLayout.CENTER);
		jimagemP.add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("imagem/TelPrinPi_com.jpg"))));
		this.add(jimagemP,BorderLayout.CENTER);		

		}
	
}
