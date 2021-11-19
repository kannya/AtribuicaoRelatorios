package genetico;

public class Opt2{

	@SuppressWarnings("unchecked")
	public int[] criarSucessor(int genes[]) { //22
		int indice1 = 0;
		int indice2 = 0;
		int indice3 = 0;
		int indice4 = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		int aux = 0;
		int genesInicial[] = genes.clone();
		int genesFinal[] = new int[genes.length];	
		
		//GERAMOS OS DOIS NÚMEROS ALEATÓRIOS
		i = new Double(Math.random() * (genesInicial.length)).intValue();
		
		j = new Double(Math.random() * (genesInicial.length)).intValue();
		
		while(j == i || j == i-1 || j == i+1){
			j = new Double(Math.random() * (genesInicial.length)).intValue();
		}
		
		if(i == 0 && j == genes.length - 1){
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
		if(i == genesInicial.length-1){
			indice2 = 0;
		}else{
			indice2 = i + 1;
		}
		
		//OBTENEMOS OSEGUNDO DESENVOLVEDOR		
		indice3 = j;		
		if(j == genesInicial.length-1){
			indice4 = 0;
		}else{
			indice4 = j + 1;
		}

		//A MUDANÇA É FEITA
		i = 0;
		while(i <= indice1){
			genesFinal[k] = genesInicial[i];
			i++;
			k++;
		}

		i = indice3;
		while(i > indice1){
			genesFinal[k] = genesInicial[i];
			i--;
			k++;
		}
		
		i = indice3 + 1;
		while(i < genesInicial.length){
			genesFinal[k] = genesInicial[i];
			i++;
			k++;
		}
		
		return genesFinal;
				
	}

}
