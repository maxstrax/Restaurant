
import java.util.*;

/**
 * holds a list of all menu items.
 */
public class menu {

    /**
     * 
     */
    private ArrayList<menuItem> items;




    /**
     * @param Index 
     * @return
     */
    public menuItem getMenu(Integer Index) {
    	return this.items.get(Index);
    }

    /**
     * @return
     */
    public Integer count() {
    	return this.items.size();
    }

    /**
     * @param Items 
     * @return
     */
    public void addItem(menuItem Items) {
    	this.items.add(Items);
    }

    /**
     * @return
     */
    public void clearMenu() {
    	this.items.clear();
    }

    /**
     * 
     */
    public menu() {
    	this.items = new ArrayList<menuItem> (); 
    }

}