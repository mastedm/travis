package com.travis.algorithms;

public class BurrowsWheelerTransform {
	
	public static int[] bwt(int[] x, int[] sa, int n) {
		int[] bwt = new int[n];
		
		for (int i = 0; i < n; i++) {
			if (sa[i] > 0) {
				bwt[i] = x[sa[i] - 1];
			} else {
				bwt[i] = Common.TERMINATION_CHAR;
			}
		}
		
		return bwt;
	}
	
}
