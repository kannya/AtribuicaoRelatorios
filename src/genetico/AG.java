package genetico;

import java.util.ArrayList;

import goldenBall.classes.FuncaoObjetivo;
import goldenBall.dao.DesenvolvedorDao;
import goldenBall.dao.RelatorioDao;
import goldenBall.dao.RodadaAtualDao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class AG {
	public Individuo[] populacao;
	Dados dados;
	int quantidade = 40;

	ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	public double melhor_Qlde = Double.MIN_VALUE;
	//private double max  = Double.MIN_VALUE;
	public ArrayList<Desenvolvedor> melhorSolucao = new ArrayList<Desenvolvedor>();
	
	public AG() {
		
		DesenvolvedorDao dao = new DesenvolvedorDao();
		RelatorioDao relDao = new RelatorioDao();
		RodadaAtualDao rodadaAtualDao = new RodadaAtualDao();
		
		try {
			desenvolvedores = dao.listaDesenvolvedores(rodadaAtualDao.buscaRodadaAtual());
			relatorios = relDao.listaRelatorios();		
		}finally {
			
		}
		populacao = new Individuo[quantidade];
		dados = new Dados(desenvolvedores, relatorios);
		
		// inicializa o array de Individuo
		for(int i = 0; i < quantidade; i++){
			populacao[i] = new Individuo(relatorios.size(), dados);
		}
	}
	
	//retorna o individuo mais apto
	public Individuo get_mais_apto(Individuo lst[]){
		//indice do individuo mais apto
		int indice = get_indice_mais_apto(lst);
		
		//individuo mais apto
		return lst[indice];		
	}
	
	//retorna o indice do mais apto
	public int get_indice_mais_apto(Individuo lst[]) {
		int indice_melhor_solucao = -1;
		int indice = 0;
		double qlde = Double.MIN_VALUE;
		double max  = Double.MIN_VALUE;
		ArrayList<Desenvolvedor> melhorSolLocal = new ArrayList<Desenvolvedor>();
		
		for (Individuo p : lst) {
			
			FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo();
			ArrayList<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();
			if(p != null) {
				for (int i = 0; i < relatorios.size(); i++) {
					for (Desenvolvedor d : this.dados.getDesenvolvedores()) {
						if(p.genes[i] == d.getIdDesenvolvedor()) {
							solLocal.add(d);
							break;
						}
					}
//					System.out.print(p.genes[i] + " ");
				}
			}
//			System.out.print("\n");
		
			qlde = funcaoObjetivo.avaliacao(solLocal);
	
			if(qlde > max){
				max = qlde;
				melhorSolLocal = solLocal;
				indice_melhor_solucao = indice;
			}
			indice ++;
		}
		if( max > melhor_Qlde) {
			melhor_Qlde = max;
			this.melhorSolucao = melhorSolLocal;
		}
		return indice_melhor_solucao;
	}

	// retorna dois individuos
	public Individuo[] torneio(){
		int qnt_torneio = relatorios.size() + 1;
		Individuo to_return[] = new Individuo[2];
		Individuo lst[] = new Individuo[qnt_torneio];
		
		// seleciona qnt_torneio Individuos aleatoriamente
		for(int i = 0; i < qnt_torneio; i++){
			lst[i] = populacao[new Double(Math.random() * quantidade).intValue()];
		}
		
		// seleciona os dois mais aptos para retornar
		
		int i1;
		i1 = get_indice_mais_apto(lst);
		to_return[0] = populacao[i1];
		lst[i1] = null;
		
		to_return[1] = get_mais_apto(lst);
		
		return to_return;
	}
	
	// retorna dois individuos
		public Individuo[] roleta(){
			
			
			return null;
		}
	
	public void proxima_geracao(){
		Individuo prox_populacao[] = new Individuo[quantidade];
		int i;
		
		prox_populacao[0] = get_mais_apto(populacao);
		
		for(i = 1; i < quantidade; i++){
			prox_populacao[i] = new Individuo(torneio(), dados);
		}
		
		populacao = prox_populacao;
	}	
}
