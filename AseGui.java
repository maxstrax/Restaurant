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
    
    public AseGui() {
        super("Order Details");
        this.setBounds(10, 50, 400, 300);
        
        JPanel searchPanel = new JPanel();
        
        searchPanel.add(new JLabel("Enter order number:"));   
  
        searchField = new JTextField(5);

        result = new JTextArea(20, 36);
        
        searchPanel.add(searchField,BorderLayout.EAST);
 
        search = new JButton("View Details");  
        searchPanel.add(search,BorderLayout.CENTER);
     
        result.setEditable(false);
        searchPanel.add(result,BorderLayout.SOUTH);
        
        add(searchPanel);
        search.addActionListener(this) ;
        
    }
 
    @Override
	public void actionPerformed(ActionEvent e) {
 
    	if(e.getSource() == search){
	    	result.setText("OK");
	    	String text = searchField.getText();
	    	System.out.println(text);
	    	result.setText(text);
}
	}

    
    
    
}
