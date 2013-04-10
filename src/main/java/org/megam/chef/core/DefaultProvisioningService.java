package org.megam.chef.core;

import org.megam.chef.exception.ProvisionerException;

/**
 * 
 * @author rajthilak
 * 
 */
public class DefaultProvisioningService<T> extends
		AbstractProvisioningService<T> {

	/**
	 * 
	 * @throws ProvisionerException
	 */
	public DefaultProvisioningService() throws ProvisionerException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.ProvisioningService#provision(java.lang.String)
	 */
	@Override
	/**
	 * @throws ProvisionerException
	 */
	public T provision(String jsonString) throws ProvisionerException {
		throw new RuntimeException(
				"Not implemented yet. Use CHEF_WITH_SHELL in your factory instead.");
	}
}
