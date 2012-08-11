package com.travis.production;

import com.travis.algorithms.SuffixArray;
import com.travis.utils.Utils;

class SuffixArrayProductionTest {
	
	public static void main(String[] args) {
		
		for (int n = 0; n < 30; n += 1) {
			String str = Utils.getFibonacciString(n);
			
			long a = System.currentTimeMillis();
			
			SuffixArray.suffixArrayNaive(str);
			
			long b = System.currentTimeMillis();
			
			int[] SA = new int[str.length()];
			int[] strIntArray = Utils.stringToIntArray(str, 3);
			SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 256);
			
			long c = System.currentTimeMillis();
			
			System.out.println(n + "\t" + str.length() + "\t" + (b - a) + "\t" + (c - b));
		}
		
	}
	
}