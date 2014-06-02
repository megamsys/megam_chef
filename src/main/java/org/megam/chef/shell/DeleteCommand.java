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
 * <p>DeleteCommand class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class DeleteCommand extends BaseCommand {
	
	 private String placeHolder = "";
	 private String appliedPlaceHolder = new String();
	 private final static Charset ENCODING = StandardCharsets.UTF_8;
	/**
	 * <p>Constructor for DeleteCommand.</p>
	 *
	 * @param list a {@link java.util.List} object.
	 * @param req_id a {@link java.lang.String} object.
	 * @param node_name a {@link java.lang.String} object.
	 */
	public DeleteCommand(List<String> list, String req_id, String node_name) {
		super(list, req_id, node_name);
	}

	
	/** {@inheritDoc} */
	@Override
	public boolean composable() {
		return true;
	}	
	
	/** {@inheritDoc} */
	public void composePlaceHolder(String tmpPlaceHolder) {
		placeHolder = tmpPlaceHolder;
		
	}
	
	/**
	 * <p>appliedPlaceHolder.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String appliedPlaceHolder() {		
		return appliedPlaceHolder;
	}


	/* (non-Javadoc)
	 * @see org.megam.chef.shell.CommandComposable#pipeto(java.lang.String[])
	 */
	/** {@inheritDoc} */
	@Override
	public List<String> pipeto(String pipeIt) throws ShellException, FileNotFoundException {
		String flag = "1";
		String exec_res = new String();
		Scanner scanner = new Scanner(super.getRedirectOutputFile(), ENCODING.name());
		while (scanner.hasNext()) {
			String s = scanner.next();			
			if (s == "1") {
				flag = "0";
				break;
			}
			if (flag == "1")
				this.appliedPlaceHolder = s;
			else
				this.appliedPlaceHolder = null;
		}			
		return null;
		//return new String[] { flag, placeHolder, appliedPlaceHolder }; //send back succes or failure, placeholdername,appliedplaceholder
	}
	
	
}
