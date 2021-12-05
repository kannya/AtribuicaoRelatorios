package goldenBall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class DesenvolvedorDao {

	public ArrayList<Desenvolvedor> listaDesenvolvedores(int rodadaAtual){
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		Connection conn = ConnectionJDBC.getConexao();
		
		try{
			//7 relatorios (18619,18649,19207,19286,19356,19840,19995)
			//8 relatorios (18619,18649,19207,19286,19356,19840,19995,20135)
			//9 relatorios (18619,18649,19207,19286,19356,19840,19995,20135,20658)
			//10 relatorios (18619,18649,19207,19286,19356,19840,19995,20135,20658, 21941)
			//20 relatorios (18619, 18649, 19207, 19286, 19356, 19840, 19995, 20135, 20658, 21713,21715,21718,21848,21935,21940,21941,21943,21972,22193)
			String sql = "select distinct id_usuario, id_relatorio, afinidade, carga_trabalho "
					+ "from metricas_afinidade where id_relatorio in (18619, 18649, 19207, 19286, 19356, 19840, 19995, 20135, 20658, 21941) and rodada = " + rodadaAtual + " order by id_relatorio";

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    ResultSet rs = stmt.executeQuery();

		    while(rs.next()){
		    	int idDesenvolvedor = rs.getInt("id_usuario");
		    	int idRelatorio = rs.getInt("id_relatorio");
		    	double afinidade = rs.getDouble("afinidade");
		        double cargaTrabalho = rs.getDouble("carga_trabalho");

		        desenvolvedores.add(new Desenvolvedor(idDesenvolvedor, idRelatorio, afinidade, cargaTrabalho));
		    }
		    
//		    conn.close();
		} catch (SQLException e) {
			System.out.println("Listagem de desenvolvedores FALHOU!");
		    e.printStackTrace();
		}

		return desenvolvedores;
	}
	
}
