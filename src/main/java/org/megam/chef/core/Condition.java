package org.megam.chef.core;

import java.util.List;
/**
 * This is a predicate class which provides a boolean true/false as seen in method "Ok". Addtionally
 * the predicate class evaluates only when input is available. The reason if the predicate turns false can be
 * got fromt the getReason method.
 *
 * @author ram
 * @version $Id: $Id
 */
public interface Condition {
	/**
	 * <p>getReason.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getReason();
	/**
	 * <p>ok.</p>
	 *
	 * @return a boolean.
	 */
	public boolean ok();

	/**
	 * <p>inputAvailable.</p>
	 *
	 * @return a boolean.
	 */
	public boolean inputAvailable();

	/**
	 * <p>name.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String name();

}
