package goldenBall.algoritmo;

import java.util.ArrayList;

/**
 * 
 * @author Maldini
 *
 * Essa é a classe que vai armazenar todos os times da liga, não pode ser usada, mas por enquanto deixo
 */
public class Liga {
	
	private ArrayList<Time> times;
	
	//Singleton
	private Liga(){
		times = new ArrayList<Time>();
	}
	private static Liga liga = new Liga();
	
	public static Liga getLiga(){
		return liga;
	}

	public ArrayList<Time> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Time> times) {
		times = times;
	}
	
	//Para buscar equipos por nombre
	public Time getTimes(String d){
		Time t = null;
		for (int i = 0; i < times.size(); i++){
			t = times.get(i);
			if (t.getNome().equals(d)){
				return t;			
			}		
		}
		return null;
	}

}
