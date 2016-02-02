
import java.util.*;

/**
 * 
 */
public class ordersNameIndexer {

    /**
     * 
     */
    private HashMap<String, LinkedList<Integer>> orderNames;


    /**
     * Creates the index. Requires dailyOrders to be populated.
     * @param dailyOrders 
     * @return
     */
    public void create(orders dailyOrders) {
    	this.orderNames.clear(); //if we have previous values, clear them up. DEBUG INFO <- if it crushes here orderNames is not allocated with new
    	String orderItemName = null;
    	LinkedList<Integer> l = null;
    	for(int i=0; i<dailyOrders.countItems(); i++) {
    		orderItemName = dailyOrders.getItem(i).getName();
    		if(orderNames.containsKey(orderItemName)) {	// if key does not exist
    			l = new LinkedList<Integer>();			//   create a new linkedlist
    			orderNames.put(orderItemName, l);		//   add it as the value to the key (order name)
    		}
    												// if key exists -> the list is allocated and initialized
    		orderNames.get(orderItemName).add(i);	//   add the new key at the end
    	}
    }

    /**
     * Returns a collection of the names in the index
     * @return
     */
    public Set<String> getNames() {
        return this.orderNames.keySet();
    }

    /**
     * Returns the amount of orders contain a specific name
     * @param Name 
     * @return
     */
    public Integer getOrdersCount(String Name) {
        return this.orderNames.size();
    }

    /**
     * Returns the index of an order  of a set name.
     * @param name 
     * @param index 
     * @return
     */
    public Integer getIndexOf(String name, Integer index) throws invalidNameException, ArrayIndexOutOfBoundsException{
        if(!this.orderNames.containsKey(name))
        	throw new invalidNameException(name);
        else {
        	List<Integer> indices = this.orderNames.get(name);
        	if(indices.size() <= index || index < 0)
        		throw new ArrayIndexOutOfBoundsException(index);
        	else
        		return indices.get(index);
        }
    }

    /**
     * calls create(dailyOrders)
     * @param dailyOrders
     */
    public ordersNameIndexer(orders dailyOrders) {
    	this.orderNames = new HashMap<String, LinkedList<Integer>>(); //init our map
    	this.create(dailyOrders);
    }

}