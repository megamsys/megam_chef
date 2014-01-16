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

	/**
	 * @param list
	 * @param compose
	 * @param shellArray
	 */
	public DeleteCommand(List<String> list, String req_id, String node_name) {
		super(list, req_id, node_name);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.megam.chef.shell.CommandComposable#composable()
	 */
	@Override
	public boolean composable() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.megam.chef.shell.CommandComposable#pipeto()
	 */
	@Override
	public String[] pipeto() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return new String[] { "1", "" };
	}
	
	
	
}