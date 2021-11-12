package genetico;

import java.util.ArrayList;

import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Main {
	
	public static void main(String[] args) {
		int repetir = 0;
		while(true){
			Individuo e;
			int epocas = 0;
			
			double melhorQlde = Double.MIN_VALUE;
			ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();
			ArrayList<Integer> idsDesenvolvedores;
			ArrayList<Relatorio> idRelatorio;
			ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
			ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
			Dados dados;
	
			DesenvolvedorDao dao = new DesenvolvedorDao();
			RelatorioDao relDao = new RelatorioDao();
			RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
			
			try {
				desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
				relatorios = relDao.listaRelatorios();		
			}finally {
				
			}
			
			dados = new Dados(desenvolvedores, relatorios);
			
			AG ag = new AG(dados, relatorios);
			Individuo[] populacao = ag.populacao;
			SelecaoTorneio torneio = new SelecaoTorneio(populacao, relatorios, dados);
		
		
		
			while(true){
				//selecao torneio
//				ag.proxima_geracao_torneio();
				
				//selecao roleta
				populacao = ag.proxima_geracao_roleta();
				e = torneio.get_mais_apto(populacao);
				
				if(melhorQlde < torneio.melhor_Qlde) {
					melhorQlde = torneio.melhor_Qlde;
					melhorSolucao = torneio.melhorSolucao;
				}
				
				epocas++;
				if(epocas == 500)break;
	//			System.out.println("melhor solução da Rodada " + epocas + ": " + torneio.melhorSolucao);
	//			System.out.println("melhor qualidade da Rodada " + epocas + ": " + torneio.melhor_Qlde);
			}
		
			repetir++;
			if(repetir == 100)break;
			
			System.out.println(melhorQlde);
		
		}
		
//		idsDesenvolvedores = new ArrayList<Integer>();
//		for(Desenvolvedor sol : melhorSolucao){
//			idsDesenvolvedores.add(sol.getIdDesenvolvedor());
//		}
		
//		System.out.println("A melhor solucao final: " + melhorSolucao);
//		System.out.println("A melhor qualidade final: " + melhorQlde);
//		System.out.println("Melhor ids: " + idsDesenvolvedores);
		
//		for(int r = 0; r < melhorSolucao.size(); r++) {
//			idRelatorio = (ArrayList<Relatorio>) melhorSolucao.get(r).getRelatorios();
//			
//			System.out.println("Relatório: " + idRelatorio.get(r).getIdRelatorio() + " - " 
//					+ "Desenvolvedor: " + idsDesenvolvedores.get(r));
//		}
	}
}
