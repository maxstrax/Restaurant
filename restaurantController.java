/**
 * Used to manipulate the data of the model.
 */
public class restaurantController {

    /**
     * 
     */
    private restaurantModel model;

    /**
     * @param newModel 
     * @return
     */
    public void setModel(restaurantModel newModel) {
        // TODO implement here
    }

    /**
     * Used after the data of the model has been loaded by loadMenu and loadOrders.
     * Creates the indexers needed to efficiently search through the data.
     * populates mainMenuIndexer and dailyOrdersTableIndexer of the model
     * @return
     */
    public void createIndexers() {
        // TODO implement here
    }

    /**
     * Loads the menu text file and populates the mainMenu object of the model
     * @param filename 
     * @return
     */
    public void loadMenu(String filename) {
        // TODO implement here
    }

    /**
     * Loads the menu text file and populates the dailyOrders object of the model
     * @param filename 
     * @return
     */
    public void loadOrders(String filename) {
        // TODO implement here
    }

    /**
     * Returns a String with the bill of a specified tableId
     * @param tableId 
     * @return
     */
    public String getBill(Integer tableId) {
        // TODO implement here
        return "";
    }

    /**
     * returns a string with a bill for each existing tableId
     * @return
     */
    public String getBills() {
        // TODO implement here
        return "";
    }

    /**
     * returns a string with the frequency with which an item was orders
     * @return
     */
    public String getFrequency() {
        // TODO implement here
        return "";
    }

    /**
     * returns a String with the Menu
     * @return
     */
    public String getMenu() {
        // TODO implement here
        return "";
    }

    /**
     * Writes a text file with the report of the day
     * @param filename 
     * @return
     */
    public void saveReport(String filename) {
        // TODO implement here
    }

    /**
     * @param toPay 
     * @return
     */
    public float calculateDiscount(float toPay) {
        // TODO implement here
        return 0.0f;
    }

    /**
     * Calls setModel(newModel)
     * @param newModel
     */
    public restaurantController(restaurantModel newModel) {
        // TODO implement here
    }

}