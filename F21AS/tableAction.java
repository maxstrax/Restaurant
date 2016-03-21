package F21AS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tableAction implements ActionListener{

		restaurantController con;
		Integer id;
	
	public tableAction(restaurantController controller, Integer tableId){
		this.con=controller;
		this.id=tableId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Log l = new  Log();
		try {
			l.showMessage(con.getBill(id));
		} catch (ArrayIndexOutOfBoundsException | invalidTableIdException
				| invalidNameException | invalidPriceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			l.showMessage("Unexpected error occured");
		}		
	}
	
}
