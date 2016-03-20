/**
 * 
 */
package F21AS;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Marios Katsigiannis
 * This class will be used to carry the list of indices in the orders object for the
 * orders of this table.
 */
public class TableIndexer {
	/**
	 * The TableId for this table
	 */
	private int id;
	
	private List<Integer> orders;
	public TableIndexer() {
		this(-1);
	}
	public TableIndexer(int id) {
		this(id, new LinkedList<Integer>());
	}
	public TableIndexer(int id, List<Integer> orders) {
		this.id = id;
		this.orders = orders;
	}
	public Integer getID() {
		return id;
	}
	public void addOrderIndex(Integer index) {
		this.orders.add(index);
	}
	public Integer count() {
		if(orders != null)
			return orders.size();
		return 0;
	}
	public Integer getOrder(Integer index) {
		if(orders != null && orders.size() > index)
			return orders.get(index);
		return null;
	}
}
