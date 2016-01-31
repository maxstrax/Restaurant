/**
 * The main class of the application
 */
public class restaurant {

    /**
     * 
     */
    public static restaurantModel model;

    /**
     * 
     */
    public static restaurantController controller;

    /**
     * 
     */
    public static restaurantView view;

    /**
     * @param args 
     * @return
     */
    public void main(String args) {
        // TODO implement here
    	model = new restaurantModel();
    	controller = new restaurantController(model);
    	view = new restaurantView(controller, model);
    	controller.loadMenu("menu.txt");
    	controller.loadOrders("orders.txt");
    	controller.saveReport("output.txt");
    	view.showGUI();
    }

}