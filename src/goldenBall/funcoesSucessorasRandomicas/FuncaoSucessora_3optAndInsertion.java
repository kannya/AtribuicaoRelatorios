package goldenBall.funcoesSucessorasRandomicas;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.Desenvolvedor;

public class FuncaoSucessora_3optAndInsertion extends FuncaoSucessoraRandomica{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> estado, double custo, double confianca) {
		if(new Double(Math.random() * 1).doubleValue() <= 0.5){
			return new FuncaoSucessora_RandomInsertion().criarSucessor(estado, custo,confianca);
		}else{
			return new FuncaoSucessora_Opt3().criarSucessor(estado, custo,confianca);
		}
	}

	@Override
	public int getNeighboor(int instanciaTam) {
		return (instanciaTam * (instanciaTam - 2))/2;
	}	
}
