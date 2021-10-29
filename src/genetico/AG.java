package genetico;

import java.util.ArrayList;
import java.util.Collection;

import goldenBall.algoritmo.Jogador;
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
//	int qnt_cidades = 8;
	ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
	ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
	private double qlde = Double.MIN_VALUE;
	private double max  = Double.MIN_VALUE;
	ArrayList<Desenvolvedor>   melhorSolucao = new ArrayList<Desenvolvedor>();
	
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
		int i;
		for(i=0; i<quantidade; i++){
			populacao[i] = new Individuo(relatorios.size(), dados);
		}
	}
	
	public void calcularQualidade(int[] genes2, int qtdeRel) {

		FuncaoObjetivo funcaoObjetivo = new FuncaoObjetivo();
		Collection<Desenvolvedor> solLocal = new ArrayList<Desenvolvedor>();

		for (int i = 0; i < qtdeRel; i++) {
			for (Desenvolvedor d : this.dados.getDesenvolvedores()) {
				if(genes2[i] == d.getIdDesenvolvedor()) {
					solLocal.add(d);
					break;
				}
			}
			System.out.print(genes2[i] + " ");
			
		}
		System.out.print("\n");
		
		qlde = funcaoObjetivo.avaliacao(solLocal);

		if(qlde > max){
			max = qlde;
			this.melhorSolucao = (ArrayList<Desenvolvedor>) solLocal;
		}
	}
	
	public void get_mais_apto(Individuo lst[]){
		
		for(int i=0; i<lst.length; i++){
			
			calcularQualidade(this.populacao[i].genes, relatorios.size());		
		}
		
	
		
		
	}
	
	// fazer
	
	// retorna dois individuos
	public Individuo[] torneio(){
		int qnt_torneio = 9;
		Individuo to_return[] = new Individuo[2];
		Individuo lst[] = new Individuo[qnt_torneio];
		
		int i;
		// seleciona qnt_torneio Individuos aleatoriamente
		for(i=0; i<qnt_torneio; i++){
			lst[i] = populacao[Utils.rand(quantidade)];
		}
		
		// seleciona os dois mais aptos para retornar
		
		int i1;
		i1 = get_indice_mais_apto(lst);
		to_return[0] = populacao[i1];
		lst[i1] = null;
		
		to_return[1] = get_mais_apto(lst);
		
		return to_return;
		
	}
	
	public void proxima_geracao(){
		Individuo prox_populacao[] = new Individuo[quantidade];
		int i;
		// preserva o mais apto
		Individuo in [];
		int j = 0;
		for (Desenvolvedor d : melhorSolucao) {
			
		}
		prox_populacao[0] = melhorSolucao;
		
		for(i=1; i<quantidade; i++){
			prox_populacao[i] = new Individuo(torneio(), dados);
		}
		
		populacao = prox_populacao;
		
	}
	
	
}
