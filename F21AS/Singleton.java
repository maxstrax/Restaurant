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
	private static Singleton reference = null;
	public Singleton() {
		Singleton.setReference(this);
	}
	public static boolean setReference(Singleton s) {
		if(Singleton.reference == null) {
			Singleton.reference = s;
			return true;
		}
		return false;
	}
	public static Singleton getReference() {
		return Singleton.reference;
	}
	public static boolean checkReference(Singleton ref) {
		return ref == Singleton.reference;
	}
}
