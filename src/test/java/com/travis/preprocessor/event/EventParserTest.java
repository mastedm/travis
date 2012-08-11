package com.travis.preprocessor.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.script.ScriptException;

import org.junit.Test;

@SuppressWarnings("restriction")
public class EventParserTest {

	@Test
	public void test() {
		URL url = EventParserTest.class.getResource("test_event.rb");
		String eventName = "TestEvent";
				
		Event event = null;
		try {
			event = EventParser.parse(url.getFile(), eventName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(event);
		assertEquals(eventName, event.getName());
		assertTrue(event.match("test 123"));
		assertFalse(event.match("test aaa"));
	}

}
