package org.megam.chef.parser;

import java.util.Formatter;

import com.google.gson.Gson;

/**
 * 
 * @author rajthilak
 * 
 */
public class JSONRequestParser {

	private JSONRequest reqData;

	/**
	 * 
	 * @param jsonString
	 */
	public JSONRequestParser(String jsonString) {
		parse(jsonString);

	}

	/**
	 * 
	 * @param jsonString
	 *            parse the json string passed.
	 */
	private void parse(String jsonString) {
		Gson gson = new Gson();
		reqData = gson.fromJson(jsonString, JSONRequest.class);
	}

	public JSONRequest data() {
		return reqData;
	}

	public String toString() {
		StringBuilder strbd = new StringBuilder();
		final Formatter formatter = new Formatter(strbd);
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.format("%12s = %s%n", "reqdata", data());
		formatter.format("%s%n",
				"*----------------------------------------------*");
		formatter.close();
		return strbd.toString();

	}

}
