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
public class CargoDAO {
	/**
	 * Metodo de Listagem de registro
	 * @param CodigoCar
	 * @param descCargo
	 * @param Ordem
	 */
	public ArrayList<CargoVO> geral(String  CodigoCar, String descCargo, String Ordem) {
		ArrayList<CargoVO> lista = null;
		CargoVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT codigoCargo, descricaoCargo, codContabil, salarioCargo"
					+ " FROM cargo");
			boolean pri = true;
			if ((CodigoCar!= null) && (CodigoCar.trim().length() != 0)) {
				sql.append(" where codigoCargo = '"+CodigoCar+"' ");
				pri = false;
			}
			if ((descCargo != null) && (descCargo.trim().length() != 0)) {
				if(pri){
					sql.append(" where upper(descricaoCargo) like upper('"+descCargo+"%"+"') ");
				}else{
					sql.append(" and upper(descricaoCargo) like upper('"+descCargo+"%"+"') ");
				}

			}
			if(Ordem != null && Ordem.trim().length() != 0){
				sql.append(" Order By "+ Ordem);
			}			
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<CargoVO>();
				do {
					vo = new CargoVO();
					vo.setCodigoCargo(rs.getString("codigoCargo"));
					vo.setDescricaoCargo(rs.getString("descricaoCargo"));
					vo.setSalarioCargo(rs.getString("codContabil"));
					vo.setCodContabil(rs.getString("salarioCargo"));
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
	 * @param codigoCargo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public CargoVO pesquisar(int codigoCargo) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer("SELECT codigoCargo, descricaoCargo, salarioCargo, codContabil "
				+ " FROM cargo WHERE codigoCargo = " + codigoCargo); 

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		CargoVO vo = new CargoVO();
		if (rs.next()) {
			vo.setCodigoCargo(rs.getString("codigoCargo")); 
			vo.setDescricaoCargo(rs.getString("descricaoCargo"));
			vo.setSalarioCargo(rs.getString("salarioCargo"));
			vo.setCodContabil(rs.getString("codContabil"));
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
		"SELECT max(codigoCargo) as cod FROM cargo"); 			

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

	public int incluir(CargoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement(
				"SELECT codigoCargo "
				+ " FROM cargo WHERE codigoCargo = ? "); // = ? and sql_deleted <> 'T' ");
		pstmt.setInt(1, vo.getCodigoCargo());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO cargo (codigoCargo, descricaoCargo, codContabil, salarioCargo) "
					+ "VALUES(?,?,?,?)");

			pstmtInsert.setInt(1, vo.getCodigoCargo());
			pstmtInsert.setString(2, vo.getDescricaoCargo().toUpperCase());
			pstmtInsert.setString(3, vo.getCodContabil().toUpperCase());
			pstmtInsert.setDouble(4, vo.getSalarioCargo());
			registrosAfetados = pstmtInsert.executeUpdate();		 

			return registrosAfetados;//converter string
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
	public int alterar(CargoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement("UPDATE cargo set descricaoCargo = ?, "
				+ "codContabil = ?, "
				+ "salarioCargo = ? " + "where codigoCargo = ? ");
		pstmtInsert.setString(1, vo.getDescricaoCargo().toUpperCase());
		pstmtInsert.setString(2, vo.getCodContabil().toUpperCase());
		pstmtInsert.setDouble(3, vo.getSalarioCargo());
		pstmtInsert.setInt(4, vo.getCodigoCargo());
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
	public int deletar(CargoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM cargo WHERE codigoCargo = ?");
		pstmtInsert.setInt(1, vo.getCodigoCargo());
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
	public boolean integridadeFunc(int codigoCargo) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM funcionario WHERE codigoCargo = '"+codigoCargo+"'"); 			

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
	public String lisCargo(){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"listagem_cargo.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("SELECT * FROM cargo");
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