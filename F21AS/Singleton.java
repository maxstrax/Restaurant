/**
 * 
 */
package F21AS;

/**
 * @author Marios Katsigiannis
 *
 * usage: in each function of a class extending Sigleton use this boilerplate code:
 * class SingletonClass1 extends Singleton {
 * 		public SingletonClass1() {
 * 			super();
 * 		}
 * 		public void function1() {
 *      	if(!Sigleton.checkReference(this))
 *      	     ((SingletonClass1)Sigleton.getReference()).function1();
 *      	else {
 *      	     .... your code here .....
 *      	}
 *      }
 * }
 */
public class Singleton {
	/**
	 * The one and only reference of the pattern
	 */
	private static Singleton reference = null;
	/**
	 * the default constructor enforces that the reference will be set by exactly one
	 * object before it is being used
	 */
	public Singleton() {
		Singleton.setReference(this);
	}
	/**
	 * Sets the reference of the singleton only if it has not been set to an object before.
	 * If called with null as parameter will not set the reference but will return whether the
	 * reference has been previously set.
	 * @param s the object to set the reference to.
	 * @return true if the reference was not set, false otherwise.
	 */
	public static boolean setReference(Singleton s) {
		if(Singleton.reference == null) {
			Singleton.reference = s;
			return true;
		}
		return false;
	}
	/**
	 * Returns the reference of the singleton
	 * @return
	 */
	public static Singleton getReference() {
		return Singleton.reference;
	}
	/**
	 * Checks whether the reference of the singleton is set to the specified object
	 * @param ref
	 */
	public static boolean checkReference(Singleton ref) {
		return ref == Singleton.reference;
	}
}
