package funcoes;

public class ValidaCPFCNPJ {
	/**
	 * Retirar mascara do CPF
	 * @param cpf
	 */
	public static String removemasc(String cgccpf){
		if(cgccpf == null){ 
			cgccpf = "";
		}
		
		cgccpf = cgccpf.trim();
		
		StringBuffer aux = new StringBuffer();
		for (int i = 0; i < cgccpf.length(); i++) {
			if((! cgccpf.substring(i,i+1).equals(" "))&&(! cgccpf.substring(i,i+1).equals("."))&&(! cgccpf.substring(i,i+1).equals("/"))&&(! cgccpf.substring(i,i+1).equals("-"))){
				aux.append(cgccpf.substring(i,i+1));	
			}			
		}
		
		cgccpf = aux.toString();
		
		return cgccpf;
	}
	/**
	 * Retirar mascara do Telefone
	 * @param cpf
	 */
	public static String removeMascFone(String telefone){
		if(telefone == null){ 
			telefone = "";
		}
		
		telefone = telefone.trim();
		
		StringBuffer aux = new StringBuffer();
		for (int i = 0; i < telefone.length(); i++) {
			if((! telefone.substring(i,i+1).equals(" "))&&(! telefone.substring(i,i+1).equals("("))&&(! telefone.substring(i,i+1).equals(")"))&&(! telefone.substring(i,i+1).equals("-"))){
				aux.append(telefone.substring(i,i+1));	
			}			
		}
		
		telefone = aux.toString();
		
		return telefone;
	}
	
	public static String masccpf(String cpf){
		if(cpf.length() == 11){
			cpf = cpf.substring(0,3)+'.'+cpf.substring(3,6)+'.'+cpf.substring(6,9)+'-'+cpf.substring(9,11);
		}
		
		return cpf;
	}

	public static String masccgc(String cgc){
		if(cgc.length() == 14){
			cgc = cgc.substring(0,2)+'.'+cgc.substring(2,5)+'.'+cgc.substring(5,8)+'/'+cgc.substring(8,12)+'-'+cgc.substring(12,14);
		}
		
		return cgc;
	}	

	public static String masccgccpf(String cgccpf){
		cgccpf = removemasc(cgccpf);
		
		if(cgccpf.length() == 11){
			cgccpf = masccpf(cgccpf.trim());
		}else{
			if(cgccpf.length() == 14){		
			  cgccpf = masccgc(cgccpf.trim());
			}
		}
		
		return cgccpf;
	}
	
	public static boolean validaccgccpf(String cgccpf){
		removemasc(cgccpf);
		if(cgccpf.trim().length() == 14){
			cgccpf = masccgc(cgccpf);
			if(!validaCgc(cgccpf)){
				return false;
			}
		}else{
			if(cgccpf.trim().length() == 11){
				cgccpf = masccpf(cgccpf);
				if(!validaCpf(cgccpf)){
					return false;
				}				
			}else{
				return false;
			}
		}
		return true;
	}
	
	
	public static boolean validaCpf(String xCPF){
		try
		{
			//Testa se o CPF é válido ou não
			int d1,d4,xx,nCount,resto,digito1,digito2;
			String Check;
			String Separadores = "/-.";
			d1 = 0; d4 = 0; xx = 1;
			for (nCount = 0; nCount < xCPF.length() -2; nCount++) 
			{
				String s_aux = xCPF.substring(nCount, nCount+1);
				//System.out.println(s_aux);
				if (Separadores.indexOf(s_aux) == -1) {
					d1 = d1 + ( 11 - xx ) * Integer.valueOf (s_aux).intValue();
					d4 = d4 + ( 12 - xx ) * Integer.valueOf (s_aux).intValue();
					xx++;
				};
			};
			resto = (d1 % 11);
			if (resto < 2)
			{
				digito1 = 0;
			}
			else
			{ 
				digito1 = 11 - resto;
			}

			d4 = d4 + 2 * digito1;
			resto = (d4 % 11);
			if (resto < 2)
			{
				digito2 = 0;
			}
			else
			{
				digito2 = 11 - resto;
			}

			Check = String.valueOf(digito1) + String.valueOf(digito2);

			String s_aux2 = xCPF.substring (xCPF.length()-2, xCPF.length());
			
			if (s_aux2.compareTo (Check) != 0)
			{
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;  
		}
	}

	public static boolean validaCgc(String xCNPJ){
		try
		{

			//Testa se o CNPJ é válido ou não
			int d1,d4,xx,nCount,fator,resto,digito1,digito2;
			String Check, s_aux;
			String Separadores = "/-.";
			d1 = 0;
			d4 = 0;
			xx = 0;
			for (nCount = 0; nCount < xCNPJ.length()-2; nCount++) 
			{
				s_aux = xCNPJ.substring (nCount, nCount+1);
				if (Separadores.indexOf(s_aux) == -1) 
				{
					if (xx < 4)
					{
						fator = 5 - xx;
					}
					else
					{
						fator = 13 - xx;
					}

					d1 = d1 + Integer.valueOf (s_aux).intValue() * fator;
					if (xx < 5)
					{
						fator = 6 - xx;
					}
					else
					{
						fator = 14 - xx;
					}
					d4 += Integer.valueOf (s_aux).intValue() * fator;
					xx++;
				};
			}
			resto = (d1 % 11);
			if (resto < 2)
			{
				digito1 = 0;
			}
			else
			{
				digito1 = 11 - resto;
			}

			d4 = d4 + 2 * digito1;
			resto = (d4 % 11);
			if (resto < 2)
			{
				digito2 = 0;
			}
			else
			{
				digito2 = 11 - resto;
			}

			Check = String.valueOf(digito1) + String.valueOf(digito2);
		
			if (Check.compareTo(xCNPJ.substring(xCNPJ.length()-2, xCNPJ.length() )) !=0)
			{
				return false;
			}

			return true;
		}
		catch (Exception e)
		{
			return false;  
		}
	}
	
	
}
