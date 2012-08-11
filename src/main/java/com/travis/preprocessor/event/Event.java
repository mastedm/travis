package com.travis.preprocessor.event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends AbstractEvent {
	
	public Event() {
		;
	}
	
	@Override
	public void setRegexp(String regexp) {
		this.regexp = regexp;
		pattern = Pattern.compile(regexp);
	}
	
	@Override
	public boolean match(String str) {
		Matcher matcher = pattern.matcher(str);
		return matcher.find();		
	}
	
}
