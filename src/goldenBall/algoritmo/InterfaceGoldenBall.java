package goldenBall.algoritmo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Observer;

import goldenBall.logica.DataBase;
import goldenBall.logica.ILocalObservable;
import interfacesUsuarioGB.Treinamento;
import util.observer.local.LocalObservable;

/**
 * 
 * @author Kannya
 *
 * Esqueleto del algoritmo
 */

public class InterfaceGoldenBall implements ILocalObservable{

	//Parametros de entrada
	private int numTimes;
	private int numJogadorPorTime;
	private int numTemporada;
	private int temporada = 0;
	private int TreinamPerson = 0;
	private int numTreinamento = 0;
	private int temporadaToGo = 0;
	private int temporadasSemMelhoria = 0;
	private double melhorSolucao = Integer.MIN_VALUE;
	private LocalObservable observable;
	
	//Atributo liga
	private ArrayList<Time> liga;
	
	private Jogador melhorJogador;
	
	//Funções típicas dO algoritmo
	private InicializarPopulacao inicializadorPopulacao;
	private FuncaoAvaliacao funcaoAvaliacao;
	private FuncaoHelp funcaoHelp;
		
	public InterfaceGoldenBall(int tN, int pNFT, int sN, InicializarPopulacao pI, FuncaoAvaliacao fA, FuncaoHelp hFunc){ //10
		this.numTimes = tN;
		this.numJogadorPorTime = pNFT;
		this.inicializadorPopulacao = pI;
		this.funcaoAvaliacao = fA;
		this.funcaoHelp = hFunc;
		observable = new LocalObservable();
		this.addLocalObserver(Treinamento.obtTreinamentos());
	}
	
	//Getters y Setters
	
	public int getTeamsNumber() {
		return numTimes;
	}

	public void setTeamsNumber(int teamsNumber) {
		this.numTimes = teamsNumber;
	}

	public int getNumJogadorPorTime() {
		return numJogadorPorTime;
	}

	public void setNumJogadorPorTime(int numJogadorPorTime) {
		this.numJogadorPorTime = numJogadorPorTime;
	}

	public int getSeasonNumber() {
		return numTemporada;
	}

	public void setSeasonNumber(int seasonNumber) {
		numTemporada = seasonNumber;
	}

	public InicializarPopulacao getPopulationInitializer() {
		return inicializadorPopulacao;
	}

	public void setPopulationInitializer(InicializarPopulacao populationInitializer) {
		this.inicializadorPopulacao = populationInitializer;
	}

	public FuncaoAvaliacao getEvaluationFunction() {
		return funcaoAvaliacao;
	}

	public void setEvaluationFunction(FuncaoAvaliacao evaluationFunction) {
		this.funcaoAvaliacao = evaluationFunction;
	}

	public Jogador getMelhorJogador() {
		return melhorJogador;
	}

	public void setMelhorJogador(Jogador melhorJogador) {
		this.melhorJogador = melhorJogador;
	}

	//Função central, aquela que executa o processo do algoritmo
	@SuppressWarnings("unchecked")
	public Jogador execute(){ //12
		/**
		 * 
		 * FASE INICIAL
		 *
		 */
		//O primeiro é criar a liga, ou seja, criar os jogadores e as equipes, o que será feito com a função de 
		//inicialização, que retornará o conjunto de equipa
		this.temporada = 0;
		this.melhorSolucao = Double.MIN_VALUE;
		this.liga = this.inicializadorPopulacao.inicializePopulacao(this.numTimes,this.numJogadorPorTime, funcaoAvaliacao);
		//Agora vamos avaliar a população, ordenar os jogadores por qualidade e, em seguida, nomeá-los
		this.avaliacao(this.liga);
		for(int i = 0; i < liga.size(); i++){
			Collections.sort(liga.get(i).getJogadores());
		}
		 
		/**
		 * 
		 * FASE INTERMEDIARIA
		 * 
		 */
		//O processo é repetido para cada estação
		int i = 0;
		do{
			//Os pontos de todas as equipes são zerados primeiro
			for(int j = 0; j < liga.size(); j++){
				liga.get(j).setPontos(0);
			}
			//Agora todas as sessões de treinamento e jogos de uma temporada são executados
			double qualidadeAnterior = 0.0;
			double qualidadeCapitaesAnterior = 0.0;
			for(int l = 0; l < liga.size(); l++){
				qualidadeAnterior += liga.get(l).getPotencia(); 
				qualidadeCapitaesAnterior += liga.get(l).getJogadores().get(0).getQualidade();
			}
			if (qualidadeAnterior == 0.0){
				qualidadeAnterior = Double.MIN_VALUE;
				qualidadeCapitaesAnterior = Double.MIN_VALUE;
			}
			
			this.executeTemporada();
			
			double calidadPosterior = 0.0;
			double calidadCapitanesPosterior = 0.0;
			for(int l = 0; l < liga.size();l++){
				calidadPosterior += liga.get(l).getPotencia(); 
				calidadCapitanesPosterior += liga.get(l).getJogadores().get(0).getQualidade();
			}
			
			if(calidadPosterior < qualidadeAnterior || calidadCapitanesPosterior < qualidadeCapitaesAnterior){
				temporadasSemMelhoria = 0;
			}else{
				temporadasSemMelhoria++;
			}
			//Para el observer
			double mejorSolucionAnterior = this.melhorSolucao;
			this.atualizarInterface(i);
			if(this.melhorSolucao < mejorSolucionAnterior){
				temporadasSemMelhoria = 0;
			}
			i++;			
		}while(temporadasSemMelhoria < 1);
		/**
		 * 
		 * FASE FINAL
		 * 
		 */	
		//Este é o ponto final da execução, começa a cerimônia da bola de ouro, na qual o melhor jogador do 
		//campeonato será decorado e é o que é devolvido do processo.
		ArrayList<Double> treinamentos = new ArrayList<Double>();
		treinamentos.add((double)this.TreinamPerson);
		treinamentos.add((double)this.numTreinamento);
		this.notifyLocalObservers(treinamentos);
		System.out.println();
//		System.out.println("Melhor Solução: " + this.goldenBallCerimonia().getQualidade());
		
		return this.goldenBallCerimonia();
	}
	
