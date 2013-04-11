/* 
 ** Copyright [2012] [Megam Systems]
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
package org.megam.chef.source;

import java.io.IOException;

import org.megam.chef.AppYaml;
import org.megam.chef.AppYamlLoader;
import org.megam.chef.BootStrapChef;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.riak.DropIn;
import org.megam.chef.source.riak.RiakSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajthilak
 * 
 */

public class SourceLoader {

	private Logger logger = LoggerFactory.getLogger(SourceLoader.class);
	private static final String RIAK = "riak";
	private static final String NONE = "none";
	private static String SOURCE;
	private Source source;
	private static String jsonString;
	private AppYaml yaml;
	AppYamlLoader app;

	/**
	 * 
	 * @param tempYaml
	 */
	public SourceLoader(AppYaml tempYaml) {
		this.yaml = tempYaml;
	}

	/**
	 * 
	 * @throws SourceException
	 */
	public void load() throws SourceException {

		SOURCE = yaml.getSource();
		switch (SOURCE) {
		case RIAK:
			source = new RiakSource(yaml);
			source.connection();
			source.bucket("rajBucket");
			logger.info("Source was loaded");
			break;
		case NONE:
			break;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SourceException
	 */
	public String fetchRequestJSON(String id) throws SourceException {
		SOURCE = yaml.getSource();
		switch (SOURCE) {
		case RIAK:
			jsonString = source().fetch(id);
			break;
		case NONE:
			jsonString = id;
			break;
		}
		return jsonString;
	}

	private Source source() {
		return source;
	}

}
