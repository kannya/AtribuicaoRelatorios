package interfacesUsuarioGB;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

public class Estatistica extends JPanel implements Observer{

	/**
	 * Create the panel.
	 */
	private Estatistica() {		
		try {
			javax.swing.UIManager.setLookAndFeel (javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private static Estatistica est = new Estatistica();

	public static Estatistica obtEstatisticas(){
		return est;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && (arg instanceof ArrayList<?>)){
			ArrayList<Double> l = (ArrayList<Double>) arg;
		}		
	}
}
