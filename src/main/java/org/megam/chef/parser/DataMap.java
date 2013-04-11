package org.megam.chef.parser;
import java.util.Map;
/**
 * 
 * @author rajthilak
 *
 * @param <T>
 */
public interface DataMap<T extends Object> {

	/**
	 * @param args
	 */
	
	public Map<String,T> map();
	

}


