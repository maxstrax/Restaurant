import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/*
 * author: Marios Katsigiannis
 */
public class menuCategoryIndexer {

    /**
     * 
     */
    private HashMap<itemCategory, LinkedList<Integer>> index;



    public Integer getIndexOf(itemCategory category, Integer itemNo) throws invalidCategoryException {
    	 if(!this.index.containsKey(category))
         	throw new invalidCategoryException(category.toString());
    	 else if(this.index.get(category).size() <= itemNo)
    		 throw new ArrayIndexOutOfBoundsException();
         else {
         	return index.get(category).get(itemNo);
         }
    }


    public void create(menu mainMenu) {
    	this.index.clear();
    	LinkedList<Integer> l;
    	for(int i=0; i<mainMenu.count(); i++) {
    		if(!this.index.containsKey(mainMenu.getMenu(i).getCategory())) {
    			l = new LinkedList<Integer>();
    			this.index.put(mainMenu.getMenu(i).getCategory(), l);
    		}
    		index.get(mainMenu.getMenu(i).getCategory()).add(i);		
    	}
    }


    public menuCategoryIndexer(menu mainMenu) {
    	this.index = new HashMap<itemCategory, LinkedList<Integer>>(); //init our map
    	this.create(mainMenu);
    }


    public Set<itemCategory> getCategories() {
    	return this.index.keySet();
    }
    
    public Integer getCategoryCount(itemCategory c) throws invalidCategoryException {
    	if(this.index.containsKey(c))
    		return this.index.get(c).size();
    	else
    		throw new invalidCategoryException(c.toString());
    }
}
