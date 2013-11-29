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

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleCloudFormatter implements OutputCloudFormatter {

	private Map<String, String> gceMap_key = new HashMap<String, String>();
	private Map<String, String> gceMap_result = new HashMap<String, String>();
	
	public GoogleCloudFormatter() {		
		this.gceMap_key.put("image", "-I");
		this.gceMap_key.put("flavor", "-m");
		this.gceMap_key.put("zone", "-Z");
		this.gceMap_key.put("ssh_user", "-x");
		this.gceMap_key.put("identity_file", "-i");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.cloudformatters.OutputCloudFormatter#format()
	 */
	@Override
	public Map<String, String> format(Map<String, String> mp_value) {
		// TODO Auto-generated method stub		
		for (Map.Entry<String, String> entry : mp_value.entrySet()) {
			if (gceMap_key.containsKey(entry.getKey())) {				
				gceMap_result.put(gceMap_key.get(entry.getKey()), entry.getValue());
			}
		}
		return gceMap_result;
	}

}