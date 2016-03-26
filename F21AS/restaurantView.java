package F21AS;

/**
 * Used to provide a Graphical User Interface, as well as command line interface to the user.
 */
public class restaurantView implements Observer {


    /**
     * 
     */
    private restaurantController Controller;

    /**
     * 
     */
    private restaurantModel Model;

    private guiFrame frame = null;
    //volatile private boolean mustChange = false;
    /**
     * @param Controller 
     * @param Model 
     * @return
     */
    public void setup(restaurantController Controller, restaurantModel Model) {
       this.Controller=Controller;
       this.Model=Model;
       this.Model.kitchen.addObserver(this);
       this.Model.tables.addObserver(this);
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
    	frame = new guiFrame(Controller,Model);
        frame.setVisible(true);
    }


	@Override
	public void invoke(int reason, Object data) {
		if(frame != null)
			this.frame.forceUpdate();
	}

}

