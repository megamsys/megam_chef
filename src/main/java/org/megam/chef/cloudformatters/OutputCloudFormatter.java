package org.megam.chef.cloudformatters;
import java.util.Map;

import org.megam.chef.core.Condition;
/**
 * 
 * @author rajthilak
 *
 * @param <T>
 */
public interface OutputCloudFormatter extends Condition {

	/**
	 * @param mp_value
	 * @return
	 */
	Map<String, String> format();
	


}