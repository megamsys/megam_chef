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
@SuppressWarnings("rawtypes")
public class ChefInfo extends ProvisionerInfo {

	/**
	 * @param args
	 */
	private Command cmd;
	private static final String COMMAND = "command";
	private static final String PLUGIN = "plugin";
	private static final String RUNLIST = "run-list";
	private static final String NAME = "name";

	List<Boolean> list = new ArrayList<Boolean>();
	List<String> inputavailablereason = new ArrayList<String>();

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

	public boolean canFeed() {
		// TODO Auto-generated method stub
		return true;
	}

	public String feed() {
		// TODO Auto-generated method stub

		return getCommand() + " " + getPlugin() + " " + getRunList() + " "
				+ getName();
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
		// TODO Auto-generated method stub

		return inputavailablereason;
	}

	public boolean ok() {
		// TODO Auto-generated method stub
		Boolean returnokvalue = true;
		validate("command", "knife");
		validate("run-list", "role[opendj]");
		for (Boolean check : list) {
			if (check != true) {
				returnokvalue = false;
			}
		}
		return returnokvalue;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void validate(String key, String value) {
		for (Map.Entry<String, String> entry : map().entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					list.add(true);
				} else {
					inputavailablereason.add(key + " is not valid ");
					list.add(false);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	public boolean inputAvailable() {
		// TODO Auto-generated method stub
		Boolean returnvalue = true;
		notNull("command");
		notNull("plugin");
		notNull("run-list");
		notNull("name");
		for (Boolean check : list) {
			if (check != true) {
				returnvalue = false;
			}
		}
		return returnvalue;
	}

	public void notNull(String str) {
		if (map().containsKey(str)) {
			list.add(true);
		} else {
			inputavailablereason.add(str + " is Missing");
			list.add(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#name()
	 */
	public String name() {
		// TODO Auto-generated method stub
		return "CHEF :";
	}

	public Condition initCondition() {

		/*
		 * cmd =new Command("knife ec2 server list");
		 * cmd.setRedirectOutput("kh2"); ProcessApp pro=new ProcessApp(cmd);
		 * ForkJoinPool pool = new ForkJoinPool(); pool.invoke(new
		 * ProcessApp(cmd)); return
		 */
		InitChefCondition ic = new InitChefCondition();
		return ic;

	}

	private class InitChefCondition implements Condition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#getReason()
		 */
		public List<String> getReason() {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#inputAvailable()
		 */
		public boolean inputAvailable() {
			// TODO Auto-generated method stub
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.megam.chef.core.Condition#name()
		 */
		public String name() {
			// TODO Auto-generated method stub
			return "Verification:";
		}

	}
}
