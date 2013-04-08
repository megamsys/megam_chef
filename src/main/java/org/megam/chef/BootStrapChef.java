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
package org.megam.chef;

import org.megam.chef.exception.BootStrapChefException;
import static org.megam.chef.Constants.*;

/**
 * @author ram
 * 
 */
public class BootStrapChef {

	private static BootStrapChef bootSingleton;
	private AppYaml bootedYaml; 
	
	private BootStrapChef() throws BootStrapChefException {
		configureRoot();
		configure();	
		bootedUp();		
	}
	
	public static BootStrapChef boot() throws BootStrapChefException {
		if(bootSingleton ==null) {
			bootSingleton = new BootStrapChef();
		}		
		return bootSingleton;		
	}
	
	public AppYaml yaml() {
		return bootedYaml;
	}
	
	private void configureRoot() {
		/** MEGAM_ROOT_DIR **/
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		//MEGAM_CHEF_ROOT =  System.getProperty("user.dir");
	}
	
	private void configure() throws BootStrapChefException {		
		AppYamlLoader yaml = new AppYamlLoader(MEGAM_CHEF_APP_YAML);				
		if (yaml.notReady()) {
			throw new BootStrapChefException(new IllegalArgumentException(
					"Something wrong in your yaml configuration file located in " + MEGAM_CHEF_APP_YAML));
		}
		bootedYaml = yaml.currentYaml();		
	}
	
	private void bootedUp() {
		/**
		 * Use LoggerFactory, to instantiate a log at the top.
		 * Put logging statements saying
		 * ------------------------- MEGAM CHEF bootedup version : build date : ----------------------
		 * dump the contents of AppYaml
		 */
		
	}
	
	//toString method

}
