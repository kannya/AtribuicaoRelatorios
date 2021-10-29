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
			String sql = "select distinct id_usuario, id_relatorio, afinidade, carga_trabalho "
					+ "from metricas_afinidade where rodada = " + rodadaAtual + " order by id_relatorio";

		    PreparedStatement stmt = conn.prepareStatement(sql);

		    ResultSet rs = stmt.executeQuery();

		    while(rs.next()){
		    	int idDesenvolvedor = rs.getInt("id_usuario");
		    	int idRelatorio = rs.getInt("id_relatorio");
		    	float afinidade = rs.getFloat("afinidade");
		        int cargaTrabalho = rs.getInt("carga_trabalho");

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
