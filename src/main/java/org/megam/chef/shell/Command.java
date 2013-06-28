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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author rajthilak
 * 
 */
public class Command {

	private File rdo;
	private File rde;
	private List<String> cmdList = new ArrayList<String>();
	private String name;
	private String inputCmd;

	/**
	 * 
	 * @param s
	 */
	public Command(String[] shellArray) {
		this.name = shellArray[0].toLowerCase();
		this.inputCmd = shellArray[1];
		StringTokenizer st = new StringTokenizer(inputCmd);
		while (st.hasMoreTokens()) {
			cmdList.add(st.nextToken());
		}
		
	   
	    
		setRedirectOutput(name+ "out");
		setRedirectError(name+ "err");
	}

	/**
	 * 
	 * @param trdo
	 */
	public void setRedirectOutput(String trdo) {
		this.rdo = new File(trdo);
	}

	/**
	 * 
	 * @return redirect output file
	 */
	public File getRedirectOutputFile() {
		return rdo;
	}

	/**
	 * 
	 * @return raw command string
	 */
	public String getRawCommandString() {
		return inputCmd;
	}

	/**
	 * 
	 * @return command list
	 */
	public List<String> getCommandList() {
		System.out.println("");
		System.out.println("get command list:" + cmdList);
		System.out.println("");
		return cmdList;
	}

	/**
	 * 
	 * @param trde
	 */
	public void setRedirectError(String trde) {
		this.rde = new File(trde);
	}

	/**
	 * 
	 * @return redirect error file
	 */
	public File getRedirectErrorFile() {
		return rde;
	}

	public String toString() {
		return "(" + getCommandList() + ")";
	}
}
