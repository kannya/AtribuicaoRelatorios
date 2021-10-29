package goldenBall.classes;


import java.util.ArrayList;
import java.util.Collection;

import goldenBall.algoritmo.FuncaoAvaliacao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.GestorDistribuicaoRelatorios;
import goldenBall.logica.Relatorio;


public class FuncaoObjetivo extends FuncaoAvaliacao { 

	private GestorDistribuicaoRelatorios gEst = GestorDistribuicaoRelatorios.getInstancia();
	@SuppressWarnings("unchecked")
	public double avaliacao(Collection<Desenvolvedor> jogador) { //19 //23 //32
		double somaAfinidades = 0d;
		double qualidade = 0d;
		//A qualidade total é a soma das afinidades dividido por um somado ao desvio padrao da carga de trabalho total.
		int i = 0;
		for (Desenvolvedor j : jogador) {
			ArrayList<Relatorio> rel = new ArrayList<Relatorio>(j.getRelatorios());		
			rel.get(i);
			somaAfinidades += rel.get(i).getAfinidade();
			i++;
		}
		qualidade = somaAfinidades / (1 + desvioPadrao(jogador));
		
		return qualidade;
	}
	
	public double desvioPadrao(Collection<Desenvolvedor> jogador) {
		float somaEsforco = 0.0f;
		float somaCargaTrabalho = 0.0f;
		float media = 0.0f;
		
		for (Desenvolvedor des : gEst.getL_desenvolvedores()) {
			somaCargaTrabalho += des.getCargaTrabalho();
		}
		
		for (Relatorio rel : gEst.getL_relatorios()) {
			somaEsforco += rel.getEsforco();
		}
		
		media = (somaCargaTrabalho + somaEsforco) / gEst.getL_desenvolvedores().size();
		
//		System.out.printf("media: %.6f", media);
		
		int i = 0;
		Collection<Desenvolvedor> cargaTabalhoJogador = new ArrayList<Desenvolvedor>();
		double somaQuadrado = 0d;
		for (Desenvolvedor j : jogador) {
			int k = 0;
			Desenvolvedor des = new Desenvolvedor();
			for (Relatorio r : gEst.getL_relatorios()) {
				if(i == k) {
					des.setIdDesenvolvedor(j.getIdDesenvolvedor());
					des.setCargaTrabalho((int) (j.getCargaTrabalho() + r.getEsforco()));
					if (cargaTabalhoJogador.contains(j)) {
						for (Desenvolvedor d : cargaTabalhoJogador) {
							if (j.getIdDesenvolvedor() == d.getIdDesenvolvedor()) {
								d.setCargaTrabalho(j.getCargaTrabalho() + d.getCargaTrabalho());
							}
						}
					}else {
						cargaTabalhoJogador.add(des);
					}

				}
				k++;
			}
			
			i++;
		}
		for (Desenvolvedor ct : cargaTabalhoJogador) {
			somaQuadrado += Math.pow((ct.getCargaTrabalho() - media), 2);
		}
		
		return Math.sqrt(somaQuadrado/gEst.getL_desenvolvedores().size());
		
	}
}