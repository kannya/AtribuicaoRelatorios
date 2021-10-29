package genetico;

import java.util.ArrayList;

public class Individuo {
	public int genes[];
	//ArrayList<Integer>  idsDesenvolvedores = new ArrayList<Integer>();
	
	Dados dadosGlobal;
	
	int len_genes;
	
	// len quantidade de relatorios no genes
	// ideal para ser chamado na primeira geracao
	@SuppressWarnings("deprecation")
	public Individuo(int qtde_rel, Dados dados) {
		dadosGlobal = dados;
		genes = new int[qtde_rel];// as string de genes vao ter tamanho 1
		int indice;
		for(int i = 0; i < qtde_rel; i++){
			
			indice = new Double(Math.random() * dados.getDesenvolvedores().size()).intValue();
		
			genes[i] = dados.getDesenvolvedores().get(indice).getIdDesenvolvedor();
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
	
	// faz um troca da posicao entre dois genes
	public void mutacao(Dados dados){
		int local1 = new Double(Math.random() * len_genes).intValue();
		int local2 = new Double(Math.random() * len_genes).intValue();

		int tmp = genes[local1];
		genes[local1] = genes[local2];
		genes[local2] = tmp;
	}
	
	// retorna os genes do filho (faz o cruzamento entre os pais)
	// cruzamento mascara
	private int[] crossover(Individuo a, Individuo b){
		int lst[] = new int[a.genes.length];
		int max = lst.length;
		int rand_point = new Double(Math.random() * a.genes.length).intValue();
		// U,B,A,T,N,F,M,S - pai 
		// M,A,S,F,B,U,N,T - mae
		
		// filho
		// X,X,|X,X,|X,X,|X,X
		//  p    m     p   m
		
		// dois de cima do pai
		// anda dois, dois de baixo da mae se ja tiver pega do pai
		int cout_pais = 2;
		int tmp;
		int j;
		
		pega de 2 em 2 e o valor é impar. tem que tratar
		
		for(int i = 0; i < max + 1; i += 2){
			if(cout_pais%2 == 0){// pai
				tmp = a.genes[i];
				j = i;
				
				lst[i] = tmp;
				tmp = a.genes[i+1];
				j = i+1;
				
				lst[i+1] = tmp;
				
			} else {// mae
				tmp = b.genes[i];
				j = i;
				
				lst[i] = tmp;
				
				tmp = b.genes[i+1];
				j = i + 1;
				
				lst[i+1] = tmp;
			}
			cout_pais++;
		}
		return lst;
	}
	
}
