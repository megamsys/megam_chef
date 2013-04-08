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
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.riak.DropIn;
import org.megam.chef.source.riak.RiakSource;

/**
 * @author rajthilak
 * 
 */

public class SourceLoader {

	private static final String RIAK = "riak";	
	private Source source;
	private AppYaml yaml;
	AppYamlLoader app;
	private String jsonString;
	SourceLoader(AppYaml tempYaml) {
		this.yaml = tempYaml;
	}

	public void load() throws SourceException  {		
		String SOURCE="riak";
		/**
		 * Use switch/case
		 * Also write a NoneSource
		 */
		if (SOURCE.equals(RIAK)) {
				source = new RiakSource(app.currentYaml());
				source.connection();
				source.bucket("rajBucket");
				//jsonString = source.fetch("sample");
		   }
		
	}

    public String fetchRequestJSON(DropIn in) throws SourceException {
    return source().fetch(in.getId());	
    }
    
    private Source source() {
    	return source;
    }
    	


}
