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
	 * Metodo de controle de inclusão de dados 
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
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getInscrEst()!= null && vo.getInscrEst().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Inscrição Estadual";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getCnpj()!= null && vo.getCnpj().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome CNPJ da Empresa";
			}


			//Testar se o CNPJ / CNPJ  digitado é Valido.
			if(!ValidaCPFCNPJ.validaCgc(vo.getCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Inválido!";
			}

			if(DAO.existeCNPJ(vo.getMascCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Ja Cadastrado!";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getNomeFantasia()!= null && vo.getNomeFantasia().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome Fantasia";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getRazaoSocial()!= null && vo.getRazaoSocial().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Razão Social";
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
	public String alterar(EmpresaVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getRazaoSocial()!= null && vo.getRazaoSocial().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Razão Social";
			}


			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getNomeFantasia()!= null && vo.getNomeFantasia().trim().length() > 0)){
				certo = false;
				retorno = "Informe o Nome Fantasia";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getCnpj()!= null && vo.getCnpj().trim().length() > 0)){
				certo = false;
				retorno = "Informe o CNPJ da Empresa";
			}	

			//Testar se o CNPJ / CNPJ  digitado é Valido.
			if(!ValidaCPFCNPJ.validaCgc(vo.getCnpj())){
				certo = false;
				retorno = "CNPJ / CNPJ Inválido!";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getInscrEst()!= null && vo.getInscrEst().trim().length() > 0)){
				certo = false;
				retorno = "Informe Inscrição Estadual";
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
	 * Metodo de controle integridade para não exlcuir caso exista relacionamento com outra tabela. 
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
				retorno = "Sem Permissão de Exclusão!";
			}
			
			if(certo){
				if(!DAO.integridadeFunc(vo.getCodigoEmp())){ 
					if(!DAO.integridadeLanc(vo.getCodigoEmp())){ 
						ret = DAO.deletar(vo);
						if(ret > 0){
							retorno = "Registro Excluído com Sucesso!";
						}else{
							retorno = "Problemas no Exclusão";							
						}
					}else{
						retorno = "Empresa possui Lancamento!";							
					}
				}else{	
					retorno = "Empresa possui Funcionário!";			
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
	 * @param  campoInterface é o campo onde será montado o combo.
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
					///// NOME: text do combo, VALOR: por trás do combo (value)
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
