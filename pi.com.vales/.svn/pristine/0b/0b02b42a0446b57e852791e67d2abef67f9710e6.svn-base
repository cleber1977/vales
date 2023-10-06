package Controle;

import java.util.ArrayList;
import java.util.Iterator;

import Conexao.Database;
import Persistencia.EstCampo;
import Persistencia.FuncionarioDAO;
import Persistencia.FuncionarioVO;
import funcoes.ValidaCPFCNPJ;

public class FuncionarioCtrl {
	FuncionarioDAO DAO;

	public FuncionarioCtrl(){
		DAO = new FuncionarioDAO();
	}
	/**
	 * Metodo de controle de inclusão de dados 
	 * @param vo
	 * @return
	 */
	public ArrayList<String> incluir(FuncionarioVO vo){
		ArrayList<String> retArray = new ArrayList<String>();
		try{
			String retorno = "";
			boolean certo = true;
			int ret = 0;		
			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getMesfechado()!= null)){
				certo = false;
				retorno = "Informe o Ultimo Mes Fechado!";
			}

			if(vo.getAdmissaoFun_String().equals("")){
				certo = false;
				retorno = "Data de Admissão Invalida";
			}
			
			if(!(vo.getCodigoCargo() != 0)){
				certo = false;
				retorno = "Informe Cargo!";
			}
			if(!(vo.getCodigoEmp()!= 0)){
				certo = false;
				retorno = "Informe Empresa!";
			}

			if(!(vo.getCpfFun()!= null && vo.getCpfFun().trim().length() > 0)){
				certo = false;
				retorno = "Informe CPF!";
			}


			//Testar se o CPF digitado e Valido.
			if(!ValidaCPFCNPJ.validaCpf(vo.getCpfFun())){
				certo = false;
				retorno = "CPF Inválido!";
			}

			if(DAO.existeCPF(vo.getMascCPFFun())){
				certo = false;
				retorno = "CPF Ja Cadastrado!";
			}
			if(!(vo.getNomeFun()!= null && vo.getNomeFun().trim().length() > 0)){
				certo = false;
				retorno = "Informe Nome!";
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
			return retArray;
		}
	}
	/**
	 * Metodo de controle de Alteração de dados 
	 * @param Paramentro do EmpresaVO
	 * @return 
	 */
	public String alterar(FuncionarioVO vo){
		try{
			String retorno = "";
			int ret = 0;
			boolean certo = true;

			/**
			 * Testando sem Valores digitados estão nulos ou vazio.
			 */
			if(!(vo.getMesfechado()!= null)){
				certo = false;
				retorno = "Informe o Ultimo Mes Fechado!";
			}

			if(vo.getAdmissaoFun_String().equals("")){
				certo = false;
				retorno = "Data de Admissão Invalida";
			}

			if(!(vo.getCodigoCargo()!= 0)){
				certo = false;
				retorno = "Informe Cargo!";
			}
			if(!(vo.getCodigoEmp()!= 0)){
				certo = false;
				retorno = "Informe Empresa!";
			}
			if(!(vo.getCpfFun()!= null && vo.getCpfFun().trim().length() > 0)){
				certo = false;
				retorno = "Informe CPF!";
			}

			//Testar se o CPF digitado e Valido.
			if(!ValidaCPFCNPJ.validaCpf(vo.getCpfFun())){
				certo = false;
				retorno = "CPF Inválido!";
			}		

			if(!(vo.getNomeFun()!= null && vo.getNomeFun().trim().length() > 0)){
				certo = false;
				retorno = "Informe Nome!";
			}
			if(!(vo.getCodigoFun()!= 0)){
				certo = false;
				retorno = "Informe codigo do Funcionario!";
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
	 * @param Paramentro do funcionario
	 */
	public String excluir(FuncionarioVO vo){
		try{
			String retorno = "";
			int ret = 0;	
			boolean certo = true;

			if(Database.getNivelUsu().equals("1")){
				certo = false;
				retorno = "Sem Permissão de Exclusão!";
			}


			if(certo){
				if(!DAO.integridadeLanc(vo.getCodigoFun())){ 
					ret = DAO.deletar(vo);
					if(ret > 0){
						retorno = "Registro Excluído com Sucesso!";
					}else{
						retorno = "Problemas no Exclusão";							
					}
				}else{	
					retorno = "Funcionario possui Lancamentos!";			
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
	public ArrayList<EstCampo> combofuncionario() {
		ArrayList<EstCampo> lista = new ArrayList<EstCampo>();		
		try {			
			FuncionarioVO voFuncionario = new FuncionarioVO();
			ArrayList<FuncionarioVO> ListaFunc = DAO.geral(null, null, null);
			if (ListaFunc != null) {
				EstCampo campo = new EstCampo("","");
				lista.add(campo);
				for (Iterator<FuncionarioVO> iterator = ListaFunc.iterator(); iterator.hasNext();) {
					FuncionarioVO vo = iterator.next();
					///// NOME: text do combo, VALOR: por trás do combo (value)
					campo = new EstCampo(vo.getNomeFun(),String.valueOf(vo.getCodigoFun()));
					lista.add(campo);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
