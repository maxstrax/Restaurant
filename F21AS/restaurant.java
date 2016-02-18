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
	        view = new restaurantView(controller, model);
			controller.loadMenu("menu.txt");
	        controller.loadOrders("orders.txt");
	        controller.createIndexers();
	        controller.saveReport("report.txt");
	        view.showGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}