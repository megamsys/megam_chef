package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.megam.chef.exception.ShellException;

/**
 * 
 * @author rajthilak
 *
 */
public interface CommandComposable {

	public boolean composable();
	public List<String> pipeto(String pipeIt) throws ShellException, FileNotFoundException ;
	public void composePlaceHolder(String placeHolder);
	public String appliedPlaceHolder();
}
