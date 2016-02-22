package F21AS;

/**
 * 
 * @author Marios Katsigiannis
 * This exception denotes either an empty name string or an inexistent one, when searching
 */
public class invalidNameException extends Exception {

	/**
	 *This class is not expected to be serialized!
	 */
	private static final long serialVersionUID = 1L;

	public invalidNameException(String name) {
		super("The requested name does not exist, or it is empty: " + name);
	}

	public invalidNameException() {
		this("Name not given!");
	}
}
