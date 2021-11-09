package genetico;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class SelecaoTorneio {
	
	ArrayList<Relatorio> relatorios;
	Individuo[] populacao;
	Dados dados;
	public double melhor_Qlde = Double.MIN_VALUE;
	public ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();
	
	public SelecaoTorneio(Individuo[] pop, ArrayList<Relatorio> rel, Dados d) {
		relatorios = rel;
		populacao = pop;
		dados = d;
	}
	public SelecaoTorneio() {
		
	}
	
	public Individuo[] torneio(){
		int qnt_torneio = relatorios.size() + 1;
		Individuo to_return[] = new Individuo[2];
		Individuo lst[] = new Individuo[qnt_torneio];
		
		// seleciona qnt_torneio Individuos aleatoriamente
		for(int i = 0; i < qnt_torneio; i++){
			lst[i] = populacao[new Double(Math.random() * populacao.length).intValue()];
		}
		
		// seleciona os dois mais aptos para retornar
		int i1;
		i1 = get_indice_mais_apto(lst);
		to_return[0] = populacao[i1];
		lst[i1] = null;
		
		to_return[1] = get_mais_apto(lst);
		
		return to_return;
	}
	
	//retorna o individuo mais apto
	public Individuo get_mais_apto(Individuo lst[]){
		//indice do individuo mais apto
		int indice = get_indice_mais_apto(lst);

		//individuo mais apto
		return lst[indice];		
	}

	//retorna o indice do mais apto
	public int get_indice_mais_apto(Individuo lst[]) {
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
			this.melhorSolucao = melhorSolLocal;
		}
		return indice_melhor_solucao;
	}
}
