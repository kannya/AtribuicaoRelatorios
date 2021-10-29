package goldenBall.logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import goldenBall.algoritmo.Jogador;
import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.funcoesSucessorasRandomicas.*;

/**
 * 
 * @author Kannya
 *
 * Esta é a classe da qual se obtem as funções de treinamento
 */

public class DataBase {
	
	public static FuncaoSucessoraRandomica[] funcoes = {
		new FuncaoSucessora_Opt2(),
		new FuncaoSucessora_Opt3(),
		new FuncaoSucessora_3optAnd2opt(),
		new FuncaoSucessora_RandomInsertion(),
		new FuncaoSucessora_3optAndInsertion(),
		new FuncaoSucessora_InsertionAnd2opt(),
		new FuncaoSucessora_Swapping(),
		new FuncaoSucessora_RandomAndSwappingInsertion(),
		new FuncaoSucessora_Opt2(),
		new FuncaoSucessora_Opt3(),
		new FuncaoSucessora_3optAnd2opt(),
		new FuncaoSucessora_RandomInsertion(),
		new FuncaoSucessora_3optAndInsertion(),
		new FuncaoSucessora_InsertionAnd2opt(),
		new FuncaoSucessora_Swapping(),
		new FuncaoSucessora_RandomAndSwappingInsertion(),
		new FuncaoSucessora_Opt2(),
		new FuncaoSucessora_Opt3(),
		new FuncaoSucessora_3optAnd2opt(),
		new FuncaoSucessora_RandomInsertion(),
		new FuncaoSucessora_3optAndInsertion(),
		new FuncaoSucessora_InsertionAnd2opt(),
		new FuncaoSucessora_Swapping(),
		new FuncaoSucessora_RandomAndSwappingInsertion(),
		new FuncaoSucessora_Opt2(),
		new FuncaoSucessora_Opt3(),
		new FuncaoSucessora_3optAnd2opt(),
		new FuncaoSucessora_RandomInsertion(),
		new FuncaoSucessora_3optAndInsertion(),
		new FuncaoSucessora_InsertionAnd2opt(),
		new FuncaoSucessora_Swapping(),
		new FuncaoSucessora_RandomAndSwappingInsertion(),
	};
	
	public static String getTime() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    return sdf.format(cal.getTime());
	}
	private static Random gerador = new Random();
	
	public static Jogador getJogador(ArrayList<Jogador> array) {
        int rnd = gerador.nextInt(array.size());
        return array.get(rnd);
    }
	
	public static int somatorio(int num) {
		int res = 1;
		while(num != 0){
			res = res + num;
			num--;
		}
		return res;
	}

}
