package F21AS;

public class invalidPriceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidPriceException(String price) {
		super("The requested price does not exist " + price);
	}
	
}
