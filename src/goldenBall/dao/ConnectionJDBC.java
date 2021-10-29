package goldenBall.dao;

import java.sql.*;

public class ConnectionJDBC {

	static String status=""; 	
	public static Connection getConexao() {
		Connection conn = null; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/bitnami_redmine?user=kn&password=123456";
			conn = DriverManager.getConnection(url);
			
			status = "Conexao Abeta";
		} catch (SQLException e) {
			status = e.getMessage();
		} catch (ClassNotFoundException e) {
			status = e.getMessage();
		}catch (Exception e) {
			status = e.getMessage();
		}
		
		return conn;
	}
	
}
