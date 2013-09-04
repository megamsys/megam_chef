package org.megam.chef.parser;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.core.Condition;

/**
 * 
 * @author rajthilak
 * 
 */
public class SystemProvider implements Condition {

	/**
	 * @param args
	 */
	List<Boolean> list = new ArrayList<Boolean>();
	List<String> inputavailablereason = new ArrayList<String>();
	private static final String PROVISIONER = "prov";
	private Map<String, String> provider = new HashMap<String, String>();

	public SystemProvider() {
	}

	/**
	 * 
	 * @return provider
	 */
	public Map<String, String> map() {
		return provider;
	}

	
	/**
	 * toString method for provisioner
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

	public List<String> getReason() {
		// TODO Auto-generated method stub
		return inputavailablereason;
	}

	public boolean ok() {
		// TODO Auto-generated method stub
		Boolean returnokvalue = true;
		validate("prov", "chef");
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
					inputavailablereason.add("Incorrect Provisioner");
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

		notNull("prov");
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

			inputavailablereason.add("Provisioner is Missing");
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
		return "PROVISIONER :";
	}

}
