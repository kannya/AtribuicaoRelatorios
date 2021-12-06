package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.algoritmo.Jogador;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class DGA{
	
	Dados dados;
	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	public Subpopulacao[] populacao;
	private int tamSubpopulacao;
	private int qtdeSubpopulacao;
	private Individuo melhorIndividuo = new Individuo();
	private int semMelhoria = 0;
	private double melhorAptidao = Integer.MIN_VALUE;

  //Constructor
  	public DGA(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> rel){ //4
  		this.dados = new Dados(des, rel);
  		this.relatorios = rel;
  	}
    
  	public Individuo executarInstancia(int qtdeSub, int tamSub) {
  		inicializaPopulacao(qtdeSub, tamSub);
		return execute();
		
  	}
  	
    public void inicializaPopulacao(int qtdeSubpop, int tamSubpop) {
		this.tamSubpopulacao = tamSubpop;
		this.qtdeSubpopulacao = qtdeSubpop;
		this.populacao = new Subpopulacao[qtdeSubpopulacao];
		
		for (int i = 0; i < qtdeSubpopulacao; i++) {
			populacao[i] = new Subpopulacao(relatorios, dados, tamSubpopulacao);
		}

	}
    
	
	public Individuo execute() {

		for (int i = 0; i < qtdeSubpopulacao; i++) {
			Subpopulacao novaPopulacao = new Subpopulacao(tamSubpopulacao);
			
			//cria nova populacao
			novaPopulacao = novaGeracao(populacao[i]);
			
			do {
							
				double aptidaoAnterior = Double.MIN_VALUE;;
				for(int l = 0; l < populacao.length; l++){
					aptidaoAnterior += populacao[l].getMediaAptidao();
				}
				
				//passar colecao de individuos devolve uma nova subpopulacao
				novaPopulacao = mutacao(novaPopulacao);
				
				for(int j = 0; j < novaPopulacao.individuos.length; j++){
					//TEM SIDO, ASSIM UMA MUDANÇA É FEITA EM SEUS GENES
					if(novaPopulacao.individuos[j].getMelhora() == 10){
						novaPopulacao.individuos[j].setMelhora(0);
						trocarGenes(i, j);
					}
				}
				
				//garante que o melhor individo(solucao) estara na proxima subpopulacao
				populacao[i] = sobrevivente(populacao[i], novaPopulacao);
				
				double aptidaoPosterior = 0.0;
				for(int l = 0; l < populacao.length;l++){
					aptidaoPosterior += populacao[l].getMediaAptidao(); 
				}
				
				//verifica aptidao da subpopulação
				if(aptidaoPosterior < aptidaoAnterior){
					semMelhoria = 0;
				}else{
					semMelhoria++;
				}
				
								//verifica a aptidao do individuo
				double melhorAptidaoAnterior = this.melhorAptidao;
				melhorSolucao();
				if(this.melhorAptidao < melhorAptidaoAnterior){
					semMelhoria = 0;
				}
				
			}while(semMelhoria < 1);
		}
		
		migracao();
		
		for(int j = 0; j < qtdeSubpopulacao; j++){
			populacao[j].ordenaPopulacao();
		}
		
		return melhorSolucao();
	}
	
	public Subpopulacao novaGeracao(Subpopulacao subPop) {
             
		//cria nova população do mesmo tamanho da antiga
		Subpopulacao novaPopulacao = new Subpopulacao(this.tamSubpopulacao);
		
		while (novaPopulacao.getNumIndividuos() < this.tamSubpopulacao) {
			Individuo[] pais = new Individuo[2];
			//seleciona os 2 pais por torneio
			pais[0] = selecaoTorneio(subPop);
			pais[1] = selecaoTorneio(subPop);

			Individuo filho;
			
			filho = Individuo.cruzamento(pais[0], pais[1]);
			//adiciona os filhos na nova geração
			novaPopulacao.setIndividuo(filho);
		}
		
		//ordena a nova população
		novaPopulacao.ordenaPopulacao();
		return novaPopulacao;
    }

	public Individuo selecaoTorneio(Subpopulacao subPop) {
		//quantidade de pais
		int n = 3;
        Subpopulacao subPopulacaoIntermediaria = new Subpopulacao(n);

       //seleciona indivíduos aleatóriamente na população para serem os pais
        for (int i = 0; i < n; i++) {
            int pos = new Double(Math.random() * this.tamSubpopulacao).intValue();
            subPopulacaoIntermediaria.setIndividuo(subPop.getIndivduo(pos));
        }

        //ordena a população
        subPopulacaoIntermediaria.ordenaPopulacao();

        return subPopulacaoIntermediaria.getIndivduo(0);
    }
	
	public Subpopulacao mutacao(Subpopulacao subPop) {
		Subpopulacao novaSubpopulacao = subPop;
		Individuo individuo = null;
		int qtdeFuncoes = 4;
		boolean melhorou = false;
		int indiceFuncao = 0;
		
		for(int vuelta = 0; vuelta < 2; vuelta++){
					
			int nMut = (int) Math.round(Math.random()*(novaSubpopulacao.individuos.length));
			
			for (int k = 0; k < novaSubpopulacao.individuos.length; k++) {
				melhorou = false;
				
				for(int i = 0; i < nMut; i++) {
	//		    	int indiceIndividuo = (int) Math.round(Math.random()*(nMut - 1));
			    	if(qtdeSubpopulacao == 1){
			    		indiceFuncao = (int) Math.round(Math.random()*(qtdeFuncoes - 1));
			    	}
			    	ArrayList<Desenvolvedor> novoGenes = new ArrayList<Desenvolvedor>();
			    	    	
					if(indiceFuncao == 0) {
						Opt2 opt2 = new Opt2();
						novoGenes = opt2.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 1) {
						Opt3 opt3 = new Opt3();
						novoGenes = opt3.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 2) {
						RandomInsertion insertion = new RandomInsertion();
						novoGenes = insertion.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 3) {
						Swapping swapping = new Swapping();
						novoGenes = swapping.criarSucessor(novaSubpopulacao.individuos[k]);
					}
					
					individuo = new Individuo(novoGenes);
					
					if(individuo.getAptidao() > novaSubpopulacao.individuos[k].aptidao) {
						novaSubpopulacao.individuos[k] = individuo;
						melhorou = true;
					}
			    }
				
				if (melhorou == false){
					novaSubpopulacao.individuos[k].setMelhora(novaSubpopulacao.individuos[k].getMelhora() + 1);
				}else{
					novaSubpopulacao.individuos[k].setMelhora(0);
				}
				
				novaSubpopulacao.ordenaPopulacao();
				
			}
		}
				
	    return novaSubpopulacao;

	}
	
	public void trocarGenes(int i, int j) {
		Individuo ind1 = null;
		
		int subpopIndice = new Double(Math.random() * this.populacao.length).intValue();
		while(subpopIndice == i){
			subpopIndice = new Double(Math.random() * this.populacao.length).intValue();
		}
		int indIndice = new Double(Math.random() * this.populacao[subpopIndice].individuos.length).intValue();
		
		ind1 = this.populacao[subpopIndice].individuos[indIndice];
		
		this.populacao[subpopIndice].individuos[indIndice] = this.populacao[i].individuos[j];
		this.populacao[i].individuos[j] = ind1;
		
	}
	
	//os melhores da nova subpopulacao substituem os piores da subpopulacao inicial
	public Subpopulacao sobrevivente(Subpopulacao popInicial, Subpopulacao novaPop) {
		
		int tamPopSobrevivente = popInicial.individuos.length;
		int tamPopTotal = popInicial.individuos.length * 2;
		Subpopulacao popTotal = new Subpopulacao(tamPopTotal);
		Subpopulacao popSobrevivente = new Subpopulacao(tamPopSobrevivente);
				
		for (int i = 0; i < tamPopSobrevivente; i++) {
			popTotal.individuos[i] = popInicial.individuos[i];
		}
		
		int l = 0;		
		for (int j = tamPopSobrevivente; j < tamPopTotal; j++) {
			popTotal.individuos[j] = novaPop.individuos[l];
			l++;
		}
		
		popTotal.ordenaPopulacao();
		
		for(int k = 0; k < tamPopSobrevivente; k++) {
			popSobrevivente.individuos[k] = popTotal.individuos[k];
		}
		
		return popSobrevivente;

	}
	
	public void migracao() {
		int tamInd = this.populacao[0].individuos.length;
		int tamPop = this.populacao.length;
		this.populacao[0].individuos[tamInd - 1] = this.populacao[tamPop - 1].individuos[0];
		
		for (int i = 1; i < this.populacao.length; i++) {
			this.populacao[i].individuos[tamInd - 1] = this.populacao[i-1].individuos[0];
		}
		
	}
	
	//Este é o método que é executado no final do processo, é aquele que retorna o melhor jogador
	private Individuo melhorSolucao() { //35
		double q = Double.MIN_VALUE;
		//Cada equipe é monitorada
		for(int i = 0; i < populacao.length; i++){
			//De cada equipe, cada jogador é rastreado
			if(populacao[i].getMediaAptidao() > q){
				q = populacao[i].getMediaAptidao();
				this.melhorIndividuo = populacao[i].individuos[0];
			}
		}
		
		if(q > this.melhorAptidao){
			this.melhorAptidao = q;
		}

		return this.melhorIndividuo;
	}
	
}
