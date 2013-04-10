package org.megam.chef.parser;

/**
 * 
 * @author rajthilak
 * 
 */
public class AccessData {

	// In this class declare set and get methods for JSON file
	private String groups;
	private String image;
	private String flavor;
	private String prov;
	private String command;
	private String plugin;
	private String runlist;
	private String name;
	private String sshkey;
	private String identityfile;
	private String sshuser;

	/**
	 * 
	 * @return groups
	 */
	public String getGroups() {
		return groups;
	}

	/**
	 * 
	 * @param tempGroups
	 */
	public void setGroups(String tempGroups) {
		this.groups = tempGroups;
	}

	/**
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 
	 * @return flavor
	 */
	public String getFlavor() {
		return flavor;
	}

	/**
	 * 
	 * @param flavor
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	/**
	 * 
	 * @return provisioner
	 */
	public String getProvisioner() {
		return prov;
	}

	/**
	 * 
	 * @param prov
	 */
	public void setProvisioner(String prov) {
		this.prov = prov;
	}

	/**
	 * 
	 * @return command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return plugin
	 */
	public String getPlugin() {
		return plugin;
	}

	/**
	 * 
	 * @param plugin
	 */
	public void setPlugin(String plugin) {
		this.plugin = plugin;
	}

	/**
	 * 
	 * @return runlist
	 */
	public String getRunList() {
		return runlist;
	}

	/**
	 * 
	 * @param runlist
	 */
	public void setRunList(String runlist) {
		this.runlist = runlist;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return ssh key
	 */
	public String getSshKey() {
		return sshkey;
	}

	/**
	 * 
	 * @param sshkey
	 */
	public void setSshKey(String sshkey) {
		this.sshkey = sshkey;
	}

	/**
	 * 
	 * @returnn identity file
	 */
	public String getIdentityFile() {
		return identityfile;
	}

	/**
	 * 
	 * @param identityfile
	 */
	public void setIdentityFile(String identityfile) {
		this.identityfile = identityfile;
	}

	/**
	 * 
	 * @return sshuser
	 */
	public String getSshUser() {
		return sshuser;
	}

	/**
	 * 
	 * @param sshuser
	 */
	public void setSshUser(String sshuser) {
		this.sshuser = sshuser;
	}
}