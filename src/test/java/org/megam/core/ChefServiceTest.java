/* 
** Copyright [2012] [Megam Systems]
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
package org.megam.core;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.megam.chef.ChefServiceRunner;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;

/**
 * @author rajthilak
 *
 */
public class ChefServiceTest {

	/**
	 * 
	 * @throws SourceException
	 * @throws ProvisionerException
	 * @throws BootStrapChefException
	 */
	@Test
	public void test() throws SourceException, ProvisionerException, BootStrapChefException {
		try {
		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input("sample").control();
		fail("Not yet implemented");
		}catch(IOException ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

}
