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
 * 
 * @author rajthilak
 * 
 */
public class MultiCommands {

	private List<String> mainList, delList;
	private String name;
	private String inputCmd;
	private boolean flag = false;
	private String str, subcommand = "";
	private LinkedList<Command> list = new LinkedList<Command>();

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
				subcommand = "<node_name>";
			} else {
				if (subcommand.length() > 0)
					str = "";
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
	 * 
	 * @return command list
	 */
	public LinkedList<Command> getOrderedCommands() {
		System.out.println("");
		System.out.println("get command list:" + list);
		System.out.println("");
		return list;
	}
}
