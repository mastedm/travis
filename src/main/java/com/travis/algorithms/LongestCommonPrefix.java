package com.travis.algorithms;


public class LongestCommonPrefix {

	/**
	 * A linear-time algorithm to compute the longest common prefix information
	 * in suffix arrays from article: "Linear-Time Longest-Common-Prefix 
	 * Computation in Suffix Arrays and Its Applications" Toru Kasai et al.
	 * 
	 * The method signature and variable names are stored under the specified 
	 * work without changes.
	 */
	public static int[] getHeight(int[] A, int[] Pos, int n) {
		int[] Rank = new int[n];
		int[] Height = new int[n];
		
		for (int i = 0; i < n; i++) {
			Rank[Pos[i]] = i;
		}
		
		int h = 0;
		for (int i = 0; i < n; i++) {
			if (Rank[i] > 0) {
				int j = Pos[Rank[i] - 1];
				while (A[i + h] == A[j + h]) {
					h += 1;
				}
				Height[Rank[i]] = h;
				if (h > 0) {
					h -= 1;
				}
			}
		}
		
		return Height;
	}
	
}
