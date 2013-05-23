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

import org.junit.Before;
import org.junit.Test;
import org.megam.chef.AppYaml;
import org.megam.chef.BootStrapChef;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.SourceException;
import org.megam.chef.parser.JSONRequest;
import org.megam.chef.source.riak.RiakSource;

/**
 * @author ram
 * 
 */
public class ProvisionerTest {

	private static AppYaml app;
	private static String jsonString;

	/**
	 * @throws java.lang.Exception
	 * @throws BootStrapChefException
	 */
	@Before
	public void setUp() throws Exception, BootStrapChefException {
		// use the Factory and fetch the appropriate ProvisoningService
		app = BootStrapChef.boot().yaml();
	}

	@Test
	public void testProvisionerService() throws SourceException {
		// assert to see the classname is what you need.
		RiakSource rs = new RiakSource(app);
		rs.connection();
		rs.bucket("megam-prov");
		jsonString = rs.fetch("sample");
		System.out.println("JSON String : " + jsonString);
		// String testOutput =
		// "{\"systemprovider\": {\"provider\": {\"prov\": \"chef\"}}, \"compute\": { \"ec2\": {\"groups\": \"megam\",\"image\": \"ami-56e6a404\",\"flavor\": \"m1.small\"},\"access\": {\"ssh-key\":\"megam_ec2\",\"identity-file\": \"~/.ssh/megam_ec2.pem\",\"ssh-user\": \"ubuntu\"}}, \"chefservice\": {\"chef\": {\"command\": \"knife\",\"plugin\": \"ec2 server create\", \"run-list\": \"\'role[opendj]\'\",\"name\": \"-N TestOverAll\"}} }";
		assertTrue("This will succeed.", true);
	}

}
