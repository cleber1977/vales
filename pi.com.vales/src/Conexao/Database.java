package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	private static Connection conectar = null;
	
	private static String banco;
	private static String servidor;
	private static String nivelUsu;


	public static Connection getConnection() {
        try {
            if (conectar == null || conectar.isClosed()) {
            	conectar = conexao();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conectar;
    }
	

    private static Connection conexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://"+servidor+":3306/"+banco, "root", "prolei9609");
        } catch (Exception ex) {
             return null;
        } 
    }

    public static void closeConnection(Connection conectar, PreparedStatement ps) {
        try {
            ps.close();
            conectar.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conectar, PreparedStatement ps, ResultSet rs) {
        try {
            rs.close();
            ps.close();
            conectar.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void atualizaConection(String serv, String banc){
    	banco = banc;
    	servidor = serv;
    }
    
    public static boolean testConection(){
    	if(conexao() == null){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    public static void setNivelUsu(String nivel){
    	nivelUsu = nivel;
    }
    
    public static String getNivelUsu(){
    	return nivelUsu;
    }
}

