package com.travis.algorithms;

import static org.junit.Assert.*;

import org.junit.Test;

import com.travis.utils.Utils;

public class SuffixArrayTest {

	@Test
	public void testSuffixArrayNaive() {
		{
			String str = "abaabaab";
		
			int[] sa = SuffixArray.suffixArrayNaive(str);
			int[] expected = {5, 2, 6, 3, 0, 7, 4, 1};
		
			assertArrayEquals(expected, sa);
		}
		
		{
			String str = "abracadabra";
			
			int[] sa = SuffixArray.suffixArrayNaive(str);
			int[] expected = {10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2};
			
			assertArrayEquals(expected, sa);
		}
	}

	@Test
	public void testRadixPass() {
		int[] str = {3, 3, 2, 1, 5, 5, 4};
		
		int[] a = {0, 1, 2, 3, 4, 5, 6};
		int[] b = new int[a.length];
		
		int n = a.length;
		
		SuffixArray.radixPass(a, b, str, n, 10);
		
		int[] expected = {3, 2, 0, 1, 6, 4, 5};
		
		assertArrayEquals(expected, b);
	}

	@Test
	public void testSuffixArrayLinear() {
		{
			int[] s = {3, 3, 2, 1, 5, 5, 4, 0, 0, 0};
			int n = s.length - 3; // exclude last 3 zeros
			int K = 10;
			int[] SA = new int[n + 3];
			
			int[] expected = {3, 2, 1, 0, 6, 5, 4, 0, 0, 0};
			
			SuffixArray.suffixArrayLinear(s, SA, n, K);
			
			assertArrayEquals(expected, SA);
		}
		
		{		
			String str = "mississippi";
			
			int[] strIntArray = Utils.stringToIntArray(str, 3); 
			
			int[] SA = new int[str.length() + 3];
			
			SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 512);
			
			int[] expected = {10, 7, 4, 1, 0, 9, 8, 6, 3, 5, 2, 0, 0, 0};
			
			assertArrayEquals(expected, SA);
		}

		{
			// Random string
			String str = Utils.getFibonacciString(20);
			
			System.out.println(str.length() + ", " + str);
			
			int[] strIntArray = Utils.stringToIntArray(str, 3);
			
			long a = System.currentTimeMillis();
			
			int[] naiveSA = SuffixArray.suffixArrayNaive(str);
			
			long b = System.currentTimeMillis();
			
			int[] SA = new int[str.length()];
			SuffixArray.suffixArrayLinear(strIntArray, SA, str.length(), 256);
			
			long c = System.currentTimeMillis();
			
			assertArrayEquals(naiveSA, SA);
			
			System.out.println("Naive: " + (b - a) + ", Linear: " + (c - b));
		}
	}

}
