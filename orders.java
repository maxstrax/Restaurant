
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
    public orderItem getItem(Integer Index) {
        // TODO implement here
     return items.get(Index);
    }

    /**
     * @return
     */
    public Integer countItems() {
        // TODO implement here
        return items.size();
    }

    /**
     * @param Item 
     * @return
     */
    public void addItem(orderItem Item) {
        items.add(Item);
    }

    /**
     * @return
     */
    public void clearOrders() {
    	items.clear();
    }

    /**
     * @return
     */
    public orders() {
    	items = new ArrayList<orderItem> (); 
    }

}