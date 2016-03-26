package F21AS;
/**
 * The main class of the application
 */
public class restaurant {

    /**
     * Default constructor
     */
    public restaurant() {
    }

    /**
     * 
     */
    public static restaurantModel model = null;

    /**
     * 
     */
    public static restaurantController controller = null;

    /**
     * 
     */
    public static restaurantView view = null;

    /**
     * @param args 
     * @return
     */
    public static void main(String[] args) {
        try {
	        model = new restaurantModel();
	        controller = new restaurantController(model);
			controller.loadMenu("./menu-initial.txt");
	        controller.loadOrders("./orders-initial.txt");
	        controller.createIndexers();
	        view = new restaurantView(controller, model);
	        //initial setup
	        model.waiters.addWaiter(new Waiter(model, model.dailyOrders, model.kitchen, false));
		    model.waiters.addWaiter(new Waiter(model, model.kitchen, model.tables));
	        model.waiters.addAllToManager(model.threads, false);
/*
	        Waiter toKitchen = new Waiter(this, this.dailyOrders, this.kitchen, false);
	        this.waiters.addWaiter(toKitchen);
	        Waiter toTables = new Waiter(this, this.kitchen, this.tables);
	        this.waiters.addWaiter(toTables);
	        threads.add(toKitchen, false);
	        threads.add(toTables, false);
*/
	        view.showGUI();
	        controller.operateTheRestaurant();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}