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

import org.megam.chef.exception.SourceException;

/**
 * <p>Source interface.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public interface Source {
	
	/**
	 * <p>connection.</p>
	 *
	 * @throws org.megam.chef.exception.SourceException if any.
	 */
	public void connection() throws SourceException;
	
	/**
	 * <p>fetch.</p>
	 *
	 * @param str a {@link java.lang.String} object.
	 * @throws org.megam.chef.exception.SourceException if any.
	 * @return a {@link java.lang.String} object.
	 */
	public String fetch(String str) throws SourceException ;
	
	/**
	 * <p>bucket.</p>
	 *
	 * @param str a {@link java.lang.String} object.
	 * @throws org.megam.chef.exception.SourceException if any.
	 */
	public void bucket(String str) throws SourceException;
	
	/**
	 * <p>mutate.</p>
	 */
	public void mutate();

}
