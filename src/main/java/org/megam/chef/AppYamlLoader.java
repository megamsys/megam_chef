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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.megam.chef.exception.BootStrapChefException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;

/**
 * @author rajthilak
 * 
 */
public class AppYamlLoader {

	AppYamlLoadedSetup loadedYaml;
	private boolean notReady = false;
	AppYaml appYaml;
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
			logger.info("Yaml File Loaded");
			notReady = (loadedYaml == null) ? true : false;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			throw new BootStrapChefException(fnfe);
		} catch (IOException ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

	/**
	 * Based on the configure: flag in the app.yaml file, send back the AppYaml
	 * 
	 * @return
	 */
	public AppYaml currentYaml() {
		AppYaml current = null;
		yamlType = new AppYaml(loadedYaml.getMegamchef()).getConfig();

		// check wheather the which source and return it
		switch (yamlType) {
		case "development":
			current = new AppYaml(loadedYaml.getDevelopment());
			break;
		case "production":
			current = new AppYaml(loadedYaml.getProduction());
			break;
		}
		return current;
	}

	/**
	 * 
	 * @return
	 */
	boolean notReady() {
		return notReady;
	}

	/**
	 * @return
	 */
	public String toString() {

		loadedYaml.toString();
		System.out.println(currentYaml());
		return null;
	}
}
