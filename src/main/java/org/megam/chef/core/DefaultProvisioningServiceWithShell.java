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
package org.megam.chef.core;

import org.megam.chef.AppYaml;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.ShellException;
import org.megam.chef.parser.JSONRequest;
import org.megam.chef.parser.JSONRequestParser;
import org.megam.chef.shell.Command;
import org.megam.chef.shell.ShellProvisioningPool;
import org.megam.chef.shell.Shellable;
import org.megam.chef.shell.Stoppable;

/**
 * @author ram
 * @param <T>
 * 
 */

public class DefaultProvisioningServiceWithShell<T> extends
		DefaultProvisioningService<T> implements Shellable, Stoppable {

	/**
	 * 
	 * @throws ProvisionerException
	 */
	public DefaultProvisioningServiceWithShell() throws ProvisionerException {
		super();
	}

	/**
	 * TO-DO : What is the output we need to send ? We need a generic way to
	 * convert a Java output to JSON output
	 * 
	 * @see org.megam.chef.ProvisioningService#provision()
	 */
	@Override
	public T provision(String jsonString) throws ProvisionerException {
		try {
			execute(jsonToCommand(jsonString));
		} catch (ShellException she) {
			throw new ProvisionerException(she);
		}
		return null;
	}

	/**
	 * @param args
	 * @throws ShellException
	 */
	public Command jsonToCommand(String jsonRequest) throws ShellException {
		Command com = new org.megam.chef.shell.Command(
				convertInput(jsonRequest));
		// logger.(com);
		return com;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.shell.Stoppable#stop()
	 */
	@Override
	public void halt() {
		throw new RuntimeException("Halt not implemented yet.");
	}

	/**
	 * Using GSON library, an input JSON string is parsed to a
	 * GlobalConfiguration Java Object. If the JSON parameters passed are
	 * validated for 1..... 2. ..... 3. .... If they are valid then the shell
	 * builder builds a script. If not an error with the reasons of validation
	 * failure is retured. command
	 * 
	 * @param myJSONString
	 * @return
	 */
	private String convertInput(String jsonRequest) throws ShellException {
		JSONRequest jrp = (new JSONRequestParser(jsonRequest)).data();

		if (new ParmsValidator().validate(jrp.conditionList())) {
			System.out.println(ShellBuilder.buildString(jrp.scriptFeeder()));
			return ShellBuilder.buildString(jrp.scriptFeeder());
		} else {
			throw new ShellException(new IllegalArgumentException(
					"Add the reason here"));
		}
	}

	public String toString() {
		return "DefaultProvisioningWithShell";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.shell.Shellable#execute(org.megam.chef.shell.Command)
	 */
	@Override
	public void execute(Command command) throws ShellException {
		(new ShellProvisioningPool()).run(command);
	}

}
