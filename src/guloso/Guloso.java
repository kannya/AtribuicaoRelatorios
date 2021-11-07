package guloso;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.classes.FuncaoObjetivo;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Guloso {

	ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	ArrayList<Desenvolvedor> solucao = new ArrayList<Desenvolvedor>();
	Dados dados;
		
	public Guloso() {
		
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
			
		}
		
		dados = new Dados(desenvolvedores, relatorios);
		
		//solucaoMelhorAfinidade();
		
		solucaoMenorCargaTrabalho();
	
	}
	
	public void solucaoMelhorAfinidade(){
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double melhor_afinidade = Double.MIN_VALUE;
			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				for (Relatorio rel : d.getRelatorios()) {
				//	System.out.println("Desenvolvedor: " + d.getIdDesenvolvedor() + " - " + rel.getAfinidade());
					if (r.getIdRelatorio() == rel.getIdRelatorio() && rel.getAfinidade() > melhor_afinidade) {
						sol = d;
						melhor_afinidade = rel.getAfinidade();
						break;
					}
				}

			}
			solucao.add(sol);
	//		System.out.println("Melhor Solução (A): " + sol);
	//		System.out.println("Melhor Afinidade: " + melhor_afinidade);
		}
	}
	
	public void solucaoMenorCargaTrabalho(){
		
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double menorCargaTrabalho = Double.MAX_VALUE;

			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				if (d.getCargaTrabalho() < menorCargaTrabalho) {

					System.out.println("Desenvolvedor: " + d.getIdDesenvolvedor() + " - " + d.getCargaTrabalho());

					for (Relatorio rel : d.getRelatorios()) {
						if (r.getIdRelatorio() ==  rel.getIdRelatorio()) {
							menorCargaTrabalho = d.getCargaTrabalho();
							d.setCargaTrabalho(menorCargaTrabalho + rel.getEsforco());
							sol = d;
							break;
						}
					}
				}

			}
			solucao.add(sol);
			System.out.println("Melhor Solução (CT): " + sol);
		}
	}
	
	//retorna o indice do mais apto
	public double FuncaoFitness(ArrayList<Desenvolvedor> desenvolvedores) {
		double qlde = Double.MIN_VALUE;

		FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo();
	
		qlde = funcaoObjetivo.avaliacao(desenvolvedores);

		return qlde;
	}

	public ArrayList<Desenvolvedor> getSolucao() {
		return solucao;
	}

	public void setSolucao(ArrayList<Desenvolvedor> solucao) {
		this.solucao = solucao;
	}

}
