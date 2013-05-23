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

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.megam.chef.BootStrapChef;
import org.megam.chef.exception.BootStrapChefException;

/**
 * @author ram
 * 
 */
public class BootStrapChefTest {

	/**
	 * @throws IOException
	 * @throws java.lang.Exception
	 * @throws BootStrapChefException
	 */
	@Before
	public void setUp() throws IOException {
		// Call BootstrapChef
		try {
			BootStrapChef.boot();
		} catch (BootStrapChefException bsce) {
			bsce.printStackTrace();
		}
	}

	@Test
	public void testVersion() {
		// test the Constants.VERSION

	}

	@Test
	public void testMegamChefRoot() {
		// test the Constants.MEGAM_CHEF_ROOT

	}

	@Test
	public void testMegamChefYaml() {
		// test the Constants.MEGAM_CHEF_YAML

	}

}
