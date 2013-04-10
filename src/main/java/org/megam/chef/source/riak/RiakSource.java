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
package org.megam.chef.source.riak;

import org.megam.chef.AppYaml;
import org.megam.chef.exception.SourceException;
import org.megam.chef.source.Source;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.cap.UnresolvedConflictException;

/**
 * @author rajthilak
 * 
 */
public class RiakSource implements Source {
	private AppYaml app;
	private IRiakClient riakClient;
	private Bucket myBucket;

	/*
	 * public static void init(AppYaml app) throws RiakException {
	 * 
	 * IRiakClient riakClient =
	 * RiakFactory.httpClient("http://"+app.getHost()+":"
	 * +app.getPort()+"/"+app.getSource()); Bucket myBucket = (Bucket)
	 * riakClient.fetchBucket("newBucket").execute(); IRiakObject myObject =
	 * ((com.basho.riak.client.bucket.Bucket)
	 * myBucket).fetch("sample").execute();
	 * 
	 * // note that getValueAsString() will return null here if there's no value
	 * in Riak System.out.println(myObject.getValueAsString());
	 * 
	 * riakClient.shutdown();
	 * 
	 * }
	 */
	public RiakSource(AppYaml app) {
		this.app = app;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.riak.Source#connection()
	 */
	public void connection() throws SourceException {
		// TODO Auto-generated method stub
		try {
			// create Riak Factory Connection Established
			riakClient = RiakFactory.httpClient("http://" + app.getHost() + ":"
					+ app.getPort() + "/" + app.getSource());
		} catch (RiakException re) {
			throw new SourceException(re);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.megam.chef.riak.Source#mutate()
	 */
	public void mutate() {
		// TODO Auto-generated method stub

	}

	/**
	 * note that getValueAsString() will return null here if there's no value in
	 * Riak
	 */
	public String fetch(String str) throws SourceException {
		// TODO Auto-generated method stub
		try {

			IRiakObject myObject = ((com.basho.riak.client.bucket.Bucket) myBucket)
					.fetch(str).execute();
			return myObject.getValueAsString();
		} catch (UnresolvedConflictException uce) {
			throw new SourceException(uce);
		} catch (RiakRetryFailedException rre) {
			throw new SourceException(rre);
		}
	}

	public void bucket(String str) throws SourceException {
		// TODO Auto-generated method stub
		try {
			myBucket = (Bucket) riakClient.fetchBucket(str).execute();
		} catch (RiakRetryFailedException rrfe) {
			throw new SourceException(rrfe);
		}
	}
}
