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
import static org.megam.chef.parser.ComputeInfo.SSHPUBLOCATION;
import static org.megam.chef.parser.ComputeInfo.SSHUSER;
import static org.megam.chef.parser.ComputeInfo.REGION;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> awsMap_key = new HashMap<String, String>();
	private Map<String, String> inputArgs;
	private List<String> unsatifiedReason;
	private String cc = "";
	private String email = "";
	private String bucket = "";
	public AmazonCloudFormatter(Map<String, String> tempArgs) {
		this.inputArgs = tempArgs;
		this.unsatifiedReason = new ArrayList<String>();
		this.awsMap_key.put(GROUPS, "-G");
		this.awsMap_key.put(IMAGE, "-I");
		this.awsMap_key.put(FLAVOR, "-f");
		this.awsMap_key.put(SSHKEY, "-S");
		this.awsMap_key.put(SSHUSER, "-x");
		this.awsMap_key.put(IDENTITYFILE, "--identity-file");
		this.awsMap_key.put(REGION, "--region");
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
	
	private String getSshKey() {
		return inputArgs.get(SSHKEY);
	}

	private String getSshUser() {
		return inputArgs.get(SSHUSER);
	}

	private String getIdentityFile() {
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println(parserwithoutBucket(inputArgs.get(SSHPUBLOCATION)));
		return parserwithoutBucket(inputArgs.get(SSHPUBLOCATION))+".key";
		//return inputArgs.get(IDENTITYFILE);
	}
	
	private String getRegion() {
		return inputArgs.get(REGION);
	}
	
	private boolean notNull(String str) {
		if (inputArgs.containsKey(str)) {
			return true;
		} else {
			unsatifiedReason.add(str + " is Missing");
		}
		return false;
	}

	public String parserwithoutBucket(String str) {
		if (str.length() > 0) {
			int lst = str.lastIndexOf("/");
			cc = str.substring(lst);
			str = str.replace(str.substring(lst), "");
			email = str.substring(str.lastIndexOf("/"));
			str = str.replace(str.substring(str.lastIndexOf("/")), "");
			bucket = str.substring(str.lastIndexOf("/") + 1);
			return bucket + email + cc;
		} else {
			return str;
		}
	}
	
	@Override
	public Map<String, String> format() {
		Map<String, String> awsMap_result = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (awsMap_key.containsKey(entry.getKey())) {
				awsMap_result.put(awsMap_key.get(entry.getKey()),
						entry.getValue());
			}
		}
		return awsMap_result;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(GROUPS, getGroups());
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(FLAVOR, getFlavor());
		isOk = isOk && validate(SSHKEY, getSshKey());
		isOk = isOk && validate(SSHUSER, getSshUser());
		isOk = isOk && validate(IDENTITYFILE, getIdentityFile());
		isOk = isOk && validate(REGION, getRegion());
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
		isAvailable = isAvailable && notNull(SSHKEY);
		isAvailable = isAvailable && notNull(SSHUSER);
		isAvailable = isAvailable && notNull(IDENTITYFILE);
		isAvailable = isAvailable && notNull(REGION);
		return isAvailable;
	}
	
	public String name() {
		return  "acf:";
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