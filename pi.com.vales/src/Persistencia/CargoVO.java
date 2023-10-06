package Persistencia;

/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 29/10/2009
 */
public class CargoVO {
	/**
	 * Atributos da tabela Cargo
	 */
	public int codigoCargo;
	public String descricaoCargo;
	public  String codContabil;
	public  double salarioCargo;
	
	/**
	 *  Gets da Classe Cargo
	 */
	public int getCodigoCargo() {
		return codigoCargo;
	}
	public String getDescricaoCargo() {
		return (descricaoCargo==null)?"":descricaoCargo;
	}
	public String getCodContabil() {
		return (codContabil==null)?"":codContabil;
	}
	public double getSalarioCargo() {
		return salarioCargo;
	}
	
	/** 
	 * Sets da Classe Cargo
	 * @param codContabil
	 */
	public void setCodContabil(String codContabil) {
		this.codContabil = codContabil;
	}	
	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		try {
			this.codigoCargo = Integer.parseInt(codigoCargo);	
		} catch (Exception e) {
			this.codigoCargo = 0;
		}
		
	}
	public void setSalarioCargo(String salarioCargo) {
		try{
			this.salarioCargo = Double.valueOf(salarioCargo);
		}catch (Exception e) {
			this.salarioCargo = 0;
		}
	}
}
