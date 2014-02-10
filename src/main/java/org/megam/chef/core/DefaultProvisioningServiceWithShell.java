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
package org.megam.chef.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.megam.chef.AppYaml;
import org.megam.chef.BootStrapChef;
import org.megam.chef.Constants;
import org.megam.chef.exception.IdentifierException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.ShellException;
import org.megam.chef.identity.IIDentity;
import org.megam.chef.identity.IdentityParser;
import org.megam.chef.identity.S3;
import org.megam.chef.parser.JSONRequest;
import org.megam.chef.parser.JSONRequestParser;
import org.megam.chef.shell.MultiCommands;
import org.megam.chef.shell.MultiCommands;
import org.megam.chef.shell.ShellProvisioningPool;
import org.megam.chef.shell.Shellable;
import org.megam.chef.shell.Stoppable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ram
 * @param <T>
 * 
 */

public class DefaultProvisioningServiceWithShell<T> extends
		DefaultProvisioningService<T> implements Shellable, Stoppable {
	private Logger logger = LoggerFactory
			.getLogger(DefaultProvisioningServiceWithShell.class);
	private String cc = "";
	private String email = "";
	private String bucket = "";

	/**
	 * 
	 * @throws ProvisionerException
	 */
	public DefaultProvisioningServiceWithShell() throws ProvisionerException {
		super();
	}

	/**
	 * TO-DO : What is the output we need to send ? We need a generic way to
	 * convert a Java output to JSON output
	 * 
	 * @throws IdentifierException
	 * @throws IOException
	 * 
	 * @see org.megam.chef.ProvisioningService#provision()
	 */
	@Override
	public T provision(String jsonString) throws ProvisionerException,
			IOException, IdentifierException {
		logger.debug("Provisioning...\n*****************\n" + jsonString + "\n");
		try {
			execute(jsonToCommand(jsonString));
		} catch (ShellException she) {
			throw new ProvisionerException(she);
		}
		/**
		 * TO-DO why do we return null here ?
		 */
		return null;
	}

	/**
	 * @param args
	 * @throws ShellException
	 * @throws IdentifierException
	 * @throws IOException
	 * @throws ProvisionerException
	 */
	public MultiCommands jsonToCommand(String jsonRequest)
			throws ShellException, IOException, IdentifierException,
			ProvisionerException {
		MultiCommands com = new org.megam.chef.shell.MultiCommands(
				convertInput(jsonRequest));
		return com;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.shell.Stoppable#stop()
	 */
	@Override
	public void halt() {
		throw new RuntimeException("Halt not implemented yet.");
	}

	/**
	 * Using GSON library, an input JSON string is parsed to a
	 * GlobalConfiguration Java Object. If the JSON parameters passed are
	 * validated for 1..... 2. ..... 3. .... If they are valid then the shell
	 * builder builds a script. If not an error with the reasons of validation
	 * failure is retured. command
	 * 
	 * @param myJSONString
	 * 
	 * @return
	 * @throws IOException
	 * @throws IdentifierException
	 * @throws ProvisionerException
	 */
	private String[] convertInput(String jsonRequest) throws ShellException,
			IOException, IdentifierException, ProvisionerException {
		JSONRequestParser jrp = new JSONRequestParser(jsonRequest);
		JSONRequest jr = jrp.data();
		/**
		 * Download the stuff from S3 The location to download can be got from
		 * parsing vault_location (in access) S3.download() If all is well
		 * proceed Wrap this method and trap for ProvisionerException
		 */
		ParmsValidator pv = new ParmsValidator();
		if (pv.validate(jr.conditionList())) {
			String vaultLocation = vaultLocationParserwithoutBucket(jr
					.getAccess().getVaultLocation());			
			S3.download(vaultLocation);
			if (jr.getAccess().getSshPubLocation().length() > 0) {
				String sshpubLocation = vaultLocationParserwithoutBucket(jr
						.getAccess().getSshPubLocation());				
				S3.downloadFile(sshpubLocation + ".key");
				S3.downloadFile(sshpubLocation + ".pub");
				File sshpubfile = new File(Constants.MEGAM_VAULT + sshpubLocation);
			}			
			String b_vaultLocation = vaultLocationParserwithBucket(jr
					.getAccess().getVaultLocation());
			List<IIDentity> fp = new IdentityParser(b_vaultLocation).identity();
			return ShellBuilder.buildString(jr.scriptFeeder(), jrp, fp);
		} else {
			throw new ShellException(new IllegalArgumentException(pv
					.reasonsNotSatisfied().toString()));
		}
	}

	public String vaultLocationParserwithoutBucket(String str) {
		if (str.length() > 0) {
			int lst = str.lastIndexOf("/");
			String cc = str.substring(lst);
			str = str.replace(str.substring(lst), "");
			String email = str.substring(str.lastIndexOf("/") + 1);
			return email + cc;
		} else {
			return str;
		}
	}

	public String vaultLocationParserwithBucket(String str) {
		if (str.length() > 0) {
			int lst = str.lastIndexOf("/");
			cc = str.substring(lst);
			str = str.replace(str.substring(lst), "");
			email = str.substring(str.lastIndexOf("/"));
			str = str.replace(str.substring(str.lastIndexOf("/")), "");
			bucket = str.substring(str.lastIndexOf("/") + 1);
			return bucket + email + cc;
		} else {
			return str;
		}
	}

	public String toString() {
		return "DefaultProvisioningWithShell";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.shell.Shellable#execute(org.megam.chef.shell.Command)
	 */
	@Override
	public void execute(MultiCommands command) throws ShellException {
		(new ShellProvisioningPool()).run(command);
	}

}
