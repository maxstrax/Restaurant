package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import F21AS.restaurantController;

@RunWith(Suite.class)
@SuiteClasses({ menuCategoryIndexerTest.class, menuIndexerTest.class, menuTest.class, ordersTest.class, ordersTableIndexerTest.class, ordersNameIndexerTest.class, restaurantController.class})
public class AllTests {

}
