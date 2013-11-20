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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.megam.chef.Constants;
import org.megam.chef.exception.IdentifierException;

public class IdentityParser implements Identifier {

	List<IIDentity> list = new ArrayList<IIDentity>();
	private String[] keyvaluepair;
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private String FILENAME = Constants.MEGAM_CREDENTIAL_PATH;
    private String vaultLocation;
    
	public IdentityParser(String vl) {
		this.vaultLocation = vl;
	}
	
	@SuppressWarnings("resource")
	public List<IIDentity> identity() throws IdentifierException, IOException {		
		Path path = Paths.get(FILENAME + vaultLocation + "/" + typeChecker());
		Scanner scanner = new Scanner(path, ENCODING.name());
		// read CSV Files and parse it to object array
		scanner.useDelimiter(System.getProperty("line.separator"));
		while (scanner.hasNext()) {
			// parse line to get Emp Object
			keyvaluepair = parseCSVLine(scanner.next());
			list.add(new IIDentity(keyvaluepair[0], keyvaluepair[1]));
		}
		scanner.close();
		return list;
	}

	public String typeChecker() throws IOException {
		Path path = Paths.get(FILENAME + vaultLocation + "/type");		
		Scanner scanner = new Scanner(path, ENCODING.name());
		// read file line by line
		scanner.useDelimiter(System.getProperty("line.separator"));
		while (scanner.hasNext()) {
			keyvaluepair = parseCSVLine(scanner.next());
		}
		scanner.close();		
		return keyvaluepair[1];
	}

	private static String[] parseCSVLine(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("\\s*=\\s*");
		String key = scanner.next();
		String value = scanner.next();		
		return new String[] { key, value };
	}

	/*
	 * private static IIDentity parseCSVLine(String line) { Scanner scanner =
	 * new Scanner(line); scanner.useDelimiter("\\s*=\\s*"); String key =
	 * scanner.next(); String value = scanner.next();
	 * 
	 * return new IIDentity(key, value); }
	 */
}