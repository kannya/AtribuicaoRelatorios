package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class MainGA {
	
	public static void main(String[] args) {
		int execucoes = 0;
		int qtdeSubPopulacao = 6;
		int qtdeIndividuos = 48;
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		ArrayList<Double> listaAptidaoResultados = new ArrayList<Double>();
		ArrayList<Double> listaAptidaoIteracoes;
		double tempoTotal = 0;
		double tempo = 0;
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
			int tamSubPopulacao = qtdeIndividuos/qtdeSubPopulacao;
			Individuo e;
			int geracao = 0;
			double melhorQlde = Double.MIN_VALUE;
			
			listaAptidaoIteracoes = new ArrayList<Double>();
			
			DGA dga = new DGA(dados, relatorios, qtdeSubPopulacao, tamSubPopulacao);
			
			//cria as primeiras subpopulaçções aleatérias
			Subpopulacao[] populacao = new Subpopulacao[1];
						
			populacao = dga.populacao;
			
			//enquanto o critério de para não for atingido
			while(true){
							
				tempo = System.currentTimeMillis();
				
				Subpopulacao novaPopulacao = new Subpopulacao(qtdeIndividuos);
					
				//cria nova populacao
				novaPopulacao = dga.novaGeracao(populacao[0]);
					
				//passar colecao de individuos devolve uma nova subpopulacao
				novaPopulacao = dga.mutacao(novaPopulacao);

				//garante que o melhor individo(solucao) estara na proxima subpopulacao
				populacao[0] = dga.sobrevivente(populacao[0], novaPopulacao);

				if(melhorQlde < populacao[0].individuos[0].aptidao) {
						melhorQlde = populacao[0].individuos[0].aptidao;
				}
				
				listaAptidaoIteracoes.add(melhorQlde);
				
				tempo = System.currentTimeMillis() - tempo;
    			tempoTotal += tempo/1000;
				
				geracao++;
				if(geracao == 100)break;
				
			}
			
			System.out.println("\nValores Iteracoes - " + execucoes);
			for (int j = 0; j < listaAptidaoIteracoes.size(); j++) {
				System.out.println(listaAptidaoIteracoes.get(j));
			}
			
			System.out.println("\nMelhor Jogador: " + populacao[0].individuos[0].genes);
			
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
