package F21AS;

public class invalidCategoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidCategoryException(String category) {
		super("The requested category does not exist " + category);
	}

	public invalidCategoryException() {
		this("");
	}
	
}
