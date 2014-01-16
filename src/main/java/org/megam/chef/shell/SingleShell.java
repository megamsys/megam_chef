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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.megam.chef.core.Condition;
import org.megam.chef.exception.SourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.megam.chef.exception.ShellException;

/**
 * 
 * @author rajthilak
 * 
 */
public class SingleShell extends RecursiveAction implements Stoppable {

	private MultiCommands cmd;
	private Logger logger = LoggerFactory.getLogger(SingleShell.class);
	private ProcessBuilder shellProc;

	/**
	 * 
	 * @param command
	 */
	public SingleShell(MultiCommands command) {
		this.cmd = command;
	}

	/**
	 * Processed the command using ProcessBuilder class Print the output's are
	 * wrote in the some file
	 */

	public void compute() {
		try {
			logger.debug("------------------->" + cmd.getOrderedCommands());
			boolean stop_flag = false;
			System.out.println(cmd.getOrderedCommands().size());
			Command prevCom = null;
			for (Iterator<Command> iter = cmd.getOrderedCommands().iterator(); iter
					.hasNext() && !stop_flag;) {
				Command com = iter.next(); 
				if(prevCom!=null && prevCom.composable()) {
					com.pipeto(null); // feed the previous pipe here.
				}
				shellProc = new ProcessBuilder(com.getCommandList());
				shellProc.redirectOutput(Redirect.appendTo(com
						.getRedirectOutputFile()));
				shellProc.redirectError(Redirect.appendTo(com
						.getRedirectErrorFile()));
				Process p = shellProc.start();
				if (com.composable()) {
					int subrc = p.waitFor();
					if (subrc != 0) {
						stop_flag = true;
					}
					prevCom = com;
				}

			}
			logger.debug("-------> An instance was started");
		} catch (IOException | InterruptedException npe) {
			try {
				throw new ShellException(npe);
			} catch (ShellException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("-------> An instance was started, exited.");
	}

	
	private String trimmer(final String s) {
		final StringBuilder sb = new StringBuilder(s);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0)))
			sb.deleteCharAt(0); // delete from the beginning
		while (sb.length() > 0
				&& Character.isWhitespace(sb.charAt(sb.length() - 1)))
			sb.deleteCharAt(sb.length() - 1); // delete from the end
		return sb.toString();
	}

	public void halt() {
		// do nothing for now, but we need to cancel the process that runs, and
		// start the
		// Rollback. Needs design in herk, to handle it.

	}

}
