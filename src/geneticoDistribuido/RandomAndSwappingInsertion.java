package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.Desenvolvedor;

public class RandomAndSwappingInsertion{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		if(new Double(Math.random() * 1).doubleValue() <= 0.5){
			return new RandomInsertion().criarSucessor(individuo);
		}else{
			return new Swapping().criarSucessor(individuo);
		}
	}
	
}
