package guloso;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Guloso {

	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	ArrayList<Desenvolvedor> solucao = new ArrayList<Desenvolvedor>();
	Dados dados;
		
	public Guloso(Dados d, ArrayList<Relatorio> rel) {
		dados = d;
		relatorios = rel;
		
//		solucaoMelhorAfinidade();
		
		solucaoMenorCargaTrabalho();
	
	}
	
	public void solucaoMelhorAfinidade(){
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double melhor_afinidade = Double.MIN_VALUE;
			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				for (Relatorio rel : d.getRelatorios()) {
					if (r.getIdRelatorio() == rel.getIdRelatorio() && rel.getAfinidade() > melhor_afinidade) {
						sol = d;
						melhor_afinidade = rel.getAfinidade();
						break;
					}
				}

			}
			solucao.add(sol);
		}
	}
	
	public void solucaoMenorCargaTrabalho(){
		
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double menorCargaTrabalho = Double.MAX_VALUE;

			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				if (d.getCargaTrabalho() < menorCargaTrabalho) {
					menorCargaTrabalho = d.getCargaTrabalho();
					sol = d;
				}
			}
			sol.setCargaTrabalho(menorCargaTrabalho + r.getEsforco());
			solucao.add(sol);
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
