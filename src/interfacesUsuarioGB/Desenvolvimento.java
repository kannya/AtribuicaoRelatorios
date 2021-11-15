package interfacesUsuarioGB;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import goldenBall.algoritmo.InterfaceGoldenBall;

public class Desenvolvimento extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	private Desenvolvimento() {
		
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
	
	private static Desenvolvimento des = new Desenvolvimento();

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && (arg instanceof InterfaceGoldenBall)){
			InterfaceGoldenBall GB = (InterfaceGoldenBall)arg;
		}
		
	}
}
