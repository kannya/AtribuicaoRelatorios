package goldenBall.algoritmo;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;


public abstract class FuncaoSucessoraRandomica {
	
	@SuppressWarnings("unchecked")
	public abstract ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> jogador, double qualidade, double confianca);
	
	public abstract int getNeighboor(int tamInstancia);
}
