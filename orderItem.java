
import java.util.*;

/**
 * Holds all the data for one order
 */
public class orderItem {

    /**
     * 
     */
    private Integer tableId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer quantity;






    /**
     * @param TableId 
     * @param Name 
     * @param Quantity
     */
    public orderItem(Integer TableId, String Name, Integer Quantity) {

    	this.tableId=TableId;
    	this.name=Name;
    	this.quantity=Quantity;
    }

    /**
     * @param TableId 
     * @return
     */
    public void setTable(Integer TableId) {
    	this.tableId=TableId;
    }

    /**
     * @param Name 
     * @return
     */
    public void setName(String Name) {
    	this.name=Name;
    }


    /**
     * @param Quantity 
     * @return
     */
    public void setQuantity(Integer Quantity) {
    	this.quantity=Quantity;
    }

    /**
     * @return
     */
    public Integer getTableId() {
        // TODO implement here
        return tableId;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return name;
    }

    
    /**
     * @return
     */
    public Integer getQuantity() {
        // TODO implement here
        return quantity;
    }

}