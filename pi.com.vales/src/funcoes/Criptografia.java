package funcoes;


public class Criptografia {

	//////////////////Fun��o para desencripitar///////////////
	public static String descriptografa(String ycTexto, String ycSenha) {

		String resp = "";
		if(ycTexto!=null){
			for (int i = 0; i < ycTexto.length(); i++) {
				resp += (char)(((int)ycTexto.charAt(i))-17-i);
			}
		}
		return resp;
	}


	//////////////////Fun��o para encripitar///////////////

	public static String criptografa(String ycTexto, String ycSenha) {
		String resp = "";
		if(ycTexto!=null){
			for (int i = 0; i < ycTexto.length(); i++) {
				resp += (char)(((int)ycTexto.charAt(i))+17+i);
			}
		}
		return resp;
	}

}