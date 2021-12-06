package goldenBall.logica;


import java.util.ArrayList;

import goldenBall.algoritmo.Jogador;
import goldenBall.classes.Problema;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import interfacesUsuarioGB.Treinamento;

public class AtribuirRelatoriosGB extends Thread implements Runnable {
	
	private String numExecucoes;
	private String temp;
	private String times;
	private String jogadores;
	private static Thread t;
	private ArrayList<Integer> melhorSolucao;
	private Jogador melhorJogador;

	public static void main(String[] args) { //1
		Treinamento.obtTreinamentos().reiniciarValores();
    	//numero de iteracoes, qtde temporada, qtde times, qtde jogadores por time
//		versão 1	2times	24jogadores por time
//		versão 2	4times	12jogadores por time
//		versão 3	6times	8jogadores por time
//		versão 4	8times	6jogadores por time
		t = new AtribuirRelatoriosGB("100", "10", "8", "6");
    	t.start();
	}
	
	//(numero, temporada, times, jogadores) 
	public AtribuirRelatoriosGB(String n, String t, String tm, String p){ //2
		this.melhorJogador = new Jogador();
		this.numExecucoes = n;
		this.temp = t;
		this.times = tm;
		this.jogadores = p;
	}

	@Override
	public void run(){ //3
		double tempo = 0;
		double tempoTotal = 0;
		int n = Integer.parseInt(numExecucoes);
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

    		listaAptidaoIteracoes = new ArrayList<Double>();
    		

    		for(int i = 0; i < n; i++){

    			tempo = System.currentTimeMillis();

    			j = P.executarInstancia(Integer.parseInt(temp), Integer.parseInt(times), Integer.parseInt(jogadores));

    			tempo = System.currentTimeMillis() - tempo;
    			tempoTotal += tempo/1000;

    			if(j.getQualidade() > max){
    				max = j.getQualidade();
    				this.melhorJogador = j;
    			}
    			
    			listaAptidaoIteracoes.add(max);

    		}

//    		System.out.println("\nValores Iteracoes - " + execucoes);
//    		for (int j1 = 0; j1 < listaAptidaoIteracoes.size(); j1++) {
//    			System.out.println(listaAptidaoIteracoes.get(j1));
//    		}
    		
//    		System.out.println("\nMelhor Jogador: " + this.melhorJogador.getGenes());
    		
//    		System.out.println("\nTempo Iteracoes");
//    		System.out.println(tempoTotal);

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

	
/*    		this.melhorSolucao = new ArrayList<Integer>();
    		for(Desenvolvedor sol : this.melhorJogador.getGenes()){
    			melhorSolucao.add(sol.getIdDesenvolvedor());
    		}

    		System.out.println(this.melhorJogador.getGenes() + "; " + this.melhorJogador.getQualidade());
    		
    		System.out.println(this.melhorJogador.getQualidade());
    		
    		//buscar relatorios
    		AtribuicaoRelatoriosDao atribuicaoDao = new AtribuicaoRelatoriosDao();
    		ArrayList<Relatorio> idRelatorio = new ArrayList<Relatorio>();

    		try {
    			for(int r = 0; r < relatorios.size(); r++) {
    				idRelatorio = (ArrayList<Relatorio>) this.melhorJogador.getGenes().get(r).getRelatorios();

    				atribuicaoDao.atribuirDesenvolvedor(idRelatorio.get(r).getIdRelatorio(), this.melhorSolucao.get(r));			
    				atribuicaoDao.mudarStatusAtribuido(idRelatorio.get(r).getIdRelatorio());
    				atribuicaoDao.mudarStatusIssues(idRelatorio.get(r).getIdRelatorio());
    			}

    		}finally {

    		}
		
		execucoes++;
		if(execucoes == 100)break;
    	}
    		System.out.println("\n" + soma/n);
    	System.out.println("\n" + tempoTotal);
*/
	}	
	
//    public void addLocalObserver(Observer observer) {
//        observable.addObserver(observer);
//    }
//
//    public void deleteLocalObserver(Observer observer) {
//        observable.deleteObserver(observer);
//    }
//    
//    public void notifyLocalObservers(Object arg) { //36
//        observable.setChanged();
//        observable.notifyObservers(arg);
//    }
    
}
