package org.megam.chef.shell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Command interface.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public interface Command extends CommandComposable{

	/**
	 * <p>getFileName.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getFileName();
	/**
	 * <p>setRedirectOutput.</p>
	 *
	 * @param trdo a {@link java.lang.String} object.
	 */
	public void setRedirectOutput(String trdo);
	/**
	 * <p>getRedirectOutputFile.</p>
	 *
	 * @return a {@link java.io.File} object.
	 */
	public File getRedirectOutputFile();
	/**
	 * <p>getRawCommandString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getRawCommandString();
	/**
	 * <p>getCommandList.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getCommandList();
	/**
	 * <p>setRedirectError.</p>
	 *
	 * @param trde a {@link java.lang.String} object.
	 */
	public void setRedirectError(String trde);
	/**
	 * <p>getRedirectErrorFile.</p>
	 *
	 * @return a {@link java.io.File} object.
	 */
	public File getRedirectErrorFile();
	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString();
}
