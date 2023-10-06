package Controle;

import java.util.ArrayList;
import java.util.Iterator;

import Conexao.Database;
import Persistencia.EmpresaDAO;
import Persistencia.EmpresaVO;
import Persistencia.EstCampo;
import funcoes.ValidaCPFCNPJ;

public class EmpresaCtrl {
	EmpresaDAO DAO;

	public EmpresaCtrl(){
		DAO = new EmpresaDAO();
	}

	/**
	 * Metodo de controle de inclus�o de dados 
	 * @param Paramentro do EmpresaDao
	 * @return 
	 */
	public ArrayList<String> incluir(EmpresaVO vo){
		ArrayList<String> retArray = new ArrayList<String>();
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getInscrEst()!= null && vo.getInscrEst().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Inscri��o Estadual";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getCnpj()!= null && vo.getCnpj().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome CNPJ da Empresa";
			}


			//Testar se o CNPJ / CNPJ  digitado � Valido.
			if(!ValidaCPFCNPJ.validaCgc(vo.getCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Inv�lido!";
			}

			if(DAO.existeCNPJ(vo.getMascCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Ja Cadastrado!";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getNomeFantasia()!= null && vo.getNomeFantasia().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome Fantasia";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getRazaoSocial()!= null && vo.getRazaoSocial().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Raz�o Social";
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
	 * @param Paramentro do EmpresaVO
	 * @return 
	 */
	public String alterar(EmpresaVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getRazaoSocial()!= null && vo.getRazaoSocial().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Raz�o Social";
			}


			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getNomeFantasia()!= null && vo.getNomeFantasia().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome Fantasia";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getCnpj()!= null && vo.getCnpj().trim().length() > 0)){
				certo = false;
				retorno = "Informe o CNPJ da Empresa";
			}	

			//Testar se o CNPJ / CNPJ  digitado � Valido.
			if(!ValidaCPFCNPJ.validaCgc(vo.getCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Inv�lido!";
			}

			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(!(vo.getInscrEst()!= null && vo.getInscrEst().trim().length() > 0)){
				certo = false;
				retorno = "Informe Inscri��o Estadual";
			}
			if(!(vo.getCodigoEmp()!= 0)){
				certo = false;
				retorno = "Informe codigo da Empresa!";
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
	public String excluir(EmpresaVO vo){
		try{
			String retorno = "";
			int ret = 0;	
			boolean certo = true;
			
			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permiss�o de Exclus�o!";
			}
			
			if(certo){
				if(!DAO.integridadeFunc(vo.getCodigoEmp())){ 
					if(!DAO.integridadeLanc(vo.getCodigoEmp())){ 
						ret = DAO.deletar(vo);
						if(ret > 0){
							retorno = "Registro Exclu�do com Sucesso!";
						}else{
							retorno = "Problemas no Exclus�o";							
						}
					}else{
						retorno = "Empresa possui Lancamento!";							
					}
				}else{	
					retorno = "Empresa possui Funcion�rio!";			
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
	public ArrayList<EstCampo> comboEmpresa() {
		ArrayList<EstCampo> lista = new ArrayList<EstCampo>();		
		try {			
			EmpresaVO voEmpresa = new EmpresaVO();
			ArrayList<EmpresaVO> ListaEmpresa = DAO.geral(null, null, null);
			if (ListaEmpresa != null) {
				EstCampo campo = new EstCampo("","");
				lista.add(campo);
				for (Iterator<EmpresaVO> iterator =ListaEmpresa.iterator(); iterator.hasNext();) {
					EmpresaVO vo = iterator.next();
					///// NOME: text do combo, VALOR: por tr�s do combo (value)
					campo = new EstCampo(vo.getRazaoSocial(),String.valueOf(vo.getCodigoEmp()));
					lista.add(campo);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
