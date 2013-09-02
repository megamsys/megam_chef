package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;

/**
 * 
 * @author rajthilak
 * 
 */
public class JSONRequest {
	
	private ComputeInfo compute;
	private SystemProvider systemprovider;
	private ChefInfo cloudtool;

	public JSONRequest() {
	}

	/**
	 * 
	 * @return compute object
	 */
	public ComputeInfo getAccess() {
		return compute;
	}

	/**
	 * 
	 * @return system provider
	 */
	public SystemProvider getProv() {
		return systemprovider;
	}

	/**
	 * 
	 * @return chef service object
	 */
	public ProvisionerInfo getCloudTool() {
		return cloudtool;
	}

	/**
	 * 
	 * @return linked list object
	 */
	public LinkedList<ScriptFeeder> scriptFeeder() {
		LinkedList<ScriptFeeder> list = new LinkedList<ScriptFeeder>();
		list.add(cloudtool);
		list.add(compute);
		return list;
	}

	/**
	 * 
	 * @return condition list
	 */
	public List<Condition> conditionList() {
		List<Condition> list = new ArrayList<Condition>();
		list.add(cloudtool);
		list.add(compute);
		list.add(systemprovider);
		list.add(getCloudTool().initCondition());
		return list;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", (getAccess()!=null ? getAccess().toString() : "Access[None]"));
		formatter.format("%s%n", (getProv()!=null ?  getProv().toString() : "Prov[None]"));
		formatter.format("%s%n", (getCloudTool()!=null ? getCloudTool().toString() : "Chef[None"));
		// formatter.format("%s%n", getService().toString());
		formatter.close();
		return strbd.toString();
	}

}