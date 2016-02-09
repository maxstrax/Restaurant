
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

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
        this.model.mainMenuCategoryIndexer = new menuCategoryIndexer(this.model.mainMenu);
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
    	if(model == null)
        	return;
        try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String[] ss = null;
			int line = 0;
			itemCategory c;
			String s;
			while(br.ready()) {
				ss = br.readLine().split("\\|");
				line++;
				if(ss.length != 3)
					throw new invalidFileFormatException(filename, line);
				s = ss[2].trim();
				if(s.toLowerCase().compareTo(itemCategory.Starter.name().toLowerCase()) == 0)
					c = itemCategory.Starter;
				else if(s.toLowerCase().compareTo(itemCategory.Main.name().toLowerCase()) == 0)
					c = itemCategory.Main;
				else if(s.toLowerCase().compareTo(itemCategory.Dessert.name().toLowerCase()) == 0)
					c = itemCategory.Dessert;
				else if(s.toLowerCase().compareTo(itemCategory.Drinks.name().toLowerCase()) == 0)
					c = itemCategory.Drinks;
				else
					throw new invalidCategoryException();
				this.model.mainMenu.addItem(new menuItem(ss[0].trim(), Float.parseFloat(ss[1].trim()), c));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if(e instanceof FileNotFoundException)
				throw (FileNotFoundException)e;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			throw new invalidFileFormatException();
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
    	if(model == null)
        	return;
        try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String[] ss = null;
			int line = 0;
			while(br.ready()) {
				ss = br.readLine().split("\\|");
				line++;
				if(ss.length != 3)
					throw new invalidFileFormatException(filename, line);
				this.model.dailyOrders.addItem(new orderItem(Integer.parseInt(ss[0].trim()), ss[1].trim(), Integer.parseInt(ss[2].trim())));
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

    private String calculateTabs(String toPrint, int count) {
    	int tab_count = 0;
    	String tab = "\t";
    	while(toPrint.length() + tab_count * 4 < 24) {
    		tab_count++;
    		tab += "\t";
    	}
    	return tab;
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
    	if(model == null || this.model.dailyOrdersTableIndexer == null || this.model.mainMenuIndexer == null)
        	return "";
        String s;
        s = "TABLE SUMMARY\n";
        s += "=============\n";
        s += "TABLE " + tableId + "\n";
        float price_total = 0.0f, discount = 0.0f, price;
        orderItem oi;
        menuItem mi;
        int orderindex, menuindex, count = this.model.dailyOrdersTableIndexer.getOrdersCount(tableId);
        for(int i=0; i<count; i++) {
			orderindex = this.model.dailyOrdersTableIndexer.getIndexOf(tableId, i);
        	oi = this.model.dailyOrders.getItem(orderindex);
        	menuindex = this.model.mainMenuIndexer.getIndexOf(oi.getName());
        	mi = this.model.mainMenu.getMenu(menuindex);
        	price = (oi.getQuantity() * mi.getPrice());
        	s += mi.getName() + calculateTabs(mi.getName(), 24) + oi.getQuantity() + " * " + mi.getPrice() + " = " + price + "\n";
        	price_total += price;
        }
        s += "\n=====\n";
        s += "Total for this table :\t\t" + price_total + "\n";
        discount = this.calculateDiscount(price_total);
        s += "Discount :\t\t\t\t\t" + discount + "\n";
        s += "Discounted total :\t\t\t" + (price_total - discount) + "\n";
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
    	if(model == null || this.model.dailyOrdersTableIndexer == null)
        	return "";
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
     * @throws invalidNameException 
     */
    public String getFrequency() throws invalidNameException {
    	if(this.model == null || this.model.dailyOrdersNameIndexer == null || this.model.mainMenuIndexer == null)
        	return "";
        String s = "";
        s += "FREQUENCY REPORT\n";
        s += "================\n";
        for(String name : new TreeSet<String>(this.model.dailyOrdersNameIndexer.getNames())) {
        	if(!this.model.mainMenuIndexer.getNames().contains(name))
        		throw new invalidNameException(name);
        	s += name + calculateTabs(name, 24) + this.model.dailyOrdersNameIndexer.getOrdersCount(name) + "\n";
        }
        s += "\nDISHES NOT ORDERED\n";
        s += "==================\n";
        for(String name : new TreeSet<String>(this.model.mainMenuIndexer.getNames()))
        	if(!this.model.dailyOrdersNameIndexer.getNames().contains(name))
        		s += name + "\n";
        return s;
    }

    
    private String getMenuItem(int index) {
    	String ret = "";
    	menuItem mi = this.model.mainMenu.getMenu(index);
    	ret += mi.getName() + calculateTabs(mi.getName(), 24) + mi.getPrice();
    	return ret;
    }
    private String printMenuItems(itemCategory category) throws invalidCategoryException {
    	String s = "";
    	s += "\n" + category.toString().toUpperCase() + "\n";
    	int count = this.model.mainMenuCategoryIndexer.getCategoryCount(category);
    	for(int i =0; i<count; i++)
    		s += "\t" + getMenuItem(this.model.mainMenuCategoryIndexer.getIndexOf(category, i)) + "\n";
    	return s;
    }
    /**
     * returns a String with the Menu
     * @return
     * @throws invalidCategoryException 
     */
    public String getMenu() throws invalidCategoryException {
        if(model == null)
        	return "";
        String ret = "MENU\n";
        ret += "====\n";
        if(this.model.mainMenuCategoryIndexer.getCategories().contains(itemCategory.Starter)) {
        	ret += printMenuItems(itemCategory.Starter);
        }
        if(this.model.mainMenuCategoryIndexer.getCategories().contains(itemCategory.Main)) {
        	ret += printMenuItems(itemCategory.Main);
        }
        if(this.model.mainMenuCategoryIndexer.getCategories().contains(itemCategory.Dessert)) {
        	ret += printMenuItems(itemCategory.Dessert);
        }
        if(this.model.mainMenuCategoryIndexer.getCategories().contains(itemCategory.Drinks)) {
        	ret += printMenuItems(itemCategory.Drinks);
        }
        
        
        return ret;
    }

    /**
     * Writes a text file with the report of the day
     * @param filename 
     * @return
     * @throws IOException 
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     * @throws invalidCategoryException 
     */
    public void saveReport(String filename) throws IOException, invalidNameException, ArrayIndexOutOfBoundsException, invalidTableIdException, invalidCategoryException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(this.getMenu() + "\n");
        bw.write(this.getFrequency() + "\n");
        bw.write(this.getBills());
        bw.close();
    }

    /**
     * @param toPay 
     * @return
     */
    public float calculateDiscount(float toPay) {
        // TODO implement here
        return (toPay > 10.0f)? 0.2f * (toPay - 10.0f) : 0.0f;
    }

    /**
     * Calls setModel(newModel)
     * @param newModel
     */
    public restaurantController(restaurantModel newModel) {
        this.setModel(newModel);
    }

}