package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class DGA{
	
	Dados dados;
	static ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	public Subpopulacao[] populacao;
	private static int tamSubpopulacao;
	private static int qtdeSubpopulacao;
	private static int[] solucao;
	private static double taxaDeSelecao = 0.3;
    private static double taxaDeMutacao = 100;

	public DGA(Dados d, ArrayList<Relatorio> rel, int qtdeSubpop, int tamSubpop) {
		dados = d;
		relatorios = rel;
		tamSubpopulacao = tamSubpop;
		qtdeSubpopulacao = qtdeSubpop;
		populacao = new Subpopulacao[qtdeSubpopulacao];
		
		for (int i = 0; i < qtdeSubpopulacao; i++) {
			populacao[i] = new Subpopulacao(rel, d, tamSubpopulacao);
		}

	}
	
	public static Subpopulacao novaGeracao(Subpopulacao populacao) {
             
		//cria nova população do mesmo tamanho da antiga
		Subpopulacao novaPopulacao = new Subpopulacao(tamSubpopulacao);
		
		//insere novos indivíduos na nova população, até atingir o tamanho máximo
		while (novaPopulacao.getNumIndividuos() < tamSubpopulacao) {
			Individuo[] pais = new Individuo[2];
			//seleciona os 2 pais por torneio
			pais[0] = selecaoTorneio(populacao);
			pais[1] = selecaoTorneio(populacao);

			Individuo filho;
			
			filho = Individuo.cruzamento(pais[0], pais[1]);
			//adiciona os filhos na nova geração
			novaPopulacao.setIndividuo(filho);
		}
		
		//ordena a nova população
		novaPopulacao.ordenaPopulacao();
		return novaPopulacao;
    }

	public static Individuo selecaoTorneio(Subpopulacao populacao) {
		//quantidade de pais
		int n = 3;
        Subpopulacao subPopulacaoIntermediaria = new Subpopulacao(n);

       //seleciona indivíduos aleatóriamente na população para serem os pais
        for (int i = 0; i < n; i++) {
            int pos = new Double(Math.random() * tamSubpopulacao).intValue();
            subPopulacaoIntermediaria.setIndividuo(populacao.getIndivduo(pos));
        }

        //ordena a população
        subPopulacaoIntermediaria.ordenaPopulacao();

        return subPopulacaoIntermediaria.getIndivduo(0);
    }
	
	public Subpopulacao mutacao(Subpopulacao pop) {
		Subpopulacao novaSubpopulacao = pop;
		int qtdeFuncoes = 4;
		int nMut = (int) Math.round(Math.random()*(novaSubpopulacao.individuos.length));
		
		for(int i = 0; i < nMut; i++) {
	    	int indiceIndividuo = (int) Math.round(Math.random()*(nMut - 1));
	    	int indiceFuncao = (int) Math.round(Math.random()*(qtdeFuncoes - 1));
	    	ArrayList<Desenvolvedor> novoGenes = new ArrayList<Desenvolvedor>();
	    	    	
			if(indiceFuncao == 0) {
				Opt2 opt2 = new Opt2();
				novoGenes = opt2.criarSucessor(novaSubpopulacao.individuos[indiceIndividuo]);
			}else if(indiceFuncao == 1) {
				Opt3 opt3 = new Opt3();
				novoGenes = opt3.criarSucessor(novaSubpopulacao.individuos[indiceIndividuo]);
			}else if(indiceFuncao == 2) {
				RandomInsertion insertion = new RandomInsertion();
				novoGenes = insertion.criarSucessor(novaSubpopulacao.individuos[indiceIndividuo]);
			}else if(indiceFuncao == 3) {
				Swapping swapping = new Swapping();
				novoGenes = swapping.criarSucessor(novaSubpopulacao.individuos[indiceIndividuo]);
			}
			
			novaSubpopulacao.individuos[indiceIndividuo] = new Individuo(novoGenes);

	    }
		
		novaSubpopulacao.ordenaPopulacao();
	    return novaSubpopulacao;

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
	
	public void migracao(Subpopulacao[] populacao) {
			Individuo imigrante1 = populacao[0].individuos[0];
			Individuo imigrante2 = populacao[1].individuos[0];
			Individuo imigrante3 = populacao[2].individuos[0];
			Individuo imigrante4 = populacao[3].individuos[0];
			
			populacao[0].individuos[11] = imigrante4;
			populacao[1].individuos[11] = imigrante1;
			populacao[2].individuos[11] = imigrante2;
			populacao[3].individuos[11] = imigrante3;
		
	}
		
	public static int[] getSolucao() {
        return solucao;
    }

    public static void setSolucao(int[] solucao) {
        DGA.solucao = solucao;
    }

    public static double getTaxaDeMutacao() {
        return taxaDeMutacao;
    }

    public static void setTaxaDeMutacao(double taxaDeMutacao) {
        DGA.taxaDeMutacao = taxaDeMutacao;
    }
}
