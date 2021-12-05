package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class MainDGA {
	
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
			ArrayList<Desenvolvedor> melhorJogador = new ArrayList<Desenvolvedor>();
			
			listaAptidaoIteracoes = new ArrayList<Double>();
			
			DGA dga = new DGA(dados, relatorios, qtdeSubPopulacao, tamSubPopulacao);
			
			//cria as primeiras subpopulaçções aleatérias
			Subpopulacao[] populacao = new Subpopulacao[qtdeSubPopulacao];
						
			populacao = dga.populacao;
			
			//enquanto o critério de para não for atingido
			while(true){
							
				tempo = System.currentTimeMillis();
				//para cada subpopulacao
				for (int i = 0; i < qtdeSubPopulacao; i++) {
					Subpopulacao novaPopulacao = new Subpopulacao(tamSubPopulacao);
					
					//cria nova populacao
					novaPopulacao = dga.novaGeracao(populacao[i]);
					
					//passar colecao de individuos devolve uma nova subpopulacao
					novaPopulacao = dga.mutacao(novaPopulacao);

					//garante que o melhor individo(solucao) estara na proxima subpopulacao
					populacao[i] = dga.sobrevivente(populacao[i], novaPopulacao);

					if(melhorQlde < populacao[i].individuos[0].aptidao) {
						melhorQlde = populacao[i].individuos[0].aptidao;
						melhorJogador = populacao[i].individuos[0].genes;
					}
					
				}
				
				listaAptidaoIteracoes.add(melhorQlde);
				
				dga.migracao(populacao);
				
				tempo = System.currentTimeMillis() - tempo;
    			tempoTotal += tempo/1000;
				
				geracao++;
				if(geracao == 100)break;
				
			}
			
			System.out.println("\nValores Iteracoes - " + execucoes);
			for (int j = 0; j < listaAptidaoIteracoes.size(); j++) {
				System.out.println(listaAptidaoIteracoes.get(j));
			}
			
			System.out.println("\nMelhor Jogador: " + melhorJogador);
			
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
