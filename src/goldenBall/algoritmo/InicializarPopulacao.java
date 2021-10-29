package goldenBall.algoritmo;

import java.util.ArrayList;

public abstract class InicializarPopulacao{
	public abstract ArrayList<Time> inicializePopulacao(int numTimes, int numJogadorePorTime, FuncaoAvaliacao funcaoEvolucao);
}
