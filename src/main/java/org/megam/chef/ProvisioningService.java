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
package org.megam.chef;

import java.io.IOException;

import org.megam.chef.exception.IdentifierException;
import org.megam.chef.exception.ProvisionerException;

/**
 * <p>ProvisioningService interface.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public interface ProvisioningService<T extends Object> {

	/**
	 * <p>provision.</p>
	 *
	 * @param jsonString a {@link java.lang.String} object.
	 * @throws org.megam.chef.exception.ProvisionerException if any.
	 * @throws org.megam.chef.exception.IdentifierException if any.
	 * @throws java.io.IOException if any.
	 * @return a T object.
	 */
	public T provision(String jsonString) throws ProvisionerException, IOException, IdentifierException;

}
