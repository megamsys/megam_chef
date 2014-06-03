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

import java.util.Formatter;
import java.util.Map;

/**
 * <p>AppYaml class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class AppYaml {

	/**
	 * create the Map object for current Yaml source
	 */
	private Map<String, String> yamlSource;
	private static final String SOURCE = "source";
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String BUCKET = "bucket";

	AppYaml(Map<String, String> type) {
		this.yamlSource = type;
	}

	/**
	 * <p>getSource.</p>
	 *
	 * @return Yaml file values
	 */
	public String getSource() {
		return yamlSource.get(SOURCE);
	}

	/**
	 * <p>getHost.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getHost() {
		return yamlSource.get(HOST);
	}

	/**
	 * <p>getPort.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getPort() {
		return yamlSource.get(PORT);
	}

	/**
	 * <p>getBucket.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getBucket() {
		return yamlSource.get(BUCKET);
	}

	/**
	 * toString() is display the map key's and their values
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n",
				"Using setup :");
		formatter.format("%14s = %s%n", SOURCE, getSource());
		formatter.format("%14s = %s%n", BUCKET, getBucket());
		formatter.format("%14s = %s%n", HOST, getHost());
		formatter.format("%14s = %s%n", PORT, getPort());
		formatter.format("%s%n",
				"*------------------------------------------*");

		formatter.close();
		return strbd.toString();
	}

}
