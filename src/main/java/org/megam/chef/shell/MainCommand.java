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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.shell.CommandComposable#composable()
	 */
	@Override
	public boolean composable() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.shell.CommandComposable#pipeto()
	 */
	@Override
	public String[] pipeto() throws FileNotFoundException {
		// TODO Auto-generated method stub
		String flag = "1";
		String exec_res = new String();
		Scanner scanner = new Scanner(super.getRedirectOutputFile(), ENCODING.name());
		while (scanner.hasNext()) {
			if (scanner.nextInt() == 0) {
				flag = "0";
				break;
			}
			if (flag == "1")
				exec_res = scanner.next();
			else
				exec_res = null;
		}
		return new String[] { flag, exec_res };
	}

}