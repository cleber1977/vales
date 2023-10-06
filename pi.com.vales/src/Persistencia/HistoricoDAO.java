package Persistencia;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Conexao.Database;

/**
 * Classe Composta pelos metodos do persistencia no banco
 * @author Cleber / Moacir
 * 	Data 29/10/2009
 */
public class HistoricoDAO {
	/**
	 * Metodo de Listagem de registro
	 * @param codigoHist
	 * @param descricaoHist
	 * @param Ordem
	 */
	public ArrayList<HistoricoVO> geral(String  codigoHist, String descricaoHist, String Ordem) {
		ArrayList<HistoricoVO> lista = null;
		HistoricoVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT codigoHist, descricaoHist, debCreHist"
					+ " FROM historico");
			boolean pri = true;
			if ((codigoHist!= null) && (codigoHist.trim().length() != 0)) {
				sql.append(" where codigoHist = '"+codigoHist+"' ");
				pri = false;
			}

			if ((descricaoHist != null) && (descricaoHist.trim().length() != 0)) {
				if(pri){
					sql.append(" where upper(descricaoHist) like upper('"+descricaoHist+"%"+"') ");
				}else{
					sql.append(" and upper(descricaoHist) like upper('"+descricaoHist+"%"+"') ");
				}	
			}

			if(Ordem != null && Ordem.trim().length() != 0){
				sql.append(" Order By "+ Ordem);
			}			
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<HistoricoVO>();
				do {
					vo = new HistoricoVO();
					vo.setCodigoHist(rs.getString("codigoHist"));
					vo.setDescricaoHist(rs.getString("descricaoHist"));
					vo.setDebCreHist(rs.getString("debCreHist"));
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
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HistoricoVO pesquisar(int codigoHistorico) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT codigoHist, descricaoHist, debCreHist"
				+ " FROM historico WHERE codigoHist = " + codigoHistorico);			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		HistoricoVO vo = new HistoricoVO();
		if (rs.next()) {
			vo.setCodigoHist(rs.getString("codigoHist"));
			vo.setDescricaoHist(rs.getString("descricaoHist"));
			vo.setDebCreHist(rs.getString("debCreHist"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}
	/**
	 * Metodo responsavel por pegar o ultimo codigo do banco
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String ultCodigo() throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
		"SELECT max(codigoHist) as cod FROM historico"); 			

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
	 * Metodo de pesquisa de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HistoricoVO pesquisar(String codigoHistorico) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT codigoHist, descricaoHist, debCreHist"
				+ " FROM historico WHERE codigoHist = " + codigoHistorico);			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		HistoricoVO vo = new HistoricoVO();
		if (rs.next()) {
			vo.setCodigoHist(rs.getString("codigoHist"));
			vo.setDescricaoHist(rs.getString("descricaoHist"));
			vo.setDebCreHist(rs.getString("debCreHist"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}

	/**
	 * Metodo de Inclusão de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int incluir(HistoricoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement(
				"SELECT codigoHist "
				+ " FROM historico WHERE codigoHist = ? ");
		pstmt.setInt(1, vo.getCodigoHist());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO historico (codigoHist, descricaoHist, debCreHist)"
					+ "VALUES(?,?,?)");

			pstmtInsert.setInt(1, vo.getCodigoHist());
			pstmtInsert.setString(2, vo.getDescricaoHist().toUpperCase());
			pstmtInsert.setString(3, vo.getDebCreHist());

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
	public int alterar(HistoricoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
				"Update historico " +
		"Set descricaoHist = ? WHERE codigoHist = ? ");


		pstmtInsert.setString(1, vo.getDescricaoHist().toUpperCase());
		//pstmtInsert.setString(2, vo.getDebCreHist());
		pstmtInsert.setInt(2, vo.getCodigoHist());

		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}

	/**
	 * Metodo de Exclusão de registro
	 * @param codigoHist
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deletar(HistoricoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM historico WHERE codigoHist = ?");
		pstmtInsert.setInt(1, vo.codigoHist);
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}
	/**
	 * Metodo de pesquisa de registro por codigo se ja existe cadastrado na tabela Lancamento, 
	 * @param historico
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean integridadeLanc(int historico) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM lancamento WHERE codigoHist = '"+historico+"'"); 			

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
	public String lisHistorico(){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"listagem_historico.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("SELECT * FROM historico");
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
}
