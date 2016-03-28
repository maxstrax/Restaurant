package F21AS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class guiFrame extends JFrame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
    
 	restaurantController controller;
    restaurantModel model;
    genPanel panel;
    genPanel kitchPanel;
    JScrollPane scrollFrame;
    private ControlPanel controlPanel;
    private HashMap<Integer, genPanel> allPanels;
    Timer timer;
    private boolean shouldUpdate;

    
    public guiFrame(restaurantController controller, restaurantModel model) {
        super("Order Details");
        
        this.setBounds(100, 50, 1300, 740);
        this.allPanels=new HashMap<Integer,genPanel>();
        
        this.controller=controller;
        this.model=model;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
 
        controlPanel = new ControlPanel(this.model, this.controller);
        this.model.waiters.addObserver(controlPanel);      
        kitchPanel=new genPanel("Kitchen","Stop",new  kitchenAction(controller));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(controlPanel, BorderLayout.NORTH);
        leftPanel.add(kitchPanel, BorderLayout.CENTER);
        this.add(leftPanel);
        
        JPanel p =new JPanel();
        int count = model.tables.getTableIds().size();
        p.setLayout(new GridLayout(count / 2 + count % 2, 2));
     
        for(Integer tableId : model.tables.getTableIds())
        {
        	panel=new genPanel("TABLE "+tableId,"Get Bill", new  tableAction(controller,tableId));
        	p.add(panel);
        	allPanels.put(tableId, panel);
        }
//        JPanel p0 = new JPanel();
//        p0.setLayout(new BorderLayout());
//        p0.add(p, BorderLayout.WEST);
        
        scrollFrame= new JScrollPane(p);
        this.add(scrollFrame); //, BorderLayout.EAST);
        
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