package com.travis.algorithms;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.travis.utils.Utils;

public class BurrowsWheelerTransformTest {

	@Test
	public void test() {
		String str = "abaababa";

		int[] strIntArray = Utils.stringToIntArray(str, 3); 
		int[] SA = new int[str.length() + 3];
		
		SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 512);
		int[] lcp = LongestCommonPrefix.getHeight(strIntArray, SA, str.length());
		int[] bwt = BurrowsWheelerTransform.bwt(strIntArray, SA, str.length());
		
		int[] expectedSA = {7, 2, 5, 0, 3, 6, 1, 4, 0, 0, 0};
		int[] expectedLCP = {0, 1, 1, 3, 3, 0, 2, 2};
		
		assertArrayEquals(expectedSA, SA);
		assertArrayEquals(expectedLCP, lcp);
		assertEquals("bbb$aaaa", Utils.intArrayToString(bwt, 0));
	}

}
