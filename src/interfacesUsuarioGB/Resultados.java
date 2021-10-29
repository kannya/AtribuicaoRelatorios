package interfacesUsuarioGB;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Resultados extends JPanel implements Observer{

	public JTextArea txtrEnEstePanel;
	/**
	 * Create the panel.
	 */
	private Resultados() {
		
	}
	
	private static Resultados res = new Resultados();
	public static Resultados obtResultados(){
		return res;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
