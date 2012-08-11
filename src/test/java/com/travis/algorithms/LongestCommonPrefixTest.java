package com.travis.algorithms;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.travis.utils.Utils;

public class LongestCommonPrefixTest {

	@Test
	public void test() {
		String str = "mississippi";
		
		int[] strIntArray = Utils.stringToIntArray(str, 3); 
		
		int[] SA = new int[str.length() + 3];
		
		SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 512);
		
		int[] lcp = LongestCommonPrefix.getHeight(strIntArray, SA, str.length());
		
		
		int[] expected = {0, 1, 1, 4, 0, 0, 1, 0, 2, 1, 3};
		assertArrayEquals(expected, lcp);
	}

}
