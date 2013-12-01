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
package org.megam.chef.cloudformatters;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.megam.chef.parser.ComputeInfo.*;

public class AmazonCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> awsMap_key = new HashMap<String, String>();
	
	public AmazonCloudFormatter() {
		this.awsMap_key.put(GROUPS, "-G");
		this.awsMap_key.put(IMAGE, "-I");
		this.awsMap_key.put(FLAVOR, "-f");
		this.awsMap_key.put(SSHKEY, "-S");
		this.awsMap_key.put(SSHKEY, "-x");
		this.awsMap_key.put(IDENTITYFILE, "-i");
	}
	
	@Override
	public Map<String, String> format(Map<String, String> mp_value) {
		Map<String, String> awsMap_result = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : mp_value.entrySet()) {
			if (awsMap_key.containsKey(entry.getKey())) {				
				awsMap_result.put(awsMap_key.get(entry.getKey()), entry.getValue());
			}
		}
		return awsMap_result;
	}
	
	
	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(GROUPS, getGroups());
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(FLAVOR, getFlavor());
		return isOk;
	}

	public boolean validate(String key, String value) {
		for (Map.Entry<String, String> entry : map().entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					return true;
				} else {
					inputavailablereason.add(key + " is not valid ");
				}
			}
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(GROUPS);
		isAvailable = isAvailable && notNull(IMAGE);
		isAvailable = isAvailable && notNull(FLAVOR);
		return isAvailable;
	}

	public boolean notNull(String str) {
		if (map().containsKey(str)) {
			return true;
		} else {
			inputavailablereason.add(str + " is Missing");
		}
		return false;
	}
	
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}
	
	

}