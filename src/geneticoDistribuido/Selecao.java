package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import genetico.Individuo;
import genetico.Opt2;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

@Deprecated
public class Selecao {

	static ArrayList<Relatorio> relatorios;
	Individuo[] subPopulacao;
	static Dados dados;
	private double txSelecao = .03; // % da população que vai sobreviver à próxima geração (através da seleção)
	private double txMutacao = .100; // % da população corrente que vai sofrer mutacão
	public static double melhor_Qlde = Double.MIN_VALUE;
	public static ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();
	static int tamSubPopulacao;
	
	public Selecao(Individuo[] subPop, ArrayList<Relatorio> rel, Dados d, int tamSubPop) {
		relatorios = rel;
		subPopulacao = subPop;
		dados = d;
		tamSubPopulacao = tamSubPop;
	}

	//utilizada a tecnica do torneio
	public Individuo[] selecao() {
				
		Individuo to_return[] = new Individuo[2];
		Individuo lst[] = new Individuo[relatorios.size() + 1];
		
		for(int i = 0; i < relatorios.size() + 1; i++){
			lst[i] = subPopulacao[new Double(Math.random() * subPopulacao.length).intValue()];
		}
			
		// seleciona os dois mais aptos para retornar
		int i1;
		i1 = get_indice_mais_apto(lst);
		to_return[0] = subPopulacao[i1];
		lst[i1] = null;
			
		to_return[1] = get_mais_apto(lst);
		
		return to_return;
		
	}

//	public int[] cruzamento(Individuo a, Individuo b) {
//
//		ArrayList<Integer> solucao = new ArrayList<Integer>();
//		int[] segundaMetade = b.genes;
//		ArrayList<Integer> segundaMetadeAposRemocao = new ArrayList<Integer>();
//		ArrayList<Integer> indices = new ArrayList<Integer>();
//		int k = 0;
//		int[] ind = new int[a.genes.length];
//
//		for(int i = 0; i < a.genes.length/2; i++){
//			solucao.add(a.genes[i]);
//			while (indices.contains(k)){
//				k = new Double(Math.random() * segundaMetade.length).intValue();
//			}
//			indices.add(k);
//		}
//
//		for (int j = 0; j < segundaMetade.length; j++) {
//			if (!indices.contains(j)) {
//				segundaMetadeAposRemocao.add(segundaMetade[j]);
//			}
//		}
//
//		solucao.addAll(segundaMetadeAposRemocao);
//
//		for (int l = 0; l < solucao.size(); l++) {
//			ind[l] = solucao.get(l);
//		}
//
//		return ind;
//	}

//	public void mutacao(Individuo[] pop) {
//		int nMut = (int) Math.ceil(pop.length * txMutacao);
//	    for(int i = 0; i < nMut; i++) {
//	    	int indiceIndividuo = (int) Math.round(Math.random()*(pop.length - 1));
//	    	int indiceFuncao = (int) Math.round(Math.random()*(4));
//				
//			if(indiceFuncao == 1) {
//				Opt2 opt2 = new Opt2();
//				opt2.criarSucessor(pop[indiceIndividuo].genes);
//			}else if(indiceFuncao == 2) {
//				Opt3 opt3 = new Opt3();
//				opt3.criarSucessor(pop[indiceIndividuo].genes);
//			}else if(indiceFuncao == 3) {
//				RandomInsertion insertion = new RandomInsertion();
//				insertion.criarSucessor(pop[indiceIndividuo].genes);
//			}else if(indiceFuncao == 4) {
//				Swapping swapping = new Swapping();
//				swapping.criarSucessor(pop[indiceIndividuo].genes);
//			}
//			
//	    }
//	}
	
//	public static Populacao sobrevivente(Subpopulacao subpopulacao) {
//		//nova população do mesmo tamanho da antiga
//		Subpopulacao novaPopulacao = new Subpopulacao(subpopulacao.individuos.length);
//
//		Individuo melhor = get_mais_apto(subpopulacao.individuos);
//		novaPopulacao.setIndividuo(melhor);
//		
//
//		//insere novos indivíduos na nova população, até atingir o tamanho máximo
//		while (novaPopulacao.getNumIndividuos() < tamSubPopulacao) {
//			//seleciona os 2 pais por torneio
//			Individuo[] pais = selecaoTorneio(subpopulacao);
//
//			Individuo[] filhos = new Individuo[2];
//
//			//verifica a taxa de crossover, se sim realiza o crossover, se não, mantém os pais selecionados para a próxima geração
//			if (r.nextDouble() <= taxaDeCrossover) {
//				filhos = crossover(pais[1], pais[0]);
//			} else {
//				filhos[0] = new Individuo(pais[0].getGenes());
//				filhos[1] = new Individuo(pais[1].getGenes());
//			}
//
//			//adiciona os filhos na nova geração
//			novaPopulacao.setIndividuo(filhos[0]);
//			novaPopulacao.setIndividuo(filhos[1]);
//		}
//
//		//ordena a nova população
//		novaPopulacao.ordenaPopulacao();
//		return novaPopulacao;
//	}
	
	//retorna o individuo mais apto
	public static Individuo get_mais_apto(Individuo lst[]){
		//indice do individuo mais apto
		int indice = get_indice_mais_apto(lst);

		//individuo mais apto
		return lst[indice];		
	}

	//retorna o indice do mais apto
	public static int get_indice_mais_apto(Individuo lst[]) {
		int indice_melhor_solucao = -1;
		int indice = 0;
		double qlde = Double.MIN_VALUE;
		double max  = Double.MIN_VALUE;
		ArrayList<Desenvolvedor> melhorSolLocal = new ArrayList<Desenvolvedor>();
		Individuo i = new Individuo();
		ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();

		for (Individuo s : lst) {

			if(s != null) {
				solLocal = i.solucao(s, relatorios, dados);
				qlde = i.individuoFitness(solLocal);
			}

			if(qlde > max){
				max = qlde;
				melhorSolLocal = solLocal;
				indice_melhor_solucao = indice;
			}
			indice ++;
		}
		if( max > melhor_Qlde) {
			melhor_Qlde = max;
			melhorSolucao = melhorSolLocal;
		}
		return indice_melhor_solucao;
	}
}
