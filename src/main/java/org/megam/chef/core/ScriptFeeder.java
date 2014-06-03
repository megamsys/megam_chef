package org.megam.chef.core;

import org.megam.chef.shell.FedInfo;

/**
 * <p>ScriptFeeder interface.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public interface ScriptFeeder {

	/**
	 * <p>canFeed.</p>
	 *
	 * @return a boolean.
	 */
	public boolean canFeed();
	/**
	 * <p>feed.</p>
	 *
	 * @return a {@link org.megam.chef.shell.FedInfo} object.
	 */
	public FedInfo feed();
}
