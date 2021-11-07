package genetico;

public class Main {
	public static void main(String[] args) {
		AG ag = new AG();
		Individuo e;
		int epocas = 0;
		
		while(true){
			e = ag.get_mais_apto(ag.populacao);
			
			ag.proxima_geracao();
			
			epocas++;
			if(epocas == 50)break;
			
		}
		System.out.println("melhor solução: " + ag.melhorSolucao);
		System.out.println("melhor qualidade: " + ag.melhor_Qlde);
	}
}
