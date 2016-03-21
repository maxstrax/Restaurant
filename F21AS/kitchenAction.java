package F21AS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class kitchenAction implements ActionListener{

		restaurantController con;
		Integer id;
	
	public kitchenAction(restaurantController controller){
		this.con=controller;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		con.stop();		
	}
	
}
