package F21AS;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class genPanel extends JPanel  {
	
	
	private static final long serialVersionUID = 1L;
	
		String name;
		String buttonName;
		JLabel labelOfPanel;
		JButton button;
		ActionListener act;
		JTextArea data;
		
		
		public genPanel(String name,String buttonName,ActionListener action){
			
			this.name=name;
			this.buttonName=buttonName;
			this.act=action;
			//labelOfPanel.setText(name);
			//button.setText(buttonName);
		
			this.setLayout(new BorderLayout());
			button = new JButton(buttonName);
			button.addActionListener(act);
	        data = new JTextArea(20, 30);
	        labelOfPanel= new JLabel(name);
	        
	        this.add(button,BorderLayout.EAST);
	        this.add(labelOfPanel,BorderLayout.WEST);
	        this.add(data,BorderLayout.SOUTH);
			
			        
	        
		}
		
		
		public void setData(String dat){
			data.setText(dat);
		}
		
		public String getData(){
			return data.getText();
			
		}
		
		
		
	
	
}
