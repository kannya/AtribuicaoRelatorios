package goldenBall.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import genetico.Dados;
import goldenBall.algoritmo.InterfaceGoldenBall;
import goldenBall.algoritmo.Jogador;
import goldenBall.help.HelpFuncao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

/**
 * 
 * @author Maldini
 *
 * Ponto de partida e inicialização dos parâmetros do algoritmo
 */

public class Problema {

	/**
	 * @param args
	 */
	private InterfaceGoldenBall algoritmo;
	private Jogador melhorJogador;
	
	//Constructor
	public Problema(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> rel){ //4
		Dados dados = new Dados(des, rel);
	}
	
	//Getters y Setters
	public InterfaceGoldenBall getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(int tN, int pNFT, int sN) {//9
		this.algoritmo = new InterfaceGoldenBall(tN, pNFT, sN, new InicializacaoPopulacao(), new FuncaoObjetivo(), new HelpFuncao());
	}
	
	public Jogador execute() { //11
		this.melhorJogador = this.algoritmo.execute();
		return this.melhorJogador;
	}
	
	public String getTime() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    return sdf.format(cal.getTime());
	}
	
	//(temporadas, times, jogadores)
	public Jogador executarInstancia(int t, int tm, int p){ //8
		this.setAlgoritmo(tm, p, t);
		return this.execute();
	}

}
