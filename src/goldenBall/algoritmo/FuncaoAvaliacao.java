package goldenBall.algoritmo;

import java.util.Collection;

import goldenBall.logica.Desenvolvedor;

public abstract class FuncaoAvaliacao {
	
	@SuppressWarnings("unchecked")
	public abstract double avaliacao(Collection<Desenvolvedor> jogador);

}
