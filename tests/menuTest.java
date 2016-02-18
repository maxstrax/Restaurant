/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F21AS.itemCategory;
import F21AS.menu;
import F21AS.menuItem;

/**
 * @author Marios Katsigiannis
 *
 */
public class menuTest extends menu {

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.addItem(new menuItem("Food 1", 5.7f, itemCategory.Starter));
		this.addItem(new menuItem("Food 2", 4.7f, itemCategory.Main));
		this.addItem(new menuItem("Food 3", 2.7f, itemCategory.Dessert));
		this.addItem(new menuItem("Food 4", 6.7f, itemCategory.Main));
		this.addItem(new menuItem("Food 5", 5.4f, itemCategory.Drinks));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("Food 4", this.getMenu(3).getName());
		try {
			this.getMenu(-1);
		}catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}
		try {
			this.getMenu(5);
		}catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}
		
		this.clearMenu();
		try {
			this.getMenu(3);
		}catch(Exception e) {
			assertTrue(e instanceof ArrayIndexOutOfBoundsException);
		}
		
	}

}
