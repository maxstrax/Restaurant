package F21AS;

import java.util.*;

/**
 * Holds a list of all the orders
 */
public class orders {

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
    }

    /**
     * @return
     */
    public orders() {
    	this.items = new ArrayList<orderItem> (); 
    }
    
    public void removeItem(int index) {
    	this.items.remove(index);
    }
    
    /**
     * Removes the last item of the orders list and returns it.
     * @return the last item of the orders list
     */
    public orderItem pop() {
    	if(this.countItems() != 0) {
    		orderItem item = this.getItem(this.countItems() - 1);
    		this.removeItem(this.countItems() - 1);
    		return item;
    	}
    	return null;
    }
}