import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;
 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;


 
public class AseGui extends JFrame implements ActionListener{

	JTextField searchField;
	JButton search;
    JTextArea result;
    restaurantController controller;
    restaurantModel model;
    
    public AseGui(restaurantController controller, restaurantModel model) {
        super("Order Details");
        this.setBounds(100, 50, 560, 340);
        this.controller=controller;
        this.model=model;
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel searchPanel = new JPanel();
        
        searchPanel.add(new JLabel("Enter number of table:"));
        add(searchPanel);
  
        searchField = new JTextField(5);

        result = new JTextArea(20, 50);
        
        searchPanel.add(searchField,BorderLayout.EAST);
 
        search = new JButton("View details of bill");  
        searchPanel.add(search,BorderLayout.CENTER);
     
        JPanel panel1 = new JPanel();
        
        result.setEditable(false);
        searchPanel.add(result,BorderLayout.SOUTH);
        
        add(searchPanel);
        search.addActionListener(this) ;
        
    }
 
    @Override
	public void actionPerformed(ActionEvent e) {
 
    	if(e.getSource() == search){
	    	result.setText("A table with that number is not in the orders list");
	    	String text = searchField.getText();
	    	try {
	    		result.setText(controller.getBill(Integer.parseInt(text)));
			} catch (ArrayIndexOutOfBoundsException | invalidTableIdException
					| invalidNameException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
}
	}

    
    
    
}
