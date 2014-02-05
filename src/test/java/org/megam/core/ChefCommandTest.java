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
import org.megam.chef.AppYaml;
import org.megam.chef.BootStrapChef;
import org.megam.chef.ProvisionerFactory;
import org.megam.chef.ProvisioningService;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.core.DefaultProvisioningServiceWithShell;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.MegamChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.riak.RiakSource;

/**
 * @author rajthilak
 * 
 */
public class ChefCommandTest {

	private static String jsonString;
	private AppYaml app;
	private ProvisioningService ps;

	/**
	 * @param <T>
	 * @throws ProvisionerException
	 * @throws BootStrapChefException
	 * @throws IOException
	 */
	@Before
	public <T> void setUp() throws ProvisionerException, BootStrapChefException {
		try {
			BootStrapChef.boot();
			ps = ProvisionerFactory.create(TYPE.CHEF_WITH_SHELL);
		} catch (Exception ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

	/**
	 * ProvisioningService
	 * 
	 * @param <T>
	 * @throws SourceException
	 * @throws ProvisionerException
	 */
	@Test
	public <T> void test() throws MegamChefException, IOException {
		app = BootStrapChef.boot().yaml();
		RiakSource rs = new RiakSource(app);
		rs.connection();
		rs.bucket("requests");
		jsonString = rs.fetch("RIP431073015971708928");
		(new DefaultProvisioningServiceWithShell<T>()).provision(jsonString);
		assertTrue("This will succeed.", true);
	}

}
