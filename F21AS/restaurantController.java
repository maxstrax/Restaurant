package F21AS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
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
        this.model.tables.addTables(this.model.dailyOrdersTableIndexer.getTableIds());
    }

    /**
     * Loads the menu text file and populates the mainMenu object of the model
     * @param filename 
     * @return
     * @throws invalidFileFormatException 
     * @throws invalidCategoryException 
     * @throws IOException 
     */
    public void loadMenu(String filename) throws invalidFileFormatException, invalidCategoryException, IOException {
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
			this.model.invokeAllNoReason();
		} catch (IllegalArgumentException e) { ///all other exception types must be returned as is
			throw new invalidFileFormatException();
		}
    }

    /**
     * Loads the menu text file and populates the dailyOrders object of the model
     * @param filename 
     * @return
     * @throws invalidFileFormatException 
     * @throws IOException 
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     */
    public void loadOrders(String filename) throws invalidFileFormatException, IOException, invalidNameException, invalidTableIdException {
    	if(model == null)
        	return;
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String[] ss = null;
		int line = 0;
		int tableId, quantity;
		String name;
		while(br.ready()) {
			ss = br.readLine().split("\\|");
			line++;
			if(ss.length != 3)
				throw new invalidFileFormatException(filename, line);
	        try {
	        	tableId = Integer.parseInt(ss[0].trim());
	        } catch (IllegalArgumentException e) {
	        	throw new invalidTableIdException();
	        }
			name = ss[1].trim();
			if(name.length() == 0)
				throw new invalidNameException(name);
			try {
				quantity = Integer.parseInt(ss[2].trim());
			} catch (IllegalArgumentException e) {
	        	throw new invalidTableIdException();
	        }
			this.model.dailyOrders.addItem(new orderItem(tableId, name, quantity));
		}
		br.close();
    }

    private String calculateTabs(String toPrint, int count) {
    	int tab_count = 0;
    	String tab = "\t";
    	while(toPrint.length() + tab_count * 4 < count) {
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
     * @throws invalidPriceException 
     */
    public String getBill(Integer tableId) throws ArrayIndexOutOfBoundsException, invalidTableIdException, invalidNameException, invalidPriceException {
    	if(model == null || this.model.mainMenuIndexer == null)// || this.model.dailyOrdersTableIndexer == null)
        	return "";
        String s;
        s = "TABLE SUMMARY\n";
        s += "=============\n";
        s += "TABLE " + tableId + "\n";
        float price_total = 0.0f, discount = 0.0f, price;
        orderItem oi;
        menuItem mi;
        int menuindex, count = this.model.tables.getTable(tableId).countItems();
        for(int i=0; i<count; i++) {
        	oi = this.model.tables.getTable(tableId).getItem(i);
        	menuindex = this.model.mainMenuIndexer.getIndexOf(oi.getName());
        	mi = this.model.mainMenu.getMenu(menuindex);
        	price = (oi.getQuantity() * mi.getPrice());
        	s += mi.getName() + calculateTabs(mi.getName(), 24) + oi.getQuantity() + " * " + String.format("%2.2f", mi.getPrice()) + " = " + price + "\n";
        	price_total += price;
        }
        s += "\n=====\n";
        s += "Total for this table :\t\t" + String.format("%.2f",price_total) + "\n";
        discount = restaurantController.calculateDiscount(price_total);
        s += "Discount :\t\t\t\t\t" + String.format("%.2f", discount) + "\n";
        float total = (price_total - discount);
        s += "Discounted total :\t\t\t" + String.format("%.2f", total) + "\n";
        return s;
    }

    /**
     * returns a string with a bill for each existing tableId
     * @return
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     * @throws invalidPriceException 
     */
    public String getBills() throws ArrayIndexOutOfBoundsException, invalidTableIdException, invalidNameException, invalidPriceException {
    	if(model == null || this.model.dailyOrdersTableIndexer == null)
        	return "";
        String s = "", p;
        float price = 0;
        for(int tableId : new TreeSet<Integer>(this.model.tables.getTableIds())) {
        	s += this.getBill(tableId) + "\n";
        	p = s.substring(s.lastIndexOf("Discounted total :\t\t\t", s.length()) + "Discounted total :\t\t\t".length(),
        			s.length());
        	price += Float.parseFloat(p.replace(',', '.'));
        }
        s += "Grant Total : \t\t\t\t" + price + "\n";
        return s.substring(0, s.length() - 2);
    }

    /**
     * returns a string with the frequency with which an item was orders
     * @return
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     */
    public String getFrequency() throws invalidNameException, ArrayIndexOutOfBoundsException, invalidTableIdException {
    	if(this.model == null || this.model.mainMenuIndexer == null)// || this.model.dailyOrdersNameIndexer == null)
        	return "";
        String s = "";
        s += "FREQUENCY REPORT\n";
        s += "================\n";
        String name;
        TreeMap<String, Integer> ordered = new TreeMap<String, Integer>();
        for(Integer tableid : this.model.tables.getTableIds())
        	for(int i=0; i<this.model.tables.getTable(tableid).countItems(); i++) {
        		name = this.model.tables.getTable(tableid).getItem(i).getName();
        		if(!ordered.containsKey(name))
        			ordered.put(name, (Integer)1);
        		else
        			ordered.put(name, ordered.get(name) + 1);
        	}
        for(Map.Entry<String, Integer> pair : ordered.entrySet()) {
        	if(!this.model.mainMenuIndexer.getNames().contains(pair.getKey()))
        		throw new invalidNameException(pair.getKey());
        	
        	s += pair.getKey() + calculateTabs(pair.getKey(), 24) + pair.getValue() + "\n";
        }
        s += "\nDISHES NOT ORDERED\n";
        s += "==================\n";
        for(String Name : new TreeSet<String>(this.model.mainMenuIndexer.getNames()))
        	if(!ordered.containsKey(Name))
        		s += Name + "\n";
        return s;
    }

    
    private String getMenuItem(int index) {
    	String ret = "";
    	menuItem mi = this.model.mainMenu.getMenu(index);
    	ret += mi.getName() + calculateTabs(mi.getName(), 20) + mi.getPrice();
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
     * Creates the report of the day
     * @param filename 
     * @return
     * @throws IOException 
     * @throws invalidNameException 
     * @throws invalidTableIdException 
     * @throws ArrayIndexOutOfBoundsException 
     * @throws invalidCategoryException 
     * @throws invalidPriceException 
     */
    public String createReport() throws IOException, invalidNameException, ArrayIndexOutOfBoundsException, invalidTableIdException, invalidCategoryException, invalidPriceException {
    	return this.getMenu() + "\n"+ this.getFrequency() + "\n" + this.getBills();
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
     * @throws invalidPriceException 
     */
    public void saveReport(String filename) throws IOException, invalidNameException, ArrayIndexOutOfBoundsException, invalidTableIdException, invalidCategoryException, invalidPriceException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(this.createReport());
        bw.close();
    }

    /**
     * @param toPay 
     * @return
     * @throws invalidPriceException 
     */
    public static float calculateDiscount(float toPay) throws invalidPriceException {
    	if(toPay < 0.0)
    		throw new invalidPriceException(toPay);
        return (toPay > 10.0f)? 0.2f * (toPay - 10.0f) : 0.0f;
    }

    /**
     * Calls setModel(newModel)
     * @param newModel
     */
    public restaurantController(restaurantModel newModel) {
        this.setModel(newModel);
    }

    public void operateTheRestaurant() {
    	this.model.operate = true;
    	this.model.threads.runAll();
    	while(this.model.operate && this.model.dailyOrders.countItems() != 0)
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	this.model.operate = false;
    	this.model.threads.waitAll();
    }

    public boolean isInOperation() {
    	return this.model.operate;
    }
    public void stop() {
    	this.model.operate = false;
    }
}