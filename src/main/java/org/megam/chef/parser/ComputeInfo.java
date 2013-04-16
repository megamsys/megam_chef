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
	private List<String> inputavailablereason = new ArrayList<String>();

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
		return inputavailablereason;
	}

	public boolean ok() {
		boolean isOk = true;
		isOk = isOk && validate("groups", "megam");
		isOk = isOk && validate("image", "ami-56e6a404");
		isOk = isOk && validate("flavor", "m1.small");		
		return isOk;
	}

	public boolean validate(String key, String value) {
		for (Map.Entry<String, String> entry : map().entrySet()) {
			if (entry.getKey().equals(key)) {
				if (entry.getValue().equals(value)) {
					return true;
				} else {
					inputavailablereason.add(key + " is not valid ");
				}
			}
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	public boolean inputAvailable() {
		boolean isAvailable = true;
		isAvailable = isAvailable && notNull("groups");
		isAvailable = isAvailable && notNull("image");
		isAvailable = isAvailable && notNull("flavor");
		return isAvailable;
	}

	public boolean notNull(String str) {
		if (map().containsKey(str)) {
			return true;
		} else {
			inputavailablereason.add(str + " is Missing");
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#name()
	 */
	public String name() {
		return "ComputeInfo";
	}

}