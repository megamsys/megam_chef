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

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.megam.chef.Constants.BUILD_DATE;
import static org.megam.chef.Constants.MEGAM_CHEF_APP_YAML;
import static org.megam.chef.Constants.MEGAM_CHEF_ROOT;
import static org.megam.chef.Constants.MEGAM_DEFAULT_CHEF_APP_YAML;
import static org.megam.chef.Constants.MEGAM_USER_HOME;
import static org.megam.chef.Constants.VERSION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.megam.chef.exception.BootStrapChefException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * @throws IOException
	 */
	private BootStrapChef() throws BootStrapChefException {
		startBoot();
		configureRoot();
		yamlSetup();
		configure();
		//add a method stuff to verify the ENV[MEGAM_AWS_SECRET_KEY], ENV[MEGAM_AWS_ACCESS_KEY] exists

	}

	/**
	 * 
	 * @return
	 * @throws BootStrapChefException
	 * @throws IOException
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
	public AppYaml yaml() {
		return bootedYaml;
	}

	/**
	 * configure the MEGAM_ROOT_DIRECTORY
	 */
	private void configureRoot() {
		logger.debug("user.dir=" + System.getProperty("user.dir"));
		MEGAM_CHEF_ROOT = System.getProperty("user.dir");
	}

	/**
	 * 
	 * @throws BootStrapChefException
	 *             configure the yaml file
	 */
	private void configure() throws BootStrapChefException {
		logger.debug("Yaml loading.." + MEGAM_CHEF_APP_YAML);
		AppYamlLoader yaml = new AppYamlLoader(MEGAM_CHEF_APP_YAML);
		if (yaml.notReady()) {
			throw new BootStrapChefException(new IllegalArgumentException(
					"Something wrong in your yaml configuration file located in "
							+ MEGAM_CHEF_APP_YAML));
		}
		bootedYaml = yaml.current();
		logger.debug(bootedYaml.toString());
	}

	/*
	 * Use LoggerFactory, to instantiate a log at the top. Put logging
	 * statements saying ------------------------- MEGAM CHEF bootedup version :
	 * build date : ----------------------
	 */
	private void startBoot() {
		logger.debug("------------------------- MEGAM CHEF version : " + VERSION
				+ "Build Date : " + BUILD_DATE + "----------------------");

	}

	/**
	 * Copy the default chefapp.yaml to .megam/chefappyaml if one doesn't exist.
	 * If one exists then, use it.
	 * 
	 * @throws BootStrapChefException
	 */
	private void yamlSetup() throws BootStrapChefException {
		try {
			File file = new File(MEGAM_CHEF_APP_YAML);
			logger.debug("user.home=" + MEGAM_USER_HOME);
			logger.debug("user.dir=" + MEGAM_CHEF_ROOT);

			if (!file.exists()) {
				String source = MEGAM_DEFAULT_CHEF_APP_YAML;
				String target = MEGAM_USER_HOME + java.io.File.separator
						+ ".megam" + java.io.File.separator;
				Path targetDir = Paths.get(target);
				targetDir.toFile().mkdirs();
				Path targetPath = targetDir.resolve(source);
				logger.warn(file.getAbsolutePath()
						+ " not found. copying default :" + source + " to "
						+ targetPath);

				InputStream in = this.getClass().getResourceAsStream(java.io.File.separator + source);
				if (in != null) {
					Files.copy(in, targetPath, REPLACE_EXISTING);
				} else {
					throw new FileNotFoundException(source
							+ "file not found. Copy failed.");
				}
				logger.debug("copy of file :" + source + " to " + target
						+ " successful.");
			}
		} catch (IOException ioe) {
			throw new BootStrapChefException(ioe);
		} catch (IllegalArgumentException ire) {
			throw new BootStrapChefException(ire);
		}
	}
}
