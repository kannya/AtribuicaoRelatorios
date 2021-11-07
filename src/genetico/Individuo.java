package genetico;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoHelp;
import goldenBall.algoritmo.Jogador;
import goldenBall.funcoesSucessorasRandomicas.FuncaoSucessora_Opt2;
import goldenBall.help.HelpFuncao;
import goldenBall.logica.Desenvolvedor;

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
		mutacao();
	}
	
	// faz um troca da posicao entre dois genes
	public void mutacao(){
		int local1 = new Double(Math.random() * genes.length).intValue();
		int local2 = new Double(Math.random() * genes.length).intValue();

		int tmp = genes[local1];
		genes[local1] = genes[local2];
		genes[local2] = tmp;
	}
	
	// retorna os genes do filho (faz o cruzamento entre os pais)
	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private int[] crossover(Individuo a, Individuo b){

		assert a.genes.length == b.genes.length;
		int[] solucao = new int[b.genes.length];
		int[] segundaMetade = b.genes;

		for(int i = 0; i < b.genes.length/2; i++){
			solucao[i] = a.genes[i];
		}
		
		for (int j = b.genes.length/2; j < segundaMetade.length; j++) {
			solucao[j] = segundaMetade[j];
		}

		return solucao;
		
	}
}
