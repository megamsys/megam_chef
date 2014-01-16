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

	public BaseCommand(List<String> list, String req_id, String nodeName) {
		this.reqId = req_id;
		this.node_name = nodeName;
		this.cmdList = list;
		if (compose) {
		}
		File dir = new File(org.megam.chef.Constants.MEGAM_CHEF_LOG + node_name);
		dir.mkdir();
		setRedirectOutput(org.megam.chef.Constants.MEGAM_CHEF_LOG + node_name
				+ "/" + node_name + "_" + reqId + "_out");
		setRedirectError(org.megam.chef.Constants.MEGAM_CHEF_LOG + node_name
				+ "/" + node_name + "_" + reqId + "_err");
	}

	public String getFileName() {
		return name;
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