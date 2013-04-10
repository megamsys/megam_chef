package org.megam.chef.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rajthilak
 * 
 */
public class ParmsValidator {

	/**
	 * 
	 * @param conditionList
	 * @return 
	 * if any validate checking is failed then could not create the command
	 * 
	 */
	public static boolean validate(List<Condition> conditionList) {
		Boolean returnokvalue = true;
		List<Boolean> list = new ArrayList<Boolean>();
		for (Condition cl : conditionList) {
			if (cl.inputAvailable()) {
				if (cl.ok()) {
					list.add(true);
				} else {
					list.add(false);
					for (String reason : cl.getReason())
						System.out.println(reason);
				}
			} else {
				list.add(false);
				for (String reason : cl.getReason())
					System.out.println(reason);
			}
		}
		for (Boolean check : list) {
			if (check != true) {
				returnokvalue = false;
			}
		}
		return returnokvalue;

	}
}
