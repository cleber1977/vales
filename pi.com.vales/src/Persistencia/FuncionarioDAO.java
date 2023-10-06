package Persistencia;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Conexao.Database;

/**
 * Classe Composta pelos metodos do persistencia no banco
 * @author Cleber / Moacir
 *  Data 29/10/2009
 */
public class FuncionarioDAO {
	/**
	 * Metodo de Listagem de registro
	 * @param codigoFun
	 * @param nomeFun
	 * @param cpfFun
	 * @param Ordem
	 */
	public ArrayList<FuncionarioVO> geral(String  codigoFun, String nomeFun, String Ordem) {
		ArrayList<FuncionarioVO> lista = null;
		FuncionarioVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT codigoFun,  nomeFun, rgFun, cpfFun, ctpsFun, dataNascFun, sexoFun, endFun, bairroFun, cidadeFun, " +
					"ufFun, codigoEmp, codigoCargo, admissaoFun, demissaoFun, salarioCargo, cepFun, foneFun, " +
					"celularFun, paiFun, maeFun, enderecoPais, bairroPais, cidadePais, ufPais, fonePais, mesfechado" +
			" FROM funcionario");

			boolean pri = true;
			if ((codigoFun!= null) && (codigoFun.trim().length() != 0)) {
				sql.append(" where codigoFun = '"+codigoFun+"' ");
				pri = false;
			}

			if ((nomeFun != null) && (nomeFun.trim().length() != 0)) {	
				if(pri){
					sql.append(" where upper(nomeFun) like upper('"+nomeFun+"%"+"') ");
				}else{
					sql.append(" and upper(nomeFun) like upper('"+nomeFun+"%"+"') ");
				}
				if(Ordem != null && Ordem.trim().length() != 0){
					sql.append(" Order By "+ Ordem);
				}			
			}
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<FuncionarioVO>();

