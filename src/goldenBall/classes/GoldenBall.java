package goldenBall.classes;

import goldenBall.algoritmo.FuncaoAvaliacao;
import goldenBall.algoritmo.FuncaoHelp;
import goldenBall.algoritmo.InterfaceGoldenBall;
import goldenBall.algoritmo.InicializarPopulacao;

public class GoldenBall extends InterfaceGoldenBall {
	
	public GoldenBall(int tN, int pNFT, int sN, InicializarPopulacao pI, FuncaoAvaliacao eF, FuncaoHelp hFunc) {
			super(tN, pNFT, sN, pI, eF, hFunc);
	}

}