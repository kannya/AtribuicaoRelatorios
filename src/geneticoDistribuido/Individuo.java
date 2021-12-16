package geneticoDistribuido;

import java.util.ArrayList;

import genetico.Dados;
import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Individuo {
	public ArrayList<Desenvolvedor> genes = new ArrayList<Desenvolvedor>();
	public double aptidao = 0.0;
	public int melhora = 0;

	// len quantidade de relatorios no genes
	// ideal para ser chamado na primeira geracao
	public Individuo(int qtdeRel, Dados dados) {
		genes = new ArrayList<Desenvolvedor>();
		
		int indice;
		
		for(int i = 0; i < qtdeRel; i++){
			indice = new Double(Math.random() * dados.getDesenvolvedores().size()).intValue();
			genes.add(dados.getDesenvolvedores().get(indice));
		}

		geraAptidao(genes);
	}

	public Individuo(ArrayList<Desenvolvedor> novoGenes) {
		genes.addAll(novoGenes);
		aptidao = 0.0;
		
		geraAptidao(genes);
	}
	
	public Individuo() {

	}

	public static Individuo cruzamento(Individuo a, Individuo b) {

		ArrayList<Desenvolvedor> novoGenes = new ArrayList<Desenvolvedor>();
		ArrayList<Desenvolvedor> novoGenes2 = new ArrayList<Desenvolvedor>();
		Individuo segundaMetade = b;
		Individuo segundaMetade2 = a;
		ArrayList<Desenvolvedor> segundaMetadeAposRemocao = new ArrayList<Desenvolvedor>();
		ArrayList<Desenvolvedor> segundaMetadeAposRemocao2 = new ArrayList<Desenvolvedor>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		ArrayList<Integer> indices2 = new ArrayList<Integer>();
		int k = 0;
		int l = 0;
		
		for(int i = 0; i < a.genes.size()/2; i++){
			novoGenes.add(a.genes.get(i));
			novoGenes2.add(b.genes.get(i));
			
			while (indices.contains(k)){
				k = new Double(Math.random() * segundaMetade.genes.size()).intValue();
			}
			indices.add(k);
			
			while (indices.contains(l)){
				l = new Double(Math.random() * segundaMetade2.genes.size()).intValue();
			}
			indices2.add(l);
		}

		for (int j = 0; j < segundaMetade.genes.size(); j++) {
			if (!indices.contains(j)) {
				segundaMetadeAposRemocao.add(segundaMetade.genes.get(j));
			}
		}
		
		for (int m = 0; m < segundaMetade2.genes.size(); m++) {
			if (!indices.contains(m)) {
				segundaMetadeAposRemocao2.add(segundaMetade2.genes.get(m));
			}
		}

		novoGenes.addAll(segundaMetadeAposRemocao);
		novoGenes2.addAll(segundaMetadeAposRemocao2);
		
		Individuo ind = new Individuo(novoGenes);
		Individuo ind2 = new Individuo(novoGenes2);
		
		if(ind.aptidao > ind2.aptidao) {
			return ind;
		}else {
			return ind2;
		}
	}
	
	public static Individuo cruzamentoTorneio(Individuo a, Individuo b) {

		ArrayList<Desenvolvedor> novoGenes = new ArrayList<Desenvolvedor>();
		Individuo segundaMetade = b;
		ArrayList<Desenvolvedor> segundaMetadeAposRemocao = new ArrayList<Desenvolvedor>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int k = 0;
		
		for(int i = 0; i < a.genes.size()/2; i++){
			novoGenes.add(a.genes.get(i));
			
			while (indices.contains(k)){
				k = new Double(Math.random() * segundaMetade.genes.size()).intValue();
			}
			indices.add(k);
		}

		for (int j = 0; j < segundaMetade.genes.size(); j++) {
			if (!indices.contains(j)) {
				segundaMetadeAposRemocao.add(segundaMetade.genes.get(j));
			}
		}

		novoGenes.addAll(segundaMetadeAposRemocao);
		
		Individuo ind = new Individuo(novoGenes);
		
		return ind;
	}

	//gera o valor de aptidão
	private void geraAptidao(ArrayList<Desenvolvedor> solLocal) {
		FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo(); 
		aptidao = funcaoObjetivo.avaliacao(solLocal);
	}

	public ArrayList<Desenvolvedor> solucao(Individuo s, ArrayList<Relatorio> rel, Dados d) {

		ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
		if(s != null) {
			for (int i = 0; i < rel.size(); i++) {
				for (Desenvolvedor des : d.getDesenvolvedores()) {
					if(s.genes.get(i).getIdDesenvolvedor() == des.getIdDesenvolvedor()) {
						solLocal.add(des);
						break;
					}
				}
			}
		}
		return solLocal;
	}

	public double getAptidao() {
		return aptidao;
	}

	public int getMelhora() {
		return melhora;
	}

	public void setMelhora(int melhora) {
		this.melhora = melhora;
	}
	
}
