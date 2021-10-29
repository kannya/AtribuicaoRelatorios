package goldenBall.logica;
import java.util.Observer;
/**
 * 
 * @author Maldini
 *
 * Esta é uma classe que serve para emular herança múltipla em java.
 */

public interface ILocalObservable{

    public void addLocalObserver(Observer observer);
    public void deleteLocalObserver(Observer observer);   
    public void notifyLocalObservers(Object arg);
	
}
