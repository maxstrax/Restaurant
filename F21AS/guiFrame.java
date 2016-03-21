package F21AS;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class guiFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
    
	restaurantController controller;
    restaurantModel model;
    genPanel panel;
    ActionListener ev;
    
    public guiFrame(restaurantController controller, restaurantModel model) {
        super("Order Details");
        this.setBounds(100, 200, 360, 340);
        this.controller=controller;
        this.model=model;
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLayout(new BorderLayout());
	    for(Integer tableId : model.tables.getTableIds())
	    {
	    	
	    	panel=new genPanel("TABLE "+tableId,"Get Bill", new  tableAction(controller,tableId));
	    }
        //panel=new genPanel("1","2",ev);
        
        this.add(panel,BorderLayout.WEST);
        
       
   
         
        
    }
 

    	
}
