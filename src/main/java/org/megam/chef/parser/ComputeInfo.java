package org.megam.chef.parser;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.Constants;
import org.megam.chef.cloudformatters.AmazonCloudFormatter;
import org.megam.chef.cloudformatters.GoogleCloudFormatter;
import org.megam.chef.cloudformatters.HPCloudFormatter;
import org.megam.chef.cloudformatters.OutputCloudFormatter;
import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;
import org.megam.chef.shell.FedInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author rajthilak
 * 
 */
public class ComputeInfo implements DataMap, ScriptFeeder, Condition {

	public static final String GROUPS = "groups";
	public static final String IMAGE = "image";
	public static final String FLAVOR = "flavor";
	public static final String SSHKEY = "ssh-key";
	public static final String DSSHKEY = "ssh_key";
	public static final String IDENTITYFILE = "identity-file";
	public static final String DIDENTITYFILE = "identity_file";
	public static final String SSHUSER = "ssh-user";
	public static final String DSSHUSER = "ssh_user";
	public static final String VAULTLOCATION = "vault_location";
	public static final String SSHPUBLOCATION = "sshpub_location";
	public static final String CREDENTIALFILE = "credential_file";
	public static final String ZONE = "zone";

	/**
	 * create Map name as cc (cross cloud) from config.json file
	 */
	private String cctype;
	private Map<String, String> cc = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();

    private OutputCloudFormatter ocf = null;
	private Logger logger = LoggerFactory.getLogger(ComputeInfo.class);

	public ComputeInfo() {
	}

	public String getCCType() {
		return cctype;
	}

	public String getGroups() {
		return cc.get(GROUPS);
	}

	public String getImage() {
		return cc.get(IMAGE);
	}

	public String getFlavor() {
		return cc.get(FLAVOR);
	}

	/**
	 * 
	 * @return sshkey
	 */
	public String getSshKey() {
		return map().get(DSSHKEY);
	}

	/**
	 * 
	 * @return identity file
	 */
	public String getIdentityFile() {
		return map().get(DIDENTITYFILE);
	}

	/**
	 * 
	 * @return vault location
	 */
	public String getVaultLocation() {
		return map().get(VAULTLOCATION);
	}

	/**
	 * 
	 * @return ssh user
	 */
	public String getSshUser() {
		return map().get(DSSHUSER);
	}

	/**
	 * @return ec2 map
	 */
	public Map<String, String> map() {
		if (!cc.keySet().containsAll(access.keySet())) {
			cc.putAll(access);
		}
		return cc;
	}

	public boolean canFeed() {
		return true;
	}

	public FedInfo feed() {
		switch (getCCType()) {
		case "ec2":
			ocf = new AmazonCloudFormatter();
			break;
		case "google":
			ocf = new GoogleCloudFormatter();
			break;
		case "hp":
			ocf = new HPCloudFormatter();
			break;
		default:
			throw new IllegalArgumentException(
					getCCType()
							+ ": configuration not supported yet. We are working on it.\n"
							+ Constants.HELP_GITHUB);
		}

		if (ocf.format(map()) != null) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : ocf.format(map()).entrySet()) {
				sb.append(" ");
				sb.append(entry.getKey());
				sb.append(" ");
				sb.append(entry.getValue());
				sb.append(" ");
			}
			return (new FedInfo(name(), sb.toString()));
		} else {
			throw new IllegalArgumentException(getCCType()
					+ ": Can't proceed with arguments missing \n"
					+ ocf.toString() + "\n" + Constants.HELP_GITHUB);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.core.Condition#name()
	 */
	public String name() {
		return "ComputeInfo";
	}

	public List<String> getReason() {
		return ocf.getReason();
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

	/* (non-Javadoc)
	 * @see org.megam.chef.core.Condition#ok()
	 */
	@Override
	public boolean ok() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.megam.chef.core.Condition#inputAvailable()
	 */
	@Override
	public boolean inputAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}