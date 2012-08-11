package com.travis.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Sergey Alexeev
 */
public class UtilsTest {
	
	@Test
	public void testStringToIntArrayConvert() {
		String str = Utils.randomString();
		
		int[] a = Utils.stringToIntArray(str, 3);
		String result = Utils.intArrayToString(a, 3);
		
		assertEquals(str, result);		
	}
	
	@Test
	public void testFibonacciString() {
		assertEquals("b", Utils.getFibonacciString(0));
		assertEquals("a", Utils.getFibonacciString(1));
		assertEquals("ab", Utils.getFibonacciString(2));
		assertEquals("aba", Utils.getFibonacciString(3));
		assertEquals("abaab", Utils.getFibonacciString(4));
		assertEquals("abaababa", Utils.getFibonacciString(5));
		assertEquals("abaababaabaab", Utils.getFibonacciString(6));
	}
	
}
