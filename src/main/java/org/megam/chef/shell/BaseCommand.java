package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>Abstract BaseCommand class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public abstract class BaseCommand implements Command {
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private File rdo;
	private File rde;
	private List<String> cmdList = new ArrayList<String>();
	private String name;
	private String inputCmd;
	private String reqId;
	private String node_name;
	private boolean compose;

	/**
	 * <p>Constructor for BaseCommand.</p>
	 *
	 * @param list a {@link java.util.List} object.
	 * @param req_id a {@link java.lang.String} object.
	 * @param nodeName a {@link java.lang.String} object.
	 */
	public BaseCommand(List<String> list, String req_id, String nodeName) {
		this.reqId = req_id;
		this.node_name = nodeName;
		this.cmdList = list;
		if (compose) {
		}
		File dir = new File(org.megam.chef.Constants.MEGAM_CHEF_LOG + node_name);
		dir.mkdir();
		//limitation in heka, it doesn't pull all the files under node_name hence ignore reqId
    //+ "/" + node_name + "_" + reqId + "_out");
		setRedirectOutput(org.megam.chef.Constants.MEGAM_CHEF_LOG + node_name
		+ "/" + node_name + "_out");
		setRedirectError(org.megam.chef.Constants.MEGAM_CHEF_LOG + 	node_name
		+ "/" + node_name + "_err");
	}

	/**
	 * <p>getFileName.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFileName() {
		return name;
	}

	/** {@inheritDoc} */
	public void setRedirectOutput(String trdo) {
		this.rdo = new File(trdo);
	}

	/**
	 * <p>getRedirectOutputFile.</p>
	 *
	 * @return redirect output file
	 */
	public File getRedirectOutputFile() {
		return rdo;
	}

	/**
	 * <p>getRawCommandString.</p>
	 *
	 * @return raw command string
	 */
	public String getRawCommandString() {
		return inputCmd;
	}

	/**
	 * <p>getCommandList.</p>
	 *
	 * @return command list
	 */
	public List<String> getCommandList() {
		List<String> newList = new ArrayList<String>();
		for (int i = 0; i < cmdList.size(); i++) {
			newList.add(trimmer(cmdList.get(i)));
		}
		return newList;
	}

	/** {@inheritDoc} */
	public void setRedirectError(String trde) {
		this.rde = new File(trde);
	}

	/**
	 * <p>getRedirectErrorFile.</p>
	 *
	 * @return redirect error file
	 */
	public File getRedirectErrorFile() {
		return rde;
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		return  getCommandList().toString();
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

}
