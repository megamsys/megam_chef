package org.megam.chef.parser;
import java.util.Map;
/**
 * <p>DataMap interface.</p>
 *
 * @author rajthilak
* @param <T>  {@link java.lang.Object} object.
 * @version $Id: $Id
 */
public interface DataMap<T extends Object> {

	/**
	 * <p>map.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String,T> map();
	

}


