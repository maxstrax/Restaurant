package F21AS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class guiFrame extends JFrame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
    
 	restaurantController controller;
    restaurantModel model;
    genPanel panel;
    genPanel kitchPanel;
    JScrollPane scrollFrame;
    private HashMap<Integer, genPanel> allPanels;
    Timer timer;
    private boolean shouldUpdate;
    
    public guiFrame(restaurantController controller, restaurantModel model) {
        super("Order Details");
        
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
        int count = model.tables.getTableIds().size();
        p.setLayout(new GridLayout(count / 2 + count % 2, 2));
     
        for(Integer tableId : model.tables.getTableIds())
        {
        	panel=new genPanel("TABLE "+tableId,"Get Bill", new  tableAction(controller,tableId));
        	p.add(panel);
        	allPanels.put(tableId, panel);
        }
     
        this.add(p);
     
        scrollFrame= new JScrollPane(p);
        
        this.add(scrollFrame);
     	this.shouldUpdate = false;
     	this.timer = new Timer(250, this);
     	this.timer.start();
    }

    public void forceUpdate() {
    	this.shouldUpdate = true;
    }
    private void update(){
    	for(Integer tableId : allPanels.keySet())
    	{
    		this.kitchPanel.setData(this.model.kitchen.toString());
    		try {
    			allPanels.get(tableId).setData(this.model.tables.getTable(tableId).toString());
    		} catch (invalidTableIdException e) {
    			e.printStackTrace();
    		}
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.shouldUpdate) {
			this.update();
			this.shouldUpdate = false;
		}
	}
     
}