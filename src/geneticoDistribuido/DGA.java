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

		Subpopulacao[] novaPopulacao = new Subpopulacao[qtdeSubpopulacao];
			
		do {
			//cria nova populacao
			novaPopulacao = novaGeracao(populacao);


			double aptidaoAnterior = Double.MIN_VALUE;;
			for(int i = 0; i < populacao.length; i++){
				aptidaoAnterior += populacao[i].getMediaAptidao();
			}

			//passar colecao de individuos devolve uma nova subpopulacao
			novaPopulacao = executaMutacao(novaPopulacao);

			//garante que os melhores individos (solucao) estarão na proxima populacao
			populacao = sobrevivente(populacao, novaPopulacao);

			double aptidaoPosterior = 0.0;
			for(int j = 0; j < populacao.length; j++){
				aptidaoPosterior += populacao[j].getMediaAptidao(); 
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

		migracao();

		for(int j = 0; j < qtdeSubpopulacao; j++){
			populacao[j].ordenaPopulacao();
		}

		return melhorSolucao();
	}
	
	public Subpopulacao[] novaGeracao(Subpopulacao[] subPop) {
           
		//cria nova população do mesmo tamanho da antiga
		Subpopulacao[] novaPopulacao = new Subpopulacao[this.qtdeSubpopulacao];
		
		for (int i = 0; i < this.qtdeSubpopulacao; i++) {
			
			novaPopulacao[i] = new Subpopulacao(tamSubpopulacao);
			
			for (int j = 0; j < this.qtdeSubpopulacao; j++) {
				while (novaPopulacao[i].getNumIndividuos() < this.tamSubpopulacao) {
					Individuo[] pais = new Individuo[2];
					//seleciona os 2 pais por torneio
					pais[0] = selecaoTorneio(subPop[i]);
					pais[1] = selecaoTorneio(subPop[i]);
		
					Individuo filho;
					
					filho = Individuo.cruzamento(pais[0], pais[1]);
					//adiciona os filhos na nova geração
					novaPopulacao[i].setIndividuo(filho);
				}
				
				//ordena a nova população
				novaPopulacao[i].ordenaPopulacao();
			}
		}
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
	
	public Subpopulacao[] executaMutacao(Subpopulacao[] Pop) {
		Subpopulacao[] novaPop = Pop;
	
		for(int vuelta = 0; vuelta < 2; vuelta++){
					
			for (int k = 0; k < qtdeSubpopulacao; k++) {
				
				for (int i = 0; i < qtdeSubpopulacao; i++) {
				
					novaPop[i] = mutacao(novaPop[i]);
					
					for(int j = 0; j < tamSubpopulacao; j++){
						//TEM SIDO, ASSIM UMA MUDANÇA É FEITA EM SEUS GENES
						if(novaPop[i].individuos[j].getMelhora() == 10){
							novaPop[i].individuos[j].setMelhora(0);
							trocarGenes(i, j);
						}
					}
				}
			}
			
			for(int l = 0; l < qtdeSubpopulacao; l++) {
				novaPop[l].ordenaPopulacao();
			}
			
		}
				
	    return novaPop;

	}
	
	public Subpopulacao mutacao(Subpopulacao subPop) {
		Subpopulacao novaSubpopulacao = subPop;
		ArrayList<Desenvolvedor> novoGenes = new ArrayList<Desenvolvedor>();
		Individuo individuo = null;
		boolean melhorou = false;
		int indiceFuncao = 0;
			
			for (int k = 0; k < tamSubpopulacao; k++) {
				melhorou = false;
				indiceFuncao = k;
				int j = 0;

				do {
					if(indiceFuncao == 0) {
						Opt2 opt2 = new Opt2();
						novoGenes = opt2.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 1) {
						Opt3 opt3 = new Opt3();
						novoGenes = opt3.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 2) {
						Opt3AndOpt2 opt3AndOpt2 = new Opt3AndOpt2();
						novoGenes = opt3AndOpt2.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 3) {
						RandomInsertion insertion = new RandomInsertion();
						novoGenes = insertion.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 4) {
						Opt3AndInsertion opt3AndInsertion = new Opt3AndInsertion();
						novoGenes = opt3AndInsertion.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 5) {
						Opt2AndInsertion opt2AndInsertion = new Opt2AndInsertion();
						novoGenes = opt2AndInsertion.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 6) {
						Swapping swapping = new Swapping();
						novoGenes = swapping.criarSucessor(novaSubpopulacao.individuos[k]);
					}else if(indiceFuncao == 6) {
						RandomInsertion randomInsertion = new RandomInsertion();
						novoGenes = randomInsertion.criarSucessor(novaSubpopulacao.individuos[k]);
					}

					individuo = new Individuo(novoGenes);

					if(individuo.getAptidao() > novaSubpopulacao.individuos[k].aptidao) {
						novaSubpopulacao.individuos[k] = individuo;
						melhorou = true;
						j = 0;
					}else {
						j++;
					}
				}while(j < tamSubpopulacao/2);

				if (melhorou == false){
					novaSubpopulacao.individuos[k].setMelhora(novaSubpopulacao.individuos[k].getMelhora() + 1);
				}else{
					novaSubpopulacao.individuos[k].setMelhora(0);
				}

				novaSubpopulacao.ordenaPopulacao();
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
	public Subpopulacao[] sobrevivente(Subpopulacao[] popInicial, Subpopulacao[] novaPop) {
		
		int tamPopSobrevivente = tamSubpopulacao;
		int tamPopTotal = tamSubpopulacao * 2;
		Subpopulacao[] popSobrevivente = new Subpopulacao[qtdeSubpopulacao];
		
		for (int i = 0; i < qtdeSubpopulacao; i++) {
			Subpopulacao popTotal = new Subpopulacao(tamPopTotal);
			popSobrevivente[i] = new Subpopulacao(tamPopSobrevivente);
						
			for (int j = 0; j < tamPopSobrevivente; j++) {
				popTotal.individuos[j] = popInicial[i].individuos[j];
			}
			
			int l = 0;		
			for (int k = tamPopSobrevivente; k < tamPopTotal; k++) {
				popTotal.individuos[k] = novaPop[i].individuos[l];
				l++;
			}
			
			popTotal.ordenaPopulacao();
			
			for(int m = 0; m < tamPopSobrevivente; m++) {
				popSobrevivente[i].individuos[m] = popTotal.individuos[m];
			}
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
