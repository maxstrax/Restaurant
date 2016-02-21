package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import F21AS.invalidNameException;
import F21AS.invalidTableIdException;
import F21AS.orderItem;
import F21AS.orders;
import F21AS.ordersNameIndexer;
import F21AS.ordersTableIndexer;

public class ordersNameIndexerTest {
	orders o = new orders();
	@Before
	public void setUp() throws Exception {
		o.addItem(new orderItem(15, "Roasted Beef", 5));
		o.addItem(new orderItem(1, "Food 2", 6));
		o.addItem(new orderItem(7, "Raosted Steel", 2));
	}
	@Test
	public void TestGetNames() {
		HashSet<String> testnames = new HashSet<String>();
		testnames.add("Roasted Beef");
		testnames.add("Raosted Steel");
		ordersNameIndexer oi = new ordersNameIndexer(o);
		Set<String> names = oi.getNames();
		assertTrue(names.containsAll(testnames));
		assertTrue( names.retainAll(testnames)); 
	}
	
	
	@Test
	public void TestGetIndexOf() {
		ordersNameIndexer oti = new ordersNameIndexer(o);
		try {
			assertEquals((Integer)1, oti.getIndexOf("Food 2", 0));
		} catch (invalidNameException e) {
			fail("invalidNameException thrown when it should not");
		}
		try {
			oti.getIndexOf("Food 3",1);
			fail("invalidNameException not thrown");
		} catch (invalidNameException e) {
			//success!
		}
		try {
			oti.getIndexOf("Food 2",4);
			fail("ArrayIndexOutOfBoundsException not thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			//success!
		} catch (invalidNameException e) {
			fail("Was Expecting ArrayIndexOutOfBoundsException, not invalidNameException");
		}
		
	}


}
