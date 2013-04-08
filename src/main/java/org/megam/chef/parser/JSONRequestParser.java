package org.megam.chef.parser;

import java.util.Formatter;

import com.google.gson.Gson;

public class JSONRequestParser {

	private JSONRequest reqData;

	public JSONRequestParser(String jsonString) {
		parse(jsonString);
		
	}
	
	private void parse(String jsonString) {
		// parse the json string passed.
		Gson gson=new Gson();
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



 
