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

import org.junit.Test;
import org.megam.chef.exception.SCMException;
import org.megam.scm.git.GitRepository;
import org.megam.chef.Constants;

/**
 * @author rajthilak
 * 
 */
public class GitRepositoryTest {

	/**
	 * 
	 * @throws SCMException	 
	 */
	@Test
	public void test() throws SCMException  {
		(new GitRepository(Constants.MEGAM_USER_HOME+"/"+new File("Repo"))).clone("https://github.com/rajthilakmca/chef-repo.git");
		assertTrue("This will succeed.", true);
	}

}
