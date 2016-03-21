/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import F21AS.invalidTableIdException;
import F21AS.restaurantController;
import F21AS.restaurantModel;

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
			assertEquals("Not the right amount of orders in the table 3", model.tables.getTable(3).countItems(), (Integer)1);
		} catch (invalidTableIdException e) {
			// TODO Auto-generated catch block
			fail("Not found a table");
		}
	}

}
