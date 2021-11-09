package genetico;

import java.util.ArrayList;

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
	
	public void proxima_geracao_torneio(){
		Individuo prox_populacao[] = new Individuo[quantidade];
		SelecaoTorneio torneio = new SelecaoTorneio(populacao, relatorios, dados);
		int i;
		
		prox_populacao[0] = torneio.get_mais_apto(populacao);
		
		for(i = 1; i < quantidade; i++){
			prox_populacao[i] = new Individuo(torneio.torneio(), dados);
		}
		
		populacao = prox_populacao;
	}	
	
	public Individuo[] proxima_geracao_roleta(){
		Individuo prox_populacao[] = new Individuo[quantidade];
		SelecaoRoleta roleta = new SelecaoRoleta(populacao, relatorios, dados);
		prox_populacao = roleta.selecao();
				
		populacao = prox_populacao;
		
		return prox_populacao;
		
	}

}
