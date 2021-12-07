package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.Desenvolvedor;

public class Opt3AndInsertion{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		if(new Double(Math.random() * 1).doubleValue() <= 0.5){
			return new RandomInsertion().criarSucessor(individuo);
		}else{
			return new Opt3().criarSucessor(individuo);
		}
	}
}
