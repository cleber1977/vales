package funcoes;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class funcaoData {

	//Valor de data Default
	public static String dataDefault(){
		return "01/01/0001";
	}

	/**
	 * Essa Função possibilita que vc set uma string em um campo java.sql.Date
	 * @author Cleber / Moacir
	 * @since  16/12/2008
	 * @param data no formato brasileiro dd/mm/aaaa
	 * @return
	 */
	public static java.sql.Date setString(String data){
		try {
			if (data != null) {
				if (data.length() == 10 && !data.equals("00/00/0000") && data.equals("  /  /    ")) {	
					return funcaoData.converteStringsql(data);
				}else if (data.trim().equals("//") || data.length() == 0) {
					return converteStringsql(funcaoData.dataDefault());
				}else{
					return converteStringsql(funcaoData.dataDefault());
				}
			}else {
				return converteStringsql(funcaoData.dataDefault());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return converteStringsql(funcaoData.dataDefault());
		}
	}
	
	////////////////////Convete Data em String/////////////////
	public static String converteDataEN(java.sql.Date data){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		try {
			if(!format.format(data).equals(converteformatoEN(funcaoData.dataDefault()))){
				return format.format(data);	
			}else{
				return converteformatoEN(funcaoData.dataDefault());
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return converteformatoEN(funcaoData.dataDefault());
		}
	}

	////////////////////Convete String em Data/////////////////
	public static java.sql.Date converteStringsql(String datastr){
		if((datastr == null)||(datastr.trim().length() == 0)||(datastr.trim().equals("//")||(datastr.trim().length() < 10))){
			datastr = dataDefault();
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		try {
			return new java.sql.Date(format.parse(datastr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return converteStringsql(funcaoData.dataDefault());
		}
	}

	/////////////////Convete BR em EN////////////////
	public static String converteformatoEN(String datastr){
		if((datastr == null)||(datastr.trim().length() == 0)||(datastr.trim().equals("//"))){
			datastr = dataDefault();
		}		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		SimpleDateFormat formatEN = new SimpleDateFormat("yyyy/MM/dd");
		try {
			java.util.Date data = new java.util.Date(format.parse(datastr).getTime());
			String dat = formatEN.format(data);
			if(!dat.trim().equals("0000/00/00")){
				return dat;	
			}else{
				return formatEN.format(dataDefault());
			}			
		} catch (ParseException e) {
			return formatEN.format(dataDefault());
		}
	}	

	////////////////////Convete EN em BR/////////////////
	public static String converteformatoBR(String datastr){
		if((datastr == null)||(datastr.trim().length() == 0)||(datastr.trim().equals("//"))){
			datastr = converteformatoEN(dataDefault());
		}		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		SimpleDateFormat formatEN = new SimpleDateFormat("yyyy/MM/dd");
		try {
			java.util.Date data = new java.util.Date(formatEN.parse(datastr).getTime());
			String dat = format.format(data);
			if(!dat.trim().equals("0000/00/00")){
				return format.format(data);
			}else{
				return dataDefault();
			}			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return dataDefault();
		}
	}	

	////////////////////Convete String em Data/////////////////
	public static java.util.Date converteString(String datastr){
		if((datastr == null)||(datastr.trim().length() == 0)||(datastr.trim().equals("//"))){
			datastr = dataDefault();
		}		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		try {
			return new java.util.Date(format.parse(datastr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	////////////////////Convete Data em String/////////////////
	public static String converteData(java.sql.Date data){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		try {
			if(data == null){
				return "";					
			}			

			if(data != null){
				String datn = format.format(data);
				if(!datn.trim().equals(funcaoData.dataDefault())){					
					return format.format(data);						
				}else{
					return "";
				}
			}else{
				return "";
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/////Valida Data////
	public static boolean validaDataMesDia(String data){
		boolean resposta = false;

		int dia = Integer.parseInt(data.substring(0, 2));
		int mes = Integer.parseInt(data.substring(3, 5));
		if(dia > 0 && dia < 32){
			if(mes > 0 && mes < 13){
				resposta = true;
			}
		}

		return resposta;
	}

	public static String validaData(String data){
		if(data.equals("  /  /    ")){
			data = "";
		}
		return data;
	}
}
