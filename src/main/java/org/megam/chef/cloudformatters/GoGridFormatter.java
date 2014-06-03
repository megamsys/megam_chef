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
import static org.megam.chef.parser.ComputeInfo.IMAGE;
import static org.megam.chef.parser.ComputeInfo.SSHKEY;
import static org.megam.chef.parser.ComputeInfo.SSHPUBLOCATION;
import static org.megam.chef.parser.ComputeInfo.SSHUSER;
import static org.megam.chef.parser.ComputeInfo.ZONE;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>GoGridFormatter class.</p>
 *
 * @author ram
 * @version $Id: $Id
 * @since 0.5.0
 */
public class GoGridFormatter implements OutputCloudFormatter {

	private final Map<String, String> gogridMap_key = new HashMap<String, String>();
	private Map<String, String> inputArgs;
	private List<String> unsatifiedReason;
	private String cc = "";
	private String email = "";
	private String bucket = "";
	private String req_type = "";

	/**
	 * <p>Constructor for GoGridFormatter.</p>
	 *
	 * @param tempArgs a {@link java.util.Map} object.
	 * @param req_type a {@link java.lang.String} object.
	 */
	public GoGridFormatter(Map<String, String> tempArgs, String req_type) {
		this.inputArgs = tempArgs;
		this.req_type = req_type;
		this.unsatifiedReason = new ArrayList<String>();
		this.gogridMap_key.put(IMAGE, "-I");
		this.gogridMap_key.put(FLAVOR, "-R");
		//this.awsMap_key.put(SSHKEY, "-S");
		//this.awsMap_key.put(SSHUSER, "-x");
		//if (!req_type.equals("delete")) {
		//	this.awsMap_key.put(IDENTITYFILE, "--identity-file");
		//}
		this.gogridMap_key.put(ZONE, "-a");
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
		if (req_type.equals("delete")) {
			return "";
		} else {
			return parserwithoutBucket(inputArgs.get(SSHPUBLOCATION)) + ".key";
		}
		// return inputArgs.get(IDENTITYFILE);
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

	/**
	 * <p>parserwithoutBucket.</p>
	 *
	 * @param str a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
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

	/** {@inheritDoc} */
	@Override
	public Map<String, String> format() {
		Map<String, String> gogridMap_result = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (gogridMap_key.containsKey(entry.getKey())) {
				//if (entry.getKey().equals(IDENTITYFILE)) {
				//	awsMap_result.put(awsMap_key.get(entry.getKey()),
				//			getIdentityFile());
				//} else {
					gogridMap_result.put(gogridMap_key.get(entry.getKey()),
							entry.getValue());
				//}
			}
		}
		return gogridMap_result;
	}

	/**
	 * <p>ok.</p>
	 *
	 * @return a boolean.
	 */
	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(FLAVOR, getFlavor());
		//isOk = isOk && validate(SSHKEY, getSshKey());
		//isOk = isOk && validate(SSHUSER, getSshUser());
		// isOk = isOk && validate(IDENTITYFILE, getIdentityFile());
		isOk = isOk && validate(ZONE, getZone());
		return isOk;
	}

	/**
	 * <p>validate.</p>
	 *
	 * @param key a {@link java.lang.String} object.
	 * @param value a {@link java.lang.String} object.
	 * @return a boolean.
	 */
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
	/**
	 * <p>inputAvailable.</p>
	 *
	 * @return a boolean.
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(IMAGE);
		isAvailable = isAvailable && notNull(FLAVOR);
		//isAvailable = isAvailable && notNull(SSHKEY);
		//isAvailable = isAvailable && notNull(SSHUSER);
		//isAvailable = isAvailable && notNull(IDENTITYFILE);
		isAvailable = isAvailable && notNull(ZONE);
		return isAvailable;
	}

	/**
	 * <p>name.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String name() {
		return "gocf:";
	}

	/**
	 * <p>getReason.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getReason() {
		return unsatifiedReason;
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
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
