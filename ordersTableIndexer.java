
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
        // TODO implement here
    }

    /**
     * Returns all the indexed TableIds
     * @return
     */
    public Set<Integer> getTableIds() {
        // TODO implement here
        return null;
    }

    /**
     * Returns the amount of orders given for a tableId
     * @param tableId 
     * @return
     */
    public Integer getOrdersCount(Integer tableId) {
        // TODO implement here
        return null;
    }

    /**
     * Returns the index of an order  of a table.
     * @param tableId 
     * @param OrderNo 
     * @return
     */
    public Integer getIndexOf(Integer tableId, Integer OrderNo) throws invalidTableIdException, ArrayIndexOutOfBoundsException {
        // TODO implement here
        return null;
    }

    /**
     * calls create(dailyOrders)
     * @param dailyOrders
     */
    public ordersTableIndexer(orders dailyOrders) {
        // TODO implement here
    }

}