package Persistencia;
/**
 * Classe responsavel pela estrutura do sistema
 * @author Cleber / Moacir
 *	Data 29/10/2009
 */
public class EmpresaVO {
	/**
	 * Atributos da tabela empresa
	 */
	public int codigoEmp; 
	public String razaoSocial;
	public String nomeFantasia;
	public String cnpj;
	public String inscrEst;
	public String endEmp;
	public int numeroEmp; 
	public String bairroEmp;
	public String cidadeEmp;
	public String ufEmp;
	public String cepEmp;
	public String foneEmp;
	
	/**
	 * Criando os Gets
	 */
	public int getCodigoEmp() {
		return codigoEmp;
	}	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	
	//Retirando a mascara do CNPJ/CNPJ
	public String getMascCnpj(){
		return this.cnpj = (funcoes.ValidaCPFCNPJ.removemasc(cnpj));
	}
	
	public String getInscrEst() {
		return inscrEst;
	}
	public String getEndEmp() {
		return endEmp;
	}
	public int getNumeroEmp() {
		return numeroEmp;
	}
	public String getBairroEmp() {
		return bairroEmp;
	}
	public String getCidadeEmp() {
		return cidadeEmp;
	}
	public String getUfEmp() {
		return ufEmp;
	}
	public String getCepEmp() {
		return cepEmp;
	}
	public String getFoneEmp() {
		return foneEmp;
	}
	
	public String getRemoveMascFone(){
		return this.foneEmp = (funcoes.ValidaCPFCNPJ.removeMascFone(foneEmp));
	}
	/**
	 * Criando os Stes da empresa
	 * @param codigoEmp
	 */
	public void setCodigoEmp(String codigoEmp) {
		try {
			this.codigoEmp = Integer.parseInt(codigoEmp);
		} catch (Exception e) {
			this.codigoEmp = 0;
		}

	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setInscrEst(String inscrEst) {
		this.inscrEst = inscrEst;
	}
	public void setEndEmp(String endEmp) {
		this.endEmp = endEmp;
	}
	public void setNumeroEmp(String numeroEmp) {
		try{
			this.numeroEmp = Integer.parseInt(numeroEmp);
		}catch (Exception e) {
			this.numeroEmp = 0;
		}	
	}
	public void setBairroEmp(String bairroEmp) {
		this.bairroEmp = bairroEmp;
	}
	public void setCidadeEmp(String cidadeEmp) {
		this.cidadeEmp = cidadeEmp;
	}
	public void setUfEmp(String ufEmp) {
		this.ufEmp = ufEmp;
	}
	public void setCepEmp(String cepEmp) {
		this.cepEmp = cepEmp;
	}
	public void setFoneEmp(String foneEmp) {
		this.foneEmp = foneEmp;
	}
}
