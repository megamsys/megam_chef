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

import java.util.Map;

/**
 * @author rajthilak
 * 
 */
public class AppYaml {

	/**
	 * create the Map object for current Yaml source
	 */
	private static Map<String, String> yamlSource;
	private static final String SOURCE = "source";
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String BUCKET = "bucket";
	private static final String CONFIG = "config";

	AppYaml(Map<String, String> type) {
		this.yamlSource = type;
	}

	/**
	 * 
	 * @return Yaml file values
	 */
	public String getSource() {
		return yamlSource.get(SOURCE);
	}

	public String getHost() {
		return yamlSource.get(HOST);
	}

	public String getPort() {
		return yamlSource.get(PORT);
	}

	public String getBucket() {
		return yamlSource.get(BUCKET);
	}

	public String getConfig() {
		return yamlSource.get(CONFIG);
	}
}
