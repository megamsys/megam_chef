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
package org.megam.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.chef.ChefServiceRunner;
import org.megam.chef.DropIn;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.IdentifierException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;

/**
 * <p>ChefServiceTest class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 * @since 0.5.0
 */
public class ChefServiceTest {

	/**
	 * <p>test.</p>
	 *
	 * @throws java.io.IOException if any.
	 * @throws org.megam.chef.exception.SourceException if any.
	 * @throws org.megam.chef.exception.ProvisionerException if any.
	 * @throws org.megam.chef.exception.BootStrapChefException if any.
	 * @throws org.megam.chef.exception.IdentifierException if any.
	 */
	@Test
	public void test() throws SourceException, ProvisionerException,
			BootStrapChefException, IOException, IdentifierException {
		try {
			//(new ChefServiceRunner()).withType(TYPE.CHEF_WITH_SHELL).input(new DropIn("RIP432866425464422400")).control();
			//(new ChefServiceRunner()).withType(TYPE.CHEF_WITH_SHELL).input(new DropIn("RIP432863478038921216")).control();
			(new ChefServiceRunner()).withType(TYPE.CHEF_WITH_SHELL).input(new DropIn("RIP466484788488830976")).control();
			assertTrue("This will succeed.", true);
		} catch (IOException ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

	
}
