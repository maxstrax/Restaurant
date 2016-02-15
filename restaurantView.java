
import javax.swing.SwingUtilities;

/**
 * Used to provide a Graphical User Interface, as well as command line interface to the user.
 */
public class restaurantView {


    /**
     * 
     */
    private restaurantController Controller;

    /**
     * 
     */
    private restaurantModel Model;


    /**
     * @param Controller 
     * @param Model 
     * @return
     */
    public void setup(restaurantController Controller, restaurantModel Model) {
       this.Controller=Controller;
       this.Model=Model;
    }

    /**
     * @param Controller 
     * @param Model
     */
    public restaurantView(restaurantController Controller, restaurantModel Model) {
    	this.setup(Controller, Model);    
    }

    /**
     * @return
     */
    public void showGUI() {
    	SwingUtilities.invokeLater(new Runnable() {
    		 
            @Override
            public void run() {
                new AseGui(Controller,Model).setVisible(true);
                
            }
        });
    	
    }

    /**
     * @return
     */
    public void showUI() {
        // TODO implement here
    }

}

