package Controle;

import java.util.ArrayList;

import Conexao.Database;
import Persistencia.UsuarioDAO;
import Persistencia.UsuarioVO;

public class UsuarioCtrl {

	UsuarioDAO DAO;
	public UsuarioCtrl(){
		DAO = new UsuarioDAO();
	}
	/**
	 * Metodo de controle de inclusão de dados
	 * @param vo
	 */	
		public ArrayList<String> incluir(UsuarioVO vo){
			ArrayList<String> retArray = new ArrayList<String>();
			try{
				String retorno = "";
				boolean certo = true;
				int ret = 0;			
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
		
			if(!(vo.getNivelUsuario()!= null && vo.getNivelUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe o nivel de acesso";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getSenhaUsuario()!= null && vo.getSenhaUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Senha";
			}
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getNomeUsuario()!= null && vo.getNomeUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Nome do Usuario";
			}
			
			if(DAO.existeUsuario(vo.getNomeUsuario())){
				certo = false;
				retorno = "Login Ja Cadastrado!";
			}

			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permissão de Incluir!";
			}
			

			String cod = "";

			if(certo){ 
				ret = DAO.incluir(vo);
				if(ret > 0){
					cod = DAO.ultCodigo();
					retorno = "Registro Incluido com Sucesso, Codigo: "+cod;
				}else{
					retorno = "Problemas no Inclusão";							
				}
			}			

			retArray.add(0, retorno);
			retArray.add(1, cod);

			return retArray;
		}catch (Exception e) {
			e.printStackTrace();
			retArray.add(0, e.toString());
			retArray.add(1, "");
			return retArray;		}
	}

	/**
	 * Metodo de controle de Alteração de dados 
	 * @param Paramentro do EmpresaVO
	 * @return 
	 */
	public String alterar(UsuarioVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;
			
			/*
			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permissão de Alteração!";
			}*/

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getNivelUsuario()!= null && vo.getNivelUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe o nivel de acesso";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getSenhaUsuario()!= null && vo.getSenhaUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Senha";
			}

			
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getNomeUsuario()!= null && vo.getNomeUsuario().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Nome do Usuario";
			}
			if(!(vo.getCodigoUsuario()!= 0)){
				certo = false;
				retorno = "Informe codigo!";
			}

			
			if(certo){ 
				ret = DAO.alterar(vo);
				if(ret > 0){
					retorno = "Registro Alterado com Sucesso";					
				}else{
					retorno = "Problemas no Alteração";							
				}
			}

			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	/**
	 * Metodo de controle integridade para não exlcuir caso titulo quitado com outra tabela. 
	 * @param Paramentro do funcionario
	 */
	public String excluir(UsuarioVO vo){
			try{
				String retorno = "";
				int ret = 0;
				boolean certo = true;

				/**
				 * Testando sem Valores digitados estão nulos ou vazio.
				 */
				if(Database.getNivelUsu().equals("1")){
					certo = false;
					retorno = "Sem Permissão de Exclusão!";
				}
				
				if(certo){ 
					ret = DAO.deletar(vo);
					if(ret > 0){
						retorno = "Registro Excluido com Sucesso";					
					}else{
						retorno = "Problemas na Exclusão";							
					}
				}

				return retorno;
			}catch (Exception e) {
				e.printStackTrace();
				return e.toString();
			}
	}
}
