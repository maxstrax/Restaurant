package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ menuCategoryIndexerTest.class, menuIndexerTest.class, menuTest.class, ordersTest.class })
public class AllTests {

}
