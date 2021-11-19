package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Swapping{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		ArrayList<Desenvolvedor> genes = individuo.genes;
		
		//INICIALIZAMOS OS 2 INDICES
		int indice1;
		int indice2;
		int aux;
		Desenvolvedor gene1;
		Desenvolvedor gene2;
		ArrayList<Desenvolvedor> genesInicial = new ArrayList<Desenvolvedor>();
		
		//OS DOIS ÍNDICES SÃO GERADOS PARA FAZER A TROCA E O NOVO ESTADO É REGENERADO
		genesInicial = (ArrayList<Desenvolvedor>) genes.clone();
		indice1 = 0;
		indice2 = 0;
		indice1 = new Double(Math.random() * (genesInicial.size())).intValue();
		indice2 = new Double(Math.random() * (genesInicial.size())).intValue();
		
		//Isso é feito para que os índices não sejam os mesmos
		if (genesInicial.size() > 1) {
			while(indice1 == indice2){
				indice2 = new Double(Math.random() * (genesInicial.size())).intValue();
			}
		}
		//isso ordena os índices de forma que o índice1 seja sempre menor que o índice2
		if (indice1 > indice2){
			aux = indice1;
			indice1 = indice2;
			indice2 = aux;
		}
				
		//AGORA A TROCA É REALIZADA
		if (genesInicial.size() > 1) {
			gene2 = genesInicial.remove(indice2);
			gene1 = genesInicial.remove(indice1);
			genesInicial.add(indice1, gene2);
			genesInicial.add(indice2, gene1);
		}
		
		return genesInicial;
	}	
}
