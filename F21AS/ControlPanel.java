/**
 * 
 */
package F21AS;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Marios Katsigiannis
 *
 */
public class ControlPanel extends JPanel implements Observer, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private restaurantModel model;
	private restaurantController controller;
	@SuppressWarnings("unused")
	private Timer timer;
	private volatile boolean mustChange;
	
	public ControlPanel(restaurantModel model, restaurantController controller) {
		this.model = model;
		this.controller = controller;
		mustChange = true;
		timer = new Timer(250, this);
		timer.start();
		update_all();
	}
	@Override
	public void invoke(int reason, Object data) {
		mustChange = true;
	}
	public void update_all() {
		if(mustChange) {
			this.removeAll(); //remove the components for all the components
			this.setLayout(new GridLayout(this.model.waiters.size() + 1, 1));
			WaiterControlPanel wcp;
			wcp = new WaiterControlPanel(controller, model, null, true); //control panel
			this.add(wcp);
			for(int i=0; i< this.model.waiters.size(); i++) {
				wcp = new WaiterControlPanel(controller, model, this.model.waiters.getWaiter(i), false);
				
				this.add(wcp);
			}
			
			mustChange = false;
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		update_all();
	}
}
