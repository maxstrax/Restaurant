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
	public void addWaiterToManager(ThreadManager manager, int index, boolean start) {
		if(index >= this.waiters.size())
			throw new ArrayIndexOutOfBoundsException();
		manager.add(this.waiters.get(index), start);
	}
	public void addAllToManager(ThreadManager manager, boolean start) {
		for(int i=0; i<this.waiters.size(); i++)
			this.addWaiterToManager(manager, i, start);
	}
}
