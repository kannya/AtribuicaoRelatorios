package forcaBruta.logica;


import java.util.ArrayList;

import forcaBruta.algoritmo.Solucao;
import goldenBall.dao.AtribuicaoRelatoriosDao;
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
		
		System.out.println("A melhor solucao: " + this.melhorSolucao);
		System.out.println("Melhor ids: " + idsDesenvolvedores);
		
		//buscar relatorios
		AtribuicaoRelatoriosDao atribuicaoDao = new AtribuicaoRelatoriosDao();
		ArrayList<Relatorio> idRelatorio = new ArrayList<Relatorio>();
		
		try {
			for(int r = 0; r < relatorios.size(); r++) {
				idRelatorio = (ArrayList<Relatorio>) this.melhorSolucao.get(r).getRelatorios();
				
				System.out.println("Relatório: " + idRelatorio.get(r).getIdRelatorio() + " - " 
						+ "Desenvolvedor: " + this.idsDesenvolvedores.get(r));
				
				atribuicaoDao.atribuirDesenvolvedor(idRelatorio.get(r).getIdRelatorio(), this.idsDesenvolvedores.get(r));			
				atribuicaoDao.mudarStatusAtribuido(idRelatorio.get(r).getIdRelatorio());
				atribuicaoDao.mudarStatusIssues(idRelatorio.get(r).getIdRelatorio());
			}
			
		}finally {
			
		}		
		
    	System.out.println("\n\n\nFim da Execução");
	}	
	
}