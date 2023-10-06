package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexao.Database;

public class LancAutomaticoDAO {
	/**
	 * Metodo de Inclusão de registro
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int incluir(LancAutomaticoVO vo) throws ClassNotFoundException, SQLException {
		
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
					"debCreHist, codigoEmp, dataEmissao, dataVenc, complLanc, dataPgto)"
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
			pstmtInsert.setString(10, vo.getComplLanc().toUpperCase());
			pstmtInsert.setString(11, vo.getDataPgtoFunSQL());
			registrosAfetados = pstmtInsert.executeUpdate();		 
				
			return registrosAfetados;
		}else{			
			return -1;				
		}
	}
}
