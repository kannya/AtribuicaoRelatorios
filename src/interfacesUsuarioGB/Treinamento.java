package interfacesUsuarioGB;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Treinamento extends JPanel implements Observer{
	private int execucao = 0;
	private double mediaTreinamentos = 0.0;
	private double mediaTreinamentosPersonalizados = 0.0;

	/**
	 * Create the panel.
	 */
	private Treinamento() {
		
	}
	
	private static Treinamento treinamentos = new Treinamento();
	public static Treinamento obtTreinamentos(){
		return treinamentos;
	}
	public void reiniciarValores(){
		this.mediaTreinamentos = 0.0;
		this.mediaTreinamentosPersonalizados = 0.0;
		this.execucao = 0;
	}
	@Override
	public void update(Observable arg0, Object arg) { //34
		if (arg != null && (arg instanceof ArrayList<?>)){
			this.execucao++;
			ArrayList<Double> l = (ArrayList<Double>) arg;
			this.mediaTreinamentosPersonalizados = this.mediaTreinamentosPersonalizados + l.get(0);
			this.mediaTreinamentos = this.mediaTreinamentos + l.get(1);
		}
		
	}
}
