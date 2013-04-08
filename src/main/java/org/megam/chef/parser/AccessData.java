package org.megam.chef.parser;

public class AccessData {
	
	//In this class declare set and get methods for JSON file
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
	
	public String getGroups() {
		return groups;
	}
	
	public void setGroups(String tempGroups) {
		this.groups  = tempGroups;		
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image  = image;		
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public void setFlavor(String flavor) {
		this.flavor = flavor;		
	}
	
	public String getProvisioner() {
		return prov;
	}
	
	public void setProvisioner(String prov) {
		this.prov= prov;
	}
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command= command;
	}
	
	public String getPlugin() {
		return plugin;
	}
	public void setPlugin(String plugin) {
		this.plugin= plugin;
	}
	public String getRunList() {
		return runlist;
	}
	
	public void setRunList(String runlist) {
		this.runlist= runlist;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name= name;
	}

	public String getSshKey() {
		return sshkey;
	}
	
	public void setSshKey(String sshkey) {
		this.sshkey= sshkey;
	}
	
	public String getIdentityFile() {
		return identityfile;
	}
	
	public void setIdentityFile(String identityfile) {
		this.identityfile= identityfile;
	}
	
	public String getSshUser() {
		return sshuser;
	}
	
	public void setSshUser(String sshuser) {
		this.sshuser= sshuser;
	}
}