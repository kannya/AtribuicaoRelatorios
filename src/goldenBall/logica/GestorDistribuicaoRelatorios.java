package goldenBall.logica;
import java.util.ArrayList;

/**
 * 
 * @author Kannya
 *
 * Esta é a classe que cuida do gerenciamento de estações (mostrar dados, pesquisar estações ...)
 */

public class GestorDistribuicaoRelatorios{
	
	private ArrayList<Desenvolvedor> l_desenvolvedores;
	private ArrayList<Relatorio> l_relatorios;
//	private Double[][] m;
	
	//Singleton

	private GestorDistribuicaoRelatorios(){
		l_desenvolvedores = new ArrayList<Desenvolvedor>();
		l_relatorios = new ArrayList<Relatorio>();
	}
	private static GestorDistribuicaoRelatorios Gestor = new GestorDistribuicaoRelatorios();
	
	public static GestorDistribuicaoRelatorios getInstancia(){
		return Gestor;
	}

	//metodos getters y setters
	
	public ArrayList<Desenvolvedor> getL_desenvolvedores() {
		return l_desenvolvedores;
	}

	public void setL_desenvolvedores(ArrayList<Desenvolvedor> lDes) {
		this.l_desenvolvedores = lDes;
	}

	public ArrayList<Relatorio> getL_relatorios() {
		return l_relatorios;
	}

	public void setL_relatorios(ArrayList<Relatorio> lRel) {
		this.l_relatorios = lRel;
	}

//	public Desenvolvedor getDesenvolv(double a){
//		Desenvolvedor d = null;
//		for (int i = 0; i < l_desenvolvedores.size(); i++){
//			d = l_desenvolvedores.get(i);
//			if (d.getAfinidade() == a){
//				return d;			
//			}		
//		}
//		return null;
//	}
	
//	public Double[][] getM() {
//		return m;
//	}
//
//	public void setM(Double[][] m) {
//		this.m = m;
//	}	
	
	public Desenvolvedor getDesenv(int id){
		Desenvolvedor d = null;
		for (int i=0; i < l_desenvolvedores.size(); i++){
			d = l_desenvolvedores.get(i);
			if (d.getId()==id){
				return d;			
			}		
		}
		return null;
	}
	
	//metodos
	public void setIdesenv() { //7
		int i1 = 1;
		for (int i = 0; i < this.l_desenvolvedores.size(); i++){
			this.l_desenvolvedores.get(i).setId(i1);
			i1++;
		}			
	}
	
	public void setIdRel() { //7
		int i1 = 1;
		for (int i = 0; i < this.l_relatorios.size(); i++){
			this.l_relatorios.get(i).setId(i1);
			i1++;
		}			
	}
	
	//saber a posição de um desenvolvedor na lista
	public int posicionDesenvolvedor(Desenvolvedor d){
		int posicion = this.l_desenvolvedores.indexOf(d);
		return posicion;
	}
	
	//para adicionar desenvolvedor
	public void agregarDesenv(Desenvolvedor d){
		this.l_desenvolvedores.add(d);
	}
	
	
	public GestorDistribuicaoRelatorios clone() {
		GestorDistribuicaoRelatorios clone = new GestorDistribuicaoRelatorios();
		clone.setL_desenvolvedores(this.l_desenvolvedores);
		for (int i = 0; i < this.l_desenvolvedores.size(); i++) {
			clone.getL_desenvolvedores().set(i, this.l_desenvolvedores.get(i));
		}
		return clone;
	}
}
