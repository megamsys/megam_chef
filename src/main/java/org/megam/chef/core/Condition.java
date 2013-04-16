package org.megam.chef.core;

import java.util.List;
/**
 * This is a predicate class which provides a boolean true/false as seen in method "Ok". Addtionally
 * the predicate class evaluates only when input is available. The reason if the predicate turns false can be 
 * got fromt the getReason method.
 * @author ram
 *
 */
public interface Condition {
    /**
     * 
     * @return
     */
	public List<String> getReason();
	/**
	 * 
	 * @return
	 */
	public boolean ok();

	public boolean inputAvailable();

	public String name();

}
