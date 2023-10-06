package funcoes;


	import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

	public class Ctextifield  extends JTextField{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		int maxlength;
		
		public Ctextifield(boolean key, boolean upper, int maxlength){
			super();
			this.maxlength = maxlength;
			if(key){
				this.addKeyListener(new KeyListener(){
		
					public void keyPressed(KeyEvent arg0) {
						if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
							arg0.consume();
							int y = ((Ctextifield)arg0.getSource()).getParent().getComponentZOrder(((Ctextifield)arg0.getSource()));
							
							Component c;
							try{
								c = (((Ctextifield)arg0.getSource()).getParent().getComponents()[y+=1]);
							}catch (Exception e) {
								c = null;
							}
							while ((c!=null)&&!( c instanceof Ctextifield)&&!(c instanceof CComboBox)&&!(c instanceof CPasswordField)) {
								try{
									c = (((Ctextifield)arg0.getSource()).getParent().getComponents()[y+=1]);
								}catch (Exception e) {
									c = null;
								}
							}
							if(c==null){
								Container cont;
								Container cult = null;
								cont = ((Ctextifield)arg0.getSource()).getParent();
								while(cont!=null){
									if(cont instanceof JTabbedPane){
										try{
											cult = cont;
											((JTabbedPane)cont).setSelectedIndex(((JTabbedPane)cont).getSelectedIndex()+1);
											cult = null;
											break;
										}catch (Exception e) {
											try{
												cont = cont.getParent();
											}catch (Exception ez) {
												cont = null;
											}
										}
									}else{
										try{
											cont = cont.getParent();
										}catch (Exception e) {
											e.printStackTrace();
											cont = null;
										}
									}
								}
								if(cult!=null){
									int x = cult.getParent().getComponentZOrder(cult);
									try{
										c = cult.getParent().getComponents()[x+=1];
									}catch (Exception ex) {
										c = null;
									}
									while ((c!=null)&&!( c instanceof Ctextifield)&&!(c instanceof CComboBox)&&!(c instanceof CPasswordField)&&!(c instanceof JButton)) {
										try{
											c = cult.getParent().getComponents()[x+=1];
										}catch (Exception ex) {
											c = null;
										}
									}
									if(c!=null){
										c.requestFocus();
									}
								}
							}else{
								c.requestFocus();
								if(c instanceof Ctextifield){
									((Ctextifield)c).setCaretPosition(0);
									((Ctextifield)c).moveCaretPosition(((Ctextifield)c).getText().length());
								}else if(c instanceof CPasswordField){
									((CPasswordField)c).setCaretPosition(0);
									((CPasswordField)c).moveCaretPosition(((CPasswordField)c).getPassword().length);
								}
							}
						}
						
					}
		
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
		
					public void keyTyped(KeyEvent arg0) {
						
					}
					
				});
			}
			if(upper){
				this.addKeyListener(new KeyListener(){
		
					public void keyPressed(KeyEvent arg0) {
						
					}
		
					public void keyReleased(KeyEvent arg0) {
						
					}
		
					public void keyTyped(KeyEvent arg0) {
						if(Ctextifield.this.maxlength>0){
							if(getText().length()>=Ctextifield.this.maxlength && arg0.getKeyCode()!=KeyEvent.VK_BACK_SPACE && getSelectedText()==null){
								arg0.consume();
								return;
							}
						}
						
						if(arg0.getKeyChar()>='a' && arg0.getKeyChar()<='z'){
							String x = String.valueOf(arg0.getKeyChar()); 
							setText(getText()+x.toUpperCase());
							arg0.consume();
						}
					}
				});
			}else if(this.maxlength>0){
				this.addKeyListener(new KeyListener(){
					
					public void keyPressed(KeyEvent arg0) {
						
					}
		
					public void keyReleased(KeyEvent arg0) {
						
					}
		
					public void keyTyped(KeyEvent arg0) {
						if(getText().length()>=Ctextifield.this.maxlength && arg0.getKeyCode()!=KeyEvent.VK_BACK_SPACE && getSelectedText()==null){
							arg0.consume();
							return;
						}
					}
				});
			}
		}
		
		public Ctextifield(){
			this(true, true, 0);
		}
	}
