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

import static org.megam.chef.AppYamlLoadedSetup.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.megam.chef.exception.BootStrapChefException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * <p>AppYamlLoader class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class AppYamlLoader {
	/**
	 * Existing loaded setup, loaded using gson.
	 */
	private AppYamlLoadedSetup loadedYaml;
	
	private boolean notReady = false;
	/**
	 * A copy of the configuration, based on the property set in the chefapp.yaml (configuration: <development/production etc..>)
	 */
	private AppYaml appYaml;
	
	private String yamlType;
	
	private Logger logger = LoggerFactory.getLogger(AppYamlLoader.class);

	/**
	 * 
	 * @param yamlFilePath
	 * @throws BootStrapChefException
	 */
	AppYamlLoader(String yamlFilePath) throws BootStrapChefException {
		load(yamlFilePath);
	}

	/**
	 * 
	 * @param yamlFilePath
	 * @throws BootStrapChefException
	 *             create the yaml object for specific constructor class Loaded
	 *             yaml file on that class
	 * 
	 */
	private void load(String yamlFilePath) throws BootStrapChefException {
		try {
			InputStream input = new FileInputStream(new File(yamlFilePath));
			Constructor constructor = new Constructor(AppYamlLoadedSetup.class);
			TypeDescription appDescription = new TypeDescription(
					AppYamlLoadedSetup.class);
			constructor.addTypeDescription(appDescription);
			Yaml yaml = new Yaml(constructor);
			loadedYaml = (AppYamlLoadedSetup) yaml.load(input);
			notReady = (loadedYaml == null) ? true : false;
		} catch (IOException ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

	/**
	 * Based on the configure: flag in the app.yaml file, send back the AppYaml
	 *
	 * @return a {@link org.megam.chef.AppYaml} object.
	 */
	public AppYaml current() {
		Map<String,String> currMap = null;
		
		yamlType = loadedYaml.getConfiguration();
		/** check whether the configuration is development, production, staging or test.
		 * Those are the 4 values supported currently. 
		 */		
		switch (yamlType) {
		case DEV:
			currMap  = loadedYaml.getDevelopment();
			break;
		case PROD:
			currMap = loadedYaml.getProduction();
			break;
		case STAGING:
			currMap = loadedYaml.getStaging();
			break;
		case TEST:
			currMap = loadedYaml.getTest();
			break;
		default:
			throw new IllegalArgumentException("configuration not found. Make sure your " + Constants.MEGAM_CHEF_APP_YAML + " contains config: <development/production/staging/test>\n"+Constants.HELP_GITHUB);
		}
		return new AppYaml(currMap);
	}

	/**
	 * 
	 * @return
	 */
	boolean notReady() {
		return notReady;
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		return loadedYaml.toString();
	}
}
