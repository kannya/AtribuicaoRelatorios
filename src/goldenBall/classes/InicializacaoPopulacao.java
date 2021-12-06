package goldenBall.classes;

import java.util.ArrayList;

import goldenBall.algoritmo.FuncaoAvaliacao;
import goldenBall.algoritmo.InicializarPopulacao;
import goldenBall.algoritmo.Jogador;
import goldenBall.algoritmo.Time;
import goldenBall.logica.DataBase;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.GestorDistribuicaoRelatorios;
import goldenBall.logica.Relatorio;

public class InicializacaoPopulacao extends InicializarPopulacao{

	private GestorDistribuicaoRelatorios gEst = GestorDistribuicaoRelatorios.getInstancia();
	
	@SuppressWarnings("unchecked")
	public ArrayList<Time> inicializePopulacao(int tN, int pNFT, FuncaoAvaliacao funcaoAvaliacao) { //13
		
		ArrayList<Desenvolvedor> desenvolvedores = gEst.getL_desenvolvedores();
		ArrayList<Relatorio> relatorios = gEst.getL_relatorios();
		ArrayList<Time> liga = new ArrayList<Time>();
		ArrayList<Jogador> jogadoresCriadosRandomicamente = new ArrayList<Jogador>();
		int numDeJogadores = tN * pNFT;
		int JogadorCont = 0;
		
		
		//CRIA JOGADORES RANDOMICAMENTE
		for (int i = 0; i < numDeJogadores; i++) {
			jogadoresCriadosRandomicamente.add(this.criarJogadores(desenvolvedores, relatorios));
		}

		//Criação de equipes
		for(int i = 0; i < tN; i++){

			Time t = new Time();
			//Agora, uma vez que a equipe é criada, os jogadores são adicionados a ela até que o número máximo de jogadores por 
			//equipe seja completado
			JogadorCont = 0;
			while (JogadorCont < pNFT){
				t.addJogador(jogadoresCriadosRandomicamente.remove(0));
				JogadorCont++;
			}
			liga.add(t);
			t.setFuncaoAvaliacao(funcaoAvaliacao);
			t.setModoTreinamento(DataBase.funcoes[i]); //revisar
		}
		//Adição de jogador
		return liga;
	}
	
	//FUNÇÃO PARA GERAR UM JOGADOR ALEATÓRIO
	//para cada relatorio buscar um desenvolvedor para corrigi-lo
	private Jogador criarJogadores(ArrayList<Desenvolvedor> desenv, ArrayList<Relatorio> rel) { //14
		//Índice do desenvolvedor a escolher (valor aleatório)
		int indice;
		ArrayList<Desenvolvedor> path = new ArrayList<Desenvolvedor>();
		
		for(int i = 0; i < rel.size(); i++) {
			indice = new Double(Math.random() * desenv.size()).intValue();
			
			//O desenvolvedor selecionado é adicionado a solucao
			path.add(desenv.get(indice));
		}
		//para definir a geração do novo gerador para 0
		Jogador p = new Jogador(path);	
		return p;
	}
}
