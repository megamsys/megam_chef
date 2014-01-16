package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author rajthilak
 * 
 */
public class MainCommand extends BaseCommand {
	private final static Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * @param list
	 * @param compose
	 * @param shellArray
	 */
	public MainCommand(List<String> list, String req_id, String node_name) {
		super(list, req_id, node_name);
		// TODO Auto-generated constructor stub
	}

	
	public boolean composable() {
		return false;
	}

	
	public String[] pipeto(String[] pipeIt) throws ShellException {
		//move the code you have outside to search on placeholder from the array.
		/*private List<String> commandlist(List<String> cmdList, Command command,
				int size) throws FileNotFoundException {
			List<String> newList = new ArrayList<String>();
			String pipe;
			if (size <= 1) {

				pipe = "1";
				System.out.println("---------------------------->" + pipe);
			} else {
				pipe = command.pipeto()[0];
				System.out.println("-----------+++++++-------------->" + pipe);
			}
			if (pipe != "0") {
				for (int i = 0; i < cmdList.size(); i++) {
					if (cmdList.get(i).contains("<node_name>")) {
						newList.add(trimmer(cmdList.get(i).replace("<node_name>",
								command.pipeto()[1])));
					} else {
						newList.add(trimmer(cmdList.get(i)));
					}
				}
			} else
				newList = null;
			return newList;
		}
*/
		
	}

}