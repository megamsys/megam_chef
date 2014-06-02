package org.megam.chef.parser;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.megam.chef.Constants;
import org.megam.chef.cloudformatters.AmazonCloudFormatter;
import org.megam.chef.cloudformatters.GoogleCloudFormatter;
import org.megam.chef.cloudformatters.HPCloudFormatter;
import org.megam.chef.cloudformatters.OpenNebulaCloudFormatter;
import org.megam.chef.cloudformatters.ProfitBricksCloudFormatter;
import org.megam.chef.cloudformatters.GoGridFormatter;
import org.megam.chef.cloudformatters.OutputCloudFormatter;
import org.megam.chef.core.Condition;
import org.megam.chef.core.ScriptFeeder;
import org.megam.chef.shell.FedInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>ComputeInfo class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class ComputeInfo implements DataMap, ScriptFeeder, Condition {

	/** Constant <code>GROUPS="groups"</code> */
	public static final String GROUPS = "groups";	
	/** Constant <code>IMAGE="image"</code> */
	public static final String IMAGE = "image";
	/** Constant <code>FLAVOR="flavor"</code> */
	public static final String FLAVOR = "flavor";
	/** Constant <code>CPUS="cpus"</code> */
	public static final String CPUS = "cpus";
	/** Constant <code>RAM="ram"</code> */
	public static final String RAM = "ram";
	/** Constant <code>HDD="hdd-size"</code> */
	public static final String HDD = "hdd-size";
	/** Constant <code>TENANTID="tenant_id"</code> */
	public static final String TENANTID = "tenant_id";
	/** Constant <code>SSHKEY="ssh_key"</code> */
	public static final String SSHKEY = "ssh_key";
	/** Constant <code>IDENTITYFILE="identity_file"</code> */
	public static final String IDENTITYFILE = "identity_file";
	/** Constant <code>SSHUSER="ssh_user"</code> */
	public static final String SSHUSER = "ssh_user";
	/** Constant <code>VAULTLOCATION="vault_location"</code> */
	public static final String VAULTLOCATION = "vault_location";
	/** Constant <code>SSHPUBLOCATION="sshpub_location"</code> */
	public static final String SSHPUBLOCATION = "sshpub_location";
	/** Constant <code>CREDENTIALFILE="credential_file"</code> */
	public static final String CREDENTIALFILE = "credential_file";
	/** Constant <code>ZONE="zone"</code> */
	public static final String ZONE = "zone";
	/** Constant <code>REGION="region"</code> */
	public static final String REGION = "region";
	private String req_type;
	/**
	 * create Map name as cc (cross cloud) from config.json file
	 */
	private String cctype;
	private Map<String, String> cc = new HashMap<String, String>();
	private Map<String, String> access = new HashMap<String, String>();

	private OutputCloudFormatter ocf = null;

	/**
	 * <p>Constructor for ComputeInfo.</p>
	 *
	 * @param req_type a {@link java.lang.String} object.
	 */
	public ComputeInfo(String req_type) {
		this.req_type = req_type;
		// tricky, gson populated your private vars (map) yet ?
	}

	private void createOCF() {
		switch (getCCType()) {
		case "ec2":
			ocf = new AmazonCloudFormatter(map(), req_type);
			break;
		case "google":			
			ocf = new GoogleCloudFormatter(map(), req_type);
			break;
		case "hp":
			ocf = new HPCloudFormatter(map(), req_type);
			break;
		case "profitbricks":
			ocf = new ProfitBricksCloudFormatter(map(), req_type);
			break;
		case "gogrid":
			ocf = new GoGridFormatter(map(), req_type);
			break;
		case "opennebula":
			ocf = new OpenNebulaCloudFormatter(map(), req_type);
			break;
		default:
			throw new IllegalArgumentException(
					getCCType()
							+ ": configuration not supported yet. We are working on it.\n"
							+ Constants.HELP_GITHUB);
		}
	}


	/**
	 * <p>setReqType.</p>
	 *
	 * @param req_type a {@link java.lang.String} object.
	 * @since 0.5.0
	 */
	public void setReqType(String req_type) {
		this.req_type = req_type;
	}
	
	/**
	 * <p>getReqType.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 * @since 0.5.0
	 */
	public String getReqType() {
		return req_type;
	}
	
	private String getCCType() {
		return cctype;
	}

	/**
	 * <p>getVaultLocation.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getVaultLocation() {
		return map().get(VAULTLOCATION);
	}

	/**
	 * <p>getSshPubLocation.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getSshPubLocation() {
		return map().get(SSHPUBLOCATION);
	}

	/**
	 * <p>map.</p>
	 *
	 * @return ec2 map
	 */
	public Map<String, String> map() {
		if (!cc.keySet().containsAll(access.keySet())) {
			cc.putAll(access);
		}
		return cc;
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
		Map<String, String> ocfout = ocf.format();
		if (ocfout != null) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : ocfout.entrySet()) {
				if (entry.getValue().length() > 0) {
					sb.append(" ");
					sb.append(entry.getKey());
					sb.append(" ");
					sb.append(entry.getValue());
					sb.append(" ");
				}
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
	/**
	 * <p>name.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String name() {
		return "ComputeInfo";
	}

	/** {@inheritDoc} */
	@Override
	public boolean ok() {
		return ocf.ok();
	}

	/** {@inheritDoc} */
	@Override
	public boolean inputAvailable() {
		createOCF();
		return ocf.inputAvailable();
	}

	/**
	 * <p>getReason.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getReason() {
		return ocf.getReason();
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
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

}
