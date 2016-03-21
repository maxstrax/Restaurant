package F21AS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class guiFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
    
	restaurantController controller;
    restaurantModel model;
    genPanel panel;
    genPanel kitchPanel;
    JScrollPane scrollFrame;
    
    public guiFrame(restaurantController controller, restaurantModel model) {
        super("Order Details");
        //this.setBounds(100, 200, 500, 500);
        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        this.controller=controller;
        this.model=model;
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setLayout(new GridLayout(1,2));
	    kitchPanel=new genPanel("Kitchen","Stop",new  kitchenAction(controller));
	    //kitchen edw
	    
	    this.add(kitchPanel);
	    JPanel p =new JPanel();
	    
	    
	    p.setLayout(new FlowLayout());
	    for(Integer tableId : model.tables.getTableIds())
	    {
	    	panel=new genPanel("TABLE "+tableId,"Get Bill", new  tableAction(controller,tableId));
	    	System.out.println(tableId);
	    	 p.add(panel);
	    }
	    this.add(p);
	    scrollFrame= new JScrollPane(p,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //panel=new genPanel("1","2",ev);
        this.add(scrollFrame);
          
   
         
        
    }
 

    	
}
