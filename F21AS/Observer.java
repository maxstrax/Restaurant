/**
 * 
 */
package F21AS;

/**
 * @author Marios Katsigiannis
 *
 */
public interface Observer {
	public void invoke(int reason, Object data);
}
