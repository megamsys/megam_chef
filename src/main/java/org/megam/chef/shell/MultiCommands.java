/** Copyright [2012-2013] [Megam Systems]
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <p>MultiCommands class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class MultiCommands {

	private List<String> mainList, delList;
	private String name;
	private String inputCmd;
	private boolean flag, config_flag = false;
	private String str, prev_token = "", subcommand = "";
	private LinkedList<Command> list = new LinkedList<Command>();

	/**
	 * <p>Constructor for MultiCommands.</p>
	 *
	 * @param shellArray an array of {@link java.lang.String} objects.
	 */
	public MultiCommands(String[] shellArray) {
		this.inputCmd = shellArray[0];
		this.mainList = new ArrayList<String>();
		this.delList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(inputCmd);
		while (st.hasMoreTokens()) {
			str = st.nextToken();
			if ((str.substring(0, 1)).matches("`")) {
				str = str.replace("`", "");
				flag = true;
			}
			if (str.endsWith("`")) {
				str = str.replace("`", "");
				flag = false;
				delList.add(str);
			}
			if (flag == true) {
				delList.add(str);
				subcommand = "<cocanut>";
			} else {
				if (subcommand.length() > 0)
					str = "";				
				if (str.equals("-c") || prev_token.equals("-c")) {					
					mainList.add(subcommand + " " + str);
					if (delList.size() > 0) {						
						delList.add(subcommand + " " + str);
					}
					prev_token = str;
				} else
					mainList.add(subcommand + " " + str);
				subcommand = "";
			}
		}
		if (delList.size() > 0) {
			list.add(new DeleteCommand(delList, shellArray[1], shellArray[2]));
		}
		list.add(new MainCommand(mainList, shellArray[1], shellArray[2]));
	}

	/**
	 * <p>getOrderedCommands.</p>
	 *
	 * @return command list
	 */
	public LinkedList<Command> getOrderedCommands() {		
		return list;
	}
}
