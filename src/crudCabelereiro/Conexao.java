package crudCabelereiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	static Connection conn;
	
	public static Connection conectar() throws SQLException{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String serverName = "localhost"; 
		String mydatabase = "cabelereiro";
		String porta = "3306";
		String username = "root";  
		String password = "baguvix1"; 
		String url = "jdbc:mysql://" + serverName +":" + porta + "/" + mydatabase+ "?useTimezone=true&serverTimezone=UTC&useSSL=false";
			
		conn = DriverManager.getConnection(url, username, password);	
		return conn;
	
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void desconectar() throws SQLException {
		conn.close();
	}
	
}
