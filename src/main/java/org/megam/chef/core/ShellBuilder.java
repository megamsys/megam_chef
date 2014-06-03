package org.megam.chef.core;

import java.util.LinkedList;
import java.util.List;

import org.megam.chef.identity.IIDentity;
import org.megam.chef.parser.JSONRequestParser;
import org.megam.chef.shell.SingleShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ShellBuilder builds a string that can be consumed by a Shell
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class ShellBuilder {

	/**
	 * The buildString iterates all the script feeders and prepares a string
	 * which can be used by a shell. It only performes this function if a script
	 * feeder "can feed" to a shell.
	 *
	 * @param scriptFeeder a {@link java.util.LinkedList} object.
	 * @param jrp a {@link org.megam.chef.parser.JSONRequestParser} object.
	 * @param fp a {@link java.util.List} object.
	 * @return an array of {@link java.lang.String} objects.
	 */
	public static String[] buildString(LinkedList<ScriptFeeder> scriptFeeder, JSONRequestParser jrp, List<IIDentity> fp) {
		StringBuilder shellB = new StringBuilder();		
		for (ScriptFeeder sf : scriptFeeder) {
			if (sf.canFeed()) {				
				shellB.append(sf.feed().getShellString());

			}
		}		
		for (IIDentity ii : fp) {			
			shellB.append(ii.toString());
		}			
		return new String[] { shellB.toString(), jrp.getReqId(), jrp.getNodeName() };

	}

}
