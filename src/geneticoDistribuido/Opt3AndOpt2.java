package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Opt3AndOpt2{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		if(new Double(Math.random() * 1).doubleValue() <= 0.5){
			return new Opt2().criarSucessor(individuo);
		}else{
			return new Opt3().criarSucessor(individuo);
		}
	}
	
	public int getNeighboor(int instanciaTam) {
		return ((instanciaTam - 3) + (Subpopulacao.somatorio(instanciaTam-3)));
	}	
}
