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
package org.megam.chef.identity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.megam.chef.Constants;
import org.megam.chef.exception.IdentifierException;
import org.megam.chef.shell.SingleShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>IdentityParser class.</p>
 *
 * @author ram
 * @version $Id: $Id
 */
public class IdentityParser implements Identifier {

	private Logger logger = LoggerFactory.getLogger(SingleShell.class);

	private List<IIDentity> list = new ArrayList<IIDentity>();
	private String[] keyvaluepair;
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	String res;
	private String vaultLocation;

	/**
	 * <p>Constructor for IdentityParser.</p>
	 *
	 * @param vl a {@link java.lang.String} object.
	 */
	public IdentityParser(String vl) {
		this.vaultLocation = vl;
	}

	/**
	 * <p>identity.</p>
	 *
	 * @return a {@link java.util.List} object.
	 * @throws org.megam.chef.exception.IdentifierException if any.
	 * @throws java.io.IOException if any.
	 */
	@SuppressWarnings("resource")
	public List<IIDentity> identity() throws IdentifierException, IOException {
		res = "";
		Path path = Paths.get(Constants.MEGAM_VAULT + vaultLocation + "/"+ typeChecker());		
		if (new File(Constants.MEGAM_VAULT + vaultLocation + "/"+ typeChecker()).exists()) {
			Scanner scanner = new Scanner(path, ENCODING.name());
			// read CSV Files and parse it to object array
			scanner.useDelimiter(System.getProperty("line.separator"));
			while (scanner.hasNext()) {
				// parse line to get Emp Object
				keyvaluepair = parseCSVLine(scanner.next());				
				list.add(new IIDentity(keyvaluepair[0], keyvaluepair[1]));
			}
			scanner.close();
		} else {			
			list = Collections.emptyList();
		}
		return list;

	}

	/**
	 * <p>typeChecker.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 * @throws java.io.IOException if any.
	 */
	public String typeChecker() throws IOException {
		Path path = Paths.get(Constants.MEGAM_VAULT + vaultLocation + "/type");
		res = "";
		if (new File(Constants.MEGAM_VAULT + vaultLocation + "/type").exists()) {
			Scanner scanner = new Scanner(path, ENCODING.name());
			// read file line by line
			scanner.useDelimiter(System.getProperty("line.separator"));
			while (scanner.hasNext()) {
				keyvaluepair = parseCSVLine(scanner.next());
			}
			scanner.close();
			res = keyvaluepair[1];
		} else {
			res = "";
		}
		return res;
	}

	private static String[] parseCSVLine(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("\\s*=\\s*");
		String key = scanner.next();
		String value = scanner.next();
		return new String[] { key, value };
	}

}