	private void atualizarInterface(int i) { //33
		this.temporada = i+1;
		double q = Double.MIN_VALUE;
		//Cada equipe é monitorada
		for(int j = 0; j < liga.size(); j++){
			//De cada equipe, cada jogador é rastreado
			if(liga.get(j).getHistorico() > q){
				q = liga.get(j).getHistorico();
			}
		}
		if(q > this.melhorSolucao){
			this.melhorSolucao = q;
			this.temporadaToGo = 0;
		}else{
			this.temporadaToGo++;
		}
		this.notifyLocalObservers(this);
	}

	//Este método executa todas as etapas de uma temporada, sessões de treinamento, jogos e transferências
	@SuppressWarnings("unchecked")
	private void executeTemporada() { //20
		
		for(int vuelta = 0; vuelta < 2; vuelta++){
			for(int time1 = 0; time1 < this.liga.size(); time1++){
				//Dentro de cada semana, a primeira etapa é o treinamento
				for(int i = 0; i < this.liga.size(); i++){
					if(this.numTreinamento == 0){
						this.numTreinamento = this.numTreinamento + this.liga.get(0).treinamento();
					}else{
						this.liga.get(i).treinamento();
					}
					//COM ESTE PARA, PROCURAMOS VER SE O JOGADOR FOI 10 TREINAMENTOS SEM MELHORAR
					for(int j = 0; j < this.liga.get(i).getJogadores().size(); j++){
						//TEM SIDO, ASSIM UMA MUDANÇA É FEITA EM SEUS GENES
						if(this.liga.get(i).getJogadores().get(j).getMelhora() == 10){
							this.liga.get(i).getJogadores().get(j).setMelhora(0);
							this.intercambiarJogador(i, j);
						}else if(this.liga.get(i).getJogadores().get(j).getMelhora() == 5 
								&& this.liga.get(i).getJogadores().get(j).getQualidade() != this.liga.get(i).getJogadores().get(0).getQualidade()){
							this.treinoAssistido(this.liga.get(i).getJogadores().get(0),this.liga.get(i).getJogadores().get(j));
							this.liga.get(i).getJogadores().get(j).setQualidade(this.liga.get(i).getFuncaoAvaliacao().avaliacao(this.liga.get(i).getJogadores().get(j).getGenes()));
							this.TreinamPerson++;
						}
					}
					liga.get(i).calcularForcaEquipe();
				}
				//Depois do confronto
				for(int time2 = time1 + 1; time2 < this.liga.size(); time2++){
					this.partidaRapida(this.liga.get(time1),this.liga.get(time2));
				}
				
			}
			//Agora a classificação é ordenada para saber qual equipe é a vencedora
			Collections.sort(liga);

			//No final de cada rodada, o período de transferência ocorre
			this.transferenciaJogadores();
		}
		
	}

	private void treinoAssistido(Jogador capitao, Jogador jogador) { //30
		jogador.setGenes(this.funcaoHelp.Help(capitao, jogador));	
	}

