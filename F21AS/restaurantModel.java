package F21AS;
/**
 * Contains all the data of the application. Use objects of the restaurantController class to manipulate the objects of this class.
 */
public class restaurantModel {

    /**
     * 
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




    public menu getMainMenu() {
		return mainMenu;
	}




	public void setMainMenu(menu mainMenu) {
		this.mainMenu = mainMenu;
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
	}




	public ordersTableIndexer getDailyOrdersTableIndexer() {
		return dailyOrdersTableIndexer;
	}




	public void setDailyOrdersTableIndexer(
			ordersTableIndexer dailyOrdersTableIndexer) {
		this.dailyOrdersTableIndexer = dailyOrdersTableIndexer;
	}




	public ordersNameIndexer getDailyOrdersNameIndexer() {
		return dailyOrdersNameIndexer;
	}




	public void setDailyOrdersNameIndexer(ordersNameIndexer dailyOrdersNameIndexer) {
		this.dailyOrdersNameIndexer = dailyOrdersNameIndexer;
	}




	/**
     * sets the indexers to Null and creates new objects for mainMenu and dailyOrders
     */
    public restaurantModel() {
        this.dailyOrders = new orders();
        this.mainMenu = new menu();
        // the structures are empty now, creating the indexers at this point will serve no purpose
        this.dailyOrdersTableIndexer = null;
        this.dailyOrdersNameIndexer = null;
        this.mainMenuIndexer = null;
        this.mainMenuCategoryIndexer = null;
    }
    
    

}