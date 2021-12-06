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
			//7 relatorios (18619,18649,19207,19286,19356,19840,19995)
			//8 relatorios (18619,18649,19207,19286,19356,19840,19995,20135)
			//9 relatorios (18619,18649,19207,19286,19356,19840,19995,20135,20658)
			//10 relatorios (18619,18649,19207,19286,19356,19840,19995,20135,20658,21941)
			//20 relatorios (18619, 18649, 19207, 19286, 19356, 19840, 19995, 20135, 20658, 21713,21715,21718,21848,21935,21940,21941,21943,21972,22193)
			String sql = "select id, id_relatorio, esforco from esforco e where status = 1 and id_relatorio in (18619,18649,19207,19286,19356,19840,19995) order by id_relatorio";
//			String sql = "select id, id_relatorio, esforco from esforco e where status = 1 and id_relatorio in (18619,18649,19207,19286,19356,19840,19995,20135) order by id_relatorio";
//			String sql = "select id, id_relatorio, esforco from esforco e where status = 1 and id_relatorio in (18619,18649,19207,19286,19356,19840,19995,20135,20658) order by id_relatorio";
//			String sql = "select id, id_relatorio, esforco from esforco e where status = 1 and id_relatorio in (18619,18649,19207,19286,19356,19840,19995,20135,20658,21941) order by id_relatorio";
			

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    ResultSet rs = stmt.executeQuery();

		    while(rs.next()){
		    	int id = rs.getInt("id");
		    	int idRelatorio = rs.getInt("id_relatorio");
		        double esforco = rs.getDouble("esforco");
		        if (esforco == 0) {
		        	esforco = 1;
		        }

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
