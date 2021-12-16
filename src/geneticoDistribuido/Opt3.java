package geneticoDistribuido;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Opt3{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(Individuo individuo) {
		ArrayList<Desenvolvedor> genes = individuo.genes;
		
		int indice1 = 0;
		int indice2 = 0;
		int indice3 = 0;
		int indice4 = 0;
		int indice5 = 0;
		int indice6 = 0;
		int indice7 = 0;
		int indice8 = 0;
		int indice9 = 0;
		int indice10 = 0;
		int indice11 = 0;
		int indice12 = 0;
		int i;
		int k;
		int j;
		int aux;
		ArrayList<Desenvolvedor> genesInicial = (ArrayList<Desenvolvedor>) genes.clone();
		ArrayList<Desenvolvedor> genesFinal = new ArrayList<Desenvolvedor>();
		
		//OBTEMOS O PRIMEIRO E SEGUNDO INDICE
		i = new Double(Math.random() * (genesInicial.size())).intValue();		
		j = new Double(Math.random() * (genesInicial.size())).intValue();		

		while(j == i || j == i-1 || j == i+1){
			j = new Double(Math.random() * (genesInicial.size())).intValue();
		}

		//OS ORDENAMOS		
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}
		//OBTEMOS O TERCEIRO INDICE
		k = new Double(Math.random() * (genesInicial.size())).intValue();		
		while(k == i || k == i-1 || k == i+1 || k == j || k == j-1 || k == j+1){
			k = new Double(Math.random() * (genesInicial.size())).intValue();
		}
		
		//ORDENAMOS TODOS OS INDICES
		if (k < j){
			aux = j;
			j = k;
			k = aux;
		}		
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}		
		if(i == 0 && k == genes.size() - 1){
			k = k-1;
		}
		
		//OBTEMOS A PRIMERA ARESTA
		indice1 = i;
		if(i == genesInicial.size() - 1){
			indice2 = 0;
		}else{
			indice2 = i + 1;
		}
		
		//OBTEMOS A SEGUNDA ARESTA
		indice3 = j;
		if(j == genesInicial.size() - 1){
			indice4 = 0;
		}else{
			indice4 = j + 1;
		}
		
		//OBTEMOS A TERCEIRA ARESTA
		indice5 = k;
		if(k == genesInicial.size() - 1){
			indice6 = 0;
		}else{
			indice6 = k + 1;
		}
		
		//A TROCA DE ARCO É FEITA
		int l = new Double(Math.random() * 3).intValue();
		
		if(l == 0){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				genesFinal.add(genesInicial.get(i));
				i++;
			}
			
			i = indice3;
			while(i >= indice8){
				genesFinal.add(genesInicial.get(i));
				i--;
			}
			
			i = indice11;
			while(i >= indice10){
				genesFinal.add(genesInicial.get(i));
				i--;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i < genesInicial.size()){
					genesFinal.add(genesInicial.get(i));
					i++;
				}
			}
		
		}else if(l == 1){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				genesFinal.add(genesInicial.get(i));
				i++;
			}
			
			i = indice10;
			while(i <= indice11){
				genesFinal.add(genesInicial.get(i));
				i++;
			}
			
			i = indice9;
			while(i >= indice8){
				genesFinal.add(genesInicial.get(i));
				i--;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i < genesInicial.size()){
					genesFinal.add(genesInicial.get(i));
					i++;
				}
			}
			
		}else if(l == 2){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				genesFinal.add(genesInicial.get(i));
				i++;
			}
			
			i = indice11;
			while(i >= indice10){
				genesFinal.add(genesInicial.get(i));
				i--;
			}
			
			i = indice8;
			while(i <= indice9){
				genesFinal.add(genesInicial.get(i));
				i++;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i < genesInicial.size()){
					genesFinal.add(genesInicial.get(i));
					i++;
				}
			}
		}
		
		return genesFinal;
	}
	
	public int getNeighboor(int instanciaTam) {
		return ((instanciaTam - 3) + (Subpopulacao.somatorio(instanciaTam-3)));
	}
}
