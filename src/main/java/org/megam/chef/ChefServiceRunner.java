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

import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.SourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajthilak
 * 
 */
public class ChefServiceRunner {

	private static String jsonString;
	protected AppYaml app;
	private SourceLoader source;
	private ProvisioningService<?> ps;
	private Logger logger = LoggerFactory.getLogger(ChefServiceRunner.class);

	/**
	 * 
	 * @param type
	 * @return
	 * @throws BootStrapChefException
	 * @throws ProvisionerException
	 * @throws SourceException
	 *             booting the initialization classes load the source
	 */
	public ChefServiceRunner with(TYPE type) throws BootStrapChefException,
			ProvisionerException, SourceException {
		logger.info("Booting Started");
		app = BootStrapChef.boot().yaml();
		source = new SourceLoader(app);
		source.load();
		ps = ProvisionerFactory.create(type);
		logger.info("Booting Successfully completed");
		return this;
	}

	/**
	 * 
	 * @param dropid
	 * @return
	 * @throws SourceException
	 * @throws ProvisionerException
	 *             fetch the json string execute the provisioning service using
	 *             that json string
	 * 
	 */
	public ChefServiceRunner input(String dropid) throws SourceException,
			ProvisionerException {
		jsonString = source.fetchRequestJSON(dropid);
		ps.provision(jsonString);
		logger.info("An instance was created");
		return this;
	}

	public ChefServiceRunner controller() {
		return this;
	}
}
