package org.megam.chef.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ParmsValidator class.</p>
 *
 * @author rajthilak
 * @version $Id: $Id
 */
public class ParmsValidator {
	
	private List<String> reasonsNotSatisfied = new ArrayList<String>();
	
	ParmsValidator() {
		
	}

	/**
	 * <p>validate.</p>
	 *
	 * @param conditionList a {@link java.util.List} object.
	 * @return a boolean.
	 */
	public  boolean validate(List<Condition> conditionList) {
		Boolean isValid = true;
		for (Condition conditions : conditionList) {
			System.out.println(conditions.name());
			System.out.println(conditions.inputAvailable());
			isValid = conditions.inputAvailable();
			if (!isValid) {		
					reasonsNotSatisfied.addAll(conditions.getReason());	
			} else if (!(isValid = conditions.ok())) {
				reasonsNotSatisfied.addAll(conditions.getReason());
			}
		}		
		return isValid;
	}
	
	/**
	 * <p>reasonsNotSatisfied.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	protected List<String> reasonsNotSatisfied() {
		return reasonsNotSatisfied;
	}
}
