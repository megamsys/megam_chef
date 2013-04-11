package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;

/**
 * 
 * @author rajthilak
 * 
 */
public class ComputeInfo implements DataMap, ScriptFeeder, Condition {

	private static final String GROUPS = "groups";
	private static final String IMAGE = "image";
	private static final String FLAVOR = "flavor";
	private static final String SSHKEY = "ssh-key";
	private static final String IDENTITYFILE = "identity-file";
	private static final String SSHUSER = "ssh-user";
	List<Boolean> list = new ArrayList<Boolean>();
	List<String> inputavailablereason = new ArrayList<String>();

	/**
	 * create Map name as ec2 from config.json file
	 */
	private Map<String, String> ec2 = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();
	private AccessData token;

	public ComputeInfo() {
		token = new AccessData();
		token();
	}

	/**
	 * @return ec2 map
	 */
	public Map<String, String> map() {
		if (!ec2.keySet().containsAll(access.keySet())) {
			ec2.putAll(access);
		}
		return ec2;
	}

	/**
	 * 
	 * @return token set values to ec2 Map it is to get the values from
	 *         config.json file
	 */
	public AccessData token() {
		token.setGroups(ec2.get(GROUPS));
		token.setImage(ec2.get(IMAGE));
		token.setFlavor(ec2.get(FLAVOR));
		return token;
	}

	/**
	 * 
	 * @return sshkey
	 */
	public String getSshKey() {
		return map().get(SSHKEY);
	}

	/**
	 * 
	 * @return identity file
	 */
	public String getIdentityFile() {
		return map().get(IDENTITYFILE);
	}

	/**
	 * 
	 * @return ssh user
	 */
	public String getSshUser() {
		return map().get(SSHUSER);
	}

	/**
	 * toString method for ec2 map
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
		return " " + getGroups() + " " + getImage() + " " + getFlavor() + " "
				+ "--" + SSHKEY + " " + getSshKey() + " " + "--" + IDENTITYFILE
				+ " " + getIdentityFile() + " " + "--" + SSHUSER + " "
				+ getSshUser();
	}

	public String getGroups() {
		return "--" + GROUPS + " " + ec2.get(GROUPS);
	}

	public String getImage() {
		return "--" + IMAGE + " " + ec2.get(IMAGE);
	}

	public String getFlavor() {
		return "--" + FLAVOR + " " + ec2.get(FLAVOR);
	}

	public List<String> getReason() {
		// TODO Auto-generated method stub

		return inputavailablereason;
	}

	public boolean ok() {
		// TODO Auto-generated method stub
		Boolean returnokvalue = true;
		validate("groups", "megam");
		validate("image", "ami-56e6a404");
		validate("flavor", "m1.small");
		for (Boolean check : list) {
			if (check != true) {
				returnokvalue = false;
			}
		}
		return returnokvalue;
	}

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
		notNull("groups");
		notNull("image");
		notNull("flavor");
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

		return "EC2 :";
	}

}