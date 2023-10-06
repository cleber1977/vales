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
public class EmpresaDAO {

	/**
	 * Metodo de Listagem de registro
	 * @param codigoEmp
	 * @param razaoSocial
	 * @param nomeFantasia
	 * @param Ordem
	 */
	public ArrayList<EmpresaVO> geral(String  codigoEmp, String razaoSocial, String Ordem) {
		ArrayList<EmpresaVO> lista = null;
		EmpresaVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT codigoEmp, razaoSocial, nomeFantasia, cnpj, inscrEst, endEmp, numeroEmp, bairroEmp, cidadeEmp, ufEmp, cepEmp, foneEmp"
					+ " FROM empresa");
			boolean pri = true;
			if ((codigoEmp!= null) && (codigoEmp.trim().length() != 0)) {
				sql.append(" where codigoEmp = '"+codigoEmp+"' ");
				pri = false;
			}
			if ((razaoSocial != null) && (razaoSocial.trim().length() != 0)) {
				if(pri){
					sql.append(" where upper(razaoSocial) like upper('"+razaoSocial+"%"+"') ");
				}else{
					sql.append(" and upper(razaoSocial) like upper('"+razaoSocial+"%"+"') ");
				}

			}
			if(Ordem != null && Ordem.trim().length() != 0){
				sql.append(" Order By "+ Ordem);
			}			
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<EmpresaVO>();
				do {
					vo = new EmpresaVO();
					vo.setCodigoEmp(rs.getString("codigoEmp"));
					vo.setRazaoSocial(rs.getString("razaoSocial"));
					vo.setNomeFantasia(rs.getString("nomeFantasia"));
					vo.setCnpj(rs.getString("cnpj"));
					vo.setInscrEst(rs.getString("inscrEst"));
					vo.setEndEmp(rs.getString("endEmp"));
					vo.setNumeroEmp(rs.getString("numeroEmp"));
					vo.setBairroEmp(rs.getString("bairroEmp"));
					vo.setCidadeEmp(rs.getString("cidadeEmp"));
					vo.setUfEmp(rs.getString("ufEmp"));
					vo.setCepEmp(rs.getString("cepEmp"));
					vo.setFoneEmp(rs.getString("foneEmp"));
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
	 * @param codigoEmp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public EmpresaVO pesquisar(int codigoEmp) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT codigoEmp, razaoSocial, nomeFantasia, cnpj, " +
				"inscrEst, endEmp, numeroEmp, bairroEmp, cidadeEmp, ufEmp, cepEmp, foneEmp"
				+ " FROM empresa WHERE codigoEmp = " + codigoEmp); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		EmpresaVO vo = new EmpresaVO();
		if (rs.next()) {

			vo.setCodigoEmp(rs.getString("codigoEmp"));
			vo.setRazaoSocial(rs.getString("razaoSocial"));
			vo.setNomeFantasia(rs.getString("nomeFantasia"));
			vo.setCnpj(rs.getString("cnpj"));
			vo.setInscrEst(rs.getString("inscrEst"));
			vo.setEndEmp(rs.getString("endEmp"));
			vo.setNumeroEmp(rs.getString("numeroEmp"));
			vo.setBairroEmp(rs.getString("bairroEmp"));
			vo.setCidadeEmp(rs.getString("cidadeEmp"));
			vo.setUfEmp(rs.getString("ufEmp"));
			vo.setCepEmp(rs.getString("cepEmp"));
			vo.setFoneEmp(rs.getString("foneEmp"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}
	/**
	 * Metodo de pesquisa de registro
	 * @param codigoEmp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean existeCNPJ(String cnpj) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM empresa WHERE cnpj = '"+cnpj+"'"); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		boolean encontrou = false; 
		if (rs.next()) {
			encontrou = true;
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return encontrou;		
	}

	/**
	 * Metodo responsavel por pegar o ultimo codigo do banco
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String ultCodigo() throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
		"SELECT max(codigoEmp) as cod FROM empresa"); 			

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
	public int incluir(EmpresaVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement(
				"SELECT codigoEmp "
				+ " FROM empresa WHERE codigoEmp = ? "); // = ? and sql_deleted <> 'T' ");
		pstmt.setInt(1, vo.getCodigoEmp());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO empresa (codigoEmp, razaoSocial, nomeFantasia, cnpj, inscrEst, endEmp, numeroEmp, bairroEmp, cidadeEmp, ufEmp, cepEmp, foneEmp) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmtInsert.setInt(1, vo.getCodigoEmp());
			pstmtInsert.setString(2, vo.getRazaoSocial().toUpperCase());
			pstmtInsert.setString(3, vo.getNomeFantasia().toUpperCase());
			pstmtInsert.setString(4, vo.getMascCnpj());
			pstmtInsert.setString(5, vo.getInscrEst().toUpperCase());
			pstmtInsert.setString(6, vo.getEndEmp().toUpperCase());
			pstmtInsert.setInt(7, vo.getNumeroEmp());
			pstmtInsert.setString(8, vo.getBairroEmp().toUpperCase());
			pstmtInsert.setString(9, vo.getCidadeEmp().toUpperCase());
			pstmtInsert.setString(10, vo.getUfEmp());
			pstmtInsert.setString(11, vo.getCepEmp().toUpperCase());
			pstmtInsert.setString(12, vo.getRemoveMascFone());
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
	public int alterar(EmpresaVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement("UPDATE empresa set razaoSocial = ?, "
				+ "nomeFantasia = ?, "
				+ "cnpj = ?, "
				+ "inscrEst = ?, "
				+ "endEmp = ?, "
				+ "numeroEmp = ?, "
				+ "bairroEmp = ?, "
				+ "cidadeEmp = ?, "
				+ "ufEmp = ?, "
				+ "cepEmp = ?, "				  																
				+ "foneEmp = ? " + "where codigoEmp = ? ");
		pstmtInsert.setString(1, vo.getRazaoSocial().toUpperCase());
		pstmtInsert.setString(2, vo.getNomeFantasia().toUpperCase());
		pstmtInsert.setString(3, vo.getMascCnpj());
		pstmtInsert.setString(4, vo.getInscrEst().toUpperCase());
		pstmtInsert.setString(5, vo.getEndEmp().toUpperCase());
		pstmtInsert.setInt(6, vo.getNumeroEmp());
		pstmtInsert.setString(7, vo.getBairroEmp().toUpperCase());
		pstmtInsert.setString(8, vo.getCidadeEmp().toUpperCase());
		pstmtInsert.setString(9, vo.getUfEmp());
		pstmtInsert.setString(10, vo.getCepEmp().toUpperCase());
		pstmtInsert.setString(11, vo.getRemoveMascFone());
		pstmtInsert.setInt(12, vo.getCodigoEmp());

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
	public int deletar(EmpresaVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM empresa WHERE codigoEmp = ?");
		pstmtInsert.setInt(1, vo.codigoEmp);
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		if (registrosAfetados > 0){

			JOptionPane.showMessageDialog(null,  "Registro Excluido com Sucesso", null,   JOptionPane.INFORMATION_MESSAGE);
		}

		return registrosAfetados;
	}
	/**
	 * Metodo de pesquisa de registro por codigo se ja existe cadastrado na tabela funcionario, 
	 * @param codigoEmp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean integridadeFunc(int empresa) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM funcionario WHERE codigoEmp = '"+empresa+"'"); 			

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
	 * Metodo de pesquisa de registro por codigo se ja existe cadastrado na tabela Lancamento, 
	 * @param empresa
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean integridadeLanc(int empresa) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * "
				+ " FROM lancamento WHERE codigoEmp = '"+empresa+"'"); 			

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
	public String lisEmpresa(){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"listagem_empresa.jrxml");

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("SELECT * FROM empresa");
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
