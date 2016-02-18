package F21AS;

public class invalidQuantityException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidQuantityException(Integer quantity) {
		super("The requested quantity does not exist " + quantity);
	}

}
