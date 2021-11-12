package goldenBall.algoritmo;

import java.util.ArrayList;
import java.util.Collections;

import goldenBall.logica.Desenvolvedor;

/**
 * 
 * @author Maldini
 *
 * Esta é a classe para codificar os equipamentos que farão parte do sistema


 */

public class Time implements Comparable{

	private ArrayList<Jogador> jogadores; 
	private double potencia;
	private int pontos;
	private String nome;
	private FuncaoSucessoraRandomica modoTreinamento;
	private FuncaoAvaliacao funcaoAvaliacao;
	private double confianca;
	private double historico = Double.MIN_VALUE;
	private int candidatoTreinamento;

	public Time(){
		this.potencia = 0.0;
		this.pontos = 0;
		this.confianca = 0.0;
		jogadores = new ArrayList<Jogador>();
	}
	
	public Time(ArrayList<Jogador> p){
		this.jogadores = p;
		this.potencia = 0.0;
		this.pontos = 0;
		this.confianca = 0.0;
	}
	
	public Time(String n){
		this.nome = n;
		this.potencia = 0.0;
		this.pontos = 0;
		this.confianca = 0.0;
		jogadores = new ArrayList<Jogador>();
	}
	
	public FuncaoAvaliacao getFuncaoAvaliacao() {
		return funcaoAvaliacao;
	}

	public void setFuncaoAvaliacao(FuncaoAvaliacao funcaoAvaliacao) {
		this.funcaoAvaliacao = funcaoAvaliacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public double getPotencia() {
		return potencia;
	}

	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public double getConfianca() {
		return confianca;
	}

	public void setConfianca(double confianca) {
		this.confianca = confianca;
	}
	
	//Para adicionar um jogador
	public void addJogador(Jogador p){
		this.jogadores.add(p);
	}
	
	//Fase de treinamento
	@SuppressWarnings("unchecked")
	public int treinamento(){ //21
		ArrayList<Desenvolvedor> novoEstado = null;
		double qualitdade = 0.0;
		boolean melhorou = false;
		int treinamento = candidatoTreinamento;
		int j = 0;
		for(int i = 0; i < jogadores.size(); i++){
			melhorou = false;
			j = 0;
			do{
				novoEstado = this.modoTreinamento.criarSucessor(this.jogadores.get(i).getGenes(), this.jogadores.get(i).getQualidade(), this.confianca);
				qualitdade = this.funcaoAvaliacao.avaliacao(novoEstado);
				if (qualitdade > jogadores.get(i).getQualidade()){
					jogadores.get(i).setGenes(novoEstado);
					jogadores.get(i).setQualidade(qualitdade);
					melhorou = true;
					j = 0;
				}else{
					j++;
				}
				if(jogadores.get(i).getQualidade() > this.historico){
					this.historico = jogadores.get(i).getQualidade();
				}
			}while(j < (treinamento / (jogadores.size()/2)));//6);
			if (melhorou == false){
				this.jogadores.get(i).setMelhora(this.jogadores.get(i).getMelhora() + 1);
			}else{
				this.jogadores.get(i).setMelhora(0);
				this.jogadores.get(i).setAposentadoria(0);
			}
			
			Collections.sort(this.getJogadores());
		}
		return (int)(treinamento);
	}
	
	public double getHistorico() {
		return historico;
	}

	public void setHistorico(double historico) {
		this.historico = historico;
	}

	public FuncaoSucessoraRandomica getModoTreinamento() {
		return modoTreinamento;
	}

	public void setModoTreinamento(FuncaoSucessoraRandomica modoTreinamento) { //15
		this.modoTreinamento = modoTreinamento;
		this.candidatoTreinamento = this.modoTreinamento.getNeighboor(this.jogadores.get(0).getGenes().size());
		
		//System.out.println(candidatoTreinamento + " é a vizinhança de " + this.getModoTreinamento().toString());
	}

	//Função para calcular a força de cada equipe, para isso faz a média dos 10 melhores jogadores do elenco
	public void calcularForcaEquipe(){ //28
		double media = 0.0;
		for(int i = 0; i < this.getJogadores().size(); i++){
			media = media + this.getJogadores().get(i).getQualidade();
		}
		this.potencia = media/10;
	}
	
	//Função para avaliar todos os jogadores da equipe
	public void avaliacao() { //18
		for(int j = 0; j < this.jogadores.size(); j++){
			this.jogadores.get(j).setQualidade(this.funcaoAvaliacao.avaliacao(this.jogadores.get(j).getGenes()));
		}
	}
	
	//Faça a soma de um número
	public int somatorio(int numero){
		int res = 1;
		while(numero != 0){
			res = res + numero;
			numero--;
		}
		return res;
	}
	
	//Função para classificar listas	
	public int compareTo(Object o){
		Time outro = (Time)o;
		if (this.pontos < outro.pontos) {
			return 1;
		} else if (this.pontos == outro.pontos) {
			return 0;
		} else {
			return -1;
		}
	}
	
}
