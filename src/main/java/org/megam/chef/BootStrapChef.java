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

import org.megam.chef.exception.BootStrapChefException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.megam.chef.Constants.*;

/**
 * @author ram
 * 
 */
public class BootStrapChef {

	private static BootStrapChef bootSingleton;
	private static AppYaml bootedYaml;
	private Logger logger = LoggerFactory.getLogger(BootStrapChef.class);

	/**
	 * 
	 * @throws BootStrapChefException
	 */
	private BootStrapChef() throws BootStrapChefException {
		bootedUp();
		configureRoot();
		configure();

	}

	/**
	 * 
	 * @return
	 * @throws BootStrapChefException
	 */
	public static BootStrapChef boot() throws BootStrapChefException {
		if (bootSingleton == null) {
			bootSingleton = new BootStrapChef();
		}
		return bootSingleton;
	}

	/**
	 * 
	 * @return
	 */
	public static AppYaml yaml() {
		return bootedYaml;
	}

	/**
	 * configure the MEGAM_ROOT_DIRECTORY
	 */
	private void configureRoot() {
		/** MEGAM_ROOT_DIR **/
		logger.info("MEGAM_ROOT_DIR");
		logger.info(System.getProperty("user.dir"));
		MEGAM_CHEF_ROOT = System.getProperty("user.dir");
	}

	/**
	 * 
	 * @throws BootStrapChefException
	 *             configure the yaml file
	 */
	private void configure() throws BootStrapChefException {
		logger.info("Yaml loaded file entry");
		AppYamlLoader yaml = new AppYamlLoader(MEGAM_CHEF_APP_YAML);
		if (yaml.notReady()) {
			throw new BootStrapChefException(new IllegalArgumentException(
					"Something wrong in your yaml configuration file located in "
							+ MEGAM_CHEF_APP_YAML));
		}
		bootedYaml = yaml.currentYaml();
	}

	private void bootedUp() {
		/**
		 * Use LoggerFactory, to instantiate a log at the top. Put logging
		 * statements saying ------------------------- MEGAM CHEF bootedup
		 * version : build date : ---------------------- dump the contents of
		 * AppYaml
		 */
		logger.info("Booted up BootStrapChef");
		logger.info("MEGAM CHEF bootedup version : " + VERSION);
		logger.info("Build Date : " + BUILD_DATE);

	}

}
