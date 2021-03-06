package goldenBall.funcoesSucessorasRandomicas;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.DataBase;
import goldenBall.logica.Desenvolvedor;


public class FuncaoSucessora_Opt3 extends FuncaoSucessoraRandomica{

	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> estado, double custo, double confianca) { //26
		int indice1 = 0;
		int indice2 = 0;
		int indice3 = 0;
		int indice4 = 0;
		int indice5 = 0;
		int indice6 = 0;
		int indice7 = 0;
		int indice8 = 0;
		int indice9 = 0;
		int indice10 = 0;
		int indice11 = 0;
		int indice12 = 0;
		int i;
		int k;
		int j;
		int aux;
		ArrayList<Desenvolvedor> estadoInicial = (ArrayList<Desenvolvedor>)estado.clone();
		ArrayList<Desenvolvedor> estadoFinal = new ArrayList<Desenvolvedor>();	
		
		//OBTEMOS O PRIMEIRO E SEGUNDO INDICE
		i = new Double(Math.random() * (estadoInicial.size())).intValue();		
		j = new Double(Math.random() * (estadoInicial.size())).intValue();		

		while(j == i || j == i-1 || j == i+1){
			j = new Double(Math.random() * (estadoInicial.size())).intValue();
		}

		//OS ORDENAMOS		
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}
		//OBTEMOS O TERCEIRO INDICE
		k = new Double(Math.random() * (estadoInicial.size())).intValue();		
		while(k == i || k == i-1 || k == i+1 || k == j || k == j-1 || k == j+1){
			k = new Double(Math.random() * (estadoInicial.size())).intValue();
		}
		
		//ORDENAMOS TODOS OS INDICES
		if (k < j){
			aux = j;
			j = k;
			k = aux;
		}		
		if (j < i){
			aux = i;
			i = j;
			j = aux;
		}		
		if(i == 0 && k == estado.size()-1){
			k = k-1;
		}
		
		//OBTEMOS A PRIMERA ARESTA
		indice1 = i;
		if(i == estadoInicial.size()-1){
			indice2 = 0;
		}else{
			indice2 = i + 1;
		}
		
		//OBTEMOS A SEGUNDA ARESTA
		indice3 = j;
		if(j == estadoInicial.size()-1){
			indice4 = 0;
		}else{
			indice4 = j + 1;
		}
		
		//OBTEMOS A TERCEIRA ARESTA
		indice5 = k;
		if(k == estadoInicial.size()-1){
			indice6 = 0;
		}else{
			indice6 = k + 1;
		}
		
		//A TROCA DE ARCO ?? FEITA
		int l = new Double(Math.random() * 3).intValue();
		
		if(l == 0){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				estadoFinal.add(estadoInicial.get(i));
				i++;
			}
			
			i = indice3;
			while(i >= indice8){
				estadoFinal.add(estadoInicial.get(i));
				i--;
			}
			
			i = indice11;
			while(i >= indice10){
				estadoFinal.add(estadoInicial.get(i));
				i--;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i<estadoInicial.size()){
					estadoFinal.add(estadoInicial.get(i));
					i++;
				}
			}
		
		}else if(l == 1){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				estadoFinal.add(estadoInicial.get(i));
				i++;
			}
			
			i = indice10;
			while(i <= indice11){
				estadoFinal.add(estadoInicial.get(i));
				i++;
			}
			
			i = indice9;
			while(i >= indice8){
				estadoFinal.add(estadoInicial.get(i));
				i--;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i<estadoInicial.size()){
					estadoFinal.add(estadoInicial.get(i));
					i++;
				}
			}
			
		}else if(l == 2){
			indice7 = indice1;
			indice8 = indice2;
			indice9 = indice3;
			indice10 = indice4;
			indice11 = indice5;
			indice12 = indice6;
			i = 0;
			while(i <= indice7){
				estadoFinal.add(estadoInicial.get(i));
				i++;
			}
			
			i = indice11;
			while(i >= indice10){
				estadoFinal.add(estadoInicial.get(i));
				i--;
			}
			
			i = indice8;
			while(i <= indice9){
				estadoFinal.add(estadoInicial.get(i));
				i++;
			}
			
			if (indice12 != 0){
				i = indice12;
				while(i < estadoInicial.size()){
					estadoFinal.add(estadoInicial.get(i));
					i++;
				}
			}
		}
		
//		System.out.println("estado inicial");
//		for (int cont = 0; cont < estadoInicial.size(); cont++) {
//			System.out.print(estadoInicial.get(cont) + "; ");
//		}
//		
//		System.out.println("estado final");
//		for (int cont1 = 0; cont1 < estadoFinal.size(); cont1++) {
//			System.out.print(estadoFinal.get(cont1) + "; ");
//		}
		
		return estadoFinal;
	}

	@Override
	public int getNeighboor(int instanciaTam) {
		return ((instanciaTam - 3) + (DataBase.somatorio(instanciaTam-3)));
	}
}
