package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexao.Database;

public class BaixaDAO {
	/**
	 * Metodo de Quitação dos registros
	 * @param vo
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int quitar(LancamentoVO vo) throws ClassNotFoundException, SQLException {

		int registrosAfetados = 0;
		Connection conectar = Database.getConnection();
		PreparedStatement pstmtInsert = conectar.prepareStatement(			
				"Update lancamento set dataPgto = ? "+
				"WHERE dataPgto = '0001-01-01' and (dataVenc between ? and ?) ");
		
		pstmtInsert.setString(1, vo.getDataPgtoFunSQL());
		pstmtInsert.setString(2, vo.getDatinicialFunSQL());
		pstmtInsert.setString(3, vo.getDatfinalFunSQL());
		
		registrosAfetados = pstmtInsert.executeUpdate();
		conectar.close();
		return registrosAfetados;
	}
}
