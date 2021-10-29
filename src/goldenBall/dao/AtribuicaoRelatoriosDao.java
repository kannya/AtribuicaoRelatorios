package goldenBall.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AtribuicaoRelatoriosDao {

	public void atribuirDesenvolvedor(int idRelatorio, int idDesenvolvedor){
		Connection conn = ConnectionJDBC.getConexao();
		
		try{
			String sql = "update issues set assigned_to_id = ? where id = ?";

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    stmt.setInt(1, idDesenvolvedor);
		    stmt.setInt(2, idRelatorio);
		    
		    stmt.executeUpdate();
		    
//		    conn.close();

		} catch (SQLException e) {
			System.out.println("Atribuição de desenvolvedores FALHOU!");
		    e.printStackTrace();
		}

	}
	
	public void mudarStatusAtribuido(int idRelatorio){
		Connection conn = ConnectionJDBC.getConexao();
		
		try{
			String sql = " update dados_bug set status = 8 where id_tarefa in (?)";

		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setInt(1, idRelatorio);
		    
		    stmt.executeUpdate();
		   
//		   conn.close();
		   
		} catch (SQLException e) {
			System.out.println("Mudança de status FALHOU!");
		    e.printStackTrace();
		}

	}
	
	public void mudarStatusIssues(int idRelatorio){
		Connection conn = ConnectionJDBC.getConexao();
		
		try{
			String sql = " update issues set status_id = 8 where id in (?)";

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    stmt.setInt(1, idRelatorio);
		    
		    stmt.executeUpdate();
		   
//		   conn.close();
		   
		} catch (SQLException e) {
			System.out.println("Mudança de status FALHOU!");
		    e.printStackTrace();
		}

	}
	
}
