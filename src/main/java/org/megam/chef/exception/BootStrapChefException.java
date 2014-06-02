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
package org.megam.chef.exception;

/**
 * <p>BootStrapChefException class.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public class BootStrapChefException extends MegamChefException {
	
	/**
	 * <p>Constructor for BootStrapChefException.</p>
	 *
	 * @param ex a {@link java.lang.Throwable} object.
	 */
	public BootStrapChefException(Throwable ex) {
		super("", ex);
	}

}
