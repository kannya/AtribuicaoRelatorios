package genetico;

public class Main {
	public static void main(String[] args) {
		AG ag = new AG();
		int epocas = 0;
		
		while(true){
			ag.get_mais_apto(ag.populacao);
			
			ag.proxima_geracao();
			
			epocas++;
			if(epocas == 50)break;
			
		}
	}
}
