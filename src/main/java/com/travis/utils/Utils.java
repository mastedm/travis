package com.travis.utils;

import java.math.BigInteger;
import java.security.SecureRandom;


public class Utils {
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	/**
	 * Generate random string
	 * @return
	 */
	public static String randomString() {
		return new BigInteger(1024, RANDOM).toString(32);
	}
	
	public static String getFibonacciString(int n) {
		if (n == 0) {
			return "b";
		} else if (n == 1) {
			return "a";		
		} else {
			return getFibonacciString(n - 1) + getFibonacciString(n - 2);
		}
	}
	
	/**
	 * Convert string into int array
	 * @param str   - source string
	 * @param extra - number of extra zeros at the of result array
	 * @return int array representation of source string
	 */
	public static int[] stringToIntArray(String str, int extra) {
		char[] strCharArray = str.toCharArray();
		int[] result = new int[strCharArray.length + extra];
		
		for (int i = 0; i < strCharArray.length; i++) {
			result[i] = strCharArray[i];
		}
		
		return result;
	}
	
	/**
	 * Convert int array into string
	 * @param array - source int array
	 * @param extra - number of extra zeros at the end of source array
	 * @return string representation of source int array
	 */
	public static String intArrayToString(int[] array, int extra) {
		String result = "";
		
		for (int i = 0; i < array.length - extra; i++) {
			result += (char)array[i];
		}
		
		return result;
	}
	
	
}

