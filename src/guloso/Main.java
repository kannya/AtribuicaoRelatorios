package guloso;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Main {
	public static void main(String[] args) {
		int epocas = 0;
		ArrayList<Integer> idsDesenvolvedores;
		ArrayList<Relatorio> idRelatorio;
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		ArrayList<Desenvolvedor> melhorSolucaoFinal = new ArrayList<Desenvolvedor>();
		Dados dados;
		double qldeSolFinal = 0.0;
		
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
			Guloso guloso = new Guloso(dados, relatorios);
			ArrayList<Desenvolvedor> melhorSolucaoEpoca = new ArrayList<Desenvolvedor>();
			double qldeSolEpoca = 0.0;
			
			melhorSolucaoEpoca = guloso.getSolucao();
			
			qldeSolEpoca = guloso.FuncaoFitness(melhorSolucaoEpoca);
						
			System.out.println("A melhor solucao:" + epocas + ": " + melhorSolucaoEpoca);
			System.out.println("A melhor qualidade" + epocas + ": " + qldeSolEpoca);
			
			if(qldeSolFinal < qldeSolEpoca) {
				qldeSolFinal = qldeSolEpoca;
				melhorSolucaoFinal = melhorSolucaoEpoca;
			}
			
			epocas++;
			if(epocas == 500)break;
		}
		idsDesenvolvedores = new ArrayList<Integer>();
		
		for(Desenvolvedor sol : melhorSolucaoFinal){
			idsDesenvolvedores.add(sol.getIdDesenvolvedor());
		}
		
		System.out.println("A melhor solucao final: " + melhorSolucaoFinal);
		System.out.println("A melhor qualidade final: " + qldeSolFinal);
		System.out.println("Melhor ids: " + idsDesenvolvedores);
		
		for(int r = 0; r < melhorSolucaoFinal.size(); r++) {
			idRelatorio = (ArrayList<Relatorio>) melhorSolucaoFinal.get(r).getRelatorios();
			
			System.out.println("Relatório: " + idRelatorio.get(r).getIdRelatorio() + " - " 
					+ "Desenvolvedor: " + idsDesenvolvedores.get(r));
		}
	}
}