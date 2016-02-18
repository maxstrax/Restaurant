package F21AS;

public class invalidFileFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public invalidFileFormatException(String filename, int line) {
		super("Invalid file format at line " + line + " in file " + filename);
	}
	
	public invalidFileFormatException() {
		this("", 0);
	}

}
