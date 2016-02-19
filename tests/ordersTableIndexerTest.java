package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import F21AS.invalidTableIdException;
import F21AS.orderItem;
import F21AS.orders;
import F21AS.ordersTableIndexer;

public class ordersTableIndexerTest {
	orders o = new orders();
	@Before
	public void setUp() throws Exception {
		o.addItem(new orderItem(15, "Roasted Beef", 5));
		o.addItem(new orderItem(1, "Food 2", 6));
		o.addItem(new orderItem(7, "Raosted Steel", 2));
	}
	
	@Test
	public void testGetIndexOf() {
		ordersTableIndexer oti = new ordersTableIndexer(o);
		try {
			assertEquals((Integer)1, oti.getIndexOf(1,2));
		} catch (invalidTableIdException e) {
			fail("InvalidTableIdException thrown when it should not");
		}
		try {
			oti.getIndexOf(1,2);
			fail("InvalidTableIdException not thrown");
		} catch (invalidTableIdException e) {
			//success!
		}
		
	}
	
	public void testGetOrdersCount() {
		ordersTableIndexer oti = new ordersTableIndexer(o);
		try {
			assertEquals((Integer)1, oti.getOrdersCount(1));
		} catch (invalidTableIdException e) {
			fail("InvalidTableIdException thrown when it should not");
		}
		try {
			oti.getOrdersCount(1);
			fail("InvalidTableIdException not thrown");
		} catch (invalidTableIdException e) {
			//success!
		}
	}
}
