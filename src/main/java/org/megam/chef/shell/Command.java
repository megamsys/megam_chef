package org.megam.chef.shell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rajthilak
 *
 */
public interface Command extends CommandComposable{

	public String getFileName();
	public void setRedirectOutput(String trdo);
	public File getRedirectOutputFile();
	public String getRawCommandString();
	public List<String> getCommandList();
	public void setRedirectError(String trde);
	public File getRedirectErrorFile();
	public String toString();
}
