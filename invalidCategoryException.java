
public class invalidCategoryException extends Exception {

	public invalidCategoryException(String category) {
		super("The requested category does not exist " + category);
	}

	public invalidCategoryException() {
		this("");
	}
	
}
