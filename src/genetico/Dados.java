package genetico;

import java.util.ArrayList;
import java.util.Collection;

import goldenBall.logica.Desenvolvedor;
import goldenBall.logica.Relatorio;

public class Dados {
	
	ArrayList<Desenvolvedor>  desenvolvedores;
	
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
