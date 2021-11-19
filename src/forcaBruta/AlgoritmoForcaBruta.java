package forcaBruta;


import java.util.ArrayList;

import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class AlgoritmoForcaBruta extends Thread implements Runnable {
	
	private static Thread t;
	private ArrayList<Integer> idsDesenvolvedores;
	private ArrayList<Desenvolvedor> melhorSolucao;
	ArrayList<Desenvolvedor> sol;
	private Solucao funcaoSolucao;

	public static void main(String[] args) {
		t = new AlgoritmoForcaBruta("50000");
    	t.start();
	}

	public AlgoritmoForcaBruta(String n){
		this.melhorSolucao = new ArrayList<Desenvolvedor>();
		this.sol = new ArrayList<Desenvolvedor>();
		this.funcaoSolucao = new Solucao();
	}

	@Override
	public void run(){
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		double qlde = 0.0;
		double tempo = 0;
		double tempoTotal = 0;
		
		tempo = System.currentTimeMillis();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
			
		}
		
		this.melhorSolucao = funcaoSolucao.permuta(relatorios, desenvolvedores);
		
		this.idsDesenvolvedores = new ArrayList<Integer>();
		
		for(Desenvolvedor sol : this.melhorSolucao){
			idsDesenvolvedores.add(sol.getIdDesenvolvedor());
		}
		
		qlde = funcaoSolucao.getMaxQlde();
		
		tempo = System.currentTimeMillis() - tempo;
		tempoTotal += tempo/1000;
		
//		System.out.println(this.melhorSolucao + "; " + qlde);
		
		System.out.println("\n" + qlde);
		System.out.println(tempoTotal);
	}	
	
}
