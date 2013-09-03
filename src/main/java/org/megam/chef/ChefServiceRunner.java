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
package org.megam.chef;

import java.io.IOException;

import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.SourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Chef service runner takes an input id, in the input method. The input id can
 * be an identifier stored in Riak which has the JSON content to run by Chef
 * system. If the source=no is configured, then the input id is assumed to be
 * JSON string To instantiate this class pass the TYPE enumerator. The supported
 * types are CHEF_WITH_SHELL and NONE
 * 
 * @author rajthilak
 * 
 */
public class ChefServiceRunner {

	private SourceLoader source;
	private ProvisioningService<?> ps;
	private Logger logger = LoggerFactory.getLogger(ChefServiceRunner.class);

	/**
	 * Takes an input enumerator TYPE
	 * 
	 * @param type
	 * @return
	 * @throws BootStrapChefException
	 * @throws ProvisionerException
	 * @throws SourceException
	 *             booting the initialization classes load the source *
	 */
	public ChefServiceRunner withType(TYPE type) throws BootStrapChefException,
			ProvisionerException, SourceException, IOException {
		logger.info("-------> Chef service runner - started.");
		AppYaml app = BootStrapChef.boot().yaml();
		source = new SourceLoader(app);
		source.load();
		ps = ProvisionerFactory.create(type);
		logger.info("-------> Provisioner created successfully.");
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
	public ChefServiceRunner input(DropIn dropid) throws SourceException,
			ProvisionerException {
			logger.info("-------> dropid =>" + dropid.getId());
			ps.provision(source.fetchRequestJSON(dropid.getId()));
			logger.info("-------> An instance was created");
			return this;
	}

	public ChefServiceRunner control() {
		return this;
	}
}
