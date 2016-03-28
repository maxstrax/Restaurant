/**
 * 
 */
package F21AS;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoundedRangeModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Marios Katsigiannis
 *
 */
public class WaiterControlPanel extends JPanel implements ChangeListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addWaiter;
	private JRadioButton kitchenToTable, ordersToKitchen;
	private JSlider controlSpeed;
	
	private restaurantController controller;
	private restaurantModel model;
	private Waiter waiter;
	
	public WaiterControlPanel(restaurantController controller, restaurantModel model, Waiter waiter, boolean addable) {
		super(true);
		//this.setLayout(new GridLayout(1, 5));
		//this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.model = model;
		this.controller = controller;
		this.waiter = waiter;
		String addString = "Add ";
		if(!addable)
			addString = "";
		addWaiter=new JButton(addString + "Waiter");
		if(addable) {
			addWaiter.addActionListener(this);
 		} else {
 			addWaiter.setEnabled(false);
 		}
		this.add(this.addWaiter);
		kitchenToTable = new JRadioButton("Get orders to the kitchen");
		ordersToKitchen = new JRadioButton("Get orders to the tables");
		if(waiter != null)
			if(!waiter.getDirection())
				ordersToKitchen.setSelected(true);
			else
				kitchenToTable.setSelected(true);
		else
			ordersToKitchen.setSelected(true);
		if(!addable) {
			ordersToKitchen.setEnabled(false);
			kitchenToTable.setEnabled(false);
		}
		ButtonGroup bg = new ButtonGroup();
		bg.add(kitchenToTable);
		bg.add(ordersToKitchen);
		this.add(kitchenToTable);
		this.add(ordersToKitchen);
		
		BoundedRangeModel bRangeModel = new DefaultBoundedRangeModel(this.getWaiterSpeed(), JSlider.HORIZONTAL, 1, 5);
		controlSpeed = new JSlider(bRangeModel);
		controlSpeed.setMinorTickSpacing(1);
		controlSpeed.setMajorTickSpacing(2);
		controlSpeed.setPaintTicks(true);
		controlSpeed.setSnapToTicks(true);
		controlSpeed.setPaintLabels(true);
		if(!addable)
			controlSpeed.addChangeListener(this);
		this.add(controlSpeed);
		
	}

	private int getComponentSpeed() {
		return this.controlSpeed.getValue() * 1000;
	}
	private int getWaiterSpeed() {
		if(this.waiter != null)
			return (int)this.waiter.getWorkingTime() / 1000;
		return 1;
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.controller.setWaiterSpeed(this.waiter, this.getComponentSpeed());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Waiter waiter;
		if(kitchenToTable.isSelected())
			waiter = this.controller.addWaiter(true);
		else
			waiter = this.controller.addWaiter(false);	
		this.controller.setWaiterSpeed(waiter, this.getComponentSpeed());
	}
}
