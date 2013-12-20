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
package org.megam.scm.git;

import java.io.File;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.megam.chef.exception.SCMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Git Repository gets the local path and git repository path, to clone the
 * project from git repository to local path
 * 
 * @author rajthilak
 * 
 */

public class GitRepository {

	private String localPath;
	private Logger logger = LoggerFactory.getLogger(GitRepository.class);

	public GitRepository(String localPath) throws SCMException {
		logger.debug("----> setting Repository local path ");
		this.localPath = localPath;
	}

	public GitRepository clone(String remotePath) throws SCMException {
		try {
			logger.debug("----> Repository Cloning start..");
			Git.cloneRepository().setURI(remotePath)
					.setDirectory(new File(localPath)).call();
			logger.debug("----> Repository Cloning completed..");
		} catch (InvalidRemoteException ire) {
			throw new SCMException("", ire);
		} catch (TransportException te) {
			throw new SCMException("", te);
		} catch (GitAPIException gae) {
			throw new SCMException("", gae);
		}
		return this;
	}

}