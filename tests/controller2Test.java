/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import F21AS.invalidTableIdException;
import F21AS.restaurantController;
import F21AS.restaurantModel;
import F21AS.restaurantView;

/**
 * @author Marios Katsigiannis
 *
 */
public class controller2Test {

	restaurantModel model;
	restaurantController controller;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = new restaurantModel();
        controller = new restaurantController(model);
		controller.loadMenu("./menu-initial.txt");
        controller.loadOrders("./orders-initial.txt");
        controller.createIndexers();
 	}

	@Test
	public final void test() {
		controller.operateTheRestaurant();
		try {
			assertEquals("Not the right amount of orders in the table 3", (int)model.tables.getTable(3).countItems(), 1);
		} catch (invalidTableIdException e) {
			// TODO Auto-generated catch block
			fail("Not found a table");
		}
	}

}
