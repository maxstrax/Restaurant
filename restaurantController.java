
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

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
        this.model = newModel;
    }

    /**
     * Used after the data of the model has been loaded by loadMenu and loadOrders.
     * Creates the indexers needed to efficiently search through the data.
     * populates mainMenuIndexer and dailyOrdersTableIndexer of the model
     * @return
     */
    public void createIndexers() {
        if(model == null)
        	return;
        this.model.dailyOrdersNameIndexer = new ordersNameIndexer(this.model.dailyOrders);
        this.model.dailyOrdersTableIndexer = new ordersTableIndexer(this.model.dailyOrders);
        this.model.mainMenuIndexer = new menuIndexer(this.model.mainMenu);
    }

    /**
     * Loads the menu text file and populates the mainMenu object of the model
     * @param filename 
     * @return
     * @throws FileNotFoundException 
     * @throws invalidFileFormatException 
     * @throws invalidCategoryException 
     */
    public void loadMenu(String filename) throws FileNotFoundException, invalidFileFormatException, invalidCategoryException {
        try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String[] ss = null;
			int line = 0;
			while(br.ready()) {
				ss = br.readLine().split("|");
				line++;
				if(ss.length != 3)
					throw new invalidFileFormatException(filename, line);
				this.model.mainMenu.addItem(new menuItem(ss[0], Float.parseFloat(ss[1]), itemCategory.valueOf(ss[2])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if(e instanceof FileNotFoundException)
				throw (FileNotFoundException)e;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			throw new invalidCategoryException();
		}
    }

    /**
     * Loads the menu text file and populates the dailyOrders object of the model
     * @param filename 
     * @return
     * @throws invalidFileFormatException 
     * @throws FileNotFoundException 
     */
    public void loadOrders(String filename) throws invalidFileFormatException, FileNotFoundException {
        try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String[] ss = null;
			int line = 0;
			while(br.ready()) {
				ss = br.readLine().split("|");
				line++;
				if(ss.length != 3)
					throw new invalidFileFormatException(filename, line);
				this.model.dailyOrders.addItem(new orderItem(Integer.parseInt(ss[0]), ss[1], Integer.parseInt(ss[2])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if(e instanceof FileNotFoundException)
				throw (FileNotFoundException)e;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
    }

    /**
     * Returns a String with the bill of a specified tableId
     * @param tableId 
     * @return
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     * @throws invalidNameException 
     */
    public String getBill(Integer tableId) throws ArrayIndexOutOfBoundsException, invalidTableIdException, invalidNameException {
        String s;
        s = "TABLE SUMMARY\n";
        s += "=============\n";
        s += "TABLE " + tableId;
        float price_total = 0.0f, discount = 0.0f;
        orderItem oi;
        menuItem mi;
        int orderindex, menuindex, count = this.model.dailyOrdersTableIndexer.getOrdersCount(tableId);
        for(int i=0; i<count; i++) {
			orderindex = this.model.dailyOrdersTableIndexer.getIndexOf(tableId, i);
        	oi = this.model.dailyOrders.getItem(orderindex);
        	menuindex = this.model.mainMenuIndexer.getIndexOf(oi.getName());
        	mi = this.model.mainMenu.getMenu(menuindex);
        	s += mi.getName() + "\t" + oi.getQuantity() + " * " + mi.getPrice() + " = " + (oi.getQuantity() * mi.getPrice());
        }
        s += "=====";
        s += "Total for this table :\t\t" + price_total;
        discount = this.calculateDiscount(price_total);
        s += "Discount :\t\t\t\t" + discount;
        s += "Discounted total :\t\t\t" + (price_total - discount);
        return s;
    }

    /**
     * returns a string with a bill for each existing tableId
     * @return
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     */
    public String getBills() throws ArrayIndexOutOfBoundsException, invalidTableIdException, invalidNameException {
        String s = "";
        Set<Integer> tables = this.model.dailyOrdersTableIndexer.getTableIds();
        for(int tableId : tables) {
        	s += this.getBill(tableId) + "\n";
        }
        return s.substring(0, s.length() - 2);
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
        if(model == null)
        	return "";
        String ret = "MENU\n";
        ret += "====\n";
        
        //TODO: add the menu!
        
        
        return ret;
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
        return (toPay > 10.0f)? 0.2f * toPay : 0.0f;
    }

    /**
     * Calls setModel(newModel)
     * @param newModel
     */
    public restaurantController(restaurantModel newModel) {
        this.setModel(newModel);
    }

}