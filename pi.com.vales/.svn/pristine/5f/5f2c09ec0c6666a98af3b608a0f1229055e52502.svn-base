package Persistencia;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Conexao.Database;
import funcoes.funcaoData;
/**
 * Classe Composta pelos metodos do persistencia no banco
 * @author Cleber / Moacir
 *	Data 19/10/2009
 */
public class LancamentoDAO {
	/**
	 * Metodo de Listagem de registro
	 * @param codigoLanc
	 * @param descricaoLanc
	 * @param dataVenc
	 * @param Ordem
	 */

	public ArrayList<LancamentoVO> geral(String  codigoLanc, String descricaoLanc, String Ordem) {
		ArrayList<LancamentoVO> lista = null;
		LancamentoVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT lanc.*, fun.nomeFun FROM lancamento lanc " +
					"left outer join funcionario fun on (lanc.codigoFun = fun.codigoFun)");

			boolean pri = true;
			if ((codigoLanc!= null) && (codigoLanc.trim().length() != 0)) {
				sql.append(" where codigoLanc = '"+codigoLanc+"' ");
				pri = false;
			}

			if ((descricaoLanc != null) && (descricaoLanc.trim().length() != 0)) {
				if(pri){
					sql.append(" where upper(descricaoLanc) like upper('"+descricaoLanc+"%"+"') ");
				}else{
					sql.append(" and upper(descricaoLanc) like upper('"+descricaoLanc+"%"+"') ");
				}

			}
			if(Ordem != null && Ordem.trim().length() != 0){
				sql.append(" Order By "+ Ordem);
			}			
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<LancamentoVO>();
				do {
					vo = new LancamentoVO();
					vo.setCodigoLanc(rs.getString("codigoLanc"));
					vo.setCodigoFun(rs.getInt("codigoFun"));
					vo.setNomeFun(rs.getString("nomeFun"));
					vo.setCodigoHist(rs.getInt("codigoHist"));
					vo.setValorLanc(rs.getString("valorLanc"));
					vo.setDescricaoLanc(rs.getString("descricaoLanc"));
					vo.setDebCreHist(rs.getString("debCreHist"));
					vo.setCodigoEmp(rs.getInt("codigoEmp"));
					vo.setDataEmissao(rs.getDate("dataEmissao"));
					vo.setDataVenc(rs.getDate("dataVenc"));
					vo.setDataPgto(rs.getDate("dataPgto"));
					vo.setComplLanc(rs.getString("complLanc"));
					lista.add(vo);
				}
				while (rs.next());
			}
			pstmt.close();
			rs.close();
			conectar.close();
			return lista;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo de pesquisa de registro
	 * @param codigoLanc
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public LancamentoVO pesquisar(int codigoLanc) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT codigoLanc, codigoFun, codigoHist, valorLanc, descricaoLanc, debCreHist, codigoEmp, " +
				"dataEmissao, dataVenc, dataPgto, complLanc"
				+ " FROM lancamento WHERE codigoLanc = " + codigoLanc);			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		LancamentoVO vo = new LancamentoVO();
		if (rs.next()) {
			vo.setCodigoLanc(rs.getString("codigoLanc"));
			vo.setCodigoFun(rs.getInt("codigoFun"));
			vo.setCodigoHist(rs.getInt("codigoHist"));
			vo.setValorLanc(rs.getString("valorLanc"));
			vo.setDescricaoLanc(rs.getString("descricaoLanc"));
			vo.setDebCreHist(rs.getString("debCreHist"));
			vo.setCodigoEmp(rs.getInt("codigoEmp"));
			vo.setDataEmissao(rs.getDate("dataEmissao"));
			vo.setDataVenc(rs.getDate("dataVenc"));
			vo.setDataPgto(rs.getDate("dataPgto"));
			vo.setComplLanc(rs.getString("complLanc"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}
	/**
	 * Metodo responsavel por pegar o ultimo codigo incluido no banco.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String ultCodigo() throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT max(codigoLanc) as cod FROM lancamento"); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		String maiorCod = "";
		if (rs.next()) {
			maiorCod = rs.getString("cod");
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return maiorCod;		
	}

	/**
	 * Metodo de Inclusão de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int incluir(LancamentoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement(
				"SELECT codigoLanc "
				+ " FROM lancamento WHERE codigoLanc = ? ");
		pstmt.setInt(1, vo.getCodigoLanc());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO lancamento (codigoLanc, codigoFun, codigoHist, valorLanc, descricaoLanc, " +
					"debCreHist, codigoEmp, dataEmissao, dataVenc, dataPgto, complLanc)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

			pstmtInsert.setInt(1, vo.getCodigoLanc());
			pstmtInsert.setInt(2, vo.getCodigoFun());
			pstmtInsert.setInt(3, vo.getCodigoHist());
			pstmtInsert.setDouble(4, vo.getValorLanc());
			pstmtInsert.setString(5, vo.getDescricaoLanc().toUpperCase());
			pstmtInsert.setString(6, vo.getDebCreHist().toUpperCase());
			pstmtInsert.setInt(7, vo.getCodigoEmp());
			pstmtInsert.setString(8, vo.getDataEmissaoFunSQL());
			pstmtInsert.setString(9, vo.getDataVencFunSQL());
			pstmtInsert.setString(10, vo.getDataPgtoFunSQL());
			pstmtInsert.setString(11, vo.getComplLanc().toUpperCase());
			registrosAfetados = pstmtInsert.executeUpdate();		 

			return registrosAfetados;
		}else{			
			return -1;				
		}
	}

	/**
	 * Metodo de Alteração de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int alterar(LancamentoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(			
				"Update lancamento set codigoFun = ?," +
				"codigoHist =?," +
				"valorLanc =?," +
				"descricaoLanc = ?," +
				"debCreHist = ?," +
				"codigoEmp = ?," +
				"dataEmissao = ?," +
				"dataVenc =?," +
				"dataPgto = ?,"+
				"complLanc = ?"+
		"WHERE codigoLanc = ? ");


		pstmtInsert.setInt(1, vo.getCodigoFun());
		pstmtInsert.setInt(2, vo.getCodigoHist());
		pstmtInsert.setDouble(3, vo.getValorLanc());
		pstmtInsert.setString(4, vo.getDescricaoLanc().toUpperCase());
		pstmtInsert.setString(5, vo.getDebCreHist().toUpperCase());
		pstmtInsert.setInt(6, vo.getCodigoEmp());
		pstmtInsert.setString(7, vo.getDataEmissaoFunSQL());
		pstmtInsert.setString(8, vo.getDataVencFunSQL());
		pstmtInsert.setString(9, vo.getDataPgtoFunSQL());
		pstmtInsert.setString(10, vo.getComplLanc().toUpperCase());
		pstmtInsert.setInt(11, vo.getCodigoLanc());

		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}

	/**
	 * Metodo de Exclusão de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deletar(LancamentoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM lancamento WHERE codigoLanc = ?");
		pstmtInsert.setInt(1, vo.codigoLanc);
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}

	/**
	 * Metodo responsavel por selecionar os registor no banco e gerar a listagem para impressão
	 * 
	 */
	public String relLancamento(String codigoEmp, String codigoFun, String codigoHist, String datainiEmissao, String datafimEmissao, String datainiPgto, String datafimPgto, String datainiVenc, String datafimVenc){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"lancamentos.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer(
					"select lanc.*, func.nomeFun, his.descricaoHist from lancamento lanc left outer join historico his on (lanc.codigoHist = his.codigoHist) "+
                    "left outer join funcionario func on (lanc.codigoFun = func.codigofun) ");
			
			
			boolean pri = true;
			
			if(codigoEmp.trim().length() > 0){
				sql.append(" where lanc.codigoEmp = "+codigoEmp);
				pri = false;
			}
			
			if(codigoFun.trim().length() > 0){
				if(pri){
					sql.append(" where lanc.codigoFun = "+codigoFun);
					pri = false;
				}else{
					sql.append(" and lanc.codigoFun = "+codigoFun);
				}
			}
			
			if(codigoHist.trim().length() > 0){
				if(pri){
					sql.append(" where lanc.codigoHist = "+codigoHist);
					pri = false;
				}else{
					sql.append(" and lanc.codigoHist = "+codigoHist);
				}
			}
			
			if((datainiEmissao.trim().length() == 10)&&(datafimEmissao.trim().length() == 10)){
				if(pri){
					sql.append(" where dataEmissao between '"+funcaoData.converteformatoEN(datainiEmissao)+"' and '"+funcaoData.converteformatoEN(datafimEmissao)+"'");
					pri = false;
				}else{
					sql.append(" and dataEmissao between '"+funcaoData.converteformatoEN(datainiEmissao)+"' and '"+funcaoData.converteformatoEN(datafimEmissao)+"'");

				}
			}
			
			if((datainiPgto.trim().length() == 10)&&(datafimPgto.trim().length() == 10)){
				if(pri){
					sql.append(" where dataPgto between '"+funcaoData.converteformatoEN(datainiPgto)+"' and '"+funcaoData.converteformatoEN(datafimPgto)+"'");
					pri = false;
				}else{
					sql.append(" and dataPgto between '"+funcaoData.converteformatoEN(datainiPgto)+"' and '"+funcaoData.converteformatoEN(datafimPgto)+"'");
				}
			}

			if((datainiVenc.trim().length() == 10)&&(datafimVenc.trim().length() == 10)){
				if(pri){
					sql.append(" where dataVenc between '"+funcaoData.converteformatoEN(datainiVenc)+"' and '"+funcaoData.converteformatoEN(datafimVenc)+"'");
					pri = false;
				}else{
					sql.append(" and dataVenc between '"+funcaoData.converteformatoEN(datainiVenc)+"' and '"+funcaoData.converteformatoEN(datafimVenc)+"'");
				}
			}

			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.substring(0,sql.length()));
			//System.out.println(pstmt);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if(rs.next()){
				rs.beforeFirst();
			    JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
			    
				JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrRS);				

				JasperViewer viewer = new JasperViewer(impressao, false);
				viewer.setVisible(true);

				retorno = "Concluído com Sucesso!";
			}else{
				retorno = "Relatório Não Possui Informação!";
			}
			pstmt.close();
			rs.close();
			con.close();
			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	/**
	 * Metodo responsavel por selecionar os registor no banco e gerar a listagem para impressão
	 * 
	 */
	public String relRecibo(int codigoFun, String dataini, String datafin){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"Extrato_Recibo.jrxml");

			HashMap<String, String> parametros = new HashMap<String, String>();
			//Pegando 
			EmpresaDAO daoemp = new EmpresaDAO();
			EmpresaVO voemp = daoemp.pesquisar(1); 

			FuncionarioDAO daofunc = new FuncionarioDAO();
			FuncionarioVO vofunc = daofunc.pesquisar(codigoFun); 
			
			
			if(voemp != null){
				parametros.put("empresa", voemp.getNomeFantasia());	
			}else{
				parametros.put("empresa", "");
			}			
			
			if(voemp != null){
				parametros.put("CNPJ/CNPJ", voemp.getCnpj());	
			}else{
				parametros.put("CNPJ/CNPJ", "");
			}
			
			parametros.put("funcionario", vofunc.getNomeFun());
			parametros.put("CPF", vofunc.getCpfFun());
			parametros.put("cargoFuncionario", vofunc.getDescricaoCargo());
			parametros.put("dataAdmissao", vofunc.getAdmissaoFunBusca());
			parametros.put("carteiraTrabalho", vofunc.getCtpsFun());
			
			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("select codigoLanc, descricaoLanc, valorLanc, debCreHist," +
					                            " if(debCreHist = '0',valorLanc,0) as debito, " +
					                            " if(debCreHist <> '0',valorLanc,0) as credito " +
					                            " FROM lancamento Where dataPgto <> '0001-01-01' ");
			
			if((dataini.trim().length() == 10)&&(datafin.trim().length() == 10)){
				sql.append(" and dataPgto between '"+funcaoData.converteformatoEN(dataini)+"' and '"+funcaoData.converteformatoEN(datafin)+"'");
			}
			if(codigoFun!=0){
				sql.append(" and codigoFun = '"+codigoFun+"'");
			}
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.substring(0,sql.length()));
			//System.out.println(pstmt);
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if(rs.next()){
				rs.beforeFirst();
			    JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
				JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrRS);				

				JasperViewer viewer = new JasperViewer(impressao, false);
				viewer.setVisible(true);

				retorno = "";
			}else{
				retorno = "Relatório Não Possui Informação!";
			}
			rs.close();
			pstmt.close();			
			con.close();
			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
