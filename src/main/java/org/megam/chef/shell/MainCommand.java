package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.megam.chef.exception.ShellException;

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

	
	public List<String> pipeto(String pipeIt) throws ShellException {
		
		//move the code you have outside to search on placeholder from the array.		
			List<String> newList = new ArrayList<String>();
			List<String> cmdList = new ArrayList<String>();
			String pipe;
			cmdList = super.getCommandList();
				for (int i = 0; i < cmdList.size(); i++) {
					if (cmdList.get(i).contains("<cocanut>")) {
						newList.add(cmdList.get(i).replace("<cocanut>", pipeIt));
					} else {
						newList.add(cmdList.get(i));
					}				
			} 
			return newList;			
	}
	

	/* (non-Javadoc)
	 * @see org.megam.chef.shell.CommandComposable#composePlaceHolder(java.lang.String)
	 */
	@Override
	public void composePlaceHolder(String placeHolder) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see org.megam.chef.shell.CommandComposable#appliedPlaceHolder()
	 */
	@Override
	public String appliedPlaceHolder() {
		// TODO Auto-generated method stub
		return null;
	}

}