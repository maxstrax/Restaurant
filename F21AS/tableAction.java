package F21AS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class tableAction implements ActionListener{

		restaurantController con;
		Integer id;
		guiFrame frame;
	
	public tableAction(restaurantController controller, Integer tableId){
		this.con=controller;
		this.id=tableId;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Log l = new  Log();
		try {
			l.showMessage(con.getBill(id));
			JOptionPane.showMessageDialog(frame,
	    	        con.getBill(id).replace('\t', ' '),
	    	        "Bill for Table "+id,
	    	        JOptionPane.INFORMATION_MESSAGE);
	        
		} catch (ArrayIndexOutOfBoundsException | invalidTableIdException
				| invalidNameException | invalidPriceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			l.showMessage("Unexpected error occured");
		}		
	}
	
}
