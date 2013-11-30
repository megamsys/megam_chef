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


public class GoogleCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> gceMap_key = new HashMap<String, String>();

	public GoogleCloudFormatter() {
		this.gceMap_key.put(IMAGE, "-I");
		this.gceMap_key.put(FLAVOR, "-m");
		this.gceMap_key.put(ZONE, "-Z");
		this.gceMap_key.put(SSHUSER, "-x");
		this.gceMap_key.put(IDENTITYFILE, "-i");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.cloudformatters.OutputCloudFormatter#format()
	 */
	@Override
	public Map<String, String> format(Map<String, String> mp_value) {
		Map<String, String> gceMap_result = new HashMap<String, String>();

		for (Map.Entry<String, String> entry : mp_value.entrySet()) {
			if (gceMap_key.containsKey(entry.getKey())) {
				gceMap_result.put(gceMap_key.get(entry.getKey()),
						entry.getValue());
			}
		}
		return gceMap_result;
	}
	
	public String neededArgs() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : gceMap_key.entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();

	}


}