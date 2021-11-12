package goldenBall.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import goldenBall.algoritmo.InterfaceGoldenBall;
import goldenBall.algoritmo.Jogador;
import goldenBall.help.HelpFuncao;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.GestorDistribuicaoRelatorios;
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
	private GestorDistribuicaoRelatorios gEst = GestorDistribuicaoRelatorios.getInstancia();
	private Collection<Desenvolvedor> desenvolvedores;
	private Collection<Relatorio> relatorios;
	private Jogador melhorJogador;
	
	//Constructor
	public Problema(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> rel){ //4
		this.preparar(des, rel);
	}
	
	//Getters y Setters
	public InterfaceGoldenBall getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(int tN, int pNFT, int sN) {//9
		this.algoritmo = new InterfaceGoldenBall(tN, pNFT, sN, new InicializacaoPopulacao(), new FuncaoObjetivo(), new HelpFuncao());
	}
	
	public void AfinidadeMatrix() { //6
		Collection<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		for (Desenvolvedor d1 : this.desenvolvedores) {
			if(!desenvolvedores.contains(d1)) {
				Desenvolvedor des = new Desenvolvedor();
				Collection<Relatorio> relatorios = new ArrayList<Relatorio>();
				for (Desenvolvedor d2 : this.desenvolvedores) {
					if (d1.getIdDesenvolvedor() == d2.getIdDesenvolvedor()) {
						Relatorio r = new Relatorio();
						for(Relatorio rel : this.relatorios) {
							if(rel.getIdRelatorio() == d2.getIdRelatorio()) {
								r.setEsforco(rel.getEsforco());
								r.setIdRelatorio(d2.getIdRelatorio());
								r.setAfinidade(d2.getAfinidade());
								relatorios.add(r);
								break;
							}
						}
					}
				}
				
				des = d1;
				des.setIdRelatorio(0);
				des.setAfinidade(0);
				des.setRelatorios(relatorios);
				if(!desenvolvedores.contains(des)) {
					desenvolvedores.add(des);
				}
			}
		}
			
		
		this.desenvolvedores = desenvolvedores;
	}
	
	public void preparar(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> rel){ //5
		try {
			this.desenvolvedores = des;
			this.relatorios = rel;
			this.AfinidadeMatrix();
			gEst.setL_desenvolvedores((ArrayList<Desenvolvedor>) this.desenvolvedores);
			gEst.setL_relatorios((ArrayList<Relatorio>) this.relatorios);
			gEst.setIdesenv();
			gEst.setIdRel();
		}finally {
			
		}
	}
	
	public Jogador execute() { //11

		this.melhorJogador = this.algoritmo.execute();
		
//		System.out.println("O melhor jogador tem uma qualidade de " + this.melhorJogador.getQualidade());
		
//		System.out.println("Melhor Jogador: " + this.melhorJogador.getGenes());
		
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
