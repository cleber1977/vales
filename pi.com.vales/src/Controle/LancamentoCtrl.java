package Controle;

import java.util.ArrayList;

import Conexao.Database;
import Persistencia.LancamentoDAO;
import Persistencia.LancamentoVO;

public class LancamentoCtrl {

	LancamentoDAO DAO;
	public LancamentoCtrl(){
		DAO = new LancamentoDAO();	
	}
	/**
	 * Metodo de controle de inclusão de dados 
	 * @param vo
	 * @return
	 */
	public ArrayList<String> incluir(LancamentoVO vo){
		ArrayList<String> retArray = new ArrayList<String>();
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(vo.getDataVenc_String().equals("")){
				certo = false;
				retorno = "Data Vencimento Invalida";
			}
			
			if(vo.getDataEmissao_String().equals("")){
				retorno = "Data Emissão Invalida";
			}
			
			if(!(vo.getValorLanc()!= 0)){
				certo = false;
				retorno = "Valor do Lancamento Invalido";
			}
			if(!(vo.getCodigoEmp()!= 0)){
				certo = false;
				retorno = "Codigo Empresa Invalida";
			}
			if(!(vo.getDebCreHist()!= null && vo.getDebCreHist().trim().length() > 0)){
				certo = false;
				retorno = "Debitar ou Creditar Invalida";
			}
			if(!(vo.getCodigoHist()!= 0)){
				certo = false;
				retorno = "Codigo Historico Invalido";
			}
			if(!(vo.getCodigoFun()!= 0)){
				certo = false;
				retorno = "Codigo do funcionario Invalido";
			}
			if(!(vo.getDescricaoLanc()!= null && vo.getDescricaoLanc().trim().length() > 0)){
				certo = false;
				retorno = "Descrição Invalida";
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
	 */
	public String alterar(LancamentoVO vo){
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
		
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(vo.getDataVenc_String().equals("")){
				certo = false;
				retorno = "Data Vencimento Invalida";
			}
			if(vo.getDataEmissao_String().equals("")){
				certo = false;
				retorno = "Data Emissão Invalida";
			}
			if(!(vo.getValorLanc()!= 0)){
				certo = false;
				retorno = "Valor do Lancamento Invalido";
			}
			if(!(vo.getCodigoEmp()!= 0)){
				certo = false;
				retorno = "Codigo Empresa Invalida";
			}
			if(!(vo.getDebCreHist()!= null && vo.getDebCreHist().trim().length() > 0)){
				certo = false;
				retorno = "Debitar ou Creditar Invalida";
			}
			if(!(vo.getCodigoHist()!= 0)){
				certo = false;
				retorno = "Codigo Historico Invalido";
			}
			if(!(vo.getCodigoFun()!= 0)){
				certo = false;
				retorno = "Codigo Empresa Invalida";
			}
			if(!(vo.getDescricaoLanc()!= null && vo.getDescricaoLanc().trim().length() > 0)){
				certo = false;
				retorno = "Descrição Invalida";
			}
			if(!(vo.getCodigoLanc()!= 0)){
				certo = false;
				retorno = "Informe codigo do Lancamento!";
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
	public String excluir(LancamentoVO vo){
			try{
				String retorno = "";
				int ret = 0;
				boolean certo = true;

				/**
				 * Testando sem Valores digitados estão nulos ou vazio.
				 */
				
				/*
				if(vo.getDataPgto_String().equals("")){
					retorno = "Titulo Quitado";
				}
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
