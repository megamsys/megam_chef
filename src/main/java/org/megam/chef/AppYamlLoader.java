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
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Map;

import org.megam.chef.exception.BootStrapChefException;

/**
 * @author rajthilak
 * 
 */
class AppYamlLoader {

	private AppYamlLoadedSetup loadedYaml;
	private boolean notReady = false;

	AppYamlLoader(String yamlFilePath) throws BootStrapChefException {
		load(yamlFilePath);
	}

	private void load(String yamlFilePath) throws BootStrapChefException {
		InputStream input = new FileInputStream(new File(yamlFilePath));
		Constructor constructor = new Constructor(AppYamlLoadedSetup.class);
		TypeDescription appDescription = new TypeDescription(
				AppYamlLoader.class);
		constructor.addTypeDescription(appDescription);
		Yaml yaml = new Yaml(constructor);
		loadedYaml = (AppYamlLoadedSetup) yaml.load(input);
		notReady = (loadedYaml == null) ? true : false;
	}

	/**
	 * Based on the configure: flag in the app.yaml file, send back the AppYaml
	 * 
	 * @return
	 */
	AppYaml currentYaml() {		
		AppYaml current = null;
		switch "???" 
		case 		
		return (new AppYaml(development));
	}

	boolean notReady() {
		return notReady;
	}

	private class AppYamlLoadedSetup {

		private Map<String, String> development;
		private Map<String, String> production;

		Map<String, String> getDevelopment() {
			return development;
		}

		void setDevelopment(Map<String, String> development) {
			this.development = development;
		}

		Map<String, String> getProduction() {
			return production;
		}

		void setProduction(Map<String, String> production) {
			this.production = production;
		}

	}

	// write a toString method for debugging.
}
