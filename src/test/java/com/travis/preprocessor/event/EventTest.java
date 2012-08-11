package com.travis.preprocessor.event;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void test() {
		Event event = new Event();
		
		event.setRegexp("^test \\d{2}$");
		
		assertFalse(event.match(""));
		assertFalse(event.match("123"));
		assertFalse(event.match("test"));
		
		assertTrue(event.match("test 12"));
	}

}
