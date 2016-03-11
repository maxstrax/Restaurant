package F21AS;

import java.util.*;

/**
 * 
 */
public class Tables {

    /**
     * 
     */
    private HashMap<Integer, Table> tableOrders;


    /**
     * Creates the index. Requires dailyOrders to be already populated
     * @param dailyOrders 
     * @return
     */
    public void create(orders dailyOrders) {
    	this.tableOrders.clear(); //if we have previous values, clear them up. DEBUG INFO <- if it crushes here orderNames is not allocated with new
    	int tableId;
    	for(int i=0; i<dailyOrders.countItems(); i++) {
    		tableId = dailyOrders.getItem(i).getTableId();
    		addOrderIndex(tableId, i);
    	}
    }
    /**
     * Used to add orders one by one
     * @param tableId
     * @param index
     */
    public void addOrderIndex(Integer tableId, Integer index) {
    	if(!tableOrders.containsKey(tableId)) {	// if key does not exist
    		Table t = new Table(tableId);		//   create a new linkedlist
			tableOrders.put(tableId, t);		//   add it as the value to the key (order name)
		}
														// if key exists -> the list is allocated and initialized
		tableOrders.get(tableId).addOrderIndex(index);	//   add the new key at the end
    }
    /**
     * Returns all the indexed TableIds
     * @return
     */
    public Set<Integer> getTableIds() {
        return this.tableOrders.keySet();

    }

    /**
     * Returns the index of an order  of a table.
     * @param tableId 
     * @param OrderNo 
     * @return
     * @throws invalidTableIdException 
     */
    public Table getTable(Integer tableId) throws invalidTableIdException {
        if(!this.tableOrders.containsKey(tableId))
        	throw new invalidTableIdException(tableId);
        else {
        	return this.tableOrders.get(tableId);
        }

    }
    public boolean tableExists(Integer tableId) {
    	return this.tableOrders.containsKey(tableId);
    }
     public Tables() {
    	this.tableOrders = new HashMap<Integer, Table>(); //init our map
    }
   /**
     * calls create(dailyOrders)
     * @param dailyOrders
     */
    public Tables(orders dailyOrders) {
    	this(); //init our map
    	this.create(dailyOrders);
    }
}