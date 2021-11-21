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
		double tempo = 0;
		double tempoTotal = 0;
		double soma = 0;
		double melhorQlde = Double.MIN_VALUE;
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
			
//		while(true){

			Individuo e;
			int epocas = 0;
			AG ag = new AG(dados, relatorios);
			
			tempo = System.currentTimeMillis();
			
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
				}
								
				epocas++;
				if(epocas == 10)break;
				
				soma += melhorQlde;
			}
			
			soma += melhorQlde;
			
			tempo = System.currentTimeMillis() - tempo;
			tempoTotal += tempo/1000;
						
			repetir++;
//			if(repetir == 20)break;

			System.out.println(melhorQlde);
		
//	}
		System.out.println("\n" + soma/repetir);
		System.out.println("\n" + tempoTotal);
	}
}
