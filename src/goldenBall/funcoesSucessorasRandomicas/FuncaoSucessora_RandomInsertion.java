package goldenBall.funcoesSucessorasRandomicas;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.Desenvolvedor;

public class FuncaoSucessora_RandomInsertion extends FuncaoSucessoraRandomica{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> estado, double custo, double confianca) { //22
		//INICIALIZAMOS AS VARIAVEIS
		int indice1;
		int indice2;
		Desenvolvedor est1;
		ArrayList<Desenvolvedor> estadoInicial;
		
		//INICIALIZAMOS O ESTADO
		estadoInicial = (ArrayList<Desenvolvedor>)estado.clone();
		
		//SELECIONAMOS UM NODO AO ACASO E O RECUPERAMOS. Calculamos a perda de remoção do nó do caminho
		indice1 = new Double(Math.random() * (estadoInicial.size())).intValue();
		est1 = estadoInicial.remove(indice1);
		
		//GENERAMOS OUTRO NUMERO AO ACASO E INSERIMOS NO NODO NO INDICE
		indice2 = new Double(Math.random() * (estadoInicial.size())).intValue();
		if(estadoInicial.size() > 1) {
			while(indice2 == indice1)
				indice2 = new Double(Math.random() * (estadoInicial.size())).intValue(); 
		}
		estadoInicial.add(indice2,est1);		
		
		return estadoInicial;
	}

	@Override
	public int getNeighboor(int instanciaTam) {
		return (instanciaTam * (instanciaTam - 2));
	}	
}
