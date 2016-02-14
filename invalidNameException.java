
public class invalidNameException extends Exception {

	/**
	 * Automatically Generated. This class is not expected to be serialized!
	 */
	private static final long serialVersionUID = 1L;

	public invalidNameException(String name) {
		super("The requested name does not exist, or it is empty: " + name);
	}

	public invalidNameException() {
		this("Name not given!");
	}
}
