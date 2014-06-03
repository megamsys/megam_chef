package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;
import org.megam.chef.shell.MultiCommands;
import org.megam.chef.shell.FedInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ChefInfo class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class ChefInfo extends ProvisionerInfo {

	/**
	 * @param args
	 */
	private static final String COMMAND = "command";
	private static final String PLUGIN = "plugin";
	private static final String RUNLIST = "run-list";
	private static final String DRUNLIST = "run_list";
	private static final String NAME = "name";

	private static final String KNIFE = "knife";

	private List<String> missingReasonToList = new ArrayList<String>();

	// create Map name as chef from config.json file
	private Map<String, String> chef = new HashMap<String, String>();
	private Logger logger = LoggerFactory.getLogger(ChefInfo.class);

	/**
	 * <p>Constructor for ChefInfo.</p>
	 */
	public ChefInfo() {
		super("CHEF");
	}

	/**
	 * <p>map.</p>
	 *
	 * @return chef map
	 */
	public Map<String, String> map() {
		return chef;
	}

	/**
	 * <p>getCommand.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCommand() {
		if (chef.get(COMMAND).length() > 0)
			return chef.get(COMMAND);
		else
			return "";
	}

	/**
	 * <p>getPlugin.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getPlugin() {
		if (chef.get(PLUGIN).length() > 0)
			return changeServerName(chef.get(PLUGIN));
		else
			return "";
	}

	/**
	 * <p>getRunList.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getRunList() {
		if (chef.get(DRUNLIST).length() > 0)
			return "--" + RUNLIST + " " + chef.get(DRUNLIST);
		else
			return "";

	}

	/**
	 * <p>getName.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		if (chef.get(NAME).length() > 0) {
			String[] parts = chef.get(PLUGIN).split(" ");
			String[] name = (chef.get(NAME)).split(" ");
			String new_name = null;
			//if (parts[0].equals("google")) {
			//	name[1] = name[1].replaceAll("\\W", "");
			//	StringBuilder builder = new StringBuilder();
			//	for (String s : name) {
			//		builder.append(s + " ");
			//	}
			//	new_name = builder.toString();
			//} else {
				new_name = chef.get(NAME);
			//}
			return new_name;
		} else
			return "";

	}

	/**
	 * <p>getReason.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getReason() {
		return missingReasonToList;
	}

	/**
	 * <p>ok.</p>
	 *
	 * @return a boolean.
	 */
	public boolean ok() {
		return validate(COMMAND, KNIFE);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	private boolean validate(String key, String value) {
		boolean isValid = true;
		for (Map.Entry<String, String> entry : map().entrySet()) {
			if (entry.getKey().equals(key)) {
				isValid = isValid && (entry.getValue().equals(value));
				if (!entry.getValue().equals(value)) {
					missingReasonToList.add(key + " is not valid ");
				}
			}
		}
		return isValid;
	}

	private String changeServerName(String plugin) {
		String[] parts = plugin.split(" ");
		String[] name = chef.get(NAME).split(" ");
		String new_plugin = null;
		if (parts[0].equals("google")) {
			parts[3] = name[1].replaceAll("\\W", "");
			StringBuilder builder = new StringBuilder();
			for (String s : parts) {
				builder.append(s + " ");
			}
			new_plugin = builder.toString();
		} else {
			new_plugin = plugin;
		}
		return new_plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	/**
	 * <p>inputAvailable.</p>
	 *
	 * @return a boolean.
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(COMMAND);
		isAvailable = isAvailable && notNull(PLUGIN);
		isAvailable = isAvailable && notNull(RUNLIST);
		isAvailable = isAvailable && notNull(NAME);
		return isAvailable;
	}

	/**
	 * <p>notNull.</p>
	 *
	 * @param str a {@link java.lang.String} object.
	 * @return a boolean.
	 */
	public boolean notNull(String str) {
		if (map().containsKey(str)) {
			return true;
		} else {
			missingReasonToList.add(str + " is Missing");
			return false;
		}
	}

	/**
	 * <p>canFeed.</p>
	 *
	 * @return a boolean.
	 */
	public boolean canFeed() {
		return true;
	}

	/**
	 * <p>feed.</p>
	 *
	 * @return a {@link org.megam.chef.shell.FedInfo} object.
	 */
	public FedInfo feed() {
		FedInfo fed = new FedInfo(getName().split(" ")[1], getCommand() + " "
				+ getPlugin() + " " + getRunList() + " " + getName());

		return fed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#name()
	 */
	/**
	 * <p>name.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String name() {
		return "CHEF :";
	}

	/**
	 * tostring method for chef map
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		logger.debug("<-------> ChefInfo <------->");

		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		logger.debug(strbd.toString());
		logger.debug("<----------------------->");

		return strbd.toString();
	}

}
