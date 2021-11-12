package goldenBall.help;

import goldenBall.algoritmo.FuncaoHelp;
import goldenBall.algoritmo.Jogador;
import goldenBall.logica.Desenvolvedor;

import java.util.ArrayList;


public class HelpFuncao extends FuncaoHelp {

	public ArrayList<Desenvolvedor> Help(Jogador capitao, Jogador jogadorHelp) {//31
		
//		System.out.println("Capitao " + capitao.getQualidade() + ": " + capitao.getGenes().toString());
//		System.out.println("Ajudou " + jogadorHelp.getQualidade() + ": " + jogadorHelp.getGenes().toString());
		
		ArrayList<Desenvolvedor> solucao = new ArrayList<Desenvolvedor>();
		ArrayList<Desenvolvedor> segundaMetade = (ArrayList<Desenvolvedor>) jogadorHelp.getGenes().clone();
		ArrayList<Desenvolvedor> segundaMetadeAposRemocao = new ArrayList<Desenvolvedor>();
		
//		System.out.println("Segunda metade antes de remover tudo: " + segundaMetade.toString());
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int k = 0;
		for(int i = 0; i < jogadorHelp.getGenes().size()/2; i++){
			solucao.add(capitao.getGenes().get(i));
			while (indices.contains(k)){
				k = new Double(Math.random() * segundaMetade.size()).intValue();
			}
			indices.add(k);
		}
		
//		System.out.println("Indices a serem removidos: " + indices);
		
//		System.out.println("Solucion intermediaria: " + solucao.toString());
		
		for (int j = 0; j < segundaMetade.size(); j++) {
			if (!indices.contains(j)) {
				segundaMetadeAposRemocao.add(segundaMetade.get(j));
			}
		}
				
//		System.out.println("Segunda metade depois de remover tudo: " + segundaMetadeAposRemocao.toString());
		
		solucao.addAll(segundaMetadeAposRemocao);
		
//		System.out.println("Solução Final: " + solucao.toString());
		
//		System.out.println();
//		System.out.println();
		
		return solucao;
	}
}
