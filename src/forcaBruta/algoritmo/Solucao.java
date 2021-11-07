package forcaBruta.algoritmo;

import java.util.ArrayList;
import java.util.Collection;

import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.GestorDistribuicaoRelatorios;
import goldenBall.logica.Relatorio;

/**
 * 
 * @author Kannya
 *
 * Esta é a classe para codificar as solucoes
 */

public class Solucao{

	private GestorDistribuicaoRelatorios gEst = GestorDistribuicaoRelatorios.getInstancia();
	
	//numero da permutacao atual
	private static int cont = 0;

	//armazena a permutacao corrente
	private Integer[] sol;
	
	//armazena todas as solucoes possiveis
	private ArrayList<ArrayList<Integer>> listaSolucoes;
	
	//guarda a melhor solucuao
	private ArrayList<Desenvolvedor> melhorSolucao;
	
	private ArrayList<Desenvolvedor> desenvolvedores;
	
	private double qlde = Double.MIN_VALUE;
	private double max = Double.MIN_VALUE;

	/**
	 * metodo principal: recebe o vetor cujos elementos que serao permutados
	 * @param vet
	 */
	public ArrayList<Desenvolvedor> permuta(ArrayList<Relatorio> relatorios, ArrayList<Desenvolvedor> desenvolvedores) {
		listaSolucoes = new ArrayList<ArrayList<Integer>>();
		preparar(desenvolvedores, relatorios);
		
		int qtdeRel = gEst.getL_relatorios().size();
		int qtdeDes = gEst.getL_desenvolvedores().size();
		
		int[] idsDesenvolvedores = new int[qtdeDes];
		int i = 0;
		
		sol = new Integer[qtdeRel];
		
		for(Desenvolvedor d : gEst.getL_desenvolvedores()){
			idsDesenvolvedores[i] = d.getIdDesenvolvedor();
			i++;
		}
		
		permuta(qtdeRel, idsDesenvolvedores, 0);
		
//		calcularQualidade(qtdeRel);
		
		return melhorSolucao;
	}


	/**
	 * método recursivo que implementa as permutacoes
	 * @param relatorios
	 * @param desenvolvedores
	 * @param n
	 */
	private void permuta(int qtdeRel, int[] idsDesenvolvedores, int n) {
		
		if (n == qtdeRel) {
			cont++;
//			montarListaSoucoes(qtdeRel);
			calcularQualidade(sol, qtdeRel);
		} else {

			for (int i = 0; i < idsDesenvolvedores.length; i++) {
				sol[n] = idsDesenvolvedores[i];
				permuta(qtdeRel, idsDesenvolvedores, n + 1);

			} //--for

		} //--if/else
		
	} //--permuta


	/** imprime a permutacao corrente */
//	private void montarListaSoucoes(int qtdeRel) {
//		ArrayList<Integer> s = new ArrayList<Integer>();
//		
//		System.out.println();
//		System.out.print("(" + cont + ") : ");
//		for (int i = 0; i < sol.length; i++) {
//			s.add(sol[i]);
//			System.out.print(sol[i] + " ");
//		}
//		
//		listaSolucoes.add(s);
//		
//	} //--imprime

	public void preparar(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> rel){
		try {
			this.desenvolvedores = des;
			this.OrganizarRelatoriosDesevolvedores(rel);
			gEst.setL_desenvolvedores((ArrayList<Desenvolvedor>) this.desenvolvedores);
			gEst.setL_relatorios((ArrayList<Relatorio>) rel);
			gEst.setIdesenv();
			gEst.setIdRel();
		}finally {
			
		}
	}
	
	public void OrganizarRelatoriosDesevolvedores(ArrayList<Relatorio> listaRel){
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		for (Desenvolvedor d1 : this.desenvolvedores) {
			Desenvolvedor desenvolvedor = new Desenvolvedor();
			Collection<Relatorio> relatorios = new ArrayList<Relatorio>();
			for (Desenvolvedor d2 : this.desenvolvedores) {
				if (d1.getIdDesenvolvedor() == d2.getIdDesenvolvedor()) {
					Relatorio r = new Relatorio();
					for(Relatorio rel : listaRel) {
						if(rel.getIdRelatorio() == d2.getIdRelatorio()) {
							r.setEsforco(rel.getEsforco());
							r.setIdRelatorio(d2.getIdRelatorio());
							r.setAfinidade(d2.getAfinidade());
							relatorios.add(r);
							break;
						}
					}
				}
			}
			
			desenvolvedor = d1;
			desenvolvedor.setIdRelatorio(0);
			desenvolvedor.setAfinidade(0);
			desenvolvedor.setRelatorios(relatorios);
			if(!desenvolvedores.contains(desenvolvedor)) {
				desenvolvedores.add(desenvolvedor);
			}
		}
		
		this.desenvolvedores = desenvolvedores;
	}
	
//	public void calcularQualidade(int qtdeRel) {
//		double qlde = Double.MIN_VALUE;
//		double max = Double.MIN_VALUE;
//		FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo();
//		
//		for (ArrayList<Integer> lista : listaSolucoes) {
//			Collection<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
//			for (int i = 0; i < qtdeRel; i++) {
//				for (Desenvolvedor d : gEst.getL_desenvolvedores()) {
//					if(lista.get(i) == d.getIdDesenvolvedor()) {
//						solLocal.add(d);
//						break;
//					}
//				}
//			}
//
//			qlde = funcaoObjetivo.avaliacao(solLocal);
//		    	
//			if(qlde > max){
//				max = qlde;
//				this.melhorSolucao = (ArrayList<Desenvolvedor>) solLocal;
//			}
//	    		
//		}
//		
//		System.out.println("\n\n\nO melhor jogador tem uma qualidade de " + max);
//	}
	
	public void calcularQualidade(Integer[] solucao, int qtdeRel) {

		FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo();
		Collection<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();

		for (int i = 0; i < qtdeRel; i++) {
			for (Desenvolvedor d : gEst.getL_desenvolvedores()) {
				if(solucao[i] == d.getIdDesenvolvedor()) {
					solLocal.add(d);
					break;
				}
			}
			System.out.print(solucao[i] + " ");
			
		}
		System.out.print("\n");
		
		qlde = funcaoObjetivo.avaliacao(solLocal);

		if(qlde > max){
			max = qlde;
			this.melhorSolucao = (ArrayList<Desenvolvedor>) solLocal;
		}
	}
	
}