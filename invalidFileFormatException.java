
public class invalidFileFormatException extends Exception {

	public invalidFileFormatException(String filename, int line) {
		super("Invalid file format at line " + line + " in file " + filename);
	}
	
	public invalidFileFormatException() {
		this("", 0);
	}

}
