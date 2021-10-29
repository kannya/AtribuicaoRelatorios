package goldenBall.funcoesSucessorasRandomicas;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.DataBase;
import goldenBall.logica.Desenvolvedor;

public class FuncaoSucessora_Opt2 extends FuncaoSucessoraRandomica{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> estado, double custo, double confianca) { //22
		int indice1 = 0;
		int indice2 = 0;
		int indice3 = 0;
		int indice4 = 0;
		int i = 0;
		int j = 0;
		int aux = 0;
		ArrayList<Desenvolvedor> estadoInicial = (ArrayList<Desenvolvedor>)estado.clone();
		ArrayList<Desenvolvedor> estadoFinal = new ArrayList<Desenvolvedor>();	
		
//		System.out.println("Estado inicial: " + estadoInicial.toString());
		
		//GERAMOS OS DOIS NÚMEROS ALEATÓRIOS
		i = new Double(Math.random() * (estadoInicial.size())).intValue();
		
		j = new Double(Math.random() * (estadoInicial.size())).intValue();
		
//		if(estadoInicial.size() >= 4) {
//			while(j == i || j == i-1 || j == i+1){
//				j = new Double(Math.random() * (estadoInicial.size())).intValue();
//			}
//		} else {
			while(j == i) {
				j = new Double(Math.random() * (estadoInicial.size())).intValue();
			}
//		}
		
		if(i == 0 && j == estado.size()-1){
			j = j-1;
		}
		//ORDENAMOS OS INDICES
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}
//		System.out.println(i + " - " + j + " Tamanho: " + estado.size());
		
		//OBTEMOS O PRIMEIRO DESENVOLVEDOR
		indice1 = i;
		indice2 = i + 1;
		if(i == estadoInicial.size()-1){
			indice2 = 0;
		}else{
			indice2 = i + 1;
		}
		
		//OBTENEMOS OSEGUNDO DESENVOLVEDOR		
		indice3 = j;		
		if(j == estadoInicial.size()-1){
			indice4 = 0;
		}else{
			indice4 = j + 1;
		}

		//A MUDANÇA É FEITA
		i = 0;
		while(i <= indice1){
			estadoFinal.add(estadoInicial.get(i));
			i++;
		}
//		System.out.println("Então o assunto vai por agora: " + estadoFinal.toString());
		i = indice3;
		while(i > indice1){
			estadoFinal.add(estadoInicial.get(i));
			i--;
		}
		
//		System.out.println("Então, o tema continua por enquanto: " + estadoFinal.toString());
		
		i = indice3 + 1;
		while(i < estadoInicial.size()){
			estadoFinal.add(estadoInicial.get(i));
			i++;
		}
		
//		System.out.println("Então, o tópico permaneceu: " + estadoFinal.toString());
//		System.out.println("Tamanho final: " + estadoFinal.size());
		
		return estadoFinal;
				
	}

	@Override
	public int getNeighboor(int instanciaTam) { //16
		return ((instanciaTam - 3) + (DataBase.somatorio(instanciaTam-3)));
	}
}
