package genetico;

import java.util.ArrayList;
import java.util.Collection;

import goldenBall.classes.FuncaoObjetivo;
import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Dados {
	
	// estrutura de dados para representar as cidades
	// um dicionario onde a chave AB -> distancia 
	// entre a cidade A e B e o valor seria a distancia
	
	ArrayList<Desenvolvedor>  desenvolvedores;
	
	
	String possiveis;
	int quantidade;
	
	
	// recebe a letra das duas ciadades e retorna a distancia delas
//	public Double get_distancia(String chave){
//		Double t;
//		if(cidades.containsKey(chave)){
//			t = cidades.get(chave);
//			assert(t != null);
//			return t;
//		} else{
//			// o valor deve estar invertido AB -> BA
//			String tmp = Utils.at(chave, 1) + Utils.at(chave, 0);
//			t = cidades.get(tmp);
//			assert(t != null);// -ea deve ser passado como parametro para vm
//			return t;
//		}
//	}
	
	
	public Dados(ArrayList<Desenvolvedor> des, ArrayList<Relatorio> listaRel){
		
    	ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<Desenvolvedor>();
		for (Desenvolvedor d1 : des) {
			Desenvolvedor desenvolvedor = new Desenvolvedor();
			Collection<Relatorio> relatorios = new ArrayList<Relatorio>();
			for (Desenvolvedor d2 : des) {
				if (d1.getIdDesenvolvedor() == d2.getIdDesenvolvedor()) {
					Relatorio r = new Relatorio();
					for(Relatorio rel : listaRel) {
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
			
			desenvolvedor = d1;
			desenvolvedor.setIdRelatorio(0);
			desenvolvedor.setAfinidade(0);
			desenvolvedor.setRelatorios(relatorios);
			if(!desenvolvedores.contains(desenvolvedor)) {
				desenvolvedores.add(desenvolvedor);
			}
		}
		
		this.desenvolvedores = desenvolvedores;
	
	}
	
	
	public ArrayList<Desenvolvedor> getDesenvolvedores() {
		return desenvolvedores;
	}

	public void setDesenvolvedores(ArrayList<Desenvolvedor> desenvolvedores) {
		this.desenvolvedores = desenvolvedores;
	}
	
	
	
}
