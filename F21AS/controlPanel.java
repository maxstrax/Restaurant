package F21AS;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class controlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
		JRadioButton kitchenToTable;
		JRadioButton tableToKitchen;
		JButton addWaiter;
		JSlider controlSpeed;
		static final int FPS_MIN = 0;
		static final int FPS_MAX = 30;
		static final int FPS_INIT = 15;
		
		
		public controlPanel() {
			super();
			
			addWaiter = new JButton("Add Waiter");
			kitchenToTable = new JRadioButton("Get orders to the kitchen");
			tableToKitchen = new JRadioButton("Get orders to the tables");
			ButtonGroup bg = new ButtonGroup();
			bg.add(kitchenToTable);
			bg.add(tableToKitchen);
			
			addWaiter.putClientProperty("JComponent.sizeVariant" , "mini");
			
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.add(kitchenToTable);
			p.add(tableToKitchen);
			controlSpeed = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
			
			this.setLayout(new BorderLayout());
			this.add(addWaiter, BorderLayout.WEST);
			this.add(p, BorderLayout.CENTER);
			this.add(controlSpeed, BorderLayout.EAST);
		}
		
		
}