				do {
					vo = new FuncionarioVO();
					vo.setCodigoFun(rs.getString("codigoFun"));
					vo.setNomeFun(rs.getString("nomeFun"));
					vo.setRgFun(rs.getString("rgFun"));
					vo.setCpfFun(rs.getString("cpfFun"));
					vo.setCtpsFun(rs.getString("ctpsFun"));
					vo.setDataNascFun(rs.getDate("dataNascFun"));
					vo.setSexoFun(rs.getString("sexoFun"));
					vo.setEndFun(rs.getString("endFun"));
					vo.setBairroFun(rs.getString("bairroFun"));
					vo.setCidadeFun(rs.getString("cidadeFun"));
					vo.setUfFun(rs.getString("ufFun"));
					vo.setCodigoEmp(rs.getInt("codigoEmp"));
					vo.setCodigoCargo(rs.getInt("codigoCargo"));
					vo.setAdmissaoFun(rs.getDate("admissaoFun"));
					vo.setDemissaoFun(rs.getDate("demissaoFun"));
					vo.setSalarioCargo(rs.getString("salarioCargo"));
					vo.setCepFun(rs.getString("cepFun"));
					vo.setFoneFun(rs.getString("foneFun"));
					vo.setCelularFun(rs.getString("celularFun"));
					vo.setPaiFun(rs.getString("paiFun"));
					vo.setMaeFun(rs.getString("maeFun"));
					vo.setEnderecoPais(rs.getString("enderecoPais"));
					vo.setBairroPais(rs.getString("bairroPais"));
					vo.setCidadePais(rs.getString("cidadePais"));
					vo.setUfPais(rs.getString("ufPais"));
					vo.setFonePais(rs.getString("fonePais"));
					vo.setMesfechado(rs.getString("mesfechado"));
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
	 * @param codigoFun
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioVO pesquisar(int codigoFun) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT f.*, c.descricaoCargo"
				+ " FROM funcionario f left join cargo c on f.codigoCargo = c.codigoCargo WHERE f.codigoFun = " + codigoFun); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		FuncionarioVO vo = new FuncionarioVO();
		if (rs.next()) {


			vo.setCodigoFun(rs.getString("codigoFun"));
			vo.setNomeFun(rs.getString("nomeFun"));
			vo.setRgFun(rs.getString("rgFun"));
			vo.setCpfFun(rs.getString("cpfFun"));
			vo.setCtpsFun(rs.getString("ctpsFun"));
			vo.setDataNascFun(rs.getDate("dataNascFun"));
			vo.setSexoFun(rs.getString("sexoFun"));
			vo.setEndFun(rs.getString("endFun"));
			vo.setBairroFun(rs.getString("bairroFun"));
			vo.setCidadeFun(rs.getString("cidadeFun"));
			vo.setUfFun(rs.getString("ufFun"));
			vo.setCodigoEmp(rs.getInt("codigoEmp"));
			vo.setCodigoCargo(rs.getInt("codigoCargo"));
			vo.setDescricaoCargo(rs.getString("descricaoCargo"));
			vo.setAdmissaoFun(rs.getDate("admissaoFun"));
			vo.setDemissaoFun(rs.getDate("demissaoFun"));
			vo.setSalarioCargo(rs.getString("salarioCargo"));
			vo.setCepFun(rs.getString("cepFun"));
			vo.setFoneFun(rs.getString("foneFun"));
			vo.setCelularFun(rs.getString("celularFun"));
			vo.setPaiFun(rs.getString("paiFun"));
			vo.setMaeFun(rs.getString("maeFun"));
			vo.setEnderecoPais(rs.getString("enderecoPais"));
			vo.setBairroPais(rs.getString("bairroPais"));
			vo.setCidadePais(rs.getString("cidadePais"));
			vo.setUfPais(rs.getString("ufPais"));
			vo.setFonePais(rs.getString("fonePais"));
			vo.setMesfechado(rs.getString("mesfechado"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}

	/**
	 * Metodo de pesquisa de registro por cpf se ja existe cadastrado
	 * @param cpfFun
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean existeCPF(String  cpfFun) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM funcionario WHERE cpfFun = '"+cpfFun+"'"); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();

		boolean achou = false;
		if (rs.next()) {
			achou = true;
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return achou;		
	}

	public String ultCodigo() throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
		"SELECT max(codigoFun) as cod FROM funcionario"); 			

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
	public int incluir(FuncionarioVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement("SELECT codigoFun "+ " FROM funcionario WHERE codigoFun = ? ");
		pstmt.setInt(1, vo.getCodigoFun());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO funcionario (codigoFun, nomeFun, rgFun, cpfFun,ctpsFun, dataNascFun, sexoFun, endFun, bairroFun, " +
					"cidadeFun, ufFun, codigoEmp, codigoCargo, admissaoFun, demissaoFun, salarioCargo, cepFun, foneFun, " +
					"celularFun, paiFun, maeFun, enderecoPais, bairroPais, cidadePais, ufPais, fonePais, mesfechado)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmtInsert.setInt(1, vo.getCodigoFun());
			pstmtInsert.setString(2, vo.getNomeFun().toUpperCase());
			pstmtInsert.setString(3, vo.getRgFun().toUpperCase());
			pstmtInsert.setString(4, vo.getMascCPFFun());
			pstmtInsert.setString(5, vo.getCtpsFun().toUpperCase());
			pstmtInsert.setString(6, vo.getDataNascFunSQL());
			pstmtInsert.setString(7, vo.getSexoFun().toUpperCase());
			pstmtInsert.setString(8, vo.getEndFun().toUpperCase());
			pstmtInsert.setString(9, vo.getBairroFun().toUpperCase());
			pstmtInsert.setString(10, vo.getCidadeFun().toUpperCase());
			pstmtInsert.setString(11, vo.getUfFun().toUpperCase());
			pstmtInsert.setInt(12, vo.getCodigoEmp());
			pstmtInsert.setInt(13, vo.getCodigoCargo());
			pstmtInsert.setString(14, vo.getAdmissaoFunFunSQL());
			pstmtInsert.setString(15, vo.getDemissaoFunFunSQL());
			pstmtInsert.setDouble(16, vo.getSalarioCargo());
			pstmtInsert.setString(17, vo.getCepFun().toUpperCase());
			pstmtInsert.setString(18, vo.getRemoveMascFone());
			pstmtInsert.setString(19, vo.getRemoveMascCelular());
			pstmtInsert.setString(20, vo.getPaiFun().toUpperCase());
			pstmtInsert.setString(21, vo.getMaeFun().toUpperCase());
			pstmtInsert.setString(22, vo.getEnderecoPais().toUpperCase());
			pstmtInsert.setString(23, vo.getBairroPais().toUpperCase());
			pstmtInsert.setString(24, vo.getCidadePais().toUpperCase());
			pstmtInsert.setString(25, vo.getUfPais().toUpperCase());
			pstmtInsert.setString(26, vo.getRemoveMascFonePais());
			pstmtInsert.setString(27, vo.getMesfechado().toUpperCase());
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
	public int alterar(FuncionarioVO vo) throws ClassNotFoundException, SQLException {
		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
				"UPDATE funcionario set nomeFun = ?, "
				+ "rgFun = ?, "
				+ "cpfFun = ?,"
				+ "ctpsFun= ?," 
				+ "dataNascFun= ?,"
				+ "sexoFun = ?,"
				+ "endFun= ?,"
				+ "bairroFun= ?,"
				+ "cidadeFun= ?,"
				+ "ufFun= ?,"
				+ "codigoEmp= ?,"
				+ "codigoCargo= ?,"
				+ "admissaoFun= ?,"
				+ "demissaoFun= ?,"
				+ "salarioCargo= ?,"
				+ "cepFun= ?,"
				+ "foneFun= ?,"
				+ "celularFun= ?,"
				+ "paiFun= ?,"
				+ "maeFun= ?,"
				+ "enderecoPais= ?,"
				+ "bairroPais= ?,"
				+ "cidadePais= ?,"
				+ "ufPais= ?,"
				+ "fonePais= ?,"
				+ "mesfechado = ? " + "where codigoFun = ? ");

		pstmtInsert.setString(1, vo.getNomeFun().toUpperCase());
		pstmtInsert.setString(2, vo.getRgFun().toUpperCase());
		pstmtInsert.setString(3, vo.getMascCPFFun());
		pstmtInsert.setString(4, vo.getCtpsFun().toUpperCase());
		pstmtInsert.setString(5, vo.getDataNascFunSQL());
		pstmtInsert.setString(6, vo.getSexoFun().toUpperCase());
		pstmtInsert.setString(7, vo.getEndFun().toUpperCase());
		pstmtInsert.setString(8, vo.getBairroFun().toUpperCase());
		pstmtInsert.setString(9, vo.getCidadeFun().toUpperCase());
		pstmtInsert.setString(10, vo.getUfFun().toUpperCase());
		pstmtInsert.setInt(11, vo.getCodigoEmp());
		pstmtInsert.setInt(12, vo.getCodigoCargo());
		pstmtInsert.setString(13, vo.getAdmissaoFunFunSQL());
		pstmtInsert.setString(14, vo.getDemissaoFunFunSQL());
		pstmtInsert.setDouble(15, vo.getSalarioCargo());
		pstmtInsert.setString(16, vo.getCepFun().toUpperCase());
		pstmtInsert.setString(17, vo.getRemoveMascFone());
		pstmtInsert.setString(18, vo.getRemoveMascCelular());
		pstmtInsert.setString(19, vo.getPaiFun().toUpperCase());
		pstmtInsert.setString(20, vo.getMaeFun().toUpperCase());
		pstmtInsert.setString(21, vo.getEnderecoPais().toUpperCase());
		pstmtInsert.setString(22, vo.getBairroPais().toUpperCase());
		pstmtInsert.setString(23, vo.getCidadePais().toUpperCase());
		pstmtInsert.setString(24, vo.getUfPais().toUpperCase());
		pstmtInsert.setString(25, vo.getRemoveMascFonePais());
		pstmtInsert.setString(26, vo.getMesfechado().toUpperCase());
		pstmtInsert.setInt(27, vo.getCodigoFun());
		registrosAfetados = pstmtInsert.executeUpdate();	
		conectar.close();
		if (registrosAfetados > 0){

		}
		return registrosAfetados;
	}

	/**
	 * Metodo de Exclusão de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deletar(FuncionarioVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM funcionario WHERE codigoFun = ?");
		pstmtInsert.setInt(1, vo.codigoFun);
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		if (registrosAfetados > 0){
			JOptionPane.showMessageDialog(null,  "Registro Excluido com Sucesso", null,   JOptionPane.INFORMATION_MESSAGE);
		}
		return registrosAfetados;
	}
	/**
	 * Metodo de pesquisa de registro por codigo se ja existe cadastrado na tabela funcionario, 
	 * @param codigoCargo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean integridadeLanc(int funcionario) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM lancamento WHERE codigoFun = '"+funcionario+"'"); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();

		boolean achou = false;
		if (rs.next()) {
			achou = true;
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return achou;		
	}

	/**
	 * Metodo responsavel por selecionar os registor no banco e gerar a listagem para impressão
	 * 
	 */
	public String lisFuncionario(){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"listagem_funcionario.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("SELECT * FROM funcionario");
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.substring(0,sql.length()));

			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if(rs.next()){
				JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, con);				

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
	public String fichaFuncionario(int codigoFun){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"Ficha_Funcionario.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			FuncionarioDAO daofunc = new FuncionarioDAO();
			FuncionarioVO vofunc = daofunc.pesquisar(codigoFun); 


			parametros.put("cargoFuncionario", vofunc.getDescricaoCargo());
			
			StringBuffer sql = new StringBuffer("SELECT * FROM funcionario where codigoFun = '"+codigoFun+"'");
			System.out.println(sql.toString());
			
			Connection con = Database.getConnection();
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql.substring(0,sql.length()));

			ResultSet rs = (ResultSet) pstmt.executeQuery(sql.toString());
			JRResultSetDataSource Query = new JRResultSetDataSource(rs);
			if(rs.next()){
				rs.beforeFirst();
				JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, Query);				

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
}
