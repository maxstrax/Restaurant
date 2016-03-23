package F21AS;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class guiFrame extends JFrame{
 
 private static final long serialVersionUID = 1L;
    
 restaurantController controller;
    restaurantModel model;
    genPanel panel;
    genPanel kitchPanel;
    JScrollPane scrollFrame;
    private ArrayList<genPanel> panelRef;
    private HashMap<Integer, genPanel> allPanels;

    
    
    public guiFrame(restaurantController controller, restaurantModel model) {
     
        super("Order Details");
        
     //this.panelRef = new ArrayList<Integer> ();
     
        this.setBounds(100, 100, 1000, 600);
        this.allPanels=new HashMap<Integer,genPanel>();
        //this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        
        this.controller=controller;
        this.model=model;
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     
     this.setLayout(new GridLayout(1,2));
     
     kitchPanel=new genPanel("Kitchen","Stop",new  kitchenAction(controller));
     this.add(kitchPanel);
     
     JPanel p =new JPanel();
     
     p.setLayout(new GridLayout(model.tables.getTableIds().size(),1));
     
     for(Integer tableId : model.tables.getTableIds())
     {
      panel=new genPanel("TABLE "+tableId,"Get Bill", new  tableAction(controller,tableId));
      System.out.println(tableId);
       p.add(panel);
       allPanels.put(tableId, panel);
     }
     
     this.add(p);
     
     scrollFrame= new JScrollPane(p);
        
     this.add(scrollFrame);
          
     
    }
 
    public void update(){
     for(Integer tableId : allPanels.keySet())
     {
      try {
    allPanels.get(tableId).setData(this.model.tables.getTable(tableId).toString());
   } catch (invalidTableIdException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
      
     }
     
    }
     
}