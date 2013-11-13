package org.megam.chef.core;

import java.util.LinkedList;
import java.util.List;

import org.megam.chef.identity.IIDentity;
import org.megam.chef.parser.JSONRequestParser;

/**
 * ShellBuilder builds a string that can be consumed by a Shell
 * 
 * @author rajthilak
 * 
 */
public class ShellBuilder {

	/**
	 * The buildString iterates all the script feeders and prepares a string
	 * which can be used by a shell. It only performes this function if a script
	 * feeder "can feed" to a shell.
	 * 
	 * @param args
	 */
	public static String[] buildString(LinkedList<ScriptFeeder> scriptFeeder, JSONRequestParser jrp, List<IIDentity> fp) {

		StringBuilder nameB = new StringBuilder();
		StringBuilder shellB = new StringBuilder();		
		for (ScriptFeeder sf : scriptFeeder) {
			if (sf.canFeed()) {
				nameB.append(sf.feed().getName());
				shellB.append(sf.feed().getShellString());

			}
		}
		for (IIDentity ii : fp) {
			shellB.append(ii.toString());
		}
		return new String[] { nameB.toString(), shellB.toString(), jrp.getReqId(), jrp.getNodeName() };

	}

}
