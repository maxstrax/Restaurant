
import java.util.*;

/**
 * enables searching the menu items by name. Must be created when the menu object, to index, is fully populated.
 */
public class menuIndexer {

    /**
     * 
     */
    private HashMap<String, Integer> index;


    /**
     * @param Name 
     * @return
     */
    public Integer getIndexOf(String Name) throws invalidNameException {
    	 if(!this.index.containsKey(Name))
         	throw new invalidNameException(Name);
         else {
         	return index.get(Name);
         }
    }

    /**
     * called to create the index. The menu item must be fully populated.
     * @param mainMenu 
     * @return
     */
    public void create(menu mainMenu) {
    	this.index.clear();
    	String menuItemName = null;
    	for(int i=0; i<mainMenu.count(); i++) {
    		menuItemName = mainMenu.getMenu(i).getName();
    		index.put(menuItemName, i);		
    	}
    }

    /**
     * calls create(mainMenu). The  object must be fully populated!
     * @param mainMenu
     */
    public menuIndexer(menu mainMenu) {
    	this.index = new HashMap<String, Integer>(); //init our map
    	this.create(mainMenu);
    }

    /**
     * returns a collection of all the names in the keys of the index.
     * @return
     */
    public Set<String> getNames() {
    	return this.index.keySet();
    }

}