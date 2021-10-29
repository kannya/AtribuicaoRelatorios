package genetico;

import java.util.ArrayList;
import java.util.Collection;

import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;

// genes ["A", "B", "F", "T"]
// com a letra inicial das cidades

// metodo importante
// get_distancia_percurso()
//   returna a distancia apara entre as cidade que estao no genes

public class Individuo {
	public int genes[];
//	ArrayList<Integer>  genes = new ArrayList<Integer>(); // idsDesenvolvedores
	ArrayList<Integer>  idsDesenvolvedores = new ArrayList<Integer>();
	
	Dados dadosGlobal;
	
	int len_genes;
	
	// len quantidade de cidade no genes
	// ideal para ser chamado na primeira geracao
	@SuppressWarnings("deprecation")
	public Individuo(int len, Dados dados) {
		dadosGlobal = dados;
		genes = new int[len];// as string de genes vao ter tamanho 1
		int tmp;
		for(int i=0; i<len; i++){
			
			tmp = new Double(Math.random() * dados.getDesenvolvedores().size()).intValue();
		
			genes[i] = dados.getDesenvolvedores().get(tmp).getIdDesenvolvedor();
		}
		
	
	}
	
	// ideal para ser chamada nas geracoes posteriores
	public Individuo(Individuo lst[], Dados dados) {
		Individuo pai = lst[0];
		Individuo mae = lst[1];
		assert pai.genes.length == mae.genes.length;
		len_genes = pai.len_genes;
		genes = crossover(pai, mae);
		mutacao(dados);
	}
	
	

	
//	public Double get_distancia_percurso(Dados dados){
//		int i, max = len_genes - 1;
//		Double count = 0.0;
//		String chave_nome;
//		for(i=0; i<max; i++){
//			chave_nome = genes[i] + genes[i+1];
//			count += dados.get_distancia(chave_nome);
//		}
//		return count;
//	}
	
	// True se exite duas letras iguais nos genes
	public boolean repete(){
		// { a, b, a, c , e ,f }
		
		int i,j;
		String tmp;
		for(i=0; i<len_genes; i++){
			tmp = genes[i];
			for(j=i+1; j<len_genes; j++){
				if(genes[j].equals(tmp))
					return true;
			}
		}
		return false;
	}
	
	// faz um troca da posicao entre dois genes (para evitar letras repetidas)
	public void mutacao(Dados dados){
		int local1 = Utils.rand(len_genes);
		int local2 = Utils.rand(len_genes);
		while(local2 == local1){
			local2 = Utils.rand(len_genes);
		}
		String tmp = genes[local1];
		genes[local1] = genes[local2];
		genes[local2] = tmp;
	}
	
	// retorna os genes do filho (faz o cruzamento entre os pais)
	// cruzamento mascara
	private String[] crossover(Individuo a, Individuo b){
		String lst[] = new String[a.len_genes];
		int i, max = lst.length;
		int rand_point = Utils.rand(len_genes);
		// U,B,A,T,N,F,M,S - pai 
		// M,A,S,F,B,U,N,T - mae
		
		// filho
		// X,X,|X,X,|X,X,|X,X
		//  p    m     p   m
		
		// dois de cima do pai
		// anda dois, dois de baixo da mae se ja tiver pega do pai
		int cout_pais = 2;
		String tmp;
		int j;
		for(i=0; i<max; i+=2){
			if(cout_pais%2 == 0){// pai
				tmp = a.genes[i];
				j = i;
				while(Utils.contain(lst, tmp)){// pega o da mae
					tmp = b.genes[j];
					if(j+1 < max){
						j++;
					} else {
						j = 0;
					}
					
				}

				lst[i] = tmp;
				tmp = a.genes[i+1];
				j = i+1;
				while(Utils.contain(lst, tmp)){
					tmp = b.genes[j];
					if(j+1 < max){
						j++;
					} else {
						j = 0;
					}
					
				}
				lst[i+1] = tmp;
				
			} else {// mae
				tmp = b.genes[i];
				j = i;
				while(Utils.contain(lst, tmp)){// pega o da pai
					tmp = a.genes[j];
					if(j+1 < max){
						j++;
					} else {
						j = 0;
					}
				}
				lst[i] = tmp;
				
				tmp = b.genes[i+1];
				j = i + 1;
				while(Utils.contain(lst, tmp)){
					tmp = a.genes[j];
					if(j+1 < max){
						j++;
					} else {
						j = 0;
					}
				}
				lst[i+1] = tmp;
			}
			cout_pais++;
			
		}
		return lst;
	}
	
	
}
