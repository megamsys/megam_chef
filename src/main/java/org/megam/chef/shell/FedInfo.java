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
package org.megam.chef.shell;

/**
 * <p>FedInfo class.</p>
 *
 * @author subash
 * @version $Id: $Id
 */
public class FedInfo {

	private String name;
	private String shellString;

	/**
	 * <p>Constructor for FedInfo.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param ShellString a {@link java.lang.String} object.
	 */
	public FedInfo(String name, String ShellString) {
		this.name = name;
		this.shellString = ShellString;
	}

	// return the name

	/**
	 * <p>Getter for the field <code>name</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return name;
	}

	// set the name

	/**
	 * <p>Setter for the field <code>name</code>.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 */
	public void setName(String name) {
		this.name = name;
	}

	// return the shellString

	/**
	 * <p>Getter for the field <code>shellString</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getShellString() {
		return shellString;
	}

	// set the shellString

	/**
	 * <p>Setter for the field <code>shellString</code>.</p>
	 *
	 * @param shellString a {@link java.lang.String} object.
	 */
	public void setShellString(String shellString) {
		this.shellString = shellString;
	}

}
