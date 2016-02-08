
public class invalidQuantityException extends Exception {
	
	public invalidQuantityException(Integer quantity) {
		super("The requested quantity does not exist " + quantity);
	}

}
