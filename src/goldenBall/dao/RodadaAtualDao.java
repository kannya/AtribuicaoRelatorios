package goldenBall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RodadaAtualDao {

	public int buscaRodadaAtual(){
		Connection conn = ConnectionJDBC.getConexao();
		int resultado = 0;
		try{
			String sql = "select max(rodada) as rodadaAtual from metricas_afinidade";

		    PreparedStatement stmt = conn.prepareStatement(sql);
	    
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next()){
		    	resultado = rs.getInt("rodadaAtual");
		    	break;
		    }
		    
		    
//		    conn.close();
		} catch (SQLException e) {
			System.out.println("Listagem de relatorios FALHOU!");
		    e.printStackTrace();
		}

		return resultado;
	}
	
}
