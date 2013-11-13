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
package org.megam.chef.identity;


/**
 * @author ram
 * 
 */
public class IIDentity {

	private String key;
	private String value;
     
    public IIDentity(String key, String value){
    	this.key = key;
		this.value = value;
    }
     
    @Override
    public String toString(){
        return " --" + this.key + " " + this.value;
    }
	
	/*
	private String key;
	private String value;
	private String shellString;

	public IIDentity(String key, String value) {
		this.key = key;
		this.value = value;
		setShellString();
	}

	// return the key

	public String getIdentityKey() {
		return key;
	}

	// return the value

	public String getIdentityValue() {
		return value;
	}

	// return the shellString

	public String getShellString() {
		return shellString;
	}

	// set the shellString

	public void setShellString() {
		this.shellString = "--" + getIdentityKey() + " " + getIdentityValue();
	}*/
}
