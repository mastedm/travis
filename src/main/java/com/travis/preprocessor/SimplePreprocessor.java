package com.travis.preprocessor;

import java.text.ParseException;
import java.util.ArrayList;

import com.travis.algorithms.Common;

/**
 * Simple Preprocessor. Supposed to use a simple log format: 
 * 		- every event on separate line
 * 		- in the beginning of the line something that implements the 
 * 		  index function (line number or time of event) and separated by space
 * 
 * @author Sergey Alexeev
 *
 */
public class SimplePreprocessor extends AbstractPreprocessor {
	
	public SimplePreprocessor(String fileName) {
		super(fileName);
	}
	
	private enum State {
		index, event, br
	}
	
	@Override
	protected void parseData() throws ParseException {
		indexes = new ArrayList<String>();
		
		String index = "";
		
		State state = State.index;
		
		int j = 0;
		
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			
			switch (c) {
			case ' ':
				if (state == State.index) {
					indexes.add(index);
					index = "";

					state = State.event;
				} else {
					data.setCharAt(j++, c);
				}
				break;

			case '\r':
				if (state == State.event) {
					state = State.br;
				} else {
					throw new ParseException("Unexpected carriage return char", i);
				}
				break;
				
			case '\n':
				if (state == State.br || state == State.event) {
					data.setCharAt(j++, Common.BR_CHAR);
					
					state = State.index;
				} else {
					throw new ParseException("Unexpected line feed char", i);
				}
								
				break;
				
			default:
				if (state == State.index) {
					index += c;
				} else if (state == State.event) {
					data.setCharAt(j++, c);
				} else if (state == State.br) {
					throw new ParseException("Error with break at " + i + " pos.", i);
				}
				
				break;
			}
			
		}
		
		data.setLength(j);		
	}
	
}