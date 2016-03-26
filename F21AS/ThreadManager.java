/**
 * 
 */
package F21AS;

import java.util.LinkedList;

/**
 * @author Marios Katsigiannis
 *
 */
public class ThreadManager {
	private LinkedList<Thread> threads;
	
	public ThreadManager() {
		threads = new LinkedList<Thread>();
	}
	
	public void add(Runnable runnable, boolean Start) {
		threads.add(new Thread(runnable));
		if(Start)
			threads.get(threads.size() - 1).start();
	}
	public void remove(int index) {
		if(index >= this.threads.size())
			throw new ArrayIndexOutOfBoundsException();
		this.threads.remove(index);
	}
	public int size() {
		return this.threads.size();
	}
	public void runAll() {
		for(Thread t : this.threads)
			if(!t.isAlive())
				t.start();
	}
	public void run(int index) {
		if(index >= this.threads.size())
			throw new ArrayIndexOutOfBoundsException();
		Thread t = this.threads.get(index);
		if(!t.isAlive())
			t.start();
	}
	public void waitAll() {
		for(Thread t : this.threads)
			if(t.isAlive())
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	}
}
