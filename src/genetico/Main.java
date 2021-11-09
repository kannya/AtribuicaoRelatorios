package genetico;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

public class Main {
	
	public static void main(String[] args) {
		AG ag = new AG();
		Individuo[] populacao = ag.populacao;
		SelecaoTorneio torneio = new SelecaoTorneio(populacao, ag.relatorios, ag.dados);
		Individuo e;
		int epocas = 0;
		double melhor_Qlde = Double.MIN_VALUE;
		ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();

		while(true){
			//selecao torneio
			/*e = torneio.get_mais_apto(ag.populacao);
			ag.proxima_geracao_torneio();
			*/
			
			//selecao roleta
			populacao = ag.proxima_geracao_roleta();
			e = torneio.get_mais_apto(populacao);
			
			if(melhor_Qlde < torneio.melhor_Qlde) {
				melhor_Qlde = torneio.melhor_Qlde;
				melhorSolucao = torneio.melhorSolucao;
			}
			
			epocas++;
			if(epocas == 500)break;
			
			System.out.println("melhor solução da Rodada " + epocas + ": " + torneio.melhorSolucao);
			System.out.println("melhor qualidade da Rodada " + epocas + ": " + torneio.melhor_Qlde);
		}
		System.out.println("melhor solução Final: " + melhorSolucao);
		System.out.println("melhor qualidade Final: " + melhor_Qlde);
	}
}
