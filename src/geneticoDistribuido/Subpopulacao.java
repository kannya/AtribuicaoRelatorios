package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.logica.Relatorio;

public class Subpopulacao {
	public Individuo[] individuos;
	Dados dadosGlobal;
	static ArrayList<Relatorio> relatorios;
	
	public Subpopulacao(ArrayList<Relatorio> rel, Dados dados, int tamSubpopulacao) {
		relatorios = new ArrayList<Relatorio>();
		dadosGlobal = dados;
		
		individuos = new Individuo[tamSubpopulacao];
		for (int i = 0; i < individuos.length; i++) {
			individuos[i] = new Individuo(rel.size(), dados);
		}
		
		ordenaPopulacao();
	}

	//cria uma população com indivíduos sem valor, será composto posteriormente
	public Subpopulacao(int tamSubPop) {
        individuos = new Individuo[tamSubPop];
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = null;
        }
	}
	
	//coloca um indivíduo em uma certa posição da população
//    public void setIndividuo(Individuo individuo, int posicao) {
//        individuos[posicao] = individuo;
//    }
	
	//coloca um indivíduo na próxima posição disponível da população
    public void setIndividuo(Individuo individuo) {
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] == null) {
                individuos[i] = individuo;
                return;
            }
        }
    }
    
   //número de indivíduos existentes na população
    public int getNumIndividuos() {
        int num = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] != null) {
                num++;
            }
        }
        return num;
    }

    public Individuo getIndivduo(int pos) {
        return individuos[pos];
    }
    
    public void ordenaPopulacao() {
        boolean trocou = true;
        while (trocou) {
            trocou = false;
            for (int i = 0; i < individuos.length - 1; i++) {
                if (individuos[i].getAptidao() < individuos[i + 1].getAptidao()) {
                    Individuo temp = individuos[i];
                    individuos[i] = individuos[i + 1];
                    individuos[i + 1] = temp;
                    trocou = true;
                }
            }
        }
    }
    
    //Função para calcular a aptidao da subpopulacao, para isso faz a média dos 10 melhores individuos
    public double getMediaAptidao() {
        double media = getSomaDaAptidao() / 10;
        return media;
    }
    
    private double getSomaDaAptidao() {
		double soma = 0.0;
		
		for (Individuo ind : individuos) {
			soma += ind.getAptidao();
		}
		return soma;
	}
    
    //Faça a soma de um número
    public static int somatorio(int num) {
		int res = 1;
		while(num != 0){
			res = res + num;
			num--;
		}
		return res;
	}

}
