package goldenBall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import goldenBall.logica.Relatorio;

public class RelatorioDao {

	public ArrayList<Relatorio> listaRelatorios(){
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		Connection conn = ConnectionJDBC.getConexao();
		
		try{
			String sql = "select id, id_relatorio, esforco from esforco e where status = 1 order by id_relatorio";

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    ResultSet rs = stmt.executeQuery();

		    while(rs.next()){
		    	int id = rs.getInt("id");
		    	int idRelatorio = rs.getInt("id_relatorio");
		        int esforco = rs.getInt("esforco");

		        relatorios.add(new Relatorio(id, idRelatorio, esforco));
		    }
		    
//		    conn.close();
		} catch (SQLException e) {
			System.out.println("Listagem de relatorios FALHOU!");
		    e.printStackTrace();
		}

		return relatorios;
	}
	
}
