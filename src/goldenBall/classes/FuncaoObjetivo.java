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
			somaAfinidades += rel.get(i).getAfinidade();
			i++;
		}
		qualidade = somaAfinidades / (1 + desvioPadrao(jogador));
		
		return qualidade;
	}
	
	public double desvioPadrao(Collection<Desenvolvedor> jogador) {
		double somaEsforco = 0.0;
		double somaCargaTrabalho = 0.0;
		double media = 0.0;
		
		for (Desenvolvedor des : gEst.getL_desenvolvedores()) {
			somaCargaTrabalho += des.getCargaTrabalho();
		}
		
		for (Relatorio rel : gEst.getL_relatorios()) {
			somaEsforco += rel.getEsforco();
		}
		
		media = (somaCargaTrabalho + somaEsforco) / gEst.getL_desenvolvedores().size();
		
		int i = 0;
		Collection<Desenvolvedor> cargaTabalhoJogador = new ArrayList<Desenvolvedor>();
		double somaQuadrado = 0d;
		for (Desenvolvedor j : jogador) {
			int k = 0;
			Desenvolvedor des = new Desenvolvedor();
			for (Relatorio r : j.getRelatorios()) {
				if(i == k) {
					if (cargaTabalhoJogador.contains(j)) {
						//verifica se o desenvolvedor já foi atribuido para outro relatorio
						for (Desenvolvedor d : cargaTabalhoJogador) {
							if (j.getIdDesenvolvedor() == d.getIdDesenvolvedor()) {
								//se sim soma as cargas de trabalho
								d.setCargaTrabalho(d.getCargaTrabalho() + r.getEsforco());
							}
						}
					}else {
						des.setIdDesenvolvedor(j.getIdDesenvolvedor());
						des.setCargaTrabalho(j.getCargaTrabalho() + r.getEsforco());
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