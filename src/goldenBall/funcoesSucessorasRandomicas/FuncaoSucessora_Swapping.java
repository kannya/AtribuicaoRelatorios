package goldenBall.funcoesSucessorasRandomicas;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoSucessoraRandomica;
import goldenBall.logica.DataBase;
import goldenBall.logica.Desenvolvedor;

public class FuncaoSucessora_Swapping extends FuncaoSucessoraRandomica{
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desenvolvedor> criarSucessor(ArrayList<Desenvolvedor> estado, double custo, double confianca) {
		//INICIALIZAMOS OS 2 INDICES
		int indice1;
		int indice2;
		int aux;
		Desenvolvedor est1;
		Desenvolvedor est2;
		ArrayList<Desenvolvedor> estadoInicial;
		
		//OS DOIS ÍNDICES SÃO GERADOS PARA FAZER A TROCA E O NOVO ESTADO É REGENERADO
		estadoInicial = (ArrayList<Desenvolvedor>)estado.clone();
		indice1 = 0;
		indice2 = 0;
		indice1 = new Double(Math.random() * (estadoInicial.size())).intValue();
		indice2 = new Double(Math.random() * (estadoInicial.size())).intValue();
		
		//Isso é feito para que os índices não sejam os mesmos
		if (estadoInicial.size() > 1) {
			while(indice1 == indice2){
				indice2 = new Double(Math.random() * (estadoInicial.size())).intValue();
			}
		}
		//isso ordena os índices de forma que o índice1 seja sempre menor que o índice2
		if (indice1 > indice2){
			aux = indice1;
			indice1 = indice2;
			indice2 = aux;
		}
	
		//AGORA A TROCA É REALIZADA
		if (estadoInicial.size() > 1) {
			est2 = estadoInicial.remove(indice2);
			est1 = estadoInicial.remove(indice1);
			estadoInicial.add(indice1, est2);
			estadoInicial.add(indice2, est1);
		}
		
		return estadoInicial;
	}

	@Override
	public int getNeighboor(int instanciaTam) {
		return DataBase.somatorio(instanciaTam-1);
	}	
}
