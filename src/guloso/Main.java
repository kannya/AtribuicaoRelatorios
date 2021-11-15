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
		ArrayList<Integer> idsDesenvolvedores;
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		ArrayList<Desenvolvedor> melhorSolucaoFinal = new ArrayList<Desenvolvedor>();
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
		

		Guloso guloso = new Guloso(dados, relatorios);
		ArrayList<Desenvolvedor> melhorSolucaoEpoca = new ArrayList<Desenvolvedor>();
		double qldeSolEpoca = 0.0;

		melhorSolucaoEpoca = guloso.getSolucao();

		qldeSolEpoca = guloso.FuncaoFitness(melhorSolucaoEpoca);

		melhorSolucaoFinal = melhorSolucaoEpoca;

		idsDesenvolvedores = new ArrayList<Integer>();
		
		for(Desenvolvedor sol : melhorSolucaoFinal){
			idsDesenvolvedores.add(sol.getIdDesenvolvedor());
		}

		System.out.println(idsDesenvolvedores + "; " + qldeSolEpoca);
	}
}