package Controle;

import java.sql.SQLException;

import Persistencia.LancAutomaticoDAO;
import Persistencia.LancAutomaticoVO;

public class LancAutomaticoCtrl {
	LancAutomaticoDAO DAO;
	public LancAutomaticoCtrl(){
		DAO = new LancAutomaticoDAO();	
	}
	/**
	 * Metodo de controle de inclus�o de dados 
	 * @param vo
	 * @return
	 */
	public String antesIncluir(LancAutomaticoVO vo){
		try{
			String retorno = null;		
			/**
			 * Testando sem Valores digitados est�o nulos ou vazio.
			 */
			if(vo.getDataEmissao_String().equals("")){
				retorno = "Informe Data de Emiss�o Invalida";
			}
			if(vo.getValorLanc() == 0){
				retorno = "Valor do Lancamento Invalido";
			}
			if(vo.getCodigoEmp() == 0){
				retorno = "Codigo Empresa Invalida";
			}
			if(!(vo.getDebCreHist()!= null && vo.getDebCreHist().trim().length() > 0)){
				retorno = "Debitar ou Creditar Invalida";
			}
			if(vo.getCodigoHist() == 0){
				retorno = "Codigo Historico Invalido";
			}
			if(vo.getCodigoFun() == 0){
				retorno = "Codigo Funcionario Invalido";
			}
			if(!(vo.getDescricaoLanc()!= null && vo.getDescricaoLanc().trim().length() > 0)){
				retorno = "Descri��o Invalida";
			}

			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
	public void incluir(LancAutomaticoVO vo) throws ClassNotFoundException, SQLException {
		DAO.incluir(vo);
	}
}
