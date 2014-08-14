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
package org.megam.chef;

import java.io.File;

/**
 * <p>Constants class.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public class Constants {

	/**
	 * The version that gets printed in the log after booting.
	 */
	public static final String VERSION = "0.5";
	/**
	 * The build date of the the jar. This might get tweaked by pulling the date
	 * when maven jars the file.
	 */
	public static final String BUILD_DATE = "20140206";

	/**
	 * The current directory from where the code runs if this called from akka
	 * (/usr/local/share/megamakka, then that would be the user.dir.
	 */
	private static String MEGAM_DEFAULT_HOME = System.getProperty("user.dir");

	/**
	 * The user home directory in unix this is ~ or /home/user.home Fix in AWS
	 * which doesn't allow /root/.megam to be stored. Hence we use
	 * ENV[MEGAM_HOME] (or) user.dir.
	 */
	public static final String MEGAM_USER_HOME = (System.getenv("MEGAM_HOME") != null && System
			.getenv("MEGAM_HOME").trim().length() > 0) ? System
			.getenv("MEGAM_HOME") : MEGAM_DEFAULT_HOME;

	/** Constant <code>MEGAM_AWS_ACCESS_KEY="System.getenv(MEGAM_AWS_ACCESS_KEY)"</code> */
	public static final String MEGAM_AWS_ACCESS_KEY = System
			.getenv("MEGAM_AWS_ACCESS_KEY");

	/** Constant <code>MEGAM_AWS_SECRET_ID="System.getenv(MEGAM_AWS_SECRET_ID)"</code> */
	public static final String MEGAM_AWS_SECRET_ID = System
			.getenv("MEGAM_AWS_SECRET_ID");
	/**
	 * The location of the app yaml configuration file, relative to
	 * MEGAM_CHEF_ROOT
	 */
	public static String MEGAM_CHEF_APP_YAML = MEGAM_USER_HOME
			+ java.io.File.separator + "megamd" + java.io.File.separator
			+ "chefapp.yaml";

	/** Constant <code>MEGAM_DEFAULT_CHEF_APP_YAML="chefapp.yaml"</code> */
	public static final String MEGAM_DEFAULT_CHEF_APP_YAML = "chefapp.yaml";

	/**
	 * The location of the Credential files, relative to MEGAM_USER_HOME
	 */
	public static String MEGAM_VAULT = MEGAM_USER_HOME + java.io.File.separator ;

	/** Constant <code>BUCKET_NAME="cloudkeys"</code> */
	public static String BUCKET_NAME = "cloudkeys";

	/**
	 * The location of the log file
	 */
	public static final String MEGAM_CHEF_LOG = MEGAM_USER_HOME
			+ java.io.File.separator + "logs" + java.io.File.separator;

	/** Constant <code>HELP_GITHUB="Refer https://github.com/indykish/megam"{trunked}</code> */
	public static final String HELP_GITHUB = "Refer https://github.com/indykish/megam_chef.git for more info.";

}
