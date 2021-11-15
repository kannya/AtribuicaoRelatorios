package forcaBruta;

import java.util.ArrayList;
import java.util.Collection;

import genetico.Dados;
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
	
	//guarda a melhor solucuao
	private ArrayList<Desenvolvedor> melhorSolucao;
	
	private double qlde = Double.MIN_VALUE;
	private double maxQlde = Double.MIN_VALUE;

	/**
	 * metodo principal: recebe o vetor cujos elementos que serao permutados
	 * @param vet
	 */
	public ArrayList<Desenvolvedor> permuta(ArrayList<Relatorio> relatorios, ArrayList<Desenvolvedor> desenvolvedores) {
		Dados dados = new Dados(desenvolvedores, relatorios);
		
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
		
		return melhorSolucao;
	}

	/**
	 * método recursivo que implementa as permutacoes
	 * @param relatorios
	 * @param desenvolvedores
	 * @param n
	 */
	private void permuta(int qtdeRel, int[] idsDesenvolvedores, int n) {
		/*sol[0] = 72;
		sol[1] = 1;
		sol[2] = 72;		
		sol[3] = 1;
		sol[4] = 77;
		sol[5] = 68;
		sol[6] = 164;
		
		calcularQualidade(sol, qtdeRel);*/
		
		if (n == qtdeRel) {
			cont++;
			calcularQualidade(sol, qtdeRel);
		} else {

			for (int i = 0; i < idsDesenvolvedores.length; i++) {
				sol[n] = idsDesenvolvedores[i];
				permuta(qtdeRel, idsDesenvolvedores, n + 1);

			} //--for

		} //--if/else
		
	} //--permuta
	
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
		}
		
		qlde = funcaoObjetivo.avaliacao(solLocal);

		if(qlde > maxQlde){
			maxQlde = qlde;
			this.melhorSolucao = (ArrayList<Desenvolvedor>) solLocal;
		}
		
	}

	public double getMaxQlde() {
		return maxQlde;
	}


	public void setMaxQlde(double maxQlde) {
		this.maxQlde = maxQlde;
	}

	
}