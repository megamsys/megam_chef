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

import org.megam.chef.exception.SourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ram
 *
 */
public class NoneSource implements Source {
	
	private Logger logger = LoggerFactory.getLogger(NoneSource.class);


	/* (non-Javadoc)
	 * @see org.megam.chef.source.Source#connection()
	 */
	@Override
	public void connection() throws SourceException {
		logger.warn("connected to none source.");
	}

	/* (non-Javadoc)
	 * @see org.megam.chef.source.Source#fetch(java.lang.String)
	 */
	@Override
	public String fetch(String str) throws SourceException {
		return str;
	}

	/* (non-Javadoc)
	 * @see org.megam.chef.source.Source#bucket(java.lang.String)
	 */
	@Override
	public void bucket(String str) throws SourceException {
		logger.warn("connected to none bucket.");

	}

	/* (non-Javadoc)
	 * @see org.megam.chef.source.Source#mutate()
	 */
	@Override
	public void mutate() {
		throw new RuntimeException("You can't mutate a none source's bucket")
	}

}
