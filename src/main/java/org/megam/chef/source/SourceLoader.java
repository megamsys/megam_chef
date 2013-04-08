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
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.riak.DropIn;
import org.megam.chef.source.riak.RiakSource;

/**
 * @author rajthilak
 * 
 */

public class SourceLoader {

	private static final String RIAK = "riak";	
	
	private AppYaml yaml;
	
	SourceLoader(AppYaml tempYaml) {
		this.yaml = tempYaml;
	}

	public  Source load() throws SourceException {		
		source = null;
		/**
		 * Use switch/case
		 * Also write a NoneSource
		 */
		if (SOURCE.equals(RIAK)) {
				 = new RiakSource(app.currentYaml());
				rs.connection();
				rs.bucket("rajBucket");
				jsonString = rs.fetch("sample");
			}
			return rs;
		} catch (IOException ioe) {
			throw new SourceException(ioe);
		}
	}

    public String fetchRequestJSON(DropIn in) {
    source().fetch(in.id());	
    }
    
    Source source() {
    	source;
    }
    	


}
