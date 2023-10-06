package Persistencia;

import java.sql.Date;

import funcoes.funcaoData;
/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 01/11/2009.
 */
public class LancAutomaticoVO {
	/**
	 * Atributos da tabela Lancamento
	 */
	public int codigoLanc;
	public int codigoFun;
	public int codigoHist;
	public double valorLanc;
	public String descricaoLanc;
	public String debCreHist;
	public int codigoEmp;
	public Date dataEmissao;
	public Date dataVenc;
	public Date dataPgto;
	public String complLanc;
	public int parcelas;
	
	/**
	 * Criando os Gets Da Classe Lancamento 
	 * Implementado pro Cleber / Moacir em 01/11/2009.
	 */
	 
	public int getParcelas() {
		return parcelas;
	}
	public int getCodigoLanc() {
		return codigoLanc;
	}
	public int getCodigoFun() {
		return codigoFun;
	}
	public int getCodigoHist() {
		return codigoHist;
	}
	public double getValorLanc() {
		return valorLanc;
	}
	public String getDescricaoLanc() {
		return (descricaoLanc==null)?"":descricaoLanc;
	}
	public String getDebCreHist() {
		return (debCreHist==null)?"":debCreHist;
	}
	public int getCodigoEmp() {
		return codigoEmp;
	}
	
	
	
	public Date getDataPgto() {
		return dataPgto;
	}
	//*Retorna a data no formato para inserir no banco*/
	public String getDataPgtoFunSQL() {
		return (dataPgto != null ? funcaoData.converteDataEN(dataPgto) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}
	
	
	
	
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public String getDataEmissao_String(){
		return funcaoData.converteData(dataEmissao);
	}
	//*Retorna a data no formato para inserir no banco*/
	public String getDataEmissaoFunSQL() {
		return (dataEmissao != null ? funcaoData.converteDataEN(dataEmissao) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}
	
		
	
	
	public Date getDataVenc() {
		return dataVenc;
	}
	public String getDataVenc_String(){
		return funcaoData.converteData(dataVenc);
	}
	//*Retorna a data no formato para inserir no banco*/
	public String getDataVencFunSQL() {
		return (dataVenc != null ? funcaoData.converteDataEN(dataVenc) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}
	
		
	
	
	public String getComplLanc() {
		return (complLanc==null)?"":complLanc;
	}
	
	/**
	 * Criando os Sets Da Classe Lancamento 
	 * Implementado pro Cleber / Moacir em 01/11/2009.
	 */
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}	
	public void setCodigoLanc(int codigoLanc) {
		this.codigoLanc = codigoLanc;
	}
	public void setCodigoFun(int codigoFun) {
		this.codigoFun = codigoFun;
	}
	public void setCodigoFun(String codigoFun) {
		try {
			this.codigoFun = Integer.parseInt(codigoFun);
		} catch (Exception e) {
			this.codigoFun = 0;
		}
	}
	public void setCodigoHist(int codigoHist) {
		this.codigoHist = codigoHist;
	}
	public void setCodigoHist(String codigoHist) {
		try {
			this.codigoHist = Integer.parseInt(codigoHist);
		} catch (Exception e) {
			this.codigoHist = 0;
		}
	}
	public void setValorLanc(String valorLanc) {
		try{
			this.valorLanc = Double.valueOf(valorLanc);
		}catch (Exception e) {
			this.valorLanc = 0;
		}
	}
	public void setDescricaoLanc(String descricaoLanc) {
		this.descricaoLanc = descricaoLanc;
	}
	public void setDebCreHist(String debCreHist) {
		this.debCreHist = debCreHist;
	}
	public void setCodigoEmp(int codigoEmp) {
		this.codigoEmp = codigoEmp;
	}
	// cache (Conferir)
	public void setCodigoEmp(String codigoEmp) {
		try {
			this.codigoEmp = Integer.parseInt(codigoEmp);
		} catch (Exception e) {
			this.codigoEmp = 0;
		}
	}
	

	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}
	public void setDataPgto(String dataPgto) {
		this.dataPgto = funcaoData.setString(dataPgto);
	}
	
	
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		dataEmissao = funcaoData.validaData(dataEmissao);
		this.dataEmissao = funcaoData.converteStringsql(dataEmissao);
	}
	
	
	
	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}
	public void setDataVenc(String dataVenc) {
		dataVenc = funcaoData.validaData(dataVenc);
		this.dataVenc = funcaoData.converteStringsql(dataVenc);
	}
	
	
	
	
	public void setComplLanc(String complLanc) {
		this.complLanc = complLanc;
	}

}
