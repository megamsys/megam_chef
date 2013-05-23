package org.megam.chef.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rajthilak
 * 
 */
public class ParmsValidator {
	
	private List<String> reasonsNotSatisfied = new ArrayList<String>();
	
	ParmsValidator() {
		
	}

	/**
	 * 
	 * @param conditionList
	 * @return 
	 * Verifies if the conditions contain an input, if they are then, the the conditions predicate needs to succeeed.
	 *  
	 */
	public  boolean validate(List<Condition> conditionList) {
		Boolean isValid = true;
		for (Condition conditions : conditionList) {
			isValid = conditions.inputAvailable();
			if (!isValid) {		
					reasonsNotSatisfied.addAll(conditions.getReason());	
			} else if (!(isValid = conditions.ok())) {
				reasonsNotSatisfied.addAll(conditions.getReason());
			}
		}		
		return isValid;
	}
	
	protected List<String> reasonsNotSatisfied() {
		return reasonsNotSatisfied;
	}
}
