
import java.util.*;

/**
 * Holds the data for one menu item.
 */
public class menuItem {

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Float price;

    /**
     * 
     */
    private itemCategory category;




    /**
     * @param Name 
     * @return
     */
    public void setName(String Name) {
    	this.name = Name;
    }

    /**
     * @param Price 
     * @return
     */
    public void setPrice(Float Price) {
        this.price = Price;
    }

    /**
     * @param Category 
     * @return
     */
    public void setCategory(itemCategory Category) {
        this.category = Category;
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
    public Float getPrice() {
        // TODO implement here
        return price;
    }

    /**
     * @return
     */
    public itemCategory getCategory() {
        // TODO implement here
        return category;
    }

    /**
     * @param Name 
     * @param Price 
     * @param Category
     */
    public menuItem(String Name, Float Price, itemCategory Category) {
        // TODO implement here
    }

}