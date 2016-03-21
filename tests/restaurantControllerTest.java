package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import F21AS.invalidPriceException;
import F21AS.restaurantController;
import F21AS.restaurantModel;

public class restaurantControllerTest {

	@Test
	public final void testPrices() {
		try {
			restaurantModel model = new restaurantModel();
			restaurantController controller = new restaurantController(model);
			controller.loadMenu("./menu-initial.txt");
			controller.loadOrders("./orders-initial.txt");
		    controller.createIndexers();
		    BufferedReader br = new BufferedReader(new FileReader("./src/report-test.txt"));
		    String test_report = "";
		    while(br.ready())
		    	test_report += br.readLine() + "\n";
		    br.close();
		    test_report = test_report.substring(0, test_report.length() - 1);
		    String report = controller.createReport();
		    assertTrue(report.trim().compareTo(test_report.trim()) == 0);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception occurred");
		}
	}
	@Test
	public final void testCalculateDiscount() {
		try {
			restaurantController.calculateDiscount(-1);
			fail("Expected an invalidPriceException");
		} catch (invalidPriceException e) {
			//success
		}
		try {
			assertEquals(restaurantController.calculateDiscount(3), 0.0f, 0.005f);
		} catch (invalidPriceException e) {
			fail("Unexpected invalidPriceException");
		}
		try {
			assertEquals(restaurantController.calculateDiscount(10.0f), 0.0f, 0.005f);
		} catch (invalidPriceException e) {
			fail("Unexpected invalidPriceException");
		}
		try {
			assertEquals(restaurantController.calculateDiscount(20.0f), 2.0f, 0.005f);
		} catch (invalidPriceException e) {
			fail("Unexpected invalidPriceException");
		}
		
	}

}
