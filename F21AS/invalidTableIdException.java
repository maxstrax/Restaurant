package F21AS;

public class invalidTableIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidTableIdException(Integer tableId) {
		super("The requested table does not exist " + tableId);
	}

	public invalidTableIdException() {
		super("The requested table does not exist");
	}
	
}