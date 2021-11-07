package guloso;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Main {
	public static void main(String[] args) {
		Guloso guloso = new Guloso();
		ArrayList<Desenvolvedor> melhor_solucao = new ArrayList<Desenvolvedor>();
		double qlde_sol = 0.0;
		
		melhor_solucao = guloso.getSolucao();
		
		System.out.println("Solução Final: " + melhor_solucao);
		
		qlde_sol = guloso.FuncaoFitness(melhor_solucao);
		
		System.out.println("Qualidade da Solução: " + qlde_sol);
	}
}
