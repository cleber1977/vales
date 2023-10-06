package Persistencia;

import java.sql.Date;

import funcoes.funcaoData;
/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 08/10/2009
 */
public class FuncionarioVO {
	/**
	 * Atributos da tabela Funcionario
	 */
	public int codigoFun; 
	public String nomeFun;
	public String rgFun;
	public String cpfFun;
	public String ctpsFun;
	public Date dataNascFun;
	public String sexoFun;
	public String endFun;
	public String bairroFun;
	public String cidadeFun;
	public String ufFun;
	public int codigoEmp; 
	public int codigoCargo; 
	public Date admissaoFun;	
	public Date demissaoFun;
	public double salarioCargo;
	public String cepFun;
	public String foneFun;
	public String celularFun;
	public String paiFun;
	public String maeFun;
	public String enderecoPais;
	public String bairroPais;
	public String cidadePais;
	public String ufPais;
	public String fonePais;
	public String mesfechado;

	// Criado somente para armazenar a descrição do cargo.
	public String descricaoCargo;

	public String getDescricaoCargo() {
		return descricaoCargo;
	}
	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}
	
	/**
	 * Criando os Gets Da Classe Funcionario 
	 * Implmentado por Cleber / Moacir em 08/10/2009
	 */
	public int getCodigoFun() {
		return codigoFun;
	}
	public String getNomeFun() {
		return (nomeFun==null)?"":nomeFun;
	}
	public String getRgFun() {
		return (rgFun==null)?"":rgFun;
	}
	public String getCpfFun() {
		return (cpfFun==null)?"":cpfFun;
	}

	// Retirando a mascara do CPF p/ gravar no banco.
	public String getMascCPFFun(){
		return this.cpfFun = (funcoes.ValidaCPFCNPJ.removemasc(cpfFun));
	}

	public String getCtpsFun() {
		return (ctpsFun==null)?"":ctpsFun;
	}


	public String getDataNascFun_String(){
		return funcaoData.converteData(dataNascFun);
	}

	//*Retorna a data no formato para inserir no banco*/
	public String getDataNascFunSQL() {
		return (dataNascFun != null ? funcaoData.converteDataEN(dataNascFun) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getDataNascFunBusca() {
		return funcaoData.converteData(dataNascFun);
	}

	public String getSexoFun() {
		return sexoFun;
	}
	public String getEndFun() {
		return (endFun==null)?"":endFun;
	}
	public String getBairroFun() {
		return (bairroFun==null)?"":bairroFun;
	}
	public String getCidadeFun() {
		return (cidadeFun==null)?"":cidadeFun;
	}
	public String getUfFun() {
		return (ufFun==null)?"":ufFun;
	}
	public int getCodigoEmp() {
		return codigoEmp;
	}
	public int getCodigoCargo() {
		return codigoCargo;
	}


	public String getAdmissaoFun_String(){
		return funcaoData.converteData(admissaoFun);
	}

	//*Retorna a data no formato para inserir no banco*/
	public String getAdmissaoFunFunSQL() {
		return (admissaoFun != null ? funcaoData.converteDataEN(admissaoFun) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getAdmissaoFunBusca() {
		return funcaoData.converteData(admissaoFun);
	}



	//Data de Demissão
	public String getgetDemissaoFun_String(){
		return funcaoData.converteData(demissaoFun);
	}

	//*Retorna a data no formato para inserir no banco*/
	public String getDemissaoFunFunSQL() {
		return (demissaoFun != null ? funcaoData.converteDataEN(demissaoFun) : funcaoData.converteformatoEN(funcaoData.dataDefault()));
	}

	//*Retorna a data no formato para buscar do banco*/
	public String getDemissaoFunBusca() {
		return funcaoData.converteData(demissaoFun);
	}




	public double getSalarioCargo() {
		return salarioCargo;
	}
	public String getCepFun() {
		return (cepFun==null)?"":cepFun;
	}
	public String getFoneFun() {
		return (foneFun==null)?"":foneFun;
	}
	//Removendo a mascara do telefone
	public String getRemoveMascFone(){
		return this.foneFun = (funcoes.ValidaCPFCNPJ.removeMascFone(foneFun));
	}

	public String getCelularFun() {
		return (celularFun==null)?"":celularFun;
	}

	//Removendo a mascara do celular	
	public String getRemoveMascCelular(){
		return this.celularFun = (funcoes.ValidaCPFCNPJ.removeMascFone(celularFun));
	}

	public String getPaiFun() {
		return (paiFun==null)?"":paiFun;
	}
	public String getMaeFun() {
		return (maeFun==null)?"":maeFun;
	}
	public String getEnderecoPais() {
		return (enderecoPais==null)?"":enderecoPais;
	}
	public String getBairroPais() {
		return (bairroPais==null)?"":bairroPais;
	}
	public String getCidadePais() {
		return (cidadePais==null)?"":cidadePais;
	}
	public String getUfPais() {
		return (ufPais==null)?"":ufPais;
	}
	public String getFonePais() {
		return (fonePais==null)?"":fonePais;
	}

	//Removendo a mascara do telefone
	public String getRemoveMascFonePais(){
		return this.fonePais = (funcoes.ValidaCPFCNPJ.removeMascFone(fonePais));
	}

	public String getMesfechado() {
		return (mesfechado==null)?"":mesfechado;
	}

	/**
	 * Criando os Sets da classe FuncionarioVo
	 * Implmentado por Cleber / Moacir em 08/10/2009
	 */

	public void setCodigoFun(String codigoFun) {
		try {
			this.codigoFun = Integer.parseInt(codigoFun);
		} catch (Exception e) {
			this.codigoFun = 0;
		}

	}
	public void setNomeFun(String nomeFun) {
		this.nomeFun = nomeFun;
	}
	public void setRgFun(String rgFun) {
		this.rgFun = rgFun;
	}
	public void setCpfFun(String cpfFun) {
		this.cpfFun = cpfFun;
	}

	/*
	public void setMascCpfFun(String cpfFun) {
		this.cpfFun = ValidaCPFCNPJ.removemasc(cpfFun);
	}
	 */

	public void setCtpsFun(String ctpsFun) {
		this.ctpsFun = ctpsFun;
	}
	public void setDataNascFun(Date dataNascFun) {
		this.dataNascFun = dataNascFun;
	}
	public void setDataNascFun(String dataNascFun) {
		dataNascFun = funcaoData.validaData(dataNascFun);
		this.dataNascFun = funcaoData.converteStringsql(dataNascFun);
	}

	public void setSexoFun(String sexoFun) {
		this.sexoFun = sexoFun;
	}
	public void setEndFun(String endFun) {
		this.endFun = endFun;
	}
	public void setBairroFun(String bairroFun) {
		this.bairroFun = bairroFun;
	}
	public void setCidadeFun(String cidadeFun) {
		this.cidadeFun = cidadeFun;
	}
	public void setUfFun(String ufFun) {
		this.ufFun = ufFun;
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
	public void setCodigoCargo(int codigoCargo) {
		this.codigoCargo = codigoCargo;
	}
	// cache (Conferir)
	public void setCodigoCargo(String codigoCargo) {
		try {
			this.codigoCargo = Integer.parseInt(codigoCargo);
		} catch (Exception e) {
			this.codigoCargo = 0;
		}
	}
	public void setAdmissaoFun(String admissaoFun) {
		admissaoFun = funcaoData.validaData(admissaoFun);
		this.admissaoFun = funcaoData.converteStringsql(admissaoFun);
	}

	public void setAdmissaoFun(Date admissaoFun) {
		this.admissaoFun = admissaoFun;
	}

	public void setDemissaoFun(String demissaoFun) {
		demissaoFun = funcaoData.validaData(demissaoFun);
		this.demissaoFun = funcaoData.converteStringsql(demissaoFun);
	}

	public void setDemissaoFun(Date demissaoFun) {
		this.demissaoFun = demissaoFun;
	}
	public void setSalarioCargo(String salarioCargo) {
		try {
			this.salarioCargo = Double.valueOf(salarioCargo);
		} catch (Exception e) {
			this.salarioCargo = 0;
		}
	}
	public void setCepFun(String cepFun) {
		this.cepFun = cepFun;
	}
	public void setFoneFun(String foneFun) {
		this.foneFun = foneFun;
	}
	public void setCelularFun(String celularFun) {
		this.celularFun = celularFun;
	}
	public void setPaiFun(String paiFun) {
		this.paiFun = paiFun;
	}
	public void setMaeFun(String maeFun) {
		this.maeFun = maeFun;
	}
	public void setEnderecoPais(String enderecoPais) {
		this.enderecoPais = enderecoPais;
	}
	public void setBairroPais(String bairroPais) {
		this.bairroPais = bairroPais;
	}
	public void setCidadePais(String cidadePais) {
		this.cidadePais = cidadePais;
	}
	public void setUfPais(String ufPais) {
		this.ufPais = ufPais;
	}
	public void setFonePais(String fonePais) {
		this.fonePais = fonePais;
	}
	public void setMesfechado(String mesfechado) {
		this.mesfechado = mesfechado;
	}
}
