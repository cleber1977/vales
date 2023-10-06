package Persistencia;

import funcoes.Criptografia;

/**
 * Classe responsavel pela estrutura do sistema
 * @author programar
 *	Data 30/10/2009
 */
public class UsuarioVO {
	/**
	 * Atributos da tabela usuario
	 */
	public int codigoUsuario;
	public String nomeUsuario;
	public String senhaUsuario;
	public String nivelUsuario;
	
	/**
	 * Gets do tabela Usuario
	 */
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public String getNomeUsuario() {
		return (nomeUsuario==null)?"":nomeUsuario;
	}
	public String getSenhaUsuario() {
		return (senhaUsuario==null)?"":senhaUsuario;
	}
	public String getCriptSenhaUsuario() {
		return Criptografia.criptografa(senhaUsuario, " !");		
	}
	public String getNivelUsuario() {
		return (nivelUsuario==null)?"":nivelUsuario;
	}
	public int getNivelUsuario_Int() {
		try{
			return Integer.valueOf(nivelUsuario);
		}catch (Exception e) {
			return 0;
		}
	}
	/**
	 * Sets da tabela usuario
	 * @param codigoUsuario
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		try {
			this.codigoUsuario = Integer.parseInt(codigoUsuario);	
		} catch (Exception e) {
			this.codigoUsuario = 0;
		}

	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public void setCriptSenhaUsuario(String senhaUsuarioCript) {
		this.senhaUsuario = Criptografia.descriptografa(senhaUsuarioCript, " !");		
	}
	public void setNivelUsuario(String nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}
}
