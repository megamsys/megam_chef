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
 * @author rajthilak
 *
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
	 * 
	 * @return Requested Command object
	 */
	public JSONRequest getReqCommand() {		
		return command;
	}
		
	public String getId() {
		return id;
	}
	
	public String getnodeId() {		
		return node_id;
	}
	
	public String getNodeName() {
		return node_name;
	}

	public String getReqType() {
		return req_type;
	}	
	
	
	public String getCreatedAt() {
		return created_at;
	}
	
	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n", (getReqCommand()!=null ? getReqCommand().toString() : "RequestedCommand[None]"));		
		formatter.close();
		return strbd.toString();
	}

}