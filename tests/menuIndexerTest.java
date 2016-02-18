package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F21AS.invalidNameException;
import F21AS.itemCategory;
import F21AS.menu;
import F21AS.menuIndexer;
import F21AS.menuItem;

public class menuIndexerTest {
	menu m = new menu();
	@Before
	public void setUp() throws Exception {
		m.addItem(new menuItem("Food 1", 5.7f, itemCategory.Starter));
		m.addItem(new menuItem("Food 2", 4.7f, itemCategory.Main));
		m.addItem(new menuItem("Food 3", 2.7f, itemCategory.Dessert));
		m.addItem(new menuItem("Food 4", 6.7f, itemCategory.Main));
		m.addItem(new menuItem("Food 5", 5.4f, itemCategory.Drinks));
	}

	@Test
	public void testGetIndexOf() {
		menuIndexer mi = new menuIndexer(m);
		try {
			assertEquals((Integer)0, mi.getIndexOf("Food 1"));
		} catch (invalidNameException e) {
			fail("InvalidNameException thrown when it should not");
		}
		try {
			mi.getIndexOf("Food 6");
			fail("InvalidNameException not thrown");
		} catch (invalidNameException e) {
			//success!
		}
	}
}
