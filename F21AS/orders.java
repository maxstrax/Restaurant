package F21AS;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Holds a list of all the orders
 */
public class orders extends Observable {

	public static final int ItemsCleared = 0;
	public static final int ItemAdded = 1;
	public static final int ItemRemoved = 2;
	
    /**
     * 
     */
    private ArrayList<orderItem> items;

    private Lock datalock;



    /**
     * @param Index 
     * @return
     */
    public orderItem getItem(Integer Index) throws ArrayIndexOutOfBoundsException {
    	
    	if(Index < items.size()) {
    		datalock.lock();
    		orderItem item = this.items.get(Index);
    		datalock.unlock();
    		return item;
    	}
    	throw new ArrayIndexOutOfBoundsException(Index);

    }

    /**
     * @return
     */
    public Integer countItems() {
        datalock.lock();
    	int size = this.items.size();
    	datalock.unlock();
        return size;
    }

    /**
     * @param Item 
     * @return
     */
    public void addItem(orderItem Item) {
    	this.datalock.lock();
        this.items.add(Item);
        this.datalock.unlock();
        this.invokeAll(orders.ItemAdded, this);
    }

    /**
     * @return
     */
    public void clearOrders() {
    	this.datalock.lock();
    	this.items.clear();
    	this.datalock.unlock();
    	this.invokeAll(orders.ItemsCleared, this);
    }

    /**
     * @return
     */
    public orders() {
    	this.items = new ArrayList<orderItem> (); 
    	this.datalock = new ReentrantLock();
    }
    
    public void removeItem(int index) {
    	this.datalock.lock();
    	this.items.remove(index);
    	this.datalock.unlock();
    	this.invokeAll(orders.ItemRemoved, this);
    }
    
    
    public orderItem pop(int index) {
    	if(this.countItems() > index) {
    		orderItem item = this.getItem(index);
    		this.removeItem(index);
    		this.invokeAll(orders.ItemRemoved, this);
    		return item;
    	}
    	return null;
    }
   /**
     * Removes the last item of the orders list and returns it.
     * @return the last item of the orders list
     */
    public orderItem popBack() {
    	return this.pop(this.countItems() - 1);
    }
    public orderItem popFront() {
    	return this.pop(0);
    }
    
    public String toString() {
    	String result = "";
    	datalock.lock();
    	for(orderItem o : this.items)
    		result += o.toString() + "\n";
    	datalock.unlock();
    	return result;
    }
}