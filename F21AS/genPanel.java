package F21AS;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class genPanel extends JPanel  {
	
	
	private static final long serialVersionUID = 1L;
	
		String name;
		String buttonName;
		JLabel labelOfPanel;
		JLabel labelOfSlider;
		JButton button;
		ActionListener act;
		JTextArea data;

		JRadioButton kitchenToTable;
		JRadioButton tableToKitchen;
		JButton addWaiter;
		JSlider controlSpeed;
		
		static final int FPS_MIN = 0;
		static final int FPS_MAX = 10;
		static final int FPS_INIT = 0;
		
		public genPanel(String name,String buttonName,ActionListener action){
			
			this.name=name;
			this.buttonName=buttonName;
			this.act=action;
		
			this.setBounds(20, 20, 20, 10);
			this.setLayout(new BorderLayout());
			
			button = new JButton(buttonName);
			button.addActionListener(act);
	        data = new JTextArea(20, 10);
	        labelOfPanel= new JLabel(name);
	        
	        JPanel p = new JPanel();
	        
	        p.setLayout(new BorderLayout());
	        p.add(button,BorderLayout.EAST);
	        p.add(labelOfPanel,BorderLayout.WEST);
	        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        this.add(p, BorderLayout.NORTH);
	        this.add(data,BorderLayout.CENTER);

		}
		
		
		public genPanel(String name, String buttonName, ActionListener action,JButton addWaiter,JRadioButton kitchenToTable,JRadioButton tableToKitchen,JSlider controlSpeed) {
			super();
			this.name=name;
			this.buttonName=buttonName;
			this.act=action;
			this.addWaiter=addWaiter;
			this.kitchenToTable=kitchenToTable;
			this.tableToKitchen=tableToKitchen;
			this.controlSpeed=controlSpeed;
			this.setBounds(20, 20, 20, 10);
			this.setLayout(new BorderLayout());
			
			button = new JButton(buttonName);
			button.addActionListener(act);
	        data = new JTextArea(20, 10);
	        labelOfPanel= new JLabel(name);
	        
	        //data.setBounds(0,0,300,50);
			addWaiter.putClientProperty("JComponent.sizeVariant" , "mini");
	        
	        JPanel p = new JPanel();
	        
	        p.setLayout(new BorderLayout());
	        p.add(button,BorderLayout.EAST);
	        p.add(labelOfPanel,BorderLayout.WEST);
	        
	        p.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        JPanel p1 = new JPanel();
	        p1.add(addWaiter,BorderLayout.CENTER);
	        
			JPanel p2 = new JPanel();
			p2.setLayout(new BorderLayout());
			p2.add(kitchenToTable, BorderLayout.EAST);
			p2.add(tableToKitchen, BorderLayout.WEST);
			labelOfSlider= new JLabel("Speed Controller");
			
			BoundedRangeModel bRangeModel = new DefaultBoundedRangeModel(FPS_INIT, JSlider.HORIZONTAL, FPS_MIN, FPS_MAX);
			controlSpeed = new JSlider(bRangeModel);
			controlSpeed.setMinorTickSpacing(2);
			controlSpeed.setMajorTickSpacing(FPS_MAX/4);
			controlSpeed.setPaintTicks(true);
			controlSpeed.setSnapToTicks(true);
			controlSpeed.setPaintLabels(true);
			
			JPanel p3 = new JPanel();
			p3.setLayout(new BorderLayout());
			p3.add(labelOfSlider, BorderLayout.NORTH);
			p3.add(controlSpeed, BorderLayout.SOUTH);
			
			p1.setLayout(new BorderLayout());
			p1.add(addWaiter, BorderLayout.WEST);
			p1.add(p2, BorderLayout.CENTER);
			p1.add(p3, BorderLayout.EAST);
			
			this.add(p, BorderLayout.NORTH);
	        this.add(data,BorderLayout.CENTER);
	        this.add(p1, BorderLayout.SOUTH);
	        
	        p1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	        
		}


		public void setData(String dat){
			data.setText(dat);
		}
		
		public String getData(){
			return data.getText();
			
		}
		
	
		
	
	
}
