package com.travis;

import java.net.URL;

import com.travis.preprocessor.AbstractPreprocessor;
import com.travis.preprocessor.EventPreprocessor;


public class App {
	
    public static void main( String[] args ) {
    	URL url = App.class.getResource("simple_trace.txt");

    	AbstractPreprocessor preProcessor = new EventPreprocessor(url.getFile());    	
    	
    	System.out.println(preProcessor.getData());
    	
    	Travis travis = new Travis(preProcessor);
    	travis.process();
    	
    	System.out.println(travis.getTmpResult());   
    	
    }

    
}
