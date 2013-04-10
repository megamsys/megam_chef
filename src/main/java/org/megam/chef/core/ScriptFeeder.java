package org.megam.chef.core;

/**
 * 
 * @author rajthilak
 *
 */
public interface ScriptFeeder {

	public boolean canFeed();
	public String feed();
}
