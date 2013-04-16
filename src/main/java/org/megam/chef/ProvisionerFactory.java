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

import org.megam.chef.core.DefaultProvisioningService;
import org.megam.chef.core.DefaultProvisioningServiceWithShell;
import org.megam.chef.core.NoneProvisioningService;
import org.megam.chef.exception.ProvisionerException;

/**
 * The provisioner factor acts as a creator of provisioning service.
 * The supported types ...
 * @author ram
 * 
 */
public class ProvisionerFactory {

	public enum TYPE {
		CHEF_WITH_SHELL, NONE
	}

	/**
	 * Creates the available Provisioning client based on the enum TYPE.
	 * 
	 * @return ProvisioningService
	 * @throws ProvisionerException
	 */
	@SuppressWarnings("rawtypes")
	public static ProvisioningService<?> create(TYPE type)
			throws ProvisionerException {

		ProvisioningService<?> ps = null;
		switch (type) {
		case CHEF_WITH_SHELL:
			ps = new DefaultProvisioningServiceWithShell();
			break;
		case NONE:
			ps = new NoneProvisioningService();
			break;
		default: // default DefaultChefWithShell
			ps = new DefaultProvisioningService();
			break;
		}
		return ps;
	}
}
