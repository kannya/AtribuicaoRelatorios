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
		int qtdeSubPopulacao = 8;
		int qtdeIndividuos = 48;
		int numExecucoes = 100;
		
		ArrayList<Double> listaAptidaoResultados = new ArrayList<Double>();
		ArrayList<Double> listaAptidaoIteracoes;
		Individuo melhorIndividuo = new Individuo();
		double tempoTotal = 0;
		double tempo = 0;
		
		int tamSubPopulacao = qtdeIndividuos/qtdeSubPopulacao;
		
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		
		Individuo individuo = new Individuo();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
	
		}
		
		DGA dga = new DGA(desenvolvedores, relatorios);
		
		while(true){
			
			double max = Double.MIN_VALUE;
			int geracao = 0;
			listaAptidaoIteracoes = new ArrayList<Double>();
			
			//enquanto o critério de para não for atingido
			while(true){

				tempo = System.currentTimeMillis();
				
				individuo = dga.executarInstancia(qtdeSubPopulacao, tamSubPopulacao);
	
				tempo = System.currentTimeMillis() - tempo;
    			tempoTotal += tempo/1000;
    			
    			if(individuo.getAptidao() > max) {
    				max = individuo.getAptidao();
    				melhorIndividuo = individuo;
    			}
    			
    			listaAptidaoIteracoes.add(max);
    			
				geracao++;
				if(geracao == numExecucoes)break;
				
			}
			
			System.out.println("\nValores Iteracoes - " + execucoes);
			for (int j = 0; j < listaAptidaoIteracoes.size(); j++) {
				System.out.println(listaAptidaoIteracoes.get(j));
			}
			
			System.out.println("\nMelhor Individuo: " + melhorIndividuo.genes);
			
			listaAptidaoResultados.add(max);
			
			execucoes++;
			if(execucoes == 20)break;
		
		}
		
		System.out.println("\nValores Resultados");
		for (int k = 0; k < listaAptidaoResultados.size(); k++) {
			System.out.println(listaAptidaoResultados.get(k));
		}
		
		System.out.println("\nTempo Resultados");
		System.out.println(tempoTotal/execucoes);
	}

}
