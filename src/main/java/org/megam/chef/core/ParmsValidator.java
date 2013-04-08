package org.megam.chef.core;


import java.util.ArrayList;
import java.util.List;

public class ParmsValidator {
	    public static boolean validate(List<Condition> conditionList)  {
	    	Boolean returnokvalue = true;
	    	List<Boolean> list = new ArrayList<Boolean>();    	  
	    	for(Condition cl:conditionList) {
	    		System.out.println(cl.name());
	    		if(cl.inputAvailable()) {	    			
	    			if(cl.ok()) {	    				
	    				list.add(true);
	    				System.out.println("Valid ");
	    			}
	    			else {
	    				list.add(false);	    				
	    				for(String reason:cl.getReason())
	    				System.out.println(reason);
	    			}	 
	    		}
	    		else {
	    			list.add(false);	    			
	    			for(String reason:cl.getReason())
	    				System.out.println(reason);
	    		}
	    	}
	    	for(Boolean check:list) {
				if(check!=true) {
					returnokvalue=false;
				}
			}
    	    	return returnokvalue;
	    	
	/**
	 * @param args
	 */
	
	    }
}
