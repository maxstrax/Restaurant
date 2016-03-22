/**
 * 
 */
package F21AS;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * @author Marios Katsigiannis
 *
 */
public class Tables {


    /**
     * 
     */
    private HashMap<Integer, orders> tables;


    /**
     * Used to add orders one by one
     * @param tableId
     * @param index
     */
    public void addTable(Integer tableId) {
    	if(!tables.containsKey(tableId)) {	// if key does not exist
    		orders table = new orders();	//   create a new orders
			tables.put(tableId, table);		//   add it as the value to the key (order name)
		}
    }
    public void addTables(Collection<Integer> tableIds) {
    	for(Integer table : tableIds)
    		this.addTable(table);
    }
    /**
     * Returns all the TableIds
     * @return
     */
    public Set<Integer> getTableIds() {
        return this.tables.keySet();
    }

    /**
     * Returns a table.
     * @param tableId 
     * @param OrderNo 
     * @return
     * @throws invalidTableIdException
     */
	public orders getTable(Integer tableId) throws invalidTableIdException {
		if(!this.tables.containsKey(tableId))
	    	throw new invalidTableIdException(tableId);
		else {
	    	return this.tables.get(tableId);
		}
	
	}
	public boolean tableExists(Integer tableId) {
		return this.tables.containsKey(tableId);
	}
	 public Tables() {
		this.tables = new HashMap<Integer, orders>(); //init our map
	}
     
	 public String toString() {
    	String result = "Tables:\n";
    	for(Map.Entry<Integer, orders> p : this.tables.entrySet())
    		result += "Table " + p.getKey() +"\n" + p.getValue().toString() + "\n";
    	return result;

	 }
}