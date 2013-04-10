package org.megam.chef.core;

import java.util.List;

public interface Condition {
	// Interface for validation
	public List<String> getReason();

	public boolean ok();

	public boolean inputAvailable();

	public String name();

}
