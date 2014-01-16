/* 
 ** Copyright [2012-2013] [Megam Systems]
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ** See the License for the specific language governing permissions and
 ** limitations under the License.
 */
package org.megam.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.chef.ChefServiceRunner;
import org.megam.chef.DropIn;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.IdentifierException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;

/**
 * @author rajthilak
 * 
 */
public class ChefServiceTest {

	/**
	 * 
	 * @throws IOException
	 * @throws SourceException
	 * @throws ProvisionerException
	 * @throws BootStrapChefException
	 */

	@Test
	public void test() throws SourceException, ProvisionerException,
			BootStrapChefException, IOException, IdentifierException {
		try {
			(new ChefServiceRunner()).withType(TYPE.CHEF_WITH_SHELL).input(new DropIn("RIP423827429656625152")).control();
			assertTrue("This will succeed.", true);
		} catch (IOException ioe) {
			throw new BootStrapChefException(ioe);
		}
	}

	/*
	 * @Test public void test() throws IOException { ProcessBuilder shellProc;
	 * List<String> list = new ArrayList<String>(); File dir = new
	 * File(org.megam.chef.Constants.MEGAM_CHEF_LOG + "test"); dir.mkdir(); File
	 * dir1 = new File(org.megam.chef.Constants.MEGAM_CHEF_LOG + "test2");
	 * dir1.mkdir(); //shellProc = new ProcessBuilder(
	 * "knife search node name:biggie1.megam.co -c cloudrecipes/sandy@megamsandbox.com/default_chef/chef-repo/.chef/knife.rb "
	 * ); list.add("knife"); list.add("search");list.add("node");
	 * list.add("name:biggie1.megam.co"); list.add("-c"); list.add(
	 * "cloudrecipes/sandy@megamsandbox.com/default_chef/chef-repo/.chef/knife.rb"
	 * ); list.add("-a"); list.add(" ec2.instance_id"); list.add("|");
	 * list.add("grep");
	 * list.add("ec2.instance_id");list.add("|");list.add("awk");
	 * list.add("'{print $2}'");
	 * 
	 * shellProc = new ProcessBuilder(list);
	 * System.out.println("----------------------------------------"+shellProc);
	 * shellProc.redirectOutput(Redirect.appendTo(new
	 * File(org.megam.chef.Constants.MEGAM_CHEF_LOG + "test/tt")));
	 * shellProc.redirectError(Redirect.appendTo(new
	 * File(org.megam.chef.Constants.MEGAM_CHEF_LOG +"test2/ttt")));
	 * shellProc.start(); }
	 */

}
