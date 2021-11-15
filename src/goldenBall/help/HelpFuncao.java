package goldenBall.help;

import goldenBall.algoritmo.FuncaoHelp;
import goldenBall.algoritmo.Jogador;
import goldenBall.logica.Desenvolvedor;

import java.util.ArrayList;


public class HelpFuncao extends FuncaoHelp {

	public ArrayList<Desenvolvedor> Help(Jogador capitao, Jogador jogadorHelp) {//31
		
		ArrayList<Desenvolvedor> solucao = new ArrayList<Desenvolvedor>();
		ArrayList<Desenvolvedor> segundaMetade = (ArrayList<Desenvolvedor>) jogadorHelp.getGenes().clone();
		ArrayList<Desenvolvedor> segundaMetadeAposRemocao = new ArrayList<Desenvolvedor>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int k = 0;
		for(int i = 0; i < jogadorHelp.getGenes().size()/2; i++){
			solucao.add(capitao.getGenes().get(i));
			while (indices.contains(k)){
				k = new Double(Math.random() * segundaMetade.size()).intValue();
			}
			indices.add(k);
		}
		
		for (int j = 0; j < segundaMetade.size(); j++) {
			if (!indices.contains(j)) {
				segundaMetadeAposRemocao.add(segundaMetade.get(j));
			}
		}
		
		solucao.addAll(segundaMetadeAposRemocao);
		
		return solucao;
	}
}
