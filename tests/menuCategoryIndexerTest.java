package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F21AS.invalidCategoryException;
import F21AS.itemCategory;
import F21AS.menu;
import F21AS.menuCategoryIndexer;
import F21AS.menuItem;

public class menuCategoryIndexerTest {
	menu m = new menu();

	@Before
	public void setUp() throws Exception {
		m.addItem(new menuItem("Food 1", 5.7f, itemCategory.Starter));
		m.addItem(new menuItem("Food 2", 4.7f, itemCategory.Main));
		m.addItem(new menuItem("Food 3", 2.7f, itemCategory.Dessert));
		m.addItem(new menuItem("Food 4", 6.7f, itemCategory.Main));
	}

	@After
	public void tearDown() throws Exception {
		m.clearMenu();
	}

	@Test
	public final void testGetIndexOf() {
		menuCategoryIndexer mci = new menuCategoryIndexer(m);
		try {
			assertEquals((Integer)1, mci.getIndexOf(itemCategory.Main, 0));
		} catch (invalidCategoryException e) {
			fail("invalid category thrown when it should not");
			e.printStackTrace();
		}
		try {
			assertEquals((Integer)3, mci.getIndexOf(itemCategory.Main, 1));
		} catch (invalidCategoryException e) {
			fail("invalid category thrown when it should not");
			e.printStackTrace();
		}
		try {
			assertEquals((Integer)0, mci.getIndexOf(itemCategory.Main, 2));
			fail("ArrayIndexOutOfBoundsException not thrown when it should");
		} catch (invalidCategoryException e) {
			fail("invalid category thrown when it should not");
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {
			//success
		}
		try {
			assertEquals((Integer)0, mci.getIndexOf(itemCategory.Drinks, 3));
			fail("invalid category not thrown when it should");
		} catch (invalidCategoryException e) {
			//success
		} catch(ArrayIndexOutOfBoundsException e) {
			fail("ArrayIndexOutOfBoundsException thrown when it should not");
		}
		
	}

	@Test
	public final void testGetCategoryCount() {
		menuCategoryIndexer mci = new menuCategoryIndexer(m);
		try {
			assertEquals((Integer)2, mci.getCategoryCount(itemCategory.Main));
		} catch (invalidCategoryException e) {
			fail("invalid category thrown when it should not");
			e.printStackTrace();
		}
		try {
			assertEquals((Integer)1, mci.getCategoryCount(itemCategory.Starter));
		} catch (invalidCategoryException e) {
			fail("invalid category thrown when it should not");
			e.printStackTrace();
		}
		try {
			mci.getCategoryCount(itemCategory.Drinks);
			fail("invalid category  was not thrown when it should");
		} catch (invalidCategoryException e) {
			//success
		}
	}

}
