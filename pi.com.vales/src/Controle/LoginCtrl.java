package Controle;

import Conexao.Database;
import Persistencia.UsuarioDAO;
import Persistencia.UsuarioVO;

public class LoginCtrl {
	
	UsuarioDAO DAO;
	public LoginCtrl(){
		DAO = new UsuarioDAO();
	}
	/**
	 * Metodo de controle de Alteração de dados 
	 * @param Paramentro do EmpresaVO
	 * @return 
	 */
	public String logar(UsuarioVO vo){
		try{
			String retorno = null;
			
			if(!DAO.existeUsuario(vo.getNomeUsuario())){
				retorno = "Usuario Não Encontrado!";
			}else{
				if(!DAO.existeSenha(vo.getCriptSenhaUsuario(), vo.getNomeUsuario())){
				retorno = "Senha Invalida!";
				}else{
				Database.setNivelUsu(DAO.nivelUsuario(vo.getNomeUsuario()));
				}
			}			
			
			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
