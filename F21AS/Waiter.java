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
	private orders target;
	private long workingTime = 1000L; //1 second per operation
	private Lock datalock, workinglock;
	
	public long getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(long workingTime) {
		this.workingTime = workingTime;
	}
	public orderItem getOrder() {
		return this.order;
	}
	public boolean getDirection() {
		return this.fromKitchen;
	}
	
	public Waiter() {
		datalock = new ReentrantLock();
		workinglock = new ReentrantLock();
		this.freeUp();
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
		return this.order == null;
	}
	public boolean serveOrder(orderItem order, orders target, boolean fromKitchen) {
		datalock.lock(); //used only to ensure that the waiter will be free, when called
		if(!this.isFree() || order.getStatus() == orderStatus.Ordered) {
			datalock.unlock();
			return false;
		}	
		this.order = order;
		datalock.unlock(); //the isFree will now return false, thus no need to keep the lock anymore
		this.order.setStatus(orderStatus.Carried);
		new Log().showMessage("order:" + order.toString() + " served to " + this.order.getStatus());
		this.fromKitchen = fromKitchen;
		this.target = target;
		return true;
	}
	private void slackALittle() {
		try {
			Thread.sleep(this.workingTime);
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
		this.slackALittle();
		this.target.addItem(this.order);
		if(this.fromKitchen)
			this.order.setStatus(orderStatus.Delivered);
		else
			this.order.setStatus(orderStatus.Kitchen);
		new Log().showMessage("order:" + order.toString() + " served to " + this.order.getStatus());
		workinglock.unlock();
		this.freeUp();
		return true;
	}
}
