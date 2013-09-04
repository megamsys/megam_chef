package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;
import org.megam.chef.shell.Command;
import org.megam.chef.shell.FedInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author rajthilak
 * 
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

	public ChefInfo() {
		super("CHEF");
	}

	/**
	 * @return chef map
	 */
	public Map<String, String> map() {
		return chef;
	}

	public String getCommand() {
		return chef.get(COMMAND);
	}

	public String getPlugin() {
		return chef.get(PLUGIN);
	}

	public String getRunList() {
		return "--" + RUNLIST + " " + chef.get(DRUNLIST);
	}

	public String getName() {
		return chef.get(NAME);
	}

	public List<String> getReason() {
		return missingReasonToList;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull(COMMAND);
		isAvailable = isAvailable && notNull(PLUGIN);
		isAvailable = isAvailable && notNull(RUNLIST);
		isAvailable = isAvailable && notNull(NAME);
		return isAvailable;
	}

	public boolean notNull(String str) {
		if (map().containsKey(str)) {
			return true;
		} else {
			missingReasonToList.add(str + " is Missing");
			return false;
		}
	}

	public boolean canFeed() {
		return true;
	}

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
	public String name() {
		return "CHEF :";
	}

	/**
	 * cmd =new Command("knife ec2 server list");
	 */
	public Condition initCondition() {
		return new InitChefCondition();
	}

	/**
	 * tostring method for chef map
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		logger.debug("-------> ChefInfo =>");

		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
		logger.debug("-------> ChefInfo =>");

		return strbd.toString();
	}

	private class InitChefCondition implements Condition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#getReason()
		 */
		public List<String> getReason() {
			List<String> list = new ArrayList<String>();
			list.add("Default");
			list.add("command");
			list.add("not");
			list.add("valid");
			return list;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#ok()
		 */
		public boolean ok() {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#inputAvailable()
		 */
		public boolean inputAvailable() {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#name()
		 */
		public String name() {
			return "Chef Verification:";
		}

	}

}
