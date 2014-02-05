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

/**
 * @author ram
 *
 */
import static org.megam.chef.parser.ComputeInfo.CPUS;
import static org.megam.chef.parser.ComputeInfo.FLAVOR;
import static org.megam.chef.parser.ComputeInfo.HDD;
import static org.megam.chef.parser.ComputeInfo.IDENTITYFILE;
import static org.megam.chef.parser.ComputeInfo.IMAGE;
import static org.megam.chef.parser.ComputeInfo.RAM;
import static org.megam.chef.parser.ComputeInfo.SSHPUBLOCATION;
import static org.megam.chef.parser.ComputeInfo.SSHUSER;
import static org.megam.chef.parser.ComputeInfo.TENANTID;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ProfitBricksCloudFormatter implements OutputCloudFormatter {

	private final Map<String, String> pbArgsStub = new HashMap<String, String>();
	private Map<String, String> inputArgs;
	private List<String> unsatifiedReason = new ArrayList<String>();

	/*
	 * There is no flavor in profit bricks. We decided to send it in this format
	 * as FLAVOR as cpus=1,ram=1024,hdd-size=20
	 */
	public ProfitBricksCloudFormatter(Map<String, String> tempArgs) {
		this.inputArgs = tempArgs;
		breakFlavor();
		this.pbArgsStub.put(IMAGE, "--image-name");
		this.pbArgsStub.put(TENANTID, "--data-center");
		this.pbArgsStub.put(SSHUSER, "-x");
		this.pbArgsStub.put(CPUS, "--cpus");
		this.pbArgsStub.put(RAM, "--ram");
		this.pbArgsStub.put(HDD, "--hdd-size");
		this.pbArgsStub.put(IDENTITYFILE, "--identity-file");
		this.pbArgsStub.put(SSHPUBLOCATION, "--public-key-file");
	}

	// we expect to see cpus=1,ram=1024,hdd-size=20
	private void breakFlavor() {
		if (inputArgs.containsKey(FLAVOR)) {
			String[] splitFlr = inputArgs.get(FLAVOR).split(",");
			if (splitFlr.length >= 3) {
				for (int i = 0; i < splitFlr.length; i++) {
					StringTokenizer eachFlavor = new StringTokenizer(
							splitFlr[i], "=");
					inputArgs.put(eachFlavor.nextToken(),
							eachFlavor.nextToken());

				}
			}
		}
	}

	private String getImage() {
		return inputArgs.get(IMAGE);
	}

	private String getCPUs() {
		return inputArgs.get(CPUS);
	}

	private String getRAM() {
		return inputArgs.get(RAM);
	}

	private String getHDD() {
		return inputArgs.get(HDD);
	}

	private String getDatacenter() {
		return inputArgs.get(TENANTID);
	}

	private String getIdentityFile() {
		return inputArgs.get(IDENTITYFILE);
	}

	private String getPublicIdentityFile() {
		return inputArgs.get(SSHPUBLOCATION);
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
		Map<String, String> pbArgsValPairs = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : inputArgs.entrySet()) {
			if (pbArgsStub.containsKey(entry.getKey())) {
				pbArgsValPairs.put(pbArgsStub.get(entry.getKey()),
						entry.getValue());
			}
		}
		return pbArgsValPairs;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate(IMAGE, getImage());
		isOk = isOk && validate(CPUS, getCPUs());
		isOk = isOk && validate(RAM, getRAM());
		isOk = isOk && validate(HDD, getHDD());
		isOk = isOk && validate(TENANTID, getDatacenter());
		isOk = isOk && validate(IDENTITYFILE, getIdentityFile());
		isOk = isOk && validate(SSHPUBLOCATION, getPublicIdentityFile());
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
		isAvailable = isAvailable && notNull(IMAGE);
		isAvailable = isAvailable && notNull(CPUS);
		isAvailable = isAvailable && notNull(RAM);
		isAvailable = isAvailable && notNull(HDD);
		isAvailable = isAvailable && notNull(TENANTID);
		isAvailable = isAvailable && notNull(IDENTITYFILE);
		isAvailable = isAvailable && notNull(SSHPUBLOCATION);
		return isAvailable;
	}

	public String name() {
		return "pb:";
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
