package F21AS;

import java.util.*;

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




    /**
     * @param Index 
     * @return
     */
    public orderItem getItem(Integer Index) throws ArrayIndexOutOfBoundsException {
    	if(Index < items.size())
    		return this.items.get(Index);
    	throw new ArrayIndexOutOfBoundsException(Index);

    }

    /**
     * @return
     */
    public Integer countItems() {
        // TODO implement here
        return this.items.size();
    }

    /**
     * @param Item 
     * @return
     */
    public void addItem(orderItem Item) {
        this.items.add(Item);
    }

    /**
     * @return
     */
    public void clearOrders() {
    	this.items.clear();
    	this.invokeAll(orders.ItemsCleared, this);
    }

    /**
     * @return
     */
    public orders() {
    	this.items = new ArrayList<orderItem> (); 
    }
    
    public void removeItem(int index) {
    	this.items.remove(index);
    	this.invokeAll(orders.ItemRemoved, this);
    }
    
    /**
     * Removes the last item of the orders list and returns it.
     * @return the last item of the orders list
     */
    public orderItem pop() {
    	if(this.countItems() != 0) {
    		orderItem item = this.getItem(this.countItems() - 1);
    		this.removeItem(this.countItems() - 1);
    		this.invokeAll(orders.ItemRemoved, this);
    		return item;
    	}
    	return null;
    }
}