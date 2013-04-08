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

import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.RecursiveAction;


public class SingleShell extends RecursiveAction {
	
	private Command cmd;
	private String s;

	public SingleShell(Command tempcmd) {
		this.cmd = tempcmd;
	}

	public void compute() {
		try {
			// Processed the command using ProcessBuilder class
			ProcessBuilder p = new ProcessBuilder(cmd.getCommandList());
			// Print the output's are wrote in the some file
			p.redirectOutput(Redirect.appendTo(cmd.getRedirectOutputFile()));
			Process p1 = p.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
