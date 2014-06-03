package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.megam.chef.exception.ShellException;

/**
 * <p>CommandComposable interface.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public interface CommandComposable {

	/**
	 * <p>composable.</p>
	 *
	 * @return a boolean.
	 */
	public boolean composable();
	/**
	 * <p>pipeto.</p>
	 *
	 * @param pipeIt a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 * @throws org.megam.chef.exception.ShellException if any.
	 * @throws java.io.FileNotFoundException if any.
	 */
	public List<String> pipeto(String pipeIt) throws ShellException, FileNotFoundException ;
	/**
	 * <p>composePlaceHolder.</p>
	 *
	 * @param placeHolder a {@link java.lang.String} object.
	 */
	public void composePlaceHolder(String placeHolder);
	/**
	 * <p>appliedPlaceHolder.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String appliedPlaceHolder();
}
