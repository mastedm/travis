package com.travis.preprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.travis.algorithms.Common;

import static org.junit.Assert.*;

public class SimplePreprocessorTest {
	
	private String createTestLog(String data) {
		File temp = null;
		FileWriter writer = null;
		
		try {
			temp = File.createTempFile("SimplePreprocessorTest", ".log");
			temp.deleteOnExit();
			
			writer = new FileWriter(temp);
			writer.write(data);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
		}	
		
		return temp != null ? temp.getAbsolutePath() : null;
	}
	
	@Test
	public void testInitialize() {
		String str = "1 one one \r\n2 two two \r\n3 three three";
		String fileName = createTestLog(str);
		
		SimplePreprocessor preProcessor = new SimplePreprocessor(fileName);
		
		assertEquals("one one " + Common.BR_CHAR + "two two " + Common.BR_CHAR + "three three", preProcessor.data.toString());
		assertEquals(3, preProcessor.indexes.size());
		assertEquals("1", preProcessor.indexes.get(0));
		assertEquals("2", preProcessor.indexes.get(1));
		assertEquals("3", preProcessor.indexes.get(2));
	}
	
}