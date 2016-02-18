package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import F21AS.orderItem;
import F21AS.orders;

public class ordersTest extends orders {

	@Before
	public void setUp() throws Exception {
		this.addItem(new orderItem(15, "Roasted Beef", 5));
		this.addItem(new orderItem(1, "Food 2", 6));
		this.addItem(new orderItem(7, "Raosted Steel", 2));
	}

	@Test
	public void testGetItem() {
		assertEquals("Food 2", this.getItem(1).getName());
		try {
			this.getItem(-1);
		}catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}
		try {
			this.getItem(5);
		}catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}
	}

	@Test
	public void testCountItems() {
		assertEquals(this.countItems(), (Integer)3);
	}

}
