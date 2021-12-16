package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class SwappingInsertion{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		if(new Double(Math.random() * 1).doubleValue() <= 0.5){
			return new Insertion().criarSucessor(individuo);
		}else{
			return new Swapping().criarSucessor(individuo);
		}
	}
	
	public int getNeighboor(int instanciaTam) {
		return ((instanciaTam-6) * (instanciaTam - 6))/2;
	}
}
