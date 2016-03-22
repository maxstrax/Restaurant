package F21AS;


/**
 * Contains all the data of the application. Use objects of the restaurantController class to manipulate the objects of this class.
 */
public class restaurantModel extends Observable {
	
	public static final int EverythingChanged = -1;
	public static final int MenuChanged = 0;
	public static final int OrdersChanged = 1;
    /**
     * This variable holds data about the menu of the restaurant
     */
    public menu mainMenu;

    /**
     * 
     */
    public menuIndexer mainMenuIndexer;

    public menuCategoryIndexer mainMenuCategoryIndexer;
    /**
     * 
     */
    public orders dailyOrders;

    /**
     * 
     */
    public ordersTableIndexer dailyOrdersTableIndexer;

    /**
     * 
     */
    public ordersNameIndexer dailyOrdersNameIndexer;

    public Tables tables;

    public orders kitchen;

    /**
    * 
    */
	public Waiter waiterKitchen, waiterTables;

	public boolean operate;
	
    public menu getMainMenu() {
		return mainMenu;
	}




	public void setMainMenu(menu mainMenu) {
		this.mainMenu = mainMenu;
		this.invokeAll(MenuChanged, this);
	}




	public menuIndexer getMainMenuIndexer() {
		return mainMenuIndexer;
	}




	public void setMainMenuIndexer(menuIndexer mainMenuIndexer) {
		this.mainMenuIndexer = mainMenuIndexer;
	}




	public orders getDailyOrders() {
		return dailyOrders;
	}




	public void setDailyOrders(orders dailyOrders) {
		this.dailyOrders = dailyOrders;
		this.invokeAll(OrdersChanged, this);
	}




	public ordersTableIndexer getDailyOrdersTableIndexer() {
		return dailyOrdersTableIndexer;
	}




	public void setDailyOrdersTableIndexer(ordersTableIndexer dailyOrdersTableIndexer) {
		this.dailyOrdersTableIndexer = dailyOrdersTableIndexer;
	}




	public ordersNameIndexer getDailyOrdersNameIndexer() {
		return dailyOrdersNameIndexer;
	}




	public void setDailyOrdersNameIndexer(ordersNameIndexer dailyOrdersNameIndexer) {
		this.dailyOrdersNameIndexer = dailyOrdersNameIndexer;
	}
	
    public Tables getTables() {
		return tables;
	}




	public Waiter getWaiterKitchen() {
		return waiterKitchen;
	}




	public Waiter getWaiterTables() {
		return waiterTables;
	}




	/**
     * sets the indexers to Null and creates new objects for mainMenu and dailyOrders
     */
    public restaurantModel() {
        this.dailyOrders = new orders();
        this.mainMenu = new menu();
        this.waiterKitchen = new Waiter();
        this.waiterKitchen.setWorkingTime(10);
        this.waiterTables = new Waiter();
        this.waiterTables.setWorkingTime(50);
        this.kitchen = new orders();
        this.tables = new Tables();
        this.operate = false;
        // the structures are empty now, creating the indexers at this point will serve no purpose
        this.dailyOrdersTableIndexer = null;
        this.dailyOrdersNameIndexer = null;
        this.mainMenuIndexer = null;
        this.mainMenuCategoryIndexer = null;
    }
    
    

}