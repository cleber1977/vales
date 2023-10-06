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
 *	Data 30/10/2009
 */
public class UsuarioDAO {

	/**
	 * Metodo de Listagem de registro
	 * @param codigoUsuario
	 * @param nomeUsuario
	 * @param Ordem
	 */
	public ArrayList<UsuarioVO> geral(String  codigoUsuario, String nomeUsuario, String Ordem) {
		ArrayList<UsuarioVO> lista = null;
		UsuarioVO vo = null;
		try {
			Connection conectar = Database.getConnection();
			StringBuffer sql = new StringBuffer(
					"SELECT codigoUsuario, nomeUsuario, senhaUsuario, nivelUsuario"
					+ " FROM usuario");

			boolean pri = true;
			if ((codigoUsuario!= null) && (codigoUsuario.trim().length() != 0)) {
				sql.append(" where codigoUsuario = '"+codigoUsuario+"' ");
				pri = false;
			}

			if ((nomeUsuario != null) && (nomeUsuario.trim().length() != 0)) {
				if(pri){
					sql.append(" where upper(nomeUsuario) like upper('"+nomeUsuario+"%"+"') ");
				}else{
					sql.append(" and upper(nomeUsuario) like upper('"+nomeUsuario+"%"+"') ");
				}
			}
			if(Ordem != null && Ordem.trim().length() != 0){
				sql.append(" Order By "+ Ordem);
			}			
		
			PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				lista = new ArrayList<UsuarioVO>();
				do {
					vo = new UsuarioVO();
					vo.setCodigoUsuario(rs.getString("codigoUsuario"));
					vo.setNomeUsuario(rs.getString("nomeUsuario"));
					vo.setSenhaUsuario(rs.getString("senhaUsuario"));
					vo.setNivelUsuario(rs.getString("nivelUsuario"));
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
	 * @param codigoUsuario
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UsuarioVO pesquisar(int codigoUsuario) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT codigoUsuario, nomeUsuario, senhaUsuario, nivelUsuario"
				+ " FROM usuario WHERE codigoUsuario = " + codigoUsuario);	

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		UsuarioVO vo = new UsuarioVO();
		if (rs.next()) {
			vo.setCodigoUsuario(rs.getString("codigoUsuario"));
			vo.setNomeUsuario(rs.getString("nomeUsuario"));
			vo.setCriptSenhaUsuario(rs.getString("senhaUsuario"));
			vo.setNivelUsuario(rs.getString("nivelUsuario"));
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return vo;		
	}
	/**
	 * Metodo Responsavel Conferir se existe nome cadastrado.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean existeUsuario(String nome) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM usuario WHERE nomeUsuario = '"+nome+"'"); 			

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
	 * Metodo Responsavel Conferir o nivel de acesso.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String nivelUsuario(String nomeUsuario) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM usuario WHERE nomeUsuario = '"+nomeUsuario+"'"); 			

		PreparedStatement pstmt = conectar.prepareStatement(sql.substring(0,sql.length()));
		ResultSet rs = pstmt.executeQuery();
		String nivel = ""; 
		if (rs.next()) {
			nivel = rs.getString("nivelUsuario");
		}
		pstmt.close();
		rs.close();
		conectar.close();
		return nivel;		
	}
	
	/**
	 * Metodo Responsavel Conferir se existe nome cadastrado.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean existeSenha(String senha, String usuario) throws ClassNotFoundException, SQLException {
		Connection conectar = Database.getConnection();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM usuario WHERE senhaUsuario = '"+senha+"' and nomeUsuario = '"+usuario+"'"); 			

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
		"SELECT max(codigoUsuario) as cod FROM usuario"); 			

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
	public int incluir(UsuarioVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;

		Connection conectar = Database.getConnection();
		ResultSet rs;				
		PreparedStatement pstmt = conectar.prepareStatement(
				"SELECT codigoUsuario "
				+ " FROM usuario WHERE codigoUsuario = ? ");
		pstmt.setInt(1, vo.getCodigoUsuario());
		rs = pstmt.executeQuery();
		if (!rs.next()) {
			PreparedStatement pstmtInsert = conectar.prepareStatement(
					"INSERT INTO usuario (codigoUsuario, nomeUsuario, senhaUsuario, nivelUsuario)"
					+ "VALUES(?,?,?,?)");
			System.out.println(vo.getCriptSenhaUsuario());
			pstmtInsert.setInt(1, vo.getCodigoUsuario());
			pstmtInsert.setString(2, vo.getNomeUsuario().toUpperCase());
			pstmtInsert.setString(3, vo.getCriptSenhaUsuario());
			pstmtInsert.setString(4, vo.getNivelUsuario().toUpperCase());

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
	public int alterar(UsuarioVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
				"Update usuario Set senhaUsuario = ?, " +
				"nivelUsuario = ? " +
		"WHERE codigoUsuario = ? ");


		//pstmtInsert.setString(1, vo.getNomeUsuario().toUpperCase());
		pstmtInsert.setString(1, vo.getCriptSenhaUsuario());
		pstmtInsert.setString(2, vo.getNivelUsuario().toUpperCase());
		pstmtInsert.setInt(3, vo.getCodigoUsuario());

		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}

	/**
	 * Metodo de Exclusão de registro
	 * @param codigoUsuario
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deletar(UsuarioVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(
		"DELETE FROM usuario WHERE codigoUsuario = ?");
		pstmtInsert.setInt(1, vo.getCodigoUsuario());
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}

	/**
	 * Metodo responsavel por selecionar os registor no banco e gerar a listagem para impressão
	 * 
	 */
	public String lisUsuario(){
		try{
			String retorno = "";

			File file = new File("");

			String pathJasper = file.getAbsolutePath() + System.getProperty("file.separator") + "Relatorio//"; 

			JasperReport  relatorio = JasperCompileManager.compileReport(pathJasper+"listagem_usuario.jrxml");
		

			Map<String, String> parametros = new HashMap<String, String>();					

			Connection con = Database.getConnection();

			StringBuffer sql = new StringBuffer("SELECT * FROM usuario");
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
