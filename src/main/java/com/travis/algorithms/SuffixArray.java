package com.travis.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class SuffixArray {
	
	private static class SuffixPair {
		
		public SuffixPair(String suffix, int pos) {
			this.suffix = suffix;
			this.pos    = pos;
		}
		
		String suffix;
		int    pos;
	}

	/**
	 * Naive approach of computing Suffix Array (SA).
	 * @param str String
	 * @return
	 */
	public static int[] suffixArrayNaive(String str) {
		List<SuffixPair> list = new ArrayList<SuffixPair>();
		
		for (int i = 0; i < str.length(); i++) {
			list.add(new SuffixPair(str.substring(i), i));
		}
		
		Collections.sort(list, new Comparator<SuffixPair>() {
			
			public int compare(SuffixPair o1, SuffixPair o2) {
				return o1.suffix.compareTo(o2.suffix);
			}
			
		});
		
		int[] result = new int[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i).pos;
		}
		
		return result;
	}
	
	/**
	 * Find the suffix array SA.
	 * Used approach from article "Linear Work Suffix Array Construction" 
	 * by Juha Karkkainen, Peter Sanders and Stefan Burkhardt. The method 
	 * signature and variable names are stored under the specified work. 
	 */
	public static void suffixArrayLinear(int[] s, int[] SA, int n, int K) {
		int n0 = (n + 2) / 3;
		int n1 = (n + 1) / 3;
		int n2 = n / 3;
		int n02 = n0 + n2;
		
		int[] s12 = new int[n02 + 3];
		int[] SA12 = new int[n02 + 3];
		int[] s0 = new int[n0];
		int[] SA0 = new int[n0];
		
		// Generate positions of mod 1 and mod 2 suffixes
		// the "+(n0-n1)" adds a dummy mod 1 suffix if n%3 == 1
		for (int i = 0, j = 0; i < n + (n0 - n1); i++) { 
			if (i%3 != 0) {
				s12[j++] = i;
			}
		}
		
		// LSB radix sort the mod 1 and mod 2 triples
		radixPass(s12, SA12, ArrayUtils.subarray(s, 2, s.length), n02, K);
		radixPass(SA12, s12, ArrayUtils.subarray(s, 1, s.length), n02, K);
		radixPass(s12, SA12, s, n02, K);
		
		// Find lexicographic names of triples
		int name = 0, c0 = -1, c1 = -1, c2 = -1;
		for (int i = 0; i < n02; i++) {
			if (s[SA12[i]] != c0 || s[SA12[i] + 1] != c1 || s[SA12[i] + 2] != c2) {
				name += 1;
				c0 = s[SA12[i]];
				c1 = s[SA12[i] + 1];
				c2 = s[SA12[i] + 2];
			}
			
			if (SA12[i] % 3 == 1) {
				s12[SA12[i]/3] = name;      // Left half
			} else {
				s12[SA12[i]/3 + n0] = name; // Right half
			}
		}
		
		// Recurse if names are not yet unique
		if (name < n02) {
			suffixArrayLinear(s12, SA12, n02, name);
			
			// Store unique names in s12 using the suffix array
			for (int i = 0; i < n02; i++) {
				s12[SA12[i]] = i + 1;
			}
		} else {
			// Generate the suffix array of s12 directly
			for (int i = 0; i < n02; i++) {
				SA12[s12[i] - 1] = i;
			}
		}
		
		// Stably sort the mod 0 suffixes from SA12 by their first character
		for (int i = 0, j = 0; i < n02; i++) {
			if (SA12[i] < n0) {
				s0[j++] = 3 * SA12[i];
			}
		}
		radixPass(s0, SA0, s, n0, K);
		
		// Merge sorted SA0 suffixes and sorted SA12 suffixes
		for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
			// Pos of current offset 12 suffix
			int i = getI(n0, SA12, t);
			
			// Pos of current offset 0 suffix
			int j = SA0[p];
			
			if (SA12[t] < n0 // Different compares for mod 1 and mod 2 suffixes
					? leq(s[i],           s12[SA12[t] + n0],     s[j],           s12[j/3])
					: leq(s[i], s[i + 1], s12[SA12[t] - n0 + 1], s[j], s[j + 1], s12[j/3 + n0]))
			{
				SA[k] = i;
				t += 1;
				if (t == n02) {
					// Done: only SA0 suffixes left
					for (k++; p < n0; p++, k++) {
						SA[k] = SA0[p];
					}
				}
			} else {
				SA[k] = j;
				p += 1;
				if (p == n0) {
					// Done: only SA12 suffixes left
					for (k++; t < n02; t++, k++) {
						SA[k] = getI(n0, SA12, t);
					}					
				}
			}
		}
	}

	private static int getI(int n0, int[] SA12, int t) {
		return SA12[t] < n0 ? SA12[t] * 3 + 1 : (SA12[t] - n0) * 3 + 2;
	}
	
	/**
	 * Lexicographic order for pairs
	 */
	private static boolean leq(int a1, int a2, int b1, int b2) {
		return a1 < b1 || a1 == b1 && a2 <= b2;
	}
	
	/**
	 * Lexicographic order for triples
	 */
	private static boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
		return a1 < b1 || a1 == b1 && leq(a2, a3, b2, b3);
	}
	
	/**
	 * Stably sort a[0 .. n - 1] to b[0 .. n - 1] with keys in 0 .. K from r
	 * 
	 * @param a - positions in r for sort
	 * @param b - sorted positions in r 
	 * @param r - source 
	 * @param n - number of positions in a and b
	 * @param K - size of alphabet
	 */
	public static void radixPass(int[] a, int[] b, int[] r, int n, int K) {
		int[] c = new int[K + 1];
		
		// Count occurrences
		for (int i = 0; i < n; i++) {
			c[r[a[i]]] += 1;
		}
		
		// Exclusive prefix sums
		for (int i = 0, sum = 0; i <= K; i++) {
			int t = c[i];
			c[i] = sum;
			sum += t;
		}
		
		// Sort
		for (int i = 0; i < n; i++) {
			b[c[r[a[i]]]++] = a[i];
		}
	}

}