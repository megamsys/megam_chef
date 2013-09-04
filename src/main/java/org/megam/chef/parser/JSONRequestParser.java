package org.megam.chef.parser;

import java.util.Formatter;

import org.megam.chef.BootStrapChef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * 
 * @author rajthilak
 * 
 */
public class JSONRequestParser {

	private JSONRequest reqData;
	private CommandJson reqCommand;
	private Logger logger = LoggerFactory.getLogger(JSONRequestParser.class);

	/**
	 * 
	 * @param jsonString
	 */
	public JSONRequestParser(String jsonString) {
		logger.debug("-------> constructor =>" + jsonString);
		parse(jsonString);

	}

	/**
	 * 
	 * @param jsonString
	 *            parse the json string passed.
	 */
	private void parse(String jsonString) {
		try {
			logger.debug("-------> Before Gson parse =>" + jsonString);
			Gson gson = new Gson();
			reqCommand = gson.fromJson(jsonString, CommandJson.class);
			logger.debug("------>command" + reqCommand);
			reqData = reqCommand.getReqCommand();
			logger.debug("-------> After Gson parse =>" + reqData);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public JSONRequest data() {
		return reqData;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		logger.debug("-------> toString =>" );
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.format("%12s = %s%n", "reqdata", data());
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.close();
		return strbd.toString();

	}

}
