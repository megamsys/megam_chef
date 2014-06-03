/* 
 ** Copyright [2012-2013] [Megam Systems]
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
package org.megam.chef.core;

import org.megam.chef.AppYaml;
import org.megam.chef.BootStrapChef;
import org.megam.chef.ProvisioningService;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.ShellException;
import org.megam.chef.shell.MultiCommands;

/**
 * This the supercalls of all provisioning service. This implements the interface provisioning service.
 * Any common methods that span across all the concrete service shall be included in here.
 * At the moment nothing is available.
 *
 * @author ram
* @param <T>  {@link java.lang.Object} object.
 * @version $Id: $Id
 */
public abstract class AbstractProvisioningService<T> implements
		ProvisioningService<T> {

	/**
	 * <p>Constructor for AbstractProvisioningService.</p>
	 *
	 * @throws org.megam.chef.exception.ProvisionerException if any.
	 */
	public AbstractProvisioningService() throws ProvisionerException {

	}

	/**
	 * <p>execute.</p>
	 *
	 * @param command a {@link org.megam.chef.shell.MultiCommands} object.
	 * @throws org.megam.chef.exception.ShellException if any.
	 */
	public void execute(MultiCommands command) throws ShellException {
		// TODO Auto-generated method stub
		
	}
}
