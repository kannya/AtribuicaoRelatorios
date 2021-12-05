package guloso;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class MainG {
	public static void main(String[] args) {
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
		Dados dados;
		double tempo = 0;
		double tempoTotal = 0;
		
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		
		tempo = System.currentTimeMillis();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
			
		}
		
		dados = new Dados(desenvolvedores, relatorios);
		
		Guloso guloso = new Guloso(dados, relatorios);
		ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();
		double qldeSol = 0.0;

		melhorSolucao = guloso.getSolucao();

		qldeSol = guloso.FuncaoFitness(melhorSolucao);

		tempo = System.currentTimeMillis() - tempo;
		tempoTotal += tempo/1000;
		
		System.out.println(qldeSol);
		System.out.println("\n" + tempoTotal);
	}
}