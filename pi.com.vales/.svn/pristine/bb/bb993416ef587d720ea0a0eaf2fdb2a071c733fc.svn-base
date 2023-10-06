package funcoes;

import javax.swing.JComboBox;

import Persistencia.EstCampo;

public class CComboBox extends JComboBox {

	public CComboBox(){}
	/**
	 * Função Responsavel por pegar o index selecionado
	 */

	public void setValor(JComboBox combo, String value) {
		if(value!=null){
			for(int i = 0; i<combo.getItemCount(); i++){
				Object obj = combo.getItemAt(i);
				if(obj instanceof EstCampo){
					if(value.equals(((EstCampo) obj).valor)){
						combo.setSelectedIndex(i);
						break;
					}
				}else if(obj instanceof String){
					if(value.equals(obj)){
						combo.setSelectedIndex(i);
						break;
					}
				}
			}
		}else{
			combo.setSelectedIndex(0);
		}
	}
}
