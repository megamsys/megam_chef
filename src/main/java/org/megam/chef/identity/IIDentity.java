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
 * <p>IIDentity class.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public class IIDentity {

	private String key;
	private String value;
     
    /**
     * <p>Constructor for IIDentity.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     */
    public IIDentity(String key, String value){
    	this.key = key;
		this.value = value;
    }
     
    /** {@inheritDoc} */
    @Override
    public String toString(){
        //return " --" + this.key + " " + this.value;
    	return " "+this.key + " " + this.value;
    }
		
}
