/**
 * 
 */
package F21AS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Marios Katsigiannis
 * This class will be used to deliver the dishes to the tables from the kitchen and to the kitchen
 * from the orders!
 */
public class Waiter {
	private orderItem order;
	private Boolean fromKitchen; //true = to table, false = to kitchen
	private Kitchen kitchen;
	private orders target;
	private static long workingTime = 1000L; //1 second per operation
	Lock datalock, workinglock;
	public Waiter(Kitchen kitchen) {
		this.kitchen = kitchen;
		this.freeUp();
		datalock = new ReentrantLock();
		workinglock = new ReentrantLock();
	}
	//ensures that 
	private void freeUp() {
		workinglock.lock(); //ensure that 
		datalock.lock();
		order = null;
		fromKitchen = null;
		target = null;
		datalock.unlock();
		workinglock.unlock();
	}
	public boolean isFree() {
		return this.order != null;
	}
	public boolean placeOrder(orderItem order) {
		datalock.lock(); //used only to ensure that the waiter will be free, when called
		if(this.isFree()) {
			datalock.unlock();
			return false;
		}	
		this.order = order;
		datalock.unlock(); //the isFree will now return false, thus no need to keep the lock anymore
		this.fromKitchen = false;
		this.target = null;
		return true;
	}
	public boolean serveOrder(orderItem order, orders target) {
		datalock.lock(); //used only to ensure that the waiter will be free, when called
		if(this.isFree()) {
			datalock.unlock();
			return false;
		}	
		this.order = order;
		datalock.unlock(); //the isFree will now return false, thus no need to keep the lock anymore
		this.fromKitchen = true;
		this.target = target;
		return true;
	}
	private void slackALittle() {
		try {
			Thread.sleep(Waiter.workingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean performCurrentOperation() {
		workinglock.lock();
		if(this.isFree()) {
			workinglock.unlock();
			return false;
		}
		slackALittle();
		if(this.fromKitchen)
			this.kitchen.addOrder(this.order);
		else
			this.target.addItem(this.order);
		this.freeUp();
		workinglock.unlock();
		return true;
	}
}
