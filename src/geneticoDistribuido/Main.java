package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
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
		int qtdeSubPopulacao = 4;
		int qtdeIndividuos = 48;
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
			
		while(true){
			int tamSubPopulacao = qtdeIndividuos/qtdeSubPopulacao;
			Individuo e;
			int geracao = 0;
			
			DGA dga = new DGA(dados, relatorios, qtdeSubPopulacao, tamSubPopulacao);
			
			//cria as primeiras subpopulaçções aleatérias
			Subpopulacao[] populacao = new Subpopulacao[qtdeSubPopulacao];
			
			tempo = System.currentTimeMillis();
			populacao = dga.populacao;
			
			//enquanto o critério de para não for atingido
			while(true){
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
					}
				}
				
				dga.migracao(populacao);

				geracao++;
				if(geracao == 10)break;

			}
			
			
			tempo = System.currentTimeMillis() - tempo;
			tempoTotal += tempo/1000;

			repetir++;
			if(repetir == 20)break;

			System.out.println(melhorQlde);
		
		}
		System.out.println("\n" + soma/repetir);
		System.out.println("\n" + tempoTotal);
	}

}
