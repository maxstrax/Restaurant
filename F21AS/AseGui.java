package F21AS;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


 
public class AseGui extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField searchField;
	JButton search;
    JTextArea result;
    restaurantController controller;
    restaurantModel model;
    
    public AseGui(restaurantController controller, restaurantModel model) {
        super("Order Details");
        this.setBounds(100, 50, 360, 340);
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
        //Fix the spacing issues
        result.setFont(new Font("monospaced", Font.PLAIN, 12)); //ensure each character has a fixed size
        result.setTabSize(4); //ensure tabs match the file output
        searchPanel.add(result,BorderLayout.SOUTH);
        
        add(searchPanel);
        search.addActionListener(this) ;
        
    }
 
    @Override
	public void actionPerformed(ActionEvent e) {
 
    	if(e.getSource() == search){
	    	String text = searchField.getText();
	    	try {
	    		result.setText(controller.getBill(Integer.parseInt(text)));
			} catch (ArrayIndexOutOfBoundsException | invalidTableIdException
					| invalidNameException e1) {
				// TODO Auto-generated catch block
				result.setText("A table with that number is not in the orders list");
				e1.printStackTrace();
			}
	    	
    	}
	}

    
    
    
}
