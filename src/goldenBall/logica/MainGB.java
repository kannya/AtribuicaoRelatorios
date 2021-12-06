package goldenBall.logica;

import java.util.ArrayList;

import goldenBall.algoritmo.Jogador;
import goldenBall.classes.Problema;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;

public class MainGB {
	
	private static int numExecucoes;
	private static int temp;
	private static int times;
	private static int jogadores;
	private static ArrayList<Integer> melhorSolucao;
	private static Jogador melhorJogador;
	
	public static void main(String[] args) {
		melhorJogador = new Jogador();
		numExecucoes = 100;
		temp = 10;
		times = 8;
		jogadores = 6;
		
		double tempo = 0;
		double tempoTotal = 0;
		int n = numExecucoes;
		ArrayList<Double> listaAptidaoResultados = new ArrayList<Double>();
		ArrayList<Double> listaAptidaoIteracoes;
		int execucoes = 0;
		
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		
		Jogador j = new Jogador();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
			
		}
					
    	Problema P = new Problema(desenvolvedores, relatorios);
    	
    	while(true){
    		double max = Double.MIN_VALUE;
    		int geracao = 0;
    		listaAptidaoIteracoes = new ArrayList<Double>();
    		

    		//enquanto o critério de para não for atingido
			while(true){

    			tempo = System.currentTimeMillis();

    			j = P.executarInstancia(temp, times, jogadores);

    			tempo = System.currentTimeMillis() - tempo;
    			tempoTotal += tempo/1000;

    			if(j.getQualidade() > max){
    				max = j.getQualidade();
    				melhorJogador = j;
    			}
    			
    			listaAptidaoIteracoes.add(max);
    			
    			geracao++;
				if(geracao == n)break;

    		}

    		System.out.println("\nValores Iteracoes - " + execucoes);
    		for (int j1 = 0; j1 < listaAptidaoIteracoes.size(); j1++) {
    			System.out.println(listaAptidaoIteracoes.get(j1));
    		}
    		
    		System.out.println("\nMelhor Jogador: " + melhorJogador.getGenes());
    		
    		listaAptidaoResultados.add(max);

    		execucoes++;
    		if(execucoes == 20)break;//quantidade de execuções do código(cada pessoa rodou uma vez)

    	}

    	System.out.println("\nValores Resultados");
    	for (int k = 0; k < listaAptidaoResultados.size(); k++) {
    		System.out.println(listaAptidaoResultados.get(k));
    	}

    	System.out.println("\nTempo Resultados");
    	System.out.println(tempoTotal/execucoes);
    	

	}	
	
}
