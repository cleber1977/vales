package Persistencia;

/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 29/10/2009
 */
public class HistoricoVO {
	/**
	 * Atributos da tabela Historico
	 */
	public int codigoHist; 
	public String descricaoHist;	
	public String debCreHist;
	
	/**
	 * Gets da classe historico
	 */
	public int getCodigoHist() {
		return codigoHist;
	}
	public String getDescricaoHist() {
		return (descricaoHist==null)?"":descricaoHist;
	}
	public String getDebCreHist() {
		return (debCreHist==null)?"":debCreHist;
	}
	public int getDebCreHist_Int() {
		try{
			return Integer.valueOf(debCreHist);
		}catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * Sets da classe Historico
	 * @param codigoHist
	 */
	public void setCodigoHist(String codigoHist) {
		try {
			this.codigoHist = Integer.parseInt(codigoHist);	
		} catch (Exception e) {
			this.codigoHist = 0;
		}
		
	}

	public void setDescricaoHist(String descricaoHist) {
		this.descricaoHist = descricaoHist;
	}
	public void setDebCreHist(String debCreHist) {
		this.debCreHist = debCreHist;
	}
}
