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
public class DeleteCommand extends BaseCommand {
	
	 private String placeHolder = "";
	 private String appliedPlaceHolder = "";
	/**
	 * @param list
	 * @param compose
	 * @param shellArray
	 */
	public DeleteCommand(List<String> list, String req_id, String node_name) {
		super(list, req_id, node_name);
	}

	
	@Override
	public boolean composable() {
		return true;
	}

	
	public String[] pipeto() throws FileNotFoundException {
		String flag = "1";
		String exec_res = new String();
		Scanner scanner = new Scanner(super.getRedirectOutputFile(), ENCODING.name());
		while (scanner.hasNext()) {
			if (scanner.nextInt() == 0) {
				flag = "0";
				break;
			}
			if (flag == "1")
				appliedPlaceHolder = scanner.next();
			else
				appliedPlaceHolder = null;
		}
		return new String[] { flag, placeHolder, appliedPlaceHolder }; //send back succes or failure, placeholdername,appliedplaceholder
	}
	
	public void composePlaceHolder(String tmpPlaceHolder) {
		placeHolder = tmpPlaceHolder;
		
	}
	
	public String appliedPlaceHolder() {
		return appliedPlaceHolder;
	}
	
	
}