	private void intercambiarJogador(int i, int j) { //24
		System.out.println("Uma troca de equipe " + this.liga.get(i).getNome() + " para o jogador " + this.liga.get(i).getJogadores().get(j).getNome());
		
		int timeIndice = new Double(Math.random() * this.liga.size()).intValue();
		while(timeIndice == i){
			timeIndice = new Double(Math.random() * this.liga.size()).intValue();
		}
		int jogadorIndice = new Double(Math.random() * this.liga.get(timeIndice).getJogadores().size()).intValue();
		this.liga.get(i).addJogador(this.liga.get(timeIndice).getJogadores().remove(jogadorIndice));
		this.liga.get(timeIndice).addJogador(this.liga.get(i).getJogadores().remove(j));
		
	}
	
	//Método de troca de jogadores
	@SuppressWarnings("unchecked")
	private void transferenciaJogadores() { //29
			
		Jogador p1;
		Jogador p2;
		
		//DEPENDENDO DA POSIÇÃO NA CLASSIFICAÇÃO
		for(int i = 0; i < liga.size()/2; i++){
			p1 = liga.get(liga.size()-(i + 1)).getJogadores().remove(i);
			p2 = liga.get(i).getJogadores().remove(liga.get(1).getJogadores().size()-(i + 1));
			liga.get(i).addJogador(p1);
			liga.get(liga.size()-(i + 1)).addJogador(p2);
		}
		
		//MUDANÇA DE FUNÇÃO DE TREINAMENTO
		for(int i = liga.size()/2; i < liga.size();i++){
			int indice1 = new Double(Math.random() * (DataBase.funcoes.length)).intValue();
			
			System.out.println(DataBase.funcoes[indice1].toString());
			
			liga.get(i).setModoTreinamento(DataBase.funcoes[indice1]);
		}
		
	}

	//Um método de confronto em equipe, neste caso o confronto rápido. Nesta partida existem 10 chances distribuídas igualmente
	//probabilístico para as equipes, baseado na força total de TODA a equipe. Cada ocasião é marcada ou não com base em sua força, então
	//novamente, probabilístico.
	private void partidaRapida(Time time, Time time2) {	//27
		int golsTime1 = 0;
		int golsTime2 = 0;
		//Fazemos um for com 10 porque há 10 jogadores por equipe
		//compara as qualidades dos jogadores
		for(int i = 0; i < time.getJogadores().size(); i++){
			//se a qualidade do jogador do time for menor que o do time2 o time2 marca gol 
			if(time.getJogadores().get(i).getQualidade() < time2.getJogadores().get(i).getQualidade()){
				golsTime2++;
				//se a qualidade do jogador do time for maior que o do time2 o time marca gol 
			}else if (time.getJogadores().get(i).getQualidade() > time2.getJogadores().get(i).getQualidade()){
				golsTime1++;
			}
		}
		//Agora temos os gols das duas equipes, portanto sabemos qual é a equipe vencedora
		if(golsTime1 > golsTime2){
			time.setPontos(time.getPontos()+3);
		}else if(golsTime1 < golsTime2){
			time2.setPontos(time2.getPontos()+3);
		}else{
			time.setPontos(time.getPontos()+1);
			time2.setPontos(time2.getPontos()+1);

		}
		
	}
	
	//Este é o método que é executado no final do processo, é aquele que retorna o melhor jogador
	private Jogador goldenBallCerimonia() { //35
		double q = Double.MIN_VALUE;
		//Cada equipe é monitorada
		for(int i = 0; i < liga.size(); i++){
			//De cada equipe, cada jogador é rastreado
			if(liga.get(i).getHistorico() > q){
				q = liga.get(i).getHistorico();
				this.melhorJogador = liga.get(i).getJogadores().get(0);
			}
		}
		
		return this.melhorJogador;
	}	

	private void avaliacao(ArrayList<Time> times) { //17
		for (int i = 0; i < times.size(); i++) {
			times.get(i).avaliacao();
		}
	}
	
	//Função para controlar o tempo
	public String getTime() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    return sdf.format(cal.getTime());
	}

	public FuncaoHelp getHelpFunction() {
		return funcaoHelp;
	}

	public void setHelpFunction(FuncaoHelp helpFunction) {
		this.funcaoHelp = helpFunction;
	}
	
	public int getTemporada(){
		return this.temporada;
	}
	
	public double getMelhorSolucao(){
		return this.melhorSolucao;
	}
	
	public int getTreinPers(){
		return this.TreinamPerson;
	}

	public int getNumTrein(){
		return this.numTreinamento;
	}
	
    public void addLocalObserver(Observer observer) {
        observable.addObserver(observer);
    }

    public void deleteLocalObserver(Observer observer) {
        observable.deleteObserver(observer);
    }
    
    public void notifyLocalObservers(Object arg) {
        observable.setChanged();
        observable.notifyObservers(arg);
    }
}
