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

import static org.megam.chef.parser.ComputeInfo.FLAVOR;
import static org.megam.chef.parser.ComputeInfo.GROUPS;
import static org.megam.chef.parser.ComputeInfo.IDENTITYFILE;
import static org.megam.chef.parser.ComputeInfo.IMAGE;
import static org.megam.chef.parser.ComputeInfo.SSHKEY;
import static org.megam.chef.parser.ComputeInfo.SSHUSER;
import static org.megam.chef.parser.ComputeInfo.TENANTID;
import static org.megam.chef.parser.ComputeInfo.ZONE;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HPCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> hpMap_key = new HashMap<String, String>();
	private Map<String, String> inputArgs;
	private List<String> unsatifiedReason;

	public HPCloudFormatter(Map<String, String> tempArgs) {
		this.inputArgs = tempArgs;
		this.hpMap_key.put(GROUPS, "-G");
		this.hpMap_key.put(IMAGE, "-I");
		this.hpMap_key.put(FLAVOR, "-f");
		this.hpMap_key.put(TENANTID, "-T");
		this.hpMap_key.put(SSHKEY, "-S");
		this.hpMap_key.put(SSHUSER, "-x");
		this.hpMap_key.put(IDENTITYFILE, "--identity-file");
		this.hpMap_key.put(ZONE, "-Z");
	}

	private String getGroups() {
		return inputArgs.get(GROUPS);
	}

	private String getImage() {
		return inputArgs.get(IMAGE);
	}

	private String getFlavor() {
		return inputArgs.get(FLAVOR);
	}
	
	private String getTenantId() {
		return inputArgs.get(TENANTID);
	}
	
	private String getSshKey() {
		return inputArgs.get(SSHKEY);
	}

	private String getSshUser() {
		return inputArgs.get(SSHUSER);
	}

	private String getIdentityFile() {
		return inputArgs.get(IDENTITYFILE);
	}
	
	private String getZone() {
		return inputArgs.get(ZONE);
	}

	private boolean notNull(String str) {
		if (inputArgs.containsKey(str)) {
			return true;
		} else {
			unsatifiedReason.add(str + " is Missing");
		}
		return false;
	}

	@Override
	public Map<String, String> format() {
		Map<String, String> hpMap_result = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (hpMap_key.containsKey(entry.getKey())) {
				hpMap_result.put(hpMap_key.get(entry.getKey()),
						entry.getValue());
			}
		}
		return hpMap_result;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(GROUPS, getGroups());
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(FLAVOR, getFlavor());
		isOk = isOk && validate(TENANTID, getTenantId());
		isOk = isOk && validate(SSHKEY, getSshKey());
		isOk = isOk && validate(SSHUSER, getSshUser());
		isOk = isOk && validate(IDENTITYFILE, getIdentityFile());
		isOk = isOk && validate(ZONE, getZone());
		return isOk;
	}

	public boolean validate(String key, String value) {
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					return true;
				} else {
					unsatifiedReason.add(key + " is not valid ");
				}
			}
		}
		return false;

	}

	
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(GROUPS);
		isAvailable = isAvailable && notNull(IMAGE);
		isAvailable = isAvailable && notNull(FLAVOR);
		isAvailable = isAvailable && notNull(TENANTID);
		isAvailable = isAvailable && notNull(SSHKEY);
		isAvailable = isAvailable && notNull(SSHUSER);
		isAvailable = isAvailable && notNull(IDENTITYFILE);
		isAvailable = isAvailable && notNull(ZONE);
		return isAvailable;
	}

	public String name() {
		return "hpcf:";
	}

	public List<String> getReason() {
		return unsatifiedReason;
	}
	


	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		return strbd.toString();
	}


}