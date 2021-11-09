package genetico;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class SelecaoRoleta {
	
	ArrayList<Relatorio> relatorios;
	Individuo[] populacao;
	Dados dados;
	
	public SelecaoRoleta(Individuo[] pop, ArrayList<Relatorio> rel, Dados d) {
		relatorios = rel;
		populacao = pop;
		dados = d;
	}
	
	// ideal para ser chamada nas geracoes posteriores
	public Individuo[] selecao() {
		
		Individuo[] novaPopulacao = new Individuo[populacao.length];
		int segundaMetade = (int)Math.ceil(populacao.length * 0.5);
	
		novaPopulacao = roleta();
		//preencher as posições restantes da população com individuos aleatórios
		for(int i = segundaMetade; i < populacao.length; i++) {
			novaPopulacao[i] = new Individuo(relatorios.size(), dados);
		}
		
		novaPopulacao = cruzamento_roleta(novaPopulacao);
		for (Individuo ind : novaPopulacao) {
			ind.mutacao();
		}
		return novaPopulacao;
	}
	
	/*public Individuo roleta(){
		double fitnessTotal = 0.0;
		double qlde = 0.0;
		Individuo ind = new Individuo();
		ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
		
	    fitnessTotal = popFitness();
	    	    
	    double rnd = Math.random();
	    for (Individuo s : populacao) {
	    	if(s != null) {
	    		solLocal = ind.solucao(s, relatorios, dados);
	    		qlde = ind.individuoFitness(solLocal);

	    		double valor = (10 * qlde / fitnessTotal);
	    		if(rnd <= valor) {
	    			return s;
	    		}
	    		rnd -= valor;
	    	}		    
	    }
	    return null;

	}*/
	
	public Individuo[] roleta(){
		double fitnessTotal = 0.0;
		double qlde = 0.0;
		Individuo ind = new Individuo();
		ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
		Individuo[] novaPopulacao = new Individuo[populacao.length];
		double[] roleta = new double[populacao.length];
		
		fitnessTotal = popFitness();

		solLocal = ind.solucao(populacao[0], relatorios, dados);
    	qlde = ind.individuoFitness(solLocal);
    	roleta[0] = (100 * qlde)/fitnessTotal;
		
	    for(int j = 1; j < populacao.length; j++) {
	    	solLocal = ind.solucao(populacao[j], relatorios, dados);
	    	qlde = ind.individuoFitness(solLocal);
	    	roleta[j] = roleta[j-1] + (100 * qlde)/fitnessTotal;
		}
	    
	    int numSel = (int)Math.ceil(populacao.length * 0.5); // número de indivíduos a serem selecionados
	    // gira a roleta "numSel" vezes
	    for(int i = 0; i < numSel; i++) { 
	    	double seta = Math.random()*100; // girou a roleta (0 a 100)
	    	for(int j = 0; j < roleta.length; j++) { 
	    		if(roleta[j] >= seta) { // selecionado
	    			novaPopulacao[i] = populacao[j];
	    			break;
	    		}
	    	}   
	    } // fim da roleta 
	    
	     return novaPopulacao;
	}
	
	private double popFitness() {
		double qlde = 0.0;
		double soma = 0.0;
		Individuo ind = new Individuo();
		ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
		
		for (Individuo s : populacao) {
			if(s != null) {
				solLocal = ind.solucao(s, relatorios, dados);
				qlde = ind.individuoFitness(solLocal);
			}
			soma += qlde;
		}
		return soma;
	}
	
	public Individuo[] cruzamento_roleta(Individuo[] proxPopulacao) {
	    int numSel = (int)Math.ceil(proxPopulacao.length * 0.5); // número de indivíduos que foram selecionados
	    int dif = 1; // diferenca entre posição entre os pais
	    int pos = 0; // começa do primeiro elemento selecionado
	    Individuo ind = new Individuo();
	    
	    for(int i = numSel; i < proxPopulacao.length; i++){
	        int[] filho = ind.cruzamento_torneio(proxPopulacao[pos], proxPopulacao[pos + dif]);
	        proxPopulacao[i].genes = filho; // adicionado à nova população
	        pos++; // incrementando a posição no vetor
	        if(pos > numSel - dif) {// implica que não vai ter parceiro, então ... 
	            pos = 0; // reinicia do primeiro elemento
	            dif++; // incrementa a diferença de posição entre pais a se cruzarem
	        }
	    }
	    return proxPopulacao;
	}
	
}
