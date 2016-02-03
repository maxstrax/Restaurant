
public class invalidTableIdException extends Exception {

	public invalidTableIdException(Integer tableId) {
		super("The requested table does not exist " + tableId);
	}

	
}