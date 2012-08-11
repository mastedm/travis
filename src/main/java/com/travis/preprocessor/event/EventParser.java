package com.travis.preprocessor.event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.jruby.RubyRegexp;

@SuppressWarnings("restriction")
public class EventParser {
	
	public static Event parse(String fileName, String eventName) 
			throws FileNotFoundException, ScriptException 
	{
		File   file   = new File(fileName);
		Reader reader = new FileReader(file);
		
		ScriptEngine engine = buildScriptEngine();
		
		engine.eval("$LOAD_PATH.unshift('" + file.getParentFile().getAbsolutePath() + "')");
		engine.eval(reader);
		
		Event event = new Event();
		event.setName(eventName);
		event.setRegexp(getRegexp(engine, eventName));
			
		IOUtils.closeQuietly(reader);
		
		return event;
	}
	
	private static ScriptEngine buildScriptEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
    	return manager.getEngineByName("jruby");
	}
	
	private static String getRegexp(ScriptEngine engine, String eventName) 
			throws ScriptException 
	{
		RubyRegexp rubyRegexp = (RubyRegexp)engine.eval(eventName + ".get_regexp");
		return rubyRegexp.source().toString();
	}
	
}
