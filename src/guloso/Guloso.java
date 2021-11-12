package guloso;

import java.util.ArrayList;
import java.util.Collection;

import genetico.Dados;
import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Guloso {

	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	ArrayList<Desenvolvedor> solucao = new ArrayList<Desenvolvedor>();
	Dados dados;
	private static ArrayList<Desenvolvedor> des = new ArrayList<Desenvolvedor>();
		
	public Guloso(Dados d, ArrayList<Relatorio> rel) {
		dados = d;
		relatorios = rel;
		des = dados.getDesenvolvedores();
		
//		solucaoMelhorAfinidade();
		
//		solucaoMenorCargaTrabalho();
		
		solucaoMedia();
	
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
	
	public void solucaoMedia(){
		for (Relatorio r : relatorios) {
			Desenvolvedor sol = new Desenvolvedor();
			double melhorMedia = Double.MIN_VALUE;
			double cargaTrabalhoLocal = 0.0;
			int j = 0;
			int i = 0;
			
			for (Desenvolvedor d : des) {
				double media = 0.0;
				double cargaTrabalhoTotal = 0.0;
				for (Relatorio relDesenv : d.getRelatorios()) {
					if (r.getIdRelatorio() == relDesenv.getIdRelatorio()){
						cargaTrabalhoTotal = d.getCargaTrabalho() + relDesenv.getEsforco();
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
			des.get(j).setCargaTrabalho(cargaTrabalhoLocal);
		}
	}
	
//	public void solucaoMedia(){
//		for (Relatorio r : relatorios) {
//			Desenvolvedor sol = new Desenvolvedor();
//			double melhorMedia = Double.MIN_VALUE;
//			double cargaTrabalhoLocal = 0.0;
//			int j = 0;
////			ArrayList<Desenvolvedor> des = dados.getDesenvolvedores();
//			
//			for (int i = 0; i < des.size(); i++) {
//				double media = 0.0;
//				for (Relatorio relDesenv : des.get(i).getRelatorios()) {
//					double cargaTrabalhoTotal = 0.0;
//					if (r.getIdRelatorio() == relDesenv.getIdRelatorio()){
//						cargaTrabalhoTotal = des.get(i).getCargaTrabalho() + relDesenv.getEsforco();
//						media = relDesenv.getAfinidade()/cargaTrabalhoTotal;
//						if(media > melhorMedia) {
//							sol = des.get(i);
//							melhorMedia = media;
//							cargaTrabalhoLocal = cargaTrabalhoTotal;
//							j = i;
//						}
//						break;
//					}
//				}
//			}
//			
//			solucao.add(sol);
//			
//			des.get(j).setCargaTrabalho(cargaTrabalhoLocal);
//		}
//	}
	
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
