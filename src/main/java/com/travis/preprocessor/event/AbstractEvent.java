package com.travis.preprocessor.event;

import java.util.regex.Pattern;

public abstract class AbstractEvent {
	
	// Event name. Should be coincide with name of the Ruby-class prototype
	private String name;
	
	// Regular expression in String format
	protected String regexp;
	
	// Pattern for specified regexp
	protected Pattern pattern;
	
	// Parent of this event
	protected AbstractEvent parent;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void setRegexp(String regexp);
	
	public abstract boolean match(String str);
}
