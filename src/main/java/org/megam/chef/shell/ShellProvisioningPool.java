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
package org.megam.chef.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.megam.chef.core.DefaultProvisioningServiceWithShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ShellProvisioningPool class.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public class ShellProvisioningPool {
	/**
	 * We need to convert the below listeners to ConcurrnetList
	 */
	private List<ShellProvisioningListener> listeners = new ArrayList<ShellProvisioningListener>();
	private List<Stoppable> stopList = new ArrayList<Stoppable>();

	private static ShellProvisioningPool spPool;
	private ForkJoinPool ourForky;
	private Logger logger = LoggerFactory
			.getLogger(ShellProvisioningPool.class);

	/**
	 * <p>Constructor for ShellProvisioningPool.</p>
	 */
	public ShellProvisioningPool() {
		ourForky = new ForkJoinPool();
	}

	/**
	 * <p>pool.</p>
	 *
	 * @return a {@link org.megam.chef.shell.ShellProvisioningPool} object.
	 */
	public static ShellProvisioningPool pool() {
		if (spPool == null) {
			spPool = new ShellProvisioningPool();
		}
		return spPool;
	}

	/**
	 * <p>addShellProvisioningListener.</p>
	 *
	 * @param sple a {@link org.megam.chef.shell.ShellProvisioningListener} object.
	 */
	public void addShellProvisioningListener(ShellProvisioningListener sple) {
		listeners.add(sple);
	}

	/**
	 * <p>notify.</p>
	 *
	 * @param se a {@link org.megam.chef.shell.ShellEvent} object.
	 */
	public void notify(ShellEvent se) {
		for (ShellProvisioningListener listener : listeners) {
			listener.tell(se);
		}
	}

	/**
	 * <p>stop.</p>
	 */
	public void stop() {
		for (Stoppable stop : stopList) {
			stop.halt();
		}
	}

	/**
	 * <p>run.</p>
	 *
	 * @param command a {@link org.megam.chef.shell.MultiCommands} object.
	 */
	public void run(MultiCommands command) {
		SingleShell oneRun = new SingleShell(command);
		ourForky.invoke(oneRun);
	}
}
