package Controle;

import Persistencia.BaixaDAO;
import Persistencia.LancamentoVO;

public class BaixaCtrl {
	BaixaDAO DAO;
	
	public BaixaCtrl(){
		DAO = new BaixaDAO();	
	}
	/**
	 * Metodo de controle de Quitação dos Titulos dentro de um periodo 
	 * @param vo
	 */
	public String quitar(LancamentoVO vo){
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(vo.getDataPgto_String().equals("")){
				certo=false;
				retorno = "Data Pgto Invalida";
			}
			if(!(vo.getCodigoFun()!= 0)){
				certo = false;
				retorno = "Informe Funcionario";
			}
	
			if(certo){ 
				ret = DAO.quitar(vo);
				if(ret > 0){
					retorno = "Registro Baixado com Sucesso";					
				}else{
					retorno = "Problemas na Quitação";							
				}
			}

			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

}
