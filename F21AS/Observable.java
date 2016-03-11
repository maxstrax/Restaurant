/**
 * 
 */
package F21AS;

import java.util.LinkedList;

/**
 * @author Marios Katsigiannis
 * This class is used to provide the interface and the implementation for being observed
 * by other objects that implement the Observer interface.
 */
public abstract class Observable {
	/**
	 * The list of observers that will be invoked to inform them of a change in the state of the observable
	 */
	private LinkedList<Observer> observers = new LinkedList<Observer>();
	/**
	 * Registers the observer for invokation whenever a state change occurs in the observable
	 * @param o the observer to be informed of the state change
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	/**
	 * Invokes all the registered observers in order to inform each of them for a state change in 
	 * the observable
	 * @param reason the id of the change
	 * @param data the data that has changed
	 */
	protected void invokeAll(int reason, Object data) {
		for(Observer o : observers)
			o.invoke(reason, data);
	}
	/**
	 * Invokes all the registered observers for multiple changes that occurred outside of the
	 * control of the observable, that affect the state of the observable.
	 * This function can be called by other objects.
	 */
	public void invokeAllNoReason() {
		invokeAll(-1, null);
	}
}
