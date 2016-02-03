
import java.util.*;

/**
 * 
 */
public class ordersTableIndexer {

    /**
     * 
     */
    private HashMap<Integer, LinkedList<Integer>> tableOrders;


    /**
     * Creates the index. Requires dailyOrders to be already populated
     * @param dailyOrders 
     * @return
     */
    public void create(orders dailyOrders) {
    	this.tableOrders.clear(); //if we have previous values, clear them up. DEBUG INFO <- if it crushes here orderNames is not allocated with new
    	int tableId;
    	LinkedList<Integer> l = null;
    	for(int i=0; i<dailyOrders.countItems(); i++) {
    		tableId = dailyOrders.getItem(i).getTableId();
    		if(tableOrders.containsKey(tableId)) {	// if key does not exist
    			l = new LinkedList<Integer>();			//   create a new linkedlist
    			tableOrders.put(tableId, l);		//   add it as the value to the key (order name)
    		}
    												// if key exists -> the list is allocated and initialized
    		tableOrders.get(tableId).add(i);	//   add the new key at the end
    	}
    }

    /**
     * Returns all the indexed TableIds
     * @return
     */
    public Set<Integer> getTableIds() {
        return this.tableOrders.keySet();

    }

    /**
     * Returns the amount of orders given for a tableId
     * @param tableId 
     * @return
     */
    public Integer getOrdersCount(Integer tableId) {

    	  return this.tableOrders.size();
    }

    /**
     * Returns the index of an order  of a table.
     * @param tableId 
     * @param OrderNo 
     * @return
     */
    public Integer getIndexOf(Integer tableId, Integer OrderNo) throws invalidTableIdException, ArrayIndexOutOfBoundsException {
        if(!this.tableOrders.containsKey(tableId))
        	throw new invalidTableIdException(tableId);
        else {
        	List<Integer> indices = this.tableOrders.get(tableId);
        	if(indices.size() <= OrderNo || OrderNo < 0)
        		throw new ArrayIndexOutOfBoundsException(OrderNo);
        	else
        		return indices.get(OrderNo);
        }
    }

    /**
     * calls create(dailyOrders)
     * @param dailyOrders
     */
    public ordersTableIndexer(orders dailyOrders) {
    	this.tableOrders = new HashMap<Integer, LinkedList<Integer>>(); //init our map
    	this.create(dailyOrders);
    }

}