package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F21AS.ordersTableIndexer;
import F21AS.invalidTableIdException;
import F21AS.orderItem;
import F21AS.orders;

public class ordersTableIndexerTest {
	orders o = new orders();
	@Before
	public void setUp() throws Exception {
		o.addItem(new orderItem(15, "Roasted Beef", 5));
		o.addItem(new orderItem(1, "Food 2", 6));
		o.addItem(new orderItem(7, "Roasted Steel", 2));
	}
	
	@Test
	public void testGetIndexOf() {
		ordersTableIndexer oti = new ordersTableIndexer(o);
		try {
			assertEquals((Integer)1, oti.getTable(1).getOrder(0));
		} catch (invalidTableIdException e) {
			fail("InvalidTableIdException thrown when it should not");
		}
		try {
			oti.getTable(2).getOrder(2);
			fail("InvalidTableIdException not thrown");
		} catch (invalidTableIdException e) {
			//success!
		}
		try {
			oti.getTable(1).getOrder(2);
			fail("ArrayIndexOutOfBoundsException not thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			//success!
		} catch (invalidTableIdException e) {
			fail("Was expecting ArrayIndexOutOfBoundsException, not invalidTableIdException");
		}
		
	}
	
	public void testGetOrdersCount() {
		ordersTableIndexer oti = new ordersTableIndexer(o);
		try {
			assertEquals((Integer)1, oti.getTable(1).count());
		} catch (invalidTableIdException e) {
			fail("InvalidTableIdException thrown when it should not");
		}
		try {
			oti.getTable(2).count();
			fail("InvalidTableIdException not thrown");
		} catch (invalidTableIdException e) {
			//success!
		}
	}
	@After
	public void tearDown() throws Exception {
		this.o.clearOrders();
	}

}
