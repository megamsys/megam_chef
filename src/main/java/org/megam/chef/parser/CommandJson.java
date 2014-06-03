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
package org.megam.chef.parser;


import java.util.Formatter;

import org.megam.chef.source.riak.RiakSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>CommandJson class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class CommandJson {
	
	private String id;
	private String node_id;
	private String node_name;
	private String req_type;	
	private JSONRequest command;	
	private String created_at;
	private Logger logger = LoggerFactory.getLogger(CommandJson.class);
	/**
	 * <p>getReqCommand.</p>
	 *
	 * @return Requested Command object
	 */
	public JSONRequest getReqCommand() {		
		return command;
	}
		
	/**
	 * <p>Getter for the field <code>id</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * <p>getnodeId.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getnodeId() {		
		return node_id;
	}
	
	/**
	 * <p>getNodeName.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getNodeName() {
		return node_name;
	}

	/**
	 * <p>getReqType.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getReqType() {
		return req_type;
	}	
	
	
	/**
	 * <p>getCreatedAt.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCreatedAt() {
		return created_at;
	}
	
	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", (getReqCommand()!=null ? getReqCommand().toString() : "RequestedCommand[None]"));		
		formatter.close();
		return strbd.toString();
	}

}
