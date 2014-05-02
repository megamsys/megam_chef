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

import static org.megam.chef.parser.ComputeInfo.IDENTITYFILE;
import static org.megam.chef.parser.ComputeInfo.IMAGE;
import static org.megam.chef.parser.ComputeInfo.SSHPUBLOCATION;
import static org.megam.chef.parser.ComputeInfo.SSHUSER;
import static org.megam.chef.parser.ComputeInfo.ZONE;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenNebulaCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> opMap_key = new HashMap<String, String>();
	private Map<String, String> inputArgs;
	private List<String> unsatifiedReason;
	private String cc = "";
	private String email = "";
	private String bucket = "";
	private String req_type = "";

	public OpenNebulaCloudFormatter(Map<String, String> tempArgs, String req_type) {
		this.inputArgs = tempArgs;
		this.req_type = req_type;
		this.opMap_key.put(IMAGE, "--template-name");
		this.opMap_key.put(SSHUSER, "-x");
		if (!req_type.equals("delete")) {
			this.opMap_key.put(IDENTITYFILE, "--identity-file");
		}
		this.opMap_key.put(ZONE, "--endpoint");
	}


	private String getImage() {
		return inputArgs.get(IMAGE);
	}


	private String getSshUser() {
		return inputArgs.get(SSHUSER);
	}

	private String getIdentityFile() {
		// return inputArgs.get(IDENTITYFILE);
		if (req_type.equals("delete")) {
			return "";
		} else {
		return parserwithoutBucket(inputArgs.get(SSHPUBLOCATION)) + ".key";
		}
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
		Map<String, String> opMap_result = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (opMap_key.containsKey(entry.getKey())) {
				if (entry.getKey().equals(IDENTITYFILE)) {
					opMap_result.put(opMap_key.get(entry.getKey()),
							getIdentityFile());
				} else {
					opMap_result.put(opMap_key.get(entry.getKey()),
							entry.getValue());
				}
			}
		}
		return opMap_result;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(SSHUSER, getSshUser());
		// isOk = isOk && validate(IDENTITYFILE, getIdentityFile());
		isOk = isOk && validate(ZONE, getZone());
		return isOk;
	}

	public boolean validate(String key, String value) {
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					System.out.println("================");
					System.out.println("------" + key + "------------" + value
							+ "--------" + entry.getValue());
					return true;
				} else {
					System.out.println("=======+++++++++++++++++=========");
					System.out.println("------" + key + "------------" + value
							+ "--------" + entry.getValue());
					unsatifiedReason.add(key + " is not valid ");
				}
			}
		}
		return false;

	}

	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(IMAGE);
		isAvailable = isAvailable && notNull(SSHUSER);
		isAvailable = isAvailable && notNull(IDENTITYFILE);
		isAvailable = isAvailable && notNull(ZONE);
		return isAvailable;
	}

	public String name() {
		return "oncf:";
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
