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
package org.megam.chef.source;

import org.megam.chef.AppYaml;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.riak.RiakSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rajthilak
 * 
 */

public class SourceLoader {

	private Logger logger = LoggerFactory.getLogger(SourceLoader.class);
	/**
	 * Static variables which represent if its riak or none source
	 */
	private static final String RIAK = "riak";
	private static final String NONE = "none";
	/**
	 * The loaded source object
	 */
	private Source source;
	/**
	 * The passed in yaml, converted to java, fetched from .megam/chefapp.yaml
	 */
	private AppYaml yaml;

	/**
	 * 
	 * @param tempYaml
	 */
	public SourceLoader(AppYaml tempYaml) {
		this.yaml = tempYaml;
	}

	/**
	 * Loads the appropriate source object. The valid source are RiakSource,
	 * NoneSource
	 * 
	 * @throws SourceException
	 */
	public void load() throws SourceException {
		switch (yaml.getSource()) {
		case RIAK:
			source = new RiakSource(yaml);
			break;
		case NONE:
			source = new NoneSource();
			break;
		}
		source.connection();
		source.bucket(yaml.getBucket());
		logger.info("Source was loaded");
	}

	/**
	 * Returns the json as fetched by using the id passed in from the source.
	 * The source can be RiakSource, or NoneSource. RiakSource => In case of
	 * riak source, a fetch on the bucket using an id is performed. This means
	 * an id containing a JSON value should exist for that host/post/bucket in
	 * riak. NoneSource => In this case it is assumed that the passed in input
	 * (as id) contains the JSON string to process.
	 * 
	 * @param id
	 * @return
	 * @throws SourceException
	 */
	public String fetchRequestJSON(String id) throws SourceException {
		return source().fetch(id);
	}

	private Source source() {
		return source;
	}

}
