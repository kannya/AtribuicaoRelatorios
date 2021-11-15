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
		
//		solucaoMenorCargaTrabalho();
		
		solucaoDivsaoAfinidadeCarga();
	
	}
	
	public void solucaoMelhorAfinidade(){
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double melhorAfinidade = Double.MIN_VALUE;
			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				for (Relatorio rel : d.getRelatorios()) {
					if (r.getIdRelatorio() == rel.getIdRelatorio() && rel.getAfinidade() > melhorAfinidade) {
						sol = d;
						melhorAfinidade = rel.getAfinidade();
						break;
					}
				}
			}
			solucao.add(sol);
		}
	}
	
	public void solucaoMenorCargaTrabalho(){
		
		for (Desenvolvedor d : dados.getDesenvolvedores()) {
			d.setCargaTrabalhoAposAtribuicao(d.getCargaTrabalho());
		}
		
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double menorCargaTrabalho = Double.MAX_VALUE;
			double cargaTrabalhoTotal = 0.0;
			int j = 0;
			int i = 0;
			
			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				cargaTrabalhoTotal = d.getCargaTrabalhoAposAtribuicao() + r.getEsforco();
				if (cargaTrabalhoTotal < menorCargaTrabalho) {
					menorCargaTrabalho = cargaTrabalhoTotal;
					sol = d;
					j = i;
				}
				i++;
			}
			
			solucao.add(sol);
			dados.getDesenvolvedores().get(j).setCargaTrabalhoAposAtribuicao(menorCargaTrabalho);
		}
	}
	
	public void solucaoDivsaoAfinidadeCarga(){
		for (Desenvolvedor d : dados.getDesenvolvedores()) {
			d.setCargaTrabalhoAposAtribuicao(d.getCargaTrabalho());
		}
		
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double melhorMedia = Double.MIN_VALUE;
			double cargaTrabalhoLocal = 0.0;
			int j = 0;
			int i = 0;
			
			for (Desenvolvedor d : dados.getDesenvolvedores()) {
				double media = 0.0;
				double cargaTrabalhoTotal = 0.0;
				for (Relatorio relDesenv : d.getRelatorios()) {
					if (r.getIdRelatorio() == relDesenv.getIdRelatorio()){
						cargaTrabalhoTotal = d.getCargaTrabalhoAposAtribuicao() + relDesenv.getEsforco();
						media = relDesenv.getAfinidade()/cargaTrabalhoTotal;
						if(media > melhorMedia) {
							sol = d;
							melhorMedia = media;
							cargaTrabalhoLocal = cargaTrabalhoTotal;
							j = i;
						}
						break;
					}
				}
				i++;

			}
			solucao.add(sol);
			dados.getDesenvolvedores().get(j).setCargaTrabalhoAposAtribuicao(cargaTrabalhoLocal);
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
