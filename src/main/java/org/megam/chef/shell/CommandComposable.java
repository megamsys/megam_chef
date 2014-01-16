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
	public String[] pipeto() throws FileNotFoundException;
}
