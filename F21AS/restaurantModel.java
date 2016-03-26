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
	//public Waiter waiterKitchen, waiterTables;
    public Waiters waiters;
    
    public ThreadManager threads;
    
	public volatile boolean operate;
	
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



	/**
     * sets the indexers to Null and creates new objects for mainMenu and dailyOrders
     */
    public restaurantModel() {
        this.dailyOrders = new orders();
        this.mainMenu = new menu();
        this.kitchen = new orders();
        this.tables = new Tables();
        this.waiters = new Waiters();
        Waiter toKitchen = new Waiter(this.operate, this.dailyOrders, this.kitchen, true);
        this.waiters.addWaiter(toKitchen);
        Waiter toTables = new Waiter(this.operate, this.kitchen, this.tables);
        this.waiters.addWaiter(toTables);
        threads = new ThreadManager();
        threads.add(toKitchen, false);
        threads.add(toTables, false);
        this.operate = false;
        // the structures are empty now, creating the indexers at this point will serve no purpose
        this.dailyOrdersTableIndexer = null;
        this.dailyOrdersNameIndexer = null;
        this.mainMenuIndexer = null;
        this.mainMenuCategoryIndexer = null;
    }
    
    

}