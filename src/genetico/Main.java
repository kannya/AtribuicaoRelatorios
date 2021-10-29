package genetico;

// a solucao do problema nao pode ter duas cidades no array de solucao
// considera e todas estao ligadas com todas

public class Main {
	public static void main(String[] args) {
		AG ag = new AG();
		Individuo e;
		Double qlde_anterior;
		int epocas = 0;
		
		while(true){
			ag.get_mais_apto(ag.populacao);
			
			ag.proxima_geracao();
			
			epocas++;
			if(epocas == 50)break;
			
		}
//		System.out.println("> " + e.get_distancia_percurso(ag.dados));
//		System.out.println(">> " + Utils.join_interable(e.genes, " "));
		
		// acho q a melhor solucao eh perto de 13.60
		// A F B N T M S U
		// U S M T N B F A
	}
}
