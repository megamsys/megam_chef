package org.megam.chef.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 
 * @author rajthilak
 *
 */
public interface CommandComposable {

	public boolean composable();
	public String[] pipeto(String[] pipeIt) throws ShellExcepiton ;
	public void composePlaceHolder(String placeHolder);
	public String appliedPlaceHolder();
}
