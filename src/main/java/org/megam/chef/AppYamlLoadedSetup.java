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
 * <p>AppYamlLoadedSetup class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class AppYamlLoadedSetup {

	/**
	 * Mapping for the Yaml file
	 * 
	 */
	private Map<String, String> megamchef;
	private Map<String, String> development;
	private Map<String, String> production;
	private Map<String, String> staging;
	private Map<String, String> test;

	/** Constant <code>CONFIG="config"</code> */
	public static final String CONFIG = "config";
	/** Constant <code>DEV="development"</code> */
	public static final String DEV = "development";
	/** Constant <code>PROD="production"</code> */
	public static final String PROD = "production";
	/** Constant <code>STAGING="staging"</code> */
	public static final String STAGING = "staging";
	/** Constant <code>TEST="test"</code> */
	public static final String TEST = "test";

	/**
	 * Returns the value set in configuration: in chefapp.yaml file. This value
	 * decides what subset of values get loaded. can hold values (development,
	 * production, staging, test etc..)
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getConfiguration() {
		return getMegamchef().get(CONFIG);
	}

	/**
	 * set development map
	 *
	 * @param development a {@link java.util.Map} object.
	 */
	public void setDevelopment(Map<String, String> development) {
		this.development = development;
	}

	/**
	 * get development map
	 *
	 * @return development map
	 */
	public Map<String, String> getDevelopment() {
		return development;
	}

	/**
	 * set production map
	 *
	 * @param production a {@link java.util.Map} object.
	 */
	public void setProduction(Map<String, String> production) {
		this.production = production;
	}

	/**
	 * get production map
	 *
	 * @return production map
	 */
	public Map<String, String> getProduction() {
		return production;
	}

	/**
	 * set production map
	 *
	 * @param staging a {@link java.util.Map} object.
	 */
	public void setStaging(Map<String, String> staging) {
		this.staging = staging;
	}

	/**
	 * get staging map
	 *
	 * @return staging map
	 */
	public Map<String, String> getStaging() {
		return staging;
	}

	/**
	 * set test map
	 *
	 * @param test a {@link java.util.Map} object.
	 */
	public void setTest(Map<String, String> test) {
		this.test = test;
	}

	/**
	 * get test map
	 *
	 * @return test map
	 */
	public Map<String, String> getTest() {
		return test;
	}

	/**
	 * get megamchef map
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, String> getMegamchef() {
		return megamchef;
	}

	/**
	 * <p>Setter for the field <code>megamchef</code>.</p>
	 *
	 * @param megamchef a {@link java.util.Map} object.
	 */
	public void setMegamchef(Map<String, String> megamchef) {
		this.megamchef = megamchef;
	}

	/**
	 * toString() is display the map key's and their values
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%s%s%n", "*--------------------- chefapp.yaml[",
				getConfiguration(), "]-------------*");
		
		formatter.format("%5s = %s%n", CONFIG, "development");
		if (getDevelopment() != null) {
			for (Map.Entry<String, String> entry : getDevelopment().entrySet()) {
				formatter.format("%10s = %s%n", entry.getKey(),
						entry.getValue());
			}
		}
		formatter.format("%5s = %s%n", CONFIG, "production");
		if (getProduction() != null) {
			for (Map.Entry<String, String> entry : getProduction().entrySet()) {
				formatter.format("%10s = %s%n", entry.getKey(),
						entry.getValue());
			}
		}
		if (getStaging() != null && !getStaging().isEmpty()) {
			formatter.format("%5s = %s%n", CONFIG, "staging");
			for (Map.Entry<String, String> entry : getStaging().entrySet()) {
				formatter.format("%14s = %s%n", entry.getKey(),
						entry.getValue());
			}
		}
		if (getTest() != null && !getTest().isEmpty()) {
			formatter.format("%5s = %s%n", CONFIG, "test");

			for (Map.Entry<String, String> entry : getTest().entrySet()) {
				formatter.format("%10s = %s%n", entry.getKey(),
						entry.getValue());
			}
		}
		formatter.close();
		return strbd.toString();
	}
}
