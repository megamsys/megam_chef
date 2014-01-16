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
package org.megam.chef.parser;

import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;
import org.megam.chef.shell.MultiCommands;
import org.megam.chef.shell.SingleShell;

/**
 * @author rajthilak
 * 
 */
abstract class ProvisionerInfo implements DataMap, ScriptFeeder, Condition {

	private String prov;

	/**
	 * 
	 * @param prov
	 */
	ProvisionerInfo(String prov) {
		this.prov = prov;
	}

	public String getName() {
		return prov;
	}


}
