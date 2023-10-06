package Controle;

import Persistencia.EstCampo;
import Persistencia.FuncionarioDAO;
import Persistencia.FuncionarioVO;
import Persistencia.LancamentoDAO;
import funcoes.ValidaCPFCNPJ;
import funcoes.funcaoData;


public class RelatorioCtrl {

	public String relLancamento(String codigoEmp, String codigoFun, String codigoHist, String datainiEmissao, String datafimEmissao, String datainiPgto, String datafimPgto, String datainiVenc, String datafimVenc){
		String retorno = null;
		try{
			/*if(funcaoData.validaDataMesDia(datini) && funcaoData.validaDataMesDia(datfin)){
				if(funcaoData.converteString(datini).after(funcaoData.converteString(datfin))){
					retorno = "Período Inválido!";
				}else{*/			
			LancamentoDAO DAO = new LancamentoDAO();
			DAO.relLancamento(codigoEmp, codigoFun, codigoHist, datainiEmissao, datafimEmissao, datainiPgto, datafimPgto, datainiVenc, datafimVenc);
			/*}
			}else{
				retorno = "Período Inválido!";
			}*/
		}catch (Exception e) {
			e.printStackTrace();
			retorno = e.toString();
		}
		return retorno;
	}

	public String recibo_Extrato(EstCampo campo, String dataini, String datafin){
		String msm="";
		LancamentoDAO DAO = new LancamentoDAO();

		int codigoFun = 0;
		if(campo != null){
			try {
				codigoFun = Integer.parseInt(campo.valor); 
			} catch (Exception e) {
				codigoFun = 0;
			}
		}

		if (codigoFun == 0) {
			msm = "Informe o funcionário!";
			return msm;
		}

		if(funcaoData.validaDataMesDia(dataini) && funcaoData.validaDataMesDia(datafin)){
			if(funcaoData.converteString(dataini).after(funcaoData.converteString(datafin))){
				msm = "Período Inválido!";
			}else{			
				msm = DAO.relRecibo(codigoFun, dataini, datafin);
				return msm;
			}
		}else{
			msm = "Período Inválido!";
		}
		return msm;
	}

	public String fichaFuncionario(FuncionarioVO vo){
		String msm="";

		if (vo != null && vo.getCodigoFun() == 0) {			
			msm = "Informe o funcionário!";
			return msm;
		}
		
		FuncionarioDAO DAO = new FuncionarioDAO();
		msm = DAO.fichaFuncionario(vo.getCodigoFun());
		
		return msm;
	}

}
