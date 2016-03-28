/**
 * 
 */
package F21AS;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marios Katsigiannis
 *
 */
public class Waiters extends Observable {
	private LinkedList<Waiter> waiters;
	
	public static final int WAITER_ADDED = 0;
	public static final int WAITER_REMOVED = 1;
	public static final int WAITER_ACTIVATED = 2;
	
	private ReentrantLock datalock;
	
	public Waiters() {
		this.waiters = new LinkedList<Waiter>();
		this.datalock = new ReentrantLock(true);
	}
	
	public void addWaiter(Waiter w) {
		datalock.lock();
		this.waiters.add(w);
		datalock.unlock();
		this.invokeAll(WAITER_ADDED, w);
	}
	public void deleteWaiter(int index) {
		Waiter w = this.waiters.get(index);
		w.fire();
		datalock.lock();
		this.waiters.remove(index);
		datalock.unlock();
		this.invokeAll(WAITER_REMOVED, w);
	}
	public int size() {
		return this.waiters.size();
	}
	public Waiter getWaiter(int index) {
		if(this.waiters.size() <= index)
			throw new ArrayIndexOutOfBoundsException();
		return this.waiters.get(index);
	}
	public void addWaiterToManager(ThreadManager manager, int index, boolean start) {
		if(index >= this.waiters.size())
			throw new ArrayIndexOutOfBoundsException();
		Waiter w = this.waiters.get(index);
		manager.add(w, start);
		this.invokeAll(WAITER_ACTIVATED, w);
	}
	public void addAllToManager(ThreadManager manager, boolean start) {
		for(int i=0; i<this.waiters.size(); i++)
			this.addWaiterToManager(manager, i, start);
	}
}
