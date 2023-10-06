package Controle;

import java.util.ArrayList;
import java.util.Iterator;

import Conexao.Database;
import Persistencia.CargoDAO;
import Persistencia.CargoVO;
import Persistencia.EstCampo;

public class CargoCtrl {
	CargoDAO DAO;

	public CargoCtrl(){
		super();
		DAO = new CargoDAO();

	}
	/**
	 * Metodo de controle de inclus�o de dados 
	 * @param Paramentro do Cargovo
	 * @return 
	 */
	public ArrayList<String> incluir(CargoVO vo){
		ArrayList<String> retArray = new ArrayList<String>();
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados est�o nulos ou vazio na Salario do Cargo
			 */
			if(!(vo.getSalarioCargo()!= 0)){
				certo = false;
				retorno = "Informe o Sal�rio Teto do Cargo";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio na descri��o do Cargo
			 */
			if(!(vo.getDescricaoCargo()!= null && vo.getDescricaoCargo().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Descri��o do Cargo";
			}

			String cod = "";

			if(certo){ 
				ret = DAO.incluir(vo);
				if(ret > 0){
					cod = DAO.ultCodigo();
					retorno = "Registro Incluido com Sucesso, Codigo: "+cod;
				}else{
					retorno = "Problemas no Inclus�o";							
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
	 * Metodo de controle de Altera��o de dados 
	 * @param Paramentro do Cargovo
	 * @return 
	 */
	public String alterar(CargoVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio na Salario do Cargo
			 */
			if(!(vo.getSalarioCargo()!= 0)){
				certo = false;
				retorno = "Informe o Sal�rio Teto do Cargo";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio na descri��o do Cargo
			 */
			if(!(vo.getDescricaoCargo()!= null && vo.getDescricaoCargo().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Descri��o do Cargo";
			}

			if(!(vo.getCodigoCargo()!= 0)){
				certo = false;
				retorno = "Informe codigo!";
			}
			if(certo){ 
				ret = DAO.alterar(vo);
				if(ret > 0){
					retorno = "Registro Alterado com Sucesso";					
				}else{
					retorno = "Problemas no Altera��o";							
				}
			}

			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	/**
	 * Metodo de controle integridade para n�o exlcuir caso exista relacionamento com outra tabela. 
	 * @param Paramentro do EmpresaVO
	 * @return 
	 */
	public String excluir(CargoVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permiss�o de Exclus�o!";
			}

			if(certo){
				if(!DAO.integridadeFunc(vo.getCodigoCargo())){ 
					ret = DAO.deletar(vo);
					if(ret > 0){
						retorno = "Registro Exclu�do com Sucesso!";
					}else{
						retorno = "Problemas no Exclus�o";							
					}
				}else{	
					retorno = "Cargo possui Funcion�rio!";			
				}
			}

			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();		}
	}
	/**
	 * @author Cleber / Moacir
	 * @since  15/10/2009
	 * @param  campoInterface � o campo onde ser� montado o combo.
	 */
	public ArrayList<EstCampo> comboCargo() {
		ArrayList<EstCampo> lista = new ArrayList<EstCampo>();		
		try {			
			CargoVO voCargo = new CargoVO();
			ArrayList<CargoVO> ListaCargo = DAO.geral(null, null, null);
			if (ListaCargo != null) {
				EstCampo campo = new EstCampo("","");
				lista.add(campo);
				for (Iterator<CargoVO> iterator = ListaCargo.iterator(); iterator.hasNext();) {
					CargoVO vo = iterator.next();
					///// NOME: text do combo, VALOR: por tr�s do combo (value)
					campo = new EstCampo(vo.getDescricaoCargo(),String.valueOf(vo.getCodigoCargo()));
					lista.add(campo);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}


}
