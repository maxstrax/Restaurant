/**
 * 
 */
package F21AS;

import java.util.LinkedList;

/**
 * @author Marios Katsigiannis
 *
 */
public class Waiters {
	LinkedList<Waiter> waiters;
	
	public Waiters() {
		waiters = new LinkedList<Waiter>();
	}
	
	public void addWaiter(Waiter w) {
		waiters.add(w);
	}
	public void deleteWaiter(int index) {
		this.waiters.get(index).fire();
		this.waiters.remove(index);
	}
}
