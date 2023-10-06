package Controle;

import java.util.ArrayList;
import java.util.Iterator;

import Conexao.Database;
import Persistencia.EstCampo;
import Persistencia.HistoricoDAO;
import Persistencia.HistoricoVO;

public class HistoricoCtrl {

	HistoricoDAO DAO;

	public HistoricoCtrl() {
		DAO = new HistoricoDAO();
	}

	/**
	 * Metodo de controle de inclusão de dados 
	 * @param vo
	 * @return
	 */
	public ArrayList<String> incluir(HistoricoVO vo){
		ArrayList<String> retArray = new ArrayList<String>();
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getDebCreHist()!= null && vo.getDebCreHist().trim().length() > 0)){
				certo = false;
				retorno = "Informe Debito / Credito";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getDescricaoHist()!= null && vo.getDescricaoHist().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Descrição";
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
	public String alterar(HistoricoVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getDebCreHist()!= null && vo.getDebCreHist().trim().length() > 0)){
				certo = false;
				retorno = "Informe Se debito / credito";
			}

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getDescricaoHist()!= null && vo.getDescricaoHist().trim().length() > 0)){
				certo = false;
				retorno = "Informe a Descrição";
			}
			if(!(vo.getCodigoHist()!= 0)){
				certo = false;
				retorno = "Informe codigo do Historico!";
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
	 * @param Paramentro do Historico
	 */
	public String excluir(HistoricoVO vo){
		try{
			String retorno = "";
			int ret = 0;	
			boolean certo = true;

			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permissão de Exclusão!";
			}
			if(certo){
				if(!DAO.integridadeLanc(vo.getCodigoHist())){ 
					ret = DAO.deletar(vo);
					if(ret > 0){
						retorno = "Registro Excluído com Sucesso!";
					}else{
						retorno = "Problemas no Exclusão";							
					}
				}else{	
					retorno = "Historico possui Lancamentos!";			
				}
		}
	

		return retorno;
	}catch (Exception e) {
		e.printStackTrace();
		return e.toString();	
	}
}
/**
 * @author Cleber / Moacir
 * @since  15/10/2009
 * @param  campoInterface é o campo onde será montado o combo.
 */
public ArrayList<EstCampo> comboHistorico() {
	ArrayList<EstCampo> lista = new ArrayList<EstCampo>();		
	try {			
		HistoricoVO voHistorico = new HistoricoVO();
		ArrayList<HistoricoVO> ListaHistorico = DAO.geral(null, null, null);
		if (ListaHistorico != null) {
			EstCampo campo = new EstCampo("","");
			lista.add(campo);
			for (Iterator<HistoricoVO> iterator = ListaHistorico.iterator(); iterator.hasNext();) {
				HistoricoVO vo = iterator.next();
				///// NOME: text do combo, VALOR: por trás do combo (value)
				campo = new EstCampo(vo.getDescricaoHist(),String.valueOf(vo.getCodigoHist()));
				lista.add(campo);
			}
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return lista;
}

}
