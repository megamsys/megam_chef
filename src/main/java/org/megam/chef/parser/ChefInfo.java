package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;
import org.megam.chef.shell.Command;

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
	private static final String NAME = "name";
	
	private static final String KNIFE = "knife";
	
	private List<String>  missingReasonToList = new ArrayList<String>();

	// create Map name as chef from config.json file
	private Map<String, String> chef = new HashMap<String, String>();
	private AccessData token;

	public ChefInfo() {
		super("CHEF");
		token = new AccessData();
		token();
	}

	/**
	 * @return chef map
	 */
	public Map<String, String> map() {
		return chef;
	}

	/**
	 * 
	 * @return set values to chef Map it is to get the values from config.json
	 *         file
	 * 
	 */
	public AccessData token() {
		token.setCommand(chef.get(COMMAND));
		token.setPlugin(chef.get(PLUGIN));
		token.setRunList(chef.get(RUNLIST));
		token.setName(chef.get(NAME));
		return token;
	}

	

	public String getCommand() {
		return chef.get(COMMAND);
	}

	public String getPlugin() {
		return chef.get(PLUGIN);
	}

	public String getRunList() {
		return "--" + RUNLIST + " " + chef.get(RUNLIST);
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

	public String feed() {
		return getCommand() + " " + getPlugin() + " " + getRunList() + " "
				+ getName();
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
		for (Map.Entry<String, String> entry : map().entrySet()) {
			formatter.format("%10s = %s%n", entry.getKey(), entry.getValue());
		}
		formatter.close();
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
