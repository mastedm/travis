package com.travis.preprocessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Abstract class for Preprocessor.
 * 
 * @author Sergey Alexeev
 *
 */
public abstract class AbstractPreprocessor {
	
	protected StringBuilder data;
	
	/**
	 * Indexes of events (line number, time of event) 
	 */
	protected List<String> indexes;
	
	protected String fileName;
	
	public AbstractPreprocessor(String fileName) {
		this.fileName = fileName;
		load();
		
		try {
			parseData();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getData() {
		return data.toString();
	}
	
	/**
	 * Parse data. Each preprocessor implements own algorithm 
	 * @throws ParseException 
	 */
	protected abstract void parseData() throws ParseException;
	
	/**
	 * Load data from file.
	 */
	private void load() {
		data = new StringBuilder();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			
			char[] buf = new char[1024];
			int len;
			while ((len = reader.read(buf)) != -1) {
				data.append(buf, 0, len);
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			// XXX Need to write handler
			e.printStackTrace();
		} catch (IOException e) {
			// XXX Need to write handler
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}		
	}
	
}