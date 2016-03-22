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
	        
	        view.showGUI();
	        controller.operateTheRestaurant();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}