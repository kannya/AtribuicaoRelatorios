package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Opt2{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		ArrayList<Desenvolvedor> genes = individuo.genes;
		
		int indice1 = 0;
		int indice2 = 0;
		int indice3 = 0;
		int indice4 = 0;
		int i = 0;
		int j = 0;
		int aux = 0;
		ArrayList<Desenvolvedor> genesInicial = (ArrayList<Desenvolvedor>) genes.clone();
		ArrayList<Desenvolvedor> genesFinal = new ArrayList<Desenvolvedor>();

		//GERAMOS OS DOIS NÚMEROS ALEATÓRIOS
		i = new Double(Math.random() * (genesInicial.size())).intValue();
		
		j = new Double(Math.random() * (genesInicial.size())).intValue();
		
		while(j == i || j == i-1 || j == i+1){
			j = new Double(Math.random() * (genesInicial.size())).intValue();
		}
		
		if(i == 0 && j == genes.size() - 1){
			j = j-1;
		}
		//ORDENAMOS OS INDICES
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}
		
		//OBTEMOS O PRIMEIRO DESENVOLVEDOR
		indice1 = i;
		indice2 = i + 1;
		if(i == genesInicial.size() - 1){
			indice2 = 0;
		}else{
			indice2 = i + 1;
		}
		
		//OBTENEMOS OSEGUNDO DESENVOLVEDOR		
		indice3 = j;		
		if(j == genesInicial.size() - 1){
			indice4 = 0;
		}else{
			indice4 = j + 1;
		}

		//A MUDANÇA É FEITA
		i = 0;
		while(i <= indice1){
			genesFinal.add(genesInicial.get(i));
			i++;
		}

		i = indice3;
		while(i > indice1){
			genesFinal.add(genesInicial.get(i));
			i--;
		}
		
		i = indice3 + 1;
		while(i < genesInicial.size()){
			genesFinal.add(genesInicial.get(i));
			i++;
		}
		
		return genesFinal;
				
	}

}
