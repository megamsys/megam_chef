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
public class AppYamlLoadedSetup {

	/**
	 * Mapping for the Yaml file
	 * 
	 */
	private Map<String, String> megamchef;
	private Map<String, String> development;
	private Map<String, String> production;

	/**
	* set development map
	* @param development
	* 
	*/
	public void setDevelopment(Map<String, String> development) {
		this.development = development;
	}

	/**
	* get development map
	* @return development map
	* 
	*/
	public Map<String, String> getDevelopment() {
		return development;
	}

	/**
	* set production map
	* @param production
	* 
	*/
	public void setProduction(Map<String, String> production) {
		this.production = production;
	}

	/**
	* get production map
	* @return production map
	* 
	*/
	public Map<String, String> getProduction() {
		return production;
	}

	/**
	 * get megamchef map
	 * @param megamchef
	 * @return
	 */
	public Map<String, String> getMegamchef() {
		return megamchef;
	}

	/**
	 * 
	 * @param megamchef
	 */
	public void setMegamchef(Map<String, String> megamchef) {
		this.megamchef = megamchef;
	}

	/**
	 * toString() is display the map key's and their values
	 */
	public String toString() {
		for (Map.Entry entry : development.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		for (Map.Entry entry : production.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		for (Map.Entry entry : megamchef.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		return null;
	}
}
