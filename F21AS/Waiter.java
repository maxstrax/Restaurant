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
public class Waiter implements Runnable{
	private volatile boolean hired;
	private restaurantModel model;
	private orderItem order;
	private boolean fromKitchen; //true = to table, false = to kitchen
	private orders origin;
	private Tables targets;
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
	
	public Waiter(restaurantModel model, orders origin, Tables targets, orders target, boolean fromKitchen) {
		this.datalock = new ReentrantLock();
		this.workinglock = new ReentrantLock();
		this.model = model;
		this.hired = true;
		this.origin = origin;
		this.targets = targets;
		this.target = target;
		this.fromKitchen = fromKitchen;
		this.freeUp();
	}
	public Waiter(restaurantModel model,orders origin, Tables targets) {
		this(model, origin, targets, null, true);
	}
	public Waiter(restaurantModel model, orders origin, orders target, boolean fromKitchen) {
		this(model, origin, null, target, fromKitchen);
	}
	
	//ensures that 
	private void freeUp() {
		workinglock.lock(); //ensure that 
		datalock.lock();
		order = null;
		datalock.unlock();
		workinglock.unlock();
	}
	public boolean isFree() {
		return this.order == null;
	}
	public static boolean getNextOrder(Waiter waiter, orders origin) {
		waiter.datalock.lock(); //used only to ensure that the waiter will be free, when called
		if(!waiter.isFree() || origin.countItems() == 0) {
			waiter.datalock.unlock();
			return false;
		}
		waiter.order = origin.popFront();
		waiter.datalock.unlock(); //the isFree will now return false, thus no need to keep the lock anymore
		waiter.order.setStatus(orderStatus.Carried);
		new Log().showMessage("order:" + waiter.order.toString() + " served to " + waiter.order.getStatus());
		return true;		
	}
	public static boolean serveOrder(Waiter waiter, orders target) {
		orderItem order;
		waiter.datalock.lock(); //used only to ensure that the waiter will be free, when called
		if(waiter.isFree() || waiter.order.getStatus() == orderStatus.Ordered) {
			waiter.datalock.unlock();
			return false;
		}
		order = waiter.order;
		target.addItem(order);
		waiter.datalock.unlock(); //the isFree will now return false, thus no need to keep the lock anymore
		waiter.freeUp();
		order.setStatus(orderStatus.Delivered);
		new Log().showMessage("order:" + order.toString() + " served to " + order.getStatus());
		return true;
	}
	private static void slackALittle(Waiter waiter) {
		try {
			Thread.sleep(waiter.workingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void performCurrentOperation(Waiter waiter) throws invalidTableIdException {
		Waiter.slackALittle(waiter);
		if(waiter.isFree() && waiter.origin.countItems() == 0)
			return;
		waiter.workinglock.lock();
		while(Waiter.getNextOrder(waiter, waiter.origin));
		orders target;
		if(waiter.targets != null)
			target = waiter.targets.getOrderTarget(waiter.order);
		else
			target = waiter.target;
		while(Waiter.serveOrder(waiter, target));
		waiter.workinglock.unlock();
		waiter.freeUp();
	}
	public boolean shouldContinue(Boolean Continue) {
		if(!Continue)
			return false;
		if(!this.fromKitchen && this.origin.countItems() == 0)
			return false;
		return true;
	}
	public void operate() {
		try {
			while(this.shouldContinue(this.model.operate))
				Waiter.performCurrentOperation(this);
		} catch (invalidTableIdException e) {
			e.printStackTrace();
		}
	}
	public void fire() {
		this.hired = false;
	}
	public boolean isHired() {
		return this.hired;
	}
	@Override
	public void run() {
		this.operate();
	}
}
