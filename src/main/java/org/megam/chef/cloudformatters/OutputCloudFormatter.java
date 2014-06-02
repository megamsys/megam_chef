package org.megam.chef.cloudformatters;
import java.util.Map;

import org.megam.chef.core.Condition;
/**
 * <p>OutputCloudFormatter interface.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public interface OutputCloudFormatter extends Condition {

	/**
	 * <p>format.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	Map<String, String> format();
	


}
