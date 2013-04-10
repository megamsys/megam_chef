/* 
 ** Copyright [2012] [Megam Systems]
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */
package org.megam.chef;

import org.megam.chef.core.AbstractProvisioningService;
import org.megam.chef.exception.ProvisionerException;

/**
 * @author ram
 * 
 */
public class NoneProvisioningService<T> extends AbstractProvisioningService<T> {

	/**
	 * 
	 * @throws ProvisionerException
	 */
	public NoneProvisioningService() throws ProvisionerException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.ProvisioningService#provision(java.lang.String)
	 */

	@Override
	public T provision(String jsonString) throws ProvisionerException {
		return null;
	}
}
