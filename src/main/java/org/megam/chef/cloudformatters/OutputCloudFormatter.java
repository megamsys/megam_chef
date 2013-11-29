package org.megam.chef.cloudformatters;
import java.util.Map;
/**
 * 
 * @author rajthilak
 *
 * @param <T>
 */
public interface OutputCloudFormatter {

	/**
	 * @param mp_value
	 * @return
	 */
	Map<String, String> format(Map<String, String> mp_value);
	

}