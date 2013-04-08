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
package org.megam.chef.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;


/**
 * @author ram
 *
 */
public class ShellProvisioningPool {
	/**
	 * We need to convert the below listeners to ConcurrnetList
	 */
	private List<ShellProvisioningListener> listeners = new ArrayList<>();
	private List<Stoppable> stopList  = new ArrayList<>();
	
	private static ShellProvisioningPool spPool; 
	private ForkJoinPool ourForky;
	
	private ShellProvisioningPool() {
	     ourForky = new ForkJoinPool();
	}
	
	public static ShellProvisioningPool pool() {
		if(spPool == null) {
			spPool = new ShellProvisioningPool(); 
			}
		return spPool;
	}

	
	public void addShellProvisioningListener(ShellProvisioningListener sple) {
		listeners.add(sple);
	}

	public void notify(ShellEvent se) {
		for(ShellProvisioningListener listener: listeners) {
			listener.tell(se);
		}
	}
	
	public void stop() {
		for(Stoppable stop: stopList) {
			stop.halt();
		}
	}
	
	public void run(Command that) {	
		SingleShell oneRun = new SingleShell(that);
		//register the oneRun stoppable in the list.
		ourForky.invoke(oneRun);		
	}
}
