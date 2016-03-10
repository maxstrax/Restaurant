/**
 * 
 */
package F21AS;

import java.util.LinkedList;

/**
 * @author Marios Katsigiannis
 *
 */
public abstract class Observable {
	private LinkedList<Observer> listeners = new LinkedList<Observer>();
	public void addObserver(Observer l) {
		listeners.add(l);
	}
	protected void invokeAll(int reason, Object data) {
		for(Observer l : listeners)
			l.invoke(reason, data);
	}
	public void invokeAllNoReason() {
		invokeAll(-1, null);
	}
}
