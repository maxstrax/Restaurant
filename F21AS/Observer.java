/**
 * 
 */
package F21AS;

/**
 * @author Marios Katsigiannis
 * This interface specifies the function used by the observable object to inform the observers
 * about a state change in the observer
 */
public interface Observer {
	/**
	 * The function used by an observable to inform the observer about a change in the observable's
	 * state
	 * @param reason the id of the state change occured in the observable
	 * @param data the data that has been modified
	 */
	public void invoke(int reason, Object data);
}
