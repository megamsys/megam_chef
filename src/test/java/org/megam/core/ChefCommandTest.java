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

import org.junit.Before;
import org.junit.Test;
import org.megam.chef.AppYaml;
import org.megam.chef.BootStrapChef;
import org.megam.chef.ProvisionerFactory;
import org.megam.chef.ProvisioningService;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.core.DefaultProvisioningServiceWithShell;
import org.megam.chef.exception.BootStrapChefException;
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
	 */
	@Before
	public <T> void setUp() throws ProvisionerException, BootStrapChefException {			
		 //app = (new DefaultProvisioningServiceWithShell<T>()).currentSource();
		BootStrapChef.boot();
		ps = ProvisionerFactory.create(TYPE.CHEF_WITH_SHELL);
	}
	
	/**
	 * @param <T>
	 * @throws SourceException
	 * @throws ProvisionerException 	
	 */
	@Test
	public <T> void test() throws SourceException, ProvisionerException {	
		app = BootStrapChef.yaml();	
		RiakSource rs =new RiakSource(app);      	   
  	    rs.connection();
  	    rs.bucket("rajBucket");
  	    jsonString = rs.fetch("sample");
  	    System.out.println("JSON String : "+jsonString);  	  
  	  (new DefaultProvisioningServiceWithShell<T>()).provision(jsonString);
		fail("Not yet implemented");
	}

}
