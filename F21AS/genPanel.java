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
		
			this.setBounds(20, 20, 20, 10);
			this.setLayout(new BorderLayout());
			
			button = new JButton(buttonName);
			button.addActionListener(act);
	        data = new JTextArea(20, 10);
	        labelOfPanel= new JLabel(name);
	        
	        JPanel p = new JPanel();
	        
	        p.setLayout(new BorderLayout());
	        p.add(button,BorderLayout.EAST);
	        p.add(labelOfPanel,BorderLayout.WEST);
	        
	        this.add(p, BorderLayout.NORTH);
	        this.add(data,BorderLayout.CENTER);
			
			        
	        
		}
		
		
		public void setData(String dat){
			data.setText(dat);
		}
		
		public String getData(){
			return data.getText();
			
		}
		
	
		
	
	
}
