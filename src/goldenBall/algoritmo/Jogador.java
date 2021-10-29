package goldenBall.algoritmo;

import java.util.ArrayList;

import goldenBall.logica.Desenvolvedor;

/**
 * 
 * @author Kannya
 *
 * Esta é a classe para codificar os indivíduos da população. Neste caso serão jogadores, que farão parte de uma equipe.
 */

public class Jogador implements Comparable{
	
	private ArrayList<Desenvolvedor> genes;
	private double qualidade;
	private String nome;
	private int melhora = 0;
	private int aposentadoria = 0;
	
	public Jogador() {
	}
	
	public Jogador(ArrayList<Desenvolvedor> genes) {
		this.genes = genes;
		this.qualidade = 0;
	}
	
	public int getAposentadoria() {
		return aposentadoria;
	}

	public void setAposentadoria(int aposentadoria) {
		this.aposentadoria = aposentadoria;
	}
	
	public int getMelhora() {
		return melhora;
	}

	public void setMelhora(int melhora) {
		this.melhora = melhora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Desenvolvedor> getGenes() {
		return genes;
	}

	public void setGenes(ArrayList<Desenvolvedor> genes) {
		this.genes = genes;
	}

	public double getQualidade() {
		return qualidade;
	}

	public void setQualidade(double qualidade) {
		this.qualidade = qualidade;
	}
	
	//Função de clonagem
	public Jogador clone(){
		Jogador p = new Jogador(this.getGenes());
		p.setQualidade(this.getQualidade());
		return p;
	}
	
	//Função para comparar e ordenar as listas com base na qualidade de cada jogador	
	public int compareTo(Object o){
		Jogador outro = (Jogador)o;
		if (this.qualidade < outro.qualidade) {
			return 1;
		} else if (this.qualidade == outro.qualidade) {
			return 0;
		} else {
			return -1;
		}
	}
	
}
