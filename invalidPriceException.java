
public class invalidPriceException extends Exception {
	
	public invalidPriceException(String price) {
		super("The requested price does not exist " + price);
	}
	
}
