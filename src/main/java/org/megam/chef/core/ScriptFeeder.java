package org.megam.chef.core;

import org.megam.chef.shell.FedInfo;

/**
 * 
 * @author rajthilak
 *
 */
public interface ScriptFeeder {

	public boolean canFeed();
	public FedInfo feed();
}
