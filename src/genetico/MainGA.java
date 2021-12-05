package genetico;

import java.util.ArrayList;

import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class MainGA {
	
	public static void main(String[] args) {
		int execucoes = 0;
		double tempoTotal = 0;
		double tempo = 0;
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		ArrayList<Double> listaAptidaoIteracoes;
		ArrayList<Double> listaAptidaoResultados = new ArrayList<Double>();
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
			
		while(true){

			Individuo e;
			int geracao = 0;
			double melhorQlde = Double.MIN_VALUE;
			
			listaAptidaoIteracoes = new ArrayList<Double>();
			
			
			
			AG ag = new AG(dados, relatorios);
			
			Individuo[] populacao = ag.populacao;
			SelecaoTorneio torneio = new SelecaoTorneio(populacao, relatorios, dados);
		
			while(true){
				
				tempo = System.currentTimeMillis();
				//selecao torneio
//				ag.proxima_geracao_torneio();
				
				//selecao roleta
				populacao = ag.proxima_geracao_roleta();
				e = torneio.get_mais_apto(populacao);
				
				if(melhorQlde < torneio.melhor_Qlde) {
					melhorQlde = torneio.melhor_Qlde;
				}
				
				listaAptidaoIteracoes.add(melhorQlde);

				tempo = System.currentTimeMillis() - tempo;
				tempoTotal += tempo/1000;
				
				geracao++;
				if(geracao == 300)break;

			}
			
			System.out.println("\nValores Iteracoes - " + execucoes);
			for (int j = 0; j < listaAptidaoIteracoes.size(); j++) {
				System.out.println(listaAptidaoIteracoes.get(j));
			}
			
			listaAptidaoResultados.add(melhorQlde);
			
			execucoes++;
			if(execucoes == 20)break;
		
	}
		System.out.println("\nValores Resultados");
		for (int k = 0; k < listaAptidaoResultados.size(); k++) {
			System.out.println(listaAptidaoResultados.get(k));
		}
		
		System.out.println("\nTempo Resultados");
		System.out.println(tempoTotal);
	}
}
