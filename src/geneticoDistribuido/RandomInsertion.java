package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class RandomInsertion{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		ArrayList<Desenvolvedor> genes = individuo.genes;
		
		//INICIALIZAMOS AS VARIAVEIS
		int indice1;
		int indice2;
		Desenvolvedor gene;
		ArrayList<Desenvolvedor> genesInicial;
		
		//INICIALIZAMOS O ESTADO
		genesInicial = (ArrayList<Desenvolvedor>) genes.clone();
	
		//SELECIONAMOS UM NODO AO ACASO E O RECUPERAMOS. Calculamos a perda de remoção do nó do caminho
		indice1 = new Double(Math.random() * (genesInicial.size())).intValue();
		gene = genesInicial.remove(indice1);
		
		//GENERAMOS OUTRO NUMERO AO ACASO E INSERIMOS NO NODO NO INDICE
		indice2 = new Double(Math.random() * (genesInicial.size())).intValue();
		if(genesInicial.size() > 1) {
			while(indice2 == indice1)
				indice2 = new Double(Math.random() * (genesInicial.size())).intValue(); 
		}
		genesInicial.add(indice2, gene);		
		
		return genesInicial;
	}
}
