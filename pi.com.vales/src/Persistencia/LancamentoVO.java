package Persistencia;

import java.sql.Date;

import funcoes.funcaoData;
/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 08/10/2009
 */
public class LancamentoVO {
	/**
	 * Atributos da Tabela lancamento
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
	
	//Variavel para armazenar o nome do funcionario.
	public String nomeFun;
	

	public String getNomeFun() {
		return nomeFun;
	}
	public void setNomeFun(String nomeFun) {
		this.nomeFun = nomeFun;
	}
	
	///Campos auxiliares///
	private Date datinicial;
	private Date datfinal;

	/**
	 * Criando os Gets Da Classe Lancamento 
	 * Implementado pro Cleber / Moacir em 08/10/2009
	 */

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
	public String getDataEmissao_String(){
		return funcaoData.converteData(dataEmissao);
	}

	//*Retorna a data no formato para inserir no banco*/
	public String getDataEmissaoFunSQL() {
		return (dataEmissao != null ? funcaoData.converteDataEN(dataEmissao) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getDataEmissaoBusca() {
		return funcaoData.converteData(dataEmissao);
	}

	public String getDataVenc_String(){
		return funcaoData.converteData(dataVenc);
	}

	//*Retorna a data no formato para inserir no banco*/
	public String getDataVencFunSQL() {
		return (dataVenc != null ? funcaoData.converteDataEN(dataVenc) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getDataVencBusca() {
		return funcaoData.converteData(dataVenc);
	}
	public String getDataPgto_String(){
		return funcaoData.converteData(dataPgto);
	}


	//*Retorna a data no formato para inserir no banco*/
	public String getDataPgtoFunSQL() {
		return (dataPgto != null ? funcaoData.converteDataEN(dataPgto) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getDataPgtoBusca() {
		return funcaoData.converteData(dataPgto);
	}


	//*Retorna a data no formato para inserir no banco*/
	public String getDataFinal_String(){
		return funcaoData.converteData(datfinal);
	}

	public String getDatfinalFunSQL() {
		return (datfinal != null ? funcaoData.converteDataEN(datfinal) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}


	//*Retorna a data no formato para inserir no banco*/
	public String getDataInicial_String(){
		return funcaoData.converteData(datinicial);
	}
	
	public String getDatinicialFunSQL() {
		return (datinicial != null ? funcaoData.converteDataEN(datinicial) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}
	
	public String getComplLanc() {
		return (complLanc==null)?"":complLanc;
	}



	/**
	 * Criando os Sets Da Classe Lancamento 
	 * Implementado pro Cleber / Moacir em 08/10/2009
	 */

	public void setCodigoLanc(String codigoLanc) {
		try {
			this.codigoLanc = Integer.parseInt(codigoLanc);	
		} catch (Exception e) {
			this.codigoLanc = 0;
		}
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
	public void setDataPgto(Date dataPgto) {
		this.dataPgto = dataPgto;
	}

	public void setDataPgto(String dataPgto) {
		dataPgto = funcaoData.validaData(dataPgto);
		this.dataPgto = funcaoData.converteStringsql(dataPgto);
	}
	public void setComplLanc(String complLanc) {
		this.complLanc = complLanc;
	}
	public void setDatinicial(String datinicial) {
		datinicial = funcaoData.validaData(datinicial);
		this.datinicial = funcaoData.converteStringsql(datinicial);
	}
	public void setDatinicial(Date datinicial) {
		this.datinicial = datinicial;
	}
	public void setDatfinal(String datfinal) {
		datfinal = funcaoData.validaData(datfinal);
		this.datfinal = funcaoData.converteStringsql(datfinal);
	}
	public void setDatfinal(Date datfinal) {
		this.datfinal = datfinal;
	}
}
