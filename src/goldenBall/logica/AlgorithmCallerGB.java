package goldenBall.logica;


import java.util.ArrayList;
import java.util.Observer;

import goldenBall.algoritmo.Jogador;
import goldenBall.classes.Problema;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import interfacesUsuarioGB.Estatistica;
import interfacesUsuarioGB.Resultados;
import interfacesUsuarioGB.Treinamento;
import util.observer.local.LocalObservable;

public class AlgorithmCallerGB extends Thread implements Runnable, ILocalObservable {
	
	private LocalObservable observable;
	private String numExecucoes;
	private String temp;
	private String times;
	private String jogadores;
	private static Thread t;
	private ArrayList<Integer> melhorSolucao;
	private Jogador melhorJogador;

	public static void main(String[] args) { //1
		Treinamento.obtTreinamentos().reiniciarValores();
    	//numero de execucoes, qtde temporada, qtde times, qtde jogadores por time
		t = new AlgorithmCallerGB("60", "10", "4", "12");
    	t.start();
	}
	
	//(numero, temporada, times, jogadores) 
	public AlgorithmCallerGB(String n, String t, String tm, String p){ //2
		observable = new LocalObservable();
		this.melhorJogador = new Jogador();
		
		this.numExecucoes = n;
		this.addLocalObserver(Resultados.obtResultados());
		this.addLocalObserver(Estatistica.obtEstatisticas());
		this.temp = t;
		this.times = tm;
		this.jogadores = p;
	}

	@Override
	public void run(){ //3
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double total = 0;
		double tempo = 0;
		double tempoTotal = 0;
		int n = Integer.parseInt(numExecucoes);
		int epocas = 0;
		
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
			for(int i = 0; i < n; i++){
				
		    	tempo = System.currentTimeMillis();
		    	
		    	j = P.executarInstancia(Integer.parseInt(temp), Integer.parseInt(times), Integer.parseInt(jogadores));
		    	
		    	tempo = System.currentTimeMillis() - tempo;
		    	tempoTotal += tempo/1000;
		    	total = total + j.getQualidade();
		    	if(j.getQualidade() < min){
					min = j.getQualidade();
				}
				if(j.getQualidade() > max){
					max = j.getQualidade();
					this.melhorJogador = j;
				}
		    	this.notifyLocalObservers(j.getQualidade());
		    	ArrayList<Double> l = new ArrayList<Double>();
		    	l.add(Math.rint((total/(i+1))*100)/100);
		    	l.add(min);
		    	l.add(max);
		    	l.add(Math.rint((tempoTotal/(i+1))*100)/100);
		    	
		    	this.notifyLocalObservers(l);
		    
	    	}
			
			this.melhorSolucao = new ArrayList<Integer>();
			for(Desenvolvedor sol : this.melhorJogador.getGenes()){
				melhorSolucao.add(sol.getIdDesenvolvedor());
			}
			
			System.out.println(this.melhorJogador.getGenes() + "; " + this.melhorJogador.getQualidade());
			
			//buscar relatorios
//			AtribuicaoRelatoriosDao atribuicaoDao = new AtribuicaoRelatoriosDao();
//			ArrayList<Relatorio> idRelatorio = new ArrayList<Relatorio>();
//			
//			try {
//				for(int r = 0; r < relatorios.size(); r++) {
//					idRelatorio = (ArrayList<Relatorio>) this.melhorJogador.getGenes().get(r).getRelatorios();
//					
//					atribuicaoDao.atribuirDesenvolvedor(idRelatorio.get(r).getIdRelatorio(), this.melhorSolucao.get(r));			
//					atribuicaoDao.mudarStatusAtribuido(idRelatorio.get(r).getIdRelatorio());
//					atribuicaoDao.mudarStatusIssues(idRelatorio.get(r).getIdRelatorio());
//				}
//				
//			}finally {
//				
//			}		
	    	
	    	epocas++;
			if(epocas == 100)break;
    	}
    	System.out.println("\n\n\nFim da Execução");
	}	
	
    public void addLocalObserver(Observer observer) {
        observable.addObserver(observer);
    }

    public void deleteLocalObserver(Observer observer) {
        observable.deleteObserver(observer);
    }
    
    public void notifyLocalObservers(Object arg) { //36
        observable.setChanged();
        observable.notifyObservers(arg);
    }
    
}